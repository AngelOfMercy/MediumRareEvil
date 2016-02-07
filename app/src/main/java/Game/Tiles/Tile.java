package Game.Tiles;


import Pieces.Piece;
import Game.Map;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */
public abstract class Tile implements Comparable<Tile> {

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

    public boolean equals(Tile t){
        return t.getX() == x &&
                t.getY() == y &&
                p.equals(p);
    }

    /**
     * Returns true, if the tile stores a piece in it.
     * @return
     */
    public boolean containsPiece(){
        return p != null;
    }

    /**
     * Returns true if the tile contains the passed piece.
     * @param p
     * @return
     */
    public boolean containsPiece(Piece p){
        return p.equals(this.p);
    }

}
