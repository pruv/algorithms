import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntegerCount {

    public static void main(String[] args) {
        double sample1 = 17.099999999999998;
        double v = Math.round(sample1 * 10) / 10.0;

        List<Integer> nums = Arrays.asList(10, 3, 3, 1, 100, 25, 36, 3, 6, 7, 8, 3, 50, 500, 3, 500);

        List<Integer> nums2 = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        List<Integer> nums3 = Arrays.asList(1, 2, 2, 3, 4, 4, 4, 4, 5, 5, 5, 10, 30, 90);
        Collections.sort(nums);
        System.out.println(IntegerCount.countOccurances(nums3, 3));
    }

    /**
     * given an array returns count of target occurrences
     *
     * @param array
     * @param target
     * @return
     */
    public static int countOccurances(List<Integer> array, int target) {

        int start = 0;
        int end = array.size();
        int prevStart = -1;
        int prevEnd = -1;
        // find closest index to start at by eliminating half array every loop
        while (!(prevStart == start && prevEnd == end)) {
            prevStart = start;
            prevEnd = end;
            int middle = start + (end - start) / 2;
            Integer integer = array.get(middle);
            if (integer <= target) {
                start = middle;
            } else {
                end = middle;
            }
        }
        int middle = start + (end - start) / 2;
        int count = 0;
        if (array.get(middle) == target) {
            count = count + 1;
        }
        for (int i = middle - 1; (i >= 0 && array.get(i) >= target); i--) {
            if (array.get(i) == target) {
                count = count + 1;
            }
        }
        for (int i = middle + 1; (i < array.size() && array.get(i) <= target); i++) {
            if (array.get(i) == target) {
                count = count + 1;
            }
        }
        return count;
    }
}
