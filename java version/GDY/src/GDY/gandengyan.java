package GDY;

import java.net.*;
import java.util.*;
import java.io.IOException;

public class gandengyan {
    public static final String[] AllPoints = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
    public static final String[] AllColors = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
    public static final String[] CardTypes = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
    public static ArrayList<Poker> Deck = new ArrayList<Poker>();
    public static int Number_of_players, First_player, Winner, Number_of_no;
    public static Pss Last_playing_card_type = new Pss(CardTypes[0], "-1");
    public static ArrayList<Poker> Last_playing_card = new ArrayList<Poker>();
    public static Seat[] Seats;
    public static String IPAddress;

    gandengyan() throws UnknownHostException, SocketException {
        InetAddress IPAddr = getLocalHostExactAddress();
        IPAddress = IPAddr.getHostAddress();
//        Number_of_players = Main.start.getPeopleNumber();
        Number_of_players = 1;
    }

    void readyToStart() {
        Seats = new Seat[Number_of_players];
        try (var s = new ServerSocket(Main.start.getThePort())) {
            var t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Main.me = new Player();
                        Main.me.autoset();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
            for (int i = 0; i < Number_of_players; ++i) {
                Socket incoming = s.accept();
                Seats[i] = new Seat(i, incoming);
//                var t = new Thread(Seats[i]);
//                t.start();
            }
            System.out.println("people all arrive");
        } catch (IOException e) {
            e.printStackTrace();
        }
        broadcast("start");
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
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr;
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
    public static void initGame() {
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
    static void Shuffle() { Collections.shuffle(Deck); }

    private void broadcast(String msg) {
        for (int i = 0; i < Number_of_players; ++i) {
            if (Seats[i] != null)
                Seats[i].send(msg);
        }
        System.out.println("broadcast: " + msg);
    }

}