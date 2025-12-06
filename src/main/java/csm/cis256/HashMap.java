package csm.cis256;

/**
 * A simple HashMap implementation using Separate Chaining.
 * Supports put, get, remove, containsKey, size, isEmpty.
 * @param <K> The type of keys
 * @param <V> The type of mapped values
 */
public class HashMap<K, V> {

    /**
     * Internal node structure for the bucket linked lists.
     * Each entry holds a key-value pair and a pointer to the next entry in the chain.
     */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K k, V v) {
            key = k;
            value = v;
            next = null;
        }
    }

    // The array of buckets. Each slot holds the head of a linked list.
    private Entry<K, V>[] buckets;
    private int size;
    // Initial size of the bucket array.
    private static final int INITIAL_CAPACITY = 11;
    // The threshold at which we resize the table.
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Computes the index in the bucket array for a given key.
     *
     * We use the key's hashCode(), apply a bitwise mask (0x7FFFFFFF) to ensure
     * the result is positive, and then use the modulo operator (%) to wrap it
     * within the array bounds.
     *
     * @param key The key
     * @return The index of the bucket
     */
    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    /** Resize table when load factor is exceeded */
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] old = buckets;
        buckets = (Entry<K, V>[]) new Entry[old.length * 2 + 1];
        size = 0;

        for (Entry<K, V> head : old) {
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key Key with which the specified value is to be associated
     * @param value Value to be associated with the specified key
     */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key is null");

        if ((double) size / buckets.length >= LOAD_FACTOR) {
            resize();
        }

        int index = getBucketIndex(key);
        Entry<K, V> curr = buckets[index];

        // Update existing
        while (curr != null) {
            if (curr.key.equals(key)) {
                curr.value = value;
                return;
            }
            curr = curr.next;
        }

        // Insert new at head of chain
        Entry<K, V> e = new Entry<>(key, value);
        e.next = buckets[index];
        buckets[index] = e;
        size++;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) return null;

        int index = getBucketIndex(key);
        Entry<K, V> curr = buckets[index];

        while (curr != null) {
            if (curr.key.equals(key)) {
                return curr.value;
            }
            curr = curr.next;
        }

        return null;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * @param key Specified key
     */
    public void remove(K key) {
        if (key == null) return;

        int index = getBucketIndex(key);
        Entry<K, V> curr = buckets[index];
        Entry<K, V> prev = null;

        while (curr != null) {
            if (curr.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key Specified key
     */
    public boolean containsKey(K key) {
        if (key == null) return false;

        int index = getBucketIndex(key);
        Entry<K, V> curr = buckets[index];

        while (curr != null) {
            if (curr.key.equals(key)) return true;
            curr = curr.next;
        }

        return false;
    }

    /** Number of key/value pairs */
    public int size() {
        return size;
    }

    /** True if table has no entries */
    public boolean isEmpty() {
        return size == 0;
    }
}
