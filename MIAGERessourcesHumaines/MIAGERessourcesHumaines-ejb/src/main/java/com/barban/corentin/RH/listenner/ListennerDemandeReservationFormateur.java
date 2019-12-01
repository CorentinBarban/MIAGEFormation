/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.listenner;

import DTO.FormateurDTO;
import Exceptions.FormateurNotFoundException;
import com.barban.corentin.RH.business.gestionRHLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
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
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_RESERVATION_FORMATEUR")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ListennerDemandeReservationFormateur implements MessageListener {

    @EJB
    private gestionRHLocal gestionRH;

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_RESERVATION_FORMATEUR";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public ListennerDemandeReservationFormateur() {
        this.setupMessageQueueConsumer();
    }

    private void setupMessageQueueConsumer() {
        try {
            this.context = new InitialContext();
            this.factory = (ConnectionFactory) context.lookup(factoryName);
            this.dest = (Destination) context.lookup(destName);
            this.connection = factory.createConnection();
            this.connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        } catch (NamingException ex) {
            Logger.getLogger(ListennerDemandeReservationFormateur.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListennerDemandeReservationFormateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {

            ObjectMessage object = (ObjectMessage) message;
            System.out.println("Je suis dans resa formateur");
            if (object.getObject() instanceof FormateurDTO) {
                FormateurDTO f = (FormateurDTO) object.getObject();
                this.gestionRH.modifierStatutFormateur(f.getIdFormateur(), f.getStatut(), f.getDate());
                ObjectMessage response = this.session.createObjectMessage(f);
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                this.replyProducer.send(message.getJMSReplyTo(), response);
            }
        } catch (JMSException e) {
            Logger.getLogger(ListennerDemandeReservationFormateur.class.getName()).log(Level.SEVERE, null, e);
        } catch (FormateurNotFoundException ex) {
            Logger.getLogger(ListennerDemandeReservationFormateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
