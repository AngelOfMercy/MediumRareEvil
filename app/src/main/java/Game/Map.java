package Game;

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


    //-------------------------------------------------------------
    //Constructors
    //-------------------------------------------------------------

    /**
     * Constructs a map object which is completely blank, using a given height and width.
     * @param width The width of a the map.
     * @param height The height of the map.
     */
    public Map(int width, int height){

        //Store the maps width and height.
        this.width = width;
        this.height = height;


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

        Tile targetTile = getAdjacentTile(loc, d);

        if(targetTile != null && targetTile.containsPiece()){
            p.setLocation(new Point(targetTile.getX(), targetTile.getY()));
            return true;
        }
        else{
            return false;
        }

    }

    private Tile getAdjacentTile(Point loc, Game.Direction d){
        int x = loc.x, y = loc.y;

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

    public Tile getTileAt(int x, int y){
        for(Tile t : map){
            if(t.getX() == x && t.getY() == y)
                return t;
        }
        return null;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }
}