package algorithms.search;

/**
 * Created by java on 1/31/17.
 */
public class BinarySearch {

    private int[] sortedArray;
    private int loopCount = 0;

    public BinarySearch(int[] sortedArray) {
        this.sortedArray = sortedArray;
    }

    public static void main(String[] args){
        int[] x = {1, 10, 31, 42, 43, 56, 58, 64, 66, 71, 88, 89, 91, 93, 95, 99};
        BinarySearch bs = new BinarySearch(x);
        System.out.println(bs.search(64));
        System.out.println("Loop Count: "+ bs.getLoopCount());
    }

    public static int searchPerfect(int[] sortedList, int key){
        int lo =0;
        int hi = sortedList.length -1 ;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if(key < sortedList[mid]) {
                hi = mid-1;
            } else if (key > sortedList[mid]) {
                lo = mid +1;
            } else {
                return sortedList[mid];
            }
        }
        return -1;
    }

    public boolean search(int x){
        int lo =0;
        int hi = sortedArray.length -1  ;
        int searchIndex = 0;
        boolean done = false;
        boolean found = false;
        while(!done) {
            loopCount++;
            if(lo == hi && x == sortedArray[lo]) {
                done = true;
                found = true;
            }
            if(lo == hi && x != sortedArray[lo]) {
                done = true;
                found = false;
            }
            searchIndex = (lo + hi) / 2;
            if(x == sortedArray[searchIndex]) {
                done = true;
                found = true;
            } else if(x < sortedArray[searchIndex]) {
                hi = searchIndex-1;
            } else{
                lo = searchIndex+1;
            }
        }
        return found;
    }

    public int getLoopCount() {
        return loopCount;
    }
}
