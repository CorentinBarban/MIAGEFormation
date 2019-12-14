/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.listenner;

import DTO.FormationDTO;
import com.barban.corentin.formation.entities.Formation;
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
import com.barban.corentin.formation.business.GestionFormationLocal;
import com.barban.corentin.formation.entities.Formationcompose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Corentin
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QUEUE_COMPTE_RENDU_DEMANDE")
    ,
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class ListennerDemandeCompteRendu implements MessageListener {

    @EJB
    private GestionFormationLocal gestionFormation;

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_FORMATION_DEMANDEE";
    Destination dest = null;
    Session session = null;
    MessageProducer replyProducer = null;

    public ListennerDemandeCompteRendu() {
        this.setupMessageQueueConsumer();
    }

    /**
     * Mise en place d'une file temporaire pour les retours d'informations
     */
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
            Logger.getLogger(ListennerDemandeCompteRendu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(ListennerDemandeCompteRendu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Attente de l'arrivé d'un message Gestion de la demande avec les
     * formations deja existantes
     *
     * @param message message
     */
    @Override
    public void onMessage(Message message) {
        try {
            List<FormationDTO> listeFormationDTO = new ArrayList();
            List<Formation> listFormations = this.gestionFormation.recupererInformationFormation();
            for (Formation formation : listFormations) {
                FormationDTO f = new FormationDTO();
                f.setDateformation(formation.getDateformation());
                f.setDateDemandeformation(formation.getFormationcomposeCollection().iterator().next().getStockagedemandeformation().getDatedemandeformation());
                int nbPersonnes = 0;
                List<String> listeClient = new ArrayList();
                for (Formationcompose formationcompose : formation.getFormationcomposeCollection()) {
                    nbPersonnes += formationcompose.getNbparticipants();
                    if (!listeClient.contains(formationcompose.getStockagedemandeformation().getNomclient())) {
                        listeClient.add(formationcompose.getStockagedemandeformation().getNomclient());
                    }

                }
                f.setListeClient(listeClient);
                f.setStatut(formation.getStatut());
                f.setNbpersonne(nbPersonnes);
                f.setCode(formation.getFormationcomposeCollection().iterator().next().getStockagedemandeformation().getCodeformationcatalogue());
                listeFormationDTO.add(f);
            }
            ObjectMessage messageCompterendu = session.createObjectMessage((Serializable) listeFormationDTO);
            messageCompterendu.setJMSCorrelationID(message.getJMSCorrelationID());
            this.replyProducer.send(message.getJMSReplyTo(), messageCompterendu);

        } catch (JMSException e) {
            Logger.getLogger(ListennerDemandeCompteRendu.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
