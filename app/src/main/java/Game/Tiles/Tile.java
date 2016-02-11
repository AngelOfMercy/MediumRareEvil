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

    public boolean setPiece(Piece p){
        if(p != null) {
            this.p = p;
            return true;
        }
        return false;
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

    /**
     * Removes the piece if it is there. Returns the piece that is removed.
     */
    public Piece removePiece(){
        Piece temp = p;
        p = null;
        return p;
    }

    /**
     * Compares two tiles in terms of map position, returns negative if the passed tile is closer to the origin
     * Zero if they are equidistant from the origin.
     * Positive if the passed tile is farther from the origin.
     * @param t
     * @return
     */
    public int compareTo(Tile t){
        double thisDist = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
        double passedDist = Math.sqrt(Math.pow(t.getX(), 2) + Math.pow(t.getY(), 2));
        double diff = passedDist - thisDist;

        if(diff == 0)
            return 0;
        else if(diff < 0)
            return -1;
        else
            return 1;
    }

}
