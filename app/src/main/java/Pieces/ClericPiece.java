package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;
public class ClericPiece extends Piece implements Ruleset{
    public ClericPiece(int OWNER_ID, Game.Direction dir){
        super("cleric", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The cleric moves in straight lines, like a rook in chess
     * @param tileset the grid of tiles that makes up the board
     */
    @Override
    public void findPossibleMoves(Tile[][] tileset){
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

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
