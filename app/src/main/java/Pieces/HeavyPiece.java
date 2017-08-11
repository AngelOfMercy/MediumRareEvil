package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;
public class HeavyPiece extends Piece implements Ruleset{
    public HeavyPiece(int OWNER_ID, Game.Direction dir){
        super("heavy", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The heavy can move two spaces in any direction
     * @return the set of coordinates
     */
    @Override
    public void findPossibleMoves(Tile[][] tileset){
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

        //south east
        for(int i = x + 1,  j = y + 1, k = 0; i < 8 && j < 8 && k < 2; ++i, ++j, ++k){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //north east
        for(int i = x,  j = y, k = 0; i < 8 && j < 0 && k < 2; ++i, --j, ++k){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //south west
        for(int i = x,  j = y, k = 0; i < 0 && j < 8 && k < 0; --i, ++j, ++k){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //north west
        for(int i = x,  j = y, k = 0; i < 0 && j < 0 && k < 0; --i, --j, ++k){
            if(checkTile(tileset[i][j])){
                break;
            }
        }
        //East
        for(int i = x, k = 0; i < 7 && k < 2; ++i, ++k){
            if(checkTile(tileset[i][y])){
                break;
            }
        }
        //West
        for(int i = x, k = 0; i >= 0 && k < 2; ++i, ++k){
            if(checkTile(tileset[i][y])){
                break;
            }
        }
        //South
        for(int i = y, k = 0; i < 7 && k < 2; ++i, ++k){
            if(checkTile(tileset[x][i])){
                break;
            }
        }
        //North
        for(int i = y, k = 0; i >= 0 && k < 2; ++i, ++k){
            if(checkTile(tileset[x][i])){
                break;
            }
        }
    }

}
