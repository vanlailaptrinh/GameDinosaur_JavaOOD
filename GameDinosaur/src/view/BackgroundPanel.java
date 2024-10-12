package view;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BackgroundPanel extends JPanel {
	private Image backgroundImage;
    private int xOffset = 0;

    public BackgroundPanel(String fileName) {
        try {
            backgroundImage = ImageIO.read(getClass().getResource(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int width = getWidth();
            int height = getHeight();
            g.drawImage(backgroundImage, xOffset, 0, width, height, this);
            if (xOffset < 0) {
                g.drawImage(backgroundImage, xOffset + width, 0, width, height, this);
            }
        }
    }

    public void updateBackground() {
        xOffset -= 1; 
        if (xOffset <= -getWidth()) {
            xOffset = 0;
        }
        repaint();
    }
}
