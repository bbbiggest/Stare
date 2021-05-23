/*
 * @Description 打牌界面
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
import javax.swing.WindowConstants;


// 基础界面，头像和牌数等 根据人数定位置
// 改主面板的background
// 放自己的展开的扑克牌，别人的扑克牌（背面，显示数字）
// 自己的扑克牌点击的时候，突出显示
// 还有目前剩余未发的扑克牌数目
//  如果可以实现选头像，就在界面加头像图片，如果不能选，就随机加（？）

public class GamePanel extends JPanel{
	
	public static String inputPoker; // 输入的扑克牌
	private static int[] text_x = {170, 400, 1050, 10, 10, 1050};
	private static int[] text_y = {520, 40, 480, 480, 190, 190};
	private static int text_width = 200, text_height = 50;
	private static int[] poker_back_x = {600, 1130, 40, 40, 1130};
	private static int[] poker_back_y = {20, 320, 320, 35, 35};
	private static int poker_back_width = 100, poker_back_height = 144;
	public GamePanel(){
		this.setLayout(null);
		this.setOpaque(false);
		
		gandengyan.Number_of_players = 6;
		// 用户名的位置
		JLabel[] text = new JLabel[6];
		for(int i = 0; i < gandengyan.Number_of_players; i++) {
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
		JLabel[] poker_back = new JLabel[5];
		
		for(int i = 0; i < gandengyan.Number_of_players - 1; i++) {
			poker_back[i] = new JLabel();
			poker_back[i].setIcon(pokerback);
			poker_back[i].setBounds(poker_back_x[i], poker_back_y[i], poker_back_width, poker_back_height);
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
//		JLabel label[] = new JLabel[54];
//    	int i = 0;
//    	for (var x : hand) {
//    		label[i] = new JLabel();
//    		ImageIcon img = new ImageIcon(this.getClass().getResource(x.getPic_addr()));
//    		img = new ImageIcon(img.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
//    		label[i].setIcon(img);
//    		i++;
//    	}
//    	int left = 640 - ((hand.size() + 1) * 25);
//    	int right = 640 + ((hand.size() + 1) * 25);
//    	for(int j = 0; j < hand.size(); j++) {
//    		label[j].setBounds(left + j*40, 520, 100, 144);
//    	}
//    	for(int j = hand.size() - 1; j >= 0; j--) {
////    		label[j].setBounds(right - j*40, 520, 100, 144);
////    		this.add(label[j]);
//    		this.add(label[j]);
//    	}
		
    	
		
		// 上次出牌的位置
		if (gandengyan.Last_playing_card.isEmpty() == false || true) {
//		JLabel[] lastpoker = new JLabel[gandengyan.Last_playing_card.size()];
			JLabel[] lastpoker = new JLabel[3];
			gandengyan.Last_playing_card.add(new Poker("Club", "3"));
			gandengyan.Last_playing_card.add(new Poker("Club", "4"));
			gandengyan.Last_playing_card.add(new Poker("Club", "5"));
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
				lastpoker[j].setBounds(left + j * 40, 260, 100, 144);
			}
			for (int j = gandengyan.Last_playing_card.size() - 1; j >= 0; j--) {
				this.add(lastpoker[j]);
			}
		}
		
		this.setVisible(true);
	}


}