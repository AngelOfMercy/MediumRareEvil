package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class ArcherPiece extends Piece implements Ruleset{
    public ArcherPiece(int OWNER_ID, Game.Direction dir){
        super("archer", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The archer moves diagonally, like a bishop in chess
     * @return the set of coordinates
     */
    @Override
    public ArrayList<Point> getPossibleMoves(){
        ArrayList<Point> tiles = new ArrayList<>();
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

        //south east
        for(int i = x,  j = y; i < 8 && j < 8; ++i, ++j){
            tiles.add(new Point(i, j));
        }
        //north east
        for(int i = x,  j = y; i < 8 && j < 0; ++i, --j){
            tiles.add(new Point(i, j));
        }
        //south west
        for(int i = x,  j = y; i < 0 && j < 8; --i, ++j){
            tiles.add(new Point(i, j));
        }
        //north west
        for(int i = x,  j = y; i < 0 && j < 0; --i, --j){
            tiles.add(new Point(i, j));
        }

        return tiles;
    }
}
