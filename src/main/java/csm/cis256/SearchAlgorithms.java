package csm.cis256;

public class SearchAlgorithms {

    // ------------------------------------------------------------
    // linearSearch for Strings  (JUnit requires EXACT signature)
    // ------------------------------------------------------------
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

    // ------------------------------------------------------------
    // binarySearch for Strings  (JUnit requires EXACT signature)
    // ------------------------------------------------------------
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
