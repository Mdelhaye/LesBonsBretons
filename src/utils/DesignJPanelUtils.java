package utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class DesignJPanelUtils {

    public Color getHSBFromRGB(int[] color) {
        float[] hsb = new float[3];
        Color.RGBtoHSB(color[0], color[1], color[2], hsb);
        // brightness
        hsb[2] *= 1f;
        // saturation
        hsb[1] *= 1f;
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    public void addComponent(JPanel jPanel, JComponent jComponent,
                             int gridx, int gridy, Integer gridwith, Integer gridheight,
                             Double weightx, Double weighty,
                             Integer anchor, Integer fill,
                             Integer insets_top, Integer insets_left, Integer insets_bottom, Integer inserts_right,
                             Integer ipadx, Integer ipady
    ) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;

        if (gridwith != null) gridBagConstraints.gridwidth = gridwith;
        if (gridheight != null) gridBagConstraints.gridheight = gridheight;
        if (weightx != null) gridBagConstraints.weightx = weightx;
        if (weighty != null) gridBagConstraints.weighty = weighty;
        if (anchor != null) gridBagConstraints.anchor = anchor;
        if (fill != null) gridBagConstraints.fill = fill;

        if (insets_top != null && insets_left != null && insets_bottom != null && inserts_right != null) {
            gridBagConstraints.insets = new Insets(insets_top, insets_left, insets_bottom, inserts_right);
        }

        if (ipadx != null) gridBagConstraints.ipadx = ipadx;
        if (ipady != null) gridBagConstraints.ipady = ipady;

        jPanel.add(jComponent, gridBagConstraints);
    }

    public Image getImage(String url) {

        try {
            Image icon = ImageIO.read(new URL(url));
            ImageIcon imageIcon = new ImageIcon(icon);

            int height = imageIcon.getIconHeight();
            int width = imageIcon.getIconWidth();
            if (height > width) {
                while (height > 190) {
                    height = height / 2;
                    width = width / 2;
                }
            } else {
                while (width > 190) {
                    height = height / 2;
                    width = width / 2;
                }
            }

            return icon.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String convertToMultiline(String oldText) {
        String newText = oldText.replaceAll("\n", "<br>");
        newText = newText.replace("\r", "<span style=\"margin-left: 5em;\"></span>");
        return "<html>" + newText + "</html>";
    }

}
