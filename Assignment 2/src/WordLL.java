/**
 * this class stores all previous guess data as well as the mystery word
 * @author amerkulo
 */

public class WordLL {
    private Word mysteryWord;
    private LinearNode<Word> history;

    public WordLL(Word mystery) {	// initializing the empty linked list
        history=new LinearNode<Word>();
        mysteryWord=mystery;		// this is what all guesses will be compared to
    }

    public boolean tryWord(Word guess) {	// storing each guess in a node in linked list
        guess.labelWord(mysteryWord);
        LinearNode<Word> guessNode=new LinearNode<Word>(guess);
        guessNode.setNext(history.getNext());
        history.setNext(guessNode);
        return guess.labelWord(mysteryWord);	// return true only if containing the correct letters in the correct order
    }

    public String toString() {		// using the override toString method to show all guess so far
        LinearNode<Word> node=history;
        String guesses="";

        while (node!=null) {
            if (node.getElement()!=null) {
                guesses+=node.getElement()+"\n"; // making sure it goes down to the next line after each word
            }
            node=node.getNext();
        }
        return guesses;
    }
}