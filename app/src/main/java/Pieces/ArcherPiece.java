package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class ArcherPiece extends Piece {
    public ArcherPiece(int OWNER_ID, Game.Direction dir){
        super("archer", OWNER_ID, dir);
    }

    ArrayList<Tile>getPossibleMoves(){
        return null;
    }
}
