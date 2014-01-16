package me.tl0.jlab;

import java.awt.Color;
import java.awt.Graphics;
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

    private void refresh() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        tick();
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        synchronized (letters) {
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
        if(health < 0) {
            g.setColor(Color.red);
            g.fillRect(0, 0, 512, 512);
            g.setColor(Color.black);
            g.drawString("HÄVISIT PELIN! :D", 220, 200);
            g.drawString("Pisteesi: " + points, 250, 220);
        } else {
            refresh();
        }
        
    }

    public void killLetters(char c) {
        synchronized (killQueue) {
            killQueue.add(c);
        }
    }

    public void tick() {
        if (letters.size() < maxLetters && (System.currentTimeMillis() - lastSpawned > 700 || letters.size() < 1)) {
            lastSpawned = System.currentTimeMillis();
            letters.add(new Letter());
        }
    }

}
