/*
 * @Description: 
 * @Author: bbbiggest
 * @Date: 2021-04
 */

#include <iostream>
#include <ctime>
#include <string>
#include <sstream>
#include <unordered_map>
#include <algorithm>
#include <cstdlib>
#include <set>
#include <vector>
using namespace std;
using Psi = pair<string, int>;
using Pss = pair<string, string>;
unordered_map<string, int> PRank;
class Poker;
class Player;
const string AllPoints[14] = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2", "JOKER"};
const string AllColors[6] = {"Diamond", "Club", "Heart", "Spade", "Black", "Red"};
const string CardTypes[8] = {"Invalid", "Single", "Pair", "Straight", "Cons_pairs", "Three", "Bomb", "KingBomb"};
int Number_of_players, First_player;
vector<Poker> Deck; // 牌堆
Player *Players;
int Winner;
Pss Last_playing_card; // 最后出的牌
int Number_of_no;      // 选择不出的人的数量

// enum Suit // 花色
// {
//     Diamond,
//     Club,
//     Heart,
//     Spade,
//     Black,
//     Red
// };

class Poker
{
public:
    Poker() {}
    Poker(string color, string point)
    {
        p_color = color;
        p_point = point;
    }
    Poker(const Poker &pa)
    {
        p_color = pa.p_color;
        p_point = pa.p_point;
    }
    string getPoint()
    {
        return p_point;
    }
    friend bool operator<(const Poker &pa, const Poker &pb);
    friend bool operator==(const Poker &pa, const string &pb);
    friend class Player;
    friend bool isPair(const Poker &pa, const Poker &pb);

private:
    // 颜色和点数
    string p_color;
    string p_point;
};

bool operator<(const Poker &pa, const Poker &pb)
{
    if (PRank[pa.p_point] == PRank[pb.p_point])
        return PRank[pa.p_color] < PRank[pb.p_color];
    return PRank[pa.p_point] < PRank[pb.p_point];
}

bool operator==(const Poker &pa, const string &pb)
{
    return PRank[pa.p_point] == PRank[pb];
}

bool isPair(const Poker &pa, const Poker &pb)
{
    if (pa.p_point == pb.p_point)
        return true;
    if (pa.p_point == AllPoints[13] || pb.p_point == AllPoints[13])
        return true;
    return false;
}

bool isPair(const string &pa, const string &pb)
{
    if (pa == pb)
        return true;
    if (pa == AllPoints[13] || pb == AllPoints[13])
        return true;
    return false;
}

bool cmp_point(const string &sa, const string &sb)
{
    return PRank[sa] < PRank[sb];
}

// Pss getPokersType(vector<Poker> ps)
// {
//     if (ps.size() == 0)
//         return make_pair(CardTypes[0], "-1");
//     sort(ps.begin(), ps.end());
//     if (ps.size() == 1)
//     {
//         if (ps[0].getPoint() == AllPoints[13])
//             return make_pair(CardTypes[0], "-1");
//         return make_pair(CardTypes[1], ps[0].getPoint());
//     }
//     else if (ps.size() == 2)
//     {
//         if (isPair(ps[0], ps[1]))
//         {
//             if (ps[0].getPoint() == AllPoints[13])
//                 return make_pair(CardTypes[7], AllPoints[13]);
//             return make_pair(CardTypes[2], ps[0].getPoint());
//         }
//         if (ps[1].getPoint() == AllPoints[13])
//             return make_pair(CardTypes[2], ps[0].getPoint());
//         return make_pair(CardTypes[0], "-1");
//     }
//     else if (ps.size() == 3)
//     {
//         if (ps[1].getPoint() == AllPoints[13])
//             return make_pair(CardTypes[5], ps[0].getPoint());
//         if (ps[0].getPoint() == ps[1].getPoint())
//             if (ps[2].getPoint() == AllPoints[13] || ps[2].getPoint() == ps[1].getPoint())
//                 return make_pair(CardTypes[5], ps[0].getPoint());
//         int ra = PRank[ps[0].getPoint()], rb = PRank[ps[1].getPoint()], rc = PRank[ps[2].getPoint()];
//         if (ra <= 10 && rb == ra + 1 && (rc == rb + 1 || rc = 14))
//             return make_pair(CardTypes[3], ps[0].getPoint());
//         else if (ra == 11 && rb == 12 && rc == 14)
//             return make_pair(CardTypes[3], AllPoints[9]);
//         return make_pair(CardTypes[0], "-1");
//     }
//     else if (ps.size() == 4)
//     {
//         string temPoint = ps[0].getPoint();
//         int cnt_same = 0;
//         for (int i = 0; i < 4; ++i)
//             if (ps[i].getPoint() == temPoint || ps[i].getPoint() == AllPoints[13])
//                 cnt_same++;
//         if (cnt_same == 4)
//             return make_pair(CardTypes[6], temPoint);

