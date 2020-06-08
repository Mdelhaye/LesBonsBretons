package view;

import dao.JpaUtilisateurDao;
import metier.UtilisateurEntity;
import utils.DesignJPanelUtils;

import javax.swing.*;
import java.awt.*;

public class Connexion extends JDialog {

    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private JLabel jLabelTitle;
    private JLabel[] labels;
    private JComponent[] components;
    private JButton jButtonConfirmer;
    private JPanel jPanel;

    public Connexion(Frame owner, boolean modal) {
        super(owner, modal);

        setComponent();
        showComponent();

        this.setContentPane(jPanel);
        this.setTitle("Connexion");
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setComponent() {
        jLabelTitle = new JLabel("Rentrer les informations de connexion");

        JLabel jLabelEmail = new JLabel("E-mail :");
        JLabel jLabelPassword = new JLabel("Mot de passe :");
        labels = new JLabel[]{
                jLabelEmail, jLabelPassword
        };

        JTextField jTextFieldEmail = new JTextField();
        JPasswordField jPasswordFieldPassword = new JPasswordField();
        components = new JComponent[]{
                jTextFieldEmail, jPasswordFieldPassword
        };

        jButtonConfirmer = new JButton("Confirmer la connexion");
        jButtonConfirmer.addActionListener(actionEvent -> {

            JpaUtilisateurDao jpaUtilisateurDao = new JpaUtilisateurDao();
            try {
                UtilisateurEntity utilisateurEntity = jpaUtilisateurDao.connexionUser(jTextFieldEmail.getText(), jPasswordFieldPassword.getText());
                if (utilisateurEntity != null) {
                    System.out.println("Connexion.java -> jButtonConfirmer(ActionListener) : Connexion réussie");
                    Acceuil acceuil = (Acceuil) getOwner();
                    acceuil.setUser(utilisateurEntity);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Connexion échouée");
                    System.out.println("Connexion.java -> jButtonConfirmer(ActionListener) : Connexion échouée");

                }
            } catch (Exception exception) {
                javax.swing.JOptionPane.showMessageDialog(null, "Connexion échouée");
                System.out.println("Connexion.java -> jButtonConfirmer(ActionListener) : " + exception.getMessage());
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
