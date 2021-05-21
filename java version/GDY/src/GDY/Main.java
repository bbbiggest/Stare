package GDY;
public class Main
{
    public static void main(String[] args)
    {
        gandengyan.startGame();
        gandengyan.initGame();
        gandengyan.Shuffle();

        Player[] Players = new Player[gandengyan.Number_of_players];
        for (int i = 0; i < gandengyan.Number_of_players; ++i)
        {
            Players[i] = new Player(i, "Anonymous");
        }


        // 一人五张牌，庄家多一张
        for (int i = 0; i < 5; ++i)
            for (int j = 0; j < gandengyan.Number_of_players; ++j)
                Players[j].getPoker();
        Players[gandengyan.First_player].getPoker();

//	    for (int i = 0; i < Number_of_players; ++i)
//	    {
//	    	System.out.println("Player " + i);
//	        Players[i].display_card();
//	        System.out.println();
//	    }


        for (int i = gandengyan.First_player;; ++i)
        {
            i %= gandengyan.Number_of_players;
            gandengyan.clearScreen();
            if (gandengyan.Winner != -99)
            {
                System.out.println("游戏结束");
                System.out.println("胜利者是玩家 " + gandengyan.Winner + "号");
                System.out.println("恭喜! !");
                break;
            }
            if (gandengyan.Last_playing_card.isEmpty() == false)
            {
                System.out.println("上一轮出的牌是：");
                for (var x : gandengyan.Last_playing_card)
                    System.out.print(x.getColor() + '|' + x.getPoint() + "  ");
                System.out.println();
            }
            Players[i].Round();
            if (gandengyan.Number_of_no == gandengyan.Number_of_players)
            {
                i--;
                gandengyan.Number_of_no = 0;
                gandengyan.Last_playing_card_type = new Pss(gandengyan.CardTypes[0], "-1");
            }
        }
    }
}