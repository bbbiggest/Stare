package GDY;


import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;


public class Room extends Thread{
    private int port;
    private int count;
    private ServerSocket serverSocket;
    private String IPAddress;
    private mainPlayer[] players;
    public Room(int port,int count)throws IOException{
        this.serverSocket = new ServerSocket(port, count);
        this.port = port;
        this.count = count;
        InetAddress IPAddr = InetAddress.getLocalHost();
        IPAddress = IPAddr.getHostAddress();
        System.out.println(IPAddress);
        this.players = new mainPlayer[count];
        start();
    }
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
    public String getIPAddress() {
        return IPAddress;
    }
    private void broadcast(String msg) {
        for (int i=0; i<count; i++) {
            if (players[i] != null)
                players[i].send(msg);
        }
    }
//    private void broadcast(String msg) {
//        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
//            if (gandengyan.Players[i] != null);
//                gandengyan.Players[i].send(msg);
//        }
//    }
}