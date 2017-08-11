package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import Game.Utility.Point;
import java.util.ArrayList;
import android.util.Log;

import Game.Tiles.*;
import Pieces.*;
import Game.Game.Direction;

public class Map{

    private String TAG = Map.class.getSimpleName();

    private ArrayList<Piece> pieces = new ArrayList<Piece>();

    public Tile[][] tileset;

    /*Only used to setup the starting population for each player*/
    //N = None
    //W = Wizard
    //S = Soldier
    //T = Trebuchet
    //P = Palisade
    //R = Spearman
    //H = Heavy
    //A = Archer
    //C = Cleric
    //B = Standard Bearer
    private String redStart[][] = {{"H", "A", "T", "W", "B", "T", "A", "H"},
                                   {"C", "P", "R", "R", "R", "R", "P", "C"},
                                   {"N", "N", "S", "S", "S", "S", "N", "N"},
                                   {"N", "N", "N", "N", "N", "N", "N", "N"},
                                   {"N", "N", "N", "N", "N", "N", "N", "N"},
                                   {"N", "N", "N", "N", "N", "N", "N", "N"},
                                   {"N", "N", "N", "N", "N", "N", "N", "N"},
                                   {"N", "N", "N", "N", "N", "N", "N", "N"}};

    private String blueStart[][] = {{"N", "N", "N", "N", "N", "N", "N", "N"},
                                    {"N", "N", "N", "N", "N", "N", "N", "N"},
                                    {"N", "N", "N", "N", "N", "N", "N", "N"},
                                    {"N", "N", "N", "N", "N", "N", "N", "N"},
                                    {"N", "N", "N", "N", "N", "N", "N", "N"},
                                    {"N", "N", "S", "S", "S", "S", "N", "N"},
                                    {"C", "P", "R", "R", "R", "R", "P", "C"},
                                    {"H", "A", "T", "B", "W", "T", "A", "H"}};

    private Bitmap mapGraphics;
    private Bitmap zoomedMapGraphics;

    public Point origin;

    public Cursor cursor;

    private boolean zoomed = false;


    //-------------------------------------------------------------
    //Constructors
    //-------------------------------------------------------------
    public Map(Bitmap mapBitmap, Bitmap cursorBitmap, Point origin){
        this.mapGraphics = mapBitmap;
        this.zoomedMapGraphics = resize(mapGraphics, 1.4f, 1.4f);
        this.origin = origin;
        this.tileset = new Tile[8][8];

        for(int x = 0; x < tileset.length; ++x){
            for(int y = 0; y < tileset[x].length; ++y){
                tileset[x][y] = new Tile(x, y, origin, cursorBitmap);
            }
        }

        this.cursor = new Cursor(getTileAt(0, 0), cursorBitmap);

        populate(redStart, 0);
        populate(blueStart, 1);

    }

    //-------------------------------------------------------------
    //Methods
    //-------------------------------------------------------------

    /**Populates the initial game piece layout*/
    private void populate(String[][] deployment, int player){
        Direction dir;
        if(player == 0){
            dir = Direction.DOWN;
        }
        else{
            dir = Direction.UP;
        }

        for(int x = 0; x < 8; ++x){
            for(int y = 0; y < 8; ++y){
                Piece p;
                switch (deployment[y][x]){
                    case "N":
                        break;
                    case "W":
                        p = new WizardPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "S":
                        p = new SoldierPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "T":
                        p = new TrebuchetPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "P":
                        p = new PalisadePiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "R":
                        p = new SpearmanPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "H":
                        p = new HeavyPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "A":
                        p = new ArcherPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "C":
                        p = new ClericPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                    case "B":
                        p = new SoldierPiece(player, dir);
                        tileset[x][y].setPiece(p);
                        pieces.add(p);
                        break;
                }
            }
        }
    }

    /**
     * Moves the cursor one tile in the given direction.
     * @param d Direction of movement.
     * @return If successful, returns true.
     */
    public boolean moveCursor(Game.Direction d){
        int x = cursor.xPos;
        int y = cursor.yPos;

        switch (d) {
            case UP:
                --y;
                if (y < 0) {
                    y = 0;
                }
                cursor.setLocation(tileset[x][y]);
                break;
            case DOWN:
                ++y;
                if (y > 8) {
                    y = 8;
                }
                cursor.setLocation(tileset[x][y]);
                break;
            case LEFT:
                ++x;
                if (x > 8) {
                    x = 8;
                }
                cursor.setLocation(tileset[x][y]);
                break;
            case RIGHT:
                --x;
                if (x < 0) {
                    x = 0;
                }
                cursor.setLocation(tileset[x][y]);
                break;
        }
        return true;
    }

