package Stoot;

import java.awt.image.BufferedImage;

/**
 * @author: kww
 * @description:
 * 飞行类的子类:玩家控制的飞机
 * @date: Created in 2020/3/5 17:30
 */
public class Player extends FlyObj{
    private int index=0;
    private int DeadIndex=0;
    private int life=3;
    private int fire=1;//火力，控制发射出的子弹的个数
    public Player() {
        width = 102;
        height=126;
        x=189;
        y=400;
    }

    /*
    火力=1：发射一颗子弹
    火力=2：发射两颗子弹
    火力=3：发射三颗子弹
    新建一个子弹数组，将其传回去
     */
    public Bullet[] shooting(){
        if(fire==1){
            Bullet[] bs=new Bullet[1];
            bs[0]=new Bullet(this.x+this.width/2,this.y-15);
            return bs;
        }else if(fire==2){
            Bullet[] bs=new Bullet[2];
            bs[0] = new Bullet(this.x + this.width/4, this.y - 15);
            bs[1] = new Bullet(this.x + 3*this.width/4,this.y-15);
            return bs;
        }else {
            Bullet[] bs = new Bullet[3];
            bs[0] = new Bullet(this.x + this.width/4, this.y - 15);
            bs[1] = new Bullet(this.x + this.width/2,this.y-15);
            bs[2] = new Bullet(this.x + 3*this.width/4,this.y-15);
            return bs;
        }
    }

    /*
    设定火力
     */
    public void setFire(int fire){
        this.fire=fire;
    }

    /*
    获得x轴坐标
     */
    public int getX(){
        return this.x;
    }

    /*
    获得x轴坐标
     */
    public int getY(){
        return this.y;
    }

    /*
    获得x轴宽度
     */
    public int getWidth(){
        return this.width;
    }

    /*
    获得生命值
     */
    public int getLife(){
        return this.life;
    }

    /*
    设定生命值
     */
    public void setLife(int life){
        this.life=life;
    }

    /*
    将生命值减1
     */
    public void subtractLife(){
        this.life--;
        if(this.life==0)
            goDead();
    }

    public void step(){
    }

    /*
    随鼠标移动
     */
    public void move(int x,int y){
        this.x=x-this.width/2;
        this.y=y-this.height/2;
    }

    /*
    获得图片
    若未被击落，则显示完好的图片
    若已被击落，则显示被击落的图片
    index控制击落图片显示的顺序
     */
    @Override
    public BufferedImage getImage() {
        if(isLife()) {
            return Images.player[index++ % 2];
        }else if(isDead()){
            BufferedImage img = Images.playerDown[DeadIndex++];
            if(DeadIndex==Images.playerDown.length)
                goRemove();
            return img;
        }
        return null;
    }

    @Override
    public boolean outOfBound() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
