/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.client;

import DTO.FormationDTO;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static jdk.internal.net.http.common.Log.headers;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

/**
 *
 * @author jdetr
 */
public class CréerDemande extends javax.swing.JFrame {

    private List<FormationDTO> listeFormations;

    /**
     * Creates new form CréerDemande
     *
     * @param formations
     */
    public CréerDemande(List<FormationDTO> formations) {
        initComponents();
        this.listeFormations = formations;
        this.remplirComboBoxFormation();
    }

    private void remplirComboBoxFormation() {
        this.CB_formation.removeAllItems();
        for (FormationDTO f : this.listeFormations) {
            this.CB_formation.addItem(f.getCode());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CB_formation = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TF_nom = new javax.swing.JTextField();
        TF_codeClient = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BTN_valider = new javax.swing.JButton();
        BTN_retour = new javax.swing.JButton();
        TF_nbPersonnes = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CB_formation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Demande de formation");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nom :");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Formation :");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Code client :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Nombre de personnes :");

        BTN_valider.setText("Valider");
        BTN_valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_validerActionPerformed(evt);
            }
        });

        BTN_retour.setText("Retour");
        BTN_retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_retourActionPerformed(evt);
            }
        });

        TF_nbPersonnes.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_valider)
                        .addGap(48, 48, 48)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BTN_retour)
                    .addComponent(TF_nom, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CB_formation, javax.swing.GroupLayout.Alignment.TRAILING, 0, 194, Short.MAX_VALUE)
                    .addComponent(TF_codeClient)
                    .addComponent(TF_nbPersonnes, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CB_formation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TF_nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(TF_codeClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TF_nbPersonnes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BTN_retour)
                    .addComponent(BTN_valider))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_retourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_retourActionPerformed
        MainMenu m = new MainMenu();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_retourActionPerformed

    private void BTN_validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_validerActionPerformed
        if (this.TF_codeClient.getText().isBlank() || this.CB_formation.getSelectedItem().toString().isBlank() || this.TF_nom.getText().isBlank() || this.TF_nbPersonnes.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
        } else if (Integer.parseInt(this.TF_nbPersonnes.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Le nombre de personnes inscrites doit être supérieur à 0.");
        } else {
            try {
                String codeF = this.CB_formation.getSelectedItem().toString();
                String intituleF = null;
                for (FormationDTO f : this.listeFormations) {
                    if (f.getCode().equals(codeF)) {
                        intituleF = f.getIntitule();
                    }
                }

                Map<String, Object> fields = new HashMap<>();
                fields.put("nomClient", this.TF_nom.getText());
                fields.put("codeFormation", codeF);
                fields.put("intitule", intituleF);
                fields.put("codeClient", this.TF_codeClient.getText());
                fields.put("nbPersonnes", this.TF_nbPersonnes.getText());
//                Unirest.put("http://localhost:8085/MIAGECommercial-web/webresources/demandeFormation").fields(fields).asJson();
               Unirest.put(("http://localhost:8085/MIAGECommercial-web/webresources/demandeFormation?nomClient=" + this.TF_nom.getText() + "&codeFormation=" + codeF + "&intitule=" + intituleF + "&codeClient=" + this.TF_codeClient.getText() + "&nbPersonnes=" + this.TF_nbPersonnes.getText()).replaceAll("\\s", "%20"))
                        .header("cache-control", "no-cache")
                        .asString();
//                try ( CloseableHttpClient httpclient = HttpClients.createDefault()) {
//                    HttpPut httpPut = new HttpPut("http://localhost:8085/MIAGECommercial-web/webresources/demandeFormation");
//                    httpPut.setHeader("Accept", "application/json");
//                    httpPut.setHeader("Content-type", "application/json");
//                    JSONObject json = new JSONObject();
//
//                    System.out.println("nomClient" + this.TF_nom.getText());
//                    System.out.println("codeFormation" + codeF);
//                    System.out.println("intitule" + intituleF);
//                    System.out.println("codeClient" + Integer.parseInt(this.TF_codeClient.getText()));
//                    System.out.println("nbPersonnes" + Integer.parseInt(this.TF_nbPersonnes.getText()));
//                   
//
//                    StringEntity se = new StringEntity(json.toString());
//                    httpPut.setEntity(se);
//
//                    ResponseHandler<String> responseHandler = response -> {
//                        int status = response.getStatusLine().getStatusCode();
//                        if (status >= 200 && status < 300) {
//                            HttpEntity entity = response.getEntity();
//                            return entity != null ? EntityUtils.toString(entity) : null;
//                        } else {
//                            throw new ClientProtocolException("Unexpected response status: " + status);
//                        }
//                    };
//                    String responseBody = httpclient.execute(httpPut, responseHandler);
//                    System.out.println("----------------------------------------");
//                    System.out.println(responseBody);
//                }
//                String adresse = "http://localhost:8085/MIAGECommercial-web/webresources/demandeFormation";
//                URL url = new URL(adresse);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("PUT");
//                conn.setRequestProperty("Content-type", "none");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//                writer.write("nomClient=" + this.TF_nom.getText() + "&codeFormation=" + codeF + "&intitule=" + intituleF + "&codeClient=" + this.TF_codeClient.getText() + "&nbPersonnes=" + this.TF_nbPersonnes.getText());
//                writer.flush();
//                writer.close();
//                os.close();
//                conn.connect();
//                if (conn.getResponseCode() != 200) {
//                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//                } else {
//                    MainMenu main = new MainMenu();
//                    JOptionPane.showMessageDialog(main, "La demande a bien été effectuée.");
//                    main.setVisible(true);
//                    this.dispose();
//                }
//                conn.disconnect();
//            } catch (IOException ex) {
//                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnirestException ex) {
                Logger.getLogger(CréerDemande.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BTN_validerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CréerDemande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CréerDemande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CréerDemande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CréerDemande.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CréerDemande(new ArrayList<FormationDTO>()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_retour;
    private javax.swing.JButton BTN_valider;
    private javax.swing.JComboBox<String> CB_formation;
    private javax.swing.JTextField TF_codeClient;
    private javax.swing.JFormattedTextField TF_nbPersonnes;
    private javax.swing.JTextField TF_nom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
