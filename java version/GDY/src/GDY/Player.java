package GDY;
import java.util.ArrayList;
import java.util.Scanner;

public class Player
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