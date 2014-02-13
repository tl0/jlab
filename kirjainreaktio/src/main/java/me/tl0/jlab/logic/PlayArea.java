package me.tl0.jlab.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.Timer;
import me.tl0.jlab.gui.PlayAreaGUI;

/**
 * PlayArea
 * 
 * @author Teemu
 */
public class PlayArea {

    List<Letter> letters;
    List<Character> killQueue;
    int maxLetters;
    long lastSpawned;
    int health;
    int points;
    int hscore;
    Timer timer;
    HighscoreSaver hs;
    PlayAreaGUI area;

    public PlayArea() {

        this.area = new PlayAreaGUI(this);
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
        spawnLetter();
        
        synchronized (letters) { // XXX Tähän on varmaankin joku järkevämpi tapa?
            synchronized (killQueue) {
                Iterator<Letter> it = letters.iterator();
                while (it.hasNext()) {
                    Letter i = it.next();
                    if (killQueue.contains(i.getChar())) {
                        letters.remove(i);
                    }

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
        }

        area.repaint();
    }
    
    /**
     * Creates new Letter
     */
    public void spawnLetter() {
        if (getLetterCount() < maxLetters && (System.currentTimeMillis() - lastSpawned > 700 || letters.size() < 1)) {
            lastSpawned = System.currentTimeMillis();
            letters.add(new Letter(area));
        }
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
    
    /**
     * Starts/Stops timer
     */
    public void pauseGame() {
        if(timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    public List<Letter> getLetters() {
        return Collections.unmodifiableList(letters);
    }
    
    public int getLetterCount() {
        return letters.size();
    }

    public void removeLetter(Letter letter) {
        points++;
        letters.remove(letter);
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

    public int getHealth() {
        return health;
    }

    public boolean gameEnded() {
        return health < 1;
    }

    public int getHS() {
        return hscore;
    }

    public PlayAreaGUI getGUI() {
        return area;
    }
}
