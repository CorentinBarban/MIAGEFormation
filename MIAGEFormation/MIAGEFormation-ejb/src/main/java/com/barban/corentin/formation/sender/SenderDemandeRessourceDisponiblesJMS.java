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
import com.barban.corentin.formation.entities.Stockagedemandeformation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
    
    /**
     * Envoyer une demande de ressources
     * @param listDemandeFormateurDispo
     * @param listDemandeSalleDispo 
     */
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
    
    /**
     * Creation d'une chaine de caractere random
     * @return 
     */
    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
    
    
    /**
     * Attente de la réponse de demande de ressources
     * @param message 
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage object = (ObjectMessage) message;

        try {
            if (object.getObject() instanceof SallesDTO) {
                System.out.println("Reception des Salles dispos");
                SallesDTO salles = (SallesDTO) object.getObject();
                this.listeSalles = salles.getHashMapDateSalle();
                System.out.println("Liste Salles dispo : " + listeSalles.toString());
                System.out.println("-------------------------------");

            } else if (object.getObject() instanceof FormateursDTO) {
                System.out.println("Reception des formateurs dispos");
                FormateursDTO formateurs = (FormateursDTO) object.getObject();
                this.listeFormateurs = formateurs.getListeFormateur();
                System.out.println("Liste Formateur dispo" + listeFormateurs.toString());
                System.out.println("-------------------------------");

            }

            if (this.listeFormateurs != null && this.listeSalles != null) {
                HashMap<FormateurDTO, SalleDTO> ressourceIdeal = trouverDateIdeale(this.demandeFormationDTO.getTypeFormation());
                System.out.println("DateIdeal = " + ressourceIdeal.entrySet().iterator().next().getValue().getDate());
                this.gestionFormation.ajouterFormateurFormation(this.formation, ressourceIdeal.entrySet().iterator().next().getKey().getIdFormateur());
                this.gestionFormation.ajouterDateFormation(this.formation, ressourceIdeal.entrySet().iterator().next().getValue().getDate());
                this.gestionFormation.ajouterSalleFormation(this.formation, ressourceIdeal.entrySet().iterator().next().getValue().getIdsalle());

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
    private HashMap<FormateurDTO, SalleDTO> trouverDateIdeale(String typeFormation) {
        int joursConsecutif = 0;
        if (typeFormation.equals("court")) {
            joursConsecutif = 3;
        } else {
            joursConsecutif = 5;
        }
        HashMap<FormateurDTO, List<List<Date>>> listDateConsecutiveFormateur = new HashMap<>();
        HashMap<SalleDTO, List<List<Date>>> listDateConsecutiveSalle = new HashMap<>();
        HashMap<FormateurDTO, SalleDTO> hashmapRessourceChoisie = new HashMap<>();

        // Recherche de la date de disponibilité au plus tot pour chaque formateur
        for (Map.Entry<FormateurDTO, List<Date>> entry : this.listeFormateurs.entrySet()) {
            FormateurDTO keyFormateur = entry.getKey();
            List<Date> valueDateFormateur = entry.getValue();
            List<List<Date>> l = consecutiveDates(valueDateFormateur, joursConsecutif);
            listDateConsecutiveFormateur.put(keyFormateur, l);
        }

        for (Map.Entry<SalleDTO, List<Date>> entrySalle : this.listeSalles.entrySet()) {
            SalleDTO keySalle = entrySalle.getKey();
            List<Date> valueDateSalle = entrySalle.getValue();
            listDateConsecutiveSalle.put(keySalle, consecutiveDates(valueDateSalle, joursConsecutif));
        }

        for (Map.Entry<FormateurDTO, List<List<Date>>> entry : listDateConsecutiveFormateur.entrySet()) {
            FormateurDTO key = entry.getKey();
            List<List<Date>> value = entry.getValue();
            for (List<Date> listDatePossibleFormateur : value) {
                for (Map.Entry<SalleDTO, List<List<Date>>> entrySalle : listDateConsecutiveSalle.entrySet()) {
                    SalleDTO keyS = entrySalle.getKey();
                    List<List<Date>> valueSalle = entrySalle.getValue();
                    if (valueSalle.contains(listDatePossibleFormateur) && listDatePossibleFormateur.get(0).compareTo(new Date()) > 0) {
                        FormateurDTO f = new FormateurDTO();
                        f.setIdFormateur(key.getIdFormateur());
                        f.setDate(listDatePossibleFormateur.get(0));
                        
                        
                        SalleDTO s = new SalleDTO();
                        s.setIdsalle(keyS.getIdsalle());
                        s.setDate(listDatePossibleFormateur.get(0));
                        if(this.formation.getStatut().equals("PLANIFIEE")){
                            f.setStatut("PLANIFIEE");
                            s.setStatut("PLANIFIEE");
                        }else{
                            f.setStatut("PRESSENTI");
                            s.setStatut("PRESSENTIE");
                        }
                        
                        hashmapRessourceChoisie.put(f, s);
                        return hashmapRessourceChoisie;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Obtenir la liste de toutes les n dates consecutives
     *
     * @param dates
     * @param nbconsecutive
     * @return
     */
    public List<List<Date>> consecutiveDates(List<Date> dates, Integer nbconsecutive) {
        List<List<Date>> listJoursConsecutif = new ArrayList();
        List<Date> consecutive = new ArrayList();
        consecutive.add(new Date(0));
        for (Date current : dates) {
            Date previous = consecutive.get(consecutive.size() - 1);
            if (previous.before(current) && (current.getTime() - previous.getTime() == 1000 * 60 * 60 * 24)) {
                consecutive.add(current);
            } else {
                consecutive.clear();
                consecutive.add(current);
            }
            if (consecutive.size() == nbconsecutive) {
                listJoursConsecutif.add(new ArrayList(consecutive));
                consecutive.clear();
                consecutive.add(current);
            }
        }
        return listJoursConsecutif;
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
