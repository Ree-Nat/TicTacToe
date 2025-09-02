package org.example;

public class ConsoleApp {
    public static void drawBoard(Board board) {
        System.out.println("----------------------------");

        // Prints the board using '-', then replaces '-' with a mark
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Mark mark = board.getCell(row, col);

                // If mark is not empty, then convert to its string counterpart
                String symbol = mark == Mark.EMPTY ? "-" : mark.toString();
                System.out.print(symbol + " ");
            }
            System.out.println();
        }
    }

    public static void winner(Mark mark) {
        System.out.println(mark + " is the winner!");
    }

    public static void draw() {
        System.out.println("Draw!");
    }
}
