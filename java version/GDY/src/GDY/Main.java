package GDY;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new Main();
    }

    public static StartFrame start;
    public static GameFrame GF;
    public static boolean isJoinRoom, isok = false;
    public static gandengyan gdy;
    public static Player me;

    Main() {
        start = new StartFrame();
    }

    public static void returnMain() throws IOException {
        if (!isJoinRoom) {
            gdy = new gandengyan();
            gdy.readyToStart();
        } else {
            me = new Player();
            me.autoset();
        }
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("wait isok");
                while (!isok) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("isok");
                GF.startGame();
            }
        });
        t.start();
    }
}