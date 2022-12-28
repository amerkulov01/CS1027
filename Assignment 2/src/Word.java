/**
 * this class represents a word which is comprised of any number of letters
 * the letters are stored in linked lists from LinearNode
 * @author amerkulo
 */

public class Word {
    private LinearNode<Letter> firstLetter = null;

    public Word(Letter[] letters) {		// stores each letter in a node in a linked list
        LinearNode<Letter> intNode;

        for (int i=letters.length-1; i>=0; i--) {	// loops however many letters there are
            intNode=new LinearNode<Letter>(letters[i]);
            intNode.setNext(firstLetter);
            firstLetter=intNode;
        }
    }

    public String toString() {		//using the override toString to format the word with the decorators on each side of each letter
        LinearNode<Letter> node=firstLetter;
        String word="Word: ";

        while (node!=null) {
            word+=node.getElement().toString() + " ";
            node=node.getNext();
        }
        return word;				// returns string after modifications made
    }

    public boolean labelWord(Word mystery) {	// this method labels each letter of the word based on its relation to the mystery word
        LinearNode<Letter> mysteryNodeHead = mystery.firstLetter;
        LinearNode<Letter> mysteryNode = mystery.firstLetter;
        LinearNode<Letter> thisNodeHead = firstLetter;
        LinearNode<Letter> thisNode = firstLetter;

        while (thisNode!=null) {		// this loop checks to see if each letter of the mystery used is used in the word guessed
            while (mysteryNode!=null) {
                if (thisNode.getElement().equals(mysteryNode.getElement())) {
                    thisNode.getElement().setUsed();
                    break;
                } else {
                    thisNode.getElement().setUnused();
                    mysteryNode=mysteryNode.getNext();
                }
            }
            mysteryNode=mysteryNodeHead;
            thisNode=thisNode.getNext();
        }

        thisNode=thisNodeHead;

        while (thisNode!=null&&mysteryNode!=null) {	// this now checks if the nodes of the guessed word match the nodes of mystery word
            if (thisNode.getElement().equals(mysteryNode.getElement())) {
                thisNode.getElement().setCorrect();
            }
            thisNode=thisNode.getNext();
            mysteryNode=mysteryNode.getNext();			// returns correct if it is true
        }

        thisNode=thisNodeHead;
        mysteryNode=mysteryNodeHead;

        while (thisNode!=null&&mysteryNode!=null) {	// finally checking to see if the whole guessed word matches the mystery word
            if (!thisNode.getElement().equals(mysteryNode.getElement())) {
                return false;
            }
            thisNode=thisNode.getNext();
            mysteryNode=mysteryNode.getNext();
        }
        return (thisNode==null&&mysteryNode==null);
    }
}