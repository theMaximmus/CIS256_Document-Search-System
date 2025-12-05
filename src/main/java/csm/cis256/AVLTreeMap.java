package csm.cis256;

/**
 * AVL Tree Map (key, value)
 * keys must be Comparable.
 * Keeps tree balanced automatically.
 */
public class AVLTreeMap<K extends Comparable<K>, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        Node(K k, V v) {
            key = k;
            value = v;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTreeMap() {
        root = null;
        size = 0;
    }

    /** Public insert/update */
    public void put(K key, V value) {
        if (key == null) return;
        root = insert(root, key, value);
    }

    /** Actual recursive insert */
    private Node insert(Node n, K key, V value) {

        if (n == null) {
            size++;
            return new Node(key, value);
        }

        int cmp = key.compareTo(n.key);

        if (cmp < 0) {
            n.left = insert(n.left, key, value);
        } else if (cmp > 0) {
            n.right = insert(n.right, key, value);
        } else {
            n.value = value;
            return n;
        }

        updateHeight(n);
        return balance(n);
    }

    /** get by key */
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

    /** height helper */
    private int h(Node n) {
        return n == null ? 0 : n.height;
    }

    /** update height */
    private void updateHeight(Node n) {
        n.height = 1 + Math.max(h(n.left), h(n.right));
    }

    /** balance factor */
    private int bf(Node n) {
        return h(n.left) - h(n.right);
    }

    /** rebalance entry */
    private Node balance(Node n) {
        int b = bf(n);

        // Left heavy
        if (b > 1) {
            if (bf(n.left) < 0) {
                n.left = rotateLeft(n.left);
            }
            return rotateRight(n);
        }

        // Right heavy
        if (b < -1) {
            if (bf(n.right) > 0) {
                n.right = rotateRight(n.right);
            }
            return rotateLeft(n);
        }

        return n;
    }

    /** LL rotation */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /** RR rotation */
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t2 = x.left;

        x.left = y;
        y.right = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
