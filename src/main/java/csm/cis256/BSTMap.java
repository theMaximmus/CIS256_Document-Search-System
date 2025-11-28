package csm.cis256;

/**
 * A Binary Search Tree Map Implementation.
 * @param <K> The key type (a Comparable).
 * @param <V> The value type.
 */
public class BSTMap<K extends Comparable<K>, V> {

    // Inner Node class
    private class Node {
        K key;
        V value;
        Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Keys can't be null.");
        }
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        // Base case
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        // Compare keys
        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            // Go left
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            // Go right
            node.right = put(node.right, key, value);
        } else {
            // Key exists. Update value
            node.value = value;
        }

        return node;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys can't be null.");
        }

        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys can't be null.");
        }

        if (containsKey(key)) {
            root = remove(root, key);
            size--;
        }
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            // Found the node to delete

            // Case 1: no children or 1 child
            if (node.left == null) {
                return node.left;
            }

            if (node.right == null) {
                return node.right;
            }

            // Case 2: 2 children

            // Replace with successor (smallest in right subtree)
            Node temporary = node;
            node = min(temporary.right);
            // Delete the successor from the right subtree
            node.right = deleteMin(temporary.right);
            node.left = temporary.left;
        }

        return node;
    }

    /**
     * Helper method.
     * Finds the minimum node. (Used for successor)
     * @param node
     * @return
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    /**
     * Helper method.
     * Deletes the minimum node.
     * @param node
     * @return
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = deleteMin(node.left);
        return node;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Debugging method used for debugging tree structure.
     */
    public void printInOrder() {
        printInOrder(root);
        System.out.println();
    }

    /**
     * Debugging method used for debugging tree structure.
     */
    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.println(node.key + " ");
            printInOrder(node.right);
        }
    }
}
