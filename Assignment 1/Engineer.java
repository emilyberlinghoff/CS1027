package BridgeBuilderAdv;

import java.util.Random;

public class Engineer {

    // Private variables
    private char token;
    private boolean hardMode;
    private Random random;

    // Public methods

    public Engineer(boolean hardMode) {
        //    -- Initialize the token and set the hardMode.
        this.token = '0';
        this.hardMode = hardMode;
        this.random = new Random();
    }

    public void makeMove(GameBoard board, int playerLastRow, int playerLastCol) {
        //   -- Based on the difficulty level (hardMode), make a move on the game board at a position determined by the Engineer's strategy. In easy mode, select a random empty position. In hard mode, select the next empty position after the player's last position, or if the row is full, select the topmost empty position in the same column.
        int row, col;
        if (hardMode) {
            row = playerLastRow;
            col = playerLastCol + 1;
            if (col >= board.getSize()) {
                col = 0;
            }
            while (!board.isPositionEmpty(row, col)) {
                col = (col + 1) % board.getSize();
            }
        }
        else { // Easy mode
            do {
                row = random.nextInt(board.getSize());
                col = random.nextInt(board.getSize());
            } while (!board.isPositionEmpty(row, col));
        }
        board.placeToken(row, col, token);
    }


    public char getToken() {
        //    -- Return the token of the engineer.
        return token;
    }
}
