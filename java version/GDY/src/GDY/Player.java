package GDY;

import java.awt.Image;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player {
    private int ID = 0;
    private final String name;
    public ArrayList<Poker> hand = new ArrayList<>(), wantPut = new ArrayList<>();
    private BufferedReader bReader;
    private Scanner in;
    private PrintWriter out;

    public Player() { this.name = Main.start.getname(); }

//    public void setID(int tID) { ID = tID; }

    public int getID() { return this.ID; }
    public String getName() { return this.name; }

    void autoset() throws IOException {
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

    public int Get_number_of_remaining_hands() {
        return hand.size();
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

    public boolean discard(ArrayList<String> dc) {
        ArrayList<Poker> Discard_pile = new ArrayList<>();
        for (int i = 0; i < dc.size(); ++i) {
            boolean isok = false;
            for (int j = 0; j < hand.size(); ++j) {
                if (hand.get(j).getPoint().equals(dc.get(i))) {
                    isok = true;
                    Discard_pile.add(hand.get(j));
                    hand.remove(j);
                    break;
                }
            }
            if (!isok) {
                hand.addAll(Discard_pile);
                return false;
            }
        }
        GameInfo.Last_playing_card = Discard_pile;
        return true;
    }

    public int FindInd(String sa) {
        for (int i = 0; i < hand.size(); ++i) {
            if (hand.get(i).getPoint().equals(sa))
                return i;
        }
        return -1;
    }

    public void noPlay() throws IOException {
        if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0])) {
            System.out.println("您是本轮第一个玩家，必须输入扑克牌的点数");
            return;
        }
        getPoker(1);
        GameInfo.Number_of_no++;
    }

    public void aLegalPlay() {
        while (true) {
            String input = GamePanel.inputPoker;
            ArrayList<String> check = new ArrayList<>();
            Scanner ss = new Scanner(input);
            String tem;
            while (ss.hasNext()) {
                tem = ss.next();
                check.add(tem);
            }

//            if (tem.equals("no") || tem.equals("NO") || tem.equals("No") || tem.equals("nO"))
//            {
//            	if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0]))
//                {
//                    System.out.println("您是本轮第一个玩家，必须输入扑克牌的点数");
//                    continue;
//                }
//                getPoker();
//                GameInfo.Number_of_no++;
//                break;
//            }

            boolean isok = true;
            for (int i = 0; i < check.size(); ++i) {
                String curp = check.get(i).toUpperCase();
                check.set(i, curp);
                char[] ch = new char[100];
                curp.getChars(0, curp.length(), ch, 0);
                if (curp.length() == 1) {
                    if (ch[0] >= '2' || ch[0] <= '9')
                        continue;
                    if (ch[0] == 'A' || ch[0] == 'J' || ch[0] == 'Q' || ch[0] == 'K')
                        continue;
                    isok = false;
                    break;
                } else if (curp.length() == 2) {
                    if (ch[0] == '1' && ch[1] == '0')
                        continue;
                    isok = false;
                    break;
                } else if (curp.length() == 5) {
                    if (curp.equals(GameInfo.AllPoints[13]))
                        continue;
                    isok = false;
                    break;
                } else {
                    isok = false;
                    break;
                }
            }
            if (!isok) {
                if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0]))
                    System.out.println("请输入正确的扑克牌点数");
                else
                    System.out.println("请输入正确的扑克牌点数，或者‘no’");
                continue;
            }

            ArrayList<Poker> check2 = new ArrayList<>();
            isok = true;
            for (int i = 0; i < check.size(); ++i) {
                int ind = FindInd(check.get(i));
                if (ind != -1)
                    check2.add(hand.get(ind));
                else {
                    isok = false;
                    break;
                }
            }
            if (!isok) {
                if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0]))
                    System.out.println("请输入你所拥有的扑克牌点数");
                else
                    System.out.println("请输入你所拥有的扑克牌点数，或者‘no’");
                continue;
            }

            Pss cur = GameInfo.getPokersType(check2);
            if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0])) {
                if (cur.first.equals(GameInfo.CardTypes[0])) {
                    System.out.println("请输入符合规则的扑克牌点数");
                    continue;
                }
                discard(check);
                GameInfo.Number_of_no = 0;
                GameInfo.Last_playing_card_type = cur;
                break;
            } else if (GameInfo.PRank.get(cur.first) > GameInfo.PRank.get(GameInfo.Last_playing_card_type.first)) {
                discard(check);
                GameInfo.Number_of_no = 0;
                GameInfo.Last_playing_card_type = cur;
                break;
            } else if (GameInfo.PRank.get(cur.first).equals(GameInfo.PRank.get(GameInfo.Last_playing_card_type.first))) {
                if (GameInfo.PRank.get(cur.first) == 6 && GameInfo.PRank.get(cur.second) > GameInfo.PRank.get(GameInfo.Last_playing_card_type.second)) {
                    discard(check);
                    GameInfo.Number_of_no = 0;
                    GameInfo.Last_playing_card_type = cur;
                    break;
                } else if (GameInfo.PRank.get(cur.second) == 13 && GameInfo.PRank.get(GameInfo.Last_playing_card_type.second) != 13) {
                    discard(check);
                    GameInfo.Number_of_no = 0;
                    GameInfo.Last_playing_card_type = cur;
                    break;
                } else if (GameInfo.PRank.get(cur.second) == GameInfo.PRank.get(GameInfo.Last_playing_card_type.second) + 1) {
                    discard(check);
                    GameInfo.Number_of_no = 0;
                    GameInfo.Last_playing_card_type = cur;
                    break;
                } else { System.out.println("请输入符合规则的扑克牌点数，或者‘no’"); }
            } else { System.out.println("请输入符合规则的扑克牌点数，或者‘no’"); }
        }
    }

    public void Round() {
        System.out.println("这是 玩家" + this.ID + "号 的回合, 请出牌");
        System.out.println();
        if (GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0]))
            System.out.println("请输出要打出的牌的点数");
        else
            System.out.println("请输出要打出的牌的点数，或者输出\"no\"");
        System.out.println("（如果有多张牌，请用空格隔开）\n");
        aLegalPlay();
        if (hand.isEmpty())
            GameInfo.Winner = this.ID;
    }

    public void connect_server(String IPAddress, int Port) throws IOException {
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
            GameInfo.players_name = new String[GameInfo.Number_of_players];
            GameInfo.players_name[0] = ID + "号 " + name;
            GameInfo.pokers_num = new int[GameInfo.Number_of_players];
            for (int i = 1; i < GameInfo.Number_of_players; ++i) {
                GameInfo.players_name[i] = read();
            }
            GameInfo.First_player = Integer.parseInt(read());
            for (int i = 0; i < 5; ++i) { getPoker(0); }
            if (GameInfo.First_player == ID)
                getPoker(0);
            GameInfo.pokers_num[0] = hand.size();
            if (GameInfo.First_player < ID)
                GameInfo.First_player++;
        }
        else if (read().equals("gameInfo")) {
            ;
        }
    }

    public void send(String msg) {
//        out.println(new String(msg.getBytes(StandardCharsets.UTF_8)));
        out.println(msg);
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