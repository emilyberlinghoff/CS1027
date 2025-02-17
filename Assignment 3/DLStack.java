import java.util.EmptyStackException;

public class DLStack<T> implements DLStackADT<T> {
    private DoubleLinkedNode<T> top;
    private int size; // Number of elements in the stack

    public DLStack() {
        top = null;
        size = 0;
    }

    // Add an item to the top of the stack
    @Override
    public void push(T dataItem) {
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(dataItem);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top.setPrevious(newNode);
            top = newNode;
        }
        size++;
    }

    // Remove and return the item from the top of the stack
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T dataItem = top.getElement();
        top = top.getNext();
        if (top != null) {
            top.setPrevious(null);
        }
        size--;
        return dataItem;
    }

    // Remove and return the k-th item from the top of the stack
    @Override
    public T pop(int k) throws InvalidItemException, EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        if (k <= 0 || k > size) {
            throw new InvalidItemException("Invalid index for pop operation.");
        }

        DoubleLinkedNode<T> current = top;
        while (k > 1) {
            current = current.getNext();
            k--;
        }

        if (current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
        } else {
            top = current.getNext();
            if (top != null) {
                top.setPrevious(null);
            }
        }

        if (current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
        }

        size--;
        return current.getElement();
    }

    // Return the item from the top of the stack without removing it
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.getElement();
    }

    // Check if the stack is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of elements in the stack
    @Override
    public int size() {
        return size;
    }

    // Return the top node of the stack
    public DoubleLinkedNode<T> getTop() {
        return top;
    }

    // Convert the stack to a string representation
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoubleLinkedNode<T> current = top;
        while (current != null) {
            sb.append(current.getElement()).append(" ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}