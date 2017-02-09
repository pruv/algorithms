package algorithms.sort;

/**
 * simialr to original merge sort except that no recursion
 */
public class BottomUpMergeSort {

    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {

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

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[a.length];

        int sz = 1;
        for (int i = sz; i < n; sz = sz + sz) {
            for (int lo = 0; lo < n - sz; lo = lo + sz + sz) {
                merge(a, lo, lo + sz - 1, Math.max(lo + sz + sz - 1, n - 1));
            }
        }
    }

    private static boolean less(Comparable a, Comparable b) {
        // compare and return
        return true;
    }


}
