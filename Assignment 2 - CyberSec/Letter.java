// Constructs a new Letter object with the specified character
public class Letter {
    private char letter;
    private int label;
    private final int UNSET = 0;
    private final int UNUSED = 1;
    private final int USED = 2;
    private final int CORRECT = 3;
    public Letter(char c) {
        letter = c; // Initially set to c
        label = UNSET; // Initially set to unset
    }

    // Checks if this Letter object is equal to another object (have the same character)
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Letter) {
            Letter otherLetter = (Letter) otherObject;
            return letter == otherLetter.letter;
        }
        return false;
    }

    // Returns the decorator corresponding to the letter's label
    // Decorators represent the letter's status
    public String decorator() {
        switch (label) {
            case USED:
                return "+";
            case UNUSED:
                return "-";
            case CORRECT:
                return "!";
            default:
                return " ";
        }
    }

    // Returns a strong representation of the Letter object
    public String toString() {
        return decorator() + letter + decorator();
    }

    // Sets the label of the letter to UNUSED, which indicates that the letter is an incorrect guess
    public void setUnused() {
        label = UNUSED;
    }

    // Sets the label of letter to USED, which indicates that the letter is a correct guess but not in the correct position
    public void setUsed() {
        label = USED;
    }

    // Sets the label of the letter to CORRECT, which indicates that the letter is a correct guess in the correct position
    public void setCorrect() {
        label = CORRECT;
    }

    // Checks if the letter is labelled as UNUSED
    public boolean isUnused() {
        return label == UNUSED;
    }

    // Creates an array of Letter objects from a string
    // Each character in the string represents a letter in the array
    public static Letter[] fromString(String s) {
        Letter[] letters = new Letter[s.length()];
        for (int i = 0; i < s.length(); i++) {
            letters[i] = new Letter(s.charAt(i));
        }
        return letters;
    }
}
