import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The <code>ImagePanel</code> class is used in printing images to 
 * 
 *
 * @author 'hardwired' @ http://www.java-forums.org
 *   'http://www.java-forums.org/awt-swing/6574-add-image-jframe.html'
 * @version 1.0
 */
public class ImagePanel extends JPanel {
    BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw image centered.
        int x = (getWidth() - image.getWidth())/2;
        int y = (getHeight() - image.getHeight())/2;
        g.drawImage(image, x, y, this);
    }

}