/*
 * @Description: 打牌界面
 */

package GDY;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class GamePanel extends JPanel{

	public static String inputPoker; // 输入的扑克牌
	private static int[] text_x = {170, 350, 1030, 2, 2, 1030};
	private static int[] text_y = {520, 50, 480, 480, 190, 190};
	private static int text_width = 200, text_height = 50;
	private static int[] poker_back_x = {600, 1130, 40, 40, 1130};
	private static int[] poker_back_y = {20, 320, 320, 35, 35};
	private static int poker_back_width = 100, poker_back_height = 144;
	private static JLabel[] text;
	private static JLabel[] poker_back;
	public GamePanel(){
		this.setLayout(null);
		this.setOpaque(false);

		// 用户名的位置
		text = new JLabel[6];
		for(int i = 0; i < 6; i++) {
			//读取用户名
			text[i] = new JLabel("玩家" + (i + 1) + ": " + (char)('A' + i), JLabel.CENTER);
			//text[i].setText("用户" + (i + 1));
			text[i].setFont(new Font("楷体",Font.BOLD,30));
			text[i].setForeground(Color.LIGHT_GRAY);
			text[i].setBounds(text_x[i], text_y[i], text_width, text_height);
			this.add(text[i]);
		}

		// 每个人剩余牌数的位置
		ImageIcon pokerback = new ImageIcon(this.getClass().getResource("/images/purple_back.png"));
		pokerback = new ImageIcon(pokerback.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
		poker_back = new JLabel[5];
//
		for(int i = 0; i < 5; i++) {
			poker_back[i] = new JLabel();
			poker_back[i].setIcon(pokerback);
			poker_back[i].setBounds(poker_back_x[i], poker_back_y[i], poker_back_width, poker_back_height);

			poker_back[i].setText("5");
			poker_back[i].setFont(new Font(null, Font.BOLD, 60));
			poker_back[i].setForeground(new Color(245, 133, 99));
			poker_back[i].setHorizontalTextPosition(SwingConstants.CENTER);
			poker_back[i].setVerticalTextPosition(SwingConstants.CENTER);
			this.add(poker_back[i]);
		}


		// 输入框
		JTextField textfield = new JTextField(20);
		textfield.setBounds(860, 580, 200, 40);
		textfield.setFont(new Font(null, Font.PLAIN, 15));
		this.add(textfield);

		// 出牌键
		MyButton yes = new MyButton("出牌");
		yes.setFont(new Font(null, Font.BOLD, 20));
		yes.setBounds(1170, 580, 80, 45);
		this.add(yes);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inputPoker = textfield.getText();
				textfield.setText("");
				System.out.println(inputPoker);
			}

		});

		// 不出键
		MyButton noButton = new MyButton("不出");
		noButton.setFont(new Font(null, Font.BOLD, 20));
		noButton.setBounds(1080, 580, 80, 45);
		this.add(noButton);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("no");
			}
		});

		// 提示文字
		JLabel label = new JLabel("请输入符合规则的扑克牌点数，或者‘不出’",JLabel.CENTER);
		label.setFont(new Font(null, Font.BOLD, 18));
		label.setForeground(Color.LIGHT_GRAY);
		label.setBounds(850, 630, 400, 40);
		this.add(label);

//		// 自己的牌的位置test
//		ArrayList<Poker> hand = new ArrayList<Poker>();
//		hand.add(new Poker("Club","3"));
//		hand.add(new Poker("Club","4"));
//		hand.add(new Poker("Club","5"));
//		JLabel labeltest[] = new JLabel[54];
//    	int itest = 0;
//    	for (var x : hand) {
//    		labeltest[itest] = new JLabel();
//    		ImageIcon img = new ImageIcon(this.getClass().getResource(x.getPic_addr()));
//    		img = new ImageIcon(img.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
//    		labeltest[itest].setIcon(img);
//    		itest++;
//    	}
//    	int lefttest = 640 - ((hand.size() + 1) * 25);
//    	int right = 640 + ((hand.size() + 1) * 25);
//    	for(int j = 0; j < hand.size(); j++) {
//    		labeltest[j].setBounds(lefttest + j*40, 520, 100, 144);
//    	}
//    	for(int j = hand.size() - 1; j >= 0; j--) {
////    		label[j].setBounds(right - j*40, 520, 100, 144);
////    		this.add(label[j]);
//    		this.add(labeltest[j]);
//    	}



		// 上次出牌的位置
		if (gandengyan.Last_playing_card.isEmpty() == false || true) {
			JLabel[] lastpoker = new JLabel[gandengyan.Last_playing_card.size()];
			// test
//			JLabel[] lastpoker = new JLabel[3];
//			gandengyan.Last_playing_card.add(new Poker("Club", "3"));
//			gandengyan.Last_playing_card.add(new Poker("Club", "4"));
//			gandengyan.Last_playing_card.add(new Poker("Club", "5"));
			int i = 0;
			int left = 660 - (gandengyan.Last_playing_card.size() * 40 + 10);
			for (var x : gandengyan.Last_playing_card) {
				lastpoker[i] = new JLabel();
				ImageIcon img = new ImageIcon(this.getClass().getResource(x.getPic_addr()));
				img = new ImageIcon(img.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
				lastpoker[i].setIcon(img);
				i++;
			}
			for (int j = 0; j < gandengyan.Last_playing_card.size(); j++) {
				lastpoker[j].setBounds(left + j * 40, 260, poker_back_width, poker_back_height);
			}
			for (int j = gandengyan.Last_playing_card.size() - 1; j >= 0; j--) {
				this.add(lastpoker[j]);
			}
		}

//		// 牌堆剩余的牌
//		JLabel remainPoker = new JLabel();
//		remainPoker.setIcon(pokerback);
//		remainPoker.setBounds(10, 530, poker_back_width, poker_back_height);
//		this.add(remainPoker);

		this.setVisible(true);
	}

	// 隐藏用不到的label
	public void changeNumber(int n) {
		// int n = gandengyan.Number_of_players;
		if(n == 5) {
			text[1].setVisible(false);
			for(int i = 2; i < 6; i++) {
				text[i].setText("玩家" + (i) + ": " + (char)('A' + i - 1));
			}
			poker_back[0].setVisible(false);
		}
		else {
			for(int i = n; i < 6; i++) {
				text[i].setVisible(false);
			}
			for(int i = n - 1; i < 5; i++) {
				poker_back[i].setVisible(false);
			}
		}

	}

}