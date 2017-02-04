import java.util.Iterator;

/**
 * Created by java on 2/4/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {


    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    }
}
