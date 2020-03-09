package Stoot;

import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 飞行类的子类:敌机3
 * @date: Created in 2020/3/5 17:38
 */
public class Enemy3 extends FlyObj{
    private int xSpeed;
    private int ySpeed;
    public Enemy3(){
        width=169;
        height=258;
        x=(int)(Math.random()*(Game.width-this.width));
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
            return Images.enemy3[0];
        }else if(isDead()){
            BufferedImage img = Images.enemy3[index++];
            if(index==Images.enemy3.length)
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
        return 5;
    }
}
