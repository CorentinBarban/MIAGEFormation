/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.patrimoine.listenner;

import DTO.SalleDTO;
import DTO.SallesDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "TOPIC_RESSOURCES_RESERVEES_SALLES")
    ,
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TOPIC_RESSOURCES_RESERVEES")
    ,
        @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "TOPIC_RESSOURCES_RESERVEES")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
    ,      
        @ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "JMSType='SALLES'")
})
public class ListennerDemandeRessourceDispoPatrimoine implements MessageListener {

    @EJB
    private gestionPatrimoineLocal gestionPatrimoine;

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "TOPIC_RESSOURCES_RESERVEES";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public ListennerDemandeRessourceDispoPatrimoine() {
        this.setupMessageQueueConsumer();
    }

    private void setupMessageQueueConsumer() {
        try {
            // Creation de la session pour la réponse retours
            this.context = new InitialContext();
            this.factory = (ConnectionFactory) context.lookup(factoryName);
            this.dest = (Destination) context.lookup(destName);
            this.connection = factory.createConnection();
            this.connection.start();
            this.session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            this.replyProducer = this.session.createProducer(null);
            this.replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        } catch (NamingException ex) {
            Logger.getLogger(ListennerDemandeRessourceDispoPatrimoine.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListennerDemandeRessourceDispoPatrimoine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage object = (ObjectMessage) message;
            if (object.getObject() instanceof HashMap) {
                HashMap<Date, List<SalleDTO>> listeSalleDate = (HashMap<Date, List<SalleDTO>>) object.getObject();
                Map.Entry<Date, List<SalleDTO>> entry = listeSalleDate.entrySet().iterator().next();
                Date date = entry.getKey();
                List<SalleDTO> listSalle = entry.getValue();
                List<SalleDTO> listeSallesDispo = this.gestionPatrimoine.listerSalleDisponible(listSalle, date);
                SallesDTO s = new SallesDTO();
                s.setListeSalle(listeSallesDispo);
                ObjectMessage response = session.createObjectMessage(s);
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                this.replyProducer.send(message.getJMSReplyTo(), response);
            }
        } catch (JMSException e) {
            //Handle the exception appropriately
        }
    }

}
