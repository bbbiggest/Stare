package GDY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class creamRoomFrame extends JDialog {
    private static int peopleNumber = -1;
    private static int thePort = -1;

    creamRoomFrame () {}

    creamRoomFrame(Frame owner, String title, boolean modal) {
//        owner.setVisible(false);
        owner.setEnabled(!modal);
//        this.setTitle("创建房间");
        this.setTitle(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension size = new Dimension(500, 350);
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        this.setBounds((int) (width - size.getWidth()) / 2,
                (int) (height - size.getHeight()) / 2, (int) size.getWidth(), (int) size.getHeight());
        this.setSize(500, 350);

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
//                owner.setVisible(true);
                dispose();
            }
        });

        curPanel.add(portText);
        curPanel.add(inputPort);
        curPanel.add(numberText);
        curPanel.add(box);
        curPanel.add(confirmButton);
        this.add(curPanel);
        this.setVisible(true);
    }

    public int getPeopleNumber() { return peopleNumber; }
    public int getThePort() { return thePort; }

    public static void main(String[] args) {
        creamRoomFrame crf = new creamRoomFrame();
    }
}
