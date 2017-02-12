package algorithms.sort;

import java.util.Arrays;

/**
 * NlgN
 * recursive
 */
public class MergeSort {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int k = 0;
        for (int i = lo; i <= mid; i++) {
            for (int j = mid + 1; j <= hi; ) {
                while (less(aux[i], aux[j]) || aux[i].equals(aux[j])) {
                    a[k] = aux[j];
                    j++;
                    k++;
                }
                a[k] = aux[i];
            }
        }

    }

    private static void sortLecture(Comparable[] a, Comparable[] aux, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortLecture(a, aux, lo, mid);
        sortLecture(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sortLecture(a, aux, 0, a.length - 1);
    }

    private static void mergeLecture(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

//        precondition checks
//        assert isSorted(a, lo, mid);
//        assert isSorted(a, mid+1, hi);

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i];
            }
        }
//        assert isSorted(a, lo, hi); // post condition that array is sorted after merge sort
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {

        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    private void sort(String[] original) {
        String[] copy = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }


    }
}
