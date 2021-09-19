package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
   public static  Timer timer;
    Random random=new Random();
    //定义蛇的数据结构
    int length;
    int[] x=new int[600];
    int[] y=new int [500];
    String direction;
    //定义食物的数据状态
    int foodX;
    int foodY;
    //游戏状态
    boolean isStart;
    boolean isFail;
    //积分系统
    int score;
    GamePanel(){
        init();
        setFocusable(true);//默认的焦点是在窗口上的，要把他转移到面板上！！！
        addKeyListener(this);
         timer=new Timer(75,this);
         timer.start();



    }
    //数据初始化
    public  void  init(){
        length=3;
        x[0]=100;y[0]=100;
        x[1]=75;y[1]=100;
        x[2]=50;y[2]=100;
        direction="R";

        foodX=25+25*random.nextInt(34);
        foodY=75+25*random.nextInt(24);

        isFail=false;
        isStart=false;

        score=0;

    }




    //组件画笔
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
      //  setBackground(Color.white);
        GameData.header.paintIcon(this,g,25,11);//广告栏
        g.fillRect(25,75,850,600);//黑板
        GameData.food.paintIcon(this,g,foodX,foodY);//食物
        //积分
        String s="长度: "+length;
        String s2="积分: "+score;
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,15));
        g.drawString(s,750,35);
        g.drawString(s2,750,50);
        //蛇身绘制
        for(int i=1;i<length;i++){
            GameData.body.paintIcon(this,g,x[i],y[i]);
        }
        //蛇头绘制
        switch (direction){
            case "R":
                GameData.right.paintIcon(this,g,x[0],y[0]);
                break;
            case "L":
                GameData.left.paintIcon(this,g,x[0],y[0]);
                break;
            case "U":
                GameData.up.paintIcon(this,g,x[0],y[0]);
                break;
            case "D":
                GameData.down.paintIcon(this,g,x[0],y[0]);
                break;
        }

        //游戏状态
        if(!isStart&&!isFail){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));//设置字体
            g.drawString("按下空格开始游戏",300,300);

        }
        if(isFail){
            g.setColor(Color.red);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败，按下空格重新开始",200,300);

        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();
        if(keyCode== KeyEvent.VK_SPACE){
            if(isFail){
                init();
            }else{
           isStart =!isStart;}
            repaint();
        }
        else if(keyCode== KeyEvent.VK_UP&&!direction.equals("D")){
            direction="U";
        }
        else if(keyCode== KeyEvent.VK_DOWN&&!direction.equals("U")){
            direction="D";
        }
        else if(keyCode== KeyEvent.VK_RIGHT&&!direction.equals("L")){
            direction="R";
        }
        else if(keyCode== KeyEvent.VK_LEFT&&!direction.equals("R")){
            direction="L";
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //失败判定
        for(int i=1;i<length;i++){
            if(x[i]==x[0]&&y[i]==y[0]){
                isFail=true;
            }
        }
        //小蛇吃食物
        if(x[0]==foodX&&y[0]==foodY){
            length++;
            score++;
            foodX=25+25*random.nextInt(34);
            foodY=75+25*random.nextInt(24);
        }
        if((!isFail)&&isStart) {
            for(int i=length-1;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }


            if(direction.equals("R")){
                x[0]=x[0]+25;
                if(x[0]>850){x[0]=25;}
                }
            else if(direction.equals("L")){
                x[0]=x[0]-25;
                if(x[0]<25){x[0]=850;}
            }
            else if(direction.equals("U")){
                y[0]=y[0]-25;
                if(y[0]<75){y[0]=650;}
            }
            else if(direction.equals("D")){
                y[0]=y[0]+25;
                if(y[0]>650){y[0]=75;}
            }
        }
        repaint();
    }
}
