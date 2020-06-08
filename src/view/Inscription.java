package view;

import dao.JpaLocalisationDao;
import dao.JpaUtilisateurDao;
import metier.LocalisationEntity;
import metier.UtilisateurEntity;
import utils.DesignJPanelUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Collection;

public class Inscription extends JDialog {

    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private JLabel jLabelTitle;
    private JLabel[] labels;
    private JComponent[] components;
    private JButton jButtonConfirmer;
    private JPanel jPanel;
    private LocalisationEntity localisationEntity;

    public Inscription(Frame owner, boolean modal) {
        super(owner, modal);

        setComponent();
        showComponent();

        this.setContentPane(jPanel);
        this.setTitle("Inscription");
        this.setSize(new Dimension(500, 700));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setComponent() {
        jLabelTitle = new JLabel("Rentrer les informations de votre nouveau compte");

        JLabel jLabelEmail = new JLabel("E-mail :");
        JLabel jLabelPassword = new JLabel("Mot de passe :");
        JLabel jLabelPasswordConfirm = new JLabel("Confirmer le mot de passe :");
        JLabel jLabelCodePostal = new JLabel("Code postal :");
        JLabel jLabelVille = new JLabel("Ville :");
        JLabel jLabelRue = new JLabel("Rue :");
        JLabel jLabelNumRue = new JLabel("Numéro dans la rue :");
        JLabel jLabelNumTel = new JLabel("Numéro de téléphone :");
        labels = new JLabel[]{
                jLabelEmail, jLabelPassword, jLabelPasswordConfirm, jLabelCodePostal,
                jLabelVille, jLabelRue, jLabelNumRue, jLabelNumTel
        };

        JTextField jTextFieldEmail = new JTextField();
        JPasswordField jPasswordFieldPassword = new JPasswordField();
        JPasswordField jPasswordFieldPasswordConfirm = new JPasswordField();
        JTextField jTextFieldCodePostal = new JTextField();
        JComboBox<LocalisationEntity> jComboBoxLocalisation = new JComboBox<>();
        JTextField jTextFieldRue = new JTextField();
        JTextField jTextFieldNumRue = new JTextField();
        JTextField jTextFieldNumTel = new JTextField();
        components = new JComponent[]{
                jTextFieldEmail, jPasswordFieldPassword, jPasswordFieldPasswordConfirm, jTextFieldCodePostal,
                jComboBoxLocalisation, jTextFieldRue, jTextFieldNumRue, jTextFieldNumTel
        };

        jTextFieldCodePostal.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                jComboBoxLocalisation.removeAllItems();
                JpaLocalisationDao jpaLocalisationDao = new JpaLocalisationDao();
                Collection<LocalisationEntity> localisationEntities = jpaLocalisationDao.findVilles(jTextFieldCodePostal.getText());
                for (LocalisationEntity currentLocalisation : localisationEntities) {
                    jComboBoxLocalisation.addItem(currentLocalisation);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                jComboBoxLocalisation.removeAllItems();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        jComboBoxLocalisation.addActionListener(actionEvent -> localisationEntity = jComboBoxLocalisation.getItemAt(jComboBoxLocalisation.getSelectedIndex()));

        jButtonConfirmer = new JButton("Confirmer l'inscription");

        jButtonConfirmer.addActionListener(actionEvent -> {

            String password = new String(jPasswordFieldPassword.getPassword());
            String confirmPassword = new String(jPasswordFieldPasswordConfirm.getPassword());

            if (password.equals(confirmPassword) && !password.equals("")) {

                UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
                utilisateurEntity.setLogin(jTextFieldEmail.getText());
                utilisateurEntity.setPassword(password);
                utilisateurEntity.setVille(localisationEntity.getNomVille());
                utilisateurEntity.setIdLocalisationUtilisateur(localisationEntity);
                utilisateurEntity.setRue(jTextFieldRue.getText());
                utilisateurEntity.setNumRue(Integer.parseInt(jTextFieldNumRue.getText()));
                utilisateurEntity.setNumTel(jTextFieldNumTel.getText());

                JpaUtilisateurDao jpaUtilisateurDao = new JpaUtilisateurDao();

                try {
                    if (jpaUtilisateurDao.create(utilisateurEntity)) {
                        System.out.println("Inscription.java -> jButtonConfirmer(ActionListener) : Inscription réussie");
                        this.dispose();
                    } else {
                        System.out.println("Inscription.java -> jButtonConfirmer(ActionListener) : Inscription échouée");
                        javax.swing.JOptionPane.showMessageDialog(null, "Inscription échouée");
                    }

                } catch (Exception exception) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Connexion échouée");
                    System.out.println("Inscription.java -> jButtonConfirmer(ActionListener) : " + exception.getMessage());
                }
            } else {
                System.out.println("Inscription.java -> jButtonConfirmer(ActionListener) : Inscription échouée - information manquante");
                javax.swing.JOptionPane.showMessageDialog(null, "Inscription échouée - information manquante");
            }
        });
    }

    private void showComponent() {
        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        designJPanelUtils.addComponent(jPanel, jLabelTitle, 0, 0, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, null, null, null, null, null, null);

        for (int i = 0; i < labels.length; i++) {
            designJPanelUtils.addComponent(jPanel, labels[i], 0, (i * 2) + 1, 1, 1, 1d, 0.01, GridBagConstraints.LINE_START, GridBagConstraints.BASELINE, 0, 32, 8, 0, null, null);
            designJPanelUtils.addComponent(jPanel, components[i], 0, (i * 2) + 2, 1, 1, 1d, 0.01, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 0, 48, 16, 48, null, null);
        }

        designJPanelUtils.addComponent(jPanel, jButtonConfirmer, 0, (labels.length * 2) + 1, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, null, null, null, null, null, null);

    }

}
