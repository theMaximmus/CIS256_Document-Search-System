package csm.cis256;

public class LinkedList<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Helper method to get the actual node objects.
    public ListNode<T> getHead() {
        return head;
    }

    public ListNode<T> getTail() {
        return tail;
    }

    public T peekHead() {
        if (head == null) return null;
        return head.data;
    }

    public T peekTail() {
        if (tail == null) return null;
        return tail.data;
    }

    public void add(T value) {
        ListNode<T> n = new ListNode<>(value);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    // Alias used by Queue but same behavior
    public void append(T value) {
        add(value);
    }

    // inserts value at index; ignores invalid indexes
    public void addAt(int index, T value) {
        if (index < 0 || index > size) return;

        ListNode<T> n = new ListNode<>(value);

        if (index == 0) { // insert at head
            n.next = head;
            head = n;
            if (tail == null) tail = n;
        } else if (index == size) { // append to tail
            tail.next = n;
            tail = n;
        } else { // insert in the middle
            ListNode<T> prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            n.next = prev.next;
            prev.next = n;
        }

        size++;
    }

    public void removeHead() {
        if (head == null) return;
        head = head.next;
        if (head == null) tail = null;
        size--;
    }

    public void removeTail() {
        if (head == null) return;

        if (head == tail) {
            head = null;
            tail = null;
            size = 0;
            return;
        }

        ListNode<T> curr = head;
        while (curr.next != tail) {
            curr = curr.next;
        }

        curr.next = null;
        tail = curr;
        size--;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= size) return;

        if (index == 0) {
            removeHead();
            return;
        }

        ListNode<T> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        ListNode<T> removed = prev.next;
        prev.next = removed.next;

        if (removed == tail) {
            tail = prev;
        }

        size--;
    }
}
