package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import Animation.Animation;
import Game.Tiles.Tile;
import Pieces.Piece;

public class Game{
    private Map map;
    private Player[] players;
    private int turn = 0;

    public Piece selected;

    private Bitmap greenHighlights;
    private Bitmap zoomedGreenHighlights;

    private Bitmap redHighlights;
    private Bitmap zoomedRedHighlights;

    public enum Direction {UP,
            DOWN,
            LEFT,
            RIGHT
    };

//---------------------------------------------------------------
//Constructors
//--------------------------------------------------------------

    public Game(Player[] p, Map m){
        map = m;
        players = p;
    }

//---------------------------------------------------------------
//Methods
//---------------------------------------------------------------

    /**
     * Increments current turn
     */
    public void nextTurn(){ ++turn; }

    /**
     * Returns the tile the cursor is over
     * @return
     */
    public Tile select() {
        return map.cursor.getLocation();
    }

    /**
     * Check if this piece can move to the given tile
     * @param p The piece that is moving
     * @param t The tile the piece is moving to
     * @return
     */
    public boolean checkMove(Piece p, Tile t) {

        if(!p.equals(selected)){
            p.findPossibleMoves(map.tileset);
        }

        if (p.moveTiles.contains(t) || p.attackTiles.contains(t)) {
            return true;
        }
        return false;
    }

    public void move(Animation anim, Piece p, Tile t){
        p.animating = true;
        map.getTileAt(p.getLocation()).removePiece();
        t.setPiece(p);
        map.currentAnimation = anim;
    }

    /**
     * Resize a bitmap by a by an x and y scalar
     * @param b bitmap to be resized
     * @param scaleX amount to scale horizontally
     * @param scaleY amount to scale vertically
     * @return the resized bitmap
     */
    private Bitmap resize(Bitmap b, float scaleX, float scaleY){
        int sizeX = (int)(b.getWidth() * scaleX);
        int sizeY = (int)(b.getHeight() * scaleY);
        return Bitmap.createScaledBitmap(b, sizeX, sizeY, false);
    }


//---------------------------------------------------------------
//Getters and Setters
//---------------------------------------------------------------

    /**
     * Method to set the highlight colours for tile selection. Separate from the map initializer for
     * personal clarity reasons.
     * @param green the bitmap to be used as a green highlight
     * @param red the bitmap to be used as a red highlight
     */
    public void setHighlights(Bitmap green, Bitmap red){
        greenHighlights = green;
        zoomedGreenHighlights = resize(green, 1.4f, 1.4f);

        redHighlights = red;
        zoomedRedHighlights = resize(red, 1.4f, 1.4f);
    }

    /**
     * Returns the current player.
     * @return
     */
    public int getCurrentPlayer(){ return turn & 1; }

    /**
     * Returns the map of the current game.
     * @return
     */
    public Map getMap(){
        return map;
    }

    /**
     * Draw the bitmaps on the screen
     * @param canvas the canvas object to draw on
     */
    public void draw(Canvas canvas){
        //1. draw scenery
        map.draw(canvas);

        //2. draw highlighted tiles (if piece is selected)
        if(selected != null){
            if(!map.getZoom()){
                for(Tile tile : selected.moveTiles) {
                    canvas.drawBitmap(greenHighlights, tile.screenPosX, tile.screenPosY, null);
                }
                for(Tile tile : selected.attackTiles){
                    canvas.drawBitmap(redHighlights, tile.screenPosX, tile.screenPosY, null);
                }
            }
            else{
                for(Tile tile : selected.moveTiles) {
                    canvas.drawBitmap(zoomedGreenHighlights, tile.screenPosX, tile.screenPosY, null);
                }
                for(Tile tile : selected.attackTiles){
                    canvas.drawBitmap(zoomedRedHighlights, tile.screenPosX, tile.screenPosY, null);
                }
            }
        }

        //3. draw cursor
        map.cursor.draw(canvas);
        //4. draw pieces
        //TODO account for zoomed graphics
        for(Piece p : map.getPieces()){
            p.draw(canvas, map.getTileAt(p.getLocation()).getScreenLocation());
        }
    }
}