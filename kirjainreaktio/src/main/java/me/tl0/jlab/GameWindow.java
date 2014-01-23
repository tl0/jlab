package me.tl0.jlab;

import java.awt.Dimension;
import javax.swing.JFrame;

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
            this.getContentPane().remove(area);
            System.out.println("!!!!");
            area = new PlayArea();
            this.getContentPane().add(area);
            area.tick();
        }
    }

    public PlayArea getPlayArea() {
        return area;
    }
}