//         // 还没有连对和大顺子

//         return make_pair(CardTypes[0], "-1");
//     }
//     else
//         return make_pair(CardTypes[0], "-1");
// }

Pss getPokersType(vector<string> ps)
{
    if (ps.size() == 0)
        return make_pair(CardTypes[0], "-1");
    sort(ps.begin(), ps.end(), cmp_point);
    if (ps.size() == 1)
    {
        if (ps[0] == AllPoints[13])
            return make_pair(CardTypes[0], "-1");
        return make_pair(CardTypes[1], ps[0]);
    }
    else if (ps.size() == 2)
    {
        if (ps[0] == AllPoints[13])
            return make_pair(CardTypes[7], AllPoints[13]);
        else if (ps[0] == ps[1])
            return make_pair(CardTypes[2], ps[0]);
        else if (ps[1] == AllPoints[13])
            return make_pair(CardTypes[2], ps[0]);
        return make_pair(CardTypes[0], "-1");
    }
    else if (ps.size() == 3)
    {
        if (ps[1] == AllPoints[13])
            return make_pair(CardTypes[5], ps[0]);
        if (ps[0] == ps[1])
            if (ps[2] == AllPoints[13] || ps[2] == ps[1])
                return make_pair(CardTypes[5], ps[0]);
        int ra = PRank[ps[0]], rb = PRank[ps[1]], rc = PRank[ps[2]];
        if (ra <= 10 && rb == ra + 1 && (rc == rb + 1 || rc == 14))
            return make_pair(CardTypes[3], ps[0]);
        else if (ra <= 10 && rb == ra + 2 && rc == 14)
            return make_pair(CardTypes[3], ps[0]);
        else if (ra == 11 && rb == 12 && rc == 14)
            return make_pair(CardTypes[3], AllPoints[9]);
        return make_pair(CardTypes[0], "-1");
    }
    else if (ps.size() == 4)
    {
        string temPoint = ps[0];
        vector<string> cnt_diff;
        int cnt_same = 0;
        for (int i = 0; i < 4; ++i)
        {
            if (ps[i] == temPoint || ps[i] == AllPoints[13])
                cnt_same++;
            else
            {
                bool Exist = false;
                for (int j = 0; j < cnt_diff.size(); ++j)
                    if (ps[i] == cnt_diff[j])
                        Exist = true;
                if (!Exist)
                    cnt_diff.push_back(ps[i]);
            }
        }
        if (cnt_same == 4)
            return make_pair(CardTypes[6], temPoint);
        else if (cnt_same == 3 && ps[3] == AllPoints[13] && PRank[cnt_diff[0]] == PRank[ps[0]] + 1)
            return make_pair(CardTypes[4], temPoint);
        else if (cnt_same == 2 && cnt_diff.size() == 1 && PRank[cnt_diff[0]] == PRank[ps[0]] + 1)
            return make_pair(CardTypes[4], temPoint);

        return make_pair(CardTypes[0], "-1");
    }
    else
        return make_pair(CardTypes[0], "-1");
}

class Player
{
public:
    Player(int tID = 0, string tname = "Anonymous")
    {
        ID = tID;
        name = tname;
    }
    void setID(int tID)
    {
        ID = tID;
    }

    // 展示手牌
    void display_card()
    {
        for (auto x : hand)
            cout << x.p_color << '|' << x.p_point << "  ";
        cout << endl;
    }

    // 获取剩余手牌数量
    int Get_number_of_remaining_hands()
    {
        return hand.size();
    }

