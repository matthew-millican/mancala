package tests;
import static org.junit.jupiter.api.Assertions.*;

import board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.StateQueue;

public class StateQueueTest {

    StateQueue queue;


    @BeforeEach
    public void setup() {
        queue = new StateQueue();
    }
    
    @Test
    public void addOne() {

        Board board = new Board();

        assertTrue(queue.isEmpty());

        queue.push(board);

        assertFalse(queue.isEmpty());

        assertEquals(1, queue.size());

        queue.pop();

        assertTrue(queue.isEmpty());

        assertNull(queue.pop());
    }

    @Test
    public void addMultiple() {

        int toAdd = 10;

        int complete = 1;
        while (complete <= toAdd) {

            Board board = new Board();

            queue.push(board);

            assertEquals(complete, queue.size());
            assertFalse(queue.isEmpty());
            complete++;
        }

        complete = 1;

        while (complete <= toAdd) {

            queue.pop();

            assertEquals(toAdd - complete, queue.size());

            complete++;
        }

        assertTrue(queue.isEmpty());

        assertNull(queue.pop());
    }
}
