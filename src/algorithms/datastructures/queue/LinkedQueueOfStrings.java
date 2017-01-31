package algorithms.datastructures.queue;

/**
 * Created by java on 1/30/17.
 */
public class LinkedQueueOfStrings {

    private Node first = null;
    private Node last = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enQueue(String s) {
        Node oldLast = last;
        last = new Node();
        last.item = s;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public String deQueue() {
        String s = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return s;
    }
}
