/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.commercial.sender;

import DTO.CompteRenduDTO;
import DTO.FormationDTO;
import com.barban.corentin.commercial.business.gestionCommercialeLocal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Corentin
 */
public class SenderDemandeCompteRendu implements MessageListener {

    Context context = null;
    ConnectionFactory factory = null;
    Connection connection = null;
    String factoryName = "ConnectionFactory";
    String destName = "QUEUE_COMPTE_RENDU_DEMANDE";
    Destination dest = null;
    Session session = null;
    MessageProducer producer = null;
    private gestionCommercialeLocal gestionCommerciale = lookupgestionCommercialeLocal();

    public SenderDemandeCompteRendu() {
    }

    /**
     * Envoyer une demande d'edition de compte rendu
     *
     */
    public void sendMessageDemandeInformationFormations() {

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

            Destination tempDest = session.createTemporaryQueue();
            MessageConsumer responseConsumer = session.createConsumer(tempDest);
            responseConsumer.setMessageListener(this);
            TextMessage demande = this.session.createTextMessage();
            demande.setText("Demande Edition compte");
            demande.setJMSReplyTo(tempDest);
            String correlationId = this.createRandomString();
            demande.setJMSCorrelationID(correlationId);
            producer.send(demande);
        } catch (NamingException ex) {
            Logger.getLogger(SenderDemandeCompteRendu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JMSException ex) {
            Logger.getLogger(SenderDemandeCompteRendu.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }

    /**
     * Attente de la message de confirmation de la prise en compte de demande de
     * formation
     *
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        String messageText = null;
        try {
            ObjectMessage reponse = (ObjectMessage) message;
            List<FormationDTO> listFormation = (List<FormationDTO>) reponse.getObject();
            for (FormationDTO formation : listFormation) {
                FormationDTO formationCatalogue = this.gestionCommerciale.recupererInformationFormationCatalogue(formation.getCode());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                int nbPersonnes = formation.getNbpersonne();
                int capaciteMin = formationCatalogue.getCapacitemin();
                Calendar dateJour30 = Calendar.getInstance();
                dateJour30.setTime(formation.getDateDemandeformation());
                dateJour30.add(Calendar.DATE, 30);
                Date jour = new Date();
                if ((nbPersonnes < capaciteMin) || ((dateFormat.format(jour).compareTo(dateFormat.format(dateJour30.getTime())) > 0) && !formation.getStatut().equals("PLANIFIEE"))) {
                    CompteRenduDTO cr = new CompteRenduDTO(formation.getIntitule(), formation.getDateformation(), formation.getListeClient(), "Négatif", formation.getNbpersonne());
                    System.out.println(cr.toString());
                } else {
                    CompteRenduDTO cr = new CompteRenduDTO(formation.getIntitule(), formation.getDateformation(), formation.getListeClient(), "Positif", formation.getNbpersonne());
                    System.out.println(cr.toString());
                }
            }
        } catch (JMSException ex) {
            Logger.getLogger(SenderDemandeCompteRendu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private gestionCommercialeLocal lookupgestionCommercialeLocal() {
        try {
            Context c = new InitialContext();
            return (gestionCommercialeLocal) c.lookup("java:global/MIAGECommercial-ear/MIAGECommercial-ejb-1.0-SNAPSHOT/gestionCommerciale");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
