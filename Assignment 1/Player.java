package BridgeBuilderAdv;

public class Player {

    // Private variables
    char token;
    int score;
    public Player() {
        //    -- Initialize the token and score.
        this.token = 'X';
        this.score = 0;
    }

    public void makeMove(GameBoard board, int row, int col) {
        //    -- Place the player's token on the game board at the specified row and column.
        board.placeToken(row, col, token);
    }

    public char getToken() {
        //    -- Return the player's token.
        return token;
    }

    public int getScore() {
        //    -- Return the player's current score.
        return score;
    }

    public void addScore(int increment) {
        //    -- Increase the player's score by the specified increment.
        score += increment;
    }
}
