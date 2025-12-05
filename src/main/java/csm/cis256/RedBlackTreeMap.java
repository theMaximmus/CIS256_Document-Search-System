package csm.cis256;

/**
 * Simple Red-Black Tree map.
 * Keys must be Comparable.
 * Supports put() and get().
 *
 * This is a lightweight version meant for the CIS256 project tests.
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

    /** PUBLIC PUT */
    public void put(K key, V value) {
        if (key == null) return;
        root = insert(root, key, value);
        root.color = BLACK;  // root must always be black
    }

    /** Recursive insert with rebalancing */
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
    private boolean isRed(Node n) {
        return n != null && n.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        if (h.left != null) h.left.color = BLACK;
        if (h.right != null) h.right.color = BLACK;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}
