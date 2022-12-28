/**
 * This exception is thrown by class Map if an invalid character is encountered 
 * during Map building.
 * 
 * @author CS1027
 * 
 */
public class InvalidMapCharacterException extends RuntimeException{
  
	/**
	   * Sets up this exception with an appropriate message.
	   * @param c: invalid char that was used while building a pyramid 
	   */
	  public InvalidMapCharacterException (char c)
	  {
	    super ("Unknown character: " + c);
	  }
}
