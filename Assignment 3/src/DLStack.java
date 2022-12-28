public class DLStack<T> implements DLStackADT<T> {

    // private instance variables
    private DoubleLinkedNode<T> top;
    private int numItems;

    public DLStack() {
        top = null;		// initializing top to null
        numItems = 0;	// initializing numItems to 0
    }

    public void push(T dataItem) {		// adds the item to the top of the stack
        if (top == null) {
            top = new DoubleLinkedNode<T>(dataItem);
        } else {
            DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(dataItem);
            newNode.setPrevious(top); // Setting the new node to the top of the stack
            top.setNext(newNode); // Setting old top of stack to new node
            top = newNode;
            newNode.setNext(null); 
        }
        numItems++;
    }

    public T pop() throws EmptyStackException {		// removes and returns data item at top of the stuck
        if (top == null) {
            throw new EmptyStackException("Stack is empty");
        } else {
            T popItem = top.getElement(); // Storing the top of the stack into popItem
            if (top.getPrevious() == null) {	// if the top node is the only node in the stack
                top = null; 
            } else {
                top = top.getPrevious(); 
                top.setNext(null); // Sets the pointer of the new top of the stack to null
            }
            numItems--;
            return popItem;
        }
    }

    public T pop(int k) throws InvalidItemException {	// removes and returns the k'th data item from the top of the stack
        T popItem;
        if (k > numItems || k <= 0) {
            throw new InvalidItemException("Invalid item");
        } else if (k == 1) {
        	popItem = pop(); // using the pop method if k is 1
        } else {
            DoubleLinkedNode<T> current = top;
            for (int i = 1; i < k - 1; i++) { // to loop through the stack
                current = current.getPrevious();
            }
            popItem = current.getPrevious().getElement(); 
            if (current.getPrevious().getPrevious() != null) { // if node before element is null
                current.getPrevious().getPrevious().setNext(current); 
                current.setPrevious(current.getPrevious().getPrevious()); 
            } else {
                current.setPrevious(null); 
            }
        }
        numItems--;
        return popItem;
    }
    
    public T peek() throws EmptyStackException {	//returns the top of the stack without removing it
        if (numItems == 0) {
            throw new EmptyStackException("Stack is empty");	// throws exception if it is empty
        }
        return top.getElement();
    }

    public boolean isEmpty() {	//returns true if the stack is empty
        return numItems == 0;
    }

    public int size() {	// returns the number of data items in the stack
        return numItems;
    }

    public DoubleLinkedNode<T> getTop() {	// returns top
        return top;
    }

    public String toString() {	// returns string in form “[data1 data2 … datan]” - data1 is top of stack, datan is bottom
        String result = "[";
        DoubleLinkedNode<T> current = top;
        while (current != null) {
            result += current.getElement();
            current = current.getPrevious();
            if (current != null) {
                result += " ";
            }
        }
        result += "]";
        return result;
    }
}