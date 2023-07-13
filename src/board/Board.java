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

    public int pickupSeeds(Row row, int index) {

        Pit pit = row.pit;
        int count = 1;
        while (pit != null) {
            if (index == count) {
                return pit.empty();
            }
            pit = pit.next;
        }

        return Mancala.ERR;
    }


    public void makeMove(int turn, int index) {

        if (turn == PLAYER) {

            int seeds = pickupSeeds(this.playerRow, index);
        }
        else if (turn == COMPUTER) {

            int seeds = pickupSeeds(this.computerRow, index);
        }
    }

    public Board() {

        this.playerRow = new Row(DEFAULT_STONES);

        this.computerRow = new Row(DEFAULT_STONES);


        evaluateKey();


        next = null;
    }




}
