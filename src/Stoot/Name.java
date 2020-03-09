package Stoot;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description: “飞机大战”
 * @date: Created in 2020/3/6 19:18
 */
public class Name extends Components{

    public Name() {
        this.width = 480;
        this.height = 84;
        this.x = 0;
        this.y = 90;
        this.speed = 3;
    }

    public void step(){
        if(y>=Game.height){
            return;
        }
        y+=speed;
    }

    public  BufferedImage getImage(){
        return Images.name;
    }

    /*
    获得重新开始的图片
     */
    public BufferedImage getImage2(){
        return Images.restart;
    }

    /*
    将重新开始的图片画出来
     */
    public void paint1(Graphics g){
        g.drawImage(getImage2(),90,400,null);
    }

}

