package csm.cis256;

/**
 * Simple Red-Black Tree map.
 * Keys must be Comparable.
 * Supports put() and get().
 *
 * This is a lightweight version meant for the CIS256 project tests.
 * @param <K> The type of keys (must be Comparable)
 * @param <V> The type of mapped values
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left, right;
        boolean color;

        Node(K k, V v, boolean c) {
            key = k;
            value = v;
            color = c;
        }
    }

    private Node root;
    private int size;

    public RedBlackTreeMap() {
        root = null;
        size = 0;
    }

    /**
     * Associates the specified value with the specified key.
     * New nodes are always inserted as RED to preserve black balance,
     * possibly violating other invariants that are fixed during recursion unwinding.
     *
     * @param key The key
     * @param value The value
     */
    public void put(K key, V value) {
        if (key == null) return;
        root = insert(root, key, value);
        root.color = BLACK;  // root must always be black
    }

    /**
     * Recursive insert that fixes LLRB invariants on the way up.
     *
     * @param h The root of the subtree
     * @param key The key to insert
     * @param value The value to associate
     * @return The new root of the subtree
     */
    private Node insert(Node h, K key, V value) {

        if (h == null) {
            size++;
            return new Node(key, value, RED);
        }

        int cmp = key.compareTo(h.key);

        if (cmp < 0) {
            h.left = insert(h.left, key, value);
        } else if (cmp > 0) {
            h.right = insert(h.right, key, value);
        } else {
            h.value = value;
        }

        // Fix right-leaning reds
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }

        // Fix two reds in a row on the left
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        // Split 4-node
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    /** Get value by key */
    public V get(K key) {
        Node n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp == 0) return n.value;
            if (cmp < 0) n = n.left;
            else n = n.right;
        }
        return null;
    }

    /** Helpers */

    /**
     * Checks the color of a node. Null nodes are considered BLACK.
     */
    private boolean isRed(Node n) {
        return n != null && n.color == RED;
    }

    /**
    * Fixes right-leaning red links.
    */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        return x;
    }

    /**
    * Fixes two red links in a row on the left.
    */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        return x;
    }

    /**
     * Splits a temporary 4-node (a node with two red children).
     */
    private void flipColors(Node h) {
        h.color = RED;
        if (h.left != null) h.left.color = BLACK;
        if (h.right != null) h.right.color = BLACK;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}
