package board;

public class Pit {


    // number of stones in the pit
    private int stoneCount;

    // index of the pit
    public final int index;

    // stores the next consecutive pit
    public Pit next;


    /**
     * Retrieve the number of stones in the pit.
     * @return an integer.
     */
    public int getStoneCount() {
        return this.stoneCount;
    }

    /**
     * Empty a pit which zeroes the stone count and returns the original value.
     * @return an integer.
     */
    public int empty() {
        int temp = this.stoneCount;
        this.stoneCount = 0;

        return temp;
    }


    /**
     * Add a stone to the pit.
     */
    public boolean increment() {

        boolean empty = this.stoneCount == 0;

        this.stoneCount++;

        return empty;
    }


    public Pit(int startingCount, int index) {

        this.stoneCount = startingCount;
        this.index = index;
        this.next = null;
    }
}
