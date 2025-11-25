
package Assignment2;

import javax.swing.*;
import java.awt.*;

public class TicTacToeGame2 extends JFrame {
private JButton[][] buttons = new JButton[3][3];
private boolean xTurn = true;
private int moves = 0;

public TicTacToeGame2() {
      setTitle("Tic Tac Toe");
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
            btn.addActionListener(e -> handleMove(row, col));
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

private void handleMove(int row, int col) {
      if (!buttons[row][col].getText().equals("")) return;
      buttons[row][col].setText(xTurn ? "X" : "O");
      moves++;
      if (checkWin()) {
            JOptionPane.showMessageDialog(this, (xTurn ? "X" : "O") + " wins!");
            reset();
      } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            reset();
      } else {
            xTurn = !xTurn;
      }
}

private boolean checkWin() {
      String s = xTurn ? "X" : "O";
      for (int i = 0; i < 3; i++)
            if (buttons[i][0].getText().equals(s) && buttons[i][1].getText().equals(s) && buttons[i][2].getText().equals(s))
            return true;
      for (int j = 0; j < 3; j++)
            if (buttons[0][j].getText().equals(s) && buttons[1][j].getText().equals(s) && buttons[2][j].getText().equals(s))
            return true;
      if (buttons[0][0].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][2].getText().equals(s))
            return true;
      if (buttons[0][2].getText().equals(s) && buttons[1][1].getText().equals(s) && buttons[2][0].getText().equals(s))
            return true;
      return false;
}

private void reset() {
      for (JButton[] row : buttons)
            for (JButton btn : row)
            btn.setText("");
      xTurn = true;
      moves = 0;
}
}