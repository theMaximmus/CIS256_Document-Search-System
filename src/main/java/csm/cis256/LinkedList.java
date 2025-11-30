package csm.cis256;

import java.util.Objects;

/**
 * Simple singly linked list with head/tail references.
 * 
 * @param <T> the type of elements held in this list
 */
public class LinkedList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the data at the head of the list without removing it.
     * 
     * @return the data at the head, or null if the list is empty
     */
    public T peekHead() {
        return head == null ? null : head.data;
    }

    /**
     * Returns the data at the tail of the list without removing it.
     * 
     * @return the data at the tail, or null if the list is empty
     */
    public T peekTail() {
        return tail == null ? null : tail.data;
    }

    /**
     * Returns the head node of the list.
     * 
     * @return the head node, or null if the list is empty
     */
    public ListNode<T> getHead() {
        return head;
    }

    /**
     * Returns the tail node of the list.
     * 
     * @return the tail node, or null if the list is empty
     */
    public ListNode<T> getTail() {
        return tail;
    }

    /**
     * Appends a node to the end of the list.
     * 
     * @param newNode the node to append
     */
    public void appendNode(ListNode<T> newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Appends data to the end of the list.
     * 
     * @param data the data to append
     */
    public void append(T data) {
        appendNode(new ListNode<>(data));
    }

    /**
     * Prepends a node to the beginning of the list.
     * 
     * @param newNode the node to prepend
     */
    public void prependNode(ListNode<T> newNode) {
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * Prepends data to the beginning of the list.
     * 
     * @param data the data to prepend
     */
    public void prepend(T data) {
        prependNode(new ListNode<>(data));
    }

    /**
     * Inserts a node after the specified current node.
     * 
     * @param currentNode the node after which to insert
     * @param newNode the node to insert
     */
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

    /**
     * Inserts new data after the first occurrence of the current item.
     * 
     * @param currentItem the item to search for
     * @param newItem the new item to insert after currentItem
     * @return true if insertion was successful, false if currentItem was not found
     */
    public boolean insertAfter(T currentItem, T newItem) {
        ListNode<T> currentNode = search(currentItem);
        if (currentNode != null) {
            ListNode<T> newNode = new ListNode<>(newItem);
            insertNodeAfter(currentNode, newNode);
            return true;
        }
        return false;
    }

    /**
     * Removes the node after the specified node.
     * If currentNode is null, removes the head.
     * 
     * @param currentNode the node before the one to remove, or null to remove head
     */
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

    /**
     * Removes and returns the data at the head of the list.
     * 
     * @return the data at the head, or null if the list is empty
     */
    public T removeHead() {
        if (head == null) {
            return null;
        }
        T data = head.data;
        removeNodeAfter(null);
        return data;
    }

    /**
     * Removes and returns the data at the tail of the list.
     * 
     * @return the data at the tail, or null if the list is empty
     */
    public T removeTail() {
        if (head == null) {
            return null;
        }
        
        T data = tail.data;
        
        if (head == tail) {
            // Only one node
            head = null;
            tail = null;
        } else {
            // Find the node before tail
            ListNode<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        
        return data;
    }

    /**
     * Removes the first occurrence of the specified item.
     * 
     * @param item the item to remove
     * @return true if the item was found and removed, false otherwise
     */
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

    /**
     * Searches for a node containing the specified data.
     * 
     * @param dataValue the data to search for
     * @return the node containing the data, or null if not found
     */
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

    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements
     */
    public int size() {
        int count = 0;
        ListNode<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * Returns a string representation of the list.
     * 
     * @return string showing list contents from head to tail
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        ListNode<T> current = head;
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