package org.example;

import java.util.Scanner;

public class HumanPlayer extends Player{

    private final Mark mark;
    public HumanPlayer(Mark mark) {
        this.mark = mark;
    }
    @Override
    //Asks human for next move, move configures the board
    //Returns move object
    public Move nextMove(Board board) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter row: ");
        int row = input.nextInt();
        System.out.print("Enter Column: ");
        int column = input.nextInt();
        //Modify board here

        //
        return new Move(row, column, mark);
    }
    @Override
    public Mark getMark() {
        return this.mark;
    }
}
