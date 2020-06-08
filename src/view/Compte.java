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

public class Compte extends JDialog {

    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private JPanel jPanel;
    private JLabel jLabelTitle;
    private JLabel[] labels;
    private JComponent[] components;
    private JButton jButtonToggle;
    private UtilisateurEntity utilisateurEntity;

    public Compte(Frame owner, boolean modal, UtilisateurEntity utilisateurEntity) {
        super(owner, modal);
        this.utilisateurEntity = utilisateurEntity;

        setComponent();
        showComponent();

        this.setContentPane(jPanel);
        this.setTitle("Mon compte");
        this.setSize(new Dimension(500, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void setComponent() {
        jLabelTitle = new JLabel("Voici les informations concernant votre compte");

        JLabel jLabelEmail = new JLabel("E-mail :");
        JLabel jLabelPassword = new JLabel("Mot de passe :");
        JLabel jLabelCodePostal = new JLabel("Code postal :");
        JLabel jLabelVille = new JLabel("Ville :");
        JLabel jLabelRue = new JLabel("Rue :");
        JLabel jLabelNumRue = new JLabel("Numéro dans la rue :");
        JLabel jLabelNumTel = new JLabel("Numéro de téléphone :");
        labels = new JLabel[]{
                jLabelEmail, jLabelPassword, jLabelCodePostal,
                jLabelVille, jLabelRue, jLabelNumRue, jLabelNumTel
        };

        LocalisationEntity localisationEntity = utilisateurEntity.getIdLocalisationUtilisateur();

        JTextField jTextFieldEmail = new JTextField(utilisateurEntity.getLogin());
        JPasswordField jPasswordFieldPassword = new JPasswordField(utilisateurEntity.getPassword());
        JTextField jTextFieldCodePostal = new JTextField(localisationEntity.getCode_postal());
        JComboBox<LocalisationEntity> jComboBoxLocalisation = new JComboBox<>();
        JTextField jTextFieldRue = new JTextField(utilisateurEntity.getRue());
        JTextField jTextFieldNumRue = new JTextField(String.valueOf(utilisateurEntity.getNumRue()));
        JTextField jTextFieldNumTel = new JTextField(utilisateurEntity.getNumTel());
        components = new JComponent[]{
                jTextFieldEmail, jPasswordFieldPassword, jTextFieldCodePostal,
                jComboBoxLocalisation, jTextFieldRue, jTextFieldNumRue, jTextFieldNumTel
        };
        enabledComponents(false);

        JpaLocalisationDao jpaLocalisationDao = new JpaLocalisationDao();
        Collection<LocalisationEntity> localisationEntities = jpaLocalisationDao.findVilles(localisationEntity.getCode_postal());
        for (LocalisationEntity currentLocalisation : localisationEntities) {
            jComboBoxLocalisation.addItem(currentLocalisation);
        }
        jComboBoxLocalisation.setSelectedItem(localisationEntity);

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

        jButtonToggle = new JButton("Modifier");
        jButtonToggle.addActionListener(actionEvent -> {
            if (jButtonToggle.getText().equals("Modifier")) {
                enabledComponents(true);
                jButtonToggle.setText(jButtonToggle.getText().equals("Modifier") ? "Sauvegarder" : "Modifier");
            } else {
                if (checkComponents()) {
                    enabledComponents(false);
                    utilisateurEntity.setLogin(jTextFieldEmail.getText());
                    utilisateurEntity.setPassword(new String(jPasswordFieldPassword.getPassword()));
                    utilisateurEntity.setIdLocalisationUtilisateur((LocalisationEntity) jComboBoxLocalisation.getSelectedItem());
                    utilisateurEntity.setRue(jTextFieldRue.getText());
                    utilisateurEntity.setNumRue(Integer.parseInt(jTextFieldNumRue.getText()));
                    utilisateurEntity.setNumTel(jTextFieldNumTel.getText());
                    if (jComboBoxLocalisation.getSelectedItem() != null) {
                        utilisateurEntity.setVille(((LocalisationEntity) jComboBoxLocalisation.getSelectedItem()).getNomVille());
                    }

                    JpaUtilisateurDao jpaUtilisateurDao = new JpaUtilisateurDao();
                    try {
                        if (jpaUtilisateurDao.update(utilisateurEntity)) {
                            System.out.println("Compte.java -> jButtonSave(ActionListener) : Update réussie");
                            jButtonToggle.setText(jButtonToggle.getText().equals("Modifier") ? "Sauvegarder" : "Modifier");
                            Acceuil acceuil = (Acceuil) getOwner();
                            acceuil.setUser(utilisateurEntity);
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Echec de la modification du compte");
                            System.out.println("Compte.java -> jButtonSave(ActionListener) : Update échouée");
                        }

                    } catch (Exception exception) {
                        System.out.println("Compte.java -> jButtonSave(ActionListener) : " + exception.getMessage());
                    }
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Echec de la modification du compte - Informations manquantes");
                    System.out.println("Compte.java -> jButtonSave(ActionListener) : Information manquante");
                }
            }
        });

        jComboBoxLocalisation.addActionListener(actionEvent -> {
            if (jButtonToggle.getLabel().equals("Modifier")) {
                jComboBoxLocalisation.setSelectedItem(localisationEntity);
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

        designJPanelUtils.addComponent(jPanel, jButtonToggle, 0, (labels.length * 2) + 1, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, null, null, null, null, null, null);

    }

    private void enabledComponents(boolean bool) {
        for (JComponent currentComponent : components) {
            if (currentComponent != components[0] || !bool) {
                if (currentComponent instanceof JTextField) ((JTextField) currentComponent).setEditable(bool);
            }
        }
    }

    private boolean checkComponents() {
        for (JComponent currentComponent : components) {
            if (currentComponent instanceof JTextField) {
                if (((JTextField) currentComponent).getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

}
