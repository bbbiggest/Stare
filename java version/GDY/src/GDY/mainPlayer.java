package GDY;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class mainPlayer {
    private Socket socket;
    private String name;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    public mainPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void connect(String IPAddress, int port) throws IOException {
        socket = new Socket(IPAddress, port);    //通过IP地址和端口创建和服务器的连接
        this.printWriter = new PrintWriter(socket.getOutputStream(), true);    //自动刷新
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        send(name); //向服务器发送自己的名称
        System.out.println("send my name");
    }

    public void send(String s) {
        printWriter.println(new String(s.getBytes(StandardCharsets.UTF_8)));
    }

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
    public String read() throws IOException {
        return bufferedReader.readLine();
    }

}
