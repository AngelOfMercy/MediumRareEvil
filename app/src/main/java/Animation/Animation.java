package Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.sql.Time;
import java.util.Timer;
import java.util.Vector;

import Game.Utility.Point;

public class Animation {
    int frame = 0;
    int frames;

    private Bitmap bitmap;

    private int frameWidth;
    private int frameHeight;

    private int offset;

    private float period;
    private float bufferTime;

    private Point screenPos;

    private int xOff = 0;
    private int yOff = 0;

    private float speed = 0;
    private float maxSpeed = 1.0f;
    private float acceleration = 0;

    private Point destination;

    private boolean moving;

    public Animation(Bitmap bitmap, int frameWidth, int frameHeight, float period, int xOff, int yOff, boolean moving){
        this.bitmap = bitmap;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.period = period;
        this.xOff = xOff;
        this.yOff = yOff;
        this.moving = moving;

        frames = Math.round(bitmap.getWidth()/frameWidth);
    }

//---------------------------------------------------------------
//Methods
//---------------------------------------------------------------

    /**
     * Handles time based frame advancement and looping of the animation
     */
    public void tick(){
        //TODO figure out android time functions
        if(true){

        }
        frame ++;
        offset += frameWidth;
    }

    /**
     * Handle movement of the animation across the screen
     */
    public void move(){
        speed += acceleration;
        if(speed > maxSpeed){speed = maxSpeed;}
        Point vector = new Point(screenPos.x - destination.x, screenPos.y - destination.y);
        //TODO Figure out Android vector functions
    }

//---------------------------------------------------------------
//Getters and Setters
//---------------------------------------------------------------


    public boolean isDone(){
        if(frames <= 0){
            return true;
        }
        return false;
    }

    public boolean isMoving(){
        return moving;
    }

    public void draw(Canvas canvas){

    }
}
