package Stoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author: kww
 * @description:
 * 飞行类的子类:敌机2
 * @date: Created in 2020/3/5 17:38
 */
public class Enemy2 extends FlyObj{
    private int xSpeed;
    private int ySpeed;

    public Enemy2(){
        width=69;
        height=99;
        Random ran=new Random();
        x=ran.nextInt(Game.width-this.width);
        y=-this.height;
        ySpeed=2;
        xSpeed=Math.random()>0.5?-1:1;
    }

    @Override
    public void step() {
        y+=ySpeed;
        x+=xSpeed;
        if(x>=Game.width-this.width||x<=0)
            xSpeed*=-1;
    }

    int index=1;
    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return Images.enemy2[0];
        }else if(isDead()){
            BufferedImage img = Images.enemy2[index++];
            if(index==Images.enemy2.length)
                goRemove();
            return img;
        }
        return null;
    }

    public boolean outOfBound() {
        return this.y>=Game.height;
    }

    @Override
    public int getScore() {
        return 4;
    }
}
