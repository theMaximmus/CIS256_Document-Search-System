package csm.cis256;

/**
 * Quick sort implementation for arrays of strings (ascending, case-sensitive).
 */
public class QuickSort {

    public static void sort(String[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(String[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }

    private static int partition(String[] arr, int low, int high) {
        String pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(String[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
