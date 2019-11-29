/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.sender;

import DTO.DemandeFormationDTO;
import DTO.FormateurDTO;
import DTO.SalleDTO;
import java.io.Serializable;
import java.util.List;
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
public class SenderDemandeRessourceDisponiblesJMS implements MessageListener {

    Context context  = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "TOPIC_RESSOURCES_RESERVEES";
    Destination dest = null;
    Session session = null;
    MessageProducer producer = null;

    public SenderDemandeRessourceDisponiblesJMS() {
    }
    
    public void sendMessageDemandeRessource(List<FormateurDTO> listDemandeFormateurDispo, List<SalleDTO> listDemandeSalleDispo) {

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
            
            // Creation des files de retours
            Destination tempDestFormateur = session.createTemporaryQueue();
            MessageConsumer responseConsumerFormateur = session.createConsumer(tempDestFormateur);
            responseConsumerFormateur.setMessageListener(this);
            
            Destination tempDestSalle = session.createTemporaryQueue();
            MessageConsumer responseConsumerSalle = session.createConsumer(tempDestSalle);
            responseConsumerSalle.setMessageListener(this);
            
            // Creation des objectMessage à envoyer
            ObjectMessage messageFormateurs = session.createObjectMessage((Serializable) listDemandeFormateurDispo);
            messageFormateurs.setJMSReplyTo(tempDestFormateur);
            String correlationIdFormateur = this.createRandomString();
            messageFormateurs.setJMSCorrelationID(correlationIdFormateur);
            messageFormateurs.setJMSType("FORMATEURS");
            
            ObjectMessage messageSalles = session.createObjectMessage((Serializable) listDemandeSalleDispo);
            messageSalles.setJMSReplyTo(tempDestSalle);
            String correlationIdSalle = this.createRandomString();
            messageSalles.setJMSCorrelationID(correlationIdSalle);
            messageSalles.setJMSType("SALLES");
            
            
            // Envoi des messages
            producer.send(messageFormateurs);
            producer.send(messageSalles);
            
        } catch (NamingException ex) {
            Logger.getLogger(SenderDemandeRessourceDisponiblesJMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(SenderDemandeRessourceDisponiblesJMS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }

    @Override
    public void onMessage(Message message) {
        String messageText = null;
        if (message instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) message;
                messageText = textMessage.getText();
                System.out.println("messageTextRessources = " + messageText);
            } catch (JMSException ex) {
                Logger.getLogger(SenderDemandeRessourceDisponiblesJMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
