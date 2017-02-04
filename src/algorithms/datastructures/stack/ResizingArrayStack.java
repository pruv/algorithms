package algorithms.datastructures.stack;

import java.util.Iterator;

/**
 * Created by java on 1/31/17.
 */
public class ResizingArrayStack<T> implements Iterable<T> {

    private T[] stack;
    int n;

    public ResizingArrayStack() {
        stack = (T[])new Object[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(T s) {
        stack[n++] = s;
        if (n == stack.length) {
            resize(stack.length * 2);
        }
    }

    private void resize(int capacity) {
        T[] temp = (T[])new Object[capacity];
        for (int i = 0; i < stack.length; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public T pop() {
        T s = stack[--n];
        stack[n] = null;
        if(n>0 && n == stack.length/4) {
            resize(stack.length/2);
        }
        return s;
    }

    @Override
    public Iterator<T> iterator() {
        return new ResizingArrayIterator();
    }

    private class ResizingArrayIterator implements Iterator<T> {

        int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public T next() {
            return stack[--i];
        }
    }
}
