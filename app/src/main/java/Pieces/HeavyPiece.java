package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class HeavyPiece extends Piece{
    public HeavyPiece(int OWNER_ID, Game.Direction dir){
        super("heavy", OWNER_ID, dir);
    }
    ArrayList<Tile> getPossibleMoves(){
        return null;
    }

}
