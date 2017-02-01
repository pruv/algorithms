package algorithms.performance;

import algorithms.search.BinarySearch;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by java on 1/31/17.
 * - Sort the N distinct numbers
 * - For each pair of a[i], a[j], do binary search to
 * find if -(a[i]+a[j]) exists in the list
 *
 *
 *  Performance:
 *  Order of growth is:
 *  - N^2 for initial sort using insertion sort
 *  - N^2lgN with binary search (vs N^3 with out binary search)
 */
public class ThreeSumWithBinarySearch {

    private int[] numbers;

    public static void main(String[] args) {
        int[] array = {10, -20, 10, 30, -10};
        Stopwatch stopwatch = new Stopwatch();
        ThreeSumWithBinarySearch tsp = new ThreeSumWithBinarySearch(array);
        System.out.println(tsp.getCountOfTriplets());
        stopwatch.elapsedTime();
    }

    public ThreeSumWithBinarySearch(int[] numbers) {
        this.numbers = numbers;
    }

    public int getCountOfTriplets() {
        // sort the array before continuing, in order to take advantage of binary search
        int cnt = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int sum = numbers[i] + numbers[j];
                int thirdNumber = BinarySearch.searchPerfect(numbers, -sum);
                if (thirdNumber != -1)
                    if ((numbers[i] < numbers[j]) && (numbers[j] < thirdNumber)) { // to avoid double counting
                        cnt++;
                    }
            }
        }
        return cnt;
    }
}
