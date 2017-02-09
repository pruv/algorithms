package algorithms.sort;

/**
 * Created by java on 2/8/17.
 */
public class SelectionSort {

    public static void main(String[] args) {
        Integer[] a = {2, 5, 6, 1, 7, 3, 4, 0, 10, 9, 8};
        SelectionSort.sort(a);
        for (int x : a) {
            System.out.println(x);
        }
    }

    public static void sort(Comparable[] a) {
        int i = 0;
        while (i < a.length) {
            int small = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[small], a[j])) {
                    small = j;
                }
            }
            exchange(a, i, small);
            i++;
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
