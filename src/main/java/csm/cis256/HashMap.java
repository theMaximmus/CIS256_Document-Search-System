package csm.cis256;

/**
 * A simple HashMap implementation using Separate Chaining.
 * Supports put, get, remove, containsKey, size, isEmpty.
 */
public class HashMap<K, V> {

    // Node for the bucket linked lists
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

    private Entry<K, V>[] buckets;
    private int size;

    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    /** Compute which bucket a key belongs in */
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

    /** Insert or update key/value */
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

    /** Look up a key */
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

    /** Remove an entry by key */
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

    /** ✔ Required by tests */
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
