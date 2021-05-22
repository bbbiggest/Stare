/**
 * @Description: 主窗口
 * @Author: chhh-hao
 * @Date: 2021-05
 */

package GDY;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
	private StartPanel spanel;
	private ChooseNumberOfPeople nop;
	
	public GameFrame() {
		this.setTitle("干瞪眼");
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		
		// 主窗体在屏幕中间
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int)(width - size.getWidth()) / 2, 
				(int)(height - size.getHeight()) / 3, (int)size.getWidth(), (int)size.getHeight());
		
		// 初始化，显示开始界面
		this.init();
	}

	
	JLabel label = new JLabel();
	public void init() {
		// 设置窗体背景
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/图片3.png"));
		icon.setImage(icon.getImage().getScaledInstance(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT, Image.SCALE_DEFAULT));
		
		label.setBounds(0, 0, GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
		label.setHorizontalAlignment(0);
		label.setIcon(icon);
		
		// 在窗口第二层加入Label
		this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));
		
		// frame 的顶层容器设为透明，显示背景和控件
		JPanel j = (JPanel)this.getContentPane();
		j.setOpaque(false);

	}
	
	
	public void changebg() {
		// 设置窗体背景
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/background4.jpg"));
		icon.setImage(icon.getImage().getScaledInstance(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT, Image.SCALE_DEFAULT));
		label.setBounds(0, 0, GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
		label.setHorizontalAlignment(0);
		label.setIcon(icon);
		
		// frame 的顶层容器设为透明，显示背景和控件
		JPanel j = (JPanel)this.getContentPane();
		j.setOpaque(false);
	}
}