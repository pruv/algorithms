package algorithms.datastructures.stack;

/**
 * If you increase size by 1 every time you do push it is not feasible as
 * it take 1 + 2 + 3 +......+ N ~ N^2/2 (quadratic)
 * <p>
 * well know approach is repeated doubling; when ever array is full double the size of array.
 *
 * Performance: (Amortized analysis) Average running time per operation over a worst case sequence of operations
 *
 * Trade-offs Linked List Vs Array implementation
 *  Linked Lists:
 *   - Every operation takes Constant time in worst case scenario
 *   - Extra time and space to deal with links
 *
 *  Resizing Array:
 *   - Every operation takes Constant amortized time
 *   - efficient usage of memory (less wasted memory)
 */
public class ResizingArrayOfStrings {

    private String[] stack;
    int n;

    public ResizingArrayOfStrings() {
        stack = new String[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String s) {
        stack[n++] = s;
        if (n == stack.length) {
            resize(stack.length * 2);
        }
    }

    private void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = 0; i < stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public String pop() {
        String s = stack[--n];
        stack[n] = null;
        if(n>0 && n == stack.length/4) {
            resize(stack.length/2);
        }
        return s;
    }
}
