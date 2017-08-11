package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

public class StandardBearerPiece extends Piece implements Ruleset{
    public StandardBearerPiece(int OWNER_ID, Game.Direction dir){
        super("standard_bearer", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The standard bearer can move in any direction, like a queen in chess
     * @return the set of coordinates
     */
    @Override
    public void findPossibleMoves(Tile[][] tileset){
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

        //south east
        for(int i = x + 1,  j = y + 1; i < 8 && j < 8; ++i, ++j){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //north east
        for(int i = x + 1,  j = y - 1; i < 8 && j >= 0; ++i, --j){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //south west
        for(int i = x - 1,  j = y + 1; i >= 0 && j < 8; --i, ++j){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //north west
        for(int i = x - 1,  j = y - 1; i >= 0 && j >= 0; --i, --j){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //East
        for(int i = x + 1; i < 8; ++i){
            if(checkTile(tileset[i][y])){
                break;
            }
        }
        //West
        for(int i = x - 1; i >= 0; --i){
            if(checkTile(tileset[i][y])){
                break;
            }
        }
        //South
        for(int i = y + 1; i < 8; ++i){
            if(checkTile(tileset[x][i])){
                break;
            }
        }
        //North
        for(int i = y - 1; i >= 0; --i){
            if(checkTile(tileset[x][i])){
                break;
            }
        }
    }
}
