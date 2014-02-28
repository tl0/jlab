package me.tl0.jlab.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class manages clicks that this program receives and handles them
 *
 * @author Teemu
 */
public class ClickListener implements MouseListener {

    GameWindow win;

    public ClickListener(GameWindow win) {
        this.win = win;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Handles fired event correctly
     *
     * @param e MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        // 200, 240, 100, 30
        if (p.x > 200 && p.x < 300 && p.y > 265 && p.y < 300) {
            win.newGame();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
