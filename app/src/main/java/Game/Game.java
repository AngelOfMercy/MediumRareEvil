package Game;

import Pieces.Piece;

/**
 * Created by AngelOfMercy on 28/01/2016.
 */


public class Game{

    private Player[] players;
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

        players = p;
        CURRENT_PLAYER = 0;
        map = m;
    }

//---------------------------------------------------------------
//Methods
//---------------------------------------------------------------

    public void nextPlayer(){
        CURRENT_PLAYER = (CURRENT_PLAYER+1)%players.length;
    }

    public boolean movePiece(Piece p, Direction d){
        //If the piece is owned by the current player.
        //It is allowed to be moved.
        if(p.getOwnerID() == players[CURRENT_PLAYER].getID()){
            return map.movePiece(p, d);
        }
        return false;
    }

    public boolean removePlayer(Player p){
        //TODO
        int indexOfPlayer = 0;//Index of the player in players
        if(indexOfPlayer <= CURRENT_PLAYER)
            CURRENT_PLAYER--;
        //Remove the player entry.
        return true;
    }

    public boolean addPlayer(Player p){
        return true; //TODO
    }

//---------------------------------------------------------------
//Getters and Setters
//---------------------------------------------------------------

    public Player getCurrentPlayer(){
        return players[CURRENT_PLAYER];
    }

    public Map getMap(){
        return map;
    }

}