package me.tl0.jlab.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This listener handles typing
 *
 * @author Teemu
 */
public class LetterDestroyer implements KeyListener {

    PlayArea area;

    public LetterDestroyer(PlayArea area) {
        this.area = area;
    }

    /**
     * Handles fired event correctly and adds it to PlayArea's killQueue waiting
     * to be processed
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i > 96) {
            i -= 32;
        }
        area.killLetters(Character.toChars(i)[0]);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
