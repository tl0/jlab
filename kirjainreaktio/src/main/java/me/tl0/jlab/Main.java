package me.tl0.jlab;

import me.tl0.jlab.gui.GameWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().run();
            }
        });
    }
}
