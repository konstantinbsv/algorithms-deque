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


    // unit testing (required)
    public static void main(String[] args) {

    }

}