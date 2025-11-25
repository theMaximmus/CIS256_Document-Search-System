package csm.cis256;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * A generic Stack implementation using an array.
 * @param <T>
 */
public class ArrayStack<T> {
    private T data[];
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Constructor initializes the array with a default capacity.
     */
    public ArrayStack() {
        this.data = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Pushes an element onto the top of the stack.
     * Resizes the underlying array if capacity is reached.
     * @param element The element to add.
     */
    public void push(T element) {
        if (size == data.length) {
            resize();
        }

        data[size] = element;
        size++;
    }

    /**
     * Removes and returns the element at the top of the stack.
     * @return The element removed from the top.
     * @throws NoSuchElementException if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop from an empty stack.");
        }

        // Get the item at the top (index is size - 1)
        T element = data[size - 1];

        // Avoid memory leak: nullify the reference so GarbageCollector can recycle it
        data[size - 1] == null;

        size--;

        return element;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     * @return The element at the top.
     * @throws NoSuchElementException if the stack is empty.
     */
    public T top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot access top of an empty stack.");
        }
        return data[size - 1];
    }

    /**
     * Returns the number of elements currently in the stack.
     * @return The integer size.
     */
    public int size() {
        return size;
    }

    /**
     * Checks of the stack is empty.
     * @return true if size is 0. false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Helper method to double the array capacity when full.
     */
    private void resize() {
        data = Arrays.copyOf(data, data.length * 2);
    }
}
