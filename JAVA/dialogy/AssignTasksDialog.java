package dialogy;

import databaze.MyDBProperties;
import databaze.OracleConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dex
 */
public class AssignTasksDialog extends javax.swing.JDialog {

    /**
     * Creates new form AssignTasksDialog
     */
    public AssignTasksDialog(java.awt.Frame parent, boolean modal, int idUkolu) {
        super(parent, modal);
        initComponents();
        initComboBox(idUkolu);
        initTableVolniUzivatele();
        initTableVyuzitiUzivatele();
    }

    private void initTableVolniUzivatele() {
        String ukol = (String) jComboBox1.getSelectedItem();
        try {
            String ID_UKOLU = (String) jComboBox1.getSelectedItem();
            ID_UKOLU = ID_UKOLU.split(" ")[0];

            String[] columnNames = {"ID_UZIVATELE", "JMENO", "PRIJMENI", "PREZDIVKA"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            PreparedStatement pstmt = OracleConnector.getConnection().prepareStatement("select SP_UZIVATELE.ID_UZIVATELE, JMENO, PRIJMENI, PREZDIVKA from SP_UZIVATELE"
                    + " MINUS"
                    + " select SP_UZIVATELE.ID_UZIVATELE, JMENO, PRIJMENI, PREZDIVKA from SP_UZIVATELE"
                    + " left join SP_UKOLY_UZIVATELU on SP_UZIVATELE.ID_UZIVATELE = SP_UKOLY_UZIVATELU.ID_UZIVATELE"
                    + " where ID_UKOLU = " + ID_UKOLU);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                Vector<String> row = new Vector();
                for (int i = 1; i <= columnNames.length; i++) {
                    row.add(rset.getString(i)); // vytvoreni radku tabulky ze ziskanych hodnot
                }
                model.addRow(row); // pridani radku do modelu

            }
            jTable1.setModel(model);

        } catch (SQLException ex) {
            String textChyby = "Chyba při vykreslování všech uživatelů! " + ex.toString();
            System.err.println(textChyby);
        }

    }

    private void initTableVyuzitiUzivatele() {
        try {
            String ID_UKOLU = (String) jComboBox1.getSelectedItem();
            ID_UKOLU = ID_UKOLU.split(" ")[0];

            String[] columnNames = {"ID_UZIVATELE", "JMENO", "PRIJMENI", "PREZDIVKA"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            PreparedStatement pstmt = OracleConnector.getConnection().prepareStatement("select ID_UZIVATELE, JMENO, PRIJMENI, PREZDIVKA"
                    + " from SP_UZIVATELE"
                    + " left join SP_UKOLY_UZIVATELU USING(ID_UZIVATELE)"
                    + " where SP_UKOLY_UZIVATELU.ID_UKOLU = " + ID_UKOLU);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                Vector<String> row = new Vector();
                for (int i = 1; i <= columnNames.length; i++) {
                    row.add(rset.getString(i)); // vytvoreni radku tabulky ze ziskanych hodnot
                }
                model.addRow(row); // pridani radku do modelu

            }
            jTable2.setModel(model);
            if (jTable2.getRowCount() == 0) {
                String query = "update SP_UKOLY SET ID_STAV = 4 WHERE ID_UKOLU = (" + ID_UKOLU + ") and ID_STAV = 1";
                Statement stmt2 = OracleConnector.getConnection().createStatement();
                stmt2.executeQuery(query);
                OracleConnector.getConnection().commit();
            }
        } catch (SQLException ex) {
            String textChyby = "Chyba při vykreslování všech uživatelů! " + ex.toString();
            System.err.println(textChyby);
        }
    }

    private void initComboBox(int idUkolu) {
        String aktualniVyber = "";
        Vector<String> vysledky = new Vector();
        Statement stmt;
        String query = "select ID_UKOLU, NAZEV"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UKOLY
                + " where ID_STAV = " + 1 + " or ID_STAV = " + 4;
        //+ " order by Vytvořeno desc";
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                vysledky.add(rs.getString("ID_UKOLU") + " " + rs.getString("NAZEV"));
                if (rs.getInt("ID_UKOLU") == idUkolu) {
                    aktualniVyber = vysledky.lastElement();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(vysledky);
        if (aktualniVyber.compareTo("") != 0) {
            model.setSelectedItem(aktualniVyber);
        }
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
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Přiřazení uživatelů k úkolům");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Vyberte úkol, ke kterému chcete přiřadit uživatele:");

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2);

        jLabel3.setText("Uživatelé, které lze přiřadit");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Uživatelé, kteří byli k úkolu přiřazeni");

        jButton1.setText("Přiřadit vybraného uživatele k úkolu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Odebrat vybraného uživatele");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hotovo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        initTableVolniUzivatele();
        initTableVyuzitiUzivatele();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int vybranyVolnyUzivatel = jTable1.getSelectedRow();
        int idUzivatele;
        if (vybranyVolnyUzivatel != -1) {
            idUzivatele = Integer.parseInt((String) jTable1.getValueAt(vybranyVolnyUzivatel, 0));

            String ID_UKOLU = (String) jComboBox1.getSelectedItem();
            ID_UKOLU = ID_UKOLU.split(" ")[0];
            Statement stmt2;
            String query2 = "insert into SP_UKOLY_UZIVATELU VALUES(" + idUzivatele + ", " + ID_UKOLU + ")";

            try {
                stmt2 = OracleConnector.getConnection().createStatement();
                ResultSet rs = stmt2.executeQuery(query2);
                OracleConnector.getConnection().commit();

            } catch (SQLException ex) {
                Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

            initTableVolniUzivatele();
            initTableVyuzitiUzivatele();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int vybranyUzivatel = jTable2.getSelectedRow();
        int idUzivatele;
        if (vybranyUzivatel != -1) {
            idUzivatele = Integer.parseInt((String) jTable2.getValueAt(vybranyUzivatel, 0));

            String ID_UKOLU = (String) jComboBox1.getSelectedItem();
            ID_UKOLU = ID_UKOLU.split(" ")[0];

            Statement stmt2;
            String query2 = "DELETE FROM SP_UKOLY_UZIVATELU where ID_UZIVATELE = " + idUzivatele + " AND ID_UKOLU = " + ID_UKOLU;

            try {
                stmt2 = OracleConnector.getConnection().createStatement();
                ResultSet rs = stmt2.executeQuery(query2);
                OracleConnector.getConnection().commit();

            } catch (SQLException ex) {
                Logger.getLogger(AssignTasksDialog.class.getName()).log(Level.SEVERE, null, ex);
            }

            initTableVolniUzivatele();
            initTableVyuzitiUzivatele();

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
