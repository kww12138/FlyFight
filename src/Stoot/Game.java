package Stoot;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: kww
 * @description:
 * 飞机大战游戏
 * @date: Created in 2020/3/5 15:45
 */
public class Game extends JPanel {

    //窗口的宽和高
    public static final int width = 480;
    public static final int height = 700;

    protected int score = 0;//分数

    //四种游戏状态
    private int ready = 0;
    private int begin = 1;
    private int pause = 2;
    private int gameOver = 3;
    private int state = ready;

    private int pressed =0;//控制是否按下
    private int shooting=0;//控制是否正在射击

    private Background background = new Background();//背景图片实例
    private Name name = new Name();//“飞机大战”实例
    private Start start = new Start();//开始按钮实例
    private Pause pau=new Pause();//暂停按钮实例
    private Player player=new Player();//玩家飞机实例
    private Bullet[] bullet={};//子弹数组
    private FlyObj[] enemy={};//敌机数组

    /*
    移动
     */
    public void step(){
        background.step();
        name.step();
        start.step();
        for(int i=0;i<bullet.length;i++){
            bullet[i].step();
        }
        for (int i = 0; i <enemy.length ; i++) {
            enemy[i].step();
        }
    }

    //控制子弹射出的速度
    int shootIndex=1;
    int shootSpeed=30;
    /*
    发射子弹
    从玩家飞机类中的shooting中获得新子弹数组
    将子弹数组扩容
    再将新子弹数组附加到子弹数组中
     */
    public void shoot(){
        shootIndex++;
        if(shootIndex%shootSpeed==0) {
            Bullet[] bs = player.shooting();
            bullet = Arrays.copyOf(bullet, bullet.length + bs.length);
            System.arraycopy(bs, 0, bullet, bullet.length - bs.length, bs.length);
        }

    }

    /*
    判断敌机、子弹是否出界
    新建一个实例数组表示未出界
    如果未出界，则将此数组赋给子弹或敌机数组
     */
    public void outOfBound(){
        int index1=0;
        Bullet[] bulletLive=new Bullet[bullet.length];
        for (int i = 0; i < bullet.length; i++) {
            if(!bullet[i].outOfBound())
                bulletLive[index1++]=bullet[i];
        }
        bullet=Arrays.copyOf(bulletLive,index1);

        int index2=0;
        FlyObj[] enemyLive=new FlyObj[enemy.length];
        for (int i = 0; i < enemy.length; i++) {
            if(!enemy[i].outOfBound())
                enemyLive[index2++]=enemy[i];
        }
        enemy=Arrays.copyOf(enemyLive,index2);
    }

    /*
    控制下一次出现的飞行类的类型
     */
    public FlyObj[] nextEnemy(int num){
        Random ran = new Random();
        FlyObj[] newEnemy = new FlyObj[num];
        int type = ran.nextInt(19);
        if(type<=5){
            for (int i = 0; i < newEnemy.length; i++) {
                newEnemy[i]=new Enemy1();
            }
            return newEnemy;
        }else if(type<=11){
            for (int i = 0; i < newEnemy.length; i++) {
                newEnemy[i]=new Enemy2();
            }
            return newEnemy;
        }else if(type<=15){
            for (int i = 0; i < newEnemy.length; i++) {
                newEnemy[i]=new Bomb();
            }
            return newEnemy;
        }else{
            for (int i = 0; i < newEnemy.length; i++) {
                newEnemy[i]=new Enemy3();
            }
            return newEnemy;
        }
    }


    //控制飞入的飞行物的速度和个数
    int enterIndex=0;
    int enterSpeed=30;
    int enterNum=1;
    /*
    敌机飞入
     */
    public void enter(){
        enterIndex++;
        if(enterIndex%enterSpeed==0){
            FlyObj obj[]= nextEnemy(enterNum);
            enemy=Arrays.copyOf(enemy,enemy.length+enterNum);
            System.arraycopy(obj, 0, enemy,enemy.length - obj.length, obj.length);
        }
    }

