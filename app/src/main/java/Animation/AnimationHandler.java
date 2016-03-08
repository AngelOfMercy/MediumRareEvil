package Animation;

/**
 * Created by AngelOfMercy on 7/02/2016.
 */
import android.media.Image;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.example.angelofmercy.mediumrareevil.R;
import Game.Game.Direction;
import android.content.Context;
import android.widget.ImageView;


//Used in handling the animation of the cursor.
public class AnimationHandler {
    Animation anim;
    ImageView image;

    /**
     * Constructor. Depending on the direction passed in,
     * we load up a different animation, up down, left or right
     * @param d the direction constant.
     * @param context We need the context for loading the animation and
     * passing to the animation listener; XMLUpdater.
     * @param i  the image of the cursor, which we also need to
     * pass to XMLUpdater, and save to call the animation on
     */
    public AnimationHandler(Direction d, Context context, ImageView i){
        image = i;
        switch (d){
            case UP:
                anim = AnimationUtils.loadAnimation(context.getApplicationContext(),
                        R.anim.move_up);
                anim.setAnimationListener(new XMLUpdater(Direction.UP, image, context));
                break;
            case DOWN:
                anim = AnimationUtils.loadAnimation(context.getApplicationContext(),
                        R.anim.move_down);
                anim.setAnimationListener(new XMLUpdater(Direction.DOWN, image, context));
                break;
            case LEFT:
                anim = AnimationUtils.loadAnimation(context.getApplicationContext(),
                        R.anim.move_left);
                anim.setAnimationListener(new XMLUpdater(Direction.LEFT, image, context));
                break;
            case RIGHT:
                anim = AnimationUtils.loadAnimation(context.getApplicationContext(),
                        R.anim.move_right);
                anim.setAnimationListener(new XMLUpdater(Direction.RIGHT, image, context));
                break;
        }
    }

    public void play(){
        image.startAnimation(anim);
    }




}
