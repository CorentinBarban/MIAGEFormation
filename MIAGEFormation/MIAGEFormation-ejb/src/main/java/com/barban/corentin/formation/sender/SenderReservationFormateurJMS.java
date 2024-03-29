/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.sender;

import DTO.FormateurDTO;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Corentin
 */
public class SenderReservationFormateurJMS implements MessageListener {
    
    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_RESERVATION_FORMATEUR";
    Destination dest = null;
    Session session = null;
    MessageProducer producer = null;
    
    public SenderReservationFormateurJMS() {
        
    }
    
    /**
     * Demander une resservation d'un formation
     * 
     * @param formateurReserve formateur Reserve
     */
    public void sendMessageDemandeReservation(FormateurDTO formateurReserve) {

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

            // Creation des objectMessage à envoyer
            ObjectMessage messageSalles = session.createObjectMessage(formateurReserve);
            messageSalles.setJMSReplyTo(tempDestFormateur);
            String correlationIdSalle = this.createRandomString();
            messageSalles.setJMSCorrelationID(correlationIdSalle);

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
    
    /**
     * Attente de la confirmation de reservation
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage object = (ObjectMessage) message;
        System.out.println("received formateur");
    }
    
}
