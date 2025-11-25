package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScaledImageButton extends JButton {
    private Image image;
    private int arcWidth = 40;
    private boolean hover = false;

    public ScaledImageButton(String imagePath, String toolTip) {
        super();
        this.image = new ImageIcon(imagePath).getImage();
        setToolTipText(toolTip);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (hover) {
            g2.setColor(new Color(245, 165, 180, 180));
        } else {
            g2.setColor(new Color(60, 60, 60, 180));
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcWidth);

        Shape clip = g2.getClip();
        g2.setClip(new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcWidth));
        if (image != null) {
            g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
        g2.setClip(clip);

        super.paintComponent(g2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcWidth, arcWidth);
        g2.dispose();
    }
}
