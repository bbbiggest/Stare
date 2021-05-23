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
/**
 * 
 * @Description 打牌界面
 */

// 基础界面，头像和牌数等 根据人数定位置
// 改主面板的background
// 放自己的展开的扑克牌，别人的扑克牌（背面，显示数字）
// 自己的扑克牌点击的时候，突出显示
// 还有目前剩余未发的扑克牌数目

public class GamePanel extends JPanel{
	
	public GamePanel(){
		this.setLayout(null);
		this.setOpaque(false);
		
		
		// 用户名的位置
		JLabel[] text = new JLabel[6];
		for(int i = 0; i < 6; i++) {
			//读取用户名
			text[i] = new JLabel("用户" + (i + 1), JLabel.CENTER);
			//text[i].setText("用户" + (i + 1));
			text[i].setFont(new Font("楷体",Font.BOLD,30));
			text[i].setForeground(Color.LIGHT_GRAY);
		}
		System.out.println(text[0].getText());
		text[0].setBounds(250, 580, 100, 50);
		text[1].setBounds(450, 40, 100, 50);
		text[2].setBounds(1130, 480, 100, 50);
		text[3].setBounds(40, 480, 100, 50);
		text[4].setBounds(40, 190, 100, 50);
		text[5].setBounds(1130, 190, 100, 50);
		this.add(text[0]);
		this.add(text[1]);
		this.add(text[2]);
		this.add(text[3]);
		this.add(text[4]);
		this.add(text[5]);
		
		
		// 每个人剩余牌数的位置
		ImageIcon poker = new ImageIcon(this.getClass().getResource("/images/Club-3.png"));
		JLabel[] poker_back = new JLabel[5];
		
		for(int i = 0; i < 5; i++) {
			poker_back[i] = new JLabel();
			poker_back[i].setIcon(poker);
		}
		poker_back[0].setBounds(600, 20, 100, 144);
		poker_back[1].setBounds(1130, 35, 100, 144);
		poker_back[2].setBounds(40, 35, 100, 144);
		poker_back[3].setBounds(40, 320, 100, 144);
		poker_back[4].setBounds(1130, 320, 100, 144);
		this.add(poker_back[0]);
		this.add(poker_back[1]);
		this.add(poker_back[2]);
		this.add(poker_back[3]);
		this.add(poker_back[4]);
		
		// 自己牌的位置
		JLabel[] mypoker = new JLabel[5];
		for(int i = 0; i < 5; i++) {
			mypoker[i] = new JLabel();
			mypoker[i].setIcon(poker);
		}
		mypoker[0].setBounds(350, 520, 100, 144);
		
		this.add(mypoker[0]);
		this.add(mypoker[1]);
		this.add(mypoker[2]);
		this.add(mypoker[3]);
		this.add(mypoker[4]);
		
		// 上次出牌的位置
		JLabel[] lastpoker = new JLabel[5];
		for(int i = 0; i < 5; i++) {
			lastpoker[i] = new JLabel();
			lastpoker[i].setIcon(poker);
		}
		lastpoker[0].setBounds(550, 280, 100, 144);
		this.add(lastpoker[0]);
		
		// test
		ArrayList<Poker> hand = new ArrayList<Poker>();
		hand.add(new Poker("Club","3"));
		hand.add(new Poker("Club","4"));
		hand.add(new Poker("Club","5"));
		JLabel label[] = new JLabel[54];
    	int i = 0;
    	for (var x : hand) {
    		label[i] = new JLabel();
    		ImageIcon img = new ImageIcon(this.getClass().getResource(x.getPic_addr()));
    		img = new ImageIcon(img.getImage().getScaledInstance(100, 144, Image.SCALE_AREA_AVERAGING));
    		label[i].setIcon(img);
    		i++;
    	}
    	int left = 640 - ((hand.size() + 1) * 25);
    	int right = 640 + ((hand.size() + 1) * 25);
    	for(int j = 0; j < hand.size(); j++) {
    		label[j].setBounds(left + j*40, 520, 100, 144);
    	}
    	for(int j = hand.size() - 1; j >= 0; j--) {
//    		label[j].setBounds(right - j*40, 520, 100, 144);
//    		this.add(label[j]);
    		this.add(label[j]);
    	}
		
    	
    	
		this.setVisible(true);
	}
	
	
	// 根据人数改变布局
	public void changenumber(int n) {
		
	}

}