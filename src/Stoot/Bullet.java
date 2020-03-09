package Stoot;

import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 飞行类的子类:子弹
 * @date: Created in 2020/3/5 17:39
 */
public class Bullet extends FlyObj{
    /*
    子弹的x、y为玩家控制的飞机的x、y所决定
     */
    public Bullet(int x, int y) {
        width=5;
        height=11;
        this.x=x;
        this.y=y;
        speed=3;
    }

    /*
    移动
     */
    @Override
    public void step() {
        y-=speed;
    }

    /*
    获取图片
     */
    @Override
    public BufferedImage getImage() {
        return Images.bullet;
    }

    /*
    判断是否越出边界
     */
    public boolean outOfBound(){
        return this.y<=-this.height;
    }

    /*
    获得分数
     */
    @Override
    public int getScore() {
        return 0;
    }
}
