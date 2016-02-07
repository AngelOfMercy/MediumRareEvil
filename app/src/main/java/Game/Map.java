package Game;

import Game.Utility.Point;

import java.util.ArrayList;

import Game.Tiles.*;
import Pieces.Piece;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */
public class Map{

    private ArrayList<Tile> map = null;
    private char[][] DEFAULT_TILE_SET = null; //TODO
    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    private int width, height;


    //-------------------------------------------------------------
    //Constructors
    //-------------------------------------------------------------

    public Map(int width, int height){

        this.width = width;
        this.height = height;

        this.map = new ArrayList<Tile>();

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                map.add(new BlankTile(x, y));
            }
        }

    }

    public Map(ArrayList<Tile> tile_set){
        map = tile_set;
    }

    //-------------------------------------------------------------
    //Methods
    //-------------------------------------------------------------

    //MOVEMENT

    public boolean movePiece(Piece p, Game.Direction d){
        Point loc = p.getLocation();//Get 'p's X and Y Location

        Point targetTile = getTargetTile(loc, d);

        if(getTileAt(targetTile.x, targetTile.y).getPiece() != null){
            p.setLocation(new Point(targetTile.x, targetTile.y));
            return true;
        }
        else{
            return false;
        }

    }

    private Point getTargetTile(Point loc, Game.Direction d){
        int x = loc.x, y = loc.y;

        //TODO change to a switch
        if(d == Game.Direction.UP){
            y = y-1;
        }
        else if (d == Game.Direction.DOWN){
            y = y+1;
        }
        else if(d == Game.Direction.LEFT){
            x = x-1;
        }
        else if(d == Game.Direction.RIGHT){
            x = x+1;
        }

        return new Point(x, y);
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