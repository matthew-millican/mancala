package computer;

import board.Board;
import state.StateQueue;
import state.StateTable;

public class Computer {


    private StateTable states;

    private StateQueue tree;




    public Computer(Board gameBoard) {

        states = new StateTable();
        tree = new StateQueue();

        states.insert(gameBoard);
        tree.push(gameBoard);

    }

    public void reset(Board gameBoard) {
        states = new StateTable();

        states.insert(gameBoard);
        tree.push(gameBoard);
    }
}
