package GDY;

import java.io.IOException;
import java.net.UnknownHostException;

public class Main
{
    public static StartFrame start;
    public static Player me;
    public static boolean isJoinRoom;
    public static gandengyan gdy;
    public static void returnMain() throws IOException {
        if (!isJoinRoom) {
            gdy = new gandengyan();
        }
        me = new Player();
        me.autoset();
    }
    public static void main(String[] args) {
        start = new StartFrame();
    }
}