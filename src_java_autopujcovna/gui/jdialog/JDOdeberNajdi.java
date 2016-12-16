/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jdialog;

/**
 *
 * @author J3BY
 */
public class JDOdeberNajdi extends javax.swing.JDialog {

    String spz = null;

    /**
     * Creates new form OdeberNajdi
     */
    public JDOdeberNajdi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        jRadioButtonVAktualni.setEnabled(false);
        jRadioButtonVeVsech.setEnabled(false);
        jPanel1.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupDruhHledani = new javax.swing.ButtonGroup();
        jButtonOdeberAuto = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSPZ = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButtonVeVsech = new javax.swing.JRadioButton();
        jRadioButtonVAktualni = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonOdeberAuto.setText("Odeber auto");
        jButtonOdeberAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOdeberAutoActionPerformed(evt);
            }
        });

        jLabel1.setText("Zadejte SPZ:");

        jTextFieldSPZ.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldSPZ.setText("ST-12345");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hledej"));

        buttonGroupDruhHledani.add(jRadioButtonVeVsech);
        jRadioButtonVeVsech.setSelected(true);
        jRadioButtonVeVsech.setText("Ve všech pobočkách");

        buttonGroupDruhHledani.add(jRadioButtonVAktualni);
        jRadioButtonVAktualni.setText("V Aktuální");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonVeVsech)
                    .addComponent(jRadioButtonVAktualni))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonVeVsech)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonVAktualni)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldSPZ)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButtonOdeberAuto))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSPZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonOdeberAuto))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOdeberAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOdeberAutoActionPerformed
        spz = jTextFieldSPZ.getText();
        this.setVisible(false);
    }//GEN-LAST:event_jButtonOdeberAutoActionPerformed

    public String getSpz() {
        return spz;
    }

    public void nastav(int cislo) {
        switch (cislo) {
            case 0:
                jLabel1.setText("Zadej hledanou SPZ: ");
                jPanel1.setEnabled(true);
                jRadioButtonVAktualni.setEnabled(true);
                jRadioButtonVeVsech.setEnabled(true);
                jButtonOdeberAuto.setText("Hledej");
                break;
            case 1:
                jLabel1.setText("Zadej SPZ: ");
                jRadioButtonVAktualni.setSelected(true);
                jButtonOdeberAuto.setText("Vypujčit");
                break;
            case 2:
                jLabel1.setText("Zadej hledanou SPZ: ");
                jButtonOdeberAuto.setText("Hledej");
                jRadioButtonVAktualni.setSelected(true);
                break;
        }

    }

    public int getDruhHledani() {
        if (jRadioButtonVeVsech.isSelected()) {
            return 1;
        } else {
            return 2;
        }

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
            java.util.logging.Logger.getLogger(JDOdeberNajdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDOdeberNajdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDOdeberNajdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDOdeberNajdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDOdeberNajdi dialog = new JDOdeberNajdi(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroupDruhHledani;
    private javax.swing.JButton jButtonOdeberAuto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButtonVAktualni;
    private javax.swing.JRadioButton jRadioButtonVeVsech;
    private javax.swing.JTextField jTextFieldSPZ;
    // End of variables declaration//GEN-END:variables
}