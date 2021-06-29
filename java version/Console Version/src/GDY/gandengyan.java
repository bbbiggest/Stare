package GDY;
import java.util.*;
import java.io.IOException;
public class gandengyan
{
	public static final String[] AllPoints = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
	public static final String[] AllColors = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
	public static final String[] CardTypes = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
	public static HashMap<String, Integer> PRank = new HashMap<String, Integer>();
	public static ArrayList<Poker> Deck = new ArrayList<Poker>();
	public static int Number_of_players, First_player, Winner, Number_of_no;
//	public static Map.Entry<String, String> Last_playing_card_type;
	public static Pss Last_playing_card_type = new Pss(CardTypes[0], "-1");
	public static ArrayList<Poker> Last_playing_card = new ArrayList<Poker>();
	
	public static void main(String[] args)
	{
		startGame();
	    initGame();
	    Shuffle();
		
		Player[] Players = new Player[Number_of_players];
		for (int i = 0; i < Number_of_players; ++i)
		{
	        Players[i] = new Player(i, "Anonymous");
		}
		
		for (int i = 0; i < 5; ++i)
	        for (int j = 0; j < Number_of_players; ++j)
	            Players[j].getPoker();
	    Players[First_player].getPoker();
	    
//	    for (int i = 0; i < Number_of_players; ++i)
//	    {
//	    	System.out.println("Player " + i);
//	        Players[i].display_card();
//	        System.out.println();
//	    }
	    
	    for (int i = First_player;; ++i)
	    {
	        i %= Number_of_players;
	        clearScreen();
	        if (Winner != -99)
	        {
	        	System.out.println("游戏结束");
	            System.out.println("胜利者是玩家 " + Winner + "号");
	            System.out.println("恭喜! !");
	            break;
	        }
	        if (Last_playing_card.isEmpty() == false)
	        {
	        	System.out.println("上一轮出的牌是：");
	        	for (var x : Last_playing_card)
	            	System.out.print(x.getColor() + '|' + x.getPoint() + "  ");
	            System.out.println();
	        }
	        Players[i].Round();
	        if (Number_of_no == Number_of_players)
	        {
	            i--;
	            Number_of_no = 0;
	            Last_playing_card_type = new Pss(CardTypes[0], "-1");
	        }
	    }
	}
	
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
//		Last_playing_card_type = Pair.of(CardTypes[0], "-1");
		Last_playing_card_type = new Pss(CardTypes[0], "-1");
	}
	public static void Shuffle()
	{
		Collections.shuffle(Deck);
	}
	public static boolean cmp_point(String sa, String sb)
	{
		return (PRank.get(sa) < PRank.get(sb));
	}
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
}

class Pair
{
    public static <T, U> Map.Entry<T, U> of(T first, U second) 
    {
        return new AbstractMap.SimpleEntry<>(first, second);
    }
}

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

class Poker implements Comparable<Poker>
{
	private String p_color;
	private String p_point;
	public Poker() {}
	public Poker(String p_color, String p_point)
	{
		this.p_color = p_color;
        this.p_point = p_point;
	}
	public Poker(Poker pa)
	{
		this.p_color = pa.p_color;
		this.p_point = pa.p_point;
	}
	public String getColor()
	{
		return this.p_color;
	}
	public String getPoint()
	{
		return this.p_point;
	}
	public boolean equals(Object otherObject)
	{
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Poker other = (Poker)otherObject;
			return p_point.equals(other.p_point) && p_point.equals(other.p_color);
	}
	public int compareTo(Poker other)
	{
		int diff = Integer.compare(gandengyan.PRank.get(this.p_point), gandengyan.PRank.get(other.p_point));
		return diff != 0 ? diff : Integer.compare(gandengyan.PRank.get(this.p_color), gandengyan.PRank.get(other.p_color));
	}
}

class Player
{
	private int ID;
	private String name;
	private ArrayList<Poker> hand = new ArrayList<Poker>();
	public Player() 
	{
		this.ID = 0;
		this.name = "Anonymous";
	}
    public Player(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }
    public void setID(int tID)
    {
        ID = tID;
    }

    public void display_card()
    {
        for (var x : hand)
        	System.out.print(x.getColor() + '|' + x.getPoint() + "  ");
        System.out.println();
    }
    
    public int Get_number_of_remaining_hands()
    {
        return hand.size();
    }
    
    public void getPoker()
    {
    	if (!gandengyan.Deck.isEmpty())
    	{
    		hand.add(gandengyan.Deck.get(0));
    		gandengyan.Deck.remove(0);
    		hand.sort(null);
    	}
    }
    
    public boolean discard(ArrayList<String> dc)
    {
    	ArrayList<Poker> Discard_pile = new ArrayList<Poker>();
        for (int i = 0; i < dc.size(); ++i)
        {
        	boolean isok = false;
            for (int j = 0; j < hand.size(); ++j)
            {
                if (hand.get(j).getPoint().equals(dc.get(i)))
                {
                	isok = true;
                	Discard_pile.add(hand.get(j));
                	hand.remove(j);
                    break;
                }
            }
            if (!isok)
            {
            	for (int j = 0; j < Discard_pile.size(); ++j)
            		hand.add(Discard_pile.get(j));
            	return false;
            }
        }
        gandengyan.Last_playing_card = Discard_pile;
        return true;
    }
    
    public int FindInd(String sa)
    {
    	for (int i = 0; i < hand.size(); ++i)
    	{
    		if (hand.get(i).getPoint().equals(sa))
    			return i;
    	}
    	return -1;
    }
    
