package me.tl0.jlab;

import me.tl0.jlab.gui.GameWindow;
import javax.swing.SwingUtilities;

/**
 * Just launches GameWindow
 *
 * @author Teemu
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameWindow().run();
            }
        });
    }
}
