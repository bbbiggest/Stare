package GDY;
import GDY.client.PLAYER;
import GDY.server.ROOM;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;
import javax.swing.*;

/**
 * @Description 开始面板
 * 
 */

public class StartPanel extends JPanel{
	private JTextArea textArea1;
	private JPanel rootPanel;
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
	PLAYER player;
	public void click(GameFrame f, CardLayout cardlayout, JPanel mainpanel) {

		// 开始游戏
		// 输入用户名，创建/加入房间
		btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame = new smallFrame("请输入用户名");
            	String tems = frame.name.getText();
            	player = new PLAYER(tems);
            	frame.btn1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if(frame.name.getText().isEmpty()) {
							new smallFrame("error","用户名不能为空！");
						}
						else {
							System.out.println("用户名:" + frame.name.getText());

							frame.setVisible(false);
							createroom = new smallFrame("创建房间", "端口号", "人数");
							
							createroom.yes.addActionListener(new ActionListener() {


								@Override
								public void actionPerformed(ActionEvent a) {

									//开始创建房间
									//创建房间ing
									int port;
									try{
										port = Integer.parseInt(createroom.duankou.getText());
									}catch (Exception e){
										new smallFrame("error","非法端口号");
										return;
									}
									if(port <= 0)
									{
										new smallFrame("error","非法端口号");
										return;
									}
									int number = 2;
									if(createroom.peopleNumber == null || createroom.peopleNumber == " ") {
										new smallFrame("error", "请选择人数");
									}
									if(createroom.peopleNumber == "2人")
										number = 2;
									else if(createroom.peopleNumber == "3人")
										number = 3;
									else if(createroom.peopleNumber == "4人")
										number = 4;
									else if(createroom.peopleNumber == "5人")
										number = 5;
									else if(createroom.peopleNumber == "6人")
										number = 6;
									try{
										String IP = new ROOM(port,number).getIPAddress();
										/**********************************
										 * 加入等待加入界面
										 *
										 */
										new smallFrame("waiting","等待玩家加入"+"IP=@"+IP+"端口号："+port+"游戏人数："+createroom.peopleNumber);
										f.setTitle("干瞪眼"+"IP=@"+IP+"端口号："+port+"游戏人数："+createroom.peopleNumber);
										cardlayout.show(mainpanel, "game");
										createroom.setVisible(false);
										/**
										 * 判断是否接入
										 */
//										while (true) {
//											try {
//												String rec = player.read();
//												if (rec.equals("begin")) {
//													f.setTitle("干瞪眼"+"IP=@"+IP+"端口号："+port+"游戏人数："+createroom.peopleNumber);
//													cardlayout.show(mainpanel, "game");
//													createroom.setVisible(false);
//													return;
//												} else {
//													//刷新人员列表
//													rec = rec.replaceAll(" ", "\n");
//													textArea1.setText("");
//													textArea1.append(rec+"\n");
//												}
//											} catch (IOException e) {
//												System.out.println("消息接收时出现错误");
//											}
//										}

									}catch (IOException e) {
										new smallFrame("error","创建失败，请退出重试");;
									}
									//旧版：
//									if(createroom.duankou.getText().isEmpty()) {
//										new smallFrame("error", "请输入端口号");
//									}
//									else if(createroom.peopleNumber == null || createroom.peopleNumber == " ") {
//										new smallFrame("error", "请选择人数");
//									}
//									else {
//										// 进入游戏界面
//										f.changebg();
//										cardlayout.show(mainpanel, "game");
//										createroom.setVisible(false);
//										System.out.println("端口:" + createroom.duankou.getText());
//										System.out.println("人数:" + createroom.peopleNumber);
//									}
								}
							});
						}
					}
            	});
            	
            	frame.btn2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent a) {


						if(frame.name.getText().isEmpty()) {
							new smallFrame("error","请输入用户名");
						}
						else {
							System.out.println("用户名:" + frame.name.getText());
							String name = frame.name.getText();
							player = new PLAYER(name);
							frame.setVisible(false);
							smallFrame joinroom = new smallFrame("加入房间", "端口号", "IP");//加入房间界面
							joinroom.yes.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent a) {
									if(joinroom.duankou.getText().isEmpty()) {
										new smallFrame("error", "请输入端口号");
									}
									else if(joinroom.ip.getText().isEmpty()) {
										new smallFrame("error", "请输入IP");
									}else {
										String ipAddress = joinroom.ip.getText();
										int port = Integer.parseInt(joinroom.duankou.getText());
										try {
											player.connect(ipAddress,port);//连接服务器
										}catch (IOException e){
											new smallFrame("error", "连接房间失败,请重试");
										}
									}



//									if(joinroom.duankou.getText().isEmpty()) {
//										new smallFrame("error", "请输入端口号");
//									}
//									else if(joinroom.ip.getText().isEmpty()) {
//										new smallFrame("error", "请输入IP");
//									}
//									else {
//
//										// 确认端口和ip是否正确
//										// 进入游戏
//
//										f.changebg();
//										cardlayout.show(mainpanel, "game");
//										joinroom.setVisible(false);
//										System.out.println("端口：" + joinroom.duankou.getText());
//										System.out.println("IP：" + joinroom.ip.getText());
//									}
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