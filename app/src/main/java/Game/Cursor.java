package Game;

/**
 * UI design element used to signify the player's selection on the board.
 * It can be moved incrementally using buttons.
 *
 * Created by Larry on 1/19/2016.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Game.Direction;
import Game.Utility.Point;

public class Cursor {
    private Point CURRENT_LOCATION;
    private Point SCREEN_LOCATION;
    Bitmap bitmap;

    public Cursor (Point origin, Bitmap bitmap){
        CURRENT_LOCATION = new Point(0,0);
        SCREEN_LOCATION = origin;
        this.bitmap = bitmap;
    }

    /*Directional movement method adds and subtracts from class fields.*/
    public void setLocation(Point p, Point origin){
        CURRENT_LOCATION = p;
        SCREEN_LOCATION = setScreenLocation(origin);
    }

    /**calculate pixel position based on origin and piece coordinates*/
    private Point setScreenLocation(Point origin){
        /*algorithm: measure along one axis the distance in pixels, then add modifier for
        distance along the opposite axis due to the diagonal nature of the game view.
        Do the same for the other axis.*/
        Float x = origin.getX()+(CURRENT_LOCATION.getX()*-105)+(CURRENT_LOCATION.getY()*-63);
        Float y = origin.getY()+(CURRENT_LOCATION.getY()*59)+(CURRENT_LOCATION.getX()*-33);
        Point xy = new Point(x, y);

        return xy;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, SCREEN_LOCATION.x, SCREEN_LOCATION.y, null);
    }
}
