public class Word {
    private LinearNode<Letter> firstLetter;

    // Constructs a Word object from an array of letters, which are stored in reverse order in the word
    public Word(Letter[] letters) {
        firstLetter = null;

        // Iterate through the letters in reverse order
        for (int i = letters.length - 1; i >= 0; i--) {
            LinearNode<Letter> node = new LinearNode<>(letters[i]);

            // Add the node to the beginning of the word
            node.setNext(firstLetter);
            firstLetter = node;
        }
    }

    // Returns a string representation of the Word object
    public String toString() {
        StringBuilder sb = new StringBuilder("Word: ");
        LinearNode<Letter> current = firstLetter;
        while (current != null) {
            sb.append(current.getElement().toString()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // Labels the word based on a mystery word
    // Sets the label of each letter in the word according to the comparison with the mystery word
    public boolean labelWord(Word mystery) {
        LinearNode<Letter> current = firstLetter;
        LinearNode<Letter> mysteryCurrent = mystery.firstLetter;
        boolean identical = true;

        while (current != null && mysteryCurrent != null) {
            Letter letter = current.getElement();
            Letter mysteryLetter = mysteryCurrent.getElement();

            if (letter.equals(mysteryLetter)) {
                letter.setCorrect();
            } else if (mystery.containsLetter(letter)) {
                letter.setUsed();
                identical = false;
            } else {
                letter.setUnused();
                identical = false;
            }

            current = current.getNext();
            mysteryCurrent = mysteryCurrent.getNext();
        }

        // Check if both words have been fully traversed
        return identical && current == null && mysteryCurrent == null;
    }

    // Checks if the word contains a specific letter
    private boolean containsLetter(Letter letter) {
        LinearNode<Letter> current = firstLetter;
        while (current != null) {
            if (current.getElement().equals(letter)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
}
