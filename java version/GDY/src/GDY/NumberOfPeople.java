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

// 六个按钮（图片形式），按中，就去相应的人数的游戏界面
// 要改background！！

public class NumberOfPeople extends JFrame{
	
	NumberOfPeople() {
		
		this.setTitle("GanDengYan");
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
		
		
		Button people2 = new Button();
		Button people3 = new Button();
		Button people4 = new Button();
		Button people5 = new Button();
		Button people6 = new Button();
		
		
		this.add(people6);
		this.add(people5);
		this.add(people4);
		this.add(people3);
		this.add(people2);
		
		people2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GamePanel(2);
			}
		});
		
		people3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GamePanel(3);
			}
		});
		
		people4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GamePanel(4);
			}
		});
		
		people5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GamePanel(5);
			}
		});
		
		people6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GamePanel(6);
			}
		});
		
		
		this.setVisible(true);
	}
}