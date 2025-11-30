package csm.cis256;

/**
 * Node used by the custom singly linked list.
 * Each node contains data and a reference to the next node in the list.
 * 
 * @param <T> the type of data held in this node
 */
public class ListNode<T> {
    /** The data stored in this node */
    T data;
    
    /** Reference to the next node in the list, or null if this is the last node */
    ListNode<T> next;

    /**
     * Constructs a new node with the specified data.
     * The next reference is initialized to null.
     * 
     * @param data the data to store in this node
     */
    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Returns a string representation of this node.
     * Shows the data contained in the node.
     * 
     * @return string representation of the node's data
     */
    @Override
    public String toString() {
        return data != null ? data.toString() : "null";
    }
}