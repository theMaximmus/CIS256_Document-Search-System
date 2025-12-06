package csm.cis256;

/**
 * An implementation of the Quick Sort algorithm for string arrays.
 */
public class QuickSort {

    /**
     * Sorts an array of strings in ascending lexicographical order.
     *
     * @param arr The array to sort.
     */
    public static void sort(String[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * The recursive core of the algorithm.
     *
     * @param arr   The array to sort.
     * @param low   The starting index of the segment.
     * @param high  The ending index of the segment.
     */
    private static void quickSort(String[] arr, int low, int high) {
        // Base case: If the segment is empty or has one element, it's sorted.
        if (low >= high) {
            return;
        }
        // Partition the array
        int pivotIndex = partition(arr, low, high);
        // Recursively sort the left sub-array (elements smaller than pivot)
        quickSort(arr, low, pivotIndex - 1);
        // Recursively sort the right sub-array (elements larger than pivot)
        quickSort(arr, pivotIndex + 1, high);
    }

    /**
     * Partitions the array segment around a pivot element.
     *
     * @param arr The array.
     * @param low Start index.
     * @param high End index (the pivot).
     * @return The final index of the pivot element.
     */
    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high]; // Choosing the last element as pivot
        int i = low - 1; // 'i' tracks the boundary of the "smaller than pivot" zone.

        for (int j = low; j < high; j++) {
            // Compare current element with pivot
            if (arr[j].compareTo(pivot) <= 0) {
                i++; // Expand the "smaller" zone
                swap(arr, i, j); // Move current element into that zone
            }
        }
        // Place the pivot in its correct sorted position (right after the "smaller" zone)
        swap(arr, i + 1, high);
        return i + 1; // Return the pivot's location
    }
    /**
     * Helper method to swap two elements in the array.
     */
    private static void swap(String[] arr, int i, int j) {
        if (i == j) {
            return; // Don't swap if indices are identical
        }
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
