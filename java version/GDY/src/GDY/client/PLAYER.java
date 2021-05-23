package GDY.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 客户端Player对象，负责与服务器的连接以及通信
 * */
public class PLAYER {

    private Socket socket;
    private String name;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public PLAYER(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

     public void connect(String IPAddress, int port) throws IOException {
        System.out.println("连接ing"+getName());
        socket = new Socket(IPAddress, port);    //通过IP地址和端口创建和服务器的连接
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);    //自动刷新
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        send(name); //向服务器发送自己的名称
    }

    /**
     * 关闭与服务器的连接，关闭数据流
     * */
    public void disconnect() {
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socket = null;

        this.printWriter.close();
        this.printWriter = null;

        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.bufferedReader = null;
    }

    /*给服务器发送消息*/
    public void send(String s) {
        printWriter.println(new String(s.getBytes(StandardCharsets.UTF_8)));
    }

    /*接收服务器发来的消息， 会阻塞*/
    public String read() throws IOException {
        return bufferedReader.readLine();
    }
}