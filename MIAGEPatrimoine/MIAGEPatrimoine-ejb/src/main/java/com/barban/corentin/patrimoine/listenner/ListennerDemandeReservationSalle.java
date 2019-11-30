/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.listenner;

import DTO.SalleDTO;
import Exceptions.SalleNotFoundException;
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
import com.barban.corentin.patrimoine.business.gestionPatrimoineLocal;

/**
 *
 * @author Corentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_RESERVATION_SALLE")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ListennerDemandeReservationSalle implements MessageListener {

    @EJB
    private gestionPatrimoineLocal gestionPatrimoine;

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_RESERVATION_SALLE";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public ListennerDemandeReservationSalle() {
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
            Logger.getLogger(ListennerDemandeReservationSalle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListennerDemandeReservationSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            
            ObjectMessage object = (ObjectMessage) message;
            if (object.getObject() instanceof SalleDTO) {
                SalleDTO s = (SalleDTO) object.getObject();
                System.out.println("sdsqd");
                this.gestionPatrimoine.editerStatutSalle(s.getIdsalle(), "PRESSENTIE", s.getDate());
                ObjectMessage response = this.session.createObjectMessage(s);
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                this.replyProducer.send(message.getJMSReplyTo(), response);
            }
        } catch (JMSException e) {
            Logger.getLogger(ListennerDemandeReservationSalle.class.getName()).log(Level.SEVERE, null, e);
        } catch (SalleNotFoundException ex) {
            Logger.getLogger(ListennerDemandeReservationSalle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
