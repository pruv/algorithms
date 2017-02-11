package algorithms.sort;

/**
 * To sort a randomly ordered array with distinct keys, insertion sort uses
 * ~1/4 * N^2 comapres and ~1\4 * N^2 exchanges
 *
 *  Best Case: N-1
 *  Worst Case: ~1/2 * N^2
 *
 *  Best suited for partially sorted arrays (Inversion)
 *  Inversion is a pair of keys that are out of order
 *
 *  An array is called partially sorted if the number of inversions is <=cN (linear)
 *
 *  Ex 1: An array of size 10 appended to a sorted sub-array of size N
 *  Ex 2: An array of size N with only 10 entries out of place
 */
public class InsetionSort {

    public static void main(String[] args) {
        Integer[] a = {50, 2, 5, 6, 1, 7, 3, 4, 0, 10, 9, -1, 8};
        InsetionSort.sort(a);
        for (int x : a) {
            System.out.println(x);
        }
    }

    public static void sort(Comparable[] a) {
        int cnt = 1;
        for (int i = 1; i < a.length; i++) {
            Comparable x = a[i];
            for (int j = i - 1; j >= 0; j--) {
                cnt++;
                if (less(x, a[j])) {
                    exchange(a, j + 1, j);
                } else {
                    break;
                }
            }
        }
        System.out.println("Count: " + cnt);
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
