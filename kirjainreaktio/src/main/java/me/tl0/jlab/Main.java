package me.tl0.jlab;

import javax.swing.SwingUtilities;
import me.tl0.jlab.gui.GameWindow;

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
