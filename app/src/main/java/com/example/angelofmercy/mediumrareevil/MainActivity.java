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

        //init animations (just load prewritten XML files)
        animUp = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_up);
        animDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_down);
        animLeft = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_left);
        animRight = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move_right);

        //assign each animation a listener with it's direction parameter (1,2,3,4)
        animUp.setAnimationListener(new Updater(1));
        animDown.setAnimationListener(new Updater(2));
        animLeft.setAnimationListener(new Updater(3));
        animRight.setAnimationListener(new Updater(4));

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


    public void moveCursorUp(View view) { image.startAnimation(animUp); }

    public void moveCursorDown(View view){
        image.startAnimation(animDown);
    }

    public void moveCursorLeft(View view){
        image.startAnimation(animLeft);
    }

    public void moveCursorRight(View view){
        image.startAnimation(animRight);
    }

    public void select(){
        //TODO Context dependent selection; affirmation, etc.
    }


    /*=================HELPER METHODS=================*/

    //conversion of px to dp.
    public float convertToDp(int px){
        Resources r = getResources();
        DisplayMetrics metrics = r.getDisplayMetrics();
        float dp = px/(metrics.densityDpi/160f);
        return dp;
    }
    //vice versa
    public float convertToPx(int dp){
        Resources r = getResources();
        DisplayMetrics metrics = r.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }


    /*=====================CLASSES=====================*/

    //Updates the position of the UI element the animation is performed on.
    private class Updater implements Animation.AnimationListener{
        float x = 0;
        float y = 0;
        int direction;

        Updater (int d){
            direction = d;
        }

        //Set new layout parameters by looking at the direction and modifying them accordingly
        @Override
        public void onAnimationEnd(Animation animation){
            //1 = up, 2 = down, 3 = left, 4 = right
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) image.getLayoutParams();
            int posX = 0;
            int posY = 0;
            switch (direction){
                case 1:
                    posX = Math.round(x+convertToPx(13));
                    posY = Math.round(y+convertToPx(-12));
                    break;
                case 2:
                    posX = Math.round(x+convertToPx(-13));
                    posY = Math.round(y+convertToPx(12));
                    break;
                case 3:
                    posX = Math.round(x+convertToPx(-22));
                    posY = Math.round(y+convertToPx(-7));
                    break;
                case 4:
                    posX = Math.round(x+convertToPx(22));
                    posY = Math.round(y+convertToPx(7));
                    break;
            }

            if (posX != 0 && posY != 0){
                lp.leftMargin = posX;
                lp.topMargin = posY;
                image.setLayoutParams(lp);
            }else{} //TODO displaying error messages


        }

        @Override
        public void onAnimationStart(Animation animation) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) image.getLayoutParams();
            x = lp.leftMargin;
            y = lp.topMargin;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
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
