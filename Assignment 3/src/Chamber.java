import java.awt.Color;

/**
 * This class represents a pointed-top Hexagonal pyramid chamber. 
 * Each chamber type will be of a different colour.
 * 
 * Chambers know about their neighbors. The neighbors of a chamber are 
 * accessed by an index 0-5 inclusive.
 * The hexagonal chambers are pointed-top in orientation, the 0 index is the
 * upper-right side. Indices for the sides progress incrementally clockwise from the 0 index,
 * to 5 on the upper-left side<
 * Eg.
 *     5 /  \ 0</code>
 *     4 |  | 1</code>
 *     3 \  / 2</code>
 * 
 * @author CS1027
 *
 */
public class Chamber extends HexComponent {
	private static final long serialVersionUID = 4865976127980106774L;

	// enum to represent available chamber types
	public static enum HexType {SEALED, ENTRANCE, TREASURE, TREASURE2, LIGHTED, ENTRANCE_PROCESSED,  
	                            TREASURE_PROCESSED,  ENTRANCE_POPPED, PUSHED, POPPED, DARK, DIM
	                           };

	// Attributes
	private HexType type;   // Stores the current type of this Chamber. This value
							// changes as the Chambers in the map are marked
	private HexType originalType; // Type initially assigned to this Chamber
	private boolean isEntrance;   // Is this the entrance?
	private boolean isTreasureChamber; // Is this a treasure chamber?
	private Chamber[] neighbors; // Stores the neighboring chambers to this one 
        private int timeDelay; // Value used to determine the speed of the animation
        private static int chamberCounter = 1;
        private int chamberId;


	/**
	 * Create a Hexagonal chamber of the specified type
	 * 
	 * @param t: the HexType to create
	 */
    public Chamber(HexType t, int delay) {
                chamberId = chamberCounter;
                chamberCounter++;
	        timeDelay = delay;
		this.type = t;
		this.originalType = t;
		this.isEntrance = (t == HexType.ENTRANCE);
		this.isTreasureChamber = (t == HexType.TREASURE || t == HexType.TREASURE2);

		// set the initial color based on the initial type
		this.setColor(this.type);
		// allocate space for the neighbor array
		this.neighbors = new Chamber[6];
	}

	/**
	 * Set the neighbor for this chamber using the neighbor index.
	 * The index for the neighbor indicates which side of the chamber this new
	 * neighbor is on. 0-5 inclusive.
	 * @param neighbor
	 *            The new Chamber neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-5
	 *            inclusive)
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-5 inclusive.
	 */
	public void setNeighbour(Chamber neighbor, int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i <= 5)
			this.neighbors[i] = neighbor;
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * Returns the neighbor for this chamber using the neighbor index
	 * The index for the neighbor indicates which side of the chamber the
	 * neighbor to get is on. 0-5 inclusive.
	 * 
	 * @param i
	 *            The index indicating the side of the chamber this neighbor is
	 *            on
	 * @return The chamber that is on the i-th side of the current chamber, or
	 *         null if no neighbor
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-5 inclusive.
	 */
	public Chamber getNeighbour(int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i <= 5)
			return this.neighbors[i];
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * This method checks if the current chamber is sealed
	 * @return true if this is a sealed chamber, false otherwise.
	 */
	public boolean isSealed() {
		return type == HexType.SEALED;
	}


	/**
	 * This method checks if the current chamber has been marked as pushed or
	 * popped.
	 * @return true if this chamber has been marked as pushed or popped, false
	 *         otherwise.
	 */

	public boolean isMarked() {
		return (type == HexType.PUSHED) || (type == HexType.POPPED);
	}

	/**
	 * This method checks if the current Chamber object represents a chamber that is lighted
	 * @return true if chamber is lighted, false otherwise.
	 */
	public boolean isLighted() {
		return originalType == HexType.LIGHTED || type == HexType.ENTRANCE || 
				originalType == HexType.TREASURE || originalType == HexType.TREASURE2 || 
				type == HexType.ENTRANCE_PROCESSED || type == HexType.TREASURE_PROCESSED;
	}
	

