package csm.cis256;

/**
 * A standard Queue implementation (First-In-First-Out).
 *
 */
public class ListQueue<T> {

    private LinkedList<T> list;

    public ListQueue() {
        list = new LinkedList<>();
    }

    /**
     * Adds an item to the back of the line.
     * @param item Item to be added.
     */
    public void enqueue(T item) {
        list.append(item);
    }

    /**
     * Removes and returns the item at the front of the queue.
     */
    public T dequeue() {
        if (list.size() == 0) {
            return null;   // tests expect null on empty dequeue
        }

        T value = list.getHead().data;  // return data, not ListNode
        list.removeHead();              // remove first
        return value;
    }

    /**
     * Checks for the item in the front without removing it.
     * @return
     */
    public T front() {
        if (list.size() == 0) {
            return null;   // tests expect null on empty front()
        }

        return list.getHead().data;
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}
