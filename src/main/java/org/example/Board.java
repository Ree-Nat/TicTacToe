package org.example;

public class Board {
    private final int size;
    private final Mark[][] grid;

    // Constructor
    public Board(int size) {
        if (size < 1) throw new IllegalArgumentException("Board size must be positive!");
        this.size = size;
        grid = new Mark[size][size];
        reset();
    }

    // Getters
    public int getSize() {
        return size;
    }

    public Mark getCell(int row, int col) {
        checkBounds(row, col);
        return grid[row][col];
    }

    // Resets the board
    public void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Mark.EMPTY;
            }
        }
    }

    // Checks if row or column is out of bounds
    private void checkBounds(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Cell out of bounds");
        }
    }

    // Places mark on given grid
    public void place(Move move) {
        int row = move.getRow();
        int col = move.getColumn();

        checkBounds(row, col);

        if (grid[row][col] != Mark.EMPTY) {
            throw new IllegalArgumentException("Cell already occupied");
        }
        grid[row][col] = move.getMark();
    }


}
