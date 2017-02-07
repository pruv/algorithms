package algorithms.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by java on 2/4/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] queue;
    private int enQuIndex = 0;
    private int deQuIndex = 0;

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(34);
        rq.isEmpty();
        rq.enqueue(33);
        rq.enqueue(41);
        rq.enqueue(4);
        rq.enqueue(37);
        rq.enqueue(35);
        rq.isEmpty();
        rq.dequeue();
        rq.enqueue(39);
        rq.enqueue(24);
        rq.enqueue(30);

        Iterator iterator = rq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        enQuIndex = queue.length - 1;
        deQuIndex = queue.length - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        queue[enQuIndex] = item;
        enQuIndex--;
        size++;
        if (size == queue.length || enQuIndex == -1) {
            resize(queue.length * 2);
        }
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item item = queue[deQuIndex];
        queue[deQuIndex] = null;
        deQuIndex--;
        size--;
        if (size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return queue[StdRandom.uniform(enQuIndex + 1, deQuIndex + 1)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        int index = 0;
        private Item[] elems;

        public RandomizedQueueIterator() {
            elems = (Item[]) new Object[size];
            int j = 0;
            for (int i = deQuIndex; i > enQuIndex; i--) {
                elems[j] = queue[i];
                j++;
            }
            StdRandom.shuffle(elems);
        }

        @Override
        public boolean hasNext() {
            return index < elems.length;
        }

        @Override
        public Item next() {
            if (index >= elems.length) {
                throw new NoSuchElementException();
            }
            return elems[index++];
        }
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        int tempIndex = temp.length - 1;
        while (deQuIndex >= 0 && queue[deQuIndex] != null) {
            temp[tempIndex--] = queue[deQuIndex--];
        }
        enQuIndex = tempIndex;
        deQuIndex = temp.length - 1;
        queue = temp;
    }
}
