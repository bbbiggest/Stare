//package GDY;
//import java.io.*;
//import java.net.Socket;
//import java.net.*;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//import java.util.*;
//
//public class mainPlayer{
//    private Socket socket;
//    public String name;
//    private PrintWriter printWriter;
//    private BufferedReader bufferedReader;
//
//    public mainPlayer(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//
//    public void connect(String IPAddress, int port) throws IOException {
//        try(Socket s = new Socket(IPAddress,port)){
//            System.out.println("hhhhhh,接上了");
//            this.printWriter = new PrintWriter(s.getOutputStream(), true);    //自动刷新
//            this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.UTF_8));
//            send(getName());
//            System.out.println(name);
//
//        }catch (IOException e)
//        {
//            System.out.println("mainPlayer error，未找到服务器");
//        }
//
//    }
//    public mainPlayer(Socket incomingSocket)
//    {
//        socket = incomingSocket;
//    }
//
//    public void send(String msg) {
//        printWriter.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
//    }
//
//    public void disconnect() {
//        try {
//            this.socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.socket = null;
//
//        this.printWriter.close();
//        this.printWriter = null;
//
//        try {
//            this.bufferedReader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.bufferedReader = null;
//    }
//    public String read() throws IOException {
//        return bufferedReader.readLine();
//    }
//
//}
