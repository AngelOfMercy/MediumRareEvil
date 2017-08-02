package Pieces;

import android.graphics.Bitmap;

import java.util.ArrayList;

import Game.Game;
import Game.Map;
import Game.Tiles.Tile;
import Game.Utility.Point;

/**
 * Created by AngelOfMercy on 14/01/2016.
 */
public class WizardPiece extends Piece implements Ruleset{

    public WizardPiece(int OWNER_ID, Game.Direction dir){
        super("wizard", OWNER_ID, dir);
        super.ATTACK_RANGE_MIN = 3;
        super.ATTACK_RANGE_MAX = 3;
        super.setDefaultHP(2);
    }

    @Override
    public boolean attackTarget(Piece p, Map m){
        applyFireball(p, m);
        return super.attackTarget(p, m);
    }

    private void applyFireball(Piece p, Map m){
        ArrayList<Tile> adjTiles = m.getAdjacentTiles(m.getTileAt(super.getLocation()));

        for(Tile t: adjTiles){
            if(t.hasPiece())
                t.getPiece().dealDamage(super.getAttackDamage());
        }
    }

    /**
     * Returns the movement pattern of this piece as a set of coordinates, to be checked later
     * the wizard can move in any direction, like a queen in chess
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
