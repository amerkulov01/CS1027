
public class TestWordLL {

	public static int passed = 0;
	public static int tested = 0;

	public static void main(String[] args) {
		//--------------------
		Letter letter1 = new Letter('J');
		Letter letter2 = new Letter('V');
		Letter letter3 = new Letter('J');

		// ********** Letter equals
		test(1,"Letter equals", !letter1.equals(letter2) && letter1.equals(letter3));
		letter1.setCorrect();
		letter2.setUsed();
		letter3.setUnused();

		// ********** Letter set methods
		test(2,"Letter set methods", (letter1.toString()+letter2.toString()+letter3.toString()).equals("!J!+V+-J-"));
		Letter[] array = Letter.fromString("JAVA");

		// ********** Letter fromString
		test(3,"Letter fromString", array[1].equals(array[3]) && array[0].equals(letter1));
		Word word1 = new Word(Letter.fromString("OBJECT"));
		Word word2 = new Word(Letter.fromString("CLASS"));
		word2.labelWord(word1);
		Word word3 = new Word(Letter.fromString("CODE"));
		word3.labelWord(word1);

		// ********** Word toString + Constructor
		test(4,"Word toString + Constructor", word1.toString().equals("Word:  O   B   J   E   C   T  "));

		// ********** Word label
		test(5,"Word label", word2.toString().equals("Word: +C+ -L- -A- -S- -S- "));

		// ********** Word label
		test(6,"Word label", word3.toString().equals("Word: +C+ +O+ -D- !E! "));

		// ********** Word label
		test(7,"Word label", (word1.labelWord(word1)+word1.toString()).equals("trueWord: !O! !B! !J! !E! !C! !T! "));

		// ********** Word label
		test(8,"Word label", (word1.labelWord(word3)+word1.toString()).equals("falseWord: +O+ -B- -J- !E! +C+ -T- "));
		WordLL wll = new WordLL(new Word(Letter.fromString("OBJECT")));
		String[] arr = {"JOB","TESTING","OBJECTS"};
		for(int i=0;i<arr.length;i++) {
			wll.tryWord( new Word(Letter.fromString(arr[i])) );
		}

		// ********** WordLL tryWord +
		test(9,"WordLL tryWord +", wll.toString().contains("Word: !O! !B! !J! !E! !C! !T! -S- ") &&
		wll.toString().contains("Word: +T+ +E+ -S- +T+ -I- -N- -G- ") &&
		wll.toString().contains("Word: +J+ +O+ +B+ ") );
		ExtendedLetter el1 = new ExtendedLetter("@"); 
		ExtendedLetter el2 = new ExtendedLetter(":)",1); 
		ExtendedLetter el3 = new ExtendedLetter(":(",1);

		// ********** ExtendedLetter equals
		test(10,"ExtendedLetter equals", el1.equals(el1) && !el1.equals(el2) && !el3.equals(el2));
		el1.setCorrect();
		el2.setUnused();
		el3.setUnused();

		// ********** ExtendedLetter toString
		test(11,"ExtendedLetter toString", (el1.toString()+el2.toString()+el3.toString()).equals("!@!-:)-.:(."));
		String[][] stArr={{"JD","AD","9H","10S"},
			{"JC","9H","KH","AS"},
			{"10C","JC","9D","KH"},
			{"JC","10D","9H","QH"},
			{"10C","AD","9H","KH"},
			{"10C","QC","QD","10H"},
			{"JC","9D","KH","9S"}};
		int[][] intArr = {{2,5,0,1},
			{2,0,4,5},
			{1,2,0,4},
			{2,1,0,3},
			{1,5,0,4},
			{1,3,3,1},
			{2,0,4,0}};		

		WordLL wll2 = new WordLL(new Word(ExtendedLetter.fromStrings(stArr[stArr.length-1],intArr[stArr.length-1])));
		for (int i = 0; i < stArr.length; i++) {
			wll2.tryWord(new Word(ExtendedLetter.fromStrings(stArr[i],intArr[i])));
		}

		// ********** ExtendLetter WordLL tryWord +
		test(12,"ExtendLetter WordLL tryWord +", wll2.toString().contains("Word: !JC! !9D! !KH! !9S! ") &&
		wll2.toString().contains("Word: -10C- -QC- -QD- -10H- ") &&
		wll2.toString().contains("Word: -10C- -AD- .9H. +KH+ ") &&
		wll2.toString().contains("Word: !JC! -10D- .9H. -QH- ") &&
		wll2.toString().contains("Word: -10C- +JC+ +9D+ +KH+ ") &&
		wll2.toString().contains("Word: !JC! .9H. !KH! -AS- ") &&
		wll2.toString().contains("Word: .JD. -AD- .9H. -10S- ") );
		System.out.println("Your code scored: " + passed + " / " + tested);
		//--------------------
	}

	public static void test(int testNumber, String message, boolean testStatus) {
		tested++;
		System.out.println("Test " + testNumber + " (" + message + ") " + (testStatus ? "passed" : "failed"));
		if (testStatus)
			passed++;
	}
}
