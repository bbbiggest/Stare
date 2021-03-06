package GDY;

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
        var t = new Thread(() -> {
            while (!isok) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                GF.startGame();
                GF.Game();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}