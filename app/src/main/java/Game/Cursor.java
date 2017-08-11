package Game;

/**
 * UI design element used to signify the player's selection on the board.
 * It can be moved incrementally using buttons.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Game.Direction;
import Game.Tiles.Tile;
import Game.Utility.Point;

public class Cursor {
    private Tile tile;
    public int xPos = 0;
    public int yPos = 0;

    private boolean zoomed = false;

    Bitmap cursorImage;
    Bitmap zoomedCursorImage;

    public Cursor (Tile start, Bitmap bitmap){
        tile = start;
        this.cursorImage = bitmap;
        this.zoomedCursorImage = resize(cursorImage, 1.4f, 1.4f);
    }

    private Bitmap resize(Bitmap b, float scaleX, float scaleY){
        int sizeX = (int)(getWidth() * scaleX);
        int sizeY = (int)(getHeight() * scaleY);
        return Bitmap.createScaledBitmap(b, sizeX, sizeY, false);
    }

    public void setZoom(Boolean z){ zoomed = z; }

    public Tile getLocation(){
        return tile;
    }

    public void setLocation(Tile t){
        tile = t;
        xPos = t.getX();
        yPos = t.getY();
    }

    public int getWidth(){
        if(!zoomed) return cursorImage.getWidth();
        return zoomedCursorImage.getWidth();
    }

    public int getHeight(){
        if(!zoomed) return cursorImage.getHeight();
        return zoomedCursorImage.getHeight();
    }

    public void draw(Canvas canvas){
        if(!zoomed) {
            canvas.drawBitmap(cursorImage, tile.screenPosX, tile.screenPosY, null);
        }
        else{
            canvas.drawBitmap(zoomedCursorImage, tile.screenPosX, tile.screenPosY, null);
        }
    }
}
