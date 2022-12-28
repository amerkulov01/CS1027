/**
 * Subclass of the Letter class and extends the functionality
 * @author amerkulo
 */

public class ExtendedLetter extends Letter{
    private String content;
    private int family;
    private boolean related;
    private final int SINGLETON=-1;

    public ExtendedLetter(String s) {	//Initializing instance variables of the superclass
        super('c');
        content=s;
        related=false;
        family=SINGLETON;
    }

    public ExtendedLetter(String s, int fam) {	//Initializing instance variables of the super class
        super('c');
        content=s;
        related=false;
        family=fam;
    }

    public boolean equals(Object other) {		// this checks to see if the letters' family is the same
        if (this.getClass()!=other.getClass()) {
            return false;
        } else {
            if (this.family==((ExtendedLetter) other).family) {
                this.related=true;
            }
            return this.content.equals(((ExtendedLetter) other).content);
        }
    }

    public String toString() {		// an override toString method to give a String representation of this ExtendedLetter object
        String c=this.content;

        if (super.isUnused()&&related) {
            return "."+c+".";
        } else {
            return decorator()+c+decorator();
        }
    }

    public static Letter[] fromStrings(String[] content, int[] codes) {		// using the rod length as the parameter, creates an array of letters objects
        Letter[] letters=new Letter[content.length];

        for (int i=0; i<content.length; i++) {		
            if (codes!=null) {
                letters[i]=new ExtendedLetter(content[i], codes[i]);
            } else {
                letters[i]=new ExtendedLetter(content[i]);
            }
        }
        return letters;
    }
}
