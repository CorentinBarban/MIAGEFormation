/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.formation.sender;

import DTO.DemandeFormationDTO;
import DTO.FormateurDTO;
import DTO.FormateursDTO;
import DTO.SalleDTO;
import DTO.SallesDTO;
import com.barban.corentin.formation.entities.Formation;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.barban.corentin.formation.business.GestionFormationLocal;
import com.barban.corentin.formation.entities.Formationcompose;
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private Stockagedemandeformation stockageDemandeFormation;
    private DemandeFormationDTO demandeFormationDTO;
    private HashMap<SalleDTO, List<Date>> listeSalles = null;
    private HashMap<FormateurDTO, List<Date>> listeFormateurs = null;

    public SenderDemandeRessourceDisponiblesJMS(Formation f, Stockagedemandeformation sf, DemandeFormationDTO df) {
        this.formation = f;
        this.stockageDemandeFormation = sf;
        this.demandeFormationDTO = df;
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
        ObjectMessage object = (ObjectMessage) message;

        try {
            if (object.getObject() instanceof SallesDTO) {
                System.out.println("Reception des Salles dispos");
                SallesDTO salles = (SallesDTO) object.getObject();
                this.listeSalles = salles.getHashMapDateSalle();
                System.out.println(listeSalles.toString());
                System.out.println("-------------------------------");

            } else if (object.getObject() instanceof FormateursDTO) {
                System.out.println("Reception des formateurs dispos");
                FormateursDTO formateurs = (FormateursDTO) object.getObject();
                this.listeFormateurs = formateurs.getListeFormateur();
                System.out.println(listeFormateurs.toString());
                System.out.println("-------------------------------");

            }

            if (this.listeFormateurs != null && this.listeSalles != null) {
                HashMap<FormateurDTO, SalleDTO> ressourceIdeal = trouverDateIdeale();
                
                Formationcompose fc = this.gestionFormation.demanderFormation(this.stockageDemandeFormation, this.formation, demandeFormationDTO.getNbPersonnes(), ressourceIdeal.entrySet().iterator().next().getValue().getDate(), ressourceIdeal.entrySet().iterator().next().getValue().getIdsalle(), ressourceIdeal.entrySet().iterator().next().getKey().getIdFormateur());
                SenderReservationSalleJMS senderResaSalle = new SenderReservationSalleJMS();
                senderResaSalle.sendMessageDemandeReservation(ressourceIdeal.entrySet().iterator().next().getValue());
                SenderReservationFormateurJMS senderResaFormateur = new SenderReservationFormateurJMS();
                senderResaFormateur.sendMessageDemandeReservation(ressourceIdeal.entrySet().iterator().next().getKey());
            }
        } catch (JMSException ex) {
            Logger.getLogger(SenderDemandeRessourceDisponiblesJMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Chercher la date ideal entre les formateurs disponibles et les salles
     * disponibles
     *
     * @return
     */
    private HashMap<FormateurDTO, SalleDTO> trouverDateIdeale() {
        HashMap<FormateurDTO, SalleDTO> hashmapRessourceChoisie = new HashMap<>();
        Date dateActuelle = new Date();
        for (Map.Entry<FormateurDTO, List<Date>> entry : this.listeFormateurs.entrySet()) {
            FormateurDTO keyFormateur = entry.getKey();
            List<Date> valueDateFormateur = entry.getValue();
            for (Date dateDispoFormateur : valueDateFormateur) {

                for (Map.Entry<SalleDTO, List<Date>> entrySalle : this.listeSalles.entrySet()) {
                    SalleDTO keySalle = entrySalle.getKey();
                    List<Date> valueDateSalle = entrySalle.getValue();

                    for (Date dateDispoSalle : valueDateSalle) {
                        if ((dateDispoFormateur.compareTo(dateDispoSalle) == 0) && dateDispoFormateur.compareTo(dateActuelle) > 0) {
                            FormateurDTO f = new FormateurDTO();
                            f.setIdFormateur(keyFormateur.getIdFormateur());
                            f.setDate(dateDispoFormateur);
                            f.setStatut("PRESSENTI");
                            SalleDTO s = new SalleDTO();
                            s.setIdsalle(keySalle.getIdsalle());
                            s.setDate(dateDispoSalle);
                            s.setStatut("PRESSENTIE");
                            hashmapRessourceChoisie.put(f, s);
                            return hashmapRessourceChoisie;
                        }
                    }
                }
            }
        }
        return null;
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
