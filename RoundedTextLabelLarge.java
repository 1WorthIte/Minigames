package Assignment2;

import javax.swing.*;
import java.awt.*;

public class RoundedTextLabelLarge extends JLabel {
    private int arc = 20;
    private Color bgColor = new Color(60, 60, 60, 0);
    private Color fgColor = Color.WHITE;
    private Color borderColor = Color.WHITE;

    public RoundedTextLabelLarge(String text, int fontSize) {
        super(text, SwingConstants.CENTER);
        setOpaque(false);
        setBorder(getBorder());
        setForeground(fgColor);
        setFont(new Font("Arial", Font.BOLD, fontSize));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        super.paintComponent(g2);
        g2.dispose();
    }

        @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        g2.setColor(borderColor);
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, arc, arc);
        g2.dispose();
    }
}