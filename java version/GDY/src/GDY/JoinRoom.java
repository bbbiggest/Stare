package GDY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class JoinRoom {

    public JoinRoom(GameFrame f, CardLayout cardlayout, JPanel mainpanel, String _IP, int _Port) {
        try {
            StartPanel.player.connect(_IP, _Port);//连接服务器
            while (true) {
                try {
                    smallFrame waitframe = new smallFrame("waiting", "等待玩家加入，游戏即将开始");
                    String rec = StartPanel.player.read();
                    if (rec.equals("begin")) {
                        waitframe.setVisible(false);
                        System.out.println("收到begin！有人来了！！！");
                        f.setTitle("干瞪眼  " + StartPanel.player.getName() + " | IP: " + _IP + "端口号: " + _Port);
                        cardlayout.show(mainpanel, "game");
                        return;
                    } else {
                        //刷新人员列表
                        System.out.println("未收到begin！还没有人来");
                        rec = rec.replaceAll(" ", "\n");
                    }
                } catch (IOException e) {
                    System.out.println("消息接收时出现错误");
                }
            }
        } catch (IOException e) {
            new smallFrame("error", "连接房间失败,请重试");
        }
    }
}

/*
smallFrame joinroom = new smallFrame("加入房间", "端口号", "IP");//加入房间界面
        joinroom.yes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                if (joinroom.duankou.getText().isEmpty()) {
                    new smallFrame("error", "请输入端口号");
                } else if (joinroom.ip.getText().isEmpty()) {
                    new smallFrame("error", "请输入IP");
                } else {
                    String _IP = joinroom.ip.getText();
                    int _Port = Integer.parseInt(joinroom.duankou.getText());
                    new JoinRoom(f, cardlayout, mainpanel, _IP, _Port);
                }
            }
        }
 */