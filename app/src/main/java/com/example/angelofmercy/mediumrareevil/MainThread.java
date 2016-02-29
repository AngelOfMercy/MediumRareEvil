package com.example.angelofmercy.mediumrareevil;

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
        long tickCount = 0L;
        Log.d(TAG, "Beginning Loop");
        while (running){
            tickCount++;
            //TODO: update and render game state
        }
        Log.d(TAG, "Game loop executed" + tickCount + "times");
    }

}