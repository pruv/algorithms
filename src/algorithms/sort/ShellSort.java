package algorithms.sort;

/**
 * The worst case number of compares used by shellsort with the 3*x+1 increments id O(N^(3/2))
 */
public class ShellSort {

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j + h]); j -= h) {
                    exchange(a, j, j - h);

                }
            }
            h = h / 3;
        }

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

