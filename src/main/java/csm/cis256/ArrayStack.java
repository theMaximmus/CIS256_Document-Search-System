package csm.cis256;

import java.util.Arrays;

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

    public void push(T element) {

    }

    public T pop() {
        return null;
    }

    public T top() {
        return data[size -1 ];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        data = Arrays.copyOf(data, data.length * 2);
    }
}
