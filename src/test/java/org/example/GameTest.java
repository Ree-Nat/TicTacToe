package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    // makes winner method accessible for testing (reflection) || bypasses encapsulation
    private boolean winner(Board board, Mark mark) throws Exception {
        var method = Main.class.getDeclaredMethod("winner", Board.class, Mark.class);
        method.setAccessible(true);
        return (boolean) method.invoke(null, board, mark);
    }

    @Test void testRowWin() throws Exception { // test fist row
        Board board = new Board(3);

        // test positions (0,0), (0,1), (0,2)
        for (int col = 0; col < 3; col++) {
            board.place(new Move(0, col, Mark.X));
        }

        // return true once positions are marked with X
        assertTrue(winner(board, Mark.X));
    }

    @Test void testColumnWin() throws Exception { // test first column
        Board board = new Board(3);

        // test positions (0,0), (1,0), (2,0)
        for (int row = 0; row < 3; row++) {
            board.place(new Move(row, 0, Mark.X));
        }

        // return true once positions are marked with X
        assertTrue(winner(board, Mark.X));
    }

    @Test void testDiagonalWin() throws Exception {
        Board board = new Board(3);

        // test positions (0,0), (1,1), (2,2)
        for (int i = 0; i < 3; i++) {
            board.place(new Move(i, i, Mark.X));
        }

        // return true once positions are marked with X
        assertTrue(winner(board, Mark.X));
    }

    @Test void testAntiDiagonalWin() throws Exception {
        Board board = new Board(3);

        // test positions (0,2), (1,1), (2,0)
        for (int i = 0; i < 3; i++) {
            board.place(new Move(i, 2 - i, Mark.X));
        }

        // return true once positions are marked with X
        assertTrue(winner(board, Mark.X));
    }

    @Test void testDraw() throws Exception {
        Board board = new Board(3);
        board.place(new Move(0, 0, Mark.X));
        board.place(new Move(0, 1, Mark.O));
        board.place(new Move(0, 2, Mark.X));
        board.place(new Move(1, 0, Mark.X));
        board.place(new Move(1, 1, Mark.X));
        board.place(new Move(1, 2, Mark.O));
        board.place(new Move(2, 0, Mark.O));
        board.place(new Move(2, 1, Mark.X));
        board.place(new Move(2, 2, Mark.O));

        // if both return false = draw (no winner)
        assertFalse(winner(board, Mark.X));
        assertFalse(winner(board, Mark.O));
    }

    @Test void testOutOfBounds() throws Exception {
        Board board = new Board(3);
        // lambda functions

        // less than board size
        assertThrows(IllegalArgumentException.class, () -> new Move(-1, 0, Mark.X));
        assertThrows(IllegalArgumentException.class, () -> new Move(0, -1, Mark.X));

        // greater than board size
        assertThrows(IndexOutOfBoundsException.class, () -> board.place(new Move(4, 0, Mark.X)));
        assertThrows(IndexOutOfBoundsException.class, () -> board.place(new Move(0, 4, Mark.X)));
    }

    @Test void testCellIfTaken() throws Exception {
        Board board = new Board(3);

        board.place(new Move(1, 1, Mark.X)); // places mark at 1, 1

        // throw exception due to cell taken
        assertThrows(IllegalArgumentException.class, () -> board.place(new Move(1, 1, Mark.X)));
    }
    
    @Test void testToString() { // directly check for last mark
        Move mark = new Move(0, 0, Mark.X);
        String expected = "Last mark: X";
        assertEquals(expected, mark.toString());
    }
}
