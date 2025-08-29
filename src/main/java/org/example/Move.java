package org.example;


public class Move {
    private final int MAX_SIZE = 2;
    public int row = MAX_SIZE;
    public int column = MAX_SIZE;
    Mark mark;


    public Move(int row, int column, Mark mark){
        checkArguments(row, column);
        this.row = row;
        this.column = column;
        this.mark = mark;
    }

    private void checkArguments(int column, int row){
        if (column > MAX_SIZE || column < 0){
            throw new IllegalArgumentException("Illegal Column Size");
        }
        if (row > MAX_SIZE || row < 0){
            throw new IllegalArgumentException("Illegal Row Size");
        }
    }

}
