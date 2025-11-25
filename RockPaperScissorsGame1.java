package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class RockPaperScissorsGame1 extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private JPanel welcomePanel = new JPanel(null) {
        Image bg = new ImageIcon("menu2.png").getImage();
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JButton startButton = makeMenuButton("Begin", 430, 440, 150, 50);
    private JLabel fingerPrompt = new JLabel("Place your finger on Q, W, or E...", SwingConstants.CENTER);

    private JPanel gamePanel = new JPanel(new BorderLayout()) {
        Image bg = new ImageIcon("menu2.png").getImage();
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
        }
    };
    private JLabel countdownLabel = new JLabel("Get Ready!", SwingConstants.CENTER);
    private JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
    private JButton playAgainButton = new JButton("Play Again");
    private JButton backButton = new JButton("Back");
    JButton backBtn = makeMenuButton("Back", 430, 590, 150, 50);

    private String[] choices = {"Rock", "Paper", "Scissors"};
    private String playerChoice = null, computerChoice = null;
    private boolean acceptingInput = false;
    private Timer countdownTimer, inputTimeoutTimer;
    private int countdownStep = 0;
    private Random rand = new Random();

    public RockPaperScissorsGame1() {
        setTitle("Rock Paper Scissors - Singleplayer");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        setupWelcomePanel();
        setupGamePanel();

        mainPanel.add(welcomePanel, "welcome");
        mainPanel.add(gamePanel, "game");
        setContentPane(mainPanel);

        showWelcome();
    }

    private void setupWelcomePanel() {
        welcomePanel.setPreferredSize(new Dimension(800, 600));

        JLabel title = new JLabel("Welcome to Rock Paper Scissors!", SwingConstants.CENTER);
        title.setFont(new Font("Segoe Script", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBounds(140, 40, 700, 50);

        JTextArea rules = new JTextArea(
            "Rules:\n" +
            "- Play against the computer by choosing Rock, Paper, or\nScissors using keys.\n" +
            "- Use Q (Rock), W (Paper), or E (Scissors).\n" +
            "- Press keys only after the countdown reaches 'Go!'.\n" +
            "- Pressing keys too early or too late counts as cheating\nand ends the game.\n" +
            "- The winner is decided by classic rules: Rock beats\nScissors, Scissors beats Paper, Paper beats Rock."
        );
        rules.setFont(new Font("Arial", Font.PLAIN, 22));
        rules.setForeground(Color.WHITE);
        rules.setBackground(new Color(0, 0, 0, 0));
        rules.setEditable(false);
        rules.setBounds(220, 150, 700, 300);

        fingerPrompt.setFont(new Font("Arial", Font.BOLD, 20));
        fingerPrompt.setForeground(Color.YELLOW);
        fingerPrompt.setBounds(50, 395, 700, 40);
        fingerPrompt.setVisible(false);

        welcomePanel.add(title);
        welcomePanel.add(rules);
        welcomePanel.add(backBtn);
        welcomePanel.add(startButton);
        welcomePanel.add(fingerPrompt);

        startButton.addActionListener(e -> {
            startButton.setEnabled(false);
            fingerPrompt.setVisible(true);
            Timer fingerTimer = new Timer(3000, ev -> {
                showGame();
            });
            fingerTimer.setRepeats(false);
            fingerTimer.start();
        });
        
        backBtn.addActionListener(e -> {
            new GameSelect().setVisible(true);
            dispose();
        });
    }

    private void setupGamePanel() {
        countdownLabel.setFont(new Font("Segoe Script", Font.BOLD, 60));
        countdownLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 32));
        statusLabel.setForeground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(countdownLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        bottomPanel.setOpaque(false);
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 24));
        playAgainButton.setVisible(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 24));
        bottomPanel.add(playAgainButton);
        bottomPanel.add(backButton);

        gamePanel.add(topPanel, BorderLayout.CENTER);
        gamePanel.add(statusLabel, BorderLayout.SOUTH);
        gamePanel.add(bottomPanel, BorderLayout.NORTH);

        playAgainButton.addActionListener(e -> showWelcome());
        backButton.addActionListener(e -> {
            new GameSelect().setVisible(true);
            dispose();
        });

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (!acceptingInput) {
                    if (countdownStep < 4) {
                        endGame("Cheating detected! Pressed before 'Go!'");
                    }
                    return;
                }
                handleKeyInput(e.getKeyCode());
            }
        });
    }

    private void showWelcome() {
        cardLayout.show(mainPanel, "welcome");
        startButton.setEnabled(true);
        fingerPrompt.setVisible(false);
        playAgainButton.setVisible(false);
        requestFocusInWindow();
    }

    private void showGame() {
        cardLayout.show(mainPanel, "game");
        playAgainButton.setVisible(false);
        countdownLabel.setText("Get Ready!");
        statusLabel.setText("Q = Rock, W = Paper, E = Scissors");
        startCountdown();
        requestFocusInWindow();
    }

    private void startCountdown() {
        countdownStep = 0;
        acceptingInput = false;
        playerChoice = null;
        computerChoice = null;
        countdownLabel.setText("Rock");
        countdownTimer = new Timer(1000, e -> {
            countdownStep++;
            switch (countdownStep) {
                case 1: countdownLabel.setText("Paper"); break;
                case 2: countdownLabel.setText("Scissors"); break;
                case 3:
                    countdownLabel.setText("Go!");
                    acceptingInput = true;
                    startInputTimeout();
                    break;
                case 4:
                    if (acceptingInput) {
                        endGame("Time's up!\nNo input received.");
                    }
                    countdownTimer.stop();
                    break;
            }
        });
        countdownTimer.setInitialDelay(1000);
        countdownTimer.start();
    }

    private void startInputTimeout() {
        inputTimeoutTimer = new Timer(2000, e -> {
            if (acceptingInput) {
                endGame("Time's up!\nNo input received.");
            }
        });
        inputTimeoutTimer.setRepeats(false);
        inputTimeoutTimer.start();
    }

    private void handleKeyInput(int keyCode) {
        if (!acceptingInput) return;
        if (playerChoice == null) {
            if (keyCode == KeyEvent.VK_Q) playerChoice = "Rock";
            else if (keyCode == KeyEvent.VK_W) playerChoice = "Paper";
            else if (keyCode == KeyEvent.VK_E) playerChoice = "Scissors";
        }
        if (playerChoice != null) {
            acceptingInput = false;
            inputTimeoutTimer.stop();
            computerChoice = choices[rand.nextInt(choices.length)];
            showResult();
        }
    }

    private void showResult() {
        String result = getResult(playerChoice, computerChoice);
        countdownLabel.setText("<html>Player: " + playerChoice + "<br>Computer: " + computerChoice + "<br>" + result + "</html>");
        statusLabel.setText("Press Back or Play Again");
        playAgainButton.setVisible(true);
    }

    private void endGame(String message) {
        acceptingInput = false;
        if (inputTimeoutTimer != null) inputTimeoutTimer.stop();
        countdownLabel.setText(message);
        statusLabel.setText("Press Back or Play Again");
        playAgainButton.setVisible(true);
    }

    private String getResult(String p1, String p2) {
        if (p1 == null || p2 == null) return "Invalid input!";
        if (p1.equals(p2)) return "It's a tie!";
        if ((p1.equals("Rock") && p2.equals("Scissors")) ||
            (p1.equals("Paper") && p2.equals("Rock")) ||
            (p1.equals("Scissors") && p2.equals("Paper")))
            return "You win!";
        else
            return "Computer wins!";
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