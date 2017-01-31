package algorithms.datastructures.stack;

/**
 * Created by java on 1/31/17.
 */
public class LinkedStack<T> {

    private Node first = null;

    private class Node {
        T item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void push(T s){
        Node n = new Node();
        n.item = s;
        n.next = first;
        first = n;
    }

    public T pop(){
        T s = first.item;
        first = first.next;
        return s;
    }
}
