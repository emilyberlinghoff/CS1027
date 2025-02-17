public class ExtendedLetter extends Letter {
    private String content;
    private int family;
    private boolean related;
    private static final int SINGLETON = -1;

    // Constructs an ExtendedLetter object with the specified character
    // Sets the character as a string and the family to SINGLETON
    public ExtendedLetter(char c) {
        super(c);
        content = Character.toString(c);
        family = SINGLETON;
        related = false;
    }

    // Constructs on ExtendedLetter object with the specified content
    // The family is set to SINGLETON
    public ExtendedLetter(String s) {
        super(' ');
        content = s;
        family = SINGLETON;
        related = false;
    }

    // Construct on the ExtendedLetter object with the specified contet and family
    public ExtendedLetter(String s, int fam) {
        super(' ');
        content = s;
        family = fam;
        related = false;
    }

    // Compares this ExtendedLetter with the specified object for equality
    // The related flag is set based on the equality of the families
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ExtendedLetter)) {
            return false;
        }

        ExtendedLetter otherLetter = (ExtendedLetter) other;
        related = family == otherLetter.family;
        return content.equals(otherLetter.content);
    }

    // Returns a string representation of the ExtendedLetter
    // If the letter is unused and related, it returns ".C." for the content and delegates to the parent's toString() method otherwise
    @Override
    public String toString() {
        if (isUnused() && related) {
            return ".C.".replace("C", content);
        } else {
            return super.toString();
        }
    }

    // Creates an array of Letter objects from the given array of content strings and option array of codes
    // If the codes array is null, it constructs ExtendedLetter objets with the content strings
    // If not, it constructs ExtendedLetter objects with the content strings and corresponding codes
    public static Letter[] fromStrings(String[] content, int[] codes) {
        Letter[] letters = new Letter[content.length];
        for (int i = 0; i < content.length; i++) {
            if (codes == null) {
                letters[i] = new ExtendedLetter(content[i]);
            } else {
                letters[i] = new ExtendedLetter(content[i], codes[i]);
            }
        }
        return letters;
    }
}