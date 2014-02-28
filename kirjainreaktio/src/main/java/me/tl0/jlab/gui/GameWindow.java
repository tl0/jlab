package me.tl0.jlab.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import me.tl0.jlab.logic.Mode;
import me.tl0.jlab.logic.PlayArea;

/**
 * GameWindow is the main component that is created by Main-class
 *
 * @author Teemu
 */
public class GameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    PlayArea area;
    LetterDestroyer keyListener;
    ClickListener clickListener;
    MenuWindow menu;
    JPanel cards;
    JPanel card1;
    JPanel card2;

    public GameWindow() {
        super("Testi");
        menu = new MenuWindow(this);
        area = new PlayArea();
        keyListener = new LetterDestroyer(area);
        clickListener = new ClickListener(this);
    }

    public void run() {
        this.setTitle("Kirjainreaktiojuttu");
        this.setPreferredSize(new Dimension(512, 512)); // TODO Muitakin kokoja?

        card1 = menu;
        card2 = area.getGUI();

        cards = new JPanel(new CardLayout());
        cards.add(card1, "menu");
        cards.add(card2, "game");

        this.setMinimumSize(new Dimension(512, 512));

        this.getContentPane().add(cards);

        this.setFocusable(true);
        card2.setFocusable(true);
        this.addKeyListener(keyListener);
        this.addMouseListener(clickListener);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(100, 100);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Changes CardLayout to show PlayArea and sets Mode correctly and starts
     * game.
     *
     * @param gamemode
     */
    public void startGame(Mode gamemode) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "game");
        card1.requestFocus();
        area.setGamemode(gamemode);
        this.setResizable(true);
        this.revalidate();
    }

    /**
     * Changes CardLayout to show menu
     */
    public void showMenu() {
        CardLayout cl = (CardLayout) (cards.getLayout());
        if (area.gameEnded()) {
            cl.show(cards, "menu");
            this.revalidate();
        }
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
