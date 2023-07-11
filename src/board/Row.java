package board;

public class Row {


    public Store store;

    public Pit pit;


    /**
     * Initialise a row which contains a store (mancala) and some pits.
     * @param startingCount number of stones to go in each pit.
     */
    public Row(int startingCount) {

        this.store = new Store();

        initPits(startingCount);
    }


    /**
     * Initialise the consecutive pits as a linked list structure.
     * @param startingCount number of stones to go in each pit.
     */
    private void initPits(int startingCount) {
        this.pit = new Pit(startingCount);

        Pit curr = this.pit;

        for (int i = 1; i < Board.PITS; i++) {
            curr.next = new Pit(startingCount);

            curr = curr.next;
        }
    }
}
