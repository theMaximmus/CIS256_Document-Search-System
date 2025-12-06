package csm.cis256;

/**
 * A collection of standard search algorithms for string arrays.
 *
 * This class provides two primary search methods used to demonstrate the
 * performance differences between O(n) and O(log n) algorithms:
 * Linear Search: Simple, works on any list, but slow for large data.
 * Binary Search: Complex logic, requires a sorted list, but extremely fast.
 */
public class SearchAlgorithms {

    /**
     * Performs a Linear Search on an array of strings.
     *
     * Algorithm:
     * This method iterates through the array sequentially from index 0 to n-1.
     * It compares every element with the target string until a match is found
     * or the end of the array is reached.
     *
     * Complexity: O(n) - In the worst case (target not found or at the end),
     * we must visit every single element.
     *
     * @param arr The array to search (can be unsorted).
     * @param target The string to search for.
     * @return The index of the target if found; -1 otherwise.
     */
    public static int linearSearch(String[] arr, String target) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Performs a Binary Search on a sorted array of strings.
     *
     * Algorithm:
     * This uses a "Divide and Conquer" strategy. It inspects the middle element:
     * 1) If the middle is the target, we are done.
     * 2) If the target is lexicographically smaller, we discard the right half.
     * 3) If the target is larger, we discard the left half.
     * This repeats until the target is found or the search interval is empty.
     *
     * Complexity: O(log n) - The search space is cut in half in every iteration.
     * This makes it vastly superior to linear search for large datasets.
     *
     * @param arr The sorted array to search.
     * @param target The string to search for.
     * @return The index of the target if found; -1 otherwise.
     */
    public static int binarySearch(String[] arr, String target) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;
            String midVal = arr[mid];

            if (midVal == null) {
                return -1;
            }

            int cmp = midVal.compareTo(target);

            if (cmp == 0) {
                return mid;
            }
            else if (cmp < 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

}