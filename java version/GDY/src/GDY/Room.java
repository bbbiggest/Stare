package GDY;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;


class Room {
    public void waitStart(int RoomPort) {
        try (var s = new ServerSocket(RoomPort)) {
            int i = 0;

            while (i < gandengyan.Number_of_players) {
                Socket incoming = s.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadedEchoHandler(incoming);
                var t = new Thread(r);
                t.start();
                i++;
                gandengyan.Players[i] = new Player();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void broadcast(String msg) {
//        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
//            if (gandengyan.Players[i] != null);
//                gandengyan.Players[i].send(msg);
//        }
//    }
}