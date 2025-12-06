package csm.cis256;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PerformanceTests {

    private static long now() {
        return System.nanoTime();
    }

    public static void main(String[] args) {

        System.out.println("==== PERFORMANCE TESTS ====");
        testTreeInsertions();
        testSorting();
        testSearching();
    }

    public static void testTreeInsertions() {
        System.out.println("\n--- Tree Insertion Performance ---");

        int n = 20000;
        Integer[] data = new Integer[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;   // sorted on purpose
        }

        List<Integer> list = Arrays.asList(data);
        Collections.shuffle(list);
        list.toArray(data);
        BSTMap<Integer, Integer> bst = new BSTMap<>();
        AVLTreeMap<Integer, Integer> avl = new AVLTreeMap<>();
        RedBlackTreeMap<Integer, Integer> rb = new RedBlackTreeMap<>();

        long t1 = now();
        for (int x : data) {
            bst.put(x, x);
        }
        long t2 = now();
        System.out.println("BST insert time (ns): " + (t2 - t1));

        t1 = now();
        for (int x : data) {
            avl.put(x, x);
        }
        t2 = now();
        System.out.println("AVL insert time (ns): " + (t2 - t1));

        t1 = now();
        for (int x : data) {
            rb.put(x, x);
        }
        t2 = now();
        System.out.println("RB insert time (ns):  " + (t2 - t1));
    }

    public static void testSorting() {
        System.out.println("\n--- Sorting Performance ---");

        int n = 50000;
        String[] arr1 = new String[n];
        String[] arr2 = new String[n];

        for (int i = 0; i < n; i++) {
            String s = "" + (int)(Math.random() * 1000000);
            arr1[i] = s;
            arr2[i] = s;
        }

        long t1 = now();
        MergeSort.sort(arr1);
        long t2 = now();
        System.out.println("MergeSort time (ns): " + (t2 - t1));

        t1 = now();
        QuickSort.sort(arr2);
        t2 = now();
        System.out.println("QuickSort time (ns): " + (t2 - t1));
    }

    public static void testSearching() {
        System.out.println("\n--- Searching Performance ---");

        int n = 1000000;
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = "" + i;
        }

        String target = "" + (n - 1);

        long t1 = now();
        int idx1 = SearchAlgorithms.linearSearch(arr, target);
        long t2 = now();
        System.out.println("Linear search time (ns): " + (t2 - t1));
        System.out.println("Linear found at index: " + idx1);

        long t3 = now();
        int idx2 = SearchAlgorithms.binarySearch(arr, target);
        long t4 = now();
        System.out.println("Binary search time (ns): " + (t4 - t3));
        System.out.println("Binary found at index: " + idx2);
    }
}
