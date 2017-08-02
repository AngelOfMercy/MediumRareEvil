package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
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
    public ArrayList<Point> getPossibleMoves(){
        ArrayList<Point> tiles = new ArrayList<>();
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;
        if(this.getOwnerID() == 1){
            tiles.add(new Point(x, y + 1));
            tiles.add(new Point(x, y + 2));
            tiles.add(new Point(x + 1, y + 1));
            tiles.add(new Point(x - 1, y + 1));
        }
        else{
            tiles.add(new Point(x, y - 1));
            tiles.add(new Point(x, y - 2));
            tiles.add(new Point(x + 1, y - 1));
            tiles.add(new Point(x - 1, y - 1));
        }

        return tiles;
    }
}
