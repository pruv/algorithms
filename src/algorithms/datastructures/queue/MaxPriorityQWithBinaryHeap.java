package algorithms.datastructures.queue;

/**
 * lgN for both insert and deleteMax
 *
 * Betterment's available:
 *  - by changing to D-ary implementation
 *  - Fibonacci Heap (Insert: constant time, deleteMax: lgN on average over all operations) but too complex in practice
 *
 */
public class MaxPriorityQWithBinaryHeap<Key extends Comparable<Key>> {

    private Key[] a;
    private int N = 0;

    public MaxPriorityQWithBinaryHeap(int capacity) {

        a = (Key[])new Comparable[capacity+1]; // as we start from 1 for calculations of parent and child (2k & k/2)
    }

    public void insert(Key v) {
        a[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = a[1];
        exchange(1, N--);
        a[N + 1] = null;
        sink(1);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {

        while (2 * k <= N) {
            int j = 2 * k;
            if (j  < N && less(j, j + 1)) {
                j++;
            }
            if (less(k, j)) {
                exchange(k, j);
            } else {
                break;
            }
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private void exchange(int i, int j) {

        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public Key maxKey() {
        return a[1];
    }

    public int size() {
        return N;
    }
}
