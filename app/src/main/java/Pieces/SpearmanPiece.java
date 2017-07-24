package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class SpearmanPiece extends Piece {
    public SpearmanPiece(int OWNER_ID, Game.Direction dir){
        super("spearman", OWNER_ID, dir);
    }

    ArrayList<Tile> getPossibleMoves(){
        return null;
    }
}
