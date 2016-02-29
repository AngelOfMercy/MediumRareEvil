package com.example.angelofmercy.mediumrareevil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import Game.Game.Direction;
import Animation.AnimationHandler;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    //ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(new MainGamePanel(this));

        //init cursor image
        //image = (ImageView) findViewById(R.id.cursorView);
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
}
/*
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


    //These methods use AnimationHandler to animate the cursor
    public void moveCursorUp(View view) {
        AnimationHandler anim = new AnimationHandler(Direction.UP, this, image);
        anim.play();
    }
    public void moveCursorDown(View view){
        AnimationHandler anim = new AnimationHandler(Direction.DOWN, this, image);
        anim.play();
    }
    public void moveCursorLeft(View view){
        AnimationHandler anim = new AnimationHandler(Direction.LEFT, this, image);
        anim.play();
    }
    public void moveCursorRight(View view){
        AnimationHandler anim = new AnimationHandler(Direction.RIGHT, this, image);
        anim.play();
    }
    public void select(View view){
    }
}*/





