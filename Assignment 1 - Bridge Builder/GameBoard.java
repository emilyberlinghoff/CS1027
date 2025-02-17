/* My approach for designing the code was to work starting with the first method mentioned and then make my way down. I
started writing code, but whenever I was stuck, I would write in pseudocode and return to it when I felt more
knowledgeable about the content. My biggest challenge was making the game follow the logic of adding to the rightmost
spot for the engineer. I went through many trial and errors on the makeMove method because it would work in some cases
for me, but then go in an unwanted spot in other cases.After referring to both the lessons and ZyBooks, I hopefully got
it! I tested the solution by referring to the posted video with the test trials and I made sure the output of my program
exactly matched the output on the video. I chose to not make a testCases.java file due to lack of time. */
package BridgeBuilderAdv;

import java.util.Arrays;

public class GameBoard {

    // Private variables
    private char[][] board;
    private int size;

    // Public methods

    public GameBoard(int size) {
        //    -- Initialize the board to a 2D char array with '.' representing empty positions.
        this.size = size;
        this.board = new char[size][size];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
    }

    public void placeToken(int row, int col, char token) {
        //    -- Place the given token at the specified row and column on the game board.
        board[row][col] = token;
    }

    public boolean isPositionEmpty(int row, int col) {
        //  -- Check whether the specified position on the board is empty (denoted by '.').
        return board[row][col] == '.';
    }

    public int getSize() {
        //    -- Return the size of the game board.
        return size;
    }

    public void displayBoard() {
        // -- Print the game board on the console, including row and column numbers.
        System.out.print("  ");
        for (int col = 0; col < size; col++) {
            System.out.print((char) ('A' + col) + " ");
        }
        System.out.println();

        for (int row = 0; row < size; row++) {
            System.out.print(row + " ");
            for (int col = 0; col < size; col++) {
                char cell = board[row][col];
                if (cell == 'X') {
                    System.out.print("+ ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    public int checkForWinDirection(Player player) {
        //    -- Check whether the player has won the game in any direction (left-to-right, bottom-to-top, or diagonally). Return 1 for left-to-right, 2 for bottom-to-top, 3 for diagonal, and 0 for no win.
        char token = player.getToken();

        // Check left to right
        for (int row = 0; row < size; row++) {
            boolean win = true;
            for (int col = 0; col < size; col++) {
                if (board[row][col] != token) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return 1; // Left to right win
            }
        }

        // Check bottom to top win
        for (int col = 0; col < size; col++) {
            boolean win = true;
            for (int row = 0; row < size; row++) {
                if (board[row][col] != token) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return 2; // Bottom to top win
            }
        }

        // Check top left to bottom right diagonal win
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (board[i][i] != token) {
                win = false;
                break;
            }
        }
        if (win) {
            return 3; // Diagonal win
        }

        return 0; // No win
    }
    public boolean checkForTie() {
        //    -- Check whether the game board is full, indicating a tie.
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '.') {
                    return false; // Board isn't full
                }
            }
        }
        return true; // Board is full and it's a tie game
    }
}
