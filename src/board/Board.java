package board;

public class Board {


    // some game board constants
    public static final int PITS = 6;

    public static final int DEFAULT_STONES = 4;


    // logical separation of rows
    public Row playerRow;

    public Row computerRow;


    public Board() {

        this.playerRow = new Row(DEFAULT_STONES);

        this.computerRow = new Row(DEFAULT_STONES);
    }

}
