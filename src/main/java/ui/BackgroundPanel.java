package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/** Paints a scalable, cached background image behind its children. */
public class BackgroundPanel extends JPanel {
    private BufferedImage original;
    private BufferedImage scaled;       // cache
    private int cacheW = -1, cacheH = -1;

    public BackgroundPanel(String resourcePath) {
        setLayout(new CardLayout()); // so we can add overlay panels
        setOpaque(true);
        try {
            original = ImageIO.read(getClass().getResource(resourcePath));
        } catch (Exception e) {
            System.err.println("Could not load background: " + resourcePath + " (" + e.getMessage() + ")");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (original == null) return;

        int sw = getWidth(), sh = getHeight();

        if (scaled == null || cacheW != sw || cacheH != sh) {
            double sx = sw / (double) original.getWidth();
            double sy = sh / (double) original.getHeight();
            double s = Math.max(sx, sy); // cover
            int w = (int) Math.ceil(original.getWidth() * s);
            int h = (int) Math.ceil(original.getHeight() * s);

            BufferedImage tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(original, 0, 0, w, h, null);
            g2.dispose();

            scaled = tmp;
            cacheW = sw; cacheH = sh;
        }

        int x = (sw - scaled.getWidth()) / 2;
        int y = (sh - scaled.getHeight()) / 2;
        ((Graphics2D) g).drawImage(scaled, x, y, null);
    }
}
