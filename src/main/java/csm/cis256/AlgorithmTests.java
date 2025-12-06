package csm.cis256;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTests {

    // Sorting Tests

    @Test
    void testMergeSort() {
        String[] input = {"c", "a", "b"};
        MergeSort.sort(input);
        assertArrayEquals(new String[]{"a", "b", "c"}, input);
    }

    @Test
    void testQuickSort() {
        String[] input = {"z", "x", "y"};
        QuickSort.sort(input);
        assertArrayEquals(new String[]{"x", "y", "z"}, input);
    }

    @Test
    void testSortingEdgeCases() {
        // Empty
        String[] empty = {};
        MergeSort.sort(empty);
        assertArrayEquals(new String[]{}, empty);

        // Single
        String[] single = {"a"};
        QuickSort.sort(single);
        assertArrayEquals(new String[]{"a"}, single);

        // Duplicates
        String[] dupes = {"b", "a", "b", "a"};
        MergeSort.sort(dupes);
        assertArrayEquals(new String[]{"a", "a", "b", "b"}, dupes);
    }

    // Searching Tests

    @Test
    void testLinearSearch() {
        String[] arr = {"apple", "banana", "cherry"};
        assertEquals(1, SearchAlgorithms.linearSearch(arr, "banana"));
        assertEquals(-1, SearchAlgorithms.linearSearch(arr, "dragonfruit"));
    }

    @Test
    void testBinarySearch() {
        String[] sorted = {"apple", "banana", "cherry", "date"};

        // Hits
        assertEquals(0, SearchAlgorithms.binarySearch(sorted, "apple"));
        assertEquals(2, SearchAlgorithms.binarySearch(sorted, "cherry"));

        // Miss
        assertEquals(-1, SearchAlgorithms.binarySearch(sorted, "zucchini"));
    }
}