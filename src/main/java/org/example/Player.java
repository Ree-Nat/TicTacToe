package org.example;


public abstract class Player {
    Mark mark;
    public abstract Move nextMove(Board board);
    public abstract Mark getMark();
}
