import java.util.Scanner;

public class ConnectFour {

    static char[][] board = new char[6][7];

    // Initialize the board
    public static void initializeBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = '.';
            }
        }
    }

    // Display the board
    public static void displayBoard() {
        System.out.println();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("-----------------------------");
        System.out.println("  1   2   3   4   5   6   7");
        System.out.println();
    }

    // Drop a player's piece
    public static boolean dropPiece(int column, char player) {

        for (int row = 5; row >= 0; row--) {

            if (board[row][column] == '.') {
                board[row][column] = player;
                return true;
            }
        }

        return false; // Column is full
    }

    // Check whether a player has won
    public static boolean checkWin(char player) {

        // Horizontal
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {

                if (board[row][col] == player &&
                    board[row][col + 1] == player &&
                    board[row][col + 2] == player &&
                    board[row][col + 3] == player) {

                    return true;
                }
            }
        }

        // Vertical
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {

                if (board[row][col] == player &&
                    board[row + 1][col] == player &&
                    board[row + 2][col] == player &&
                    board[row + 3][col] == player) {

                    return true;
                }
            }
        }

        // Diagonal (\)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {

                if (board[row][col] == player &&
                    board[row + 1][col + 1] == player &&
                    board[row + 2][col + 2] == player &&
                    board[row + 3][col + 3] == player) {

                    return true;
                }
            }
        }

        // Diagonal (/)
        for (int row = 3; row < 6; row++) {
            for (int col = 0; col < 4; col++) {

                if (board[row][col] == player &&
                    board[row - 1][col + 1] == player &&
                    board[row - 2][col + 2] == player &&
                    board[row - 3][col + 3] == player) {

                    return true;
                }
            }
        }

        return false;
    }

    // Check whether board is full
    public static boolean isBoardFull() {

        for (int col = 0; col < 7; col++) {
            if (board[0][col] == '.') {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        initializeBoard();

        char player = 'X';

        System.out.println("===== CONNECT FOUR GAME =====");
        System.out.println("Player 1: X");
        System.out.println("Player 2: O");

        while (true) {

            displayBoard();

            System.out.print("Player " + player +
                    ", choose a column (1-7): ");

            int column = sc.nextInt();
            column--;

            // Check valid column
            if (column < 0 || column >= 7) {
                System.out.println("Invalid column! Choose 1-7.");
                continue;
            }

            // Try to place piece
            if (!dropPiece(column, player)) {
                System.out.println("Column is full! Choose another column.");
                continue;
            }

            // Check winner
            if (checkWin(player)) {

                displayBoard();

                System.out.println(" Player " + player + " WINS!");
                break;
            }

            // Check draw
            if (isBoardFull()) {

                displayBoard();

                System.out.println("Game Draw!");
                break;
            }

            // Change player
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
        }

        sc.close();
    }
}