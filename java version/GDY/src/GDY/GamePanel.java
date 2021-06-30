package GDY;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {

    private static final int[] text_x = {170, 350, 1030, 2, 2, 1030};
    private static final int[] text_y = {520, 50, 480, 480, 190, 190};
    private static final int text_width = 300, text_height = 50;
    private static final int[] poker_back_x = {5, 600, 1130, 40, 40, 1130};
    private static final int[] poker_back_y = {535, 20, 320, 320, 35, 35};
    private static final int poker_back_width = 100, poker_back_height = 144;
    private final MyButton yesButton, noButton;
    private final JLabel prompt, roundLabel;
    private PokerLabel[] hand;
    private final JLabel[] poker_back;
    private JLabel[][] last_pokers;

    public GamePanel() throws IOException {
        Main.me.acceptInfo();
        this.setLayout(null);
        this.setOpaque(false);

        // 用户名的位置
        JLabel[] nameLabel = new JLabel[GameInfo.Number_of_players];
        for (int i = 0; i < GameInfo.Number_of_players; ++i) {
            //读取用户名
            nameLabel[i] = new JLabel("玩家" + GameInfo.players_name[i], JLabel.CENTER);
            nameLabel[i].setFont(new Font("楷体", Font.BOLD, 28));
            nameLabel[i].setForeground(Color.LIGHT_GRAY);
            nameLabel[i].setBounds(text_x[i], text_y[i], text_width, text_height);
            this.add(nameLabel[i]);
        }

        // 每个人剩余牌数的位置
        ImageIcon pokerback = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/images/purple_back.png")));
        pokerback = new ImageIcon(pokerback.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
        poker_back = new JLabel[GameInfo.Number_of_players];
        last_pokers = new JLabel[GameInfo.Number_of_players][];
        for (int i = 1; i < GameInfo.Number_of_players; ++i) {
            last_pokers[i] = new JLabel[1];
            last_pokers[i][0] = new JLabel();
            poker_back[i] = new JLabel();
            poker_back[i].setIcon(pokerback);
            poker_back[i].setBounds(poker_back_x[i], poker_back_y[i], poker_back_width, poker_back_height);

            poker_back[i].setText("" + GameInfo.pokers_num[i]);
            poker_back[i].setFont(new Font(null, Font.BOLD, 60));
            poker_back[i].setForeground(new Color(245, 133, 99));
            poker_back[i].setHorizontalTextPosition(SwingConstants.CENTER);
            poker_back[i].setVerticalTextPosition(SwingConstants.CENTER);
            this.add(poker_back[i]);
        }

        // 出牌键
        yesButton = new MyButton("出牌");
        yesButton.setFont(new Font(null, Font.BOLD, 18));
        yesButton.setBounds(520, 426, 80, 45);
        yesButton.addActionListener(e -> {
            System.out.print("want put: ");
            for (var x : Main.me.wantPut) {
                System.out.print(x.toString() + " ");
            }
            System.out.println();
            Main.me.wantPlay();
            Main.me.wantPut = new ArrayList<>();
            updateHand();
        });

        // 不出键
        noButton = new MyButton("不出");
        noButton.setFont(new Font(null, Font.BOLD, 18));
        noButton.setBounds(680, 426, 80, 45);
        noButton.addActionListener(e -> {
            try {
                Main.me.noPlay();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            updateHand();
            System.out.println("no put");
        });

        // 提示文字
        prompt = new JLabel("", JLabel.CENTER);
        prompt.setFont(new Font("楷体", Font.PLAIN, 17));
        prompt.setForeground(Color.YELLOW);
        prompt.setBounds(440, 479, 400, 20);
        add(prompt);
        roundLabel = new JLabel("这是XX的回合", JLabel.CENTER);
        roundLabel.setFont(new Font("楷体", Font.PLAIN, 24));
        roundLabel.setForeground(Color.YELLOW);
        roundLabel.setBounds(440, 200, 400, 40);
        add(roundLabel);

        // 自己的牌
        hand = new PokerLabel[Main.me.hand.size()];
        int handleft = 640 - ((Main.me.hand.size()) * 25);
        for (int i = 0; i < Main.me.hand.size(); ++i) {
            hand[i] = new PokerLabel(Main.me.hand.get(i));
            hand[i].setX(handleft + i * 40);
        }
        for (int i = hand.length - 1; i >= 0; --i)
            add(hand[i]);

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (PokerLabel pokerLabel : hand) {
                    pokerLabel.allDown();
                }
            }
        });

        // 上次出牌的位置
//		if (GameInfo.Last_playing_card.isEmpty() == false || true) {
//			JLabel[] lastpoker = new JLabel[GameInfo.Last_playing_card.size()];
//			// test
////			JLabel[] lastpoker = new JLabel[3];
////			GameInfo.Last_playing_card.add(new Poker("Club", "3"));
////			GameInfo.Last_playing_card.add(new Poker("Club", "4"));
////			GameInfo.Last_playing_card.add(new Poker("Club", "5"));
//			int i = 0;
//			int left = 660 - (GameInfo.Last_playing_card.size() * 40 + 10);
//			for (var x : GameInfo.Last_playing_card) {
//				lastpoker[i] = new JLabel();
//				ImageIcon img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource(x.getPic_addr())));
//				img = new ImageIcon(img.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
//				lastpoker[i].setIcon(img);
//				i++;
//			}
//			for (int j = 0; j < GameInfo.Last_playing_card.size(); j++) {
//				lastpoker[j].setBounds(left + j * 40, 260, poker_back_width, poker_back_height);
//			}
//			for (int j = GameInfo.Last_playing_card.size() - 1; j >= 0; j--) {
//				this.add(lastpoker[j]);
//			}
//		}

        this.setVisible(true);
    }

    void updateInfo() throws IOException {
        Main.me.acceptInfo();
        for (int i = 1; i < GameInfo.Number_of_players; ++i) {
            if (last_pokers.length > 0) {
                for (int j = 0; j < last_pokers[i].length; ++j) {
                    remove(last_pokers[i][j]);
                }
            }
        }
        for (int i = 1; i < GameInfo.Number_of_players; ++i) {
            poker_back[i].setText("" + GameInfo.pokers_num[i]);
        }
        for (int i = 1; i < GameInfo.Number_of_players; ++i) {
            if (GameInfo.Last_playing_card[i].size() == 0) {
                last_pokers[i] = new JLabel[1];
                last_pokers[i][0] = new JLabel("", JLabel.CENTER);
                last_pokers[i][0].setFont(new Font(null, Font.PLAIN, 22));
                last_pokers[i][0].setForeground(Color.RED);
                last_pokers[i][0].setBounds(poker_back_x[i] + 100, poker_back_y[i], 100, 20);
                add(last_pokers[i][0]);
                ;
            } else {
                last_pokers[i] = new JLabel[GameInfo.Last_playing_card[i].size()];
                for (int j = 0; j < GameInfo.Last_playing_card[i].size(); ++j) {
                    Poker pp = new Poker(GameInfo.Last_playing_card[i].get(j));
                    ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(pp.getPic_addr())));
                    img = new ImageIcon(img.getImage().getScaledInstance(80, 124, Image.SCALE_AREA_AVERAGING));
                    last_pokers[i][j] = new JLabel();
                    last_pokers[i][j].setIcon(img);
                    last_pokers[i][j].setBounds(poker_back_x[i] + 100 + j * 25, poker_back_y[i], 80, 124);
                    add(last_pokers[i][j]);
                }
            }
        }
    }

    public void setPrompt(String s) {
        if (!GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0]))
            s += "，或者选择不出";
        prompt.setText(s);
    }

    public void updateRound() {
        if (GameInfo.round == 0) {
            roundLabel.setText("这是您的回合");
            setPrompt("请出牌");
        } else {
            roundLabel.setText("轮到 玩家" + GameInfo.players_name[GameInfo.round] + " 的回合");
        }
        if (GameInfo.round == 0) {
            if (!GameInfo.Last_playing_card_type.first.equals(GameInfo.CardTypes[0])) {
                yesButton.setBounds(520, 426, 80, 45);
                add(yesButton);
                add(noButton);
            } else {
                yesButton.setBounds(600, 426, 80, 45);
                this.add(yesButton);
                remove(noButton);
            }
        } else {
            remove(yesButton);
            remove(noButton);
            prompt.setText("");
        }
    }

    private void updateHand() {
        for (PokerLabel pokerLabel : hand) remove(pokerLabel);
        hand = new PokerLabel[Main.me.hand.size()];
        int handleft = 640 - ((Main.me.hand.size()) * 25);
        for (int i = 0; i < Main.me.hand.size(); ++i) {
            hand[i] = new PokerLabel(Main.me.hand.get(i));
            hand[i].setX(handleft + i * 40);
        }
        for (int i = hand.length - 1; i >= 0; --i)
            add(hand[i]);
        Main.GF.revalidate();
        Main.GF.repaint();
    }

}