    // 摸牌
    void getPoker()
    {
        if (!Deck.empty())
        {
            hand.insert(Deck.back());
            Deck.pop_back();
        }
    }

    // 将牌打出去
    bool discard(vector<string> dc)
    {
        vector<Poker> Discard_pile;
        for (int i = 0; i < dc.size(); ++i)
        {
            bool isok = false;
            for (auto &x : hand)
            {
                if (x == dc[i])
                {
                    isok = true;
                    Discard_pile.push_back(Poker(x));
                    hand.erase(x);
                    break;
                }
            }
            if (!isok)
            {
                for (int j = 0; j < Discard_pile.size(); ++j)
                    hand.insert(Discard_pile[j]);
                return false;
            }
        }
        return true;
    }

    // 一次合法的出牌
    void aLegalPlay()
    {
        string input;
        while (getline(cin, input))
        {
            vector<string> check;
            stringstream ss(input);
            string tem;
            while (ss >> tem)
                check.push_back(tem);

            if (tem == "no" || tem == "NO" || tem == "No" || tem == "nO")
            {
                if (Last_playing_card.first == CardTypes[0])
                {
                    cout << "You must enter the points of any playing card\n";
                    continue;
                }
                getPoker();
                Number_of_no++;
                break;
            }

            bool isok = true;
            for (int i = 0; i < check.size(); ++i)
            {
                string &curp = check[i];
                if (curp.size() == 1)
                {
                    if (curp[0] >= '2' && curp[0] <= '9')
                        continue;
                    if (curp[0] >= 'a')
                        curp[0] -= 32;
                    if (curp[0] == 'A' || curp[0] == 'J' || curp[0] == 'Q' || curp[0] == 'K')
                        continue;
                    isok = false;
                    break;
                }
                else if (curp.size() == 2)
                {
                    if (curp[0] == '1' && curp[1] == '0')
                        continue;
                    isok = false;
                    break;
                }
                else if (curp.size() == 5)
                {
                    for (int i = 0; i < curp.size(); ++i)
                        if (curp[i] >= 'a')
                            curp[i] -= 32;
                    if (curp == AllPoints[13])
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
            if (!isok)
            {
                if (Last_playing_card.first == CardTypes[0])
                    cout << "Please enter the correct point of poker you want to play\n";
                else
                    cout << "Please enter the correct point of poker you want to play, or enter \"no\"\n";
                continue;
            }

            Pss cur = getPokersType(check);
            if (Last_playing_card.first == CardTypes[0])
            {
                if (cur.first == CardTypes[0])
                {
                    cout << "Please enter the point of poker that meet the rules\n";
                    continue;
                }
                if (discard(check))
                {
                    Number_of_no = 0;
                    Last_playing_card = cur;
                    break;
                }
                else
                {
                    cout << "Please enter the point of poker you own\n";
                    continue;
                }
            }
            else if (PRank[cur.first] > PRank[Last_playing_card.first])
            {
                if (discard(check))
                {
                    Number_of_no = 0;
                    Last_playing_card = cur;
                    break;
                }
                else
                {
                    cout << "Please enter the point of poker you own, or enter \"no\"\n";
                    continue;
                }
            }
            else if (cur.first == Last_playing_card.first)
            {
                if (PRank[cur.first] == 6 && PRank[cur.second] > PRank[Last_playing_card.second])
                {
                    if (discard(check))
                    {
                        Number_of_no = 0;
                        Last_playing_card = cur;
                        break;
                    }
                    else
                    {
                        cout << "Please enter the point of poker you own, or enter \"no\"\n";
                        continue;
                    }
                }
                else if (PRank[cur.second] == 13 ||
                         PRank[cur.second] == PRank[Last_playing_card.second] + 1)
                {
                    if (discard(check))
                    {
                        Number_of_no = 0;
                        Last_playing_card = cur;
                        break;
                    }
                    else
                    {
                        cout << "Please enter the point of poker you own, or enter \"no\"\n";
                        continue;
                    }
                }
                else
                {
                    cout << "Please enter the point of poker that meet the rules, or enter \"no\"\n";
                    continue;
                }
            }
            else
            {
                cout << "Please enter the point of poker that meet the rules, or enter \"no\"\n";
                continue;
            }
        }

        // clock_t start = clock();
        // clock_t end = clock();
        // double length_of_time =  (double)(end - start) / CLOCKS_PER_SEC;
    }

    // 回合
    void Round()
    {
        // 这是玩家某某的回合，请出牌
        cout << "This is the round of player " << ID << ", please play the card\n";
        cout << '\n';
        if (Last_playing_card.first == CardTypes[0])
            cout << "Please enter the point of any poker you own\n";
        else
            cout << "Please enter the point of poker you want to play, or enter \"no\"\n";
        cout << "(If there are multiple cards, please separate them with spaces)\n";
        cout << '\n';
        display_card();

        aLegalPlay();

        // 回合结束，进行更新
        if (hand.empty())
            Winner = ID;
    }

private:
    int ID;               // 玩家编号
    string name;          // 玩家名称
    multiset<Poker> hand; // 手牌
};

// 游戏开始
void startGame()
{
    // 请输入玩家数量（2-6人）
    cout << "Please enter the number of players (2-6 people)\n";
    string input;
    while (getline(cin, input))
    {
        if (input.size() >= 2)
        {
            cout << "Please enter the number of players (Numbers 2 to 6)\n";
            continue;
        }
        // else if (input.size() == 2)
        // {
        //     if (input[0] != '1' || input[1] != '0')
        //     {
        //         cout << "Please enter the number of players (Numbers 2 to 6)\n";
        //         continue;
        //     }
        //     else
        //     {
        //         Number_of_players = 10;
        //         break;
        //     }
        // }
        else if (input[0] < '2' || input[0] > '6')
        {
            cout << "Please enter the number of players (Numbers 2 to 6)\n";
            continue;
        }
        else
        {
            Number_of_players = input[0] - '0';
            break;
        }
    }
}

// 初始化
void initGame()
{
    srand(time(nullptr));
    for (int i = 0; i < 14; ++i)
        PRank[AllPoints[i]] = i + 1;
    for (int i = 0; i < 6; ++i)
        PRank[AllColors[i]] = i + 1;
    for (int i = 0; i < 6; ++i)
        PRank[CardTypes[i]] = 1;
    PRank[CardTypes[6]] = 2;
    PRank[CardTypes[7]] = 3;
    PRank["-1"] = -1;

    for (int i = 0; i < 4; ++i)
        for (int j = 0; j < 13; ++j)
            Deck.push_back(Poker(AllColors[i], AllPoints[j]));
    Deck.push_back(Poker(AllColors[4], AllPoints[13]));
    Deck.push_back(Poker(AllColors[5], AllPoints[13]));

    // // 大于三个人就用两幅扑克牌
    // if (Number_of_players > 3)
    // {
    //     Deck.resize(Deck.size() * 2);
    //     for (int i = 0; i < 54; ++i)
    //         Deck[i + 54] = Deck[i];
    // }

    First_player = rand() % Number_of_players;
    Winner = -99;
    Number_of_no = 0;
    Last_playing_card = make_pair(CardTypes[0], "-1");
}

// 洗牌
void Shuffle()
{
    random_shuffle(Deck.begin(), Deck.end());
}

int main()
{

    startGame();
    initGame();
    Shuffle();

    Players = new Player[Number_of_players];
    for (int i = 0; i < Number_of_players; ++i)
        Players[i].setID(i);

    // 发牌
    for (int i = 0; i < 5; ++i)
        for (int j = 0; j < Number_of_players; ++j)
            Players[j].getPoker();
    Players[First_player].getPoker();

    // 测试一下发牌有没有问题
    // for (int i = 0; i < Number_of_players; ++i)
    // {
    //     cout << "Player " << i << '\n';
    //     Players[i].display_card();
    //     cout << '\n';
    // }

    // 游戏开始
    for (int i = First_player;; ++i)
    {
        i %= Number_of_players;
        system("cls");
        if (Winner != -99)
        {
            cout << "Game over\n";
            cout << "The winner is player " << Winner << '\n';
            cout << "Congratulate! !\n";
            break;
        }
        Players[i].Round();
        if (Number_of_no == Number_of_players)
        {
            i--;
            Number_of_no = 0;
            Last_playing_card = make_pair(CardTypes[0], "-1");
        }
    }

    system("pause");
    return 0;
}