import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_CAPACITY = 16;

    private int last = 0;
    private int first = 0;
    private int capacity = INITIAL_CAPACITY;

    private Object[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = new Object[INITIAL_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return last == first;
    }

    // return the number of items on the randomized queue
    public int size() {
        assert last - first >= 0;

        return last - first;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (last == capacity) doubleCapacity();

        queue[last++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int itemIndex = StdRandom.uniform(first, last); // get random index
        Item dequeuedItem = (Item) queue[itemIndex]; // get item at that index
        queue[itemIndex] = queue[--last]; // move top item to location of dequeued item
        queue[last] = null; // remove item from top of queue

        return dequeuedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int itemIndex = StdRandom.uniform(first, last); // get random index
        return (Item) queue[itemIndex]; // return item at that index
    }

    private void doubleCapacity() {
        assert capacity >= last;

        int elementsInQueue = size();
        capacity *= 2;
        Object[] newQueue = new Object[capacity];
        for (int i = first; i < last; i++) {
            newQueue[i- first] = queue[i];
        }

        first = 0;
        last = first + elementsInQueue;
        queue = newQueue;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomQueIterator();
    }

    private class RandomQueIterator implements Iterator<Item> {
        private int i = 0;

        private RandomQueIterator() {
            StdRandom.shuffle(queue, 0, last);
        }

        @Override
        public boolean hasNext() {
            return i < last;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return (Item) queue[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queueUnderTest = new RandomizedQueue<>();

        System.out.println("queueUnderTest.isEmpty() = " + queueUnderTest.isEmpty());
        System.out.println("queueUnderTest.size() = " + queueUnderTest.size());

        System.out.println("---Filling queue---");
        for (int i = 1; i <= 50; i++) {
            queueUnderTest.enqueue(i);
        }
        System.out.println("Random samples");
        System.out.println("queueUnderTest.sample() = " + queueUnderTest.sample());
        System.out.println("queueUnderTest.sample() = " + queueUnderTest.sample());

        for (Integer i: queueUnderTest) {
            System.out.println("i = " + i);
        }
        System.out.println("queueUnderTest.isEmpty() = " + queueUnderTest.isEmpty());
        System.out.println("queueUnderTest.size() = " + queueUnderTest.size());

        System.out.println("---Dequeue-ing---");
        for (int i = 0; i < 50; i++) {
            System.out.println("dequeue = " + queueUnderTest.dequeue());
        }
        System.out.println("queueUnderTest.isEmpty() = " + queueUnderTest.isEmpty());
        System.out.println("queueUnderTest.size() = " + queueUnderTest.size());
    }

}