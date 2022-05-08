package PaooGame.Graphics;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed, idx;
    private BufferedImage[] frames;
    private long time, timer;
    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        idx = 0;
        time = System.currentTimeMillis();
        timer = 0;
    }

    public void Update(){
        timer += System.currentTimeMillis() -  time;
        time = System.currentTimeMillis();

        if(timer> speed){
            idx++;
            timer = 0;
            if (idx >= frames.length)
            {
                idx = 0;
            }
        }
    }

    public BufferedImage getCurrntFrame(){
        return frames[idx];
    }
}
