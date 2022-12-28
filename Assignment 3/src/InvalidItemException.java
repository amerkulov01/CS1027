/**
 * @author CS1027
 *
 *  Represents the situation in which an inexistent item is requested from the stack.
 */

public class InvalidItemException extends RuntimeException
{
  /**
   * Sets up this exception with an appropriate message.
   * @param message String representing the error encountered
   */
  public InvalidItemException (String message)
  {
    super (message);
  }
}
