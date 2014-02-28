package me.tl0.jlab.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Iterator;
import javax.swing.JPanel;
import me.tl0.jlab.logic.Mode;
import me.tl0.jlab.logic.PlayArea;
import me.tl0.jlab.logic.PlayObject;

/**
 * PlayAreaGUI is the area that gameplay happends.
 *
 * TODO More gamemodes etc?
 *
 * @author Teemu
 */
public class PlayAreaGUI extends JPanel {

    private static final long serialVersionUID = 1L;

    final PlayArea area;
    int redStage;

    public PlayAreaGUI(final PlayArea area) {
        this.area = area;
        this.redStage = 0;
        final PlayAreaGUI joo = this;

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                area.resized(joo.getSize());
            }
        });
    }

    /**
     * Paints PlayAreaGUI and it's letters correctly
     *
     * TODO Hirviö =O Do somthng
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        if (area.gameEnded()) {
            g.setColor(Color.red);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.black);
            g.drawString("Game over :(", this.getWidth() / 2 - 40, this.getHeight() / 2);
            g.drawString("Your score: " + area.getPoints(), this.getWidth() / 2 - 40, this.getHeight() / 2 + 20);
            g.drawString("Highscore: " + area.getHS(), this.getWidth() / 2 - 40, this.getHeight() / 2 + 40);
            g.setColor(Color.white);
            g.drawString("Click anywhere to return menu!", this.getWidth() / 2 - 100, this.getHeight() / 2 + 60);
        } else {
            synchronized (area.getLetters()) { // XXX Tähän on varmaankin joku järkevämpi tapa?
                g.clearRect(0, 0, this.getWidth(), this.getHeight());
                if (redStage > 0) {
                    g.setColor(new Color(255, 0, 0, redStage));
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    redStage -= 10;
                }
                g.setColor(Color.black);
                g.drawString("X", this.getWidth() / 2, this.getHeight() / 2);
                g.drawString("<3 jäljellä " + area.getHealth(), 20, 20);
                g.drawString("Pisteesi: " + area.getPoints(), 20, 30);
                g.drawString("Highscore: " + area.getHS(), 20, 40);

                Iterator<PlayObject> it = area.getLetters().iterator();
                while (it.hasNext()) {
                    PlayObject i = it.next();

                    g.setColor(Color.black);
                    g.fillOval(i.getX(), i.getY(), i.getWidth(), 30);
                    g.setColor(Color.white);
                    g.drawString(String.valueOf(i.getContent()), i.getX() + 12, i.getY() + 20);
                    if (area.getMode() == Mode.WORD) { // drawString to draw different color to show current progress ! :)
                        g.setColor(Color.green);
                        g.drawString(String.valueOf(i.getTypedContent()), i.getX() + 12, i.getY() + 20);
                    }
                }
            }
            if (!area.isRunning()) {
                g.setColor(Color.blue);
                g.drawString("!!! PAUSED !!!", this.getWidth() / 2 - 30, this.getHeight() / 2 - 15);
            }
        }
    }

    public void flashRed() {
        if (redStage <= 1) {
            redStage = 100;
        }
    }
}
