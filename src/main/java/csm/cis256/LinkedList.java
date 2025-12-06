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

    // required by SearchEngine + InvertedIndex tests
    public ListNode<T> getHead() {
        return head;
    }

    // required by Tokenizer + SearchEngine tests
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

    // THIS MUST WORK PERFECTLY (fixes the NPE problems)
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

    // used by queue
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

    // used by removeAt
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
