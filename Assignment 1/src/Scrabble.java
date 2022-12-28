import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scrabble {
	
	/*
	  * Private variables
	  */
	
	private Tile tile[];
	
	/*
	 *  Public Methods
	 */
	
	public Scrabble () {
		tile = new Tile[7]; // This is because we have a total of 7 tiles
		for(int i=0;i<7;i++) {
			tile[i].pickup();
		}
	}
	
	public Scrabble(Tile[]t) {
		tile = new Tile[7]; // Same as before, because we have 7 tiles
		tile=t;
	}
	
	public String getLetters() {
		String word=""; 
		for(int i=0;i<7;i++) {
			word = word + tile[i].getValue(); // So basically this appends the value to the string
		}
		return word; // This returns the new string
	}

	public ArrayList<String>getWords() {
		ArrayList<String> word = new ArrayList<String>();
		try {
			File file = new File("CollinsScrabbleWords2019.txt"); // checks to see if this file exists, then gets all the possible words we can make given our available letters
			Scanner scanner = new  Scanner(file);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
		}
		scanner.close();
		return word;
	}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			return word;
		}
	}
	public int[] getScores() {
		letValues.put('A', 1);
		letValues.put('B', 3);
		letValues.put('C', 3);
		letValues.put('D', 2);
		letValues.put('E', 1);
		letValues.put('F', 4);
		letValues.put('G', 2);
		letValues.put('H', 4);
		letValues.put('I', 1);
		letValues.put('J', 8);
		letValues.put('K', 5);
		letValues.put('L', 1);
		letValues.put('M', 3);
		letValues.put('N', 1);
		letValues.put('O', 1);
		letValues.put('P', 3);
		letValues.put('Q', 10);
		letValues.put('R', 1);
		letValues.put('S', 1);
		letValues.put('T', 1);
		letValues.put('U', 1);
		letValues.put('V', 4);
		letValues.put('W', 4);
		letValues.put('X', 8);
		letValues.put('Y', 4);
		letValues.put('Z', 10);
		
		int[] wordValues = new int[wordList.size()];
		for(int x=0; x<wordList.size(); x++); {
			String word = wordList.get(x);
			int wordScore = 0;
			for (int y = 0; y<word.length(); y++) {
				int letterValue = letValues.get(word.charAt(y));
				wordScore += letterValue;
				wordValues[x] = wordScore;
			}
		}
		Arrays.sort(wordValues);
		return wordValues;
	}
	/*
	 * Checking the value of the tiles
	 */
	public boolean equals(Scrabble s) {
		for(int i=0;i<7;i++) {
			if(this.tile[i].getValue() != s.tile[i].getValue()) //returns false if tile does not match
				return false;
		}
		return true; //returns true otherwise
	}

}
