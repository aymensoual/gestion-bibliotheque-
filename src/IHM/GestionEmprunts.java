package IHM;

import Utility.BibalExceptions;
import Utility.DBConnection;
import static Utility.Utility.closeStatementResultSet;
import static Utility.Utility.initialiseRequetePreparee;
import static Utility.Utility.showMessageSucces;
import control.EmpruntControl;
import control.UsagerControl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Integer.parseInt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.*;
import objets_metiers.Usager;

/**
 *
 * @author Aymen Souelmi
 */
public class GestionEmprunts extends JDialog {

    private JButton annulerBouton;
    private JLabel identifiantLabel;
    private JComboBox identifiantUsagerCombo;
    private JPanel jPanel1;
    private JLabel nomLabel;
    private JLabel nomUsagerLabel;
    private JPanel panAjoutBas;
    private JLabel titleLabel;
    private JButton validerBouton;

    public GestionEmprunts(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setIdentifiant();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/bibliotheque_icone.png")));
    }

    public GestionEmprunts(java.awt.Frame parent, boolean modal, String titre) {
        this(parent, modal);
        this.titre = titre;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        titleLabel = new JLabel();
        panAjoutBas = new JPanel();
        nomLabel = new JLabel();
        identifiantLabel = new JLabel();
        identifiantUsagerCombo = new JComboBox();
        validerBouton = new JButton();
        annulerBouton = new JButton();
        nomUsagerLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emprunter");

        jPanel1.setBackground(new Color(255, 255, 255));
        jPanel1.setName("Gestion personnel"); 
        jPanel1.setPreferredSize(new Dimension(1162, 608));
        jPanel1.setRequestFocusEnabled(false);

        titleLabel.setFont(new Font("Times New Roman", 1, 36)); 
        titleLabel.setForeground(new Color(19, 102, 170));
        titleLabel.setText("Choisir Usager");

        panAjoutBas.setBackground(new Color(255, 255, 255));
        panAjoutBas.setBorder(BorderFactory.createTitledBorder(""));

        nomLabel.setFont(new Font("Times New Roman", 1, 14)); 
        nomLabel.setForeground(new Color(19, 102, 170));
        nomLabel.setText("Nom usager");

        identifiantLabel.setFont(new Font("Times New Roman", 1, 14)); 
        identifiantLabel.setForeground(new Color(19, 102, 170));
        identifiantLabel.setText("Identifiant ");

        identifiantUsagerCombo.setFont(new Font("Times New Roman", 1, 12)); 
        identifiantUsagerCombo.setModel(new DefaultComboBoxModel(new String[]{"Choisir identifiant"}));
        identifiantUsagerCombo.setPreferredSize(new Dimension(123, 26));
        identifiantUsagerCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                identifiantUsagerComboActionPerformed(evt);
            }
        });

        validerBouton.setFont(new Font("Times New Roman", 1, 16)); 
        validerBouton.setText("Valider");
        validerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emprunter(evt);
            }
        });

        annulerBouton.setFont(new Font("Times New Roman", 1, 16)); 
        annulerBouton.setText("Annuler");
        annulerBouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                annulerBoutonActionPerformed(evt);
            }
        });

        nomUsagerLabel.setFont(new Font("Times New Roman", 1, 18)); 
        nomUsagerLabel.setText(" ");

        GroupLayout panAjoutBasLayout = new GroupLayout(panAjoutBas);
        panAjoutBas.setLayout(panAjoutBasLayout);
        panAjoutBasLayout.setHorizontalGroup(
                panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, panAjoutBasLayout.createSequentialGroup()
                                        .addGap(0, 169, Short.MAX_VALUE)
                                        .addComponent(annulerBouton)
                                        .addGap(30, 30, 30)
                                        .addComponent(validerBouton))
                                .addGroup(panAjoutBasLayout.createSequentialGroup()
                                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(nomLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(identifiantLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(62, 62, 62)
                                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(identifiantUsagerCombo, 0, 230, Short.MAX_VALUE)
                                                .addComponent(nomUsagerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
        );
        panAjoutBasLayout.setVerticalGroup(
                panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panAjoutBasLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(identifiantLabel)
                                .addComponent(identifiantUsagerCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nomLabel)
                                .addComponent(nomUsagerLabel))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(panAjoutBasLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(annulerBouton)
                                .addComponent(validerBouton))
                        .addGap(5, 5, 5))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panAjoutBas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(titleLabel)
                        .addGap(18, 18, 18)
                        .addComponent(panAjoutBas, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>                        

     private void identifiantUsagerComboActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        try {
            int usagerId = getUsagerID();
            if (usagerId != -1) {
                Usager usager = UsagerControl.findById(usagerId);
                if (null != usager) {
                    String nom = usager.getNom() + " " + usager.getPrenom();
                    nomUsagerLabel.setText(nom);
                }
            }
        } catch (BibalExceptions e) {
            System.out.println("IHM.Emprunter.identifiantUsagerComboActionPerformed()");
        }
    }                                                      

    private void emprunter(java.awt.event.ActionEvent evt) {                           

        try {
            int usagerId = getUsagerID();
            if (usagerId != -1) {
                EmpruntControl.emprunter(usagerId, titre);
                showMessageSucces("Emprunt enregistré");
            } else {
                showMessageSucces("Veuillez choisir un identifiant");
            }
        } catch (BibalExceptions e) {
            System.out.println("IHM.Emprunter.validerBoutonActionPerformed()");
        }
    }                          

    private void annulerBoutonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        this.dispose();
    }                                             

    private int getUsagerID() {
        String id = identifiantUsagerCombo.getSelectedItem().toString();
        if (!id.equals("Choisir identifiant")) {
            return parseInt(id);
        }
        return -1;
    }

    private void setIdentifiant() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            final String SQL_SELECT_ID = "SELECT id FROM usager ";
            preparedStatement = initialiseRequetePreparee(DBConnection.getConnection(),
                    SQL_SELECT_ID, new Object[0]);
            resultSet = preparedStatement.executeQuery();
            int identifiant;
            while (resultSet.next()) {
                identifiant = resultSet.getInt("id");
                identifiantUsagerCombo.addItem(Integer.toString(identifiant));
            }
        } catch (SQLException | BibalExceptions e) {
            JOptionPane.showMessageDialog(null, "Erreurs d'accès à la base de données",
                    "Erreurs", JOptionPane.ERROR_MESSAGE);
        } finally {
            closeStatementResultSet(preparedStatement, resultSet);
        }
    }

    private String titre;                 
}
