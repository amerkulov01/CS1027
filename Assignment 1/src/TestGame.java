import java.io.*;
import java.io.File;  // Import the File class
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

public class TestGame {

	public static void main(String[] args) throws Exception{
		
		//All required variables to store tests' results if you want to do that
		/**************************************************/
		BufferedWriter writer = new BufferedWriter(new FileWriter("tests_results_game.txt"));
		File myObj=null;
		Scanner scanRead=null;
		/**************************************************/
		
		Scrabble game1, game2, game3, game4, game5;
		
		Tile[] gameTwoTiles = new Tile[7];
		Tile[] gameThreeTiles = new Tile[7];
		Tile[] gameFourTiles = new Tile[7];
		Tile[] gameFiveTiles = new Tile[7];
		
		gameTwoTiles[0] = new Tile('A');
		gameTwoTiles[1] = new Tile('B');
		gameTwoTiles[2] = new Tile('C');
		gameTwoTiles[3] = new Tile('D');
		gameTwoTiles[4] = new Tile('E');
		gameTwoTiles[5] = new Tile('F');
		gameTwoTiles[6] = new Tile('G');
		
		gameThreeTiles[0] = new Tile('Z');
		gameThreeTiles[1] = new Tile('Y');
		gameThreeTiles[2] = new Tile('X');
		gameThreeTiles[3] = new Tile('W');
		gameThreeTiles[4] = new Tile('V');
		gameThreeTiles[5] = new Tile('U');
		gameThreeTiles[6] = new Tile('T');
		
		gameFourTiles[0] = new Tile('Y');
		gameFourTiles[1] = new Tile('Z');
		gameFourTiles[2] = new Tile('T');
		gameFourTiles[3] = new Tile('W');
		gameFourTiles[4] = new Tile('U');
		gameFourTiles[5] = new Tile('V');
		gameFourTiles[6] = new Tile('X');
		
		gameFiveTiles[0] = new Tile('C');
		gameFiveTiles[1] = new Tile('O');
		gameFiveTiles[2] = new Tile('O');
		gameFiveTiles[3] = new Tile('O');
		gameFiveTiles[4] = new Tile('L');
		gameFiveTiles[5] = new Tile('Y');
		gameFiveTiles[6] = new Tile('Y');
		
		
		game1 = new Scrabble();
		game2 = new Scrabble(gameTwoTiles);
		game3 = new Scrabble(gameThreeTiles);
		game4 = new Scrabble(gameFourTiles);
		game5 = new Scrabble(gameFiveTiles);
		
		ArrayList<String> getWordCheckerGameThree = new ArrayList<String>() {{
		    add("TUX");
		    add("UT");
		    add("WUZ");
		    add("XU");
		    add("YU");
		    add("YUTZ");
		}};
		
		ArrayList<String> getWordCheckerGameFive = new ArrayList<String>() {{
		    add("CLOY");
		    add("CLY");
		    add("COL");
		    add("COLY");
		    add("COO");
		    add("COOL");
		    add("COOLY");
		    add("COY");
		    add("COYLY");
		    add("LO");
		    add("LOCO");
		    add("LOO");
		    add("LOY");
		    add("OO");
		    add("OY");
		    add("YO");
		}};
		
		// Test 1 - constructors and getLetters
	
		if (game2.getLetters().equals("ABCDEFG")) {
			System.out.println("Test 1 Passed");
			writer.write("test 1 passed \n");
		} else {
			System.out.println("Test 1 Failed");
			writer.write("test 1 failed \n");
		}
		
		
		// Test 2 - constructors and getLetters
		
		if (game3.getLetters().equals("ZYXWVUT")) {
			System.out.println("Test 2 Passed");
			writer.write("test 2 passed \n");
		} else {
			System.out.println("Test 2 Failed");
			writer.write("test 2 failed \n");
		}
		
		
		
		// Test 3 - equals
		
		if (game3.equals(game4)) {
			System.out.println("Test 3 Passed");
			writer.write("test 3 passed \n");
		} else {
			System.out.println("Test 3 Failed");
			writer.write("test 3 failed \n");
		}
		
		//Test 4 - equals
		
		if (game3.equals(game1)) {
			System.out.println("Test 4 Failed");
			writer.write("test 4 Failed \n");
		} else {
			System.out.println("Test 4 Passed");
			writer.write("test 4 Passed \n");
		}
		
		
		// Test 5 - getWords

		if (game3.getWords().equals(getWordCheckerGameThree)) {
			System.out.println("Test 5 Passed");
			writer.write("test 5 passed \n");
		} else {
			System.out.println("Test 5 Failed");
			writer.write("test 5 failed \n");
		}
		
		// Test 6 - getWords

		if (game5.getWords().equals(getWordCheckerGameFive)) {
			System.out.println("Test 6 Passed");
			writer.write("test 6 passed \n");
		} else {
			System.out.println("Test 6 Failed");
			writer.write("test 6 failed \n");
		}
				
		
		// Test 7 - getScores
		
		int[] vals = {2, 5, 9, 10, 15, 16};

		if (Arrays.toString(game3.getScores()).replaceAll("\\s+","").equals(Arrays.toString(vals).replaceAll("\\s+",""))) {
			System.out.println("Test 7 Passed");
			writer.write("test 7 passed \n");
		} else {
			System.out.println("Test 7 Failed");
			writer.write("test 7 failed \n");
		}

		// Test 8 - getScores
		
		int[] vals2 = {2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 10, 11, 11};

		if (Arrays.toString(game2.getScores()).replaceAll("\\s+","").equals(Arrays.toString(vals2).replaceAll("\\s+",""))) {
			System.out.println("Test 8 Passed");
			writer.write("test 8 passed \n");
		} else {
			System.out.println("Test 8 Failed");
			writer.write("test 8 failed \n");
		}

		writer.close();
	}
	
}

