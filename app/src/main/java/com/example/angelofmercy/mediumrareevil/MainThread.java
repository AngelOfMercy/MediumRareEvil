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
    public MainThread (SurfaceHolder holder, MainGamePanel gamePanel){
        super();
        this.holder = holder;
        this.gamePanel = gamePanel;
    }

    public void setRunning (boolean running){
        this.running = running;
        long tickCount = 0L;
        while(running) {
            tickCount++;
        }
        Log.d(TAG, "Game loop executed" + tickCount + "times");
    }

    @Override
    public void run(){
        while (running){

        }
    }

}