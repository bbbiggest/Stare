package GDY;
import java.util.*;
import java.io.IOException;
public class gandengyan
{
	public static final String[] AllPoints = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
	public static final String[] AllColors = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
	public static final String[] CardTypes = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
	public static HashMap<String, Integer> PRank = new HashMap<String, Integer>();
	public static ArrayList<Poker> Deck = new ArrayList<Poker>(), Discard_pile = new ArrayList<Poker>();
	public static int Number_of_players, First_player, Winner, Number_of_no;
	public static Map.Entry<String, String> Last_playing_card;
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		System.out.println("请输入玩家数量(2~6)");
		Number_of_players = in.nextInt();
		First_player = rand.nextInt(Number_of_players);
		Winner = -99;
		
		for (int i = 0; i < 14; ++i)
			PRank.put(AllPoints[i], i + 1);
		for (int i = 0; i < 6; ++i)
			PRank.put(AllColors[i], i + 1);
		
		for (int i = 0; i < 13; ++i)
			for (int j = 0; j < 4; ++j)
				Deck.add(new Poker(AllColors[j], AllPoints[i]));
		Deck.add(new Poker(AllColors[4], AllPoints[13]));
		Deck.add(new Poker(AllColors[5], AllPoints[13]));
		
		Collections.shuffle(Deck);
		
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
	        Players[i].Round();
	        if (Number_of_no == Number_of_players)
	        {
	            i--;
	            Number_of_no = 0;
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
    
    public void discard(ArrayList<String> dc)
    {
        for (int i = 0; i < dc.size(); ++i)
        {
            for (int j = 0; j < hand.size(); ++j)
            {
                if (hand.get(j).getPoint().equals(dc.get(i)))
                {
                	hand.remove(j);
                    break;
                }
            }
        }
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
                getPoker();
                gandengyan.Number_of_no++;
                break;
            }
            
            discard(check);

//            Pss cur = getPokersType(check);
//            // 暂时先默认输入都为合法的
//            if (cur.first == gandengyan.Last_playing_card.first || 1)
//            {
//                discard(check);
//                gandengyan.Number_of_no = 0;
//                gandengyan.Last_playing_card = cur;
//            }
            break;
        }
    }

    public void Round()
    {
    	System.out.println("这是 玩家" + this.ID + "号的回合, 请出牌");
    	System.out.println();
    	System.out.println("请输出要打出的牌的点数，或者输出\"no\"");
    	System.out.println("（如果有多张牌，请用空格隔开）\n");
    	display_card();
    	aLegalPlay();
    	if (hand.isEmpty())
            gandengyan.Winner = this.ID;
    }

    public void getPoker()
    {
        hand.add(gandengyan.Deck.get(0));
        gandengyan.Deck.remove(0);
        hand.sort(null);
    }
};