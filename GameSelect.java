package Assignment2;

import javax.swing.*;
import java.awt.*;

public class GameSelect extends JFrame {
public GameSelect() {
      setTitle("Select a Game");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setSize(1024, 700);
      setResizable(false);
      setLocationRelativeTo(null);

      JPanel panel = new JPanel() {
      Image bg = new ImageIcon("menu2.png").getImage();
      @Override
      protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
      }
      };
      panel.setLayout(null);

      JLabel label = new JLabel("Choose Your Game");
      label.setFont(new Font("Segoe Script", Font.BOLD, 40));
      label.setForeground(Color.WHITE);
      label.setBounds(300, 40, 500, 60);
      panel.add(label);

      int w = 277, h = 130, x = 212, gap = 20;
      int y = 140;

      JButton tttBtn1 = new ScaledImageButton("ttt.png", "Tic Tac Toe Singleplayer");
      tttBtn1.setBounds(x, y, w, h);

      JButton tttBtn2 = new ScaledImageButton("ttt.png", "Tic Tac Toe Multiplayer");
      tttBtn2.setBounds(x, y + (h+gap), w, h);

      JButton rpsBtn1 = new ScaledImageButton("rps.png", "Rock Paper Scissors Singleplayer");
      rpsBtn1.setBounds(x + (w + gap), y, w, h);

      JButton rpsBtn2 = new ScaledImageButton("rps.png", "Rock Paper Scissors Singleplayer");
      rpsBtn2.setBounds(x + (w + gap), y + (h+gap), w, h);

      JButton hiloBtn = new ScaledImageButton("hilo.png", "High-Low Guessing Game");
      hiloBtn.setBounds(x, y + 300, w + 295, h - 33);

      JButton backButton = makeMenuButton("Back", 430, 590, 150, 50);
      
      panel.add(tttBtn1);
      panel.add(tttBtn2);
      panel.add(rpsBtn1);
      panel.add(rpsBtn2);
      panel.add(hiloBtn);
      panel.add(backButton);

      RoundedTextLabelSmall tttLabel1 = new RoundedTextLabelSmall("1 player",12);
      tttLabel1.setBounds(x + 172, y + 100, 100, 25);

      panel.add(tttLabel1);

      RoundedTextLabelSmall tttLabel2 = new RoundedTextLabelSmall("2 player",12);
      tttLabel2.setBounds(x + 172, y + 100 + h + gap, 100, 25);

      panel.add(tttLabel2);

      RoundedTextLabelSmall rpsLabel1 = new RoundedTextLabelSmall("1 player",12);
      rpsLabel1.setBounds(x + 172 + w + gap, y + 100, 100, 25);

      panel.add(rpsLabel1);

      RoundedTextLabelSmall rpsLabel2 = new RoundedTextLabelSmall("2 player",12);
      rpsLabel2.setBounds(x + 172 + w + gap, y + 100 + h + gap, 100, 25);

      panel.add(rpsLabel2);

      RoundedTextLabelLarge tttLabel = new RoundedTextLabelLarge("Tic Tac Toe", 22);
      tttLabel.setBounds(10, 255, 170, 50);

      panel.add(tttLabel);
      
      RoundedTextLabelLarge rpsLabel = new RoundedTextLabelLarge("Rock Pap. Sciss.", 22);
      rpsLabel.setBounds(820, 255, 192, 50);

      panel.add(rpsLabel);

      RoundedTextLabelLarge hiloLabel = new RoundedTextLabelLarge("High-Low Guessing Game", 22);
      hiloLabel.setBounds(350, 463, 300, 50);

      panel.add(hiloLabel);

      tttBtn1.addActionListener(e -> {
      new TicTacToeGame1().setVisible(true);
      dispose();
      });

      tttBtn2.addActionListener(e -> {
      new TicTacToeGame2().setVisible(true);
      dispose();
      });

      rpsBtn1.addActionListener(e -> {
      new RockPaperScissorsGame1().setVisible(true);
      dispose();
      });

      rpsBtn2.addActionListener(e -> {
      new RockPaperScissorsGame2().setVisible(true);
      dispose();
      });

      hiloBtn.addActionListener(e -> {
      new HiLoGame().setVisible(true);
      dispose();
      });

      backButton.addActionListener(e -> {
            new Menu().setVisible(true);
            dispose();
      });

      panel.setComponentZOrder(tttLabel1, 0);
      panel.setComponentZOrder(tttLabel2, 0);
      panel.setComponentZOrder(rpsLabel1, 0);
      panel.setComponentZOrder(rpsLabel2, 0);
      panel.setComponentZOrder(hiloLabel, 0);

      setContentPane(panel);
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

}