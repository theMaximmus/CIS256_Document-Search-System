package csm.cis256;

public class LinkedList<T> {

    public static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T d) {
            data = d;
            next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
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
    public Node<T> getHead() {
        return head;
    }

    // required by Tokenizer + SearchEngine tests
    public Node<T> getTail() {
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
        Node<T> n = new Node<>(value);
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

        Node<T> curr = head;
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

        Node<T> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        Node<T> removed = prev.next;
        prev.next = removed.next;

        if (removed == tail) {
            tail = prev;
        }

        size--;
    }
}
