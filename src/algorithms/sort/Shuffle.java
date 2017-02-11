package algorithms.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by java on 2/10/17.
 */
public class Shuffle {

    public static void shuffle(Comparable[] a) {

        for(int i=0; i< a.length; i++) {
            exchange(a, i, StdRandom.uniform(0, i));
        }
    }

    private static void exchange(Comparable[] a, int i, int j) {

        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
