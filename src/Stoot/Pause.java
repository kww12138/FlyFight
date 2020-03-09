package Stoot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description: 暂停按钮
 * @date: Created in 2020/3/6 23:44
 */
public class Pause extends Components{
    public Pause(){
        x=210;
        y=300;
    }

    @Override
    public BufferedImage getImage() {
        return Images.pause;
    }

    /*
    获得暂停按钮被按下的图片
     */
    public BufferedImage getImage1(){
        return Images.pausePressed;
    }

    /*
    将暂停按钮被按下的图片画出来
     */
    public void paint2(Graphics g) {
        g.drawImage(getImage1(), x, y, null);
    }

    @Override
    public void step() {

    }
}
