package Stoot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 背景图片
 * @date: Created in 2020/3/5 16:45
 */
public class Background extends Components{
    private int width = 480;
    private int height = 700;
    private static int x = 0;
    private static int y = 0;
    private static int y1 = -700;//第二张图片的y轴
    private int speed = 1;//移动速度

    /*
    获取图片
     */
    public BufferedImage getImage(){
        return Images.background;
    }

    /*
    移动
     */
    public void step(){
        y += speed;
        y1 += speed;
        if (y >= this.height)
            y = -this.height;
        if (y1 >= this.height)
            y1 = -this.height;
    }

    /*
    获取图片，画出背景
     */
    public void paint(Graphics g){
        g.drawImage(getImage(),x,y,null);
        g.drawImage(getImage(),x,y1,null);
    }
}
