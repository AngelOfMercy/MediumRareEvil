package Pieces;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

import Game.Game.*;
import Game.Tiles.Tile;
import Game.Utility.Point;
import Game.Map;
public abstract class Piece {

    //----------------------------------------------------------------------------------------------
    //Constants and Variables.
    //----------------------------------------------------------------------------------------------
    /**
     * Utility
     */
    private static int ID_COUNTER = 0;
    String TAG = Piece.class.getSimpleName();

    //This string is used to find which bitmap to use for drawing.
    //The convention used is "_colour_direction_type"
    private String bitmapURL;
    private Bitmap bitmap;

    //Each image needs to be centered on its tile differently due to their different sizes, so have
    // an offset for each type of unit.
    public int xOff = 0;
    public int yOff = 0;

    //When selected, store a list of possible move and attack tiles
    public ArrayList<Tile>moveTiles = new ArrayList<>();
    public ArrayList<Tile>attackTiles = new ArrayList<>();

    //When animating, we dont want to draw the static image
    public boolean animating = false;

    /**
     * Unit Statistics
     * There are the default rules for a unit.
     */
    protected int ATTACK_RANGE_MIN = 0, ATTACK_RANGE_MAX = 0;
    protected int ATTACK_DAMAGE = 1;
    protected int MAX_HP = 2;

    /**
     * Unit Status
     * Current Condition of the unit
     */
    protected int CURRENT_HP = MAX_HP;
    private Point CURRENT_LOCATION = new Point(0,0);
    private Direction UNIT_FACING = Direction.UP;
    /**
     * Unit Identifiers
     * Theses are the ways for the unit to be uniquely identified
     */
    private String name;
    private int UNIT_ID;
    private int OWNER_ID;

    //----------------------------------------------------------------------------------------------
    //Constructors
    //----------------------------------------------------------------------------------------------
    public Piece(String name, int OWNER_ID, Direction initialDirection){
        this.name = name;
        this.UNIT_ID = ID_COUNTER++;
        this.OWNER_ID = OWNER_ID;
        this.UNIT_FACING = initialDirection;

        this.bitmapURL = buildURL();

        switch(name){
            case "heavy":
                xOff = -3;
                yOff = -45;
                break;
            case "archer":
                xOff = -5;
                yOff = -50;
                break;
            case "trebuchet":
                xOff = -3;
                yOff = -44;
                break;
            case "wizard":
                xOff = -5;
                yOff = -50;
                break;
            case "cleric":
                xOff = -5;
                yOff = -45;
                break;
            case "palisade":
                xOff = 1;
                yOff = -45;
                break;
            case "soldier":
                xOff = -2;
                yOff = -52;
                break;
            case "spearman":
                xOff = -5;
                yOff = -54;
        }
    }

    //----------------------------------------------------------------------------------------------
    //Methods.
    //----------------------------------------------------------------------------------------------
    private String buildURL(){
        StringBuilder sb = new StringBuilder("_");
        if(OWNER_ID == 1){
            sb.append("blue");
        }
        else{
            sb.append("blue");
        }

        sb.append("_");

        switch (UNIT_FACING){
            case DOWN:
                sb.append("down");
                break;
            case UP:
                sb.append("up");
                break;
            case LEFT:
                sb.append("left");
                break;
            case RIGHT:
                sb.append("right");
                break;
        }

        sb.append("_");
        sb.append(name);
        return sb.toString();
    }
    /**
     *Set the units max hp, and changes the current hp to have the same total 'damage' inflicted on the unit.
     * @param hp A natural number greater to or equal to 1.
     */
    protected void setDefaultHP(int hp){
        if(hp < 1)
            throw new IllegalArgumentException("Max HP cannot be set to less than 1.");
        int difference = MAX_HP - CURRENT_HP;
        this.MAX_HP = 2;
        CURRENT_HP = MAX_HP - difference;
    }

    public boolean checkTile(Tile tile){
        if(tile.hasPiece()){
            if(tile.getPiece().getOwnerID() != this.getOwnerID()){
                attackTiles.add(tile);
                return true;
            }
            return false;
        }
        else{
            moveTiles.add(tile);
            return false;
        }
    }
    /**
     * Executes an attack against a target.
     * Determines if the target is legal and deals the appropriate amount of damage.
     *
     * Does not determine if the target is in range.
     * Does not move the unit into the square if the target is killed.
     * @param target
     * @return returns true, if the unit is killed.
     */
    public boolean attackTarget(Piece target, Map m){
        return target.dealDamage(this.ATTACK_DAMAGE);
    }

    /**
     * Inflicts an amount of damage on this piece.
     *
     * @param dmg The amount of damage inflicted. If the value is negative, it will instead apply as healing.
     *            This cannot cause a unit ti go above its max HP.
     * @return True if the damage is lethal. Brings the piece to 0 or less HP.
     */
    public boolean dealDamage(int dmg){
        this.CURRENT_HP = this.CURRENT_HP - dmg;
        this.CURRENT_HP = Math.min(this.MAX_HP, this.CURRENT_HP);
        return CURRENT_HP <= 0;
    }
    //----------------------------------------------------------------------------------------------
    //Getters and Setters.
    //----------------------------------------------------------------------------------------------

    /**
     * Get the damage for this units' attacks.
     * @return
     */
    public int getAttackDamage(){
        return ATTACK_DAMAGE;
    }

    public Direction getFacing() { return UNIT_FACING; }


    public void findPossibleMoves(Tile[][] tileset){  }

    /**
     * Get the minimum distance of the units attack.
     * A value of 0 means that it attacks on tile entry.
     * @return
     */
    public int getAttackRangeMin(){
        return ATTACK_RANGE_MIN;
    }

    /**
     * Get the maximum distance of the units attacks.
     * It cannot have a ranged attack if the minimum attack range is 0.
     * @return
     */
    public int getAttackRangeMax(){
        if(ATTACK_RANGE_MIN == 0)
            return 0;
        return ATTACK_RANGE_MAX;
    }

    /**
     * Returns the units name
     * @retrun
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the units unique identifier
     */
    public int getUnitID(){
        return UNIT_ID;
    }

    /**
     * Returns the Unique Identifier for the player that controls the unit.
     * @return
     */
    public int getOwnerID(){
        return OWNER_ID;
    }

    /**
     * Returns the board coordinates for this piece
     * @return
     */
    public Point getLocation(){
        return CURRENT_LOCATION;
    }

    /**
     * Returns the bitmap url for this piece
     * @return
     */
    public String getBitmapURL(){ return bitmapURL; }
    /**
     * Sets the board location and calculates the on screen position of the piece
     * @param p Board coordinates
     * @return
     */
    public boolean setLocation(Point p){
        CURRENT_LOCATION = p;
        return true;
    }


    public void setBitmap(Bitmap bm){
        bitmap = bm;
    }


    public void draw(Canvas canvas, Point p){
        if(!animating) {
            canvas.drawBitmap(bitmap, p.x + xOff,
                    p.y + yOff, null);
        }
    }
}