	/**
	 * This method checks if the current chamber is the entrance.
	 * @return true if this is the entrance, false otherwise.
	 */
	public boolean isEntrance() {
		return this.isEntrance;
	}

	/**
	 * This method checks if the current chamber contains treasure.
	 * @return true if this is chamber contains treasure, false otherwise.
	 */
	public boolean isTreasure() {
		return this.isTreasureChamber;
	}

	/**
	 * This re-draws the current chamber
	 */
        private void reDraw() {
			if (Map.testingMode) return;
			try {
				Thread.sleep(timeDelay);
			} catch (Exception e) {
				System.err.println("Error while issuing time delay\n" + e.getMessage());
			}
			super.repaint();
        }

	/**
	 * This method marks the chamber as Pushed and updates the chamber's color
	 */
	public void markPushed() {
		if (originalType == HexType.TREASURE)
			type = HexType.PUSHED;
		else if (originalType == HexType.TREASURE2) 
			type = HexType.PUSHED;
		else type = HexType.PUSHED;
		setColor(type);
		reDraw();
	}

	/**
	 * This method marks the chamber as popped and updates the chamber's color
	 */
	public void markPopped() {
		type = HexType.POPPED;
		setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the treasure chamber and updates the chamber's color
	 */
	public void markExit() {
		this.type = HexType.TREASURE_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the chamber as the starting chamber and updates the
	 * chamber's color
	 */
	public void markStart() {
		this.type = HexType.ENTRANCE_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}
	

	/**
	 * Helper method to set the current chamber color based on the type of
	 * chamber.
	 * 
	 * @param t
	 *            The type of the chamber; used to set the color
	 */
	private void setColor(HexType t) {
		switch (t) {
		case SEALED:
			this.setBackground(HexColors.SEALED);
			break;
		case ENTRANCE:
			this.setBackground(HexColors.ENTRANCE);
			break;
		case TREASURE:
			if (originalType == HexType.TREASURE)
				this.setBackground(HexColors.TREASURE);
			else this.setBackground(HexColors.TREASURE2);
			break;
		case TREASURE2:
			this.setBackground(HexColors.TREASURE2);
			break;			
		case LIGHTED:
			this.setBackground(HexColors.LIGHTED);
			break;
		case TREASURE_PROCESSED:
			this.setBackground(HexColors.TREASURE_PROCESSED);
			break;
		case ENTRANCE_PROCESSED:
			this.setBackground(HexColors.ENTRANCE_PROCESSED);
			break;
		case ENTRANCE_POPPED:
			this.setBackground(HexColors.ENTRANCE_POPPED);
			break;
		case PUSHED:
			if (originalType == HexType.DARK)
				setBackground(HexColors.DARK_PUSHED);
			else if (originalType == HexType.DIM)
				setBackground(HexColors.DIM_PUSHED);
			else if (originalType == HexType.ENTRANCE)
				this.setBackground(HexColors.ENTRANCE_PROCESSED);
			else if (originalType == HexType.TREASURE)
				this.setBackground(HexColors.TREASURE_PROCESSED);
			else if (originalType == HexType.TREASURE2)
				this.setBackground(HexColors.TREASURE_PROCESSED1);
			else
				setBackground(HexColors.PUSHED);
			break;
		case POPPED:
			if (originalType == HexType.DARK)
				setBackground(HexColors.DARK_POPPED);
			else if (originalType == HexType.DIM)
				setBackground(HexColors.DIM_POPPED);
			else if (originalType == HexType.ENTRANCE)
				setBackground(HexColors.ENTRANCE_POPPED);
			else
				setBackground(HexColors.POPPED);
			break;
		case DARK:
			this.setBackground(HexColors.DARK);
			break;
		case DIM:
			this.setBackground(HexColors.DIM);
			break;
		default:
			this.setBackground(HexColors.SEALED);
			break;
		}
		this.setForeground(Color.BLACK);
	}
	
	public String toString() {
   		return "Chamber "+chamberId+" of type "+originalType;
	}

}
