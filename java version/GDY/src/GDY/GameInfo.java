package GDY;

import java.util.ArrayList;
import java.util.HashMap;

public class GameInfo {
    public static final String[] AllPoints = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
    public static final String[] AllColors = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
    public static final String[] CardTypes = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
    public static HashMap<String, Integer> PRank = new HashMap<String, Integer>();
    public static int Number_of_players, First_player, Winner, Number_of_no;
    public static Pss Last_playing_card_type = new Pss(CardTypes[0], "-1");
    public static ArrayList<Poker> Last_playing_card = new ArrayList<Poker>();
    public static String[] players_name;
    public static int[] pokers_num;
    GameInfo () {}

    void initGame() {
        for (int i = 0; i < 14; ++i)
            PRank.put(AllPoints[i], i + 1);
        for (int i = 0; i < 6; ++i)
            PRank.put(AllColors[i], i + 1);
        for (int i = 0; i < 6; ++i)
            PRank.put(CardTypes[i], 1);
        PRank.put(CardTypes[6], 2);
        PRank.put(CardTypes[7], 3);
        PRank.put("-1", -1);
    }

    // 两张牌比较大小
    public static boolean cmp_point(String sa, String sb) {
        return (PRank.get(sa) < PRank.get(sb));
    }

    // 判断牌型
    public static Pss getPokersType(ArrayList<Poker> ps) {
        if (ps.size() == 0)
            return new Pss(CardTypes[0], "-1");
        ps.sort(null);
        if (ps.size() == 1) {
            if (ps.get(0).getPoint().equals(AllPoints[13]))
                return new Pss(CardTypes[0], "-1");
            return new Pss(CardTypes[1], ps.get(0).getPoint());
        } else if (ps.size() == 2) {
            if (ps.get(0).getPoint().equals(AllPoints[13]))
                return new Pss(CardTypes[7], AllPoints[13]);
            else if (ps.get(0).getPoint().equals(ps.get(1).getPoint()))
                return new Pss(CardTypes[2], ps.get(0).getPoint());
            else if (ps.get(1).getPoint().equals(AllPoints[13]))
                return new Pss(CardTypes[2], ps.get(0).getPoint());
            else
                return new Pss(CardTypes[0], "-1");
        } else if (ps.size() == 3) {
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
        } else if (ps.size() == 4) {
            String temPoint = ps.get(0).getPoint();
            ArrayList<String> cnt_diff = new ArrayList();
            int cnt_same = 0;
            for (int i = 0; i < 4; ++i) {
                if (ps.get(i).getPoint().equals(temPoint) || ps.get(i).getPoint().equals(AllPoints[13]))
                    cnt_same++;
                else {
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
        } else
            return new Pss(CardTypes[0], "-1");
    }
}