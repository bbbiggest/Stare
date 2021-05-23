package GDY;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class tlmain
{
	public static GamePanel gamepanel;
    public static void main(String[] args)
    {

    	CardLayout cardlayout = new CardLayout();
        JPanel mainpanel = new JPanel(cardlayout);
        gamepanel = new GamePanel();
        StartPanel startpanel = new StartPanel();
        
        mainpanel.add(startpanel,"start");
        mainpanel.add(gamepanel,"game");
        mainpanel.setOpaque(false);
        
        GameFrame gfm = new GameFrame();
        
        gfm.add(mainpanel);
        cardlayout.show(mainpanel, "game");
        startpanel.click(gfm, cardlayout, mainpanel);
        gfm.setVisible(true);
        return;
    }
}