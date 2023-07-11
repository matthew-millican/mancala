package board;

public class Store {

    // number of stones in a store
    private int score;


    /**
     * Simple getter.
     * @return integer
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Add a stone to the store.
     */
    public void increment() {
        this.score++;
    }


    public Store() {
        this.score = 0;
    }
}
