package GDY;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Seat {
    private final int ID;
    private final String name;
    private final BufferedReader bReader;
    private final Scanner in;
    private final PrintWriter out;

    public int getID() { return ID; }
    public String getName() { return name; }

    public Seat(int ID, Socket incomingSocket) throws IOException {
        this.ID = ID;
        this.bReader = new BufferedReader(new InputStreamReader(incomingSocket.getInputStream(), StandardCharsets.UTF_8));
        InputStream inStream = incomingSocket.getInputStream();
        OutputStream outStream = incomingSocket.getOutputStream();
        in = new Scanner(inStream, StandardCharsets.UTF_8);
        out = new PrintWriter(
                new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);
        this.name = read();
        System.out.println(ID + "-" + name);
        out.println(ID);
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

    public String read() throws IOException {
        return bReader.readLine().trim();
//        return in.nextLine();
    }

    public void sendStartInfo() {
        // Number_of_players, players_name[], First_player
        send("startInfo");
        send("" + gandengyan.Number_of_players);
        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
            if (i == ID)
                continue;
            send(gandengyan.Seats[i].getID() + "号 " + gandengyan.Seats[i].getName());
        }
        send("" + gandengyan.First_player);
    }
}
