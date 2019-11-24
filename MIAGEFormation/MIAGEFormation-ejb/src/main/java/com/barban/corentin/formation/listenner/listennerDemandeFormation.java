/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.listenner;

import DTO.DemandeFormationDTO;
import DTO.SalleDTO;
import com.barban.corentin.formation.business.gestionFormationLocal;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
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
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.activemq.broker.BrokerService;

/**
 *
 * @author Corentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_FORMATION_DEMANDEE")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class listennerDemandeFormation implements MessageListener {

    @EJB
    private gestionFormationLocal gestionFormation;
    
    

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_FORMATION_DEMANDEE";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public listennerDemandeFormation() {
        try {
            BrokerService broker = new BrokerService();
            broker.setPersistent(false);
            broker.setUseJmx(false);
            broker.addConnector(factoryName);
            broker.start();
        } catch (Exception e) {
            //Handle the exception appropriately
        }
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
            MessageConsumer consumer = this.session.createConsumer(dest);
            consumer.setMessageListener(this);

        } catch (NamingException ex) {
            Logger.getLogger(listennerDemandeFormation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(listennerDemandeFormation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage response = this.session.createTextMessage();
            ObjectMessage text = (ObjectMessage) message;
            if (text.getObject() instanceof DemandeFormationDTO) {
                DemandeFormationDTO df = (DemandeFormationDTO) text.getObject();
                //Stocker la demande de formation
                Stockagedemandeformation sf = this.gestionFormation.stockerDemande(df.getCodeFormation(),df.getIntitule(),df.getCodeClient());
                System.out.println(sf.toString());
                // Creation de la formation
                this.gestionFormation.demanderFormation(df.getNomClient(),df.getNbPersonnes(),df.getDate(),df.getCodeFormation(),sf);
                
                System.out.println("Received: " + df.toString());
                response.setText("bien recu, je te renvoi la balle");
            }
            response.setJMSCorrelationID(message.getJMSCorrelationID());
            this.replyProducer.send(message.getJMSReplyTo(), response);
        } catch (JMSException e) {
            //Handle the exception appropriately
        }
    }

}
