package csm.cis256;

/**
 * Simple Binary Search Tree Map (K,V).
 * Keys must be Comparable.
 * @param <K> The type of the keys maintained by this map.
 * @param <V> The type of mapped values.
 */
public class BSTMap<K extends Comparable<K>, V> {

    /**
     * Internal node structure representing a single entry in the tree.
     */
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

    /**
     * Initializes an empty Binary Search Tree map.
     */
    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (key == null) return;
        root = insert(root, key, value);
    }

    /**
     * Recursive helper method to insert a new key-value pair.
     *
     * Algorithm:
     * 1) Base Case: If the current node is null, we have found the insertion point.
     * Create and return a new Node, incrementing the size.
     * 2) Recursive Step: Compare the key with the current node's key.
     * - If less, recurse left.
     * - If greater, recurse right.
     * - If equal, update the existing value (no structural change).</p>
     *
     * @param n The root of the subtree to insert into
     * @param key The key to insert
     * @param value The value to associate
     * @return The root of the updated subtree
     */
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

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key The key whose associated value is to be returned
     * @return The value to which the specified key is mapped, or null if not found
     */
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

    /**
     * Returns the number of key-value mappings in this map.
     * @return The number of key-value mappings
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this map contains no key-value mappings.
     * @return True if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
