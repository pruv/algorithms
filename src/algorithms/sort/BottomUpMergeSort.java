package algorithms.sort;

import java.util.Comparator;

/**
 * similar to original merge sort except that no recursion
 */
public class BottomUpMergeSort {

    private static Comparable[] aux;

    private static void merge(Comparable[] a, Comparator comparator, int lo, int mid, int hi) {

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int k = 0;
        for (int i = lo; i <= mid; i++) {
            for (int j = mid + 1; j <= hi; ) {
                while (less(comparator, aux[i], aux[j]) || aux[i].equals(aux[j])) {
                    a[k] = aux[j];
                    j++;
                    k++;
                }
                a[k] = aux[i];
            }
        }
    }

    public static void sort(Comparable[] a ,Comparator comparator) {
        int n = a.length;
        aux = new Comparable[a.length];

        int sz = 1;
        for (int i = sz; i < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo = lo + sz + sz) {
                merge(a, comparator, lo, lo + sz - 1, Math.max(lo + sz + sz - 1, n - 1));
            }
        }
    }

    private static boolean less(Comparator comparator, Comparable a, Comparable b) {
        return comparator.compare(a, b) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {

        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
