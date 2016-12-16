package dialogy;

import databaze.OracleConnector;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dex
 */
public class AddUserDialog extends javax.swing.JDialog {

    /**
     * Creates new form AddUserDialog
     */
    public AddUserDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Vytvoření nového uživatele");
        //jPanel1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(231, 600));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Stavovy radek");
        getContentPane().add(jLabel8, java.awt.BorderLayout.PAGE_END);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel1.setMinimumSize(new java.awt.Dimension(231, 460));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vytvořit nového uživatele");
        jPanel1.add(jLabel1);

        jLabel2.setText("Jméno");
        jPanel1.add(jLabel2);

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField1MouseEntered(evt);
            }
        });
        jPanel1.add(jTextField1);

        jLabel3.setText("Příjmení");
        jPanel1.add(jLabel3);

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
        });
        jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField2MouseEntered(evt);
            }
        });
        jPanel1.add(jTextField2);

        jLabel4.setText("Přezdívka / login");
        jPanel1.add(jLabel4);

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });
        jTextField3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField3MouseEntered(evt);
            }
        });
        jPanel1.add(jTextField3);

        jLabel9.setText("Role");
        jPanel1.add(jLabel9);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrátor", "Uživatel" }));
        jComboBox1.setSelectedIndex(1);
        jPanel1.add(jComboBox1);

        jLabel10.setText("Pohlaví");
        jPanel1.add(jLabel10);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Muž");
        jPanel3.add(jRadioButton1);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Žena");
        jPanel3.add(jRadioButton2);

        jPanel1.add(jPanel3);

        jLabel5.setText("Datum narození");
        jPanel1.add(jLabel5);

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        jSpinner1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSpinner1FocusGained(evt);
            }
        });
        jSpinner1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSpinner1MouseEntered(evt);
            }
        });

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        jSpinner2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSpinner2FocusGained(evt);
            }
        });
        jSpinner2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSpinner2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSpinner2MouseEntered(evt);
            }
        });

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(1990, 1900, 2015, 1));
        jSpinner3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jSpinner3FocusGained(evt);
            }
        });
        jSpinner3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSpinner3MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
            .addComponent(jSpinner2)
            .addComponent(jSpinner3)
        );

        jPanel1.add(jPanel2);

        jLabel6.setText("Heslo");
        jPanel1.add(jLabel6);

        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseEntered(evt);
            }
        });
        jPanel1.add(jPasswordField1);

        jLabel7.setText("Heslo pro kontrolu");
        jPanel1.add(jLabel7);

        jPasswordField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPasswordField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField2FocusGained(evt);
            }
        });
        jPasswordField2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPasswordField2MouseEntered(evt);
            }
        });
        jPanel1.add(jPasswordField2);

        jButton1.setText("Vytvořit uživatele");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setText("Nevytvářet");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String jmeno = jTextField1.getText();
        String prijmeni = jTextField2.getText();
        String prezdivka = jTextField3.getText();
        String datumNarozeni = jSpinner1.getValue() + "." + jSpinner2.getValue() + "." + jSpinner3.getValue();
        String pohlaviMuz = "Muž";
        String pohlaviZena = "Žena";
        int role = jComboBox1.getSelectedIndex();
        if (role == -1) {
            role = 2;
        } else {
            role++;
        }
        String heslo = String.copyValueOf(jPasswordField1.getPassword());
        String heslo2 = String.copyValueOf(jPasswordField2.getPassword());
        if (heslo.compareTo(heslo2) == 0) {

            try {
                SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
                java.util.Date narozeni = format1.parse(datumNarozeni);
                PreparedStatement pstmt = OracleConnector.getConnection().prepareStatement("INSERT INTO"
                        + " SP_UZIVATELE(ID_UZIVATELE,JMENO,PRIJMENI,PREZDIVKA,DATUM_NAROZENI,POHLAVI,HESLO,ID_ROLE,ID_ZNAMENI) "
                        + " VALUES(SP_SEQ_UZIVATELE.nextval, ?, ?, ?, ?, ?, ?, ?, SP_PROCEDURY_FUNKCE.SP_DEJ_ZNAMENI((?)))");
                //Date date = new java.sql.Date(narozeni.getTime());
                pstmt.setString(1, jmeno);
                pstmt.setString(2, prijmeni);
                pstmt.setString(3, prezdivka);
                pstmt.setDate(4, new java.sql.Date(narozeni.getTime()));
                if (jRadioButton1.isSelected()) {
                    pstmt.setString(5, pohlaviMuz);
                } else {
                    pstmt.setString(5, pohlaviZena);
                }
                String vysledekHashe;
                CallableStatement cstmt = OracleConnector.getConnection().prepareCall("{? = call MD5HASH(?)}");
                cstmt.registerOutParameter(1, Types.VARCHAR);
                cstmt.setString(2, heslo);
                cstmt.executeUpdate();
                vysledekHashe = cstmt.getString(1);
                pstmt.setString(6, vysledekHashe);
                pstmt.setInt(7, role);
                String datum = "'";
                datum += jSpinner1.getValue() + "." + jSpinner2.getValue() + "." + jSpinner3.getValue() + "'";
                //pstmt.setString(8, datum);
                pstmt.setDate(8, new java.sql.Date(narozeni.getTime()));
                pstmt.executeUpdate();
                OracleConnector.getConnection().commit();
                jLabel8.setText("Uživatel úspěšně vytvořen!");
                dispose();
            } catch (SQLException | ParseException ex) {
                jLabel8.setText(ex.getClass().toString());
                ex.printStackTrace();
            }
        } else {
            jLabel8.setText("Zadana hesla nejsou stejna!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained

    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained

    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained

    }//GEN-LAST:event_jTextField3FocusGained

    private void jSpinner1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSpinner1FocusGained
        jLabel8.setText("Den narozeni");
    }//GEN-LAST:event_jSpinner1FocusGained

    private void jSpinner2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSpinner2FocusGained
        jLabel8.setText("Mesic narozeni");
    }//GEN-LAST:event_jSpinner2FocusGained

    private void jSpinner3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSpinner3FocusGained

    }//GEN-LAST:event_jSpinner3FocusGained

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained

    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jPasswordField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField2FocusGained

    }//GEN-LAST:event_jPasswordField2FocusGained

    private void jSpinner1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinner1MouseEntered
        jLabel8.setText("Den narozeni");
    }//GEN-LAST:event_jSpinner1MouseEntered

    private void jSpinner2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinner2MouseClicked

    }//GEN-LAST:event_jSpinner2MouseClicked

    private void jTextField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseEntered
        jLabel8.setText("Krestni jmeno noveho uzivatele");
    }//GEN-LAST:event_jTextField1MouseEntered

    private void jTextField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField2MouseEntered
        jLabel8.setText("Prijmeni noveho uzivatele");
    }//GEN-LAST:event_jTextField2MouseEntered

    private void jTextField3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField3MouseEntered
        jLabel8.setText("Prezdivka noveho uzivatele. Bude slouzit jako login");
    }//GEN-LAST:event_jTextField3MouseEntered

    private void jSpinner3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinner3MouseEntered
        jLabel8.setText("Rok narozeni");
    }//GEN-LAST:event_jSpinner3MouseEntered

    private void jPasswordField1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseEntered
        jLabel8.setText("Heslo uzivatele");
    }//GEN-LAST:event_jPasswordField1MouseEntered

    private void jPasswordField2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField2MouseEntered
        jLabel8.setText("Heslo uzivatele podruhe, pro kontrolu");
    }//GEN-LAST:event_jPasswordField2MouseEntered

    private void jSpinner2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSpinner2MouseEntered
        jLabel8.setText("Mesic narozeni");
    }//GEN-LAST:event_jSpinner2MouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
