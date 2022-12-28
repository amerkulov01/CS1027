public class Pyramid {
	private static final int NO_CHECK = 2; // Output was not checked as input file is not one of the
																					// provided maps
	private static final int TEST_PASSED = 1;
	private static final int TEST_FAILED = 0;
	private static int[][] index = { { 2, 2, 1, 2, 1, 5, 5, 5, 5 }, { 0, 5, 5, 4, 5 }, { 4, 4, 5, 5, 0, 1, 5 },
			{ 5, 5, 4, 0, 5, 5, 4, 4, 4, 0, 0, 5, 4, 5 },
			{ 1, 1, 0, 1, 2, 3, 3, 3, 1, 1, 2, 3, 4, 4, 5, 3, 4, 0, 5, 4, 4, 5, 5, 0, 5, 0, 5, 4, 4, 4, 0, 5, 4, 3, 3, 3, 4,
					5, 5, 0, 0, 0, 5, 5, 5, 0, 0 },
			{ 0, 5, 1, 1, 1, 5, 0, 0, 5, 4, 3, 4, 4, 4, 4, 5, 0, 5, 4, 4, 5, 5, 4, 3, 3, 3, 4, 5, 5, 0, 0, 0, 1, 0, 0, 1, 5,
					4, 5, 5, 4 },
			{ 0, 5, 5, 0, 5, 0, 5, 0, 1, 5, 5, 1, 1, 0, 1, 1, 3, 2, 0, 1, 3, 3, 2, 3, 1, 0, 0, 5, 0, 5, 5, 5, 5, 5, 4, 4, 5,
					0, 5, 4, 4, 0 },
			{ 1, 0, 5, 0, 1, 2, 3, 3, 1, 1, 5, 0, 0, 5, 4, 4 } };

	/* Check correctness of the path */
	private static int checkPath(String input, DLStack<Chamber> path) {
		if (input.length() == 8 && input.indexOf("map") != -1) {
			int test = (int) input.charAt(3) - (int) '1';
			Chamber curr = path.pop();
			Chamber next;
			int i = 0;
			// Last chamber must be a treasure chamber
			if (!curr.isTreasure())
				return TEST_FAILED;
			// Check that chambers aer selected according to rules specified in assignment
			while (!path.isEmpty()) {
				next = path.pop();
				if (next != curr.getNeighbour(index[test][i]))
					return TEST_FAILED;
				++i;
				curr = next;
			}
			// First chamber must be the entrance
			if (!curr.isEntrance())
				return TEST_FAILED;
			else
				return TEST_PASSED;
		} else
			return NO_CHECK;
	}

	public static void main(String[] args) {
		/* Check that the user specified an input file */
		if (args.length != 1) {
			System.out.println("Usage: java Pyramid inputFile");
			System.out.println("If using Eclipse, you must specify the input file");
			System.exit(0); // terminate the program
		}
		try {
			PathFinder program = new PathFinder(args[0]);
			Map pyramidMap = program.getMap();
			pyramidMap.showMap();
			DLStack<Chamber> path = program.path();

			int res = checkPath(args[0], path);
			if (res == TEST_PASSED)
				System.out.println("Test for map " + args[0] + " passed");
			else if (res == TEST_FAILED)
				System.out.println("Test for map " + args[0] + " failed");
			else
				System.out.println("Visually check that the output is correct");
		} catch (Exception e) {
			System.out.println("Your program crashed. Here the the exception that is thrown:");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}