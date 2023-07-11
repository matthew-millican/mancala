package game;

import board.Board;
import board.Pit;
import board.Store;

public class Mancala {

    // the main mancala game board
    private static Board gameBoard;


    public static void main(String[] args) {


        gameBoard = new Board();

        printBoard();

    }

    /**
     * Print the game board to the terminal.
     */
    private static void printBoard() {


        // computer row

        Pit pit = gameBoard.computerRow.pit;

        // align with tabs
        System.out.print("\t");


        // print indices of pits
        int index = 6;
        while (index > 0) {
            System.out.print(index + "\t");

            index--;
        }

        System.out.println();

        printSeparators();

        System.out.print("\t");

        // print consecutive pits
        while (pit != null) {
            System.out.print(pit.getStoneCount() + "\t");
            pit = pit.next;
        }
        System.out.println();

        // print stores

        Store store = gameBoard.computerRow.store;
        System.out.print(store.getScore());

        System.out.print("\t\t\t\t\t\t\t");
        store = gameBoard.playerRow.store;
        System.out.print(store.getScore());

        System.out.println();
        System.out.print("\t");


        // player row

        pit = gameBoard.playerRow.pit;
        while (pit != null) {
            System.out.print(pit.getStoneCount() + "\t");
            pit = pit.next;
        }
        System.out.println();

        printSeparators();


        System.out.print("\t");


        // lastly, print indices of player pits
        index = 1;
        while (index <= Board.PITS) {
            System.out.print(index + "\t");


            index++;
        }

        System.out.println();

    }

    /**
     * Print a visible separator between pit indices and pit stone counts to the terminal.
     */
    private static void printSeparators() {

        System.out.print("\t");
        for (int i = 0; i < Board.PITS; i++) {
            System.out.print("-\t");
        }
        System.out.println();
    }
}
