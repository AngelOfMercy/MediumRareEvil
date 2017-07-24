package Game;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import Game.Tiles.Tile;
import Game.Utility.Point;
import Pieces.Piece;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */


public class Game{
    private Map map;
    private Player[] players;
    private int turn = 0;

    private Piece selected;

    public enum Direction {UP,
            DOWN,
            LEFT,
            RIGHT
    };

//---------------------------------------------------------------
//Constructors
//--------------------------------------------------------------

    public Game(Player[] p, Map m){
        map = m;
        players = p;
    }

//---------------------------------------------------------------
//Methods
//---------------------------------------------------------------

    /**
     * Increments current turn
     */
    public void nextTurn(){ ++turn; }

    /**
     * Handles cursor selection by checking the tile the cursor is over and reacting accordingly
     * @return
     */
    public boolean select(){
        Tile tile = map.getTileAt(map.cursor.xPos, map.cursor.yPos);

        if(tile.hasPiece()){
            Piece p = tile.getPiece();

            if(p.getOwnerID() == (turn & 1)){
                selected = p;
                return true;
            }
        }
        else if(selected != null){
            return movePiece(selected, tile);
        }
        return false;
    }
    /**
     * Moves the given piece to the gven tile
     *
     * @param p The piece to move
     * @param t The direction to move the piece.
     * @return Returns true if the piece was succesfully moved.
     */
    public boolean movePiece(Piece p, Tile t){
        p.setLocation(t.getLocation());
        return true;
    }


//---------------------------------------------------------------
//Getters and Setters
//---------------------------------------------------------------

    /**
     * Returns the current player.
     * @return
     */
    public int getCurrentPlayer(){ return turn & 1; }

    /**
     * Returns the map of the current game.
     * @return
     */
    public Map getMap(){
        return map;
    }

}