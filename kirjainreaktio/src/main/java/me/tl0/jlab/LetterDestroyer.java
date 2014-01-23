package me.tl0.jlab;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LetterDestroyer implements KeyListener {

    PlayArea area;

    public LetterDestroyer(PlayArea area) {
        this.area = area;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i > 96) {
            i -= 32;
        }
        area.killLetters(Character.toChars(i)[0]);
    }

    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
