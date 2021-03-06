package panely;

import databaze.MyDBProperties;
import databaze.OracleConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class NeaktivniUkoly extends javax.swing.JPanel implements ObnovitelnyPanel{

    /**
     * Creates new form VsechnyUkoly
     */
    public NeaktivniUkoly() {
        initComponents();
        vykresliVsechnyUkoly();
    }

    private void vykresliVsechnyUkoly() {
         try {
            String[] columnNames = {"ID", "Název", "Popis", "Vytvořeno", "Deadline", "Priorita", "Stav"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            PreparedStatement pstmt = OracleConnector.getConnection().prepareStatement("select * from SP_VSECHNY_NEAKTIVNI_UKOLY");
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
            String textChyby = "Chyba při vykreslování všech aktivních úkolů! " + ex.toString();
            System.err.println(textChyby);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

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

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public int getSelectedRow() {
        return jTable1.getSelectedRow();
    }

    public int getIDUkol(int vysledek) {
        return Integer.parseInt((String) jTable1.getValueAt(vysledek, 0));
    }

    @Override
    public void obnovTabulku() {
        vykresliVsechnyUkoly();
    }

}
