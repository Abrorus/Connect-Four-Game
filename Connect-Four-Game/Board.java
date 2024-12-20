public class Board {
    private char[][] grid;
    private final int ROWS = 6;
    private final int COLS = 7;

    public Board() {
        grid = new char[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                grid[row][col] = ' ';
            }
        }
    }

    public int dropDisc(int column, char player) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == ' ') {
                grid[row][column] = player;
                return row;
            }
        }
        return -1; // Column is full
    }

    public boolean checkWin(char player) {
        // Check horizontal
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (grid[row][col] == player && grid[row][col + 1] == player &&
                    grid[row][col + 2] == player && grid[row][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == player && grid[row + 1][col] == player &&
                    grid[row + 2][col] == player && grid[row + 3][col] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (grid[row][col] == player && grid[row - 1][col + 1] == player &&
                    grid[row - 2][col + 2] == player && grid[row - 3][col + 3] == player) {
                    return true;
                }
            }
        }

        // Check diagonal (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLS - 3; col++) {
                if (grid[row][col] == player && grid[row + 1][col + 1] == player &&
                    grid[row + 2][col + 2] == player && grid[row + 3][col + 3] == player) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isFull() {
        for (int col = 0; col < COLS; col++) {
            if (grid[0][col] == ' ') {
                return false;
            }
        }
        return true;
    }
}
