package csm.cis256;

public class ListQueue<T> {

    private LinkedList<T> list;

    public ListQueue() {
        list = new LinkedList<>();
    }

    // Add to the back of the queue
    public void enqueue(T item) {
        list.append(item);
    }

    // Remove from the front of the queue
    public T dequeue() {
        if (list.size() == 0) {
            return null;   // tests expect null on empty dequeue
        }

        T value = list.getHead().data;  // return data, not ListNode
        list.removeHead();              // remove first
        return value;
    }

    // Look at the front item
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
