package Pieces;

import android.graphics.Bitmap;

import java.util.ArrayList;

import Game.Map;
import Game.Tiles.Tile;

/**
 * Created by AngelOfMercy on 14/01/2016.
 */
public class WizardPiece extends Piece{

    public WizardPiece(int OWNER_ID, Bitmap bitmap){
        super("Wizard", OWNER_ID, bitmap);
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
}
