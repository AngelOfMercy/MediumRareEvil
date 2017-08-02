package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class ClericPiece extends Piece implements Ruleset{
    public ClericPiece(int OWNER_ID, Game.Direction dir){
        super("cleric", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The cleric moves in straight lines, like a rook in chess
     * @return the set of coordinates
     */
    @Override
    public ArrayList<Point> getPossibleMoves(){
        ArrayList<Point> tiles = new ArrayList<>();
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

        //East
        for(int i = x; i < 8; ++i){
            tiles.add(new Point(i, y));
        }
        //West
        for(int i = x; i >= 0; ++i){
            tiles.add(new Point(i, y));
        }
        //South
        for(int i = y; i < 8; ++i){
            tiles.add(new Point(x, i));
        }
        //North
        for(int i = y; i >= 0; ++i){
            tiles.add(new Point(x, i));
        }

        return tiles;
    }
}
