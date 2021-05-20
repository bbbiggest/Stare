package sample;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
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
 * @Discription 开始面板
 * 
 */
// 想要在登陆注册的时候让用户选一个头像，但是不会

public class StartPanel extends JPanel{
	private boolean logok =  false;
	private boolean beginclick = false;
	
	StartPanel(){
		logok = false;
		beginclick = false;
		
		this.setLayout(null);
		MyButton btn1 = new MyButton("Log in or Register");
		this.add(btn1);
		btn1.setBounds(250, 500, 200, 50);
		
		MyButton btn2 = new MyButton("Game Begin");
		this.add(btn2);
		btn2.setBounds(550, 500, 200, 50);
		
		MyButton btn3 = new MyButton("Game Rules");
		this.add(btn3);
		btn3.setBounds(850, 500, 200, 50);
		this.setVisible(true);
		
		// 登陆注册（一个文本框，一个密码框以及相关文字说明，和确认按钮）
		// 没有和账号密码的匹配部分
		// 没有返回“密码错误”或者“登陆成功”的部分！！！！！！！！！！
		// 想优化成半透明的窗体，不太会
		btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame LogInFrame = new JFrame("Log in / Register");
            	LogInFrame.setSize(700, 500);
            	LogInFrame.setLocationRelativeTo(null);
            	JPanel accpanel = new JPanel();
            	accpanel.setLayout(null);
            	
            	JTextField account = new JTextField(8);
            	account.setFont(new Font(null, Font.PLAIN, 15));
            	account.setBounds(300, 100, 200, 40);
            	
            	JPasswordField password = new JPasswordField(8);
            	password.setFont(new Font(null, Font.PLAIN, 15));
            	password.setBounds(300, 200, 200, 40);
            	
            	JLabel jlb1 = new JLabel();
            	jlb1.setText("账号");
            	jlb1.setFont(new Font(null, Font.BOLD, 20));
            	jlb1.setBounds(230, 100, 100, 40);
            	
            	JLabel jlb2 = new JLabel();
            	jlb2.setText("密码");
            	jlb2.setFont(new Font(null, Font.BOLD, 20));
            	jlb2.setBounds(230, 200, 100, 40);
            	
            	MyButton LogBtn = new MyButton("确认");
            	LogBtn.setFont(new Font(null, Font.BOLD, 18));
            	LogBtn.setBounds(300, 300, 100, 50);
            	
            	accpanel.add(account);
            	accpanel.add(password);
            	accpanel.add(jlb1);
            	accpanel.add(jlb2);
            	accpanel.add(LogBtn);
            	accpanel.setVisible(true);
            	LogInFrame.add(accpanel);
            	LogInFrame.setVisible(true);
            	
            	// 按下确认键后，判断账号密码是否正确
            	LogBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						
						String acc = account.getText();
						String psw = new String(password.getPassword());
						
						System.out.println("账号：" + acc);
						System.out.println("密码：" + psw);
						
						// 如果账号密码正确
						if(acc.equals(Constant.Test_Account) && psw.equals(Constant.Test_Password)) {
							logok = true;
							LogInFrame.setVisible(false); // LogInFrame消失
						}
						
						// 如果账号或密码错
						else {
							logok = false;
							new smallFrame("error", "账号或密码错误");
						}
					}
            	});
            }
        });
		
		// 开始游戏
		// 跳回主窗体，换新的界面
		btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// 登陆成功，新界面
            	if(logok) {
            		beginclick = true;
//            		System.out.println(beginclick);
            		NumberOfPeople numframe = new NumberOfPeople();
            		
            	}
            	
            	// 没登录成功
            	else {
            		new smallFrame("error", "请先登录");
            	}
            }
        });
		
		
		// 游戏规则---bingo
		btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	JFrame ruleframe = new JFrame("Game Rules");
            	ruleframe.setLayout(null);
            	ruleframe.setSize(700, 500);
            	ruleframe.setLocationRelativeTo(null);
            	
            	JTextArea ta = new JTextArea();
            	ta.setLineWrap(true);
            	ta.setFont(new Font(null, Font.PLAIN, 15));
            	ta.setText(Constant.GAME_RULES);
            	ta.setEditable(false);
            	JScrollPane scroll = new JScrollPane(
            			ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            	
            	ruleframe.setContentPane(scroll);
            	
            	JScrollBar bar = scroll.getVerticalScrollBar();
            	// 确保滚动条在最上方
            	javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        bar.setValue(0);
                    }
                });
            	ruleframe.setVisible(true);
            }
        });
	}
}