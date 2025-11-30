package csm.cis256;

import java.util.NoSuchElementException;

/**
 * Queue implementation backed by the custom singly linked list.
 */
public class ListQueue<T> {
    private final LinkedList<T> list;
    private int size;

    /**
     * Constructs an empty queue.
     */
    public ListQueue() {
        this.list = new LinkedList<>();
        this.size = 0;
    }

    /**
     * Adds an item to the rear of the queue.
     * 
     * @param item the element to add
     */
    public void enqueue(T item) {
        list.append(item);
        size++;
    }

    /**
     * Removes and returns the item at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        size--;
        return list.removeHead();
    }

    /**
     * Returns the item at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return list.peekHead();
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if the queue contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the queue.
     * 
     * @return the number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements from the queue.
     */
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
    }

    /**
     * Returns a string representation of the queue.
     * 
     * @return string showing queue contents from front to rear
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Queue: []";
        }
        
        StringBuilder sb = new StringBuilder("Queue: [");
        ListNode<T> current = list.getHead();
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}