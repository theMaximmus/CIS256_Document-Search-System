package csm.cis256;

import java.util.Objects;

/**
 * Simple singly linked list with head/tail references.
 */
public class LinkedList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T peekHead() {
        return head == null ? null : head.data;
    }

    public ListNode<T> getHead() {
        return head;
    }

    public void appendNode(ListNode<T> newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void append(T data) {
        appendNode(new ListNode<>(data));
    }

    public void prependNode(ListNode<T> newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void prepend(T data) {
        prependNode(new ListNode<>(data));
    }

    public void insertNodeAfter(ListNode<T> currentNode, ListNode<T> newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (currentNode == tail) {
            tail.next = newNode;
            tail = newNode;
        } else {
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }

    public boolean insertAfter(T currentItem, T newItem) {
        ListNode<T> currentNode = search(currentItem);
        if (currentNode != null) {
            ListNode<T> newNode = new ListNode<>(newItem);
            insertNodeAfter(currentNode, newNode);
            return true;
        }
        return false;
    }

    public void removeNodeAfter(ListNode<T> currentNode) {
        if (currentNode == null && head != null) {
            ListNode<T> succeedingNode = head.next;
            head = succeedingNode;
            if (succeedingNode == null) {
                tail = null;
            }
        } else if (currentNode != null && currentNode.next != null) {
            ListNode<T> succeedingNode = currentNode.next.next;
            currentNode.next = succeedingNode;
            if (succeedingNode == null) {
                tail = currentNode;
            }
        }
    }

    public T removeHead() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        removeNodeAfter(null);
        return data;
    }

    public boolean remove(T item) {
        ListNode<T> previous = null;
        ListNode<T> current = head;
        while (current != null) {
            if (Objects.equals(current.data, item)) {
                removeNodeAfter(previous);
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public ListNode<T> search(T dataValue) {
        ListNode<T> currentNode = head;
        while (currentNode != null) {
            if (Objects.equals(currentNode.data, dataValue)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}
