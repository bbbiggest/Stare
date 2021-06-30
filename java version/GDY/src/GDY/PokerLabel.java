package GDY;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class PokerLabel extends JLabel {
    private static final int width = 100;
    private static final int height = 144;
    private int x, y = 520;
    private boolean isUp = false;
    private Poker pp;

    PokerLabel() {
    }

    PokerLabel(Poker pp) {
        this.pp = new Poker(pp);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource(pp.getPic_addr())));
        img = new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING));
        setIcon(img);
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (isUp) {
                    y += 20;
                    for (int i = 0; i < Main.me.wantPut.size(); ++i) {
                        if (Main.me.wantPut.get(i).equals(pp)) {
                            Main.me.wantPut.remove(i);
                            break;
                        }
                    }
                    System.out.println(pp + " DOWN");
                } else {
                    y -= 20;
                    Main.me.wantPut.add(pp);
                    System.out.println(pp + " UP");
                }
                isUp = !isUp;
                setBounds(x, y, width, height);
                Main.GF.revalidate();
                Main.GF.repaint();
            }
        });
    }

    public void setX(int x) {
        this.x = x;
        setBounds(x, y, width, height);
    }

    public void allDown() {
        if (isUp) {
            y += 20;
            for (int i = 0; i < Main.me.wantPut.size(); ++i) {
                if (Main.me.wantPut.get(i).equals(pp)) {
                    Main.me.wantPut.remove(i);
                    break;
                }
            }
            System.out.println(pp.toString() + " DOWN");
            isUp = !isUp;
            setBounds(x, y, width, height);
            Main.GF.revalidate();
            Main.GF.repaint();
        }
    }

}
