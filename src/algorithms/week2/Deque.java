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
        Deque<String> deque = new Deque<>();
        deque.addLast("l1");
        deque.addFirst("f1");
        deque.addLast("l2");
        deque.addFirst("f2");
        deque.addFirst("f3");
        deque.addLast("l3");
        deque.addFirst("f4");

        for (Object aDeque : deque) {
            System.out.println(aDeque);
        }

        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();

        System.out.println("*************");

        for (Object aDeque : deque) {
            System.out.println(aDeque);
        }

        deque.removeLast();

        System.out.println("*************");

        for (Object aDeque : deque) {
            System.out.println(aDeque);
        }

        deque.removeFirst();

        System.out.println("*************");

        for (Object aDeque : deque) {
            System.out.println(aDeque);
        }

        System.out.println("*************");
        System.out.println("Is Empty: "+ deque.isEmpty());

        deque.addLast("l1");
        System.out.println("Is Empty: " + deque.isEmpty());

        deque.addFirst("f1");
        deque.addLast("l2");
        deque.addFirst("f2");
        deque.addFirst("f3");
        deque.addLast("l3");
        deque.addFirst("f4");

        for (Object aDeque : deque) {
            System.out.println(aDeque);
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
        if(size == 0) {
            firstInFirst = 0;
            firstInLast = 0;
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
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
        if(size == 0) {
            firstInFirst = 0;
            firstInLast = 0;
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Item next() {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            Item item = currentNode.item;
            currentNode = currentNode.next;
            if (item == null) {
                item = currentNode.item;
                currentNode = currentNode.next;
            }
            return item;
        }
    }
}
