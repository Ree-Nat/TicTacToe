package org.example;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(Mark mark) { super(mark); } // Calls Player constructor using super for HumanPlayer instance

    @Override
    public Move nextMove(Board board) {
        Scanner input = new Scanner(System.in);
        int row, column;

        while (true) {
            // User input
            System.out.print("Enter row: ");
            row = input.nextInt();

            System.out.print("Enter column: ");
            column = input.nextInt();

            // returns valid move if space is empty.
            try {
                if (board.getCell(row, column) == Mark.EMPTY) {
                    return new Move(row, column, mark);
                } else {
                    System.out.println("Cell is taken, try again!");
                }
            } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
                System.out.println("Illegal move, try again!");
            }
        }
    }
}
