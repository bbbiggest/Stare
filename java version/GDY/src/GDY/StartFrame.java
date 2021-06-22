package GDY;

import java.awt.*;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.*;

public class StartFrame extends JFrame {
    public String name;
    MyButton begin = new MyButton("Game Begin");
    MyButton rule = new MyButton("Game Rules");
    public boolean isok = false;
    boolean isJoinRoom;
    private static int peopleNumber = -1;
    private static int thePort = -1;
    private static String theIP = "";
    private JFrame scjF; // SelectFrame, CreamFrame and JoinFrame

    StartFrame() {
        name = JOptionPane.showInputDialog(this, "请输入用户名");
        while (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名不能为空", "提示", JOptionPane.WARNING_MESSAGE);
            name = JOptionPane.showInputDialog(this, "请输入用户名");
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


        // rule按钮
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

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scjF = new JFrame();
                scjF.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                scjF.addWindowListener(new WindowAdapter()
                {
                    @Override
                    public void windowClosing(WindowEvent e)
                    {
                        setEnabled(true);
                        e.getWindow().dispose();
                    }
                });
                setEnabled(false);
                setSelectFrame();
            }
        });
    }

    void setSelectFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        Dimension size = new Dimension(500, 350);
        scjF.setBounds((int) (width - size.getWidth()) / 2, (int) (height - size.getHeight()) / 2,
                (int) size.getWidth(), (int) size.getHeight());
        scjF.setSize(500, 350);
        scjF.setTitle("请选择");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);

        MyButton crb = new MyButton("创建房间"); // create room button
        MyButton jrb = new MyButton("加入房间"); // join room button
        crb.setBounds(70, 120, 150, 70);
        jrb.setBounds(260, 120, 150, 70);

        panel.add(crb);
        panel.add(jrb);
        panel.setVisible(true);
        scjF.add(panel);
        scjF.setVisible(true);

        crb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isJoinRoom = false;
                scjF.remove(panel);
                setCreamFrame();
            }
        });
        jrb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isJoinRoom = true;
                scjF.remove(panel);
                setJoinFrame();
            }
        });
    }

    void setCreamFrame() {
        scjF.setTitle("创建房间");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(500, 350);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        scjF.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 2, (int) size.getWidth(), (int) size.getHeight());
        scjF.setSize(500, 350);

        JPanel curPanel = new JPanel();
        curPanel.setLayout(null);
        curPanel.setOpaque(false);

        JLabel portText = new JLabel("端口号", JLabel.CENTER);
        portText.setFont(new Font(null, Font.BOLD, 20));
        portText.setBounds(60, 60, 100, 40);

        JTextField inputPort = new JTextField(9);
        inputPort.setFont(new Font(null, Font.PLAIN, 15));
        inputPort.setText("2323");
        inputPort.setBounds(180, 60, 200, 40);

        JLabel numberText = new JLabel("游戏人数", JLabel.CENTER);
        numberText.setFont(new Font(null, Font.BOLD, 20));
        numberText.setBounds(60, 150, 100, 40);

        final String[] chooseNumber = new String[]{"2人", "3人", "4人", "5人", "6人"};
        final JComboBox<String> box = new JComboBox<String>(chooseNumber);
        box.setBounds(180, 150, 200, 40);
        box.setFocusable(false);
        box.setFont(new Font(null, Font.BOLD, 15));

        MyButton confirmButton = new MyButton("确认");
        confirmButton.setBounds(190, 220, 120, 55);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thePort = Integer.parseInt(inputPort.getText().trim());
                peopleNumber = box.getSelectedItem().toString().charAt(0) - '0';
                System.out.println("port is: " + thePort + "\nnumber of people is: " + peopleNumber);
//                mainPlayer player = new mainPlayer(name);
                setEnabled(true);
                isok = true;
            }
        });

        curPanel.add(portText);
        curPanel.add(inputPort);
        curPanel.add(numberText);
        curPanel.add(box);
        curPanel.add(confirmButton);
        scjF.add(curPanel);
        scjF.setVisible(true);
    }

    void setJoinFrame() {
        scjF.setTitle("加入房间");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(500, 350);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        scjF.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 2, (int) size.getWidth(), (int) size.getHeight());
        scjF.setSize(500, 350);

        JPanel curPanel = new JPanel();
        curPanel.setLayout(null);
        curPanel.setOpaque(false);

        JLabel ipText = new JLabel("IP", JLabel.CENTER);
        ipText.setFont(new Font(null, Font.BOLD, 20));
        ipText.setBounds(60, 60, 100, 40);

        JLabel portText = new JLabel("端口号", JLabel.CENTER);
        portText.setFont(new Font(null, Font.BOLD, 20));
        portText.setBounds(60, 150, 100, 40);

        JTextField inputIP = new JTextField(9);
        inputIP.setFont(new Font(null, Font.PLAIN, 15));
        inputIP.setText("localhost");
        inputIP.setBounds(180, 60, 200, 40);

        JTextField inputPort = new JTextField(9);
        inputPort.setFont(new Font(null, Font.PLAIN, 15));
        inputPort.setText("2323");
        inputPort.setBounds(180, 150, 200, 40);

        MyButton confirmButton = new MyButton("确认");
        confirmButton.setBounds(190, 220, 120, 55);

        curPanel.add(ipText);
        curPanel.add(portText);
        curPanel.add(confirmButton);
        curPanel.add(inputIP);
        curPanel.add(inputPort);
        scjF.add(curPanel);
        scjF.setVisible(true);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thePort = Integer.parseInt(inputPort.getText().trim());
                theIP = inputIP.getText();
                System.out.println("ip is: " + theIP + "\nport is: " + thePort);
                mainPlayer player = new mainPlayer(name);
                setEnabled(true);
                isok = true;
            }
        });
    }


//    void setSelectFrame(String name) {
//
//        Object[] options = new Object[]{"创建房间", "加入房间"};
//        int optionSelected = JOptionPane.showOptionDialog(
//                getFrames()[0],
//                "请点击按钮选择",
//                "请选择游戏方式",
//                JOptionPane.YES_NO_CANCEL_OPTION,
//                JOptionPane.ERROR_MESSAGE,
//                null,
//                options,
//                options[1]
//        );
//        if (options[optionSelected] == "创建房间") {
//
//            System.out.println("创建房间");
//            creamRoomFrame crF = new creamRoomFrame(getFrames()[0], "创建房间", true, name);
//            crF.setVisible(true);
//
//        } else {
//            smallFrame joinRoom = new smallFrame("加入房间", "端口号", "IP", name);
//            joinRoom.setVisible(false);
//            System.out.println("加入房间");
//            joinRoom.setVisible(true);
//
//
//        }
////        if (optionSelected >= 0) {
////            System.out.println("点击的按钮: " + options[optionSelected]);
////        }
//    }

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
