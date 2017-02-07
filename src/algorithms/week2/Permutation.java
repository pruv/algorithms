package algorithms.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Scanner;

/**
 * Created by java on 2/4/17.
 */
public class Permutation {

    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);

        if (k > 0) {
            String[] n = StdIn.readAllStrings();
            String[] res = new String[k];

            for (int i = 0; i < k; i++) {
                res[i] = n[i];
            }

            for (int i = k; i < n.length; i++) {
                int random = StdRandom.uniform(0, i);
                if (random < k) {
                    res[random] = n[i];
                }
            }

            for (String b : res) {
                StdOut.println(b);
            }
        }
    }
}
