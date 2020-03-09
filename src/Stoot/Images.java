package Stoot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.BatchUpdateException;

/**
 * @author: kww
 * @description:
 * 加载图片到内存中
 * @date: Created in 2020/3/5 15:56
 */
public class Images {
    public static BufferedImage background;//背景图片
    public static BufferedImage name;//“飞机大战”
    public static BufferedImage start;//开始按钮
    public static BufferedImage startPressed;//开始按钮被按下
    public static BufferedImage pause;//暂停按钮
    public static BufferedImage pausePressed;//暂停按钮被按下
    public static BufferedImage bullet;//子弹
    public static BufferedImage restart;//重新开始
    public static BufferedImage bomb;//炸弹
    public static BufferedImage[] player;//玩家控制的飞机
    public static BufferedImage[] playerDown;//玩家控制的飞机被击落后
    public static BufferedImage[] enemy1;//敌机1
    public static BufferedImage[] enemy2;//敌机2
    public static BufferedImage[] enemy3;//敌机3

    static {
        background = loadImage("background.png");
        name = loadImage("name.png");
        start = loadImage("start.png");
        startPressed = loadImage("startPressed.png");
        bullet=loadImage("bullet.png");
        pause=loadImage("pause.png");
        pausePressed=loadImage("pausePressed.png");
        restart=loadImage("restart.png");
        bomb=loadImage("bomb.png");

        player=new BufferedImage[2];
        player[0]=loadImage("player1.png");
        player[1]=loadImage("player2.png");

        enemy1=new BufferedImage[5];
        enemy2=new BufferedImage[5];
        enemy3=new BufferedImage[5];
        enemy1[0]=loadImage("enemy1.png");
        enemy2[0]=loadImage("enemy2.png");
        enemy3[0]=loadImage("enemy3.png");
        for(int i=1;i<enemy1.length;i++){
            enemy1[i]=loadImage("enemy1Destory"+i+".png");
            enemy2[i]=loadImage("enemy2Destory"+i+".png");
            enemy3[i]=loadImage("enemy3Destory"+i+".png");
        }

        playerDown=new BufferedImage[4];
        for (int i = 0; i < playerDown.length; i++) {
            playerDown[i]=loadImage("playerDestory"+i+".png");
        }

    }

    /*
    加载图片的方法
     */
    public static BufferedImage loadImage(String filename){
        try {
            BufferedImage img = ImageIO.read(Images.class.getResource("/"+filename));
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
