package Stoot;

import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 飞行类的子类:敌机1
 * @date: Created in 2020/3/5 17:38
 */
public class Enemy1 extends FlyObj{
    private int xSpeed;
    private int ySpeed;

    public Enemy1(){
        width=57;
        height=43;
        x=(int)(Math.random()*(Game.width-this.width));
        y=-this.height;
        ySpeed=3;
        xSpeed=Math.random()>0.5?-1:1;//横向移动的速度为-1或1,概率分别为50%，随机显示两个数的解决方案
    }

    /*
    移动
     */
    @Override
    public void step() {
        y+=ySpeed;
        x+=xSpeed;
        if(x>=Game.width-this.width||x<=0)
            xSpeed*=-1;
    }

    /*
    获得图片
    若未被击落，则显示完好的图片
    若已被击落，则显示被击落的图片
    index控制击落图片显示的顺序
     */
    int index=1;
    @Override
    public BufferedImage getImage() {
        if(isLife()){
            return Images.enemy1[0];
        }else if(isDead()){
            BufferedImage img = Images.enemy1[index++];
            if(index==Images.enemy1.length)
                goRemove();
            return img;
        }
        return null;
    }

    /*
    判断是否越出边界
     */
    public boolean outOfBound() {
        return this.y>=Game.height;
    }

    /*
    获得分数
     */
    @Override
    public int getScore() {
        return 3;
    }


}
