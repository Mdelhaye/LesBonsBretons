package view;

import dao.JpaAnnonceDao;
import dao.JpaCategorieDao;
import dao.JpaLocalisationDao;
import dao.JpaSurCategorieDao;
import metier.*;
import utils.DesignJPanelUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Collection;

public class AnnonceAjout extends JDialog {

    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private JPanel jPanel;
    private JLabel jLabelTitle;
    private JLabel[] labels;
    private JComponent[] components;
    private JButton jButtonAjouter;
    private UtilisateurEntity utilisateurEntity;
    private LocalisationEntity localisationEntity;
    private AnnoncesUtilisateur annoncesUtilisateur;

    public AnnonceAjout(Frame owner, boolean modal, UtilisateurEntity utilisateurEntity, AnnoncesUtilisateur annoncesUtilisateur) {
        super(owner, modal);
        this.utilisateurEntity = utilisateurEntity;
        this.annoncesUtilisateur = annoncesUtilisateur;

        setComponent();
        showComponent();

        this.setContentPane(jPanel);
        this.setTitle("Nouvelle annonce");
        this.setSize(new Dimension(1000, 1000));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setComponent() {
        jLabelTitle = new JLabel("Entrer les informations d'une nouvelle annonce");

        JLabel jLabelTitreAnnonce = new JLabel("Titre :");
        JLabel jLabelDescription = new JLabel("Description :");
        JLabel jLabelOffre = new JLabel("Est-ce une offre :");
        JLabel jLabelSurCategorie = new JLabel("Categorie principale :");
        JLabel jLabelCategorie = new JLabel("Categorie :");
        JLabel jLabelCodePostal = new JLabel("Code postal :");
        JLabel jLabelLocation = new JLabel("Ville :");
        JLabel jLabelPrix = new JLabel("Prix :");
        JLabel jLabelLienImage = new JLabel("Lien de votre photo :");
        labels = new JLabel[]{
                jLabelTitreAnnonce, jLabelDescription, jLabelOffre, jLabelSurCategorie,
                jLabelCategorie, jLabelCodePostal, jLabelLocation, jLabelPrix, jLabelLienImage
        };

        JTextField jTextFieldTitreAnnonce = new JTextField();
        JTextArea jTextAreaDescription = new JTextArea();
        JScrollPane jScrollPaneDescription = new JScrollPane(jTextAreaDescription, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JCheckBox jCheckBoxOffre = new JCheckBox();
        JComboBox<SurCategorieEntity> jComboBoxSurCategorie = new JComboBox<>();
        JComboBox<CategorieEntity> jComboBoxCategorie = new JComboBox<>();
        JTextField jTextFieldCodePostal = new JTextField();
        JComboBox<LocalisationEntity> jComboBoxLocalisation = new JComboBox<>();
        JTextField jTextFieldPrix = new JTextField();
        JTextField jTextFieldLienImage = new JTextField();
        components = new JComponent[]{
                jTextFieldTitreAnnonce, jScrollPaneDescription, jCheckBoxOffre, jComboBoxSurCategorie,
                jComboBoxCategorie, jTextFieldCodePostal, jComboBoxLocalisation, jTextFieldPrix, jTextFieldLienImage
        };

        JpaSurCategorieDao jpaSurCategorieDao = new JpaSurCategorieDao();
        Collection<SurCategorieEntity> surCategories = jpaSurCategorieDao.findAll();
        for (SurCategorieEntity currentSurCategorie : surCategories) {
            jComboBoxSurCategorie.addItem(currentSurCategorie);
        }
        jComboBoxSurCategorie.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCategorieList(jComboBoxSurCategorie, jComboBoxCategorie);
            }
        });

        jCheckBoxOffre.setSelected(true);

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


        jButtonAjouter = new JButton("Ajouter la nouvelle annonce");
        jButtonAjouter.addActionListener(actionEvent -> {
            if (checkComponents()) {
                AnnonceEntity annonceEntity = new AnnonceEntity(
                        jTextFieldTitreAnnonce.getText(), jTextAreaDescription.getText(), jCheckBoxOffre.isSelected(),
                        (CategorieEntity) jComboBoxCategorie.getSelectedItem(), utilisateurEntity,
                        (LocalisationEntity) localisationEntity, new Timestamp(System.currentTimeMillis()),
                        Integer.parseInt(jTextFieldPrix.getText()), jTextFieldLienImage.getText()
                );

                JpaAnnonceDao jpaAnnonceDao = new JpaAnnonceDao();


                try {
                    if (jpaAnnonceDao.create(annonceEntity)) {
                        System.out.println("AnnonceAjout.java -> jButtonAjouter(ActionListener) : Ajout réussi");
                        annoncesUtilisateur.setAnnonceEntities();
                        this.dispose();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Echec de l'ajout d'une annonce");
                        System.out.println("AnnonceAjout.java -> jButtonAjouter(ActionListener) : Ajout échoué");
                    }
                } catch (Exception exception) {
                    System.out.println("AnnonceAjout.java -> jButtonAjouter(ActionListener) : " + exception.getMessage());
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Echec de l'ajout d'une annonce - Informations manquantes");
                System.out.println("AnnonceAjout.java -> jButtonAjouter(ActionListener) : Informations manquantes");
            }
        });
    }

    private void showComponent() {
        jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        designJPanelUtils.addComponent(jPanel, jLabelTitle, 0, 0, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, null, null, null, null, null, null);

        for (int i = 0; i < labels.length; i++) {
            designJPanelUtils.addComponent(jPanel, labels[i], 0, (i * 2) + 1, 1, 1, 1d, 0.01, GridBagConstraints.NORTHWEST, GridBagConstraints.BASELINE, 0, 32, 8, 0, null, null);
            designJPanelUtils.addComponent(jPanel, components[i], 0, i == 2 ? (i * 2) + 1 : (i * 2) + 2, 1, 1, 1d, i == 1 ? 0.05 : 0.01, GridBagConstraints.CENTER, i == 2 ? GridBagConstraints.BASELINE : GridBagConstraints.HORIZONTAL, 0, 48, 16, 48, null, null);
        }

        designJPanelUtils.addComponent(jPanel, jButtonAjouter, 0, (labels.length * 2) + 1, 1, 1, 1d, 1d, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, null, null, null, null, null, null);

    }

    private boolean checkComponents() {
        for (JComponent currentComponent : components) {
            if (currentComponent instanceof JTextField) {
                if (((JTextField) currentComponent).getText().equals("")) {
                    return false;
                }
            } else if (currentComponent instanceof JTextArea) {
                if (((JTextArea) currentComponent).getText().equals("")) {
                    return false;
                }
            } else if (currentComponent instanceof JComboBox) {
                if (((JComboBox) currentComponent).getSelectedItem() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateCategorieList(JComboBox<SurCategorieEntity> jComboBoxSurCategorie, JComboBox<CategorieEntity> jComboBoxCategorie) {
        SurCategorieEntity surCategorieEntity = jComboBoxSurCategorie.getItemAt(jComboBoxSurCategorie.getSelectedIndex());
        JpaCategorieDao jpaCategorieDao = new JpaCategorieDao();
        Collection<CategorieEntity> categories = jpaCategorieDao.find(surCategorieEntity);
        jComboBoxCategorie.removeAllItems();
        for (CategorieEntity c : categories) {
            jComboBoxCategorie.addItem(c);
        }
    }


}
