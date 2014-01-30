package me.tl0.jlab.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import me.tl0.jlab.ClickListener;
import me.tl0.jlab.LetterDestroyer;

public class GameWindow extends JFrame {

    PlayArea area;
    LetterDestroyer killer;
    ClickListener klikker;

    public GameWindow() {
        super("Testi");
        area = new PlayArea();
        killer = new LetterDestroyer(area);
        klikker = new ClickListener(this);
    }

    public void run() {
        this.setTitle("JavaLabra");
        this.setPreferredSize(new Dimension(512, 512));

        this.setResizable(false);
        this.getContentPane().add(area);
        this.addKeyListener(killer);
        this.addMouseListener(klikker);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void newGame() {
        if (area.gameEnded()) {
            area.restartGame();
        }
    }

    public PlayArea getPlayArea() {
        return area;
    }
}
