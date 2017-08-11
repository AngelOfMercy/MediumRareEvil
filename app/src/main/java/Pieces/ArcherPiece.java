package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;
public class ArcherPiece extends Piece implements Ruleset{
    public ArcherPiece(int OWNER_ID, Game.Direction dir){
        super("archer", OWNER_ID, dir);
    }

    /**Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The archer moves diagonally, like a bishop in chess
     * @param tileset the grid of tiles that makes up the board
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
    }
}
