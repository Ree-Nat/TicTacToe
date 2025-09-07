
package org.example;

import java.util.Random;

/**
 * Represents an AI player
 *
 * @author Nathan Rinon
 * **/
public class RandomAIPlayer extends Player {
    private final Random random = new Random(); // Random number generator
    private Strategy strategy;

    /**
     * Initializes a RandomAIPlayer
     * @param mark a mark object containing the AI's mark
     *
     * **/
    public RandomAIPlayer(Mark mark) {
        super(mark);
    }

    /**
     * returns the next best available move on a board object for
     * the artificial intelligence to make
     * @param board object
     * @return a Move object that is free to Mark on board
     */
    @Override
    public Move nextMove(Board board) {
        //sets default strategy to decision tree
        strategy = new DecisionTreeStrategy(board, this.getMark());
        return strategy.pickMove(board);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }


}


