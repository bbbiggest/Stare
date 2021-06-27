package GDY;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Seat {
    private int ID;
    private String name;
    private Socket incoming;
    private PrintWriter pWriter;
    private BufferedReader bReader;
    private Scanner in;
    private PrintWriter out;

    public Seat(int ID, Socket incomingSocket) throws IOException {
        this.ID = ID;
        this.incoming = incomingSocket;
        this.pWriter = new PrintWriter(incoming.getOutputStream(), true);
        this.bReader = new BufferedReader(new InputStreamReader(incoming.getInputStream(), StandardCharsets.UTF_8));
        InputStream inStream = incoming.getInputStream();
        OutputStream outStream = incoming.getOutputStream();
        in = new Scanner(inStream, StandardCharsets.UTF_8);
        out = new PrintWriter(
                new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);
        this.name = read();
        System.out.println(ID + "-" + name);
        out.println(this.ID);
    }

    void putPoker() {
        if (!gandengyan.Deck.isEmpty()) {
            send(gandengyan.Deck.get(0).toString());
            gandengyan.Deck.remove(0);
        } else {
            send("none");
        }
    }

    public void send(String msg) {
//        out.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
        out.println(msg);
        System.out.println("send: " + msg);
    }

    public String read() {
//        return bReader.readLine();
        return in.nextLine();
    }
}
