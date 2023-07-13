package tests;
import static org.junit.jupiter.api.Assertions.*;

import board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.StateTable;

public class StateTableTest {

    StateTable table;


    @BeforeEach
    public void setup() {
        table = new StateTable();
    }

    @Test
    public void addOne() {

        Board board = new Board();

        assertTrue(table.isEmpty());

        assertTrue(table.insert(board));

        assertFalse(table.isEmpty());

        assertEquals(1, table.size());
    }

    @Test
    public void addMultiple() {

        Board board = new Board();

        assertTrue(table.isEmpty());

        assertTrue(table.insert(board));


        for (int i = 0; i < 10; i++) {

            assertFalse(table.insert(board));

        }

        assertFalse(table.isEmpty());
    }
}
