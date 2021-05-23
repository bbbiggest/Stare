package GDY.server;
import GDY.gandengyan;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;

public class ROOM extends Thread {
    private ServerSocket serverSocket;
    private String IPAddress;
    private int count;  //玩家数量
    private PLAYER[] players;


    /**
     * 创建一个房间对象
     * @param port : 端口号
     * @param count ：玩家人数
     * */
    public ROOM(int port, int count) throws IOException {
        this.serverSocket = new ServerSocket(port, count);
        IPAddress = getHostIp();
        this.count = count;
        this.players = new PLAYER[count];
        start();
    }


    public String getIPAddress() {
        return IPAddress;
    }

    /**
     * 获取本机的内网IP地址
     * */
    private static String getHostIp() {
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && !ip.getHostAddress().contains(":")) {
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将消息发送给每一位连接的玩家
     * @param msg : 要发送的消息(指令、标志、数据)
     * */
    private void broadcast(String msg) {
        for (int i=0; i<count; i++) {
            if (players[i] != null)
                players[i].send(msg);
        }
    }

    @Override
    public void run() {
        //等待玩家加入房间
        String p = "";
        for (int n=0; n < count; n++) {
            try {
                players[n] = new PLAYER(serverSocket.accept());
                p += players[n].getName() + " ";
                broadcast(p);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(2);
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //所有玩家都已加入，开始游戏
        broadcast("begin"); //发出通知，使客户端进入游戏界面
        gameStart();
    }


    /**
     * start 干瞪眼
     *
     */
    private void gameStart() {

    }







}