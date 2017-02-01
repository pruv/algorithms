package algorithms.performance;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by java on 1/31/17.
 *
 * Observational Model:
 * Running time as function of number of inputs n
 * Power law
 * T(n) = a*n^b,  where b is slope
 *
 * Mathematical Model: John Knuth
 * Total running time = (sum of cost) * (frequency of all operations)
 *  - Need to analyze each program to indentify set of operations
 *  - Cost depends on machine, compiler
 *  - Frequency depends on algorithm and input data
 */
public class ThreeSumProblem {

    private int[] numbers;

    public static void main(String[] args){
        int[] array = {10, -20, 10, 30, -10};
        Stopwatch stopwatch = new Stopwatch();
        ThreeSumProblem tsp = new ThreeSumProblem(array);
        System.out.println(tsp.test());
        stopwatch.elapsedTime();
    }

    public ThreeSumProblem(int[] numbers) {
        this.numbers = numbers;
    }

    public int test() {
        int cnt = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                for(int k=j+1;k<numbers.length;k++)
                if (0 == numbers[i] + numbers[j] + numbers[k]) {
                    ++cnt;
                }
            }
        }
        return cnt;
    }
}
