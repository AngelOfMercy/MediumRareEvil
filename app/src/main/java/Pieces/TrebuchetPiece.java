package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class TrebuchetPiece extends Piece {
    public TrebuchetPiece(int OWNER_ID, Game.Direction dir){
        super("trebuchet", OWNER_ID, dir);
    }
    ArrayList<Tile> getPossibleMoves(){
        return null;
    }
}
