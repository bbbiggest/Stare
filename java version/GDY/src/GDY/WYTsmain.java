package GDY;
import GDY.server.ROOM;
import java.awt.CardLayout;
import java.io.IOException;
import java.util.Objects;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;
import java.util.Objects;
import javax.swing.*;

public class WYTsmain {
    WYTsmain(){
        CardLayout cardlayout = new CardLayout();
        JPanel mainpanel = new JPanel(cardlayout);
        GamePanel gamepanel = new GamePanel();
        StartPanel startpanel = new StartPanel();

        mainpanel.add(startpanel, "start");
        mainpanel.add(gamepanel, "game");
        mainpanel.setOpaque(false);

        GameFrame gfm = new GameFrame();

        gfm.add(mainpanel);
//        cardlayout.show(mainpanel,"game");
        cardlayout.show(mainpanel, "start");
        startpanel.click(gfm, cardlayout, mainpanel);
        gfm.setVisible(true);

    }

    public static void main(String[] args) {
        new WYTsmain();
        new WYTsmain();



    }
/** ***********************
 * 创建房间
    **********************************/



}
