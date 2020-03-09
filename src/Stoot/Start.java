package Stoot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description: 开始按钮
 * @date: Created in 2020/3/6 20:10
 */
public class Start extends Components{

    public Start() {
        this.width = 60;
        this.height = 45;
        this.x = 210;
        this.y = 300;
        this.speed = 3;
    }

    public void step(){
        if(y>=Game.height){
            return;
        }
        y+=speed;
    }

    public BufferedImage getImage(){
        return Images.start;
    }

    /*
    获得开始按钮被按下的图片
     */
    public BufferedImage getImage1(){
        return Images.startPressed;
    }

    /*
    将暂停按钮被按下的图片画出来
     */
    public void paint2(Graphics g){
        g.drawImage(getImage1(),x,y,null);
    }
}
