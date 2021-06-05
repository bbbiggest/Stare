package GDY;
import java.net.Socket;
import java.util.*;
import javax.swing.JLabel;
import java.io.IOException;

public class gandengyan
{
	public static final String[] AllPoints = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
	public static final String[] AllColors = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
	public static final String[] CardTypes = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
	public static HashMap<String, Integer> PRank = new HashMap<String, Integer>();
	public static ArrayList<Poker> Deck = new ArrayList<Poker>();
	public static int Number_of_players, First_player, Winner, Number_of_no;
	public static Pss Last_playing_card_type = new Pss(CardTypes[0], "-1");
	public static ArrayList<Poker> Last_playing_card = new ArrayList<Poker>();
	public static Player[] Players;

	public static void clearScreen()
	{
	    //Clears Screen in java
	    try {

	        if (System.getProperty("os.name").contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");

	    } catch (IOException | InterruptedException ex) {}
	}

	// 控制台输入玩家数量
	public static void startGame()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("请输入玩家数量(2~6)");
		Number_of_players = in.nextInt();
		while (Number_of_players < 2 || Number_of_players > 6)
		{
			System.out.println("请输入玩家数量(2~6)");
			Number_of_players = in.nextInt();
		}
	}

	// 初始化
	public static void initGame()
	{
		for (int i = 0; i < 14; ++i)
			PRank.put(AllPoints[i], i + 1);
		for (int i = 0; i < 6; ++i)
			PRank.put(AllColors[i], i + 1);
	    for (int i = 0; i < 6; ++i)
	        PRank.put(CardTypes[i], 1);
	    PRank.put(CardTypes[6], 2);
	    PRank.put(CardTypes[7], 3);
	    PRank.put("-1", -1);

		for (int i = 0; i < 13; ++i)
			for (int j = 0; j < 4; ++j)
				Deck.add(new Poker(AllColors[j], AllPoints[i]));
		Deck.add(new Poker(AllColors[4], AllPoints[13]));
		Deck.add(new Poker(AllColors[5], AllPoints[13]));

		Random rand = new Random();
		First_player = rand.nextInt(Number_of_players);
		Winner = -99;
		Number_of_no = 0;
		Last_playing_card_type = new Pss(CardTypes[0], "-1");

		Players = new Player[Number_of_players];
        for (int i = 0; i < Number_of_players; ++i)
            Players[i] = new Player(i, "Anonymous");
	}

	// 随机洗牌
	public static void Shuffle()
	{
		Collections.shuffle(Deck);
	}

	// 两张牌比较大小
	public static boolean cmp_point(String sa, String sb)
	{
		return (PRank.get(sa) < PRank.get(sb));
	}

	// 判断牌型
	public static Pss getPokersType(ArrayList<Poker> ps)
	{
		if (ps.size() == 0)
			return new Pss(CardTypes[0], "-1");
		ps.sort(null);
		if (ps.size() == 1)
		{
			if (ps.get(0).getPoint().equals(AllPoints[13]))
				return new Pss(CardTypes[0], "-1");
			return new Pss(CardTypes[1], ps.get(0).getPoint());
		}
		else if (ps.size() == 2)
		{
			if (ps.get(0).getPoint().equals(AllPoints[13]))
				return new Pss(CardTypes[7], AllPoints[13]);
			else if (ps.get(0).getPoint().equals(ps.get(1).getPoint()))
				return new Pss(CardTypes[2], ps.get(0).getPoint());
			else if (ps.get(1).getPoint().equals(AllPoints[13]))
				return new Pss(CardTypes[2], ps.get(0).getPoint());
			else
				return new Pss(CardTypes[0], "-1");
		}
		else if (ps.size() == 3)
		{
			if (ps.get(1).getPoint().equals(AllPoints[13]))
				return new Pss(CardTypes[5], ps.get(0).getPoint());
			if (ps.get(0).getPoint().equals(ps.get(1).getPoint()))
				if (ps.get(2).getPoint().equals(AllPoints[13]) || ps.get(2).getPoint().equals(ps.get(1).getPoint()))
					return new Pss(CardTypes[5], ps.get(0).getPoint());
			int ra = PRank.get(ps.get(0).getPoint());
			int rb = PRank.get(ps.get(1).getPoint());
			int rc = PRank.get(ps.get(2).getPoint());
			if (ra <= 10 && rb == ra + 1 && (rc == rb + 1 || rc == 14))
				return new Pss(CardTypes[3], ps.get(0).getPoint());
			else if (ra <= 10 && rb == ra + 2 && rc == 14)
				return new Pss(CardTypes[3], ps.get(0).getPoint());
			else if (ra == 11 && rb == 12 && rc == 14)
				return new Pss(CardTypes[3], AllPoints[9]);
			else
				return new Pss(CardTypes[0], "-1");
		}
		else if (ps.size() == 4)
		{
			String temPoint = ps.get(0).getPoint();
			ArrayList<String> cnt_diff = new ArrayList();
			int cnt_same = 0;
			for (int i = 0; i < 4; ++i)
			{
				if (ps.get(i).getPoint().equals(temPoint) || ps.get(i).getPoint().equals(AllPoints[13]))
	                cnt_same++;
				else
				{
					boolean Exist = false;
					for (int j = 0; j < cnt_diff.size(); ++j)
	                    if (ps.get(i).getPoint().equals(cnt_diff.get(j)))
	                        Exist = true;
	                if (!Exist)
	                    cnt_diff.add(ps.get(i).getPoint());
				}
			}
			if (cnt_same == 4)
				return new Pss(CardTypes[6], temPoint);
			else if (cnt_same == 3 && ps.get(3).getPoint().equals(AllPoints[13]) && PRank.get(cnt_diff.get(0)) == PRank.get(ps.get(0).getPoint()) + 1)
				return new Pss(CardTypes[4], temPoint);
			else if (cnt_same == 2 && cnt_diff.size() == 1 && PRank.get(cnt_diff.get(0)) == PRank.get(ps.get(0).getPoint()) + 1)
				return new Pss(CardTypes[4], temPoint);
			return new Pss(CardTypes[0], "-1");
		}
		else
			return new Pss(CardTypes[0], "-1");
	}

//	public static void temp()
//	{
//		while (true)
//		{
//			Socket incoming = s.accept();
//			Runnable r = ThreadedEchoHandler(incoming);
//
//			Thread t = new Thread(r);
//			t.start();
//		}
//	}

	private void broadcast(String msg) {
        for (int i = 0; i < gandengyan.Number_of_players; ++i) {
            if (gandengyan.Players[i] != null);
                gandengyan.Players[i].send(msg);
        }
    }
}

// pair of (String, String)
class Pss
{
	public String first;
	public String second;
	public Pss() {}
	public Pss(String first, String second)
	{
		this.first = first;
		this.second = second;
	}
}

