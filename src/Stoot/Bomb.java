package Stoot;

import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 飞行类的子类:炸弹
 * @date: Created in 2020/3/5 17:39
 */
public class Bomb extends FlyObj{
    public Bomb(){
        width=60;
        height=107;
        x=(int)(Math.random()*(Game.width-this.width));//x轴为随机
        y=-this.height;
        speed=2;
    }

    /*
    移动
     */
    @Override
    public void step() {
        y+=speed;
    }

    /*
    获取图片
     */
    @Override
    public BufferedImage getImage() {
        if(isLife()) {
            return Images.bomb;
        }else{
            return null;
        }
    }

    /*
    判断是否越出边界
     */
    @Override
    public boolean outOfBound() {
        return false;
    }

    /*
    获得分数
     */
    @Override
    public int getScore() {
        return 1;
    }
}
