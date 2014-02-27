package me.tl0.jlab.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import me.tl0.jlab.logic.PlayArea;

/**
 * GameWindow is the main component that is created by Main-class
 *
 * @author Teemu
 */
public class GameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

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
        this.setTitle("Kirjainreaktiojuttu");
        this.setPreferredSize(new Dimension(512, 512)); // TODO Muitakin kokoja?

        //this.setResizable(false);
        this.setMinimumSize(new Dimension(512, 512));
        this.getContentPane().add(area.getGUI());
        this.addKeyListener(killer);
        this.addMouseListener(klikker);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Only restart game, if game has ended
     */
    public void newGame() {
        if (area.gameEnded()) {
            area.restartGame();
        }
    }

    public PlayArea getPlayArea() {
        return area;
    }
}
