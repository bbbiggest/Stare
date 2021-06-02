/*
 * 输入用户名界面
 */

package GDY;

import GDY.client.PLAYER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class nameFrame extends JDialog {
    String name;

    nameFrame(Frame owner, String title, boolean modal) {
        owner.setVisible(false);
        JTextField inputName = new JTextField(9);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(500, 350);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 2, (int) size.getWidth(), (int) size.getHeight());

        this.setTitle("请输入用户名");
        this.setSize(500, 350);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setOpaque(false);

        JLabel jlb1 = new JLabel();
        jlb1.setText("用户名");
        jlb1.setFont(new Font(null, Font.BOLD, 20));
        jlb1.setBounds(80, 100, 100, 40);


        inputName.setFont(new Font(null, Font.PLAIN, 15));
        inputName.setBounds(180, 100, 200, 40);

        JButton confirm = new JButton("下一步");
        confirm.setBounds(200, 200, 100, 50);
        panel.add(confirm);

        panel.add(jlb1);
        panel.add(inputName);
        panel.setVisible(true);
        this.add(panel);
        this.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputName.getText().isEmpty()) {
                    new smallFrame("error", "用户名不能为空！");
                } else {
                    System.out.println("用户名:" + inputName.getText());
                    String name = inputName.getText();
//                    owner.name = name;
                    owner.setVisible(true);
                    dispose();
                }
            }
        });

    }

//    String getName() {
//        return this.name;
//    }

}

