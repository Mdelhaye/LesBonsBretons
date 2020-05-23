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
        setComponent();
    }

    private void setComponent() {
        JTextField jTextField = new JTextField();
        jTextField.setPreferredSize(dimension);

        JComboBox<SurCategorieEntity> jComboBoxSurCategorie = new JComboBox<>();
        jComboBoxSurCategorie.setPreferredSize(dimension);
        JpaSurCategorieDao jpaSurCategorieDao = new JpaSurCategorieDao();
        Collection<SurCategorieEntity> surCategories = jpaSurCategorieDao.findAll();
        for (SurCategorieEntity currentSurCategorie : surCategories) {
            jComboBoxSurCategorie.addItem(currentSurCategorie);
        }

        JComboBox<CategorieEntity> jComboBoxCategorie = new JComboBox<>();
        jComboBoxCategorie.setPreferredSize(dimension);
        JpaCategorieDao jpaCategorieDao = new JpaCategorieDao();
        Collection<CategorieEntity> categories = jpaCategorieDao.findAll();
        for (CategorieEntity currentCategorie : categories) {
            jComboBoxCategorie.addItem(currentCategorie);
        }

        JButton jButtonRecherche = new JButton("Rechercher");
        jButtonRecherche.setPreferredSize(dimension);
        jButtonRecherche.addActionListener(actionEvent -> {
            JpaAnnonceDao jpaAnnonceDao = new JpaAnnonceDao();
            annonceEntities = jpaAnnonceDao.findAnnonce(
                    jTextField.getText(),
                    jComboBoxSurCategorie.getItemAt(jComboBoxSurCategorie.getSelectedIndex())
            );
            updateAnnonceList();
        });

        designJPanelUtils.addComponent(this, jTextField, 0, 0, 1, null, null, 0.01, GridBagConstraints.NORTH, GridBagConstraints.BASELINE, 25, 0, 0, 10, null, null);
        designJPanelUtils.addComponent(this, jComboBoxSurCategorie, 1, 0, 1, null, null, 0.01, GridBagConstraints.NORTH, GridBagConstraints.BASELINE, 25, 0, 0, 10, null, null);
        designJPanelUtils.addComponent(this, jComboBoxCategorie, 2, 0, 1, null, null, 0.01, GridBagConstraints.NORTH, GridBagConstraints.BASELINE, 25, 0, 0, 10, null, null);
        designJPanelUtils.addComponent(this, jButtonRecherche, 3, 0, 1, null, null, 0.01, GridBagConstraints.NORTH, GridBagConstraints.BASELINE, 25, 0, 0, 10, null, null);

        updateAnnonceList();
    }

    private void updateAnnonceList() {
        if (this.getComponentCount() > 4) this.remove(4);
        if (annonceEntities != null) {
            designJPanelUtils.addComponent(this, new AnnonceList(new ArrayList<>(annonceEntities)), 0, 1, 4, null, null, 1d, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 0, 0, 25, 0, null, null);
        }
        this.updateUI();
    }
}