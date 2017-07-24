package com.example.angelofmercy.mediumrareevil;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import Game.Game.Direction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static View gamePanel;
    private static Boolean fatFingerBar = true;
    private int ffbHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        gamePanel = findViewById(R.id.mainPanel);
        ffbHeight = toPx(75);
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

    /*Debug method for controlling input for grid alignment.*/
    /*
    public void sendValues(View view){
        try {
            Method m = gamePanel.getClass().getDeclaredMethod("setGrid",
                    float.class, float.class, float.class, float.class, float.class, float.class);

            EditText xEdit = (EditText)findViewById(R.id.x_var);
            EditText xModEdit = (EditText)findViewById(R.id.x_mod_var);
            EditText yEdit = (EditText)findViewById(R.id.y_var);
            EditText yModEdit = (EditText)findViewById(R.id.y_mod_var);
            EditText xOrignEdit = (EditText)findViewById(R.id.x_origin);
            EditText yOriginEdit = (EditText)findViewById(R.id.y_origin);

            float x = Float.parseFloat(xEdit.getText().toString());
            float xMod = Float.parseFloat(xModEdit.getText().toString());
            float y = Float.parseFloat(yEdit.getText().toString());
            float yMod = Float.parseFloat(yModEdit.getText().toString());
            float xOrig = Float.parseFloat(xOrignEdit.getText().toString());
            float yOrig = Float.parseFloat(yOriginEdit.getText().toString());

            m.invoke(gamePanel, x, xMod, y, yMod, xOrig, yOrig);
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
    */

    //----------------------------------------------------------------------------------------------------
    //The following five methods handle the left, right, up, down and select button pushes from the game UI.
    //They work by getting methods from the gamePanel object, which is a SurfaceView object, and invoking them.
    //----------------------------------------------------------------------------------------------------

    //Handle up button
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

    //Handle down button
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

    //Handle left button
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

    //Handle right button
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

    //Handle select button
    public void select(View view){
        try{
            Method m = gamePanel.getClass().getDeclaredMethod("select");
            m.invoke(gamePanel);
        }
        catch (NoSuchMethodException e){
            Log.d(TAG, "Method not found, " + e);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------------------

    //Handle pressing toggle button
    public void toggleFFB(View view){
        fatFingerBar = !fatFingerBar;

        if(fatFingerBar){
            transformWindow(0);
        }
        else{
            transformWindow(1);
        }
    }

    /*Method to deflate or expand the window for different control settings
    * @param ls: 1: drop ffb, expand board, 0: raise ffb, deflate board*/
    public void transformWindow(int ls){
        //first grab hold of all components: fat finger bar, toggle
        View ffb = findViewById(R.id.fatFingerBar);
        View ffbToggle = findViewById(R.id.fatFingerToggle);

        //we need to modify the margins of the toggle button and fat finger
        //bar so we don't leave whitespace behind when moving components around. This also
        //helps position the map to be in the center of the screen.
        ViewGroup.LayoutParams pFfb = ffb.getLayoutParams();
        RelativeLayout.LayoutParams lpFfb = (RelativeLayout.LayoutParams)pFfb;

        ViewGroup.LayoutParams pToggle = ffbToggle.getLayoutParams();
        RelativeLayout.LayoutParams lpToggle = (RelativeLayout.LayoutParams)pToggle;

        //drop ffb
        if(ls == 1){
            //drop ffb
            ffb.animate().translationY(ffbHeight).start();
            ffbToggle.animate().translationY(ffbHeight).start();

            //modify margins to combat whitespace
            lpFfb.setMargins(0, -ffbHeight, 0, 0);
            ffb.setLayoutParams(lpFfb);

            lpToggle.setMargins(0, 0, 0, ffbHeight);
            ffbToggle.setLayoutParams(lpToggle);
        }
        //do inverse to deflate board, raise ffb
        else if(ls == 0){
            ffb.animate().translationY(0).start();
            ffbToggle.animate().translationY(0).start();

            lpFfb.setMargins(0, 0, 0, 0);
            ffb.setLayoutParams(lpFfb);

            lpToggle.setMargins(0, 0, 0, 0);
            ffbToggle.setLayoutParams(lpToggle);
        }

        //scale the game panel
        try {
            Method m = gamePanel.getClass().getDeclaredMethod("zoomPanel", int.class);
            m.invoke(gamePanel, ls);
        }
        catch (NoSuchMethodException e) {
            Log.d(TAG, "Method not found, " + e);
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public int toPx(int dp){
        float logicalDensity = getResources().getDisplayMetrics().density;
        return (int) Math.ceil(dp * logicalDensity + 0.5f);
    }
}





