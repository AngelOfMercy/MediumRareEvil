package Game.Utility;

import Game.*;
import Pieces.Piece;
import Game.Tiles.*;

/**
 * Created by AngelOfMercy on 11/02/2016.
 */
public class Combat{

    private Map map;

    public Combat(Map m){
        this.map = m;
    }

    public void attackUnit(Tile src, Game.Tiles.Tile target){

        //TODO: Check that there is a src piece on the tile, and a target.

        //Check that the target is not on the same team as the source.
        if(src.getPiece().getOwnerID() == target.getPiece().getOwnerID())
            throw new IllegalArgumentException("Target is on your team!");

        //Check that the attack is within range parameters.
        if(!withinRange(src, target));
            throw new IllegalArgumentException("Target not in range!");

        //Calls the method for the piece to perform the attack.
        src.getPiece().attackTarget(target.getPiece(), map);
    }

    public boolean withinRange(Tile src, Tile target){
        int min = src.getPiece().getAttackRangeMin();
        int max = src.getPiece().getAttackRangeMax();

        int diff = map.getDistanceTo(src, target);

        return diff >= min && diff <= max;

    }


}
