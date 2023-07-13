package game;

import board.Board;
import board.Pit;
import board.Store;
import computer.Computer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mancala {

    // the main mancala game board
    private static Board gameBoard;


    private static Computer computer;


    public static final int ERR = -1;


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));


        gameBoard = new Board();

        computer = new Computer(gameBoard);


        boolean gameOver = false;


        while (!gameOver) {
            
            printBoard();
            System.out.println("Player turn:");

            // player move

            String move = reader.readLine();

            int index = validateMove(move);

            if (index == ERR) {
                System.out.println("Invalid pit. Pits are denoted between 1 and 6. ");
                
                continue;
            }

            // computer move

            computer.reset(gameBoard);

            gameOver = gameBoard.isGameOver();


        }

        reader.close();



    }


    private static int validateMove(String move) {

        try {
            int index = Integer.parseInt(move);

            if (index < 1 || index > 6) {
                throw new NumberFormatException();
            }
            return index;
        }
        catch(NumberFormatException nfe) {

            return ERR;

        }


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
