package dialogy;


import databaze.MyDBProperties;
import databaze.OracleConnector;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

public class EditProfileDialog extends javax.swing.JDialog {

    private int idUzivatele;
    private boolean zmeny = false;

    public EditProfileDialog(java.awt.Frame parent, boolean modal, String prezdivka, int idUzivatele) {
        super(parent, modal);
        initComponents();
        this.idUzivatele = idUzivatele;
        this.setTitle("Dialog pro úpravu profilu");
        this.setLocationRelativeTo(this);
        try {
            OracleConnector.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        Statement stmt = null;
        String query = "select jmeno, prijmeni, prezdivka, id_znameni, nazev, tabZnam.popis"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UZIVATELE
                + " join SP_ROLE using(ID_ROLE)"
                + " join SP_ZNAMENI tabZnam using(ID_ZNAMENI)"
                + " where prezdivka = '" + prezdivka + "'";
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                jLabel1.setText(rs.getString("prezdivka"));
                jTextFieldJmeno.setText(rs.getString("jmeno"));
                jTextFieldPrijmeni.setText(rs.getString("prijmeni"));
                jTextFieldPrezdivka.setText(rs.getString("prezdivka"));
                jLabelZnameni.setText(rs.getString("id_znameni"));
                jLabelRoleUzivatele.setText(rs.getString("nazev"));
                jTextAreaPopisZnameni.setText(rs.getString("popis"));
            }
            int id = idUzivatele;
            new Thread() {
                public void run() {
                    Statement stmt;
                    Connection conn;

                    try {
                        conn = OracleConnector.getConnection();
                        String statementString = "select obrazek from SP_OBRAZKY where ID_UZIVATELE = " + id;
                        stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(statementString);
                        Blob blob = conn.createBlob();
                        while (rs.next()) {
                            blob = rs.getBlob(1);
                        }
                        InputStream is = blob.getBinaryStream();
                        Image stazenyObrazek = ImageIO.read(is);
                        jLabel8.setIcon(new ImageIcon(stazenyObrazek));
                    } catch (IOException | SQLException | NullPointerException ex) {
                    }

                }
            }.start();
        } catch (SQLException ex) {
           ex.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPrezdivka = new javax.swing.JTextField();
        jTextFieldJmeno = new javax.swing.JTextField();
        jLabelRoleUzivatele = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldPrijmeni = new javax.swing.JTextField();
        jLabelZnameni = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPopisZnameni = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabelPocetPochval = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(300, 500));

        jButton1.setText("Storno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Příjmení:");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        jPanel5.setMaximumSize(new java.awt.Dimension(207, 300));
        jPanel5.setPreferredSize(new java.awt.Dimension(2, 2));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel8, java.awt.BorderLayout.CENTER);

        jLabel6.setText("Počet pochval:");

        jLabel4.setText("Přezdívka:");

        jLabel7.setText("Role:");

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JmenoUziv");

        jTextFieldPrezdivka.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPrezdivka.setText("jTextField3");
        jTextFieldPrezdivka.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPrezdivkaFocusGained(evt);
            }
        });

        jTextFieldJmeno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldJmeno.setText("jTextField1");
        jTextFieldJmeno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldJmenoFocusGained(evt);
            }
        });
        jTextFieldJmeno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldJmenoActionPerformed(evt);
            }
        });
        jTextFieldJmeno.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextFieldJmenoPropertyChange(evt);
            }
        });

        jLabelRoleUzivatele.setText("USER");

        jLabel2.setText("Jméno:");

        jLabel5.setText("Znamení:");

        jTextFieldPrijmeni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPrijmeni.setText("jTextField2");
        jTextFieldPrijmeni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPrijmeniFocusGained(evt);
            }
        });

        jLabelZnameni.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelZnameni.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelZnameni.setText("jLabel8");

        jTextAreaPopisZnameni.setEditable(false);
        jTextAreaPopisZnameni.setColumns(20);
        jTextAreaPopisZnameni.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jTextAreaPopisZnameni.setLineWrap(true);
        jTextAreaPopisZnameni.setRows(5);
        jTextAreaPopisZnameni.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextAreaPopisZnameni);

        jButton3.setText("Nahrát nový avatar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabelPocetPochval.setText("0");

        jButton4.setText("Změnit heslo");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldJmeno)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldPrijmeni)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldPrezdivka)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelRoleUzivatele)
                            .addComponent(jLabelPocetPochval)))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelZnameni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 35, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabelZnameni))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldJmeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPrijmeni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPrezdivka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabelPocetPochval))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelRoleUzivatele)
                                .addComponent(jLabel7)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fc = new JFileChooser();
        //vyvolani dialogoveho okna pro vyber souboru
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                zmeny = true;
                //načtení souboru z disku
                File file = fc.getSelectedFile();
                //převod souboru na obrázek
                BufferedImage image = ImageIO.read(file);
                //načtení obrázku do zobrazovací oblasti
                jLabel8.setIcon(new ImageIcon(image));

                PreparedStatement ps;
                Statement stmt;
                Connection conn;
                String vymazObrazek = "delete from SP_obrazky where id_uzivatele = " + idUzivatele;
                String insertBlob = "Insert into SP_obrazky"
                        + " VALUES(SP_SEQ_OBRAZKY.nextval, ?, 'avatar' , sysdate, " + idUzivatele + ")";
                try {
                    conn = OracleConnector.getConnection();
                    stmt = conn.createStatement();
                    stmt.execute(vymazObrazek);
                    ps = conn.prepareStatement(insertBlob);
                    InputStream is = new FileInputStream(file);
                    ps.setBinaryStream(1, is, file.length());
                    ps.executeUpdate();
                } catch (SQLException | FileNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException ex) {
            }
            repaint();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (zmeny) {
            try {
                String jmeno, prijmeni, prezdivka;
                jmeno = jTextFieldJmeno.getText();
                prijmeni = jTextFieldPrijmeni.getText();
                prezdivka = jTextFieldPrezdivka.getText();
                String updateUser = "update SP_UZIVATELE set jmeno = '" + jmeno + "', prijmeni = '" + prijmeni + "', prezdivka = '" + prezdivka + "'"
                        + " where id_uzivatele = " + idUzivatele;
                Statement stmt = OracleConnector.getConnection().createStatement();
                stmt.executeQuery(updateUser);
                OracleConnector.getConnection().commit();
            } catch (SQLException ex) {
                Logger.getLogger(EditProfileDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldJmenoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextFieldJmenoPropertyChange
        
    }//GEN-LAST:event_jTextFieldJmenoPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            OracleConnector.getConnection().rollback();
        } catch (SQLException ex) {
            Logger.getLogger(EditProfileDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldJmenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldJmenoActionPerformed
 
    }//GEN-LAST:event_jTextFieldJmenoActionPerformed

    private void jTextFieldJmenoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldJmenoFocusGained
       zmeny = true;
    }//GEN-LAST:event_jTextFieldJmenoFocusGained

    private void jTextFieldPrijmeniFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrijmeniFocusGained
        zmeny = true;
    }//GEN-LAST:event_jTextFieldPrijmeniFocusGained

    private void jTextFieldPrezdivkaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrezdivkaFocusGained
        zmeny = true;
    }//GEN-LAST:event_jTextFieldPrezdivkaFocusGained

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ChangePasswordDialog passDialog = new ChangePasswordDialog(null, true, idUzivatele);
        passDialog.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelPocetPochval;
    private javax.swing.JLabel jLabelRoleUzivatele;
    private javax.swing.JLabel jLabelZnameni;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPopisZnameni;
    private javax.swing.JTextField jTextFieldJmeno;
    private javax.swing.JTextField jTextFieldPrezdivka;
    private javax.swing.JTextField jTextFieldPrijmeni;
    // End of variables declaration//GEN-END:variables

    public String getUzivatel() {
        return jTextFieldPrezdivka.getText();
    }
}