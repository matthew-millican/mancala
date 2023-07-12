package state;
import board.Board;

import java.util.ArrayList;

public class StateTable {


    private final ArrayList<Board> buckets;


    public static final int SIZE = 109;

    private int size;


    public StateTable() {
        buckets = new ArrayList<>();

        // list of nulls
        for (int i = 0; i < SIZE; i++) {
            buckets.add(null);
        }
    }

    /**
     * Primarily used during testing to make sure the size counter is correct.
     * @return integer
     */
    public int size() {
        return this.size;
    }

    /**
     * Test helper function.
     * @return boolean
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Basic hash function using prime numbers for a string.
     * @param s string
     * @return integer hash code
     */
    private int hash(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = h * 31 + s.charAt(i);
        }
        // Return the absolute value in case there is overflow and h becomes negative.
        return Math.abs(h);
    }

    /**
     * Returns the basic array list.
     * @return list of states
     */
    public ArrayList<Board> getBucketsAsArray() {
        return this.buckets;
    }

    /**
     * Uses modulo arithmetic of the hash code to get the index to place the element in
     * @param key string
     * @return integer index
     */
    private int getBucketIndex(String key) {
        return hash(key) % SIZE;
    }


    /**
     * Insert game state into the hash table
     * @param state game board to insert
     * @return success of the insertion
     */
    public boolean insert(Board state) {
        int index = getBucketIndex(state.getKey());
        Board s = buckets.get(index);
        if (s != null) {
            // a game state with that index exists
            while (true) {
                // duplicate game state
                if (s.getKey().equals(state.getKey())) {
                    return false;
                }
                if (s.next == null) {
                    // chain in linked list
                    s.next = state;
                    this.size++;
                    return true;
                }
                else {
                    s = s.next;
                }
            }
        }
        // s == null
        buckets.set(index, state);
        this.size++;
        return true;
    }
}
