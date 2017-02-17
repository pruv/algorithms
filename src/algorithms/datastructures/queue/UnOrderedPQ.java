package algorithms.datastructures.queue;

import java.util.Comparator;

/**
 * Created by java on 2/16/17.
 */
public class UnOrderedPQ<Key extends Comparable<Key>> {

    private Key[] a;
    private int N = 0;

    public UnOrderedPQ(int capacity) {
        a = (Key[]) new Object[capacity];
    }

    public void insert(Key v) {
        a[N++] = v;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 1; i < N; i++) {
            if (less(a[max], a[i])) {
                max = i;
            }
        }
        exchange(a, max, N-1);
        return a[N--];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key maxKey() {
        return null;
    }

    public int size() {
        return 0;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void exchange(Comparable[] a, int i, int j) {

        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
