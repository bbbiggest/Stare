package GDY;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
 * @Discription 玩家人数选择界面
 * 
 */

public class NumberOfPeople extends JFrame{
	
	private GamePanel gp;
	private MyButton people2, people3, people4, people5, people6;
	private boolean isok = false;
	
	NumberOfPeople() {
		
		this.setTitle("请选择游戏人数");
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		
		// 主窗体在屏幕中间
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int)(width - size.getWidth()) / 2, 
				(int)(height - size.getHeight()) / 3, (int)size.getWidth(), (int)size.getHeight());
		
		this.setLayout(null);
		// 设置窗体背景
		ImageIcon icon = new ImageIcon("src/images/background4.jpg");
		icon.setImage(icon.getImage().getScaledInstance(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT, Image.SCALE_DEFAULT));
		JLabel label = new JLabel();
		label.setBounds(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		label.setHorizontalAlignment(0);
		label.setIcon(icon);
		// 在窗口第二层加入Label
		this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
		// frame 的顶层容器设为透明，显示背景和控件
		JPanel j = (JPanel)this.getContentPane();
		j.setOpaque(false);
		
		ImageIcon board = new ImageIcon("src/images/board2.png");
		board.setImage(board.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
		people2 = new MyButton("2人游戏");
		people3 = new MyButton("3人游戏");
		people4 = new MyButton("4人游戏");
		people5 = new MyButton("5人游戏");
		people6 = new MyButton("6人游戏");
		
		people2.setFont(new Font(null, Font.BOLD, 24));
		people3.setFont(new Font(null, Font.BOLD, 24));
		people4.setFont(new Font(null, Font.BOLD, 24));
		people5.setFont(new Font(null, Font.BOLD, 24));
		people6.setFont(new Font(null, Font.BOLD, 24));
		
		people2.setBounds(400,220,200,100);
		people3.setBounds(700,220,200,100);
		people4.setBounds(250,420,200,100);
		people5.setBounds(550,420,200,100);
		people6.setBounds(850,420,200,100);
		
		this.add(people6);
		this.add(people5);
		this.add(people4);
		this.add(people3);
		this.add(people2);
		
		this.setVisible(true);
		
		clickto();
	}
	
	public void clickto() {
		
		isok = false;
		
		people2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isok = true;
				gp = new GamePanel(2);
				
			}
		});
		
		people3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gp = new GamePanel(3);
				isok = true;
			}
		});
		
		people4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gp = new GamePanel(4);
				isok = true;
			}
		});
		
		people5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gp = new GamePanel(5);
				isok = true;
			}
		});
		
		people6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gp = new GamePanel(6);
				isok = true;
			}
		});
		
		if(isok) {
			this.remove(people2);
			this.remove(people3);
			this.remove(people4);
			this.remove(people5);
			this.remove(people6);
			this.repaint();
		}
		
	}
}