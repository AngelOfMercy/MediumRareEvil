package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import Pieces.Piece;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */


public class Game{

    private ArrayList<Player> players;
    private int CURRENT_PLAYER;
    private Map map;

    public enum Direction {UP,
            DOWN,
            LEFT,
            RIGHT
    };

//---------------------------------------------------------------
//Constructors
//--------------------------------------------------------------

    public Game(Player[] p, Map m){
        if(p.length < 1)
            throw new IllegalArgumentException("Need at least one player");

        players = new ArrayList<Player>(Arrays.asList(p));

        CURRENT_PLAYER = 0;
        map = m;
    }

//---------------------------------------------------------------
//Methods
//---------------------------------------------------------------

    /**
     * Sets the current player to the next one in the array.
     * Returns to the start of the array, if it would overflow.
     */
    public void nextPlayer(){
        CURRENT_PLAYER = (CURRENT_PLAYER+1)%players.size();
    }

    /**
     * Moves the given piece one tile in the given direction.
     *
     * @param p The piece to move
     * @param d The direction to move the piece.
     * @return Returns true if the piece was succesfully moved.
     */
    public boolean movePiece(Piece p, Direction d){
        //If the piece is owned by the current player.
        //It is allowed to be moved.
        if(p.getOwnerID() == players.get(CURRENT_PLAYER).getID()){
            return map.movePiece(p, d);
        }
        return false;
    }

    /**
     * Removes a player from turn rotation. Will return an exception if the player is not in the game.
     * @param p The player to remove
     */
    public void removePlayer(Player p){
        //TODO
        int indexOfPlayer = players.indexOf(p);//Index of the player in players

        //If the given player is not playing the game, throw an exception.
        if(indexOfPlayer < 0)
            throw new NoSuchElementException("Player not found");


        //If the player is before the current player in the list, decrease the lists count by one.
        if(indexOfPlayer <= CURRENT_PLAYER)
            CURRENT_PLAYER--;

        players.remove(p);
    }

    /**
     * Adds the given player to the end of the current player cycle.
     * @param p
     * @return
     */
    public void addPlayer(Player p){
        players.add(p);
    }

//---------------------------------------------------------------
//Getters and Setters
//---------------------------------------------------------------

    /**
     * Returns the current player.
     * @return
     */
    public Player getCurrentPlayer(){
        return players.get(CURRENT_PLAYER);
    }

    /**
     * Returns the map of the current game.
     * @return
     */
    public Map getMap(){
        return map;
    }

}