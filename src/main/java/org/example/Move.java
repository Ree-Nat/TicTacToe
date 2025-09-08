package org.example;

// Move (immutable): row, col, mark with getters; validate in constructor (row/col ≥ 0).

public class Move {
    private final int row, column;
    private final Mark mark;

    // Constructor
    public Move(int row, int column, Mark mark) {
        if (row < 0 || column < 0)
        {
            throw new IllegalArgumentException("Row and column must be positive!");
        }
        if (mark == null)
        {
            throw new IllegalArgumentException("Mark must not be null!");
        }

        this.row = row;
        this.column = column;
        this.mark = mark;
    }

    // Getters
    public int getRow() { return row; }
    public int getColumn() { return column; }
    public Mark getMark() { return mark; }

    @Override public String toString() {
        return "Last mark: " + mark;
    }
}
