package org.example;

import java.util.Optional;

public class Board {
    private final int SIZE = 3;
    private final Mark[][] grid;

    // Constructor
    public Board() {
        grid = new Mark[SIZE][SIZE];
        reset();
    }

    // Getters
    public int getSize() {
        return SIZE;
    }

    public Mark getCell(int row, int col) {
        checkBounds(row, col);
        return grid[row][col];
    }

    // Resets the board
    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = Mark.EMPTY;
            }
        }
    }

    // Checks if row or column is out of bounds
    private void checkBounds(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
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
