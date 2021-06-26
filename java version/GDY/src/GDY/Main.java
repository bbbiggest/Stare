package GDY;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public static StartFrame start;
    public static JFrame GameFrame;
    public static Player me;
    public static boolean isJoinRoom;
    public static gandengyan gdy;

    Main() { start = new StartFrame(); }

    public static void returnMain() throws IOException {
        if (!isJoinRoom) {
            gdy = new gandengyan();
            gdy.readyToStart();
        } else {
            me = new Player();
            me.autoset();
            JOptionPane.showMessageDialog(GameFrame, "等待人到齐后游戏开始\n",
                    "waitting...", JOptionPane.WARNING_MESSAGE);
        }
        while (!me.read().equals("start"))
            ;
        System.out.println("Main30 - while end");
        GameFrame = new JFrame();
        GamePanel curGame = new GamePanel();
        GameFrame.add(curGame);
        GameFrame.setVisible(true);
    }
}