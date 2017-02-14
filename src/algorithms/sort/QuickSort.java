package algorithms.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by java on 2/13/17.
 */
public class QuickSort {


    public static void main(String[] args) {
        Integer[] a = {100, -3, 20, 50, 10, 11, 3, 0, 13, 5, 6, 70, -8};
        QuickSort.sort(a);
        for (Integer i : a) {
            System.out.println(i);
        }
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a); // shuffle is needed to make sure performance is good
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
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
