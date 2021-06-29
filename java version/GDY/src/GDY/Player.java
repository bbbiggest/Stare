package GDY;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class Player {
    private int ID = 0;
    private final String name;
    public ArrayList<Poker> hand = new ArrayList<>(), wantPut = new ArrayList<>();
    private BufferedReader bReader;
    private Scanner in;
    private PrintWriter out;

    public Player() {
        this.name = Main.start.getname();
    }

//    public void setID(int tID) { ID = tID; }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void autoset() throws IOException {
        if (Main.isJoinRoom) {
            System.out.println("is Join");
            connect_server(Main.start.getTheIP(), Main.start.getThePort());
            System.out.println("connnect " + Main.start.getTheIP() + ' ' + Main.start.getThePort());
        } else {
            connect_server(gandengyan.IPAddress, Main.start.getThePort());
            System.out.println("connnect " + gandengyan.IPAddress + ' ' + Main.start.getThePort());
        }
        Main.GF = new GameFrame();
        Main.GF.waitting();
        System.out.println("GF waitting");
        while (!read().equals("start"))
            ;
        Main.isok = true;
        System.out.println("isok = true");
    }

    public void getPoker(int op) throws IOException {
        if (op == 1)
            send("getPoker");
        String line = read();
        if (!line.equals("none")) {
            hand.add(new Poker(line));
            hand.sort(null);
        }
    }

    void discard() {
        for (int i = 0; i < wantPut.size(); ++i) {
            for (int j = 0; j < hand.size(); ++j) {
                if (hand.get(j).equals(wantPut.get(i))) {
                    hand.remove(j);
                    break;
                }
            }
        }
        GameInfo.Last_playing_card[0] = new ArrayList<>();
        GameInfo.Last_playing_card[0].addAll(wantPut);
        GameInfo.pokers_num[0] = hand.size();
        wantPut = new ArrayList<>();
        GameInfo.canNext = true;
        System.out.println("canNext = true");
    }

    public void noPlay() throws IOException {
        getPoker(1);
        GameInfo.Last_playing_card[0] = new ArrayList<>();
        GameInfo.pokers_num[0] = hand.size();
        GameInfo.Number_of_no++;
        GameInfo.canNext = true;
    }

    public void wantPlay() {
        wantPut.sort(null);
        if (GameInfo.isLegal()) {
            discard();
//            Number_of_no = 0;
        }
        else {
            Main.GF.setPrompt("请选择符合游戏规则的扑克牌");
        }
    }

//    public void Round() {
//        System.out.println("这是 玩家" + this.ID + "号 的回合, 请出牌");
//        System.out.println();
//        if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0]))
//            System.out.println("请输出要打出的牌的点数");
//        else
//            System.out.println("请输出要打出的牌的点数，或者输出\"no\"");
//        System.out.println("（如果有多张牌，请用空格隔开）\n");
//        if (hand.isEmpty())
//            GameInfo.Winner = this.ID;
//    }

    void connect_server(String IPAddress, int Port) throws IOException {
        Socket incoming = new Socket(IPAddress, Port);
        InputStream inStream = incoming.getInputStream();
        OutputStream outStream = incoming.getOutputStream();
        in = new Scanner(inStream, StandardCharsets.UTF_8);
        out = new PrintWriter(
                new OutputStreamWriter(outStream, StandardCharsets.UTF_8), true);
        bReader = new BufferedReader(new InputStreamReader(incoming.getInputStream(), StandardCharsets.UTF_8));
        send(name);
        this.ID = Integer.parseInt(read());
        System.out.println(ID + " yes");
    }

    public void acceptInfo() throws IOException {
        while (!read().equals("INFO"))
            ;
        if (read().equals("startInfo")) {
            GameInfo.Number_of_players = Integer.parseInt(read());
            GameInfo.Last_playing_card = new ArrayList[GameInfo.Number_of_players];
            for (int i = 0; i < GameInfo.Number_of_players; ++i)
                GameInfo.Last_playing_card[i] = new ArrayList<>();
            GameInfo.players_name = new String[GameInfo.Number_of_players];
            GameInfo.players_name[0] = ID + "号 " + name;
            GameInfo.pokers_num = new int[GameInfo.Number_of_players];
            for (int i = 1; i < GameInfo.Number_of_players; ++i) {
                GameInfo.players_name[i] = read();
            }
            GameInfo.First_player = Integer.parseInt(read());
            GameInfo.Winner = Integer.parseInt(read());
            for (int i = 0; i < 5; ++i) {
                getPoker(0);
            }
            if (GameInfo.First_player == ID)
                getPoker(0);
            GameInfo.pokers_num[0] = hand.size();
            if (GameInfo.First_player < ID)
                GameInfo.First_player++;
        } else if (read().equals("gameInfo")) {
            for (int i = 1; i < GameInfo.Number_of_players; ++i)
                GameInfo.pokers_num[i] = Integer.parseInt(read());
            GameInfo.Number_of_no = Integer.parseInt(read());
            GameInfo.Last_playing_card_type.first = read();
            GameInfo.Last_playing_card_type.second = read();
            for (int i = 1; i < GameInfo.Number_of_players; ++i) {
                GameInfo.Last_playing_card[i] = new ArrayList<>();
                int cnt = Integer.parseInt(read());
                for (int j = 0; j < cnt; ++j) {
                    GameInfo.Last_playing_card[i].add(new Poker(read()));
                }
            }
            GameInfo.Winner = Integer.parseInt(read());
        }
    }

    void acceptRound() throws IOException {
        while (!read().equals("ROUND"))
            ;
        GameInfo.round = Integer.parseInt(read());
        System.out.println("||||||||||||||||||");
        if (GameInfo.round == ID) {
            GameInfo.round = 0;
            GameInfo.canNext = false;
        } else if (GameInfo.round < ID) {
            GameInfo.round++;
        }
        System.out.println("Player-170 ok");
        var t = new Thread(() -> {
            Main.GF.updateRound();
        });
        t.start();
        System.out.println("Player-175 ok");
        var t2 = new Thread(() -> {
            if (GameInfo.round == 0) {
                System.out.println("ready to sendMyInfo");
                while (!GameInfo.canNext) {
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("ready to sendMyInfo2");
                sendMyInfo();
                System.out.println("already to sendMyInfo");
            }
            try {
                Main.GF.update();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t2.start();
    }

    void sendMyInfo() {
        send("roundInfo");
        send("" + hand.size());
        send(GameInfo.Last_playing_card_type.first);
        send(GameInfo.Last_playing_card_type.second);
        send("" + GameInfo.Last_playing_card[0].size());
        for (var x : GameInfo.Last_playing_card[0])
            send(x.toString());
        send("" + GameInfo.Number_of_no);
    }

    public void send(String msg) {
        out.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
//        out.println(msg);
        System.out.println("player send: " + msg);
    }

    public String read() throws IOException {
        String line = bReader.readLine();
        System.out.println("read: " + line);
        return line.trim();
//        if (in.hasNextLine()) {
//            String line = in.nextLine();
//            System.out.println("read: " + line);
//            return line.trim();
//        }
//        return "";
    }
}