    public boolean movePiece(Piece p, Direction d){
        Point loc = p.getLocation();
        int x = (int)loc.x;
        int y = (int)loc.y;
        Tile targetTile = getAdjacentTile(x, y, d);

        if(targetTile != null && targetTile.containsPiece()){
            p.setLocation(new Point(targetTile.getX(), targetTile.getY()));

            Tile homeTile = getTileAt(x, y);
            if(homeTile.containsPiece(p)){
                homeTile.removePiece();
                return targetTile.setPiece(p);
            }
            return false;
        }
        else{
            return false;
        }
    }

    private Tile getAdjacentTile(int x, int y, Game.Direction d){

        switch(d){
            case UP:
                --y;
            case DOWN:
                ++y;
            case LEFT:
                --x;
            case RIGHT:
                ++x;
        }
        return getTileAt(x, y);
    }

    public void select(Piece p){
        p.findPossibleMoves(tileset);
        Log.d(TAG, "Movetiles: ");
        for(Tile tile : p.moveTiles){
            Log.d(TAG, "x: " + tile.getX() + ", y: " + tile.getY());
        }

        Log.d(TAG, "Attacktiles: ");
        for(Tile tile : p.attackTiles){
            Log.d(TAG, "x: " + tile.getX() + ", y: " + tile.getY());
        }
    }

    //-------------------------------------------------------------
    //Getters and Setters
    //-------------------------------------------------------------

    /*Method to return the tile at a location*/
    public Tile getTileAt(int x, int y){
        return tileset[x][y];
    }

    /*Method to move a piece from one tile to another*/
    public void movePiece(Tile t1, Tile t2){
        t2.setPiece(t1.getPiece());
        t1.removePiece();
    }

    /*Method to translate points for easier programming*/
    public Tile getTileAt(Point p){
        int x = (int)p.x;
        int y = (int)p.y;
        return getTileAt(x, y);
    }

    /*Method to return ArrayList of adjacent tiles*/
    public ArrayList<Tile> getAdjacentTiles(Tile p){

        ArrayList<Tile> adjTiles = new ArrayList<>();

        int pX = p.getX();
        int pY = p.getY();

        if(pX == 0){ adjTiles.add(getTileAt(1, pY)); }
        else if(pX == 8){ adjTiles.add(getTileAt(7, pY)); }
        else{
            adjTiles.add(getTileAt(pX + 1, pY));
            adjTiles.add(getTileAt(pX - 1, pY));
        }

        if(pY == 0){ adjTiles.add(getTileAt(pX, 1)); }
        else if(pY == 8){ adjTiles.add(getTileAt(pX, 7)); }
        else{
            adjTiles.add(getTileAt(pX, pY + 1));
            adjTiles.add(getTileAt(pX, pY - 1));
        }

        return adjTiles;
    }

    public int getDistanceTo(Tile src, Tile target){
        return Math.abs(src.getX() - target.getX()) + Math.abs(src.getY() - target.getY());
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
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

    /*Specify mode to draw in, adjust origin*/
    public void setZoom (Boolean z, Point o){
        zoomed = z;
        cursor.setZoom(z);
        this.origin = o;

        for(int i = 0; i < tileset.length; ++i){
            for(int j = 0; j < tileset[i].length; ++j){
                tileset[i][j].setOrigin(o);
                tileset[i][j].calculateScreenPos(z);
            }
        }
    }

    public boolean getZoom(){ return zoomed; }

    /*Debug method for grid UI adjustment*/
    public void setGrid(float x, float xMod, float y, float yMod, Point o){
        this.origin = o;
        for(int i = 0; i < tileset.length; ++i){
            for(int j = 0; j < tileset[i].length; ++j){
                tileset[i][j].setConstants(x, xMod, y, yMod, o);
            }
        }
    }

    /**
     * Draw the bitmaps on the screen
     * @param canvas the canvas object to draw on
     */
    public void draw (Canvas canvas){
        if(!zoomed) {
            canvas.drawBitmap(mapGraphics,
                    (canvas.getWidth() / 2) - (mapGraphics.getWidth() / 2),
                    (canvas.getHeight() / 2) - (mapGraphics.getHeight() / 2),
                    null);
        }
        else {
            canvas.drawBitmap(zoomedMapGraphics,
                    (canvas.getWidth() / 2) - (zoomedMapGraphics.getWidth() / 2),
                    (canvas.getHeight() / 2) - (zoomedMapGraphics.getHeight() / 2),
                    null);
        }
        //Comment or uncomment this to toggle the debug grid placement view.
        /*
        for(int x = 0; x < tileset.length; ++x){
            for(int y = 0; y < tileset[x].length; ++y){
                tileset[x][y].debugDraw(canvas);
            }
        }
        */

        cursor.draw(canvas);
        for(Piece p : pieces){
            p.draw(canvas, getTileAt(p.getLocation()).getScreenLocation());
        }
    }
}