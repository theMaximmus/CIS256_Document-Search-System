package csm.cis256;

/**
 * An implementation of the Merge Sort algorithm for string arrays.
 */
public class MergeSort {

    /**
     * Sorts an array of strings in ascending lexicographical order.
     *
     * @param arr The array to sort.
     */
    public static void sort(String[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        String[] temp = new String[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    /**
     * Recursive helper method that splits the array range.
     *
     * @param arr The primary array.
     * @param temp The helper array for merging.
     * @param left The starting index of the current range.
     * @param right The ending index of the current range.
     */
    private static void mergeSort(String[] arr, String[] temp, int left, int right) {

        // Base case: If the range has 1 or 0 elements, stop splitting.
        if (left >= right) {
            return;
        }
        // Calculate midpoint to split the range in half
        int mid = left + (right - left) / 2;
        // Recursive Step: Sort the left half
        mergeSort(arr, temp, left, mid);
        // Recursive Step: Sort the right half
        mergeSort(arr, temp, mid + 1, right);
        // Merge Step: Combine the two sorted halves
        merge(arr, temp, left, mid, right);
    }

    /**
     * Merges two sorted sub-arrays into a single sorted segment.
     *
     * Sub-array 1: arr[left...mid]
     * Sub-array 2: arr[mid+1...right]
     *
     * @param arr The primary array containing the two sub-arrays.
     * @param temp The helper array used for temporary storage.
     * @param left Start index of the first sub-array.
     * @param mid End index of the first sub-array.
     * @param right End index of the second sub-array.
     */
    private static void merge(String[] arr, String[] temp, int left, int mid, int right) {
        int i = left; // Pointer for left sub-array
        int j = mid + 1; // Pointer for right sub-array
        int k = left; // Pointer for temp array

        // Compare elements from both sub-arrays and copy the smaller one to temp
        while (i <= mid && j <= right) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // Copy any remaining elements from the left sub-array
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // Copy remaining elements from the right sub-array
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // Copy the sorted segment from temp back to the original array
        for (int idx = left; idx <= right; idx++) {
            arr[idx] = temp[idx];
        }
    }
}
