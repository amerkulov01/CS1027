/**
 * @author CS1027
 *
 *  Represents the situation in which a stack is empty.
 */

public class EmptyStackException extends RuntimeException
{
  /**
   * Sets up this exception with an appropriate message.
   * @param message String representing the error encountered
   */
  public EmptyStackException (String message)
  {
    super (message);
  }
}
