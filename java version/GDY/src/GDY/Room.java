package GDY;


import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;


public class Room extends Thread{
    private int port;
    private int count;
    private ServerSocket serverSocket;
    private String IPAddress;
    private mainPlayer[] players;
    public Room(int port,int count)throws IOException{


        this.port = port;
        this.count = count;
        this.players = new mainPlayer[count];
        InetAddress IPAddr = InetAddress.getLocalHost();
        IPAddress = IPAddr.getHostAddress();
        System.out.println(IPAddress);
        try(ServerSocket serverSocket = new ServerSocket(port, count)) {
            int  i = 1;
            Socket[] socket = new Socket[count];
            while(true)
            {

                Socket incoming = serverSocket.accept();
                System.out.println("Spawning "+ i);
                Runnable r = new ThreadedEchoHandler(incoming);
                Thread t = new Thread(r);
                t.start();
                i++;
            }

        }catch (IOException e)
        {
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


}