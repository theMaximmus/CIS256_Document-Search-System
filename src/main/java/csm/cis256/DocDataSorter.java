package csm.cis256;

public class DocDataSorter {
    public static void sort(DocData[] arr) {
        if (arr == null || arr.length < 2) return;
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(DocData[] arr, int low, int high) {
        if (low >= high) return;
        int pivotIndex = partition(arr, low, high);
        sort(arr, low, pivotIndex - 1);
        sort(arr, pivotIndex + 1, high);
    }

    private static int partition(DocData[] arr, int low, int high) {
        DocData pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            // (high freq < low freq)
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                DocData temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        DocData temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}