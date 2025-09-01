package org.example;

// Move (immutable): row, col, mark with getters; validate in constructor (row/col ≥ 0).

public class Move {
    private final int MAX_SIZE = 3;
    private final int row, column;
    private final Mark mark;

    // Constructor
    public Move(int row, int column, Mark mark) {
        if (row < 0 || row >= MAX_SIZE) {
            throw new IllegalArgumentException("Illegal Row Size");
        }
        if (column < 0 || column >= MAX_SIZE) {
            throw new IllegalArgumentException("Illegal Column Size");
        }
        if (mark == null) {
            throw new IllegalArgumentException("Mark cannot be null");
        }

        this.row = row;
        this.column = column;
        this.mark = mark;
    }

    // Getters
    public int getRow() { return row; }
    public int getColumn() { return column; }
    public Mark getMark() { return mark; }
}
