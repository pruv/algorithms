package algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null; //
    private Node last = null;
    private Node linkingNode = null;
    private int size = 0;
    private byte firstInFirst = 0;
    private byte firstInLast = 0;

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.removeFirst();

//        System.out.println(deque.size());

        Iterator iter = deque.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    private class Node {
        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
        linkingNode = new Node();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldFirstNode = first;

        Node newFirstNode = new Node();
        newFirstNode.previous = null;

        newFirstNode.item = item;
        newFirstNode.next = oldFirstNode;
        if (oldFirstNode != null) {
            oldFirstNode.previous = newFirstNode;
        } else {
            newFirstNode.next = linkingNode;
        }
        first = newFirstNode;
        if (firstInFirst == 0) {
            linkingNode.previous = newFirstNode;
            firstInFirst++;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node oldLastNode = last;

        Node newLastNode = new Node();
        newLastNode.next = null;

        newLastNode.item = item;
        newLastNode.previous = oldLastNode;
        if (oldLastNode != null) {
            oldLastNode.next = newLastNode;
        } else {
            newLastNode.previous = linkingNode;
        }

        last = newLastNode;

        if (firstInLast == 0) {
            linkingNode.next = newLastNode;
            firstInLast++;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first == null || first.item == null) {
            first = linkingNode.next;
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.previous = null;
            if (item == null) {
                first = first.next;
                first.previous = null;
            }
        }
        size--;
        if (size == 0) {
            resetDeQueue();
        }
        return item;
    }

    private void resetDeQueue(){
        first = null;
        last = null;
        firstInFirst = 0;
        firstInLast = 0;
        linkingNode = new Node();
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (last == null || last.item == null) {
            last = linkingNode.previous;
        }
        Item item = last.item;
        last = last.previous;
        if (last != null) {
            last.next = null;
            if (item == null) {
                last = last.previous;
                last.next = null;
            }
        }
        size--;
        if (size == 0) {
            resetDeQueue();
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        Node currentNode;

        public DequeIterator() {
            if (first == null || first.item == null) {
                currentNode = linkingNode.next;
            } else {
                this.currentNode = first;
            }
        }

        @Override
        public boolean hasNext() {
            return currentNode != null && currentNode.item != null;
        }

        @Override
        public Item next() {
            if (currentNode == null || currentNode.item == null) {
                throw new NoSuchElementException();
            }
            Item item = currentNode.item;
            currentNode = currentNode.next;
            if (item == null) {
                item = currentNode.item;
                currentNode = currentNode.next;
            }
            if (currentNode != null && currentNode.item == null) {
                currentNode = currentNode.next;
            }
            return item;
        }
    }
}
