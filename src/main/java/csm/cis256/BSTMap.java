package csm.cis256;

/**
 * Simple Binary Search Tree Map (K,V).
 * Keys must be Comparable.
 */
public class BSTMap<K extends Comparable<K>, V> {

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    /** Insert or update */
    public void put(K key, V value) {
        if (key == null) return;
        root = insert(root, key, value);
    }

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
        }

        return n;
    }

    /** retrieve value or null */
    public V get(K key) {
        Node n = root;

        while (n != null) {
            int cmp = key.compareTo(n.key);

            if (cmp == 0) return n.value;
            else if (cmp < 0) n = n.left;
            else n = n.right;
        }
        return null;
    }

    /** return size */
    public int size() {
        return size;
    }

    /** return true if empty */
    public boolean isEmpty() {
        return size == 0;
    }
}
