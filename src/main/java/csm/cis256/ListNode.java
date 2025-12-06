package csm.cis256;

/**
 * A generic node container for the Singly Linked List.
 *
 * @param <T> The type of data held by this node.
 */
public class ListNode<T> {
    // The data.
    public T data;
    // The pointer to the next node in the list (or null if this is the tail).
    public ListNode<T> next;

    /**
     * Constructs a new node containing the specified data.
     * The "next" pointer is initialized to null by default.
     *
     * @param d The data to store.
     */
    public ListNode(T d) {
        data = d;
        next = null;
    }
}
