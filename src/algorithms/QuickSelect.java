package algorithms;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Given an array of N items find Kth largest element
 * Ex: Min(k=0) Max(k=N-1) Median(k=N/2)
 * <p>
 * solution: sort and find kth element
 * by using quick sort partitioning: after every partition if k is lesser or greater than j,
 * just sort lower or upper half of the array and find k.
 *
 * performance: takes linear time on average
 * N + N/2 + N/4 + ....1 = 2N
 *
 * worst case: 1/2N^2 (but highly unlikely as we shuffle it randomly always)
 */
public class QuickSelect {


    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0;
        int hi = a.length - 1;

        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo + 1;
        int j = hi;
        Comparable k = a[lo];
        while (i <= j) {
            if (!less(a[i], k)) { // find item on left to swap
                while (j >= i) {
                    if (less(a[j], k)) { //find item on right to swap
                        exchange(a, i, j);
                        break;
                    }
                    j--;
                }
            }
            i++;
        }
        exchange(a, j, lo); // swap with partitioning item
        return j; // return index of item now known to be in place
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void exchange(Comparable[] a, int i, int j) {

        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
