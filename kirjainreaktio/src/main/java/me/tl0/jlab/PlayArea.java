package me.tl0.jlab;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class PlayArea extends JPanel {

    List<Letter> letters;
    List<Character> killQueue;
    int maxLetters;
    long lastSpawned;
    int health;
    int points;

    public PlayArea() {
        letters = Collections.synchronizedList(new CopyOnWriteArrayList<Letter>());
        killQueue = Collections.synchronizedList(new CopyOnWriteArrayList<Character>());
        maxLetters = 7;
        lastSpawned = 0;
        health = 5;
        points = 0;
    }

    @Override
    public void paint(Graphics g) {
        synchronized (letters) { // Tähän on varmaankin joku järkevämpi tapa?
            synchronized (killQueue) {
                g.clearRect(0, 0, 512, 512);
                g.drawString("X", 250, 250);
                g.drawString("<3 jäljellä " + health, 20, 20);
                g.drawString("Pisteesi: " + points, 20, 30);

                Iterator< Letter> it = letters.iterator();
                while (it.hasNext()) {
                    Letter i = it.next();
                    if (killQueue.contains(i.getChar())) {
                        letters.remove(i);
                        points++;
                    }

                    i.paint(g);
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
            g.setColor(Color.red);
            g.fillRect(0, 0, 512, 512);
            g.setColor(Color.black);
            g.drawString("HÄVISIT PELIN! :D", 220, 200);
            g.drawString("Pisteesi: " + getPoints(), 250, 220);
            g.setColor(Color.gray);
            g.fillRect(200, 240, 100, 30);
            g.setColor(Color.black);
            g.drawString("Uusi peli", 220, 260);
        } else {
            tick();
        }

    }

    public void killLetters(char c) {
        synchronized (killQueue) {
            killQueue.add(c);
        }
    }

    public void tick() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (letters.size() < maxLetters && (System.currentTimeMillis() - lastSpawned > 700 || letters.size() < 1)) {
            lastSpawned = System.currentTimeMillis();
            letters.add(new Letter());
        }

        this.repaint();
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
