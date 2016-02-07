package Animation;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import Game.Game.Direction;

/**
 * Created by Larry on 2/7/2016.
 */
//Updates the position of the UI element (The cursor) the animation is performed on.
class XMLUpdater implements Animation.AnimationListener{
    float x = 0;
    float y = 0;
    Direction direction;
    ImageView image;
    Context context;

    //Constructor. See AnimationHandler for parameter details.
    XMLUpdater (Direction d, ImageView i, Context c){
        direction = d;
        image = i;
        context = c;
    }

    //Set new layout parameters by looking at the direction and modifying them accordingly
    @Override
    public void onAnimationEnd(Animation animation){
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) image.getLayoutParams();
        int posX = 0;
        int posY = 0;
        switch (direction){
            case UP:
                posX = Math.round(x+convertToPx(13, context));
                posY = Math.round(y+convertToPx(-12, context));
                break;
            case DOWN:
                posX = Math.round(x+convertToPx(-13, context));
                posY = Math.round(y+convertToPx(12, context));
                break;
            case LEFT:
                posX = Math.round(x+convertToPx(-22, context));
                posY = Math.round(y+convertToPx(-7, context));
                break;
            case RIGHT:
                posX = Math.round(x+convertToPx(22, context));
                posY = Math.round(y+convertToPx(7, context));
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

    /*=================HELPER METHODS=================*/
    public static float convertToPx(int dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    public static float convertToDp(int px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }
}
