/**
 * Thrown when access is attempted outside valid Hexagon neighbor index range.
 * Since it is a Hexagon, can only have 6 neighbors (0-5 inclusive)
 * @author CS1027
 *
 */
public class InvalidNeighbourIndexException extends ArrayIndexOutOfBoundsException{

	public InvalidNeighbourIndexException(int i){
		super("Invalid index for hexagon neighbor:" + i + "  Must be 0-5 inclusive");
	}
}
