//package GDY;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///*************************************************************************************
// * 服务器的PLAYER对象，负责与客户端的连接、通信和玩家信息记录
// * */
//public class serPlayer {
//    private String name;
//    private PrintWriter printWriter;
//    private BufferedReader bufferedReader;
//    serPlayer(Socket socket) throws IOException {
//        this.printWriter = new PrintWriter(socket.getOutputStream(), true);
//        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        this.name = bufferedReader.readLine();
//    }
//
//}
