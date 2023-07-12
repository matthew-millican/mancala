package state;

import board.Board;

public class StateQueue {


    private Board head;

    private Board tail;

    private int size = 0;


    public void push(Board item) {

        if (head == null) {
            head = item;
            tail = item;


            size++;
            return;
        }

        Board pred = tail;
        pred.next = item;
        tail = item;

        size++;
    }

    public Board pop() {


        if (head == null) {
            return null;
        }

        Board curr = head;

        head = head.next;

        size--;
        return curr;
    }

    public boolean isEmpty() {
        return size == 0;

    }


    public int size() {
        return this.size;
    }


}
