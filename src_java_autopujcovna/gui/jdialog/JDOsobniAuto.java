/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jdialog;

import auta.Barva;
import auta.IAuto;
import auta.Osobni;
import java.awt.Color;
import pobocka.EnumPozice;

/**
 *
 * @author J3BY
 */
public class JDOsobniAuto extends javax.swing.JDialog {

    private String spz;
    private int stavTachometru, pocetVypujceni;
    private Barva barva = null;
    private IAuto osobni;
    private boolean vytvorit = true;

    /**
     * Creates new form OsobniAuto
     */
    public JDOsobniAuto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        predvypln();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupPozice = new javax.swing.ButtonGroup();
        jButtonOK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldSPZ = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTachometr = new javax.swing.JTextField();
        jTextFieldPocetPujceni = new javax.swing.JTextField();
        jComboBoxBarva = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vlož osobní automobil");

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jLabel1.setText("Barva:");

        jLabel3.setText("SPZ:");

        jTextFieldSPZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldSPZ.setText("ST-39740");

        jLabel4.setText("Stav tachometru:");

        jLabel5.setText("Počet vypůjčení:");

        jTextFieldTachometr.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTachometr.setText("100000");

        jTextFieldPocetPujceni.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldPocetPujceni.setText("5");

        jComboBoxBarva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BILA", "MODRA", "CERVENA", "CERNA", "ZELENA", "HNEDA", "ZLUTA", " " }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxBarva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldTachometr, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPocetPujceni, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldSPZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxBarva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldTachometr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldPocetPujceni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jButtonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        barva = Barva.valueOf(jComboBoxBarva.getSelectedItem().toString());
        spz = jTextFieldSPZ.getText();
        stavTachometru = Integer.valueOf(jTextFieldTachometr.getText());
        pocetVypujceni = Integer.valueOf(jTextFieldPocetPujceni.getText());
        if (vytvorit) {
            osobni = new Osobni(barva, spz, stavTachometru, pocetVypujceni);
            
        }
        this.setVisible(false);

    }//GEN-LAST:event_jButtonOKActionPerformed

    public void predvypln() {
        int selectedItem = (int) (7 * Math.random());
        int tachometrPred = (int) (10000 + (50000 - 10000 + 1) * Math.random());
        int spzPred = (int) (20000 + (50000 - 20000 + 1) * Math.random());
        int pocetVypujceniPred = (int) (8 * Math.random());

        jComboBoxBarva.setSelectedIndex(selectedItem);
        jTextFieldTachometr.setText(Integer.toString(tachometrPred));
        jTextFieldSPZ.setText("ST-" + Integer.toString(spzPred));
        jTextFieldPocetPujceni.setText(Integer.toString(pocetVypujceniPred));
    }

    public IAuto getOsobni() {
        return osobni;
    }

    public String getSpz() {
        return spz;
    }

    public int getStavTachometru() {
        return stavTachometru;
    }

    public int getPocetVypujceni() {
        return pocetVypujceni;
    }

    public Barva getBarva() {
        return barva;
    }

    public void upravAuto(IAuto osobni) {
        setTitle("Uprav osobní automobil");
        jButtonOK.setText("Změnit");      
        switch (osobni.getBarva()) {
            case BILA:
                jComboBoxBarva.setSelectedIndex(0);
                break;
            case MODRA:
                jComboBoxBarva.setSelectedIndex(1);
                break;
            case CERVENA:
                jComboBoxBarva.setSelectedIndex(2);
                break;
            case CERNA:
                jComboBoxBarva.setSelectedIndex(3);
                break;
            case ZELENA:
                jComboBoxBarva.setSelectedIndex(4);
                break;
            case HNEDA:
                jComboBoxBarva.setSelectedIndex(5);
                break;
            case ZLUTA:
                jComboBoxBarva.setSelectedIndex(6);
                break;
        }
        jTextFieldPocetPujceni.setText(Integer.toString(osobni.getPocetVypujceni()));
        jTextFieldSPZ.setText(osobni.getSpz());
        jTextFieldTachometr.setText(Integer.toString(osobni.getPocetKilometru()));
        vytvorit = false;
        
        spz = osobni.getSpz();
        stavTachometru = osobni.getPocetKilometru();
        pocetVypujceni = osobni.getPocetVypujceni();
        barva = osobni.getBarva();
    }

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
            java.util.logging.Logger.getLogger(JDOsobniAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDOsobniAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDOsobniAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDOsobniAuto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDOsobniAuto dialog = new JDOsobniAuto(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupPozice;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JComboBox jComboBoxBarva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextFieldPocetPujceni;
    private javax.swing.JTextField jTextFieldSPZ;
    private javax.swing.JTextField jTextFieldTachometr;
    // End of variables declaration//GEN-END:variables
}