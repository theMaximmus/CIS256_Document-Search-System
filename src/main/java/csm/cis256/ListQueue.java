package csm.cis256;

import java.util.NoSuchElementException;

/**
 * Queue implementation backed by the custom singly linked list.
 */
public class ListQueue<T> {
    private final LinkedList<T> list;
    private int size;

    public ListQueue() {
        this.list = new LinkedList<>();
        this.size = 0;
    }

    public void enqueue(T item) {
        list.append(item);
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        size--;
        return list.removeHead();
    }

    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return list.peekHead();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
