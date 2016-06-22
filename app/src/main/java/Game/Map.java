package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Utility.Point;

import java.util.HashSet;
import java.util.ArrayList;

import Game.Tiles.*;
import Pieces.Piece;
import Game.Game.Direction;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */
public class Map{

    private HashSet<Tile> map = null;

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    private int width, height;

    private Bitmap bitmap;

    private Point origin;


    //-------------------------------------------------------------
    //Constructors
    //-------------------------------------------------------------

    /**
     * Constructs a map object which is completely blank, using a given height and width.
     * @param width The width of a the map.
     * @param height The height of the map.
     */
    public Map(int width, int height, Bitmap bitmap, Point origin){

        //Store the maps width and height.
        this.width = width;
        this.height = height;
        this.bitmap = bitmap;
        this.origin = origin;

        //Create an empty list of tiles.
        this.map = new HashSet<Tile>();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                //map.add(new BlankTile(x, y));
            }
        }

    }

    public Map(HashSet<Tile> tile_set){
        map = tile_set;
    }

    //-------------------------------------------------------------
    //Methods
    //-------------------------------------------------------------

    //MOVEMENT

    /**
     * Moves the given piece one tile in the give direction.
     * @param p
     * @param d
     * @return If successful, returns true.
     */
    public boolean movePiece(Piece p, Game.Direction d){
        Point loc = p.getLocation();//Get 'p's X and Y Location

        switch (d){
            case UP:
                loc.y-=1;
                p.setLocation(loc, origin);
                break;
            case DOWN:
                loc.y+=1;
                p.setLocation(loc, origin);
                break;
            case LEFT:
                loc.x-=1;
                p.setLocation(loc, origin);
                break;
            case RIGHT:
                loc.x+=1;
                p.setLocation(loc, origin);
                break;
        }
        Tile targetTile = getAdjacentTile(loc, d);

//        if(targetTile != null && targetTile.containsPiece()){
//            p.setLocation(new Point(targetTile.getX(), targetTile.getY()));
//
//            Tile homeTile = getTileAt(loc);
//            if(homeTile.containsPiece(p)){
//                homeTile.removePiece();
//                return targetTile.setPiece(p);
//            }
//            return false;
//        }
//        else{
//            return false;
//        }
        return true;
    }

    private Tile getAdjacentTile(Point loc, Game.Direction d){
        float x = loc.x, y = loc.y;

        switch(d){
            case UP:
                y = y-1;
            case DOWN:
                y = y+1;
            case LEFT:
                x = x - 1;
            case RIGHT:
                x = x + 1;
        }
        return getTileAt(x, y);
    }

    public ArrayList<Tile> getValidMovement(Piece p){
        //TODO

        //Get all tiles within Piece.getMovement spaces.

        //Add them to the array

        //Remove all

        return null;

    }



    //-------------------------------------------------------------
    //Getters and Setters
    //-------------------------------------------------------------

    public Tile getTileAt(float x, float y){
        for(Tile t : map){
            if(t.getX() == x && t.getY() == y)
                return t;
        }
        return null;
    }

    public ArrayList<Tile> getAdjacentTiles(Tile p){

        ArrayList<Tile> adjTiles = new ArrayList<>();

        for(Tile t : map){
            if(getDistanceTo(p, t) == 1)
                adjTiles.add(t);
        }

        return adjTiles;
    }

    public int getDistanceTo(Tile src, Tile target){
        return Math.abs(src.getX() - target.getX()) + Math.abs(src.getY() - target.getY());
    }

    public Tile getTileAt(Point loc){
        return getTileAt(loc.getX(), loc.getY());
    }

    public int getWidth(){
        return bitmap.getWidth();
    }

    public int getHeight(){
        return bitmap.getHeight();
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }

    public void draw (Canvas canvas){
        canvas.drawBitmap(bitmap, (canvas.getWidth()/2) - (bitmap.getWidth()/2), 40, null);
    }
}