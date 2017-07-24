package Game.Tiles;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import Pieces.Piece;
import Game.Map;
import Game.Utility.Point;

/**
 * Created by AngelOfMercy on 28/01/2016.
 * Edited by Lawrence Buck on 27/11/2016
 */
public class Tile implements Comparable<Tile>{

    private int x = 0, y = 0;
    private Piece p = null;

    public float screenPosX;
    public float screenPosY;

    private Point origin;

    private Bitmap debug;
    private Bitmap zoomedDebug;

    private boolean zoomed = false;

    //126, -74, 70, 41
    private float xOff = 90, xOffMod = -52.5f, yOff = 49, yOffMod = 29;
    private float zXOff = 126, zXOffMod = -75, zYOff = 70, zYOffMod = 41;

    public Tile(int x, int y, Point origin, Bitmap debug){
        this.x = x;
        this.y = y;
        this.origin = origin;

        this.debug = debug;
        this.zoomedDebug = resize(debug, 1.4f, 1.4f);
        calculateScreenPos(false);
    }

    private Bitmap resize(Bitmap b, float scaleX, float scaleY){
        int sizeX = (int)(b.getWidth() * scaleX);
        int sizeY = (int)(b.getHeight() * scaleY);
        return Bitmap.createScaledBitmap(b, sizeX, sizeY, false);
    }
    /*Method to calculate where tile is on the screen*/
    public void calculateScreenPos(boolean z){
        zoomed  = z;
        /**calculate pixel position based on origin and map coordinates*/

        /*algorithm: measure along one axis the distance in pixels, then add modifier for
        distance along the opposite axis due to the diagonal nature of the game view.
        Do the same for the other axis.*/
        if(!zoomed){

            screenPosX = origin.getX() + (x * xOff) + (y * xOffMod);
            screenPosY = origin.getY() + (y * yOff) + (x * yOffMod);
        }

        else{
            screenPosX = origin.getX() + (x * zXOff) + (y * zXOffMod);
            screenPosY = origin.getY() + (y * zYOff) + (x * zYOffMod);
        }
    }

    /*Method to set origin*/
    public void setOrigin(Point o){
        this.origin = o;
    }

    /*Debug method to set constants for grid positioning*/
    public void setConstants(float xO, float xOMod, float yO, float yOMod, Point o){
        if(!zoomed) {
            xOff = xO;
            xOffMod = xOMod;
            yOff = yO;
            yOffMod = yOMod;
        }
        else{
            zXOff = xO;
            zXOffMod = xOMod;
            zYOff = yO;
            zYOffMod = yOMod;
        }

        origin = o;

        calculateScreenPos(zoomed);
    }

    public int getX(){ return x; }

    public int getY(){
        return y;
    }

    public Point getLocation(){ return new Point(x, y); }

    public Piece getPiece(){
        return p;
    }

    public Point getScreenLocation(){ return new Point(screenPosX, screenPosY); }

    public boolean hasPiece(){
        return p != null;
    }

    public boolean setPiece(Piece p){
        if(p != null) {
            this.p = p;
            p.setLocation(new Point(x, y));
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

    //draw a placeholder to show the position of the tile for debug purposes.
    public void debugDraw(Canvas canvas){
        if(!zoomed){
            canvas.drawBitmap(debug, screenPosX, screenPosY, null);
        }
        else{
            canvas.drawBitmap(zoomedDebug, screenPosX,screenPosY, null);
        }
    }

}
