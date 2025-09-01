package org.example;

/*

Step B — Players (Inheritance)
1. abstract class Player with fields protected final Mark mark.

   public abstract Move nextMove(Board board);

*/

public abstract class Player {
    protected final Mark mark;

    public Player(Mark mark) { this.mark = mark; }

    public abstract Move nextMove(Board board);

    // Getter
    public Mark getMark() { return mark; }
}
