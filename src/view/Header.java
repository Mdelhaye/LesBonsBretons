package view;

import metier.UtilisateurEntity;
import utils.DesignJPanelUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class Header extends JPanel {

    public static Dimension dimension = new Dimension(200, 25);
    public static String title = "<html><h1>Les Bons Bretons</h1></html>";
    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private final JFrame acceuil;
    private final UtilisateurEntity utilisateurEntity;

    public Header(JFrame parent, UtilisateurEntity utilisateurEntity) {
        this.setLayout(new GridBagLayout());
        this.acceuil = parent;
        this.utilisateurEntity = utilisateurEntity;
        setComponent();
    }

    private void setComponent() {
        JLabel jLabelTitle = new JLabel(title);
        designJPanelUtils.addComponent(this, jLabelTitle, 0, 0, 1, null, 10d, 1d, GridBagConstraints.LINE_START, GridBagConstraints.BASELINE, 0, 25, 0, 0, null, null);

        if (utilisateurEntity != null) {
            JLabel jLabelUtilisateur = new JLabel("Bonjour " + utilisateurEntity.getLogin());

            JComboBox<String> jComboBoxButton = new JComboBox<>();
            jComboBoxButton.addItem("Menu");
            jComboBoxButton.addItem("Mon compte");
            jComboBoxButton.addItem("Mes annonces");
            jComboBoxButton.addItem("Déconnexion");
            jComboBoxButton.addItemListener(itemEvent -> {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    switch (itemEvent.getItem().toString()) {
                        case "Mon compte":
                            jComboBoxButton.setSelectedIndex(0);
                            Compte compte = new Compte(acceuil, true, utilisateurEntity);
                            break;
                        case "Mes annonces":
                            jComboBoxButton.setSelectedIndex(0);
                            AnnoncesUtilisateur annoncesUtilisateur = new AnnoncesUtilisateur(acceuil, true, utilisateurEntity);
                            break;
                        case "Déconnexion":
                            Acceuil acceuil = (Acceuil) this.acceuil;
                            acceuil.setUser(null);
                            System.out.println("Header.java -> jComboBoxButton(Déconnexion) : Déconnexion réussie");
                            break;
                    }
                }
            });

            designJPanelUtils.addComponent(this, jLabelUtilisateur, 1, 0, 1, null, 0.1, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 0, 0, 0, 5, null, null);
            designJPanelUtils.addComponent(this, jComboBoxButton, 2, 0, 1, null, 0.1, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 0, 0, 0, 5, null, null);

        } else {
            JButton jButtonConnexion = new JButton("Connexion");
            jButtonConnexion.setPreferredSize(dimension);

            JButton jButtonInscription = new JButton("Inscription");
            jButtonInscription.setPreferredSize(dimension);

            designJPanelUtils.addComponent(this, jButtonConnexion, 1, 0, 1, null, 0.1, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, null, null, null, null, null, null);
            designJPanelUtils.addComponent(this, jButtonInscription, 2, 0, 1, null, 0.1, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 0, 0, 0, 5, null, null);

            jButtonConnexion.addActionListener(actionEvent -> {
                Connexion connexion = new Connexion(acceuil, true);
            });

            jButtonInscription.addActionListener(actionEvent -> {
                Inscription inscription = new Inscription(acceuil, true);
            });
        }

    }
}
