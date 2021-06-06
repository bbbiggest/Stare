package GDY;


import java.net.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;


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
        start();

    }
    public void run()
    {
        try(ServerSocket serverSocket = new ServerSocket(port, count)) {
            int  i = 0;

            Socket[] socket = new Socket[count];
            smallFrame wait = new smallFrame("waitting..."+"IP:"+IPAddress+"端口号:"+port,"IP:"+IPAddress+"\r"+"端口号:"+String.valueOf(port));
            wait.setVisible(true);
            while(i<count)
            {

                Socket incoming = serverSocket.accept();
                System.out.println("hello");
                System.out.println("玩家 "+ i);
                i++;
            }
            wait.setVisible(false);
            System.out.println("wow,人到齐了");
            broadcast("begin");


        }catch (IOException e)
        {
            System.out.println("Room error!");
            e.printStackTrace();
        }
    }

    public String getIPAddress() {
        return IPAddress;
    }
    public void broadcast(String msg) {
        for (int i=0; i<count; i++) {
            if (players[i] != null)
                players[i].send(msg);
        }
    }


}