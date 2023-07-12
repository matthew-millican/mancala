package board;

public class Board {


    // some game board constants
    public static final int PITS = 6;

    public static final int DEFAULT_STONES = 4;


    // logical separation of rows
    public Row playerRow;

    public Row computerRow;


    public Board next;

    private String key;
    
    
    public String getKey() {
        
        return this.key;
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

        System.out.println(this.key);

    }

    public Board() {

        this.playerRow = new Row(DEFAULT_STONES);

        this.computerRow = new Row(DEFAULT_STONES);


        next = null;
    }




}
