/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogy;

import databaze.MyDBProperties;
import databaze.OracleConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Dex
 */
public class EditTaskDialog extends javax.swing.JDialog {

    /**
     * Creates new form EditTask
     */
    public EditTaskDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Dialog pro úpravu úkolů");
        initComboBox(-1);
        init();

    }

    private void init() {

        initComboBoxStav();
        initDatumVytvoreno();
        initDeadline();
        initHodnoty();
    }

    private void initDeadline() {
        Statement stmt;
        String ID_UKOLU = (String) jComboBox1.getSelectedItem();
        ID_UKOLU = ID_UKOLU.split(" ")[0];
        String query = "select to_char(DEADLINE, 'DD MM YYYY HH24 MI') Deadline"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UKOLY
                + " where ID_UKOLU = " + ID_UKOLU;
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println(rs.getString("Deadline"));
                String[] cas = rs.getString("Deadline").split(" ");
                int dny = Integer.parseInt(cas[0]);
                int mesice = Integer.parseInt(cas[1]);
                int roky = Integer.parseInt(cas[2]);
                int hodiny = Integer.parseInt(cas[3]);
                int minuty = Integer.parseInt(cas[4]);
                jSpinner2.setValue(dny);
                jSpinner3.setValue(mesice);
                jSpinner4.setValue(roky);
                jSpinner1.setValue(hodiny);
                jSpinner5.setValue(minuty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {

        }
    }

    private void initDatumVytvoreno() {
        Statement stmt;
        String ID_UKOLU = (String) jComboBox1.getSelectedItem();
        ID_UKOLU = ID_UKOLU.split(" ")[0];
        String query = "select to_char(DATUM_VYTVORENI, 'DD MM YYYY HH24 MI') Vytvořeno"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UKOLY
                + " where ID_UKOLU = " + ID_UKOLU;
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                System.out.println(rs.getString("Vytvořeno"));
                String[] cas = rs.getString("Vytvořeno").split(" ");
                int dny = Integer.parseInt(cas[0]);
                int mesice = Integer.parseInt(cas[1]);
                int roky = Integer.parseInt(cas[2]);
                int hodiny = Integer.parseInt(cas[3]);
                int minuty = Integer.parseInt(cas[4]);
                jSpinner6.setValue(dny);
                jSpinner7.setValue(mesice);
                jSpinner8.setValue(roky);
                jSpinner9.setValue(hodiny);
                jSpinner10.setValue(minuty);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComboBoxStav() {
        Vector<String> vysledky = new Vector();
        Statement stmt;
        String query = "select NAZEV"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.STAVY
                + " order by ID_STAV asc";
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                vysledky.add(rs.getString("NAZEV"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(vysledky);
        jComboBox3.setModel(model);
    }

    private void initHodnoty() {
        try {
            String ID_UKOLU = (String) jComboBox1.getSelectedItem();
            ID_UKOLU = ID_UKOLU.split(" ")[0];
            Statement stmt;
            stmt = OracleConnector.getConnection().createStatement();
            String query = "select NAZEV, POPIS, ID_PRIORITY, ID_STAV"
                    + " from SP_UKOLY where ID_UKOLU = " + ID_UKOLU;
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                jTextField1.setText(rs.getString("NAZEV"));
                jTextArea1.setText(rs.getString("POPIS"));
                jComboBox2.setSelectedIndex(rs.getInt("ID_PRIORITY") - 1);
                jComboBox3.setSelectedIndex((rs.getInt("ID_STAV") - 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditTaskDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initComboBox(int idUkolu) {
        String aktualniVyber = "";
        Vector<String> vysledky = new Vector();
        Statement stmt;
        String query = "select ID_UKOLU, NAZEV"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UKOLY;
        //+ " order by Vytvořeno desc";
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                vysledky.add(rs.getString("ID_UKOLU") + " " + rs.getString("NAZEV"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(vysledky);

        jComboBox1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jSpinner6 = new javax.swing.JSpinner();
        jSpinner7 = new javax.swing.JSpinner();
        jSpinner8 = new javax.swing.JSpinner();
        jSpinner9 = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jSpinner10 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Upravit úkol");

        jLabel2.setText("Upravit úkol: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setMinimumSize(new java.awt.Dimension(4, 300));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        jLabel3.setText("Název úkolu:");
        jPanel2.add(jLabel3);

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 60));
        jPanel2.add(jTextField1);

        jLabel9.setText("Datum vytvoření");
        jPanel2.add(jLabel9);

        jPanel5.add(jCheckBox2);

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        jPanel5.add(jSpinner6);

        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jPanel5.add(jSpinner7);

        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(2016, 2016, 2030, 1));
        jPanel5.add(jSpinner8);

        jSpinner9.setModel(new javax.swing.SpinnerNumberModel(12, 0, 24, 1));
        jPanel5.add(jSpinner9);

        jLabel10.setText(":");
        jPanel5.add(jLabel10);

        jSpinner10.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
        jPanel5.add(jSpinner10);

        jPanel2.add(jPanel5);

        jLabel5.setText("Deadline");
        jPanel2.add(jLabel5);

        jPanel4.add(jCheckBox1);

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        jPanel4.add(jSpinner2);

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jPanel4.add(jSpinner3);

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(2016, 2016, 2030, 1));
        jPanel4.add(jSpinner4);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), null, Integer.valueOf(23), Integer.valueOf(1)));
        jPanel4.add(jSpinner1);

        jLabel7.setText(":");
        jPanel4.add(jLabel7);

        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
        jPanel4.add(jSpinner5);

        jPanel2.add(jPanel4);

        jLabel6.setText("Priorita");
        jPanel2.add(jLabel6);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vysoká", "Střední", "Nízká" }));
        jComboBox2.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel2.add(jComboBox2);

        jLabel8.setText("Stav");
        jPanel2.add(jLabel8);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox3);

        jLabel4.setText("Popis úkolu:");

        jButton1.setText("Upravit úkol");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Neupravovat");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String nazev = jTextField1.getText();
        String popis = jTextArea1.getText();
        int id_priorita = jComboBox2.getSelectedIndex() + 1;
        int id_stav = jComboBox3.getSelectedIndex() + 1;
        if (id_priorita == 0) {
            id_priorita = 1;
        }
        try {
            String ID_UKOLU = (String) jComboBox1.getSelectedItem();
            ID_UKOLU = ID_UKOLU.split(" ")[0];
            String deadlineParse = jSpinner2.getValue() + "." + jSpinner3.getValue() + "." + jSpinner4.getValue() + " " + jSpinner1.getValue() + ":" + jSpinner5.getValue();
            String vytvoreniParse = jSpinner6.getValue() + "." + jSpinner7.getValue() + "." + jSpinner8.getValue() + " " + jSpinner9.getValue() + ":" + jSpinner10.getValue();
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            java.util.Date deadline = format1.parse(deadlineParse);
            java.util.Date datumVytvoreni = format1.parse(vytvoreniParse);
            if (jCheckBox2.isSelected()) {
                PreparedStatement pstmtUpravaVytvoreni = OracleConnector.getConnection().prepareStatement("UPDATE"
                        + " SP_UKOLY SET NAZEV = ?, POPIS = ?, DATUM_VYTVORENI = ?, DEADLINE = ?, ID_PRIORITY = ?, ID_STAV = ?"
                        + " where ID_UKOLU = " + ID_UKOLU);
                pstmtUpravaVytvoreni.setString(1, nazev);
                pstmtUpravaVytvoreni.setString(2, popis);
                pstmtUpravaVytvoreni.setDate(3, new java.sql.Date(datumVytvoreni.getTime()));
                if (jCheckBox1.isSelected()) {
                    pstmtUpravaVytvoreni.setDate(4, new java.sql.Date(deadline.getTime()));
                } else {
                    pstmtUpravaVytvoreni.setNull(4, java.sql.Types.DATE);
                }

                pstmtUpravaVytvoreni.setInt(5, id_priorita);
                pstmtUpravaVytvoreni.setInt(6, id_stav);
                pstmtUpravaVytvoreni.executeUpdate();
                OracleConnector.getConnection().commit();
                //jLabel8.setText("Uživatel úspěšně vytvořen!");
                dispose();
            } else {
                PreparedStatement pstmtBezUpravyVytvoreni = OracleConnector.getConnection().prepareStatement("UPDATE"
                        + " SP_UKOLY SET NAZEV = ?, POPIS = ?, DEADLINE = ?, ID_PRIORITY = ?, ID_STAV = ?"
                        + " where ID_UKOLU = " + ID_UKOLU);
                pstmtBezUpravyVytvoreni.setString(1, nazev);
                pstmtBezUpravyVytvoreni.setString(2, popis);

                if (jCheckBox1.isSelected()) {
                    pstmtBezUpravyVytvoreni.setDate(3, new java.sql.Date(deadline.getTime()));
                } else {
                    pstmtBezUpravyVytvoreni.setNull(3, java.sql.Types.DATE);
                }

                pstmtBezUpravyVytvoreni.setInt(4, id_priorita);
                pstmtBezUpravyVytvoreni.setInt(5, id_stav);
                pstmtBezUpravyVytvoreni.executeUpdate();
                OracleConnector.getConnection().commit();
                //jLabel8.setText("Uživatel úspěšně vytvořen!");
                dispose();
            }
        } catch (SQLException ex) {
            //  jLabel8.setText(ex.getClass().toString());
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(AddTaskDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        init();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner10;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
