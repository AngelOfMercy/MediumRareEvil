package com.example.angelofmercy.mediumrareevil;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import Game.Game.Direction;
import Animation.AnimationHandler;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {
    ImageView image;

    //animations for moving the cursor
    Animation animUp;
    Animation animDown;
    Animation animLeft;
    Animation animRight;

    //init animations and assign animation listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init cursor image
        image = (ImageView) findViewById(R.id.cursorView);
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
    public void select(){
        //TODO Context dependent selection; affirmation, etc.
    }



    /*class GameView extends SurfaceView implements Runnable {

        Thread gameThread = null;
        SurfaceHolder holder;
        volatile boolean playing;
        Canvas canvas;
        Paint paint;
        long fps;
        private long timeThisFrame;
        Bitmap bitmapTest;
        boolean isMoving = false;
        float walkSpeedPerSecond = 150;
        float xPos = 10;

        float runTime;

        public GameView (Context context, float time){
            super(context);
            runTime = time;
            holder = getHolder();
            paint = new Paint();
            bitmapTest = BitmapFactory.decodeResource(this.getResources(), R.drawable.soldier_blue);
        }

        @Override
        public void run(){
            while(playing){
                long startFrameTime = System.currentTimeMillis();

                update();
                draw();

                timeThisFrame = System.currentTimeMillis() - startFrameTime;
                if (timeThisFrame > 0) {
                    fps = 1000 / timeThisFrame;
                }
            }
        }

        public void update(){
            if (isMoving) {
                xPos = xPos + walkSpeedPerSecond;
            }
        }

        public void draw(){
            if(holder.getSurface().isValid()){
                canvas = holder.lockCanvas();
                canvas.drawColor(Color.argb(255, 26, 128, 182));
                paint.setColor(Color.argb(255, 249, 129, 0));
                paint.setTextSize(45);
                canvas.drawText("FPS:" + fps, 20, 40, paint);
                canvas.drawBitmap(bitmapTest, xPos, 200, paint);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e){
                Log.e("Error:", "joining thread");
            }
        }

        public void resume(){
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }*/
}
