package csm.cis256;

public class ArrayStack<T> {

    private T[] data;
    private int top; // Points to the next available slot.

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        data = (T[]) new Object[1000];
        top = 0;
    }

    /**
     * Pushes an item onto the top of the stack.
     * Before adding, always checks for available space.
     * @param item Item to be pushed.
     */
    public void push(T item) {
        if (top == data.length - 1) {
            resize();
        }
        data[top++] = item;
    }

    /**
     * Removes and returns the item currently at the top.
     */
    public T pop() {
        if (top == 0) {
            return null;
        }
        // Decrement top first, because 'top' points to the next open slot.
        return data[--top];
    }

    /**
     * Just looks at the top item without removing it.
     */
    public T peek() {
        if (top == 0) {
            return null;
        }
        return data[top - 1];
    }

    /**
     * Checks if the stack is empty.
     * @return A boolean value representing the state.
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Returns the size of the stack.
     * @return Integer size of the stack.
     */
    public int size() {
        return top;
    }

    /**
     * Empties the stack.
     */
    public void clear() {
        top = 0;
    }

    /**
     * Doubles the array capacity when we hit the limit.
     */
    private void resize() {
        T[] newData = (T[]) new Object[data.length + 1000];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
