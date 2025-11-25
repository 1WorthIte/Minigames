package Assignment2;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    public Menu() {
        setTitle("The Bawa Games");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel bgPanel = new JPanel() {
            Image bg = new ImageIcon("menu2.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);

        JLabel title = new JLabel("The Bawa Games");
        title.setFont(new Font("Segoe Script", Font.BOLD, 48));
        title.setForeground(new Color(255, 255, 255));
        title.setBounds(280, 150, 450, 60);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        bgPanel.add(title);

        int btnWidth = 575, btnHeight = 140, btnX = 212, btnY = 240, btnGap = 17;
        JButton playBtn = makeMenuButton("Play", btnX, btnY, btnWidth, btnHeight);
        JButton quitBtn = makeMenuButton("Quit", btnX, btnY + btnHeight + btnGap, btnWidth, btnHeight);

        bgPanel.add(playBtn);
        bgPanel.add(quitBtn);

        playBtn.addActionListener(e -> {
            new GameSelect().setVisible(true);
            dispose();
        });

        quitBtn.addActionListener(e -> System.exit(0));

        setContentPane(bgPanel);
    }

    private JButton makeMenuButton(String text, int x, int y, int w, int h) {
        RoundedButton btn = new RoundedButton(text);
        btn.setBounds(x, y, w, h);
        btn.setFont(new Font("Segoe Script", Font.BOLD, 32));
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBackground(new Color(60, 60, 60, 200));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(new Color(245, 165, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE);
            }
        });
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu().setVisible(true));
    }
}
