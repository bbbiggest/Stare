package GDY;


import java.awt.*;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartFrame extends JFrame {
    public String name;
    MyButton begin = new MyButton("Game Begin");
    MyButton rule = new MyButton("Game Rules");
    boolean isJoinRoom;

    StartFrame() {
        name = JOptionPane.showInputDialog(this,"请输入用户名");
        while (name.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"用户名不能为空","提示",JOptionPane.WARNING_MESSAGE);
            name = JOptionPane.showInputDialog(this,"请输入用户名");
        }

//        nameFrame nF = new nameFrame(this, "请输入用户名", true);
        this.setTitle("干瞪眼 " + name);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 3, (int) size.getWidth(), (int) size.getHeight());

        JLabel startLabel = new JLabel();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/图片3.png"));
        icon.setImage(icon.getImage().getScaledInstance(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT, Image.SCALE_DEFAULT));

        startLabel.setBounds(0, 0, GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
        startLabel.setHorizontalAlignment(0);
        startLabel.setIcon(icon);

        // 在窗口第二层加入Label
        this.getLayeredPane().add(startLabel, Integer.valueOf(Integer.MIN_VALUE));

        // frame 的顶层容器设为透明，显示背景和控件
        JPanel j = (JPanel) this.getContentPane();
        j.setOpaque(false);

        CardLayout cardlayout = new CardLayout();
        JPanel startPanel = new JPanel(cardlayout);
        startPanel.setLayout(null);
        startPanel.setOpaque(false);
        startPanel.add(begin);    //  Game Gegin
        begin.setBounds(350, 500, 200, 65);

        startPanel.add(rule);   //   Game Rules
        rule.setBounds(750, 500, 200, 65);
        startPanel.setVisible(true);
        this.add(startPanel);
        this.setVisible(true);


        //rule按钮
        rule.addActionListener(new ActionListener() {
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

//        selectFrame sF = new selectFrame();
//        sF.setVisible(false);

//        sF.crb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                sF.setEnabled(false);
//                sF.setVisible(false);
//                creamRoomFrame crF = new creamRoomFrame(sF, "创建房间", true);
////                crF.setVisible(true);
////                setEnabled(true);
//            }
//        });
//        sF.jrb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                joinRoom.setVisible(true);
//            }
//        });

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                setEnabled(false);
//                selectFrame sF = new selectFrame(getFrames()[0], "请选择", true);
//                sF.setVisible(true);
//                JDialog sF = new JDialog(getFrames()[0], "请选择", true);
                setSelectFrame(name);
                //startPanel.setVisible(false);
        }
        });
    }
    void setSelectFrame(String name)
    {

        Object[] options = new Object[]{"创建房间","加入房间"};
        int optionSelected = JOptionPane.showOptionDialog(
                getFrames()[0],
                "请点击按钮选择",
                "请选择游戏方式",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[1]
        );
        if(options[optionSelected] == "创建房间")
        {

            System.out.println("创建房间");
            creamRoomFrame crF = new creamRoomFrame(getFrames()[0], "创建房间", true,name);
            crF.setVisible(true);

        }else{
            smallFrame joinRoom = new smallFrame("加入房间", "端口号", "IP",name);
            joinRoom.setVisible(false);
            System.out.println("加入房间");
            joinRoom.setVisible(true);


        }
//        if (optionSelected >= 0) {
//            System.out.println("点击的按钮: " + options[optionSelected]);
//        }

//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        int width = toolkit.getScreenSize().width;
//        int height = toolkit.getScreenSize().height;
//        Dimension size = new Dimension(500, 350);
//        sF.setBounds((int)(width - size.getWidth()) / 2, (int)(height - size.getHeight()) / 2,
//                (int)size.getWidth(), (int)size.getHeight());
//        sF.setSize(500,350);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//        panel.setOpaque(false);
//
//        crb.setBounds(70, 120, 150, 70);
//        jrb.setBounds(260, 120, 150, 70);
//
//        panel.add(crb);
//        panel.add(jrb);
//        panel.setVisible(true);
//        sF.setContentPane(panel);
//        sF.setVisible(true);
//        crb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("创建房间");
//                isJoinRoom = false;
//                sF.setEnabled(false);
//                sF.setVisible(false);
//                creamRoomFrame crF = new creamRoomFrame(getFrames()[0], "创建房间", true);
//                crF.setVisible(true);
//            }
//        });
//        jrb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("加入房间");
//                isJoinRoom = true;
////                joinRoom.setVisible(true);
//            }
//        });
    }

//    void CreamRoom(creamRoomFrame frame,String name,int port,int count){
//        try {
//            Room mainroom = new Room(port,count);
//            String IP = mainroom.getIPAddress();
//            mainroom.waitStart();
//            mainPlayer player = new mainPlayer(name);
//            player.connect(IP,port);
//
//        }catch(IOException e)
//        {
//            System.out.println("error");
//        }
//    }

    public static void main(String[] args) {
        new StartFrame();
    }

}
