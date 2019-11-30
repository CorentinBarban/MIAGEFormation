/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.RH.listenner;

import DTO.FormateurDTO;
import DTO.FormateursDTO;
import com.barban.corentin.RH.business.gestionRHLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Corentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TOPIC_RESSOURCES_RESERVEES_FORMATEURS")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TOPIC_RESSOURCES_RESERVEES")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TOPIC_RESSOURCES_RESERVEES")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    ,      
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "JMSType = 'FORMATEURS'")
})
public class ListennerDemandeRessourceDispoRH implements MessageListener {

    @EJB
    private gestionRHLocal gestionRH;

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "TOPIC_RESSOURCES_RESERVEES";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public ListennerDemandeRessourceDispoRH() {

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
            Logger.getLogger(ListennerDemandeRessourceDispoRH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListennerDemandeRessourceDispoRH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage text = (ObjectMessage) message;
            if (text.getObject() instanceof HashMap) {
                HashMap<Date, List<FormateurDTO>> listeFormateur = (HashMap<Date, List<FormateurDTO>>) text.getObject();
                HashMap.Entry<Date, List<FormateurDTO>> entry = listeFormateur.entrySet().iterator().next();
                Date date = entry.getKey();
                List<FormateurDTO> listFormateur = entry.getValue();
                List<FormateurDTO> listeFormateurDispo = this.gestionRH.fournirPlanningFormateur(listFormateur, date);
                FormateursDTO f = new FormateursDTO();
                f.setListeFormateur(listFormateur);
                ObjectMessage response = session.createObjectMessage(f);
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                this.replyProducer.send(message.getJMSReplyTo(), response);
            }
        } catch (JMSException e) {
            //Handle the exception appropriately
        }
    }
}
