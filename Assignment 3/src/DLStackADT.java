/* Extended Stack ADT */

public interface DLStackADT<T>
{
  /**  Adds one element to the top of this stack. 
   *   @param element element to be pushed onto stack
   */
  public void push (T element);
  
  /**  Removes and returns the top element from this stack. Throws
   *   an EmptyStackException if the stack is empty
   *   @return T element removed from the top of the stack
   */
  public T pop() throws EmptyStackException;

  /**  Returns without removing the top element of this stack. Throws
   *   an EmptyStackException if the stack is empty
   *   @return T element on top of the stack
   */
  public T peek();
  
  /** Removes and returns the k-th element from the top of the stack.
   *  Throws an InvalidItemException if the value of k is larger than
   *  the number of elements in the stack.
   *  @param position of element from the top of the stack 
   */
   public T pop(int k) throws InvalidItemException;
  
  /**  Returns true if this stack contains no elements. 
   *   @return boolean whether or not this stack is empty
   */
  public boolean isEmpty();

  /**  Returns the number of elements in this stack. 
   *   @return int number of elements in this stack
   */
  public int size();

  /**  Returns a reference to the top of the stack. 
   *   @return node at the top of the stack
   */
  public DoubleLinkedNode<T> getTop();
}
