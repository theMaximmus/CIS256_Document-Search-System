package csm.cis256;

/**
 * A specialized sorting utility for DocData objects.
 */
public class DocDataSorter {
    /**
     * Sorts an array of DocData objects in place.
     * @param arr The array to sort.
     */
    public static void sort(DocData[] arr) {
        // Sorting is unnecessary for null or single-item arrays.
        if (arr == null || arr.length < 2) return;
        sort(arr, 0, arr.length - 1);
    }

    /**
     * Standard QuickSort recursive function.
     *
     * @param arr The array.
     * @param low Starting index.
     * @param high Ending index.
     */
    private static void sort(DocData[] arr, int low, int high) {
        if (low >= high) return;
        // Partition the array around a pivot
        int pivotIndex = partition(arr, low, high);
        // Recursively sort the elements before and after the pivot
        sort(arr, low, pivotIndex - 1);
        sort(arr, pivotIndex + 1, high);
    }

    /**
     * Partitions the array segment using the last element as the pivot.
     *
     * @param arr The array.
     * @param low The start of the segment.
     * @param high The end of the segment (pivot).
     * @return The final index of the pivot.
     */
    private static int partition(DocData[] arr, int low, int high) {
        DocData pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // Checks if arr[j] should come before the pivot.
            // Because of DocData's compareTo(), this handles the descending order.
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                // Swap arr[i] and arr[j]
                DocData temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Place pivot in its correct position
        DocData temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}