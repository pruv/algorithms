package algorithms.datastructures.stack;

/**
 * Created by java on 1/30/17.
 */
public class FixedCapacityStackOfStrings {

    private String[] stack;
    int n = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        stack = new String[capacity];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public void push(String s){
        stack[n++] = s;
    }

    // loitering: Holding a reference to an object when it is no longer needed.
    public String pop(){
        String s = stack[--n];
        stack[n] = null; // to take care of loitering
        return s;
    }
}
