package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;
public class PalisadePiece extends Piece implements Ruleset{
    public PalisadePiece(int OWNER_ID, Game.Direction dir){
        super("palisade", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The palisade can move either two spaces forwards or one space diagonally forwards
     * @return the set of coordinates
     */
    @Override
    public void findPossibleMoves(Tile[][] tileset){
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;
        if(this.getOwnerID() == 0){
            for(int i = y + 1, k = 0; i < 8 && k < 2; ++i, ++k){
                checkTile(tileset[x][i]);
            }
            if(x < 7 && y < 7){ checkTile(tileset[x + 1][y + 1]); }
            if(x > 0 && y < 7){ checkTile(tileset[x - 1][y + 1]); }
        }
        else{
            for(int i = y - 1, k = 0; i >= 0 && k < 2; --i, ++k){
                checkTile(tileset[x][i]);
            }
            if(x < 7 && y > 0){ checkTile(tileset[x + 1][y - 1]); }
            if(x > 0 && y > 0){ checkTile(tileset[x - 1][y - 1]); }
        }
    }
}
