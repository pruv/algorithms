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
    private int deQuIndex;

    public static void main(String[] args) {
        RandomizedQueue<String> rQu = new RandomizedQueue<>();
        rQu.enqueue("f1");
        rQu.enqueue("f2");
        System.out.println("deQud: " + rQu.dequeue());
        rQu.enqueue("f3");
        rQu.enqueue("f4");
        rQu.enqueue("f5");
        System.out.println("deQud: " + rQu.dequeue());
        rQu.enqueue("f6");
        Iterator<String> iter = rQu.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        rQu.dequeue();
        rQu.dequeue();
        rQu.enqueue("f1");
        System.out.println("********************");
        Iterator<String> iter2 = rQu.iterator();
        while (iter2.hasNext()) {
            System.out.println(iter2.next());
        }

        System.out.println("********************");
        rQu.enqueue("f8");
        rQu.enqueue("f9");
        rQu.enqueue("f10");
        rQu.enqueue("f11");

        System.out.println(rQu.sample());
        System.out.println(rQu.sample());
        System.out.println(rQu.sample());

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
        if (size == queue.length) {
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
        return queue[StdRandom.uniform(enQuIndex+1,deQuIndex+1)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        int index = deQuIndex;

        @Override
        public boolean hasNext() {
            return index > enQuIndex;
        }

        @Override
        public Item next() {
            if (index <= enQuIndex) {
                throw new NoSuchElementException();
            }
            return queue[index--];
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
