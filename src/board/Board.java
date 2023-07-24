package board;

import game.Mancala;
import game.Response;

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
     * Transform the index in one row to the index of the opposite row.
     * @param index
     * @return
     */
    public int transformIndex(int index) {

        return (Math.abs(PITS - index) + 1);
    }

    /**
     * Pick up seeds from a given pit.
     * @param row current row
     * @param index pit index to empty
     * @return the number of seeds in that pit
     */
    public int pickupSeeds(Row row, int index) {

        Pit pit = row.pit;
        while (pit != null) {
            if (index == pit.index) {
                return pit.empty();
            }
            pit = pit.next;
        }

        return Mancala.ERR;
    }

    /**
     * Sow seeds picked up from a pit in the proceeding pits next to it
     * @param turn defines which player of the game is making a move
     * @param index index of the emptied pit
     * @param seeds number of seeds picked up
     * @return
     */
    public Response sowSeeds(int turn, int index, int seeds) {

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

        boolean lastSeed;
        while (seeds > 0) {
            lastSeed = seeds == 1;
            // current person's row
            if (control == 0) {
                if (pit != null) {
                    // place last seed in an empty pit
                    if (pit.increment() && lastSeed) {
                        // capture opposite seeds from opponent
                        int capturedSeeds = pickupSeeds(opponent, transformIndex(pit.index));
                        row.store.add(capturedSeeds);
                        return new Response("Well done. You stole some seeds.", false);
                    }
                    pit = pit.next;
                }
                else {
                    // reached the end of the row
                    row.store.increment();

                    // detect an extra go
                    if (lastSeed) {
                        return new Response("Well done. You can go again.", true);
                    }

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

        return new Response("", false);
    }

    /**
     * Perform a single move by emptying a pit and sowing into consecutive pits.
     * @param turn defines who's turn it is.
     * @param index index of the pit to be emptied.
     */
    public Response makeMove(int turn, int index) {

        // player turn
        if (turn == PLAYER) {
            int seeds = pickupSeeds(this.playerRow, index);

            if (seeds == 0) {
                return new Response("Invalid pit. That pit is empty.", true);
            }
            // TODO: seeds == -1 something has gone wrong with the game.
            return sowSeeds(PLAYER, index, seeds);
        }
        // computer turn
        int seeds = pickupSeeds(this.computerRow, index);
        return sowSeeds(COMPUTER, index, seeds);
    }

    public Board() {

        this.playerRow = new Row(DEFAULT_STONES);
        this.computerRow = new Row(DEFAULT_STONES);

        evaluateKey();
        next = null;
    }




}
