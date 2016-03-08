package com.example.angelofmercy.mediumrareevil;

/**
 * Created by Larry on 2/22/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import Game.Utility.Point;
import Pieces.Piece;
import Pieces.SoldierPiece;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;  //thread used to draw on the canvas

    private Piece soldier;

    /**
     * Implementing the SurfaceView window in XML requires these constructors
     */
    public MainGamePanel (Context context){
        super(context);
        init();
    }
    public MainGamePanel (Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }
    public MainGamePanel (Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init();
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

    /**
     * Drawing method to draw on the canvas in the SurfaceView.
     */
    @Override
    protected void onDraw (Canvas canvas){
        //Canvas is when program is first loaded, so skip over at first.
        if(canvas != null) {
            //this call refreshes the canvas, ready to be updated for the next frame
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            soldier.draw(canvas);
        }
    }

    //Utility method for the constructors
    private void init(){
        getHolder().addCallback(this);

        soldier = new SoldierPiece(1, BitmapFactory.decodeResource(getResources(), R.drawable.soldier_blue));

        this.setZOrderOnTop(true);
        SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);

        thread = new MainThread(holder,this);

        setFocusable(true);
    }
}
