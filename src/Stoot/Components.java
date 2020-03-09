package Stoot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 背景图片、开始按钮、暂停按钮、重新开始按钮、“飞机大战”所继承的抽象类
 * @date: Created in 2020/3/6 20:28
 */
public abstract class Components {
    protected int width;//宽度
    protected int height;//高度
    protected int x;//x轴坐标
    protected int y;//y轴坐标
    protected int speed;//移动速度

    /*
    获取图片
     */
    public abstract BufferedImage getImage();

    /*
    移动
     */
    public abstract void step();

    /*
    画出图片
     */
    public void paint(Graphics g){
        g.drawImage(getImage(),x,y,null);
    }
}
