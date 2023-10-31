import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private JFrame frame;
    private JButton[][] buttons;
    private char currentPlayer;

    public Main() {
        frame = new JFrame("Tic Tac Toe");
        buttons = new JButton[3][3];
        currentPlayer = 'X';

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].addActionListener(new ButtonClickListener(row, col));
                frame.add(buttons[row][col]);
            }
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (buttons[row][col].getText().equals("")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                buttons[row][col].setEnabled(false);
                if (checkWin(row, col)) {
                    JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " wins!");
                    resetGame();
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(frame, "It's a draw!");
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWin(int row, int col) {
        String symbol = String.valueOf(currentPlayer);

        // Check row
        if (buttons[row][(col + 1) % 3].getText().equals(symbol) && buttons[row][(col + 2) % 3].getText().equals(symbol)) {
            return true;
        }

        // Check column
        if (buttons[(row + 1) % 3][col].getText().equals(symbol) && buttons[(row + 2) % 3][col].getText().equals(symbol)) {
            return true;
        }

        // Check diagonals
        if (row == col && buttons[(row + 1) % 3][(col + 1) % 3].getText().equals(symbol)
                && buttons[(row + 2) % 3][(col + 2) % 3].getText().equals(symbol)) {
            return true;
        }
        if (row + col == 2 && buttons[(row + 1) % 3][(col + 2) % 3].getText().equals(symbol)
                && buttons[(row + 2) % 3][(col + 1) % 3].getText().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
