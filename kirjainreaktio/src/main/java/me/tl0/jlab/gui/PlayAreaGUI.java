package me.tl0.jlab.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Iterator;
import javax.swing.JPanel;
import me.tl0.jlab.logic.Letter;
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

    PlayArea area;
    int redStage;

    public PlayAreaGUI(PlayArea area) {
        this.area = area;
        this.redStage = 0;
    }

    /**
     * Paints PlayAreaGUI and it's letters correctly
     *
     * TODO Clean and refactor (split)
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        if (area.gameEnded()) {
            g.setColor(Color.red);
            g.fillRect(0, 0, 512, 512);
            g.setColor(Color.black);
            g.drawString("Peli loppui!", 200, 200);
            g.drawString("Pisteesi: " + area.getPoints(), 220, 220);
            g.drawString("Highscore: " + area.getHS(), 220, 240);
            g.setColor(Color.gray);
            g.fillRect(200, 240, 100, 30);
            g.setColor(Color.black);
            g.drawString("Uusi peli", 220, 260);
        } else {
            synchronized (area.getLetters()) { // XXX Tähän on varmaankin joku järkevämpi tapa?
                g.clearRect(0, 0, this.getWidth(), this.getHeight());
                if (redStage > 0) {
                    g.setColor(new Color(255, 0, 0, redStage));
                    g.fillRect(0, 0, this.getWidth(), this.getHeight());
                    redStage-=10;
                }
                g.setColor(Color.black);
                g.drawString(Arrays.toString(area.getKillQueue().toArray()), 20, 70);
                g.drawString(Arrays.toString(area.getLetters().toArray()), 22, 80);
                g.drawString("X", 250, 250);
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
                }
            }
        }
    }

    public void flashRed() {
        if (redStage <= 1) {
            redStage = 100;
        }
    }
}
