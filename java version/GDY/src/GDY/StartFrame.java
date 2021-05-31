package GDY;

import GDY.client.PLAYER;
import GDY.server.ROOM;

import java.awt.*;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Objects;
import javax.swing.*;

public class StartFrame extends JFrame {
    MyButton begin = new MyButton("Game Begin");
    MyButton rule = new MyButton("Game Rules");

    StartFrame(String name) {
        this.setTitle("干瞪眼 " + name);
        this.setDefaultCloseOperation(3);
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

        begin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                smallFrame sF = new smallFrame("请选择");
                sF.setVisible(true);
                sF.btn1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ;
                    }
                });
                sF.btn2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ;
                    }
                });
            }
        });
    }


//    smallFrame createroom;
//    smallFrame frame;
//    public void click(GameFrame f, CardLayout cardlayout, JPanel mainpanel){
//        begin.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame = new smallFrame("请选择游戏方式");
//                frame.btn1.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        smallFrame smfm = new smallFrame("我点了创建房间","哈哈哈哈");
//                    }
//                });
//
//                frame.btn2.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        smallFrame smfm = new smallFrame("我点了加入房间","哈哈哈哈");
//                    }
//                });
//
//
//            }
//        });
//    }

//    void creatroom(String duankou)
//    {
//
//    }

    public static void main(String[] args) {
        new nameFrame();
    }

}
