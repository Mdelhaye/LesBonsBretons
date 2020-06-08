package view;

import dao.JpaAnnonceDao;
import metier.AnnonceEntity;
import metier.UtilisateurEntity;
import utils.DesignJPanelUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AnnoncesUtilisateur extends JDialog {

    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();
    public static Dimension dimension = new Dimension(200, 25);
    public JpaAnnonceDao jpaAnnonceDao = new JpaAnnonceDao();

    private JPanel jPanel;
    private UtilisateurEntity utilisateurEntity;
    private JComponent[] components;
    private ArrayList<AnnonceEntity> annonceEntities = new ArrayList<>();
    private AnnonceList annonceList;

    public AnnoncesUtilisateur(Frame owner, boolean modal, UtilisateurEntity utilisateurEntity) {
        super(owner, modal);
        this.utilisateurEntity = utilisateurEntity;

        setComponent();
        showComponent();

        this.setContentPane(jPanel);
        this.setTitle("Mes annonces");
        this.setSize(1500, 1000);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void setComponent() {
        annonceEntities.addAll(jpaAnnonceDao.findAnnonceByIdUser(utilisateurEntity));

        JButton jButtonAjouter = new JButton("Ajouter une annonce");
        jButtonAjouter.setPreferredSize(dimension);
        JButton jButtonModifier = new JButton("Modifier une annonce");
        jButtonModifier.setPreferredSize(dimension);
        JButton jButtonSupprimer = new JButton("Supprimer une annonce");
        jButtonSupprimer.setPreferredSize(dimension);
        components = new JComponent[]{
                jButtonAjouter, jButtonModifier, jButtonSupprimer
        };

        jButtonAjouter.addActionListener(actionEvent -> {
            AnnonceAjout annonceAjout = new AnnonceAjout((Frame) getOwner(), true, utilisateurEntity, this);
            updateAnnonceList();
        });

        jButtonModifier.addActionListener(actionEvent -> {
            String buttonInfo = (annonceList.getRadioButtonSelected()).getName();
            for (AnnonceEntity currentAnnonce : annonceEntities) {
                if (currentAnnonce.getIdAnnonce() == Integer.parseInt(buttonInfo)) {
                    AnnonceModification annonceModification = new AnnonceModification((Frame) getOwner(), true, utilisateurEntity, currentAnnonce, this);
                }
            }
            updateAnnonceList();
        });

        jButtonSupprimer.addActionListener(actionEvent -> {
            String buttonInfo = (annonceList.getRadioButtonSelected()).getName();
            for (AnnonceEntity currentAnnonce : annonceEntities) {
                if (currentAnnonce.getIdAnnonce() == Integer.parseInt(buttonInfo)) {
                    try {
                        if (jpaAnnonceDao.delete(currentAnnonce)) {
                            System.out.println("AnnoncesUtilisateur.java -> jButtonSupprimer(ActionListener) : Annonce supprimée");
                            annonceEntities.remove(currentAnnonce);
                            updateAnnonceList();
                            return;
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Echec de la suppression de l'annonce");
                            System.out.println("AnnoncesUtilisateur.java -> jButtonSupprimer(ActionListener) : Annonce non supprimée");
                        }
                    } catch (Exception exception) {
                        System.out.println("AnnoncesUtilisateur.java -> jButtonSupprimer(ActionListener) : " + exception.getMessage());
                    }
                }
            }
        });
    }

    private void showComponent() {
        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < components.length; i++) {
            designJPanelUtils.addComponent(jPanel, components[i], i, 0, 1, null, 1d, 0.01, GridBagConstraints.NORTH, GridBagConstraints.BASELINE, 25, 25, 0, 25, null, null);
        }
        if (annonceEntities != null) {
            annonceList = new AnnonceList(new ArrayList<>(annonceEntities), true);
            designJPanelUtils.addComponent(jPanel, annonceList, 0, 1, 3, 1, 1d, 10d, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, 25, 25, 25, 25, null, null);
        }
        jPanel.updateUI();
    }

    private void updateAnnonceList() {
        annonceList.setAnnonceEntities(annonceEntities);
        annonceList.updateUI();
    }

    public void setAnnonceEntities() {
        annonceEntities = new ArrayList<>();
        annonceEntities.addAll(jpaAnnonceDao.findAnnonceByIdUser(utilisateurEntity));
        updateAnnonceList();
    }
}
