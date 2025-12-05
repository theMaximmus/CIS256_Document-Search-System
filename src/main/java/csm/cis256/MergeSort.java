package csm.cis256;

/**
 * Merge sort implementation for arrays of strings (ascending, case-sensitive).
 */
public class MergeSort {

    public static void sort(String[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        String[] temp = new String[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(String[] arr, String[] temp, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(String[] arr, String[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int idx = left; idx <= right; idx++) {
            arr[idx] = temp[idx];
        }
    }
}
