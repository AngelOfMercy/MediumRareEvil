package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class StandardBearerPiece extends Piece {
    public StandardBearerPiece(int OWNER_ID, Game.Direction dir){
        super("standard_bearer", OWNER_ID, dir);
    }

    ArrayList<Tile> getPossibleMoves(){
        return null;
    }
}
