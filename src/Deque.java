import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private static final int INITIAL_CAPACITY = 16;

    private int first = INITIAL_CAPACITY/2;
    private int last = INITIAL_CAPACITY/2;
    private int capacity = INITIAL_CAPACITY;

    private Object[] deque;

    // construct an empty deque
    public Deque() {
         deque = new Object[INITIAL_CAPACITY];
    }

    // is the deque empty?
    public boolean isEmpty() {
        return last == first;
    }

    // return the number of items on the deque
    public int size() {
        return last - first;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (first == 0) doubleCapacity();

        first--;
        deque[first] = item;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (last == capacity) doubleCapacity();

        deque[last] = item;
        last++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Item firstItem;
        firstItem = (Item) deque[first];
        deque[first] = null;
        first++;
        return firstItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Item lastItem;
        last--; // move back one spot to first item
        lastItem = (Item) deque[last]; // get first item
        deque[last] = null; // prevent loitering
        return lastItem;
    }

    private void doubleCapacity() {
        assert capacity >= last;

        int elementsInDeque = size();
        int centerOfDequeElements = (elementsInDeque)/2;

        capacity *= 2;
        Object[] newDeque = new Object[capacity];
        int newDequeCenter = capacity/2;

        for (int i = first; i < last; i++) {
            newDeque[newDequeCenter - centerOfDequeElements + (i- first)] = deque[i];
        }

        first = newDequeCenter - centerOfDequeElements;
        last = newDequeCenter - centerOfDequeElements + elementsInDeque;
        deque = newDeque;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int i;

        private DequeIterator() {
            i = first;
        }

        @Override
        public boolean hasNext() {
            return i < last;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return (Item) deque[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dequeUnderTest = new Deque<>();
        assert dequeUnderTest.isEmpty();

        System.out.println("dequeUnderTest.isEmpty() = " + dequeUnderTest.isEmpty());
        System.out.println("dequeUnderTest.size() = " + dequeUnderTest.size());

        for (int i = 1; i <= 400; i += 1) {
            if (i % 2 == 0) {
                dequeUnderTest.addFirst(i);
            } else {
                dequeUnderTest.addLast(i);
            }
        }

        for (Integer i: dequeUnderTest) {
            System.out.println("i = " + i);
        }

        System.out.println("dequeUnderTest.size() = " + dequeUnderTest.size());
        System.out.println("dequeUnderTest.removeFirst() = " + dequeUnderTest.removeFirst());
        System.out.println("dequeUnderTest.removeLast() = " + dequeUnderTest.removeLast());
        System.out.println("dequeUnderTest.isEmpty() = " + dequeUnderTest.isEmpty());
        System.out.println("dequeUnderTest.size() = " + dequeUnderTest.size());

        System.out.println("---Removing items---");
        for (Integer i: dequeUnderTest) {
            dequeUnderTest.removeLast();
            dequeUnderTest.removeFirst();
        }

        System.out.println("dequeUnderTest.isEmpty() = " + dequeUnderTest.isEmpty());
        System.out.println("dequeUnderTest.size() = " + dequeUnderTest.size());
    }

}

