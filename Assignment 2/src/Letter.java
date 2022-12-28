/**
 * this class labels each letter based on if it is used in the mystery word or not
 * @author amerkulo
 */

public class Letter {
    private char letter;
    private int label;
    private final int UNSET=0;
    private final int UNUSED=1;
    private final int USED=2;
    private final int CORRECT=3;

    public Letter(char c) {	//setting the letter to equal char taken
        letter=c;
        label=0;
    }

    public boolean equals(Object otherObject) {		//we are comparing if two objects are the equal and in the letter class

        if (this.getClass()==otherObject.getClass()) {
            return this.letter==((Letter) otherObject).letter;
        } else {
            return false;
        }
    }

    public String decorator() {		//these are the symbols assigned to each letter stating whether it is used in the mystery word
        switch (label) {
            case UNUSED:
                return "-";			// not in the mystery word
            case USED:
                return "+";			// in the mystery word but wrong position
            case CORRECT:
                return "!";			// write position in mystery word
            default:
                return " ";
        }
    }

    public String toString() {		// formatting the string by overriding to show the decorator on both sides
        return decorator()+letter+decorator();
    }

    public void setUnused() { 		// method to set label of letter to use
        label=UNUSED;
    }

    public void setUsed() {			// method to set label of letter to use
        label=USED;
    }

    public void setCorrect() {		// method to set label of letter to use
        label=CORRECT;
    }

    public boolean isUnused() {		// if the letter is unused, returns true
        return label==UNUSED;
    }

    public static Letter[] fromString(String s) {		// creates an array which holds each letter of the word
        Letter[] letterArray=new Letter[s.length()];

        for (int i=0; i<s.length(); i++) {			// loop runs for however long the word is
            letterArray[i]=new Letter(s.charAt(i));
        }
        return letterArray;
    }
}