package GDY;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame{
	GameInfo Info;
	GamePanel curGame;
	JLabel waitLabel, winLabel;
    JLabel label = new JLabel();
	public GameFrame() {
		Info = new GameInfo();
		this.setTitle("干瞪眼 " + Main.me.getID() + "号玩家 " + Main.me.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// 主窗体在屏幕中间
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension size = new Dimension(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
		int width = toolkit.getScreenSize().width;
		int height = toolkit.getScreenSize().height;
		this.setBounds((int)(width - size.getWidth()) / 2,
				(int)(height - size.getHeight()) / 3, (int)size.getWidth(), (int)size.getHeight());

		// 在窗口第二层加入Label
		this.getLayeredPane().add(label, Integer.valueOf(Integer.MIN_VALUE));

		// 设置窗体背景
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/background4.jpg"));
		icon.setImage(icon.getImage().getScaledInstance(GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT, Image.SCALE_DEFAULT));
		label.setBounds(0, 0, GameRule.FRAME_WIDTH, GameRule.FRAME_HEIGHT);
		label.setHorizontalAlignment(0);
		label.setIcon(icon);

		// frame 的顶层容器设为透明，显示背景和控件
		JPanel j = (JPanel)this.getContentPane();
		j.setOpaque(false);
		setVisible(true);
	}

	void waitting() {
		waitLabel = new JLabel("", SwingConstants.CENTER);
		if (Main.isJoinRoom)
			waitLabel.setText("等待人到齐后游戏开始");
		else
			waitLabel.setText("<html>等待别人加入<br>IP: " + Main.gdy.IPAddress + "<br>端口号: "
					+ Main.start.getThePort() + "</html>");
		waitLabel.setFont(new Font("楷体", Font.BOLD, 30));
		waitLabel.setForeground(Color.YELLOW);
		add(waitLabel);
		setEnabled(false);
	}

	void startGame() {
		remove(waitLabel);
		setEnabled(true);
		curGame = new GamePanel(Info);
		add(curGame);
		revalidate();
		repaint();
	}

	void update() {
		curGame.updateInfo(Info);
		revalidate();
		repaint();
	}

	void win() {
		winLabel = new JLabel("", SwingConstants.CENTER);
		winLabel.setText("<html>游戏结束<br>玩家" + Info.players_name[Info.Winner] + " 胜利！！！</html>");
		winLabel.setFont(new Font("楷体", Font.BOLD, 30));
		winLabel.setForeground(Color.YELLOW);
		add(winLabel);
		revalidate();
		repaint();
	}
}