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
     * Check if the row of pits does not contain anymore pits
     * @return true or false
     */
    public boolean isEmpty() {
        boolean isEmpty = true;

        Pit curr = this.pit;
        while (curr != null) {

            isEmpty = isEmpty && curr.getStoneCount() == 0;


            curr = curr.next;
        }

        return isEmpty;

    }

    /**
     * Return the pit denoted at the specified index.s
     * @param index index of the pit.
     * @return pit
     */
    public Pit getPit(int index) {

        int count = 1;
        Pit pit = this.pit;

        while (pit != null) {

            if (count == index) {
                return pit;
            }
            pit = pit.next;
            count++;
        }

        return null;
    }


    /**
     * Initialise the consecutive pits as a linked list structure.
     * @param startingCount number of stones to go in each pit.
     */
    private void initPits(int startingCount) {
        this.pit = new Pit(startingCount, 1);

        Pit curr = this.pit;

        for (int i = 1; i < Board.PITS; i++) {
            curr.next = new Pit(startingCount, i+1);

            curr = curr.next;
        }
    }
}
