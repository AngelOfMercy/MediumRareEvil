package Pieces;

import java.util.ArrayList;

import Game.Game;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by Larry on 7/17/2017.
 */
public class HeavyPiece extends Piece implements Ruleset{
    public HeavyPiece(int OWNER_ID, Game.Direction dir){
        super("heavy", OWNER_ID, dir);
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * The heavy can move two spaces in any direction
     * @return the set of coordinates
     */
    @Override
    public ArrayList<Point> getPossibleMoves(){
        ArrayList<Point> tiles = new ArrayList<>();
        int x = (int)this.getLocation().x;
        int y = (int)this.getLocation().y;

        tiles.add(new Point(x + 1, y));
        tiles.add(new Point(x + 2, y));
        tiles.add(new Point(x - 1, y));
        tiles.add(new Point(x - 2, y));

        tiles.add(new Point(x, y + 1));
        tiles.add(new Point(x, y + 2));
        tiles.add(new Point(x, y - 1));
        tiles.add(new Point(x, y - 2));

        tiles.add(new Point(x + 1, y + 1));
        tiles.add(new Point(x + 2, y + 2));
        tiles.add(new Point(x - 1, y - 1));
        tiles.add(new Point(x - 2, y - 2));

        tiles.add(new Point(x + 1, y - 1));
        tiles.add(new Point(x + 2, y - 2));
        tiles.add(new Point(x - 1, y + 1));
        tiles.add(new Point(x - 2, y + 2));

        return tiles;
    }

}
