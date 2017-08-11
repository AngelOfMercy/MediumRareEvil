package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Tiles.Tile;
import Pieces.Piece;

public class Game{
    private Map map;
    private Player[] players;
    private int turn = 0;

    private Piece selected;

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
     * Handles cursor selection by checking the tile the cursor is over and handling contextually
     * @return
     */
    public boolean select(){
        //Get tile cursor is over
        Tile tile = map.getTileAt(map.cursor.xPos, map.cursor.yPos);

        //If there is a piece on this tile, make this the selected piece...
        if(tile.hasPiece()){
            Piece p = tile.getPiece();

            //if it belongs to the correct player
            if(p.getOwnerID() == (turn & 1)){
                selected = p;
                map.select(selected);
                return true;
            }

            //Otherwise, check if we have a selected piece, if we do then we are probably attackng
            //and enemy piece, so let the movepiece method handle this one.
            else if(selected != null){
                return movePiece(selected, tile);
            }
        }
        //If we already have a piece selected, then attempt to move piece to this tile
        else if(selected != null){
            return movePiece(selected, tile);
        }
        return false;
    }
    /**
     * Moves the given piece to the given tile
     *
     * @param p The piece to move
     * @param t The tile to move the piece.
     * @return Returns true if the piece was succesfully moved.
     */
    public boolean movePiece(Piece p, Tile t){
        map.movePiece(map.getTileAt(p.getLocation()), t);
        p.setLocation(t.getLocation());
        return true;
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
        map.draw(canvas);

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
    }
}