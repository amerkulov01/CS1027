
public class Tile {
	
	/* Private Variables 
	 * 
	 */
	
	private char value;
	
	/* Public Methods
	 * 
	 */
	public Tile () {
		value = ' ';
		
	}
	public Tile (char v) {
		value = v;
	}
	
	public void pickup() {
		int number  = (int)(Math.random()*(91-65+1)+65);
		
		value = (char)number;
	}
	
	public char getValue() {
		return value;
	}

}
