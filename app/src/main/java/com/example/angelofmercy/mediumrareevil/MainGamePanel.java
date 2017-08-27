package com.example.angelofmercy.mediumrareevil;

/**
 * Created by Larry on 2/22/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.io.File;

import Animation.Animation;
import Game.*;
import Game.Tiles.Tile;
import Game.Utility.Point;
import Pieces.Piece;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();

    private MainThread thread;  //thread used to draw on the canvas

    private Map map;

    private Game game;

    //258, //75
    private float xOff = 203, yOff = 88; //origin calculation constants
    private float zXOff = 275, zYOff = 55; //zoomed origin calculation constants
    private Point origin = calculateOrigin(); //screen location of point 0,0 on the map

    public boolean zoomed = false;

    private Context context;

    /**
     * Implementing the SurfaceView window in XML requires these three constructors
     */
    public MainGamePanel (Context context){
        super(context);
        this.context = context;
        init();
    }
    public MainGamePanel (Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        init();
    }
    public MainGamePanel (Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.context = context;
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

    /*Debug method for grid alignment*/
    public void setGrid(float x, float xMod, float y, float yMod, float xOrigin, float yOrigin){
        //recalculate origin
        if(!zoomed) {
            xOff = xOrigin;
            yOff = yOrigin;
        }
        else{
            zXOff = xOrigin;
            zYOff = yOrigin;
        }
        this.origin = calculateOrigin();

        map.setGrid(x, xMod, y, yMod, this.origin);
    }

    /**
     * Drawing method to draw on the canvas in the SurfaceView.
     * This method also updates the animation state of each object.
     */
    @Override
    protected void onDraw (Canvas canvas){
        //Canvas is when program is first loaded, so skip over at first.
        if(canvas != null) {
            //This call refreshes the canvas, ready to be updated for the next frame.
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);

            game.draw(canvas);
        }
    }

    /* Method to calculate origin for positioning purposes*/
    public Point calculateOrigin(){
        //screen width used needs to be found
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        //point 0,0
        if (!zoomed){
            return new Point(width/2 - xOff, yOff);
        }
        return new Point(width/2 - zXOff, zYOff);
    }

    public void zoomPanel(int inOut){
        Log.d(TAG, "" + inOut);
        zoomed = !zoomed;
        origin = calculateOrigin();
        map.setZoom(zoomed, origin);
    }

    public void move(Game.Direction d){
        Log.d(TAG, "" + d);
        map.moveCursor(d);
    }

    /**
     * Handles contextual selection
     */
    public void select(){
        //Get tile the cursor is on
        Tile tile = game.select();

        //If tile has a piece on it, check if...
        //-piece belongs to current player
        //  -if yes, set this as selected piece
        //  -if no, check if we already have a piece selected
        //      -if yes, check if we can attack this piece
        //          -if yes, build move and attack animation and pass on
        //          -if no, do nothing
        //      -if no, do nothing
        //If tile does not have a piece, check if...
        //-we have a piece selected
        //  -if yes, check if we can move to this space
        //      -if yes, build move animation and pass on
        //      -if no, do nothing
        //  -if no, do nothing

        //If tile has piece
        if(tile.hasPiece()){
            Piece p = tile.getPiece();
            //If piece belongs to current player
            if(p.getOwnerID() == game.getCurrentPlayer()){
                game.selected = p;
                p.findPossibleMoves(game.getMap().tileset);
            }
            //Else if piece does not belong to current player but we already have a piece selected
            else if(game.selected != null){
                //If selected piece can attack
                if(game.checkMove(game.selected, tile)){
                    Animation m = getAnim(buildAnimURL(game.selected, 1), game.selected, true);
                    Animation a = getAnim(buildAnimURL(game.selected, 2), game.selected, false);
                    //game.attack(m, a, game.selected, tile);
                }
            }
        }
        //If tile does not have a piece
        else{
            //If we already have a piece selected
            if(game.selected != null){
                //If selected piece can move to this tile
                if(game.checkMove(game.selected, tile)){
                    Animation m = getAnim(buildAnimURL(game.selected, 1), game.selected, true);
                    game.move(m, game.selected, tile);
                }
            }
        }
    }

    /**
     * Return a string pertaining to the name of the bitmap of the animation strip we want
     * the format is "_OwnerID_Direction_PieceName_Action"
     * @param p The piece we are animating
     * @param action The action we want (0 = death, 1 = walk, 2 = attack)
     * @return The final string
     */
    private String buildAnimURL(Piece p, int action){
        StringBuilder sb = new StringBuilder("_");
        if(p.getOwnerID() == 1){
            sb.append("blue");
        }
        else{
            sb.append("blue");
        }

        sb.append("_");

        switch (p.getFacing()){
            case DOWN:
                sb.append("down");
                break;
            case UP:
                sb.append("up");
                break;
            case LEFT:
                sb.append("left");
                break;
            case RIGHT:
                sb.append("right");
                break;
        }

        sb.append("_");
        sb.append(p.getName());
        sb.append("_");
        switch (action){
            case 0:
                sb.append("death");
                break;
            case 1:
                sb.append("walk");
                break;
            case 2:
                sb.append("attack");
                break;
        }
        return sb.toString();
    }

    private Animation getAnim(String animURL, Piece p, boolean moving){
        int id = context.getResources().getIdentifier(animURL, "drawable", context.getPackageName());
        Bitmap animationStrip = BitmapFactory.decodeResource(getResources(), id);
        //TODO We need a bit of ugliness to account for different frame sizes
        return new Animation(animationStrip, 50, 50, 100, p.xOff, p.yOff, moving);
    }

    //Utility method for the constructors, containing initializations for variables
    private void init(){
        getHolder().addCallback(this);

        map = new Map(BitmapFactory.decodeResource(getResources(), R.drawable.placeholder2),
                BitmapFactory.decodeResource(getResources(), R.drawable.cursor_placeholder), origin);

        Player players[] = {new Player("p0"), new Player("p1")};

        game = new Game(players, map);
        game.setHighlights(BitmapFactory.decodeResource(getResources(), R.drawable.green_highlight_2),
                BitmapFactory.decodeResource(getResources(), R.drawable.red_highlight_2));

        for(Piece p : map.getPieces()){
            String bitmapURL = p.getBitmapURL();

            int id = context.getResources().getIdentifier(bitmapURL, "drawable", context.getPackageName());

            p.setBitmap(BitmapFactory.decodeResource(getResources(), id));
        }




        this.setZOrderOnTop(true);
        SurfaceHolder holder = getHolder();
        holder.setFormat(PixelFormat.TRANSPARENT);

        thread = new MainThread(holder,this);

        setFocusable(true);
    }
}
