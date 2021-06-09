package GDY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectFrame extends JDialog {
    MyButton crb = new MyButton("创建房间"); // create room button
    MyButton jrb = new MyButton("加入房间"); // join room button
    selectFrame(Frame owner, String title, boolean modal) {
        owner.setEnabled(modal);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(500, 350);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int)(width - size.getWidth()) / 2,
                (int)(height - size.getHeight()) / 2, (int)size.getWidth(), (int)size.getHeight());

        this.setTitle("请选择");
        this.setSize(500,350);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);

        crb.setBounds(70, 120, 150, 70);
        jrb.setBounds(260, 120, 150, 70);

        panel.add(crb);
        panel.add(jrb);
        panel.setVisible(true);
        this.add(panel);
        this.setVisible(true);

        crb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);
//                setVisible(false);
//                creamRoomFrame crF = new creamRoomFrame(owner, "创建房间", true);
//                crF.setVisible(true);
//                setEnabled(true);
            }
        });
//        jrb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                joinRoom.setVisible(true);
//            }
//        });
    }
}
