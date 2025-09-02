
package org.example;

import java.util.Random;

/**
 * Represents an AI player
 *
 * @author Nathan Rinon
 * **/
public class RandomAIPlayer extends Player {
    private final Random random = new Random(); // Random number generator
    private final Mark opponent_mark;

    /**
     * Returns an AI player object
     * @param mark a mark object containing the AI's mark
     * @returns a RandomAIPlayer object
     * **/
    public RandomAIPlayer(Mark mark) {
        super(mark);
        if(this.getMark() == Mark.X)
        {
            opponent_mark = Mark.O;
        }
        else{
            opponent_mark = Mark.X;
        }
    }

    /**
     * returns the next best available move on a board object for
     * the artificial intelligence to make
     * @param board object
     * @return a Move object that is free to Mark on board
     */
    @Override
    public Move nextMove(Board board) {
        if (is_middle_empty(board)) {
            return new Move(1, 1, mark);
        }
        Move next_bot_move = blockWin(board);
        if(next_bot_move != null) {
            return next_bot_move;
        }
        next_bot_move = attempt_win(board);
        if(next_bot_move != null) {
            return next_bot_move;
        }

        //return next available cell
        return findEmpty(board);
    }

    private Move findEmpty(Board board) {
        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                if( board.getCell(i, j) == Mark.EMPTY) {
                    return new Move(i, j, this.getMark());
                }
            }
        }
        return null;
    }

    private Move blockWin(Board board) {
        int close_to_win_marks = 2;
        return find_winning_move(board, close_to_win_marks, opponent_mark);
    }

    private Move attempt_win(Board board) {
       int close_to_win_marks = 2;
       return find_winning_move(board, close_to_win_marks, this.getMark());
    }


    private boolean is_middle_empty(Board board) {
        Mark middle_cell = board.getCell(1, 1);
        return middle_cell == Mark.EMPTY;
    }

    //checks row if it has a certain amount of marks and contains an empty cell,
    //if row does not proceed conditions, proceeds to check column
    private Move find_winning_move(Board board, int required_marks, Mark mark) {
        boolean hasEmpty = false;
        int mark_count = 0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) == Mark.EMPTY) {
                    row = i;
                    col = j;
                    hasEmpty = true;
                } else if (board.getCell(i, j) != Mark.EMPTY
                        && board.getCell(i, j) == mark) {
                    mark_count++;
                }
                if (hasEmpty && required_marks == mark_count) {
                    return new Move(row, col, this.getMark());
                }
            }
            mark_count = 0;
            hasEmpty = false;
        }
        return checkCol(board, required_marks, mark);
    }

    private Move checkCol(Board board, int required_marks, Mark mark) {
        boolean hasEmpty = false;
        int mark_count = 0;
        int row = 0;
        int col = 0;
        for (int j = 0; j < board.getSize(); j++) {
            for (int i = 0; i < board.getSize(); i++) {
                if (board.getCell(i, j) == Mark.EMPTY) {
                    hasEmpty = true;
                    row = i;
                    col = j;
                } else if (board.getCell(i, j) != Mark.EMPTY
                        && board.getCell(i, j) == mark) {
                    mark_count++;
                }
                if (hasEmpty && required_marks == mark_count) {
                    return new Move(row, col, this.getMark());
                }
            }
            mark_count = 0;
            hasEmpty = false;
        }
        return check_right_diagonal(board, required_marks, mark);
    }

    private Move check_right_diagonal(Board board, int required_marks, Mark mark) {
        int mark_count = 0;
        boolean hasEmpty = false;
        int row = 0;
        int col = 0;
        for (int i = 0, j = 0; i < board.getSize(); i++, j++) {
            if (board.getCell(i, j) == Mark.EMPTY) {
                hasEmpty = true;
                row = i;
                col = j;
            } else if (board.getCell(i, j) != Mark.EMPTY
                    && board.getCell(i, j) == mark) {
                mark_count++;
            }
        }
        if (hasEmpty && mark_count == required_marks) {
            return new Move(row, col, this.getMark());
        }
        return check_left_diagonal(board, required_marks, mark);
    }


    private Move check_left_diagonal(Board board, int required_marks, Mark mark) {
        int mark_count = 0;
        boolean hasEmpty = false;
        int row = 0;
        int col = 0;
        for (int i = 2, j = 2; i >= 0; i--, j--) {
            if (board.getCell(i, j) == Mark.EMPTY) {
                hasEmpty = true;
                row = i;
                col = j;
            } else if (board.getCell(i, j) != Mark.EMPTY
                    && board.getCell(i, j) == mark) {
                mark_count++;
            }
        }
        if (hasEmpty && mark_count == required_marks) {
            return new Move(row, col, mark);
        }
        return null;
    }

}


