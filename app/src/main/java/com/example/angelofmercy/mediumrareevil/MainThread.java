package com.example.angelofmercy.mediumrareevil;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Larry on 2/22/2016.
 */
public class MainThread extends Thread {

    private static final String TAG = MainThread.class.getSimpleName();

    private SurfaceHolder holder;
    private MainGamePanel gamePanel;

    private boolean running;
    public void setRunning (boolean running){
        this.running = running;
    }

    public MainThread (SurfaceHolder holder, MainGamePanel gamePanel){
        super();
        this.holder = holder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run(){
        Canvas canvas;
        Log.d(TAG, "Beginning Loop");

        while (running){
            canvas = null;
            try{
                canvas = this.holder.lockCanvas();
                synchronized (holder){
                    this.gamePanel.onDraw(canvas);
                }
            }finally {
                if (canvas != null){
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}