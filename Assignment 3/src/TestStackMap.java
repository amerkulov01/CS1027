
public class TestStackMap {

	public static void main(String[] args) {
		boolean testPassed = true;
		Integer val = null;

		DLStack<Integer> s = new DLStack<Integer>();

		// Test 1. Small stack, test push and peek
		try {
			for (int i = 0; i < 11; ++i)
				s.push(Integer.valueOf(i));

			val = s.peek();
			if (val.intValue() == 10 && s.size() == 11 && !s.isEmpty())
				testPassed = true;
			else
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}
		if (testPassed)
			System.out.println("Test 1 passed");
		else
			System.out.println("Test 1 failed");

		// Test 2. Small stack. Test pop.
		try {
			for (int i = 0; i < 5; ++i)
				val = s.pop();
			if (val.intValue() == 6 && !s.isEmpty() && s.size() == 6)
				testPassed = true;
			else
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");

		// Test 3. Pop and peek from an empty stack, pop(int k)
		testPassed = false;
		try {
			for (int i = 0; i < 10; ++i)
				val = s.pop();
		} catch (EmptyStackException e) {
			try {
				val = s.peek();
			} catch (EmptyStackException e2) {
				testPassed = true;
			}
			for (int i = 0; i < 5; ++i)
				s.push(Integer.valueOf(i + 1));
			val = s.pop(5);
			if (val.intValue() != 1)
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}
		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");

		// Test 4. Test pop(int k)
		DLStack<String> stack = new DLStack<String>();
		testPassed = true;
		try {
			for (int i = 0; i < 10; ++i)
				stack.push("" + i);
			String value = stack.pop(2); // Delete node storing "8"
			if (!value.equals("8"))
				testPassed = false;
			DoubleLinkedNode<String> node = stack.getTop();
			if (!node.getPrevious().getElement().equals("7"))
				testPassed = false;
			if (!node.getPrevious().getNext().getElement().equals("9"))
				testPassed = false;

			value = stack.pop(1);
			if (!value.equals("9"))
				testPassed = false;
			node = stack.getTop();
			if (!node.getElement().equals("7"))
				testPassed = false;

			value = stack.pop(6);
			if (!value.equals("2"))
				testPassed = false;
			for (int i = 0; i < 6; ++i)
				node = node.getPrevious();
			if (!node.getElement().equals("0"))
				testPassed = false;

			for (int i = 0; i < 6; ++i)
				node = node.getNext();
			if (!node.getElement().equals("7"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 4 passed");
		else
			System.out.println("Test 4 failed");

		// Test 5. Large stack, test push, pop, size
		testPassed = true;
		try {
			s = new DLStack<Integer>();
			for (int i = 0; i < 1000; ++i)
				s.push(Integer.valueOf(i));

			if (s.size() != 1000)
				testPassed = false;
			for (int i = 999; i >= 0; --i) {
				val = s.pop();
				if (val.intValue() != i) {
					testPassed = false;
					break;
				}
			}
			if (testPassed)
				System.out.println("Test 5 passed");
			else
				System.out.println("Test 5 failed");
		} catch (Exception e) {
			System.out.println("Test 5 failed");
		}

		PathFinder program;
		testPassed = true;

		program = new PathFinder("map0.txt");
		try {
			Map m = program.getMap();
			Chamber current = m.getEntrance();
			current = current.getNeighbour(2);
			if (program.isDim(current))
				testPassed = false;
			if (!program.isDim(current.getNeighbour(1)))
				testPassed = false;
			Chamber next = program.bestChamber(current);
			if (next != current.getNeighbour(2))
				testPassed = false;
			current = current.getNeighbour(2);
			next = program.bestChamber(current);
			if (next != current.getNeighbour(3))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}
		if (testPassed)
			System.out.println("Test 6 passed");
		else
			System.out.println("Test 6 failed");
		System.exit(0);
	}

}
