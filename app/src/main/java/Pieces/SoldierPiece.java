package Pieces;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.angelofmercy.mediumrareevil.R;

import Game.Utility.Point;

/**
 * Created by AngelOfMercy on 14/01/2016.
 */
public class SoldierPiece extends Piece {

    public SoldierPiece(int OWNER_ID, Bitmap bitmap, Point origin){
        super("Soldier", OWNER_ID,  bitmap, origin);
    }
}
