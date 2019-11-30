/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.sender;

import DTO.FormateurDTO;
import DTO.FormateursDTO;
import DTO.SalleDTO;
import DTO.SallesDTO;
import com.barban.corentin.formation.entities.Formation;
import com.barban.corentin.formation.repositories.FormationFacadeLocal;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import com.barban.corentin.formation.business.GestionFormationLocal;

/**
 *
 * @author Corentin
 */
public class SenderDemandeRessourceDisponiblesJMS implements MessageListener {

    private GestionFormationLocal gestionFormation = lookupgestionFormationLocal();
    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "TOPIC_RESSOURCES_RESERVEES";
    Destination dest = null;
    Session session = null;
    MessageProducer producer = null;
    private Formation formation;

    public SenderDemandeRessourceDisponiblesJMS(Formation f) {
        this.formation = f;
    }

    public void sendMessageDemandeRessource(List<FormateurDTO> listDemandeFormateurDispo, List<SalleDTO> listDemandeSalleDispo, Date DateDipo) {

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
            HashMap<Date, List<FormateurDTO>> hashmapFormateur = new HashMap<>();
            hashmapFormateur.put(DateDipo, listDemandeFormateurDispo);
            // Creation des objectMessage à envoyer
            ObjectMessage messageFormateurs = session.createObjectMessage((Serializable) hashmapFormateur);
            messageFormateurs.setJMSReplyTo(tempDestFormateur);
            String correlationIdFormateur = this.createRandomString();
            messageFormateurs.setJMSCorrelationID(correlationIdFormateur);
            messageFormateurs.setJMSType("FORMATEURS");

            HashMap<Date, List<SalleDTO>> hashmapSalle = new HashMap<>();
            hashmapSalle.put(DateDipo, listDemandeSalleDispo);
            ObjectMessage messageSalles = session.createObjectMessage((Serializable) hashmapSalle);
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
        ObjectMessage object = (ObjectMessage) message;
        try {
            if (object.getObject() instanceof SallesDTO) {
                SallesDTO salles = (SallesDTO) object.getObject();
                List<SalleDTO> listeSalles = salles.getListeSalle();
                this.gestionFormation.ajouterSalleFormation(this.formation.getIdformation(), listeSalles.get(0).getIdsalle());
                SalleDTO s = new SalleDTO();
                s.setDate(this.formation.getDateformation());
                s.setIdsalle(listeSalles.get(0).getIdsalle());
                //Demande de changement de statut
                SenderReservationSalleJMS senderResaSalle = new SenderReservationSalleJMS();
                senderResaSalle.sendMessageDemandeReservation(s);

            } else if (object.getObject() instanceof FormateursDTO) {
                FormateursDTO formateurs = (FormateursDTO) object.getObject();
                List<FormateurDTO> listeFormateurs = formateurs.getListeFormateur();
                System.out.println(this.formation.getIdformation());
                System.out.println(listeFormateurs.get(0).getIdFormateur());
                this.gestionFormation.ajouterFormateurFormation(this.formation.getIdformation(), listeFormateurs.get(0).getIdFormateur());
                FormateurDTO f = new FormateurDTO();
                f.setDate(this.formation.getDateformation());
                f.setIdFormateur(listeFormateurs.get(0).getIdFormateur());
                //Demande de changement de statut
                SenderReservationFormateurJMS senderResaFormateur = new SenderReservationFormateurJMS();
                senderResaFormateur.sendMessageDemandeReservation(f);
                //Demande de change de statut
            }
        } catch (JMSException ex) {
            Logger.getLogger(SenderDemandeRessourceDisponiblesJMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private GestionFormationLocal lookupgestionFormationLocal() {
        try {
            Context c = new InitialContext();
            return (GestionFormationLocal) c.lookup("java:global/MIAGEFormation-ear/MIAGEFormation-ejb-1.0-SNAPSHOT/GestionFormation!com.barban.corentin.formation.business.GestionFormationLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
