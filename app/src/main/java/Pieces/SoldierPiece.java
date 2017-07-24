package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Map;

/**
 * Created by AngelOfMercy on 14/01/2016.
 */
public class SoldierPiece extends Piece {
    public SoldierPiece(int OWNER_ID, Game.Direction dir){
        super("soldier", OWNER_ID, dir);
    }

    ArrayList<Tile> getPossibleMoves(Map map){

        return null;
    }
}
