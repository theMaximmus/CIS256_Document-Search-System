package csm.cis256;

/**
 * AVL Tree Map (key, value)
 * keys must be Comparable.
 * Keeps tree balanced automatically.
 * @param <K> The type of keys maintained by this map (must be Comparable)
 * @param <V> The type of mapped values
 */
public class AVLTreeMap<K extends Comparable<K>, V> {

    /**
     * Internal node structure extended to track height.
     */
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

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * The tree is rebalanced after insertion if necessary.
     *
     * @param key Key with which the specified value is to be associated
     * @param value Value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (key == null) return;
        root = insert(root, key, value);
    }

    /**
     * Recursive helper to insert a key-value pair and rebalance the tree.
     *
     * The method performs a standard BST insert, then unwinds the recursion stack.
     * As it unwinds, it updates the height of each ancestor and checks the balance factor.
     * If an imbalance is detected, rotations are performed to restore the AVL property.
     *
     * @param n The root of the subtree
     * @param key The key to insert
     * @param value The value to associate
     * @return The new root of the subtree
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
            return n;
        }

        updateHeight(n);
        return balance(n);
    }

    /**
     * Returns the value to which the specified key is mapped.
     *
     * @param key The key whose associated value is to be returned
     * @return The value mapped to the key, or null if not found
     */
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

    /**
     * Returns the height of a node, handling nulls safely.
     * @param n The node
     * @return The height (0 if null)
     */
    private int h(Node n) {
        return n == null ? 0 : n.height;
    }

    /**
     * Updates the height of a node based on its children.
     * Height = 1 + max(height of left child, height of right child).
     */
    private void updateHeight(Node n) {
        n.height = 1 + Math.max(h(n.left), h(n.right));
    }

    /**
     * Calculates the Balance Factor (BF) of a node.
     * BF = Height(Left) - Height(Right).
     *
     * @param n The node
     * @return The balance factor
     */
    private int bf(Node n) {
        return h(n.left) - h(n.right);
    }

    /**
     * Restores the AVL property for a node if it has become unbalanced.
     *
     * There are four cases of imbalance:
     * 1) Left-Left Case: Rotate Right.
     * 2) Left-Right Case: Rotate Left child, then Rotate Right.
     * 3) Right-Right Case: Rotate Left.
     * 4) Right-Left Case: Rotate Right child, then Rotate Left.
     *
     * @param n The node to balance
     * @return The new root of this subtree
     */
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

    /**
     * Performs a right rotation on the subtree rooted at y.
     * This is used to fix a Left-Heavy imbalance.
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    /**
    * Performs a left rotation on the subtree rooted at y.
    * This is used to fix a Right-Heavy imbalance.
    */
    private Node rotateLeft(Node y) {
        Node x = y.right;
        Node t2 = x.left;

        // Perform rotation
        x.left = y;
        y.right = t2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x; // New root
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
