package sample;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

public class smallFrame extends JFrame{
	smallFrame(String title, String text){
		this.setTitle(title);
		JLabel label = new JLabel(text,JLabel.CENTER);
		this.setLayout(null);
		this.setSize(400,250);
		this.setLocationRelativeTo(null);
		label.setFont(new Font(null, Font.BOLD, 26));
		label.setSize(400,200);
		//label.setHorizontalAlignment(label.CENTER);
		
		this.add(label);
		this.setVisible(true);
	}
}