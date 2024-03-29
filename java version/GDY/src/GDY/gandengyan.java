package GDY;

import java.net.*;
import java.util.*;
import java.io.IOException;

public class gandengyan {
    public static final String[] AllPoints = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
    public static final String[] AllColors = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
    public static final String[] CardTypes = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
    public static ArrayList<Poker> Deck = new ArrayList<>();
    public static int Number_of_players, First_player, Winner, Number_of_no;
    public static Pss Last_playing_card_type = new Pss(CardTypes[0], "-1");
    public static Seat[] Seats;
    public static String IPAddress;
    public static int round;

    gandengyan() {
        InetAddress IPAddr = getLocalHostExactAddress();
        assert IPAddr != null;
        IPAddress = IPAddr.getHostAddress();
        Number_of_players = Main.start.getPeopleNumber();
    }

    void readyToStart() {
        Seats = new Seat[Number_of_players];
        var t1 = new Thread(() -> {
            try {
                Main.me = new Player();
                Main.me.autoset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        var t2 = new Thread(() -> {
            try (var s = new ServerSocket(Main.start.getThePort())) {

                for (int i = 0; i < Number_of_players; ++i) {
                    Socket incoming = s.accept();
                    Seats[i] = new Seat(i, incoming);
                }
                System.out.println("people all arrive");
            } catch (IOException e) {
                e.printStackTrace();
            }
            initGame();
            broadcast("start");
            broadcastStartInfo();
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < Number_of_players; ++j)
                    Seats[j].putPoker();
            }
            Seats[First_player].putPoker();
            broadcastGameInfo();
            Game();
        });
        t2.start();
    }

    void Game() {
        var t = new Thread(() -> {
            for (round = First_player; ; ++round) {
                if (Number_of_no == Number_of_players) {
                    round--;
                    Number_of_no = 0;
                    Last_playing_card_type = new Pss(CardTypes[0], "-1");
                    for (int i = 0; i < Number_of_players; ++i) {
                        Seats[i].Last_playing_card = new ArrayList<>();
                    }
                }
                round %= Number_of_players;
                broadcastGameInfo();
                broadcast("ROUND");
                broadcast("" + round);
                try {
                    Seats[round].Round();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < Number_of_players; ++i) {
                    if (Seats[i].pokers_num == 0) {
                        Winner = i;
                        break;
                    }
                }
                broadcastGameInfo();
                if (Winner != -99) {
                    break;
                }
            }
        });
        t.start();
    }

    public static InetAddress getLocalHostExactAddress() {
        try {
            InetAddress candidateAddress = null;
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface iface = networkInterfaces.nextElement();
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()
                                && !inetAddr.getHostAddress().equals(InetAddress.getLocalHost().getHostAddress())) {
                            candidateAddress = inetAddr;
                        }
                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }

            return candidateAddress == null ? InetAddress.getLocalHost() : candidateAddress;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 初始化
    void initGame() {
        for (int i = 0; i < 13; ++i)
            for (int j = 0; j < 4; ++j)
                Deck.add(new Poker(AllColors[j], AllPoints[i]));
        Deck.add(new Poker(AllColors[4], AllPoints[13]));
        Deck.add(new Poker(AllColors[5], AllPoints[13]));
        Shuffle();

        Random rand = new Random();
        First_player = rand.nextInt(Number_of_players);
        Winner = -99;
        Number_of_no = 0;
        Last_playing_card_type = new Pss(CardTypes[0], "-1");
    }

    // 随机洗牌
    void Shuffle() { Collections.shuffle(Deck); }

    void broadcast(String msg) {
        for (int i = 0; i < Number_of_players; ++i) {
                Seats[i].send(msg);
        }
    }

    void broadcastStartInfo() {
        broadcast("INFO");
        for (int i = 0; i < Number_of_players; ++i)
            Seats[i].sendStartInfo();
    }

    void broadcastGameInfo() {
        broadcast("INFO");
        for (int i = 0; i < Number_of_players; ++i)
            Seats[i].sendGameInfo();
    }

}