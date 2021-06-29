package GDY;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Seat {
    private final int ID;
    private final String name;
    private final BufferedReader bReader;
    private final PrintWriter out;
    public int pokers_num;
    public ArrayList<String> Last_playing_card = new ArrayList<>();

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Seat(int ID, Socket incomingSocket) throws IOException {
        this.ID = ID;
        this.bReader = new BufferedReader(new InputStreamReader(incomingSocket.getInputStream(), StandardCharsets.UTF_8));
        OutputStream outStream = incomingSocket.getOutputStream();
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
        out.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
//        System.out.println("send: " + msg);
    }

    public String read() throws IOException {
        return bReader.readLine().trim();
    }

    public void sendStartInfo() {
        send("startInfo");
        send("" + gandengyan.Number_of_players);
        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
            if (i == ID)
                continue;
            send(gandengyan.Seats[i].getID() + "号 " + gandengyan.Seats[i].getName());
        }
        send("" + gandengyan.First_player);
        send("" + gandengyan.Winner);
        if (gandengyan.First_player == ID)
            pokers_num = 6;
        else
            pokers_num = 5;
    }

    public void sendGameInfo() {
        send("gameInfo");
        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
            if (i == ID)
                continue;
            send("" + gandengyan.Seats[i].pokers_num);
        }
        send("" + gandengyan.Number_of_no);
        send(gandengyan.Last_playing_card_type.first);
        send(gandengyan.Last_playing_card_type.second);
        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
            if (i == ID)
                continue;
            send("" + gandengyan.Seats[i].Last_playing_card.size());
            if (gandengyan.Seats[i].Last_playing_card.size() > 0) {
                for (var x : gandengyan.Seats[i].Last_playing_card)
                    send(x);
            }
        }
        send("" + gandengyan.Winner);
    }

    public void Round() throws IOException, InterruptedException {
        String line = read();
        while (!line.equals("roundInfo")) {
            if (line.equals("getPoker"))
                putPoker();
            line = read();
        }
        pokers_num = Integer.parseInt(read());
        gandengyan.Last_playing_card_type.first = read();
        gandengyan.Last_playing_card_type.second = read();
        int cnt = Integer.parseInt(read());
        Last_playing_card = new ArrayList<>();
        for (int i = 0; i < cnt; ++i) {
            Last_playing_card.add(read());
        }
        gandengyan.Number_of_no = Integer.parseInt(read());
    }
}
