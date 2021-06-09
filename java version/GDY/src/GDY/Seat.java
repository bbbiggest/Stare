package GDY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Seat {
    private int ID;
    private String name;
    private String IP;
    private int Port;
    private Socket mySocket;
    private PrintWriter pWriter;
    private BufferedReader bReader;

    public Seat(int ID) { this.ID = ID; }

    public void sitDown(String name, String IP, int Port) throws IOException {
        this.name = name;
        this.IP = IP;
        this.Port = Port;
        connect_server(this.IP, this.Port);
    }

    public void connect_server(String IPAddress, int Port) throws IOException {
        mySocket = new Socket(IPAddress, Port);
        this.pWriter = new PrintWriter(mySocket.getOutputStream(), true);
        this.bReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream(), StandardCharsets.UTF_8));
        send(Integer.toString(ID));
    }

    public void send(String msg) {
        pWriter.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
    }

    public String read() throws IOException {
        return bReader.readLine();
    }
}
