import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.getInteger(args[0]); // number of strings to print

        RandomizedQueue randQueue = new RandomizedQueue();
        
        while(!StdIn.isEmpty()) {
            randQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randQueue.dequeue());
        }
    }
}
