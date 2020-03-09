package Stoot;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * @author: kww
 * @description:
 * 三种敌机、玩家控制的飞机、炸弹、子弹的父类
 * 定义为抽象类
 * @date: Created in 2020/3/5 17:30
 */
public abstract class FlyObj {
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected int speed;
    private static int LIFE=0;
    private static int DEAD=1;
    private static int REMOVE=2;
    protected int state=LIFE;//飞行类的状态：LIFE活着、DEAD死亡、REMOVE从数组中移除

    /*
    移动的抽象方法
     */
    public abstract void step();

    /*
    获得图片的抽象方法
     */
    public abstract BufferedImage getImage();

    /*
    判断是否越出边界的抽象方法
     */
    public abstract boolean outOfBound();

    /*
    画出图片
     */
    public void paint(Graphics g){
        g.drawImage(getImage(),x,y,null);
    }

    /*
    判断是否活着
     */
    public boolean isLife(){
        return state==LIFE;
    }

    /*
    判断是否死亡
     */
    public boolean isDead(){
        return state==DEAD;
    }

    /*
    判断是否被移除
     */
    public boolean isRemove(){
        return state==REMOVE;
    }

    /*
    将状态改为死亡
     */
    public void goDead(){
        state=DEAD;
    }

    /*
    将状态改为移除
     */
    public void goRemove(){ state=REMOVE;}

    /*
    判断两飞行物是否撞击
     */
    public boolean hit(FlyObj obj){
        int x1 = this.x-obj.width;
        int x2 = this.x+this.width;
        int y1 = this.y-obj.height;
        int y2 = this.y+this.height;
        int x = obj.x;
        int y = obj.y;

        return x>=x1 && x<=x2 && y>=y1 && y<=y2;
    }

    /*
    获得分数的抽象方法
     */
    public abstract int getScore();

}
