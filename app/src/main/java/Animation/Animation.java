package Animation;

/**
 * Created by Larry on 7/12/2016.
 */
public abstract class Animation {
    int frames;

    public Animation(int frames){
        this.frames = frames;
    }

    public abstract void tick();

    public boolean isDone(){
        if(frames <= 0){
            return true;
        }
        return false;
    }
}
