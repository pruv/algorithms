package algorithms.datastructures.stack;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by java on 1/31/17.
 */
public class LinkedStack<T> implements Iterable<T> {

    private Node first = null;

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    @Override
    public void forEach(Consumer action) {

    }

    private class ListIterator implements Iterator<T> {

        Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T t = currentNode.item;
            currentNode = currentNode.next;
            return t;
        }
    }

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T s) {
        Node n = new Node();
        n.item = s;
        n.next = first;
        first = n;
    }

    public T pop() {
        T s = first.item;
        first = first.next;
        return s;
    }
}