    public void aLegalPlay()
    {
    	Scanner in = new Scanner(System.in);
        while (true)
        {
        	String input = in.nextLine();
            ArrayList<String> check = new ArrayList<String>();
            Scanner ss = new Scanner(input);
            String tem = "";
            while (ss.hasNext())
            {
            	tem = ss.next();
            	check.add(tem);
            }

            if (tem.equals("no") || tem.equals("NO") || tem.equals("No") || tem.equals("nO"))
            {
            	if (gandengyan.Last_playing_card_type.first.equals(gandengyan.CardTypes[0]))
                {
                    System.out.println("您是本轮第一个玩家，必须输入扑克牌的点数");
                    continue;
                }
                getPoker();
                gandengyan.Number_of_no++;
                break;
            }
            
            boolean isok = true;
            for (int i = 0; i < check.size(); ++i)
            {
            	String curp = check.get(i).toUpperCase();
            	check.set(i, curp);
            	char[] ch = new char[100];
        		curp.getChars(0, curp.length(), ch, 0);
            	if (curp.length() == 1)
            	{
            		if (ch[0] >= '2' || ch[0] <= '9')
            			continue;
            		if (ch[0] == 'A' || ch[0] == 'J' || ch[0] == 'Q' || ch[0] == 'K')
            			continue;
            		isok = false;
            		break;
            	}
            	else if (curp.length() == 2)
            	{
            		if (ch[0] == '1' && ch[1] == '0')
            			continue;
            		isok = false;
            		break;
            	}
            	else if (curp.length() == 5)
            	{
                    if (curp.equals(gandengyan.AllPoints[13]))
                        continue;
                    isok = false;
                    break;
            	}
            	else
            	{
            		isok = false;
                    break;
            	}
            }
            if (isok == false)
            {
            	if (gandengyan.Last_playing_card_type.first.equals(gandengyan.CardTypes[0]))
            		System.out.println("请输入正确的扑克牌点数");
                else
                	System.out.println("请输入正确的扑克牌点数，或者‘no’");
                continue;
            }
            
            ArrayList<Poker> check2 = new ArrayList<Poker>();
            isok = true;
            for (int i = 0; i < check.size(); ++i)
            {
            	int ind = FindInd(check.get(i));
            	if (ind != -1)
            		check2.add(hand.get(ind));
            	else
            	{
            		isok = false;
            		break;
            	}
            }
            if (isok == false)
            {
            	if (gandengyan.Last_playing_card_type.first.equals(gandengyan.CardTypes[0]))
            		System.out.println("请输入你所拥有的扑克牌点数");
            	else
            		System.out.println("请输入你所拥有的扑克牌点数，或者‘no’");
            	continue;
            }
            
            Pss cur = gandengyan.getPokersType(check2);
            if (gandengyan.Last_playing_card_type.first.equals(gandengyan.CardTypes[0]))
            {
            	if (cur.first.equals(gandengyan.CardTypes[0]))
            	{
            		System.out.println("请输入符合规则的扑克牌点数");
            		continue;
            	}
            	discard(check);
            	gandengyan.Number_of_no = 0;
                gandengyan.Last_playing_card_type = cur;
                break;
            }
            else if (gandengyan.PRank.get(cur.first) > gandengyan.PRank.get(gandengyan.Last_playing_card_type.first))
            {
            	discard(check);
            	gandengyan.Number_of_no = 0;
                gandengyan.Last_playing_card_type = cur;
                break;
            }
            else if (gandengyan.PRank.get(cur.first) == gandengyan.PRank.get(gandengyan.Last_playing_card_type.first))
            {
            	if (gandengyan.PRank.get(cur.first) == 6 && gandengyan.PRank.get(cur.second) > gandengyan.PRank.get(gandengyan.Last_playing_card_type.second))
            	{
            		discard(check);
                	gandengyan.Number_of_no = 0;
                    gandengyan.Last_playing_card_type = cur;
                    break;
            	}
            	else if (gandengyan.PRank.get(cur.second) == 13 && gandengyan.PRank.get(gandengyan.Last_playing_card_type.second) != 13)
            	{
            		discard(check);
                	gandengyan.Number_of_no = 0;
                    gandengyan.Last_playing_card_type = cur;
                    break;
            	}
            	else if (gandengyan.PRank.get(cur.second) == gandengyan.PRank.get(gandengyan.Last_playing_card_type.second) + 1)
            	{
            		discard(check);
                	gandengyan.Number_of_no = 0;
                    gandengyan.Last_playing_card_type = cur;
                    break;
            	}
            	else
            	{
            		System.out.println("请输入符合规则的扑克牌点数，或者‘no’");
            		continue;
            	}
            }
            else
        	{
        		System.out.println("请输入符合规则的扑克牌点数，或者‘no’");
        		continue;
        	}
        }
    }

    public void Round()
    {
    	System.out.println("这是 玩家" + this.ID + "号 的回合, 请出牌");
    	System.out.println();
    	if (gandengyan.Last_playing_card_type.first.equals(gandengyan.CardTypes[0]))
    		System.out.println("请输出要打出的牌的点数");
    	else
    		System.out.println("请输出要打出的牌的点数，或者输出\"no\"");
    	System.out.println("（如果有多张牌，请用空格隔开）\n");
    	display_card();
    	aLegalPlay();
    	if (hand.isEmpty())
            gandengyan.Winner = this.ID;
    }
};