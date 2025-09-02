package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(3);
        Player first_player = new HumanPlayer(Mark.O); // Sets who is the first player
        Player second_player = new HumanPlayer(Mark.X); // Sets who is the first player
        RandomAIPlayer aiPlayer;
        int playerChoice = choosePlayerType();
        if (playerChoice == 0) {
            second_player = new RandomAIPlayer(Mark.X); // Sets who is the first player
        }
        Player currentPlayer = first_player;
        // Main game loop
        while (true) {
            ConsoleApp.drawBoard(board);
            Move move = currentPlayer.nextMove(board);
            board.place(move);

            if (winner(board, currentPlayer.getMark())) {
                ConsoleApp.drawBoard(board);
                ConsoleApp.winner(currentPlayer.getMark());
                break;
            }

            if (isDraw(board)) {
                ConsoleApp.drawBoard(board);
                ConsoleApp.draw();
                break;
            }

            // Changes current player after a turn has been made
            currentPlayer = (currentPlayer == first_player) ? second_player : first_player;
        }
    }


    //returns player choice to choose AI player or human player
    private static int choosePlayerType(){
        System.out.println("Type which opponent to play against - \n 0:AI \n 1:Human");
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int output = -1;
        while(!valid)
        {
            try{
                output = scanner.nextInt();
                if(!(output == 1 || output == 0))
                {
                    System.out.println("Invalid choice. Try again.");
                }
                else {valid = true;}
            }
            catch (InputMismatchException e) {
                System.out.println("Input must be an integer");
                scanner.next();
            }

        }
        return output;
    }

    // Checks which mark has won
    private static boolean winner(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (checkRow(board, i, mark) || checkColumn(board, i, mark)) return true;
        }
        return checkDiagonal(board, mark) || checkAntiDiagonal(board, mark);
    }

    // returns true if a single row all have same mark
    private static boolean checkRow(Board board, int row, Mark mark) {
        for (int col = 0; col < board.getSize(); col++) {
            if (board.getCell(row, col) != mark) return false;
        }
        return true;
    }

    // returns true if a single column all have the same mark
    private static boolean checkColumn(Board board, int col, Mark mark) {
        for (int row = 0; row < board.getSize(); row++) {
            if (board.getCell(row, col) != mark) return false;
        }
        return true;
    }

    // returns true if left diagonal '\' all have the same mark
    private static boolean checkDiagonal(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, i) != mark) return false;
        }
        return true;
    }

    // returns true if right diagonal '/' all have the same mark
    private static boolean checkAntiDiagonal(Board board, Mark mark) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, board.getSize() - 1 - i) != mark) return false;
        }
        return true;
    }

    // returns true if all spots are taken
    private static boolean isDraw(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getCell(row, col) == Mark.EMPTY) return false;
            }
        }
        return true;
    }
}
