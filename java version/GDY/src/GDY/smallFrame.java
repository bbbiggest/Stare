package GDY;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
 * @Description 用于提示的小界面
 *
 */

public class smallFrame extends JFrame{
	smallFrame(String title, String text){
		this.setTitle(title);
		JLabel label = new JLabel(text,JLabel.CENTER);
		this.setLayout(null);
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		label.setFont(new Font(null, Font.BOLD, 26));
		label.setSize(400,200);
		//label.setHorizontalAlignment(label.CENTER);
		
		this.add(label);
		this.setVisible(true);
	}
	
	MyButton btn1 = new MyButton("创建房间");
	MyButton btn2 = new MyButton("加入房间");
	JTextField name = new JTextField(9);
	smallFrame(String title){
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(500, 350);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int)(width - size.getWidth()) / 2, 
				(int)(height - size.getHeight()) / 2, (int)size.getWidth(), (int)size.getHeight());
		
    	this.setTitle(title);
    	this.setSize(500,350);
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	panel.setOpaque(false);
    	
    	JLabel jlb1 = new JLabel();
    	jlb1.setText("用户名");
    	jlb1.setFont(new Font(null, Font.BOLD, 20));
    	jlb1.setBounds(80, 100, 100, 40);
    	
    	
    	name.setFont(new Font(null, Font.PLAIN, 15));
    	name.setBounds(180, 100, 200, 40);
    	
    	btn1.setBounds(100, 200, 100, 50);
    	btn2.setBounds(290, 200, 100, 50);
    	
    	panel.add(jlb1);
    	panel.add(name);
    	panel.add(btn1);
    	panel.add(btn2);
    	panel.setVisible(true);
    	this.add(panel);
    	this.setVisible(true);
	}
	
	JTextField duankou = new JTextField(9);
	JTextField ip = new JTextField(9);
	MyButton yes = new MyButton("确认");
	String peopleNumber;
	smallFrame(String title, String s1, String s2){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(500, 350);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int)(width - size.getWidth()) / 2, 
				(int)(height - size.getHeight()) / 2, (int)size.getWidth(), (int)size.getHeight());
		
    	this.setTitle(title);
    	this.setSize(500,350);
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	panel.setOpaque(false);
    	
    	JLabel jlb1 = new JLabel(s1,JLabel.CENTER);
    	jlb1.setText(s1);
    	jlb1.setFont(new Font(null, Font.BOLD, 20));
    	jlb1.setBounds(80, 50, 100, 40);
    	
    	JLabel jlb2 = new JLabel(s2,JLabel.CENTER);
    	jlb2.setText(s2);
    	jlb2.setFont(new Font(null, Font.BOLD, 20));
    	jlb2.setBounds(80, 130, 100, 40);
    	
    	duankou.setFont(new Font(null, Font.PLAIN, 15));
    	duankou.setBounds(180, 50, 200, 40);
    	if(s2=="IP") {
    		ip.setFont(new Font(null, Font.PLAIN, 15));
        	ip.setBounds(180, 130, 200, 40);
        	panel.add(ip);
    	}
    	else {
    		String[] choose = new String[] {
    				" ","2人","3人","4人","5人","6人"
    		};
    		final JComboBox<String> box = new JComboBox<String>(choose);
    		box.setBounds(180, 130, 200, 40);
    		
    		box.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					peopleNumber = box.getSelectedItem().toString();
					// System.out.println(peopleNumber);
				}
    		});
    		box.setFocusable(false);
    		box.setFont(new Font(null, Font.BOLD, 15));
    		
    		panel.add(box);
    	}
    	
    	yes.setBounds(200, 210, 120, 50);
    	yes.setFont(new Font(null, Font.BOLD, 20));    	
    	panel.add(jlb2);
    	panel.add(jlb1);
    	panel.add(yes);
    	panel.add(duankou);
    	panel.setVisible(true);
    	this.add(panel);
    	this.setVisible(true);
	}
}