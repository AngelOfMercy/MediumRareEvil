package com.example.angelofmercy.mediumrareevil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import Game.Game.Direction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static View gamePanel;
    private static Boolean fatFingerBar = true;
    private int ffsHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        gamePanel = findViewById(R.id.mainPanel);
        ffsHeight = toPx(75);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Destroying...");
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopping...");
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveCursorUp(View view) {
        try {
            Method m = gamePanel.getClass().getDeclaredMethod("move", Direction.class);
            m.invoke(gamePanel, Direction.UP);
        }
        catch (NoSuchMethodException e) {
            Log.d(TAG, "Method not found, " + e);
        }
         catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void moveCursorDown(View view){
        try {
            Method m = gamePanel.getClass().getDeclaredMethod("move", Direction.class);
            m.invoke(gamePanel, Direction.DOWN);
        }
        catch (NoSuchMethodException e) {
            Log.d(TAG, "Method not found, " + e);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void moveCursorLeft(View view){
        try {
            Method m = gamePanel.getClass().getDeclaredMethod("move", Direction.class);
            m.invoke(gamePanel, Direction.LEFT);
        }
        catch (NoSuchMethodException e) {
            Log.d(TAG, "Method not found, " + e);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void moveCursorRight(View view){
        try {
            Method m = gamePanel.getClass().getDeclaredMethod("move", Direction.class);
            m.invoke(gamePanel, Direction.RIGHT);
        }
        catch (NoSuchMethodException e) {
            Log.d(TAG, "Method not found, " + e);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void select(View view){
        try {
            Method m = gamePanel.getClass().getMethod("test");
            m.invoke(gamePanel);
        }
        catch (NoSuchMethodException e) {
            Log.d(TAG, "Method not found, " + e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void toggleFFB(View view){
        fatFingerBar = !fatFingerBar;
        View ffb = findViewById(R.id.fatFingerBar);
        ViewGroup.LayoutParams layoutParams = ffb.getLayoutParams();

        if(fatFingerBar){
            //TODO: CONVERT TO DP
            layoutParams.height = ffsHeight;
            ffb.setLayoutParams(layoutParams);
        }
        else{
            layoutParams.height = 0;
            ffb.setLayoutParams(layoutParams);
        }

    }

    public int toPx(int dp){
        float logicalDensity = getResources().getDisplayMetrics().density;
        return (int) Math.ceil(dp * logicalDensity + 0.5f);
    }
}





