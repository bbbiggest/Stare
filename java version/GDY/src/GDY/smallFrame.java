package GDY;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * @Description 用于提示的小界面
 *
 */

public class smallFrame extends JFrame{
	
	// 用于提示的界面
	smallFrame(String title, String text){
		this.setTitle(title);
		JLabel label = new JLabel(text,JLabel.CENTER);
		this.setLayout(null);
		this.setSize(600,250);
		this.setLocationRelativeTo(null);
		label.setFont(new Font(null, Font.BOLD, 15));
		label.setSize(600,200);
		//label.setHorizontalAlignment(label.CENTER);
		
		this.add(label);
		this.setVisible(true);
	}

	// 创建房间后显示的界面
	smallFrame(String title, String s1, String s2, String s3, String s4){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(500, 500);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int)(width - size.getWidth()) / 2, 
				(int)(height - size.getHeight()) / 2, (int)size.getWidth(), (int)size.getHeight());
    	this.setTitle(title);
    	this.setSize(500,350);
    	
    	JPanel panel = new JPanel();
    	panel.setLayout(null);
    	panel.setOpaque(false);
    	
    	JLabel jlb1 = new JLabel(s1, JLabel.CENTER);
    	JLabel jlb2 = new JLabel(s2, JLabel.LEFT);
    	JLabel jlb3 = new JLabel(s3, JLabel.LEFT);
    	JLabel jlb4 = new JLabel(s4, JLabel.LEFT);
    	
    	jlb1.setFont(new Font(null, Font.BOLD, 24));
    	jlb2.setFont(new Font(null, Font.PLAIN, 20));
    	jlb3.setFont(new Font(null, Font.PLAIN, 20));
    	jlb4.setFont(new Font(null, Font.PLAIN, 20));
    	
    	jlb1.setBounds(0, 40, 500, 50);
    	jlb2.setBounds(170, 100, 500, 50);
    	jlb3.setBounds(170, 160, 500, 50);
    	jlb4.setBounds(170, 220, 500, 50);
    	
    	panel.add(jlb1);
    	panel.add(jlb2);
    	panel.add(jlb3);
    	panel.add(jlb4);
    	this.add(panel);
    	this.setVisible(true);
	}
	
	// 创建房间和加入房间的界面
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

    	btn1.setBounds(70, 120, 150, 70);
    	btn2.setBounds(260, 120, 150, 70);

//    	panel.add(jlb1);
//    	panel.add(name);
    	panel.add(btn1);
    	panel.add(btn2);
    	panel.setVisible(true);
    	this.add(panel);
    	this.setVisible(true);
	}
	
	JTextField duankou = new JTextField("2323",9);
	JTextField ip = new JTextField("localhost",9);
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
    	//jlb1.setBounds(80, 50, 100, 40);

    	JLabel jlb2 = new JLabel(s2,JLabel.CENTER);
    	jlb2.setText(s2);
    	jlb2.setFont(new Font(null, Font.BOLD, 20));
    	//jlb2.setBounds(80, 130, 100, 40);


		jlb2.setBounds(80, 50, 100, 40);
		jlb1.setBounds(80, 130, 100, 40);
		ip.setFont(new Font("localhost", Font.PLAIN, 15));
		duankou.setBounds(180, 130, 200, 40);
		duankou.setFont(new Font("2323", Font.PLAIN, 15));
		ip.setBounds(180, 50, 200, 40);
		panel.add(ip);
		String IP = ip.getText();
		System.out.println(IP);


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
	smallFrame(String title, String s1, String s2,String name){//joinroom界面
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
		//jlb1.setBounds(80, 50, 100, 40);

		JLabel jlb2 = new JLabel(s2,JLabel.CENTER);
		jlb2.setText(s2);
		jlb2.setFont(new Font(null, Font.BOLD, 20));
		//jlb2.setBounds(80, 130, 100, 40);


		jlb2.setBounds(80, 50, 100, 40);
		jlb1.setBounds(80, 130, 100, 40);
		ip.setFont(new Font("localhost", Font.PLAIN, 15));
		duankou.setBounds(180, 130, 200, 40);
		duankou.setFont(new Font("2323", Font.PLAIN, 15));
		ip.setBounds(180, 50, 200, 40);
		panel.add(ip);
		String IP = ip.getText();
		System.out.println(IP);
		int port = Integer.parseInt(duankou.getText());



		yes.setBounds(200, 210, 120, 50);
		yes.setFont(new Font(null, Font.BOLD, 20));
		panel.add(jlb2);
		panel.add(jlb1);
		panel.add(yes);
		panel.add(duankou);
		panel.setVisible(true);
		this.add(panel);
		this.setVisible(true);
		yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mainPlayer player = new mainPlayer(name);
					player.connect(IP, port);
				}catch (IOException a)
				{
					System.out.println("请新连接！");
				}
			}
		});
	}

//	public static void main(String[] args)
//	{
//		smallFrame sF = new smallFrame("加入房间", "端口号", "IP");
//	}
}