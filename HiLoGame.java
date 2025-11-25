package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HiLoGame extends JFrame {
    private int target, maxTries, tries, min, max;
    private JLabel promptLabel = new JLabel("", SwingConstants.CENTER);
    private JTextField guessField = new JTextField();
    private JButton guessBtn = new JButton("Guess");
    private JLabel infoLabel = new JLabel("", SwingConstants.CENTER);
    private JComboBox<String> difficultyBox;

    private static final String[] DIFFICULTIES = {
        "Easy (1-10, 15 tries)", "Normal (1-50, 12 tries)", "Hard (1-100, 10 tries)", "Expert (1-500, 7 tries)", "Insane (1-1000, 5 tries)"
    };

    public HiLoGame() {
        setTitle("Hi-Lo Guessing Game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1024, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel bgPanel = new JPanel(null) {
            Image bg = new ImageIcon("menu2.png").getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        JLabel title = new JLabel("Hi-Lo Guessing Game", SwingConstants.CENTER);
        title.setFont(new Font("Segoe Script", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBounds(210, 40, 600, 50);

        difficultyBox = new JComboBox<>(DIFFICULTIES);
        difficultyBox.setBounds(220, 150, 200, 30);
        JButton startBtn = new JButton("Start Game");
        startBtn.setBounds(660, 150, 120, 30);

        promptLabel.setFont(new Font("Arial", Font.BOLD, 20));
        promptLabel.setForeground(Color.WHITE);
        promptLabel.setBounds(180, 500, 600, 30);

        guessField.setBounds(350, 320, 200, 30);
        guessBtn.setBounds(560, 320, 80, 30);

        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBounds(205, 235, 600, 30);

        JButton backBtn = makeMenuButton("Back", 430, 590, 150, 50);

        bgPanel.add(title);
        bgPanel.add(difficultyBox);
        bgPanel.add(startBtn);
        bgPanel.add(promptLabel);
        bgPanel.add(guessField);
        bgPanel.add(guessBtn);
        bgPanel.add(infoLabel);
        bgPanel.add(backBtn);

        guessField.setEnabled(false);
        guessBtn.setEnabled(false);

        startBtn.addActionListener(e -> startGame());
        guessBtn.addActionListener(e -> handleGuess());
        guessField.addActionListener(e -> handleGuess());
        backBtn.addActionListener(e -> {
            new GameSelect().setVisible(true);
            dispose();
        });

        setContentPane(bgPanel);
    }

    private void startGame() {
        int idx = difficultyBox.getSelectedIndex();
        switch (idx) {
            case 0: min = 1; max = 20; maxTries = 15; break;
            case 1: min = 1; max = 50; maxTries = 12; break;
            case 2: min = 1; max = 100; maxTries = 9; break;
            case 3: min = 1; max = 500; maxTries = 7; break;
            case 4: min = 1; max = 1000; maxTries = 5; break;
        }
        target = new Random().nextInt(max - min + 1) + min;
        tries = 0;
        promptLabel.setText("Guess a number between " + min + " and " + max + ". You have " + maxTries + " tries.");
        infoLabel.setText("");
        guessField.setEnabled(true);
        guessBtn.setEnabled(true);
        guessField.setText("");
        guessField.requestFocus();
    }

    private void handleGuess() {
        String guessStr = guessField.getText().trim();
        int guess;
        try {
            guess = Integer.parseInt(guessStr);
        } catch (NumberFormatException ex) {
            infoLabel.setText("Please enter a valid number!");
            return;
        }
        tries++;
        if (guess < min || guess > max) {
            infoLabel.setText("Your guess must be between " + min + " and " + max + ".");
            return;
        }
        if (guess == target) {
            infoLabel.setText("Correct! You guessed it in " + tries + " tries.");
            guessField.setEnabled(false);
            guessBtn.setEnabled(false);
        } else if (tries >= maxTries) {
            infoLabel.setText("Out of tries! The number was " + target + ".");
            guessField.setEnabled(false);
            guessBtn.setEnabled(false);
        } else if (guess < target) {
            infoLabel.setText("Too low! Tries left: " + (maxTries - tries));
        } else {
            infoLabel.setText("Too high! Tries left: " + (maxTries - tries));
        }
        guessField.setText("");
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