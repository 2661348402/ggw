package SnakeGame;

import javax.swing.*;
//主游戏类
public class GameStart {
    public static void main(String[] args) {
        JFrame frame=new JFrame("贪吃蛇小游戏");
//窗口设置
        frame.setBounds(10,10,900,720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(new GamePanel());

    }
}
