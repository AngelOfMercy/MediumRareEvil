package com.example.angelofmercy.mediumrareevil;

/**
 * UI design element used to signify the player's selection on the board.
 * It can be moved incrementally using buttons.
 *
 * Created by Larry on 1/19/2016.
 */
import Game.Game.Direction;

public class Cursor {
    public int posX;
    public int posY;
    public Direction d;

    public void Cursor (int X, int Y, Direction direction){
        posX = X;
        posY = Y;
        d = direction;
    }

    /*Directional movement method adds and subtracts from class fields.
    * 1 = up
    * 2 = right
    * 3 = left
    * 4 = down*/
    public void move (Direction d) {
        //TODO need to ask board for size
        switch (d){
            case UP:
                posY += 1;
                break;
            case DOWN:
                posX += 1;
                break;
            case LEFT:
                posX -= 1;
                break;
            case RIGHT:
                posY -= 1;
                break;
        }
    }
}
