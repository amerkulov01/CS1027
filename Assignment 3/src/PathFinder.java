import java.io.IOException;

public class PathFinder {
    private Map pyramidMap;

    public PathFinder(String fileName) throws InvalidMapCharacterException {	// reads inputed file and checks if it is valid
        try {
            pyramidMap = new Map(fileName);
        } catch (IOException e) {
            System.out.println("Filename is Invalid!");
            System.exit(0);
        }
    }

    public DLStack<Chamber> path() {	// finds a path from the entrance to all treasure chambers
        DLStack<Chamber> stack = new DLStack<Chamber>();
        int numb = pyramidMap.getNumTreasures();
        int foundTreasure = 0;
        stack.push(pyramidMap.getEntrance());
        stack.peek().markPushed();
        while (!stack.isEmpty() && foundTreasure != numb) {	// as long as stack is not empty, loop runs
            Chamber cham = stack.peek();
            cham = bestChamber(cham);	// setting current chamber to bestChamber
            if (cham != null) {
                stack.push(cham);	// pushing new chamber into stack
                stack.peek().markPushed();
                if (stack.peek().isTreasure()) foundTreasure++;
            }
            if (cham == null) { 	// if a best chamber does not exist, use pop
                stack.peek().markPopped();
                stack.pop();
            }
        }
        return stack;
    }
    
    public Map getMap() { 	// returns value of pyramidMap
        return pyramidMap;
    }

    public boolean isDim(Chamber currentChamber) {	// returns true if currentChamber is dim
        boolean dim = false;
        if (currentChamber != null && !currentChamber.isSealed() && !currentChamber.isLighted()) { // dim if not null, not sealed, not lighted and a neighbor is lighted
            for (int i = 0; i <= 5; i++) {
                if (currentChamber.getNeighbour(i) != null)
                    if (currentChamber.getNeighbour(i).isLighted()) {
                        dim = true;
                        break;
                    }
            }
        }

        return dim;
    }

    public Chamber bestChamber(Chamber currentChamber) {	// identifies the best chamber to move to
        Chamber bestChamber = currentChamber;
        boolean nextChamber = false;
        for (int i = 0; i <= 5; i++) {	// checking if the neighboring chambers have a treasure
            if (currentChamber.getNeighbour(i) != null && !currentChamber.getNeighbour(i).isSealed()
                    && !currentChamber.getNeighbour(i).isMarked() && !nextChamber) {
                if (currentChamber.getNeighbour(i).isTreasure()) { 		// treasure is found
                    bestChamber = currentChamber.getNeighbour(i);
                    nextChamber = true;	// next chamber to move to
                }
            }
        }
        for (int i = 0; i <= 5; i++) {	// this checks if the neighbors are lighted
            if (currentChamber.getNeighbour(i) != null && !currentChamber.getNeighbour(i).isSealed()
                    && !currentChamber.getNeighbour(i).isMarked() && !nextChamber) {
                if (currentChamber.getNeighbour(i).isLighted()) {		// neighbor is lighted
                    bestChamber = currentChamber.getNeighbour(i);
                    nextChamber = true;	// next chamber to move to
                }
            }
        }
        for (int i = 0; i <= 5; i++) {	// this checks if the neighbor is dim
            if (currentChamber.getNeighbour(i) != null && !currentChamber.getNeighbour(i).isSealed()
                    && !currentChamber.getNeighbour(i).isMarked() && !nextChamber) {
                if (isDim(currentChamber.getNeighbour(i))) {			// neighbor is dim
                    bestChamber = currentChamber.getNeighbour(i);
                    nextChamber = true;	// next chamber to move to
                }
            }
        }
        if (!nextChamber) bestChamber = null;	// if there is no unmarked treasure, lighted or dim chamber, return null
        return bestChamber;
    }
}