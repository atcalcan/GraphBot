package Graphs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Render extends JPanel {
    private BufferedImage image;

    public Render() {
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/graph.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        g2d.drawLine();
        g2d.drawImage(image, 100, 100, null);
    }

}
