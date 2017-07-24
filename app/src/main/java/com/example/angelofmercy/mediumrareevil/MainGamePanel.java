package com.example.angelofmercy.mediumrareevil;

/**
 * Created by Larry on 2/22/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.File;

import Game.*;
import Game.Utility.Point;
import Pieces.Piece;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;  //thread used to draw on the canvas

    private Map map;

    private Game game;

    //258, //75
    private float xOff = 203, yOff = 88; //origin calculation constants
    private float zXOff = 275, zYOff = 55; //zoomed origin calculation constants
    private Point origin = calculateOrigin(); //screen location of point 0,0 on the map

    public boolean zoomed = false;

    /**
     * Implementing the SurfaceView window in XML requires these three constructors
     */
    public MainGamePanel (Context context){
        super(context);
        init(context);
    }
    public MainGamePanel (Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }
    public MainGamePanel (Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height){
    }

    /**
     * Set the thread running only when the SurfaceView has been created
     */
    @Override
    public void surfaceCreated (SurfaceHolder holder){
        Log.d(TAG, "Creating thread...");
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed (SurfaceHolder holder){
        boolean retry = true;
        while (retry){
            try{
                thread.join();
                retry = false;
            }
            catch (InterruptedException e){
            }
        }
    }

    /*Debug method for grid alignment*/
    public void setGrid(float x, float xMod, float y, float yMod, float xOrigin, float yOrigin){
        //recalculate origin
        if(!zoomed) {
            xOff = xOrigin;
            yOff = yOrigin;
        }
        else{
            zXOff = xOrigin;
            zYOff = yOrigin;
        }
        this.origin = calculateOrigin();

        map.setGrid(x, xMod, y, yMod, this.origin);
    }

    /**
     * Drawing method to draw on the canvas in the SurfaceView.
     * This method also updates the animation state of each object.
     */
    @Override
    protected void onDraw (Canvas canvas){
        //Canvas is when program is first loaded, so skip over at first.
        if(canvas != null) {
            //This call refreshes the canvas, ready to be updated for the next frame.
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);

            map.draw(canvas);
        }
    }

    /* Method to calculate origin for positioning purposes*/
    public Point calculateOrigin(){
        //screen width used needs to be found
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        //point 0,0
        if (!zoomed){
            return new Point(width/2 - xOff, yOff);
        }
        return new Point(width/2 - zXOff, zYOff);
    }

    public void zoomPanel(int inOut){
        Log.d(TAG, "" + inOut);
        zoomed = !zoomed;
        origin = calculateOrigin();
        map.setZoom(zoomed, origin);
    }

    public void move(Game.Direction d){
        Log.d(TAG, "" + d);
        map.moveCursor(d);
    }

    public void select(){
        game.select();
    }

    public Map getMap(){
        return map;
    }

    //Utility method for the constructors, containing initializations for variables
    private void init(Context context){
        getHolder().addCallback(this);

        map = new Map(BitmapFactory.decodeResource(getResources(), R.drawable.placeholder2),
                BitmapFactory.decodeResource(getResources(), R.drawable.cursor_placeholder), origin);

        Player players[] = {new Player("p1"), new Player("p2")};

        game = new Game(players, map);

        for(Piece p : map.getPieces()){
            String bitmapURL = p.getBitmapURL();
            Log.d(TAG, "x: " + p.getLocation().x + ", y: " + p.getLocation().y + " [" + bitmapURL + "]");

            int id = context.getResources().getIdentifier(bitmapURL, "drawable", context.getPackageName());

            p.setBitmap(BitmapFactory.decodeResource(getResources(), id));
        }


        this.setZOrderOnTop(true);
        SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);

        thread = new MainThread(holder,this);

        setFocusable(true);
    }
}
