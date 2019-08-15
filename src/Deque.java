import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int INITIAL_CAPACITY = 16;

    private int top = INITIAL_CAPACITY/2;
    private int bottom = INITIAL_CAPACITY/2;
    private int capacity = INITIAL_CAPACITY;

    private Object[] deque;

    // construct an empty deque
    public Deque() {
         deque = new Object[INITIAL_CAPACITY];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return top == bottom;
    }

    // return the number of items on the deque
    public int size() {
        return top - bottom;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (top == capacity) doubleCapacity();

        deque[top] = item;
        top++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (bottom == 0) doubleCapacity();

        bottom--;
        deque[bottom] = item;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item firstItem;
        top--; // move back one spot to first item
        firstItem = (Item) deque[top]; // get first item
        deque[top] = null; // prevent loitering
        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item lastItem;
        lastItem = (Item) deque[bottom];
        deque[bottom] = null;
        bottom++;
        return lastItem;
    }

    private void doubleCapacity() {
        assert capacity >= top;

        int elementsInDeque = size();
        int centerOfDequeElements = (elementsInDeque)/2;

        capacity *= 2;
        Object[] newDeque = new Object[capacity];
        int newDequeCenter = capacity/2;

        for (int i = bottom; i < top; i++) {
            newDeque[newDequeCenter - centerOfDequeElements + (i-bottom)] = deque[i];
        }

        bottom = newDequeCenter - centerOfDequeElements;
        top    = newDequeCenter - centerOfDequeElements + elementsInDeque;
        deque = newDeque;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {


    }

    // unit testing (required)
    public static void main(String[] args) {


}

