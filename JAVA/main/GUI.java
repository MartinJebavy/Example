package main;

import databaze.MyDBProperties;
import databaze.OracleConnector;
import dialogy.EditTaskDialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import panely.*;
import dialogy.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Dex
 */
public class GUI extends javax.swing.JFrame {

    public static final int USPECH = 0;
    public static final int UZIVATEL_NEEXISTUJE = 1;
    public static final int SPATNE_HESLO = 2;
    public static String PRIHLASENY_UZIVATEL;
    public static int PRIHLASENY_UZIVATEL_ID = -1;
    public static int PRIHLASENY_UZIVATEL_ROLE;
    private static final int ROLE_ADMIN = 1;
    private static final int ROLE_USER = 2;
    private static final int ROLE_GUEST = 3;

    private JPanel[] poleAktivnichPanelu = new JPanel[5];

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        setTitle("Upominky 1.0a");
        setLocationRelativeTo(this);
        pripojDoDatabaze();
        prihlasUzivateleUpominek();
    }

    private void jeSvatek() {
        try {
            Calendar cal = new GregorianCalendar();
            int dny = cal.get(Calendar.DAY_OF_MONTH);
            int mesice = cal.get(Calendar.MONTH) + 1;
            String datum = "to_date('" + dny + "." + mesice + ".00')";
            String query = "select SP_PROCEDURY_FUNKCE.SP_JE_SVATEK(" + datum + ") from dual";
            Statement stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = null; // vysledek po execute
            rs = stmt.executeQuery(query);
            rs.next();
            jLabelStatus.setText(jLabelStatus.getText() + ". " + rs.getString(1));
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initProfil() {
        
        jLabel1.setText(PRIHLASENY_UZIVATEL);
        Statement stmt = null;
        String query = "select jmeno, prijmeni, prezdivka, id_znameni, nazev"
                + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UZIVATELE
                + " join " + MyDBProperties.ROLE + " using(ID_ROLE)"
                + " where prezdivka = '" + PRIHLASENY_UZIVATEL + "'";
        try {
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                jTextFieldJmeno.setText(rs.getString("jmeno"));
                jTextFieldPrijmeni.setText(rs.getString("prijmeni"));
                jTextFieldPrezdivka.setText(rs.getString("prezdivka"));
                jTextFieldZnameni.setText(rs.getString("id_znameni"));
                jLabel7.setText("Role: " + rs.getString("nazev"));
            }
        } catch (SQLException ex) {
            String textChyby = "Chyba při načítání profilu! " + ex.toString();
            jLabelStatus.setText(textChyby);
        }
        new Thread() {
            public void run() {
                Statement stmt;
                Connection conn;

                try {
                    conn = OracleConnector.getConnection();
                    String statementString = "select obrazek from " + MyDBProperties.OBRAZKY + " where ID_UZIVATELE = " + PRIHLASENY_UZIVATEL_ID;
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(statementString);
                    Blob blob = conn.createBlob();
                    while (rs.next()) {
                        blob = rs.getBlob(1);
                    }
                    InputStream is = blob.getBinaryStream();
                    Image stazenyObrazek = ImageIO.read(is);
                    jLabel8.setIcon(new ImageIcon(stazenyObrazek));
                    jPanel5.setSize(new Dimension(jLabel8.getIcon().getIconWidth(), jLabel8.getIcon().getIconHeight()));
                } catch (IOException | SQLException | NullPointerException ex) {
                    try {
                        Image defaultniObrazek = ImageIO.read(getClass().getResource("res/defaultUser.png"));
                        jLabel8.setIcon(new ImageIcon(defaultniObrazek));
                    } catch (IOException ex1) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }

            }
        }.start();
    }

    private void initUser() {
        jeSvatek();
        jTabbedPaneObsah.removeAll();
        jPanelTlacitka.removeAll();
        initButtonsUser();
        UkolyUzivatele ukolyUzivatelePanel = new UkolyUzivatele(PRIHLASENY_UZIVATEL_ID);
        jTabbedPaneObsah.add("Moje úkoly", ukolyUzivatelePanel);
        AktivniUkoly ukolyVsechUzivatelu = new AktivniUkoly();
        jTabbedPaneObsah.add("Aktivní úkoly", ukolyVsechUzivatelu);
        jTabbedPaneObsah.add("Neaktivní úkoly", new NeaktivniUkoly());
        UkolyBezUzivatelu bezUzivatelu = new UkolyBezUzivatelu();
        jTabbedPaneObsah.add("Volné úkoly", bezUzivatelu);
        jMenuItem2.setVisible(true);
    }

    private void initButtonsUser() {
        jButton1.setVisible(false);
        javax.swing.JButton jRezervuj = new javax.swing.JButton();
        javax.swing.JButton jOznacSplneno = new javax.swing.JButton();
        javax.swing.JButton jProdluzDeadline = new javax.swing.JButton();
        jRezervuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                try {
                    Statement stmt = OracleConnector.getConnection().createStatement();
                    int idUkolu = Integer.parseInt(JOptionPane.showInputDialog("Zadejte ID úkolu, který chcete rezervovat."));
                    String query = "insert into SP_UKOLY_UZIVATELU VALUES (" + PRIHLASENY_UZIVATEL_ID + ", " + idUkolu + ")";
                    stmt.executeQuery(query);
                    query = "update SP_UKOLY set ID_STAV = 1 where ID_UKOLU = " + idUkolu;
                    stmt.executeQuery(query);
                    OracleConnector.getConnection().commit();
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }
            }
        });

        jOznacSplneno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                try {
                    Statement stmt = OracleConnector.getConnection().createStatement();
                    int idUkolu = Integer.parseInt(JOptionPane.showInputDialog("Zadejte ID úkolu, který jste splnili."));
                    String query = "update SP_UKOLY SET ID_STAV = 2 WHERE ID_UKOLU = (" + idUkolu + ")";
                    stmt.executeQuery(query);
                    OracleConnector.getConnection().commit();
                } catch (SQLException ex) {
                    jLabelStatus.setText(ex.getSQLState());
                }
                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }
            }
        });
        /*
         jProdluzDeadline.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
         jButton1ActionPerformed(evt);
         }

         private void jButton1ActionPerformed(ActionEvent evt) {
         AddUserDialog addUser = new AddUserDialog(null, true);
         addUser.setVisible(true);
         for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
         ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
         panel.obnovTabulku();
         }
         }
         });*/
        jRezervuj.setText("Rezervovat úkol!");
        jOznacSplneno.setText("Označit úkol jako splněný");
        jPanelTlacitka.setLayout(new GridLayout(6, 1));
        jPanelTlacitka.add(jRezervuj);
        jPanelTlacitka.add(jOznacSplneno);
        jMenuItem2.setVisible(true);
        jPanelTlacitka.setVisible(true);
        jPanelProfil.setVisible(true);
    }

    private void initGuest() {
        jeSvatek();
        jPanelProfil.setVisible(false);
        jMenuItem2.setVisible(false);
        jPanelTlacitka.setVisible(false);
        jTabbedPaneObsah.removeAll();
        PrehledZnameni prehledZnameni = new PrehledZnameni();
        jTabbedPaneObsah.add("Přehled znamení", prehledZnameni);
        jTabbedPaneObsah.add("Věk uživatelů", new VekUzivatelu());
        jLabelStatus.setText(("Jsi host a nemas zadna prava."));
    }

    private void initAdmin() {
        jeSvatek();
        initButtonsAdmin();
        jButton1.setVisible(true);
        this.setMinimumSize(new Dimension(500, 4 * 60));
        jTabbedPaneObsah.removeAll();
        poleAktivnichPanelu[0] = new UkolyUzivatele(PRIHLASENY_UZIVATEL_ID);
        jTabbedPaneObsah.add("Moje úkoly", poleAktivnichPanelu[0]);
        poleAktivnichPanelu[1] = new AktivniUkoly();
        jTabbedPaneObsah.add("Aktivní úkoly", poleAktivnichPanelu[1]);
        poleAktivnichPanelu[2] = new VekUzivatelu();
        jTabbedPaneObsah.add("Věk uživatelů", poleAktivnichPanelu[2]);
        poleAktivnichPanelu[3] = new VsichniUzivatele();
        jTabbedPaneObsah.add("Všichni uživatelé", poleAktivnichPanelu[3]);
        jTabbedPaneObsah.add("Volné úkoly", new UkolyBezUzivatelu());
        jTabbedPaneObsah.add("Přehled znamení", new PrehledZnameni());
        jTabbedPaneObsah.add("Neaktivní úkoly", new NeaktivniUkoly());
        jTabbedPaneObsah.add("Všechny úkoly", new VsechnyUkoly());
        jMenuItem2.setVisible(true);
        jPanelTlacitka.setVisible(true);
        jPanelProfil.setVisible(true);
    }

    private void initButtonsAdmin() {
        jPanelTlacitka.removeAll();
        javax.swing.JButton jAddUser = new javax.swing.JButton();
        javax.swing.JButton jDeleteUser = new javax.swing.JButton();
        javax.swing.JButton jAddTask = new javax.swing.JButton();
        javax.swing.JButton jDeleteTask = new javax.swing.JButton();
        javax.swing.JButton jAssignTask = new javax.swing.JButton();
        javax.swing.JButton jEditTask = new javax.swing.JButton();
        jAddUser.setHorizontalAlignment(SwingConstants.LEFT);
        jDeleteUser.setHorizontalAlignment(SwingConstants.LEFT);
        jAddTask.setHorizontalAlignment(SwingConstants.LEFT);
        jDeleteTask.setHorizontalAlignment(SwingConstants.LEFT);
        jAssignTask.setHorizontalAlignment(SwingConstants.LEFT);
        jEditTask.setHorizontalAlignment(SwingConstants.LEFT);
        try {
            Image img = ImageIO.read(getClass().getResource("res/addUser.png"));
            jAddUser.setIcon(new ImageIcon(img));
            jAddUser.setText("  Přidat uživatele");
            img = ImageIO.read(getClass().getResource("res/delUser.png"));
            jDeleteUser.setIcon(new ImageIcon(img));
            jDeleteUser.setText("  Vymazat uživatele");
            jAddTask.setText("  Přidat úkol");
            img = ImageIO.read(getClass().getResource("res/addTask.png"));
            jAddTask.setIcon(new ImageIcon(img));
            jDeleteTask.setText("  Vymazat úkol");
            img = ImageIO.read(getClass().getResource("res/delTask.png"));
            jDeleteTask.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("res/assignTasks.png"));
            jAssignTask.setIcon(new ImageIcon(img));
            jAssignTask.setText("  Přidělit úkoly");
            jEditTask.setText("  Upravit úkol");
            img = ImageIO.read(getClass().getResource("res/editTask.png"));
            jEditTask.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
            String textChyby = "Chyba při načítání avataru! " + ex.toString();
            jLabelStatus.setText(textChyby);
        }

        jAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                AddUserDialog addUser = new AddUserDialog(null, true);
                addUser.setVisible(true);
                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }
            }
        });

        jEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                EditTaskDialog et = new EditTaskDialog(null, true);
                et.setVisible(true);
                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }
            }
        });

        jDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                try {
                    int idUzivatele = Integer.parseInt(JOptionPane.showInputDialog("Zadejte ID uživatele, kterého chcete vymazat."));
                    Statement stmt = OracleConnector.getConnection().createStatement();
                    ResultSet rs = stmt.executeQuery("select id_uzivatele from SP_UZIVATELE where id_uzivatele = " + idUzivatele);
                    if (!rs.next()) {
                        jLabelStatus.setText("Uzivatel se zadanym ID neexistuje");
                        return;
                    }

                    stmt = null;
                    String query = "delete"
                            + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UZIVATELE
                            + " where id_uzivatele = " + idUzivatele;
                    try {
                        stmt = OracleConnector.getConnection().createStatement();
                        rs = stmt.executeQuery(query);
                        jLabelStatus.setText("Uživatel s id " + idUzivatele + " úspěšně odstraněn!");
                        OracleConnector.getConnection().commit();
                    } catch (SQLException ex) {
                        String textChyby = "Chyba při odstraňování uživatele s id " + idUzivatele + ". " + ex.toString();
                        jLabelStatus.setText(textChyby);
                    }
                    for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                        ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                        panel.obnovTabulku();
                    }
                } catch (Exception e) {
                }
            }
        });

        jAddTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                AddTaskDialog addTask = new AddTaskDialog(null, true);
                addTask.setVisible(true);
                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }
            }
        });
        jDeleteTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                //int row = ukoly.getSelectedRow();
                int idUkolu;
                try {
                    idUkolu = Integer.parseInt(JOptionPane.showInputDialog("Zadejte ID úkolu, který chcete vymazat."));
                    Statement stmt = OracleConnector.getConnection().createStatement();
                    ResultSet rs = stmt.executeQuery("select id_ukolu from SP_UKOLY where id_ukolu = " + idUkolu);
                    if (!rs.next()) {
                        jLabelStatus.setText("Ukol se zadanym ID neexistuje");
                        return;
                    }
                } catch (Exception e) {
                    return;
                }

                Statement stmt = null;
                String query = "delete"
                        + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UKOLY
                        + " where id_ukolu = " + idUkolu;
                try {
                    stmt = OracleConnector.getConnection().createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    jLabelStatus.setText("Úkol s id " + idUkolu + " úspěšně odstraněn!");
                    OracleConnector.getConnection().commit();
                } catch (SQLException ex) {
                    String textChyby = "Chyba při odstraňování úkolu s id " + idUkolu + ". " + ex.toString();
                    jLabelStatus.setText(textChyby);
                }
                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }

            }
        });

        jAssignTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }

            private void jButton1ActionPerformed(ActionEvent evt) {
                if (jTabbedPaneObsah.getSelectedIndex() == 1) {
                    AktivniUkoly aktivniPanel = (AktivniUkoly) jTabbedPaneObsah.getSelectedComponent();
                    int vysledek = aktivniPanel.getSelectedRow();
                    if (vysledek != -1) {
                        int id_ukolu = aktivniPanel.getIDUkol(vysledek);
                        AssignTasksDialog assignTask = new AssignTasksDialog(null, true, id_ukolu);
                        assignTask.setVisible(true);
                    } else {
                        AssignTasksDialog assignTask = new AssignTasksDialog(null, true, -1);
                        assignTask.setVisible(true);
                    }

                } else {
                    AssignTasksDialog assignTask = new AssignTasksDialog(null, true, -1);
                    assignTask.setVisible(true);
                }

                for (int i = 0; i < jTabbedPaneObsah.getTabCount(); i++) {
                    ObnovitelnyPanel panel = (ObnovitelnyPanel) jTabbedPaneObsah.getComponentAt(i);
                    panel.obnovTabulku();
                }
            }
        });

        jPanelTlacitka.setLayout(new GridLayout(6, 1));
        jPanelTlacitka.add(jAddUser);
        jPanelTlacitka.add(jDeleteUser);
        jPanelTlacitka.add(jAddTask);
        jPanelTlacitka.add(jDeleteTask);
        jPanelTlacitka.add(jAssignTask);
        jPanelTlacitka.add(jEditTask);
    }

    private void pripojDoDatabaze() {
        try {
            OracleConnector.setUpConnection("fei-sql1.upceucebny.cz", 1521, "ee11", MyDBProperties.MYNAME, MyDBProperties.MYPWD);
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Chyba při připojení do databáze!");
            System.exit(-1);
        }
    }

    private void prihlasUzivateleUpominek() {
        String stav = "";
        String user = "";
        OUTER:
        while (true) {
            LoginDialog login = new LoginDialog(this, true);
            login.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            login.setStav(stav);
            login.setUser(user);
            if (user.compareTo("") != 0) {
                login.setFocusPass();
            }
            login.setVisible(true);
            String jmeno;
            String heslo;
            int vysledek;
            jmeno = login.getUsername();
            heslo = login.getPassword();
            try {
                if (jmeno.compareTo("GUEST") == 0) {
                    PRIHLASENY_UZIVATEL = "GUEST";
                    PRIHLASENY_UZIVATEL_ROLE = ROLE_GUEST;
                    PRIHLASENY_UZIVATEL_ID = -1;
                    break;
                }
                vysledek = prihlasitUzivatele(jmeno, heslo);
                switch (vysledek) {
                    case SPATNE_HESLO:
                        stav = "Zadali jste špatné heslo!";
                        user = jmeno;
                        break;
                    case UZIVATEL_NEEXISTUJE:
                        stav = "Zadany uzivatel neexistuje!";
                        user = "";
                        break;
                    default:
                        break OUTER;
                }
            } catch (Exception e) {
                System.err.println("Zadejte platne jmeno a heslo!");
                login.setVisible(true);
            }
        }

        switch (PRIHLASENY_UZIVATEL_ROLE) {
            case ROLE_ADMIN:
                initAdmin();
                initProfil();
                break;
            case ROLE_USER:
                initUser();
                initProfil();
                break;
            case ROLE_GUEST:
                initGuest();
                break;
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

        jLabelStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanelProfil = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldJmeno = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPrijmeni = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPrezdivka = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldZnameni = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanelTlacitka = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPaneObsah = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatus.setText("Stavový řádek");
        getContentPane().add(jLabelStatus, java.awt.BorderLayout.SOUTH);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanelProfil.setPreferredSize(new java.awt.Dimension(200, 100));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("JmenoUziv");

        jPanel5.setMaximumSize(new java.awt.Dimension(207, 300));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel5.add(jLabel8, java.awt.BorderLayout.CENTER);

        jLabel2.setText("Jméno:");

        jTextFieldJmeno.setEditable(false);
        jTextFieldJmeno.setText("jTextField1");

        jLabel3.setText("Příjmení:");

        jTextFieldPrijmeni.setEditable(false);
        jTextFieldPrijmeni.setText("jTextField2");

        jLabel4.setText("Přezdívka:");

        jTextFieldPrezdivka.setEditable(false);
        jTextFieldPrezdivka.setText("jTextField3");

        jLabel5.setText("Znamení:");

        jLabel6.setText("Počet pochval: 0");

        jLabel7.setText("Role: USER");

        jTextFieldZnameni.setEditable(false);
        jTextFieldZnameni.setText("jTextField1");

        jButton1.setText("Zkontroluj pochvaly");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Statistiky uživatelů");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Zkontroluj deadline");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProfilLayout = new javax.swing.GroupLayout(jPanelProfil);
        jPanelProfil.setLayout(jPanelProfilLayout);
        jPanelProfilLayout.setHorizontalGroup(
            jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jTextFieldJmeno)
                    .addComponent(jTextFieldPrijmeni)
                    .addComponent(jTextFieldPrezdivka)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldZnameni)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelProfilLayout.createSequentialGroup()
                        .addGroup(jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelProfilLayout.setVerticalGroup(
            jPanelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldJmeno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPrijmeni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPrezdivka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldZnameni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(16, 16, 16))
        );

        jPanel1.add(jPanelProfil, java.awt.BorderLayout.WEST);

        jPanelTlacitka.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanelTlacitka.setLayout(new java.awt.GridLayout(3, 1));
        jPanel1.add(jPanelTlacitka, java.awt.BorderLayout.EAST);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(300, 300));

        jTabbedPaneObsah.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPaneObsahStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneObsah, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneObsah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenuBar1.setBorder(null);

        jMenu1.setText("Uživatel");

        jMenuItem2.setText("Upravit profil");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Odhlásit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPaneObsahStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPaneObsahStateChanged

    }//GEN-LAST:event_jTabbedPaneObsahStateChanged

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        prihlasUzivateleUpominek();
        this.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        EditProfileDialog upravProfil = new EditProfileDialog(this, true, PRIHLASENY_UZIVATEL, PRIHLASENY_UZIVATEL_ID);
        upravProfil.setVisible(true);
        PRIHLASENY_UZIVATEL = upravProfil.getUzivatel();
        jLabel1.setText(PRIHLASENY_UZIVATEL);
        jTextFieldPrezdivka.setText(PRIHLASENY_UZIVATEL);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int rozdanoPochval, odebranoPochval;
            CallableStatement callableStatement = null; // priprava na vlozeni procedury
            String getReport = "{call SP_PROCEDURY_FUNKCE.SP_UDELIT_ODEBRAT_POCVHALU(?,?)}";// nastaveni pro zavolani procedury z databaze
            ResultSet rs = null; //vysledek po execute

            callableStatement = OracleConnector.getConnection().prepareCall(getReport);// priprava pocedury na zavolani s nastaveným propojenim
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER); // nastaveni vystupniho parametru
            callableStatement.registerOutParameter(2, OracleTypes.INTEGER); // nastaveni vystupniho parametru
            callableStatement.execute(); // provedeni Statementu

            rozdanoPochval = (int) callableStatement.getObject(1);
            odebranoPochval = (int) callableStatement.getObject(2);

            System.out.println("Pocet rozdanych pochval: " + rozdanoPochval);
            System.out.println("Pocet odebranych pochval: " + odebranoPochval);
            JOptionPane.showMessageDialog(jPanel1, " " + rozdanoPochval + " muzu a " + odebranoPochval + " zen.");
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            int pocetMuzu, pocetZen;
            CallableStatement callableStatement = null; // priprava na vlozeni procedury
            String getReport = "{call SP_PROCEDURY_FUNKCE.SP_POCET_MUZI_ZENY(?,?)}";// nastaveni pro zavolani procedury z databaze
            ResultSet rs = null; //vysledek po execute

            callableStatement = OracleConnector.getConnection().prepareCall(getReport);// priprava pocedury na zavolani s nastaveným propojenim
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER); // nastaveni vystupniho parametru
            callableStatement.registerOutParameter(2, OracleTypes.INTEGER); // nastaveni vystupniho parametru
            callableStatement.execute(); // provedeni Statementu

            pocetMuzu = (int) callableStatement.getObject(1);
            pocetZen = (int) callableStatement.getObject(2);

            System.out.println("Pocet muzu: " + pocetMuzu);
            System.out.println("Pocet zen: " + pocetZen);
            JOptionPane.showMessageDialog(jPanel1, "V databazi je: " + pocetMuzu + " muzu a " + pocetZen + " zen.");
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
try {
            int pocetNesplnenych;
            CallableStatement callableStatement = null; // priprava na vlozeni procedury
            String getReport = "{call SP_PROCEDURY_FUNKCE.SP_DEADLINE_UKOLU(?)}";// nastaveni pro zavolani procedury z databaze
            ResultSet rs = null; //vysledek po execute
 
            callableStatement = OracleConnector.getConnection().prepareCall(getReport);// priprava pocedury na zavolani s nastaveným propojenim
            callableStatement.registerOutParameter(1, OracleTypes.INTEGER); // nastaveni vystupniho parametru
            callableStatement.execute(); // provedeni Statementu
 
            pocetNesplnenych = (int) callableStatement.getObject(1);
            JOptionPane.showMessageDialog(jPanelProfil, "Pocet ukolu kterym vyprsel deadline a nebyly splneny je: " + pocetNesplnenych);
            System.out.println("Pocet nesplnenych: " + pocetNesplnenych);
 
 
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    private int prihlasitUzivatele(String jmeno, String heslo) {
        try {
            String vysledekHashe;
            CallableStatement cstmt = OracleConnector.getConnection().prepareCall("{? = call MD5HASH(?)}");
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setString(2, heslo);
            cstmt.executeUpdate();
            vysledekHashe = cstmt.getString(1);
            Statement stmt = null;
            String query = "select id_uzivatele, prezdivka, heslo, id_role"
                    + " from " + MyDBProperties.MYNAME + "." + MyDBProperties.UZIVATELE
                    + " where prezdivka = '" + jmeno + "'";
            stmt = OracleConnector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return UZIVATEL_NEEXISTUJE;
            } else if (vysledekHashe.compareTo(rs.getString("heslo")) == 0) {
                PRIHLASENY_UZIVATEL = rs.getString("prezdivka");
                PRIHLASENY_UZIVATEL_ID = rs.getInt("id_uzivatele");
                int role = rs.getInt("id_role");
                switch (role) {
                    case ROLE_ADMIN:
                        PRIHLASENY_UZIVATEL_ROLE = ROLE_ADMIN;
                        break;
                    default:
                        PRIHLASENY_UZIVATEL_ROLE = ROLE_USER;
                        break;
                }
                jLabelStatus.setText("Uspesne prihlasen uzivatel " + PRIHLASENY_UZIVATEL);
                return USPECH;
            } else {
                return SPATNE_HESLO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelProfil;
    private javax.swing.JPanel jPanelTlacitka;
    private javax.swing.JTabbedPane jTabbedPaneObsah;
    private javax.swing.JTextField jTextFieldJmeno;
    private javax.swing.JTextField jTextFieldPrezdivka;
    private javax.swing.JTextField jTextFieldPrijmeni;
    private javax.swing.JTextField jTextFieldZnameni;
    // End of variables declaration//GEN-END:variables

}
