package Game.Tiles;


import Pieces.Piece;
import Game.Map;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */
public abstract class Tile {

    private int x = -1, y = -1;
    private Piece p = null;

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Piece getPiece(){
        return p;
    }

}
