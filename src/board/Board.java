package board;

import game.Mancala;

public class Board {


    // some game board constants
    public static final int PITS = 6;

    public static final int DEFAULT_STONES = 4;

    public static final int PLAYER = 1;

    public static final int COMPUTER = 2;


    // logical separation of rows
    public Row playerRow;

    public Row computerRow;


    public Board next;

    private String key;
    
    
    public String getKey() {
        
        return this.key;
    }

    /**
     * The game is finished when one or both of the players pits are empty
     * @return true or false
     */
    public boolean isGameOver() {
        return this.playerRow.isEmpty() || this.computerRow.isEmpty();
    }

    public void evaluateKey() {
        // use pit stone counts and store to create string key

        Pit player = this.playerRow.pit;
        Pit computer = this.computerRow.pit;

        StringBuilder sb = new StringBuilder();
        while (player != null) {
            sb.append(player.getStoneCount());
            sb.append(computer.getStoneCount());

            player = player.next;
            computer = computer.next;
        }

        // now check stores

        sb.append(this.playerRow.store.getScore());
        sb.append(this.computerRow.store.getScore());

        this.key = sb.toString();

    }

    /**
     * Pick up seeds from a given pit.
     * @param row current row
     * @param index pit index to empty
     * @return the number of seeds in that pit
     */
    public int pickupSeeds(Row row, int index) {

        Pit pit = row.pit;
        int count = 1;
        while (pit != null) {
            if (index == count) {
                return pit.empty();
            }
            count++;
            pit = pit.next;
        }

        return Mancala.ERR;
    }

    public void sowSeeds(int turn, int index, int seeds) {

        Row row = turn == PLAYER ? this.playerRow : this.computerRow;
        Row opponent = turn == PLAYER ? this.computerRow : this.playerRow;


        // monitors which row of seeds we're sowing into
        int control = 0;

        Pit pit = row.pit;
        Pit opPit = opponent.pit;

        // move to correct index (+1 of emptied pit)
        int count = 1;
        while (pit != null) {
            if (index == count) {
                // move one more time
                pit = pit.next;
                break;
            }
            pit = pit.next;
            count++;
        }
        // sow seeds in consecutive pits

        while (seeds > 0) {
            // current person's row
            if (control == 0) {
                if (pit != null) {
                    pit.increment();
                    pit = pit.next;
                }
                else {
                    // reached the end of the row
                    row.store.increment();

                    // now we circle round to the opponent's row so flip the control
                    control = 1;

                    // reset pit
                    pit = row.pit;
                }
            }
            else {
                if (opPit != null) {
                    opPit.increment();
                    opPit = opPit.next;
                }
                else {
                    // flip control
                    control = 0;

                    // reset pit
                    opPit = opponent.pit;
                }
            }


            seeds--;
        }
    }

    /**
     * Perform a single move by emptying a pit and sowing into consecutive pits.
     * @param turn defines who's turn it is.
     * @param index index of the pit to be emptied.
     */
    public boolean makeMove(int turn, int index) {

        if (turn == PLAYER) {
            // collect seeds from a pit and sow into the consecutive pits
            int seeds = pickupSeeds(this.playerRow, index);

            if (seeds == 0) {
                System.out.println("Invalid pit. That pit is empty.");
                return false;
            }
            sowSeeds(PLAYER, index, seeds);
        }
        else if (turn == COMPUTER) {
            int seeds = pickupSeeds(this.computerRow, index);

            if (seeds == 0) {
                return false;
            }
            sowSeeds(COMPUTER, index, seeds);
        }

        return true;
    }

    public Board() {

        this.playerRow = new Row(DEFAULT_STONES);
        this.computerRow = new Row(DEFAULT_STONES);

        evaluateKey();
        next = null;
    }




}
