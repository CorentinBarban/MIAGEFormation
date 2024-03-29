/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.sender;

import DTO.DemandeFormationDTO;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Corentin
 */
public class SenderDemandeFormationJMS implements MessageListener {

    Context context  = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_FORMATION_DEMANDEE";
    Destination dest = null;
    Session session = null;
    MessageProducer producer = null;

    public SenderDemandeFormationJMS() {
    }
    
    /**
     * Envoyer une demande de formation via JMS
     * 
     * @param demandeFormation Demande de formation
     */
    public void sendMessageDemandeFormation(DemandeFormationDTO demandeFormation) {

        try {
            // create the JNDI initial context.
            context = new InitialContext();
            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);
            // look up the Destination
            dest = (Destination) context.lookup(destName);
            // create the connection
            connection = factory.createConnection();
            // start the connection, to enable message sends
            connection.start();
            // create the session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create the sender
            producer = session.createProducer(dest);

            Destination tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);
            responseConsumer.setMessageListener(this);

            ObjectMessage message = session.createObjectMessage(demandeFormation);
            message.setJMSReplyTo(tempDest);
            String correlationId = this.createRandomString();
            message.setJMSCorrelationID(correlationId);
            producer.send(message);
        } catch (NamingException ex) {
            Logger.getLogger(SenderDemandeFormationJMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(SenderDemandeFormationJMS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Créer un string aléatoire 
     * @return string aléatoire 
     */
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
    
    /**
     * Attente de la message de confirmation de la prise en compte de demande de formation
     * @param message message
     */
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("messageText = " + textMessage.getText());
            } catch (JMSException ex) {
                Logger.getLogger(SenderDemandeFormationJMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
