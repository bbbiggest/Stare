package GDY;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * @Discription 开始面板
 * 
 */
// 想要在登陆注册的时候让用户选一个头像，但是不会

public class StartPanel extends JPanel{
//	private boolean logok =  false;
//	private boolean beginclick = false;
	
	StartPanel(){
//		logok = false;
//		beginclick = false;
		
		this.setLayout(null);
//		MyButton btn1 = new MyButton("Log in or Register");
//		this.add(btn1);
//		btn1.setBounds(250, 500, 200, 50);
		
		MyButton btn2 = new MyButton("Game Begin");
		this.add(btn2);
		btn2.setBounds(350, 500, 200, 65);
		
		MyButton btn3 = new MyButton("Game Rules");
		this.add(btn3);
		btn3.setBounds(750, 500, 200, 65);
		this.setVisible(true);
		

//		btn1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	JFrame LogInFrame = new JFrame("Log in / Register");
//            	LogInFrame.setSize(700, 500);
//            	LogInFrame.setLocationRelativeTo(null);
//            	JPanel accpanel = new JPanel();
//            	accpanel.setLayout(null);
//            	
//            	JTextField account = new JTextField(8);
//            	account.setFont(new Font(null, Font.PLAIN, 15));
//            	account.setBounds(300, 100, 200, 40);
//            	
//            	JPasswordField password = new JPasswordField(8);
//            	password.setFont(new Font(null, Font.PLAIN, 15));
//            	password.setBounds(300, 200, 200, 40);
//            	
//            	JLabel jlb1 = new JLabel();
//            	jlb1.setText("账号");
//            	jlb1.setFont(new Font(null, Font.BOLD, 20));
//            	jlb1.setBounds(230, 100, 100, 40);
//            	
//            	JLabel jlb2 = new JLabel();
//            	jlb2.setText("密码");
//            	jlb2.setFont(new Font(null, Font.BOLD, 20));
//            	jlb2.setBounds(230, 200, 100, 40);
//            	
//            	MyButton LogBtn = new MyButton("确认");
//            	LogBtn.setFont(new Font(null, Font.BOLD, 18));
//            	LogBtn.setBounds(300, 300, 100, 50);
//            	
//            	accpanel.add(account);
//            	accpanel.add(password);
//            	accpanel.add(jlb1);
//            	accpanel.add(jlb2);
//            	accpanel.add(LogBtn);
//            	accpanel.setVisible(true);
//            	LogInFrame.add(accpanel);
//            	LogInFrame.setVisible(true);
//            	
//            	// 按下确认键后，判断账号密码是否正确
//            	LogBtn.addActionListener(new ActionListener() {
//					@Override
//					public void actionPerformed(ActionEvent e) {
//						
//						String acc = account.getText();
//						String psw = new String(password.getPassword());
//						
//						System.out.println("账号：" + acc);
//						System.out.println("密码：" + psw);
//						
//						// 如果账号密码正确
//						if(acc.equals(GameRule.Test_Account) && psw.equals(GameRule.Test_Password)) {
//							logok = true;
//							LogInFrame.setVisible(false); // LogInFrame消失
//						}
//						
//						// 如果账号或密码错
//						else {
//							logok = false;
//							new smallFrame("error", "账号或密码错误");
//						}
//					}
//            	});
//            }
//        });
		
		
		
		// 开始游戏
		// 输入用户名，创建/加入房间
		btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//            	// 登陆成功，新界面
//            	if(logok) {
//            		beginclick = true;
////            		System.out.println(beginclick);
//            		NumberOfPeople numframe = new NumberOfPeople();
//            	}
//            	// 没登录成功
//            	else {
//            		new smallFrame("error", "请先登录");
//            	}
            	
            	smallFrame frame = new smallFrame("请输入用户名");
            	String tems = frame.name.getText();
            	frame.btn1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(frame.name.getText().isEmpty()) {
							new smallFrame("error","请输入用户名");
						}
						else {
							frame.setVisible(false);
							smallFrame createroom = new smallFrame("创建房间", "端口号", "人数");
							
							createroom.yes.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if(createroom.duankou.getText().isEmpty()) {
										new smallFrame("error", "请输入端口号");
									}
									else {
										// 进入游戏界面
										
										System.out.println("name:" + createroom.name.getText());
										System.out.println("number" + createroom.peopleNumber);
									}
								}
								
							});
						}
					}
            	});
            	
            	frame.btn2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(frame.name.getText().isEmpty()) {
							new smallFrame("error","请输入用户名");
						}
						else {
							frame.setVisible(false);
							smallFrame joinroom = new smallFrame("加入房间", "端口号", "IP");
							joinroom.yes.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if(joinroom.duankou.getText().isEmpty()) {
										new smallFrame("error", "请输入端口号");
									}
									else if(joinroom.ip.getText().isEmpty()) {
										new smallFrame("error", "请输入IP");
									}
									else {
										// 确认端口和ip是否正确
										// 进入游戏
										System.out.println("端口：" + joinroom.duankou.getText());
										System.out.println("IP：" + joinroom.ip.getText());
									}
								}
							});
						}
						
					}
            		
            	});
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
            	ta.setText(GameRule.GAME_RULES);
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