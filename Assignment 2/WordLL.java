/* My approach to designing this code was to read over all of the assignment specifications and given code and make note
 * on the most important things (there was way too much to remember in just one read). I then started off with half java
 * half pseudocode (that I would then reference the zybooks and lectures to turn into java). My biggest challenge for
 * this assignment was passing all the test cases. Anytime I figured out a way to pass one test cause, I would fail two
 * more. Unfortunately, I left it to the last minute and couldn't get help from the TA office hours. For this WordLL
 * class, I had to implement a linked data structure that maintains two instance variables; mysteryWord and history, the
 * target word to be guessed and a linked list of guessed words, respectively. The tryWord() method takes a guessed
 * word, labels it based on the mystery word using the labelWord() method of the word class and adds it to the history
 * by creating. A new node and updating the linked list. The toString() method constructs a string representation of the
 * WordLL object by iterating through the history and appending each guessed word's string representation. */

public class WordLL {
    private Word mysteryWord;
    private LinearNode<Word> history;

    // Constructs a WordLL object with the specified mystery word
    public WordLL(Word mystery) {
        mysteryWord = mystery;
        history = null;
    }

    // Tries a word and labels it based on the mystery word
    // Adds the guessed word to the history
    public boolean tryWord(Word guess) {

        // Label the guess word based on the mystery word
        guess.labelWord(mysteryWord);

        // Add the guess word to the history
        LinearNode<Word> node = new LinearNode<>(guess);
        node.setNext(history);
        history = node;

        // Return the result of the labelWord method for further evaluation
        return guess.labelWord(mysteryWord);
    }

    // Returns a string representation of the WordLL object, including the history of guessed words
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinearNode<Word> current = history;

        while (current != null) {
            sb.append(current.getElement().toString()).append("\n");
            current = current.getNext();
        }

        return sb.toString();
    }
}