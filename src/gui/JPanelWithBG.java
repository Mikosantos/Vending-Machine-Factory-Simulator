package gui;

import javax.swing.*;
import java.awt.*;
public class JPanelWithBG extends JPanel{
    private Image image;

    public JPanelWithBG(ImageIcon background, int imageWidth, int imageHeight) {
        this.image = background.getImage().getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }


}
