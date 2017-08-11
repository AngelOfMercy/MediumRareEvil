package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

public class TrebuchetPiece extends Piece implements Ruleset{
    public TrebuchetPiece(int OWNER_ID, Game.Direction dir){
        super("trebuchet", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The trebuchet can move one space in any direction, like a king in chess
     * @return the set of coordinates
     */
    @Override
    public void findPossibleMoves(Tile[][] tileset){
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

        //Lazy coding here, but the check is only performed once so it doesn't matter
        if(x < 7 && y < 7){ checkTile(tileset[x + 1][y + 1]); }
        if(x > 0 && y < 7){ checkTile(tileset[x - 1][y + 1]); }
        if(x < 7 && y > 0){ checkTile(tileset[x + 1][y - 1]); }
        if(x > 0 && y > 0){ checkTile(tileset[x - 1][y - 1]); }
        if(x > 0){ checkTile(tileset[x - 1][y]); }
        if(x < 7){ checkTile(tileset[x + 1][y]); }
        if(y > 0){ checkTile(tileset[x][y - 1]); }
        if(y < 7){ checkTile(tileset[x][y + 1]); }

    }
}
