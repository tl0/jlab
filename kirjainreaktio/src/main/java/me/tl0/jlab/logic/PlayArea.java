package me.tl0.jlab.logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import me.tl0.jlab.gui.PlayAreaGUI;

/**
 * PlayArea
 *
 * @author Teemu
 */
public class PlayArea {

    List<PlayObject> letters;
    List<Object> killQueue;
    int maxLetters;
    long lastSpawned;
    int health;
    int points;
    int hscore;
    Timer timer;
    HighscoreSaver hs;
    PlayAreaGUI area;
    Mode mode;

    public PlayArea() {

        this.mode = Mode.WORD; // Game Mode :D
        this.area = new PlayAreaGUI(this);
        letters = Collections.synchronizedList(new CopyOnWriteArrayList<PlayObject>());
        killQueue = Collections.synchronizedList(new CopyOnWriteArrayList<Object>());
        maxLetters = 10;
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
    public void killLetters(Object c) { // XXX WORD support
        synchronized (killQueue) {
            killQueue.add(c);
            if (mode == Mode.WORD) {
                for (PlayObject i : letters) {
                    i.addTypedLetter((char) c);
                }
            }
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
                Iterator<PlayObject> it = letters.iterator();
                while (it.hasNext()) {
                    PlayObject i = it.next();
                    if (killQueue.contains(i.getContent()) || i.getContent().toString().equalsIgnoreCase(i.getTypedContent().toString())) {
                        removeLetter(i);
                    }

                    i.move();

                    if (i.shouldDie()) {
                        letters.remove(i);
                        health--;
                        area.flashRed();
                    }
                }
                killQueue.clear();
            }
        }

        if (gameEnded()) {
            timer.stop();
            hs.saveScore(points);
        }

        area.repaint();
    }

    /**
     * Creates new Letter
     */
    public void spawnLetter() {
        if (getLetterCount() < maxLetters && (System.currentTimeMillis() - lastSpawned > 753 || letters.size() < 1)) {
            lastSpawned = System.currentTimeMillis();
            try {
                //letters.add(new Letter(area));
                letters.add((PlayObject) mode.getJuttu().getConstructor().newInstance());
            } catch (Exception ex) {
                Logger.getLogger(PlayArea.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        if (timer.isRunning()) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    public List<PlayObject> getLetters() {
        return Collections.unmodifiableList(letters);
    }

    public int getLetterCount() {
        return letters.size();
    }

    public void removeLetter(PlayObject letter) {
        points += letter.getValue();
        letters.remove(letter);
    }

    public List<Object> getKillQueue() {
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
    
    public Mode getMode() {
        return mode;
    }
}