    /*
    控制相撞后的操作
    分为敌机和玩家飞机相撞和子弹和敌机相撞
     */
    public void hit(){
        for (int i = 0; i < enemy.length; i++) {
            FlyObj f=enemy[i];
            if(player.hit(f)){
                if(f.isLife()&&player.isLife()){
                    f.goDead();
                    player.subtractLife();
                }
            }else{
                for (int j = 0; j < bullet.length; j++) {
                    Bullet b=bullet[j];
                    if(b.isLife()&&f.isLife()&&b.hit(f)){
                        b.goDead();
                        f.goDead();
                        score+=f.getScore();
                    }
                }
            }
        }
    }

    /*
    重新开始
     */
    public void reStart(){
        state=ready;
        player=new Player();
        enemy=new FlyObj[0];
        bullet=new Bullet[0];
        background=new Background();
        name=new Name();
        start=new Start();
        pau=new Pause();
        score=0;
        player.setLife(3);
        enterNum=1;
        enterSpeed=30;
    }

    /*
    根据分数设置火力
     */
    public void addFire(){
        if(score>100)
            player.setFire(2);
        if(score>300)
            player.setFire(3);
    }

    /*
    判断游戏是否结束
    如果玩家飞机被移除，游戏结束
     */
    public void gameOver(){
        if(player.isRemove()) {
            state = gameOver;
        }
    }

    /*
    地狱模式
     */
    public void hell(){
        if(score>300){
            enterSpeed=20;
        }
        if(score>400) {
            enterNum = 2;
        }
        if(score>500){
            enterNum=3;
            enterSpeed=10;
        }
    }

    /*
    鼠标，刷新
     */
    public void move(){
        //鼠标监听事件
        MouseAdapter l=new MouseAdapter() {
            /*
            鼠标点击发生的事件
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                if(state==ready)
                    state= begin;
                if(state==gameOver){
                    reStart();
                }
                if(state==pause)
                    state=begin;
            }

            /*
            鼠标进入窗口发生的事件
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            /*
            鼠标按下发生的事件
             */
            @Override
            public void mousePressed(MouseEvent e) {
                pressed=1;
            }

            /*
            鼠标释放发生的事件
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                if(state==begin&&shooting==1)
                    shooting=0;
                pressed=0;
            }

            /*
            鼠标离开窗口发生的事件
             */
            @Override
            public void mouseExited(MouseEvent e) {
                if(state==begin)
                    state=pause;
            }

            /*
            鼠标拖拉发生的事件
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                if(state==begin) {
                    shooting = 1;
                    int x=e.getX();
                    int y=e.getY();
                    player.move(x,y);
                }
            }

            /*
            鼠标拖拉发生的事件
             */
            @Override
            public void mouseMoved(MouseEvent e) {
                if(state==begin){
                    int x=e.getX();
                    int y=e.getY();
                    player.move(x,y);
                }
            }
        };
        this.addMouseListener(l);
        this.addMouseMotionListener(l);

        //重绘 刷新
        Timer timer = new Timer();
        int blink=10;//刷新频率
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(state == begin){
                    step();
                    outOfBound();
                    enter();
                    hit();
                    addFire();
                    gameOver();
                    hell();
                }
                repaint();
            }
        },blink,blink);
    }

    /*
    画图
     */
    public void paint(Graphics g){
        background.paint(g);
        name.paint(g);
        if(pressed==0) {
            start.paint(g);
        }else{
            start.paint2(g);
        }
        if(shooting==1)
            shoot();
        if(state==pause){
            if(pressed==0)
                pau.paint(g);
            else
                pau.paint2(g);
        }
        if(state==gameOver) {
            name.paint1(g);
        }

        player.paint(g);

        for(int i=0;i<bullet.length;i++){
            bullet[i].paint(g);
        }
        for (int i = 0; i < enemy.length; i++) {
            enemy[i].paint(g);
        }

        g.setFont(new Font("宋体",Font.BOLD,16));
        g.drawString("分数："+score,10,25);
        g.drawString("生命值："+player.getLife(),10,45);

    }

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Game game = new Game();
        JFrame frame =new JFrame();
        frame.add(game);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //播放背景音乐
        Clip bgm= AudioSystem.getClip();
        java.io.InputStream is=Game.class.getClassLoader().getResourceAsStream("./bgm.wav");
        AudioInputStream ais=AudioSystem.getAudioInputStream(is);
        bgm.open(ais);
        bgm.start();

        game.move();

    }
}
