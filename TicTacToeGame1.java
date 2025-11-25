package Assignment2;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGame1 extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerTurn = true;
    private int moves = 0;

    public TicTacToeGame1() {
        setTitle("Tic Tac Toe - Singleplayer");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(640, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        Font font = new Font("Arial", Font.BOLD, 100);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(font);
                btn.setFocusPainted(false);
                buttons[i][j] = btn;
                int row = i, col = j;
                btn.addActionListener(e -> {
                    if (playerTurn) {
                        handlePlayerMove(row, col);
                    }
                });
                panel.add(btn);
            }
        }

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            new GameSelect().setVisible(true);
            dispose();
        });

        JPanel bottom = new JPanel();
        bottom.add(backBtn);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bottom, BorderLayout.SOUTH);
    }

    private void handlePlayerMove(int row, int col) {
        if (!buttons[row][col].getText().equals("") || !playerTurn) return;
        buttons[row][col].setText("X");
        moves++;
        
        if (checkWin("X")) {
            JOptionPane.showMessageDialog(this, "You win!");
            reset();
            return;
        } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            reset();
            return;
        }
        
        playerTurn = false;
        Timer timer = new Timer(500, e -> {
            computerMove();
            ((Timer)e.getSource()).stop();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void computerMove() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    buttons[i][j].setText("O");
                    moves++;
                    
                    if (checkWin("O")) {
                        JOptionPane.showMessageDialog(this, "Computer wins!");
                        reset();
                        return;
                    } else if (moves == 9) {
                        JOptionPane.showMessageDialog(this, "It's a draw!");
                        reset();
                        return;
                    }
                    
                    playerTurn = true;
                    return;
                }
            }
        }
    }

    private boolean checkWin(String player) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(player) && 
                buttons[i][1].getText().equals(player) && 
                buttons[i][2].getText().equals(player)) {
                return true;
            }
        }
        
        for (int j = 0; j < 3; j++) {
            if (buttons[0][j].getText().equals(player) && 
                buttons[1][j].getText().equals(player) && 
                buttons[2][j].getText().equals(player)) {
                return true;
            }
        }
        
        if (buttons[0][0].getText().equals(player) && 
            buttons[1][1].getText().equals(player) && 
            buttons[2][2].getText().equals(player)) {
            return true;
        }
        
        if (buttons[0][2].getText().equals(player) && 
            buttons[1][1].getText().equals(player) && 
            buttons[2][0].getText().equals(player)) {
            return true;
        }
        
        return false;
    }

    private void reset() {
        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                btn.setText("");
            }
        }
        playerTurn = true;
        moves = 0;
    }
}