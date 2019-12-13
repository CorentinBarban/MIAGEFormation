/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barban.corentin.client;

import DTO.FormateurDTO;
import DTO.FormationDTO;
import DTO.SalleDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jdetr
 */
public class GérerRessourcesFormation extends javax.swing.JFrame {

    private boolean isPatrimoine;
    private List<FormateurDTO> allFormateurs;
    private List<SalleDTO> allSalles;
    private List<FormateurDTO> formateursOfFormation;
    private List<SalleDTO> sallesOfFormation;
    private String codeF;
    private ArrayList<SalleDTO> sallesOld;
    private ArrayList<FormateurDTO> formateursOld;
    
    /**
     * Creates new form GérerRessourcesFormation
     */
    public GérerRessourcesFormation(boolean isPatrimoine, String code) {
        this.isPatrimoine = isPatrimoine;
        this.codeF = code;
        initComponents();
        if (!this.isPatrimoine) {
            this.jLabel1.setText("Gestion des formateurs compétents");
            this.formateursOld = new ArrayList<>();
            this.formateursOfFormation = new ArrayList<>();
        } else {
            this.jLabel1.setText("Gestion des salles adéquates");
            this.sallesOld = new ArrayList<>();
            this.sallesOfFormation = new ArrayList<>();
        }
        initListes();
        
    }
    
    private void initListes() {
        if (this.isPatrimoine) {
            allSalles = new ArrayList<>();
            sallesOfFormation = new ArrayList<>();
            
            try {
                // Deuxième GET pour récupérer les salles déjà adéquates pour la formation
                URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + this.codeF + "/salles");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                Gson gson = new Gson();

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                } else {
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    java.lang.reflect.Type typeMyType = new TypeToken<ArrayList<SalleDTO>>() {
                    }.getType();
                    while ((output = br.readLine()) != null) {
                        this.sallesOfFormation = gson.fromJson(output, typeMyType);
                    }
                }
                conn.disconnect();
                
                // Premier get pour récupérer toutes les salles
                url = new URL("http://localhost:8085/MIAGEPatrimoine-web/webresources/salles");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                } else {
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    java.lang.reflect.Type typeMyType = new TypeToken<ArrayList<SalleDTO>>() {
                    }.getType();
                    while ((output = br.readLine()) != null) {
                        this.allSalles = gson.fromJson(output, typeMyType);
                    }
                }
                conn.disconnect();
                
                // On enlève de la liste d'ajout les salles déjà adéquates et on insère dans la bonne jList
                this.chargerListes();
                
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GérerRessourcesFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            allFormateurs = new ArrayList<>();
            formateursOfFormation = new ArrayList<>();
            
            try {
                // Premier GET pour récupérer les salles déjà adéquates pour la formation
                URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + this.codeF + "/formateurs");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                Gson gson = new Gson();

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                } else {
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    java.lang.reflect.Type typeMyType = new TypeToken<ArrayList<FormateurDTO>>() {
                    }.getType();
                    while ((output = br.readLine()) != null) {
                        this.formateursOfFormation = gson.fromJson(output, typeMyType);
                    }
                }
                conn.disconnect();
                
                // Deuxième get pour récupérer toutes les salles
                url = new URL("http://localhost:8085/MIAGERessourcesHumaines-web/webresources/formateurs");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                } else {
                    InputStreamReader in = new InputStreamReader(conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    java.lang.reflect.Type typeMyType = new TypeToken<ArrayList<FormateurDTO>>() {
                    }.getType();
                    while ((output = br.readLine()) != null) {
                        this.allFormateurs = gson.fromJson(output, typeMyType);
                    }
                }
                conn.disconnect();
                
                // On enlève de la liste d'ajout les salles déjà adéquates et on insère dans la bonne jList
                this.chargerListes();
                
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(GérerRessourcesFormation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    private void chargerListes() throws InterruptedException {
        if (isPatrimoine) {
            if (!this.sallesOfFormation.isEmpty()) {
                for (SalleDTO s : this.sallesOfFormation) {
                    this.sallesOld.add(s);
                }
            }
            DefaultListModel model = new DefaultListModel();
            DefaultListModel modelAll =  new DefaultListModel();
            List<SalleDTO> temp = new ArrayList<>();
            for (SalleDTO s : this.allSalles) {
                for (SalleDTO sf : this.sallesOfFormation) {
                    if (sf.getIdsalle().equals(s.getIdsalle())) {
                        temp.add(s);
                    }
                }
            }
            this.sallesOfFormation = temp;
            System.out.println("1 : " + this.sallesOfFormation.toString());
            this.allSalles.removeAll(this.sallesOfFormation);
            System.out.println("2 : " + this.sallesOfFormation.toString());
            for (SalleDTO s : this.allSalles) {
                modelAll.addElement(s.getNom());    
            }
            for (SalleDTO sf : this.sallesOfFormation) {
                model.addElement(sf.getNom());
            }              
            this.L_AllRessources.setModel(modelAll);
            this.L_RessourcesOfFormation.setModel(model);
        } else {
            if (!this.formateursOfFormation.isEmpty()) {
                for (FormateurDTO f : this.formateursOfFormation) {
                    this.formateursOld.add(f);
                }
            }
            DefaultListModel model = new DefaultListModel();
            DefaultListModel modelAll =  new DefaultListModel();
            List<FormateurDTO> temp = new ArrayList<>();
            for (FormateurDTO f : this.allFormateurs) {
                for (FormateurDTO ff : this.formateursOfFormation) {
                    if (ff.getIdFormateur() == f.getIdFormateur()) {
                        temp.add(f);
                    }
                }
            }
            this.formateursOfFormation = temp;
            this.allFormateurs.removeAll(this.formateursOfFormation);
            for (FormateurDTO f : this.allFormateurs) {
                modelAll.addElement(f.getNom());    
            }
            for (FormateurDTO ff : this.formateursOfFormation) {
                model.addElement(ff.getNom());
            }              
            this.L_AllRessources.setModel(modelAll);
            this.L_RessourcesOfFormation.setModel(model);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        L_RessourcesOfFormation = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        L_AllRessources = new javax.swing.JList<>();
        BTN_Ajouter = new javax.swing.JButton();
        BTN_Enlever = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        BTN_Valider = new javax.swing.JButton();
        BTN_Retour = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        L_RessourcesOfFormation.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        L_RessourcesOfFormation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L_RessourcesOfFormationMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(L_RessourcesOfFormation);

        L_AllRessources.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        L_AllRessources.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                L_AllRessourcesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(L_AllRessources);

        BTN_Ajouter.setText(">");
        BTN_Ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AjouterActionPerformed(evt);
            }
        });

        BTN_Enlever.setText("<");
        BTN_Enlever.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EnleverActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Gestion des ressources");

        BTN_Valider.setText("Valider");
        BTN_Valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ValiderActionPerformed(evt);
            }
        });

        BTN_Retour.setText("Retour");
        BTN_Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_RetourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BTN_Valider)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BTN_Ajouter)
                            .addComponent(BTN_Enlever))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BTN_Retour))))
                .addGap(57, 57, 57))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(BTN_Ajouter)
                        .addGap(40, 40, 40)
                        .addComponent(BTN_Enlever)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BTN_Valider)
                            .addComponent(BTN_Retour))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_RetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_RetourActionPerformed
        MainMenu main = new MainMenu();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_RetourActionPerformed

    private void L_RessourcesOfFormationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L_RessourcesOfFormationMouseClicked

    }//GEN-LAST:event_L_RessourcesOfFormationMouseClicked

    private void L_AllRessourcesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_L_AllRessourcesMouseClicked

    }//GEN-LAST:event_L_AllRessourcesMouseClicked

    private void BTN_AjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AjouterActionPerformed
        if (this.isPatrimoine) {
            if (this.L_AllRessources.getSelectedValue() != null) {
                String nom = this.L_AllRessources.getSelectedValue();
                SalleDTO s = null;
                for (SalleDTO sliste : this.allSalles) {
                    if (sliste.getNom().equals(nom)) {
                        s = sliste;
                    }
                }
                this.sallesOfFormation.add(s);
                this.allSalles.remove(s);
                DefaultListModel model = new DefaultListModel();
                DefaultListModel modelAll = new DefaultListModel();
                for (SalleDTO salle : this.allSalles) {
                    modelAll.addElement(salle.getNom());
                }
                for (SalleDTO salle : this.sallesOfFormation) {
                    model.addElement(salle.getNom());
                }
                this.L_AllRessources.setModel(modelAll);
                this.L_RessourcesOfFormation.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vous devez sélectionner un élément de la liste de gauche.");
            }
        } else {
            if (this.L_AllRessources.getSelectedValue() != null) {
                String nom = this.L_AllRessources.getSelectedValue();
                FormateurDTO f = null;
                for (FormateurDTO fliste : this.allFormateurs) {
                    if (fliste.getNom().equals(nom)) {
                        f = fliste;
                    }
                }
                this.formateursOfFormation.add(f);
                this.allFormateurs.remove(f);
                DefaultListModel model = new DefaultListModel();
                DefaultListModel modelAll = new DefaultListModel();
                for (FormateurDTO formateur : this.allFormateurs) {
                    modelAll.addElement(formateur.getNom());
                }
                for (FormateurDTO formateur : this.formateursOfFormation) {
                    model.addElement(formateur.getNom());
                }
                this.L_AllRessources.setModel(modelAll);
                this.L_RessourcesOfFormation.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vous devez sélectionner un élément de la liste de gauche.");
            }
        }
    }//GEN-LAST:event_BTN_AjouterActionPerformed

    private void BTN_EnleverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EnleverActionPerformed
        if (this.isPatrimoine) {
            if (this.L_RessourcesOfFormation.getSelectedValue() != null) {
                String nom = this.L_RessourcesOfFormation.getSelectedValue();
                SalleDTO s = null;
                for (SalleDTO sliste : this.sallesOfFormation) {
                    if (sliste.getNom().equals(nom)) {
                        s = sliste;
                    }
                }
                this.sallesOfFormation.remove(s);
                this.allSalles.add(s);
                DefaultListModel model = new DefaultListModel();
                DefaultListModel modelAll = new DefaultListModel();
                for (SalleDTO salle : this.allSalles) {
                    modelAll.addElement(salle.getNom());
                }
                for (SalleDTO salle : this.sallesOfFormation) {
                    model.addElement(salle.getNom());
                }
                this.L_AllRessources.setModel(modelAll);
                this.L_RessourcesOfFormation.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vous devez sélectionner un élément dans la liste de droite.");
            }
        } else {
            if (this.L_RessourcesOfFormation.getSelectedValue() != null) {
                String nom = this.L_RessourcesOfFormation.getSelectedValue();
                FormateurDTO f = null;
                for (FormateurDTO fliste : this.formateursOfFormation) {
                    if (fliste.getNom().equals(nom)) {
                        f = fliste;
                    }
                }
                this.formateursOfFormation.remove(f);
                this.allFormateurs.add(f);
                DefaultListModel model = new DefaultListModel();
                DefaultListModel modelAll = new DefaultListModel();
                for (FormateurDTO formateur : this.allFormateurs) {
                    modelAll.addElement(formateur.getNom());
                }
                for (FormateurDTO formateur : this.formateursOfFormation) {
                    model.addElement(formateur.getNom());
                }
                this.L_AllRessources.setModel(modelAll);
                this.L_RessourcesOfFormation.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "Vous devez sélectionner un élément dans la liste de droite.");
            }
        }
    }//GEN-LAST:event_BTN_EnleverActionPerformed

    private void BTN_ValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ValiderActionPerformed
        if (isPatrimoine) {
            try {
                if (!this.sallesOld.isEmpty()) {
                    for (SalleDTO s : this.sallesOld) {
                        URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + this.codeF + "/salle/" + s.getIdsalle().toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("DELETE");
                        conn.connect();

                        if (conn.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                        }
                        conn.disconnect();
                    }
                }
                if (!this.sallesOfFormation.isEmpty()) {
                    for (SalleDTO s : this.sallesOfFormation) {
                        URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + this.codeF + "/salle/" + s.getIdsalle().toString());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.connect();

                        if (conn.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                        } 
                        conn.disconnect();
                    }
                }
                MainMenu main = new MainMenu();
                main.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                if (!this.formateursOld.isEmpty()) {
                    for (FormateurDTO f : this.formateursOld) {
                        URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + this.codeF + "/formateur/" + f.getIdFormateur());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("DELETE");
                        conn.connect();

                        if (conn.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                        }
                        conn.disconnect();
                    }
                }
                if (!this.formateursOfFormation.isEmpty()) {
                    for (FormateurDTO f : this.formateursOfFormation) {
                        URL url = new URL("http://localhost:8085/MIAGETechnicoCommercial-web/webresources/formationsCatalogue/" + this.codeF + "/formateur/" + f.getIdFormateur());
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.connect();

                        if (conn.getResponseCode() != 200) {
                            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                        } 
                        conn.disconnect();
                    }
                }
                MainMenu main = new MainMenu();
                main.setVisible(true);
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_BTN_ValiderActionPerformed

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
            java.util.logging.Logger.getLogger(GérerRessourcesFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GérerRessourcesFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GérerRessourcesFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GérerRessourcesFormation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GérerRessourcesFormation(true, "toto").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Ajouter;
    private javax.swing.JButton BTN_Enlever;
    private javax.swing.JButton BTN_Retour;
    private javax.swing.JButton BTN_Valider;
    private javax.swing.JList<String> L_AllRessources;
    private javax.swing.JList<String> L_RessourcesOfFormation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
