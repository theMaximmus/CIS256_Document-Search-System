package csm.cis256;

/**
 * Node used by the custom singly linked list.
 */
public class ListNode<T> {
    T data;
    ListNode<T> next;

    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
}
