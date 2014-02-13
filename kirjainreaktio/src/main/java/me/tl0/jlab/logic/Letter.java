package me.tl0.jlab.logic;

import java.util.Random;
import me.tl0.jlab.gui.PlayAreaGUI;

/**
 * Represents one letter in-game
 *
 * @author Teemu
 */
public class Letter {

    private char c;
    private int y;
    private int x;
    private double xX;
    private double yY;
    private Random random;
    private PlayAreaGUI area;

    public Letter() {
        this.random = new Random();
        this.c = Character.toChars(random.nextInt(26) + 65)[0]; // A-Z
        y = 245;
        x = 245;
        xX = Math.sin(random.nextDouble() * 365);
        yY = Math.sin(random.nextDouble() * 365);
    }

    /**
     * This is needed to get correct size for PlayAreaGUI
     *
     * @param area PlayAreaGUI
     */
    public Letter(PlayAreaGUI area) {
        this();
        this.area = area;
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }

    /**
     * Moves letter along it's path
     */
    public void move() {
        yY *= (3.5 / Math.sqrt(xX * xX + yY * yY));
        xX *= (3.5 / Math.sqrt(xX * xX + yY * yY));
        this.x += xX;
        this.y += yY;
    }

    /**
     * If letter is out from PlayAreaGUI, it should die (removed) If PlayAreaGUI is
     * not defined (eg. tests), default to 512
     *
     * @return
     */
    public boolean shouldDie() {
        int w = (area instanceof PlayAreaGUI) ? area.getWidth() : 512;
        int h = (area instanceof PlayAreaGUI) ? area.getHeight() : 512;
        return (x < 0 || y < 0 || x > w - 30 || y > h - 30);
    }

    public char getChar() {
        return this.c;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
