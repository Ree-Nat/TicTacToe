package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DecisionTreeStrategy implements  Strategy {

    private final Player player;
    private Move nextMove;
    private Board board;
    private Mark opponent_mark;

    public DecisionTreeStrategy(Board board, Player player) {
        this.player = player;
        this.board = board;
        if (player.getMark() == Mark.X){
            opponent_mark = Mark.O;
        }
        else{
            opponent_mark = Mark.X;
        }
    }

    @Override
    public Move pickMove(Board board) {
       if(is_middle_empty(board))
       {
           return new Move(1, 1, player.getMark());
       }

       choose_empty_cell(board);
       return nextMove;
    }

    //Chooses next empty cell linearly from 0, 1, 2... left to right
    private void choose_empty_cell(Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == Mark.EMPTY) {
                    nextMove = new Move(i, j, player.getMark());
                }
            }
        }
    }

    private boolean is_middle_empty(Board board) {
        Mark middle_cell = board.getCell(1, 1);
        return middle_cell == Mark.EMPTY;
    }

    private boolean blockWin(Board board) {
        for(int i = 0; i < board.getSize(); i++) {
            int boardSize = board.getSize();
            ArrayList<Mark> currentRow = getRow(i);
            if (Collections.frequency(currentRow, opponent_mark) == boardSize-1
                    && currentRow.contains(Mark.EMPTY))
            {
                int empty_index = currentRow.indexOf(Mark.EMPTY);
                nextMove = new Move(i, empty_index, player.getMark());
                return true;}
        }
        for(int i = 0; i < board.getSize(); i++) {
            int boardSize = board.getSize();
            ArrayList<Mark> currentRow = getCol(i);
            if (Collections.frequency(currentRow, opponent_mark) == boardSize-1
                    && currentRow.contains(Mark.EMPTY))
            {
                int empty_index = currentRow.indexOf(Mark.EMPTY);
                nextMove = new Move(empty_index, i, player.getMark());
                return true;}
        }

        return false;
    }

    private ArrayList<Mark> getRow(int row) {
        ArrayList<Mark> rowList = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            rowList.add(board.getCell(row, i));
        }
        return rowList;
    }

    private ArrayList<Mark> getCol(int col) {
        ArrayList<Mark> rowList = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            rowList.add(board.getCell(i, col));
        }
        return rowList;
    }

    private ArrayList<Mark> getRightDiagnol() {
        ArrayList<Mark> rightDiagnolList = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            rightDiagnolList.add(board.getCell(i, i));
        }
        return rightDiagnolList;
    }

    private ArrayList<Mark> getLeftDiagnol() {
        ArrayList<Mark> leftDiagnolList = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            int j = board.getSize() - (i+1);
            leftDiagnolList.add(board.getCell(j, j));
        }
        return leftDiagnolList;
    }



}


