package me.tl0.jlab.gui;

import java.awt.Graphics;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.X_AXIS;
import javax.swing.JPanel;

/**
 * MenuWindow that is used to launch game.. later..
 *
 * TODO Fixme
 *
 * @author Teemu
 */
public class MenuWindow extends JPanel {

    private static final long serialVersionUID = 1L;

    public MenuWindow() {
        this.setLayout(new BoxLayout(this, X_AXIS));

        this.setVisible(true);
        //PlayAreaGUI p1 = new PlayAreaGUI();
        //this.add(new PlayAreaGUI(), BoxLayout.X_AXIS);
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Kirjainreaktiojuttu", getWidth() / 2 - 130, 120);
    }

}
