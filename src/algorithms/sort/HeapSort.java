package algorithms.sort;

/**
 * Construction <= 2N
 * Comparisons and exchanegs: <=2NlgN
 *
 * IN place sort algorithm with worst case performance of NlgN
 *
 * advantages over
 *  - Merge Sort - no, linear extra space (in place possible , not practical)
 *  - Quick Sort - no, quadratic worst-case (NlgN worst-case quick sort possible, not practical)
 *
 *  HeapSort is optimal for both time and space
 *   - Not stable (Merge sort is stable)
 */
public class HeapSort {

    public void sort(Comparable[] a){
        int N = a.length;
        for(int k = N/2; k>=1; k--) {
            sink(a, k, N);
        }
        while (N > 1) {
            exchange(a, 1, N--);
            sink(a, 1, --N);
        }

    }

    private void sink(Comparable[] a, int k, int N) {

        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) {
                j++;
            }
            if (less(a, k, j)) {
                exchange(a, k, j);
            } else {
                break;
            }
            k = j;
        }
    }

    private boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private void exchange(Comparable[] a, int i, int j) {

        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
