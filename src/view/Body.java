package view;

import dao.JpaAnnonceDao;
import dao.JpaCategorieDao;
import dao.JpaSurCategorieDao;
import metier.AnnonceEntity;
import metier.CategorieEntity;
import metier.SurCategorieEntity;
import utils.DesignJPanelUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Body extends JPanel {

    public static Dimension dimension = new Dimension(200, 25);
    public static DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private Collection<AnnonceEntity> annonceEntities = null;

    public Body() {
        this.setLayout(new GridBagLayout());
        this.setName("Body");
        setComponent();
    }

    private void setComponent() {
        JLabel jLabelRecherche = new JLabel("Recherche :");
        JTextField jTextFieldRecherche = new JTextField();
        jTextFieldRecherche.setPreferredSize(dimension);

        JComboBox<SurCategorieEntity> jComboBoxSurCategorie = new JComboBox<>();
        jComboBoxSurCategorie.setPreferredSize(dimension);
        JpaSurCategorieDao jpaSurCategorieDao = new JpaSurCategorieDao();
        Collection<SurCategorieEntity> surCategories = jpaSurCategorieDao.findAll();
        for (SurCategorieEntity currentSurCategorie : surCategories) {
            jComboBoxSurCategorie.addItem(currentSurCategorie);
        }

        JComboBox<CategorieEntity> jComboBoxCategorie = new JComboBox<>();
        jComboBoxCategorie.setPreferredSize(dimension);
        updateCategorieList(jComboBoxSurCategorie, jComboBoxCategorie);

        jComboBoxSurCategorie.addActionListener(actionEvent -> updateCategorieList(jComboBoxSurCategorie, jComboBoxCategorie));

        JLabel jLabelPrix = new JLabel("Prix max :");
        JTextField jTextFieldPrix = new JTextField();
        jTextFieldPrix.setPreferredSize(dimension);
        jTextFieldPrix.setToolTipText("Prix max...");

        JLabel jLabelOffre = new JLabel("Offre :");
        JCheckBox jCheckBoxOffre = new JCheckBox();
        jCheckBoxOffre.setSelected(true);


        JButton jButtonRecherche = new JButton("Rechercher");
        jButtonRecherche.setPreferredSize(dimension);
        jButtonRecherche.addActionListener(actionEvent -> {
            JpaAnnonceDao jpaAnnonceDao = new JpaAnnonceDao();
            if (jTextFieldPrix.getText().equals("")) {
                annonceEntities = jpaAnnonceDao.findAnnonce(
                        jTextFieldRecherche.getText(),
                        (CategorieEntity) jComboBoxCategorie.getSelectedItem(),
                        jCheckBoxOffre.isSelected()
                );
            } else {
                annonceEntities = jpaAnnonceDao.findAnnonce(
                        jTextFieldRecherche.getText(),
                        (CategorieEntity) jComboBoxCategorie.getSelectedItem(),
                        Integer.parseInt(jTextFieldPrix.getText()),
                        jCheckBoxOffre.isSelected()
                );
            }

            updateAnnonceList();
        });

        designJPanelUtils.addComponent(this, jLabelRecherche, 0, 0, 1, 1, 10d, 0.01, GridBagConstraints.EAST, GridBagConstraints.BASELINE, 24, 0, 0, 2, null, null);
        designJPanelUtils.addComponent(this, jTextFieldRecherche, 1, 0, 1, 1, 2d, 0.01, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 24, 2, 0, 24, null, null);
        designJPanelUtils.addComponent(this, jComboBoxSurCategorie, 2, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 24, 24, 0, 4, null, null);
        designJPanelUtils.addComponent(this, jComboBoxCategorie, 3, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 24, 4, 0, 24, null, null);
        designJPanelUtils.addComponent(this, jLabelPrix, 4, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 24, 24, 0, 2, null, null);
        designJPanelUtils.addComponent(this, jTextFieldPrix, 5, 0, 1, 1, 2d, 0.01, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 24, 2, 0, 24, null, null);
        designJPanelUtils.addComponent(this, jLabelOffre, 6, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 24, 24, 0, 2, null, null);
        designJPanelUtils.addComponent(this, jCheckBoxOffre, 7, 0, 1, 1, 0.01, 0.01, GridBagConstraints.CENTER, GridBagConstraints.BASELINE, 24, 2, 0, 32, null, null);
        designJPanelUtils.addComponent(this, jButtonRecherche, 8, 0, 1, 1, 10d, 0.01, GridBagConstraints.WEST, GridBagConstraints.BASELINE, 24, 32, 0, 0, null, null);

        updateAnnonceList();


    }

    private void updateAnnonceList() {
        if (this.getComponentCount() > 9) this.remove(9);
        if (annonceEntities != null) {
            designJPanelUtils.addComponent(this, new AnnonceList(new ArrayList<>(annonceEntities), false), 0, 1, 9, 1, null, 1d, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 32, 16, 0, 16, null, null);
        } else {
            designJPanelUtils.addComponent(this, new JPanel(), 0, 1, 9, 1, null, 1d, GridBagConstraints.CENTER, GridBagConstraints.BOTH, null, null, null, null, null, null);
        }
        this.updateUI();
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
