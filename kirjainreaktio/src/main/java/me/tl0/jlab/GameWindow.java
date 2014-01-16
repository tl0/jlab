package me.tl0.jlab;

import java.awt.Dimension;
import javax.swing.JFrame;

public class GameWindow extends JFrame {

    PlayArea area;
    LetterDestroyer killer;
    
    public GameWindow() {
        super("Testi");
        area = new PlayArea();
        killer = new LetterDestroyer(area);
    }

    public void run() {
        this.setTitle("JavaLabra");
        this.setPreferredSize(new Dimension(512, 512));

        this.setResizable(false);
        this.getContentPane().add(area);
        this.addKeyListener(killer);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
}
