import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectFourGUI {
    private JFrame frame;
    private JButton[] columnButtons;
    private JLabel[][] grid;
    private JLabel statusLabel;
    private Board board;
    private char currentPlayer;

    public ConnectFourGUI() {
        board = new Board();
        currentPlayer = 'R'; // R for Red, Y for Yellow
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Connect Four");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Column Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7));
        columnButtons = new JButton[7];
        for (int col = 0; col < 7; col++) {
            final int column = col;
            columnButtons[col] = new JButton("↓");
            columnButtons[col].addActionListener(e -> makeMove(column));
            buttonPanel.add(columnButtons[col]);
        }
        frame.add(buttonPanel, BorderLayout.NORTH);

        // Game Grid
        JPanel gridPanel = new JPanel(new GridLayout(6, 7));
        grid = new JLabel[6][7];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                grid[row][col] = new JLabel("", SwingConstants.CENTER);
                grid[row][col].setFont(new Font("Arial", Font.BOLD, 24));
                grid[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.WHITE);
                gridPanel.add(grid[row][col]);
            }
        }
        frame.add(gridPanel, BorderLayout.CENTER);

        // Status Label
        statusLabel = new JLabel("Player Red's Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(statusLabel, BorderLayout.SOUTH);

        frame.setSize(700, 600);
        frame.setVisible(true);
    }

    private void makeMove(int column) {
        int row = board.dropDisc(column, currentPlayer);
        if (row != -1) {
            grid[row][column].setBackground(currentPlayer == 'R' ? Color.RED : Color.YELLOW);
            if (board.checkWin(currentPlayer)) {
                statusLabel.setText("Player " + (currentPlayer == 'R' ? "Red" : "Yellow") + " Wins!");
                disableButtons();
            } else if (board.isFull()) {
                statusLabel.setText("It's a Draw!");
                disableButtons();
            } else {
                currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
                statusLabel.setText("Player " + (currentPlayer == 'R' ? "Red" : "Yellow") + "'s Turn");
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Column is full! Choose another column.", "Invalid Move", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void disableButtons() {
        for (JButton button : columnButtons) {
            button.setEnabled(false);
        }
    }
}
