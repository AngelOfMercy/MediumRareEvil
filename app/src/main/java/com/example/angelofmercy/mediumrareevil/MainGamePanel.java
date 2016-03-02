package com.example.angelofmercy.mediumrareevil;

/**
 * Created by Larry on 2/22/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

    private MainThread thread;

    private Piece soldier;

    public MainGamePanel (Context context){
        super(context);
        getHolder().addCallback(this);

        soldier = new SoldierPiece(1, BitmapFactory.decodeResource(getResources(), R.drawable.soldier_blue));

        thread = new MainThread(getHolder(),this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged (SurfaceHolder holder, int format, int width, int height){
    }

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

    @Override
    public boolean onTouchEvent (MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            soldier.handleAction((int)event.getX(), (int)event.getY());
            if (event.getY() > getHeight() - 50){
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            }
            else {
                Log.d(TAG, "Coords: x= " + event.getX() + ", y= " + event.getY());
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if(soldier.getTouched() == true){
                Point p = new Point((int)event.getX(), (int)event.getY());
                soldier.setLocation(p);
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            soldier.setTouched(false);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw (Canvas canvas){
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.soldier_blue), 10, 10, null);
    }
}
