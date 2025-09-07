package org.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DecisionTreeStrategy implements Strategy {

    private final Mark aiMark;
    private Move nextMove;
    private final Board board;
    private final Mark opponent_mark;

    /**
     * Creates a Decision Tree Strategy Object
     * @param mark the mark to strategize for
     * @param board a board object to analyze for the best next move
     * **/
    public DecisionTreeStrategy(Board board, Mark aiMark) {
        this.board = board;
        this.aiMark = aiMark;
        if (aiMark == Mark.X){
            opponent_mark = Mark.O;
        }
        else{
            opponent_mark = Mark.X;
        }
    }

    /**
     * Picks the best next move on the board based firstly on strategizing the middle, then blocking
     * and then finding where the AI can move.
     * @param board a board object to analyze
     * @return A Move object which shows the best possible move for the mark that was strategized
     * **/
    @Override
    public Move pickMove(Board board) {
       if(is_middle_empty(board))
       {
           return new Move(1, 1, aiMark);
       }
       if(canWin(board, opponent_mark)){
           return nextMove;
       }
       else if(canWin(board, aiMark)){
           return nextMove;
       }
       else
           return find_empty_cell(board);
    }

    //Chooses next empty cell linearly from 0, 1, 2... left to right
    private Move find_empty_cell(Board board) {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == Mark.EMPTY) {
                    return new Move(i, j, aiMark);
                }
            }
        }
        //returns error
        return new Move(-1,-1, aiMark);
    }

    private int getEmpty_index(ArrayList<Mark> markList) {
        return markList.indexOf(Mark.EMPTY);
    }

    private boolean is_middle_empty(Board board) {
        Mark middle_cell = board.getCell(1, 1);
        return middle_cell == Mark.EMPTY;
    }


    private boolean canWin(Board board, Mark mark) {
        int boardSize = board.getSize();

        for(int i = 0; i < board.getSize(); i++) {
            ArrayList<Mark> currentRow = getRow(i);
            if (hasMarks(currentRow, boardSize-1, mark) && hasEmptyMark(currentRow))
            {
                int empty_index = currentRow.indexOf(Mark.EMPTY);
                nextMove = new Move(i, empty_index, aiMark);
                return true;}
        }
        for(int i = 0; i < board.getSize(); i++) {
            ArrayList<Mark> currentCol = getCol(i);
            if (hasMarks(currentCol, boardSize-1, mark) && hasEmptyMark(currentCol))
            {
                int empty_index = currentCol.indexOf(Mark.EMPTY);
                nextMove = new Move(empty_index, i, aiMark);
                return true;}
        }

        ArrayList<Mark> rightDiagonal = getRightDiagonal();
        ArrayList<Mark> leftDiagonal = getLeftDiagonal();
        if (hasMarks(rightDiagonal, boardSize-1, mark) && hasEmptyMark(leftDiagonal)){
            int empty_index = rightDiagonal.indexOf(Mark.EMPTY);
            nextMove = new Move(empty_index, empty_index, aiMark);
            return true;
        }

        if (hasMarks(leftDiagonal, boardSize-1, mark) && hasEmptyMark(leftDiagonal)){
            int empty_index = leftDiagonal.indexOf(Mark.EMPTY);
            nextMove = new Move(empty_index, empty_index, aiMark);
            return true;
        }

        //get error
        return false;
    }
    
    private boolean hasMarks(ArrayList<Mark> listofMarks, int numberOfMarks, Mark mark) {
        return Collections.frequency(listofMarks, mark) == numberOfMarks;
    }

    private boolean hasEmptyMark(ArrayList<Mark> listofMarks)
    {
        return listofMarks.contains(Mark.EMPTY);
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

    private ArrayList<Mark> getRightDiagonal() {
        ArrayList<Mark> rightDiagnolList = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            rightDiagnolList.add(board.getCell(i, i));
        }
        return rightDiagnolList;
    }

    private ArrayList<Mark> getLeftDiagonal() {
        ArrayList<Mark> leftDiagonalList = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            int j = board.getSize() - (i+1);
            leftDiagonalList.add(board.getCell(j, j));
        }
        return leftDiagonalList;
    }



}


