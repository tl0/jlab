package me.tl0.jlab.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import me.tl0.jlab.logic.HighscoreSaver;
import me.tl0.jlab.logic.Letter;

/**
 * PlayArea is the area that gameplay happends.
 *
 * TODO More gamemodes etc?
 *
 * @author Teemu
 */
public class PlayArea extends JPanel {

    private static final long serialVersionUID = 1L;

    List<Letter> letters;
    List<Character> killQueue;
    int maxLetters;
    long lastSpawned;
    int health;
    int points;
    int hscore;
    Timer timer;
    HighscoreSaver hs;

    public PlayArea() {
        letters = Collections.synchronizedList(new CopyOnWriteArrayList<Letter>());
        killQueue = Collections.synchronizedList(new CopyOnWriteArrayList<Character>());
        maxLetters = 9;
        lastSpawned = 0;
        health = 5;
        points = 0;
        hs = new HighscoreSaver();
        hscore = hs.getHScore();
        timer = new Timer(1000 / 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.setRepeats(true);
        timer.start();
    }

    /**
     * Paints PlayArea and it's letters correctly
     *
     * TODO Clean and refactor (split)
     *
     * @param g Graphics
     */
    @Override
    public void paint(Graphics g) {
        synchronized (letters) { // XXX Tähän on varmaankin joku järkevämpi tapa?
            synchronized (killQueue) {
                g.clearRect(0, 0, 512, 512);
                g.drawString("X", 250, 250);
                g.drawString("<3 jäljellä " + health, 20, 20);
                g.drawString("Pisteesi: " + points, 20, 30);
                g.drawString("Highscore: " + this.hscore, 20, 40);

                Iterator< Letter> it = letters.iterator();
                while (it.hasNext()) {
                    Letter i = it.next();
                    if (killQueue.contains(i.getChar())) {
                        letters.remove(i);
                        points++;
                    }

                    g.setColor(Color.black);
                    g.fillOval(i.getX(), i.getY(), 30, 30);
                    g.setColor(Color.white);
                    g.drawString(String.valueOf(i.getChar()), i.getX() + 12, i.getY() + 20);

                    i.move();
                    if (i.shouldDie()) {
                        letters.remove(i);
                        health--;
                    }
                }
                killQueue.clear();
            }
        }
        if (gameEnded()) {
            timer.stop();
            hs.saveScore(getPoints());
            g.setColor(Color.red);
            g.fillRect(0, 0, 512, 512);
            g.setColor(Color.black);
            g.drawString("HÄVISIT PELIN! :D", 200, 200);
            g.drawString("Pisteesi: " + getPoints(), 220, 220);
            g.drawString("Highscore: " + hs.getHScore(), 220, 240);
            g.setColor(Color.gray);
            g.fillRect(200, 240, 100, 30);
            g.setColor(Color.black);
            g.drawString("Uusi peli", 220, 260);
        }

    }

    /**
     * This is called by LetterDestroyer, this adds that letter to killQueue
     *
     * @param c
     */
    public void killLetters(char c) {
        synchronized (killQueue) {
            killQueue.add(c);
        }
    }

    /**
     * Timer calls this method and this handles of spawning new letters and
     * repainting PlayArea
     */
    public void tick() {
        if (letters.size() < maxLetters && (System.currentTimeMillis() - lastSpawned > 700 || letters.size() < 1)) {
            lastSpawned = System.currentTimeMillis();
            letters.add(new Letter(this));
        }

        this.repaint();
    }

    /**
     * Restarts game
     */
    public void restartGame() {
        this.points = 0;
        this.health = 5;
        this.hscore = hs.getHScore();
        letters.clear();
        killQueue.clear();
        timer.start();
    }

    public List<Letter> getLetters() {
        return Collections.unmodifiableList(letters);
    }

    public List<Character> getKillQueue() {
        return Collections.unmodifiableList(killQueue);
    }

    public long getLastSpawned() {
        return lastSpawned;
    }

    public int getPoints() {
        return points;
    }

    public boolean gameEnded() {
        return health < 1;
    }

}
