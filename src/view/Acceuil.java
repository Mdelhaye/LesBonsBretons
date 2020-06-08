package view;

import metier.UtilisateurEntity;
import utils.DesignJPanelUtils;

import javax.swing.*;
import java.awt.*;

public class Acceuil extends JFrame {

    public static int[] orange = new int[]{255, 87, 51};
    public DesignJPanelUtils designJPanelUtils = new DesignJPanelUtils();

    private UtilisateurEntity utilisateurEntity = null;
    private JPanel jPanelParent;
    private JPanel jPanelHeader;

    public Acceuil() {
        jPanelParent = new JPanel();

        setJPanelHeader();

        JPanel jpanelBody = new Body();
        jpanelBody.setPreferredSize(new Dimension(getWidth(), 900));

        JPanel jPanelFooter = new Footer();
        jPanelFooter.setPreferredSize(new Dimension(getWidth(), 25));
        jPanelFooter.setBackground(designJPanelUtils.getHSBFromRGB(orange));

        jPanelParent.setLayout(new BorderLayout());
        jPanelParent.add(jPanelHeader, BorderLayout.NORTH);
        jPanelParent.add(jpanelBody, BorderLayout.CENTER);
        jPanelParent.add(jPanelFooter, BorderLayout.SOUTH);

        this.setContentPane(jPanelParent);
        this.setSize(1500, 1000);
        this.setTitle("Les Bons Bretons");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void setJPanelHeader() {
        if (jPanelHeader != null) jPanelParent.remove(jPanelHeader);
        jPanelHeader = new Header(this, utilisateurEntity);
        jPanelHeader.setPreferredSize(new Dimension(getWidth(), 75));
        jPanelHeader.setBackground(designJPanelUtils.getHSBFromRGB(orange));
        jPanelParent.add(jPanelHeader, BorderLayout.NORTH);
        jPanelParent.updateUI();
    }

    public void setUser(UtilisateurEntity utilisateurEntity) {
        this.utilisateurEntity = utilisateurEntity;
        System.out.println(this.utilisateurEntity);
        setJPanelHeader();
    }

}