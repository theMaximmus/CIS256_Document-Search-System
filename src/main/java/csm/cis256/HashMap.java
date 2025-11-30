package csm.cis256;

/**
 * A Hash Map implementation using Separate Chaining.
 * @param <K> The key type
 * @param <V> The value type
 */
public class HashMap<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry<K, V>[] buckets;
    private int size;

    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public HashMap() {
        this.buckets = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Inserts a key-value pair. If the key exists, updates value.
     * Handles collisions via separate chaining. (linked list at bucket index)
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }

        // Check load factor and resize if necessary
        if ( (double) size / buckets.length >= LOAD_FACTOR ) {
            resize();
        }

        // Find the bucket index
        int index = getBucketIndex(key);

        // Traverse the chain to look for existing key
        Entry head = buckets[index];
        Entry current = head;

        while (current != null) {
            if (current.key.equals(key)) {
                // Key exists: Update value
                current.value = value;
                return;
            }

            current = current.next;
        }

        // Key not found
        Entry newEntry = new Entry(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    public V get (K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }

        int index = getBucketIndex(key);
        Entry<K, V> current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // Search miss
    }

    /**
     * Removes the mapping for the specified key.
     * @param key Specified key.
     */
    public void remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }

        int index = getBucketIndex(key);
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.key.equals(key)) {
                // Found node to remove
                if (previous == null) {
                    // Removing head of the chain
                    buckets[index] = current.next;
                } else {
                    // Removing from middle/end
                    previous.next = current.next;
                }
                size--;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    /**
     * A helper method that computes bucket index using hashCode.
     * Uses bitwise AND to ensure positive index within bounds.
     * @param key Specified key.
     * @return
     */
    private int getBucketIndex(K key) {
        // key.hashCode() can be negative
        // 0x7FFFFFFF clears the sign bit
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    /**
     * A helper method that doubles the capacity and rehashes all entries.
     */
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = (Entry<K, V>[]) new Entry[oldBuckets.length * 2 + 1];
        size = 0;

        for (Entry<K, V> head: oldBuckets) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}