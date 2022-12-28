
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordLLExamples {
	private static Scanner scan;
	private static final int NUMBER_OF_WORDS = 2992; // Number of possible mystery words
	private static String[] mysteryWord = new String[NUMBER_OF_WORDS];

	private static String[] rank = { "9", "10", "J", "Q", "K", "A" };
	private static String[] suit = { "C", "D", "H", "S" };
	/** 
	 * a utility function to create card sets
	 * @return a random Word representing an ascending set of 4 Euchre Cards.
	 *         The idea of ascending comes from both the rank (ordered: 9,10,jack,queen,king,ace) 
	 *         and the suit (ordered: clubs, diamonds, hearts, spades) 
	 *         in particular the ordering will be: 9C,10C,JC,QC,KC,AC,9D,...,AC,9H,...,AH,9S,...,AS
	 *         As well, cards of the same rank will be regarded as being related: so for instance,
	 *         9C, 9D, 9H, and 9S are related
	 */
	public static Word ascendingEuchreCards() {
		// create a combination of (24 choose 4)
		int[] choice = new int[4];
		for (int i = 0; i < choice.length; i++) {
			boolean taken = false;
			do {
				taken = false;
				choice[i] = pick(24);
				for (int j = 0; j < i; j++)
					if (choice[j] == choice[i])
						taken = true;
			} while (taken);
		}
		// sort into ascending order
		for (int i = 0; i < choice.length; i++) {
			int min = i;
			for (int j = i; j < choice.length; j++) {
				if (choice[j] < choice[min])
					min = j;
			}
			int temp = choice[i];
			choice[i] = choice[min];
			choice[min] = temp;
		}

		// create String representations for of the 24 cards and corresponding families
		String[] card = new String[4];
        int[] family = new int[4];
		for (int i = 0; i < choice.length; i++) {
			card[i] = rank[choice[i] % 6] + suit[(int) Math.floor(choice[i] / 6)];
			family[i] = choice[i] % 6; //only the rank is important for the relatedness 
		}
		return new Word(ExtendedLetter.fromStrings(card, family));
	}

	/* Read input file 'words' that contains about 3000 mystery words */
	public static void readMysteryWords(String filename) {
		try {
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			int i = 0;
			while (myReader.hasNextLine()) {
				mysteryWord[i++] = myReader.nextLine();
				// System.out.println(mysteryWord[i-1]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred opening file \'words\'.");
			e.printStackTrace();
		}
	}

	public static int pick(int n) { // return an random choice 0-(n-1)
		return (int) Math.floor(Math.random() * n);
	}

	public static void main(String[] args) {
		scan = new Scanner(System.in, "UTF-8");

		readMysteryWords("words");
		playEnglish();
		//playCards();
		System.out.println("end of testing.");
	}

	/* Play WordLL with words of varying lengths */
	public static void playEnglish() {
		String randomWord = mysteryWord[pick(mysteryWord.length)].toUpperCase();
		Word mystery = new Word(Letter.fromString(randomWord));
		String message = "Enter a word of length " + randomWord.length() + " (XX to stop):";

		WordLL wll = new WordLL(mystery);

		System.out.print(message);
		String wordStr = scan.next().toUpperCase();
		while (!wordStr.equals("XX")) {
			Word word = new Word(Letter.fromString(wordStr));
			if (wll.tryWord(word)) {
				System.out.println("Success!");
				wordStr = "XX";
			} else {
				System.out.println(wll);
				System.out.print(message);
				wordStr = scan.next().toUpperCase();
			}
		}
	}

	/* Play WordLL with Euchre Cards rather than using the implicit patterns of language this game relies on
	 * an ordering of the cards and the ranks of a card to give you information about the mystery "Word" 
	 * which is just a sequence of 4 cards.  */
	public static void playCards() {
		Word mystery = ascendingEuchreCards();
		System.out.println("To Enter cards use this format: AC,KC,9H,1S or R (for a random guess)");
		String message = "Enter 4 Cards (XX to stop):";

		WordLL wll = new WordLL(mystery);
		// --------------------
		Word word = null;

		System.out.print(message);
		String wordStr = scan.next().toUpperCase();
		while (!wordStr.equals("XX")) {
			try {
				if (wordStr.charAt(0) == 'R')  
					// this option saves on typing and lets the player start with some information
					word = ascendingEuchreCards();
				else {
					// construct a card Word from the textual input 
					String[] card = new String[4];
					int[] rank = new int[4];
					for (int i = 0; i < card.length; i++) {
						card[i] = wordStr.substring(i * 3, i * 3 + 2);
						if (card[i].charAt(0)=='1') card[i]="10"+card[i].charAt(1);  //ugly
						//System.out.print(card[i]);
						rank[i] = "91JQKA".indexOf(card[i].charAt(0));  
					}
					word = new Word(ExtendedLetter.fromStrings(card, rank));
				}
				if (wll.tryWord(word)) {
					System.out.println("Success!");
					wordStr = "XX";
				} else {
					System.out.println(wll);  // shows the outcome of the guess through the WordLL history
					System.out.print(message);
					wordStr = scan.next().toUpperCase();
				}
			} catch (Exception e) {
				System.out.println(e);
				wordStr = "XX";
			}
		}
	}
}
