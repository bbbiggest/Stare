package GDY;
import java.awt.CardLayout;
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
 * @Description 开始面板
 * 
 */

public class StartPanel extends JPanel{
//	private boolean logok =  false;
//	private boolean beginclick = false;
	MyButton btn2 = new MyButton("Game Begin");
	MyButton btn3 = new MyButton("Game Rules");
	
	
	StartPanel(){
//		logok = false;
//		beginclick = false;
		
		this.setLayout(null);
		this.setOpaque(false);
//		MyButton btn1 = new MyButton("Log in or Register");
//		this.add(btn1);
//		btn1.setBounds(250, 500, 200, 50);

		this.add(btn2);
		btn2.setBounds(350, 500, 200, 65);
		
		this.add(btn3);
		btn3.setBounds(750, 500, 200, 65);
		this.setVisible(true);
			
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
	
	// 点击开始游戏按钮
	smallFrame createroom;
	smallFrame frame;
	public void click(GameFrame f, CardLayout cardlayout, JPanel mainpanel) {
		// 开始游戏
		// 输入用户名，创建/加入房间
		btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame = new smallFrame("请输入用户名");
            	String tems = frame.name.getText();
            	frame.btn1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(frame.name.getText().isEmpty()) {
							new smallFrame("error","请输入用户名");
						}
						else {
							System.out.println("用户名:" + frame.name.getText());
							frame.setVisible(false);
							createroom = new smallFrame("创建房间", "端口号", "人数");
							
							createroom.yes.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									if(createroom.duankou.getText().isEmpty()) {
										new smallFrame("error", "请输入端口号");
									}
									else if(createroom.peopleNumber == null || createroom.peopleNumber == " ") {
										new smallFrame("error", "请选择人数");
									}
									else {
										// 进入游戏界面
										f.changebg();
										cardlayout.show(mainpanel, "game");
										createroom.setVisible(false);
										System.out.println("端口:" + createroom.duankou.getText());
										System.out.println("人数:" + createroom.peopleNumber);
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
							System.out.println("用户名:" + frame.name.getText());
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
										createroom.setVisible(false);
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
	}
	
	
	// 读取选择的人数
	public int getNumber() {
		char tem = createroom.peopleNumber.charAt(0);
		return (int)(tem - '0');
	}
	
	// 读取用户名
	public String getName() {
		return frame.name.getText();
	}
}