package me.tl0.jlab.logic;

import java.util.Random;
import me.tl0.jlab.gui.PlayAreaGUI;

/**
 * Represents one letter in-game
 *
 * @author Teemu
 */
public class Letter implements PlayObject {

    char c;
    Random random;
    private int y;
    private int x;
    private double xX;
    private double yY;
    private PlayAreaGUI area;

    public Letter() {
        this.random = new Random();
        this.c = Character.toChars(random.nextInt(26) + 65)[0]; // A-Z
        x = 512 / 2;
        y = 512 / 2;
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
    @Override
    public void move() {
        xX *= (3.5 / Math.sqrt(xX * xX + yY * yY)) + area.getWidth() - 512;
        yY *= (3.5 / Math.sqrt(xX * xX + yY * yY)) + area.getHeight() - 512;
        this.x += xX;
        this.y += yY;
    }

    /**
     * If letter is out from PlayAreaGUI, it should die (removed) If PlayAreaGUI
     * is not defined (eg. tests), default to 512
     *
     * @return
     */
    @Override
    public boolean shouldDie() {
        int w = (area instanceof PlayAreaGUI) ? area.getWidth() : 512;
        int h = (area instanceof PlayAreaGUI) ? area.getHeight() : 512;
        return (x < 0 || y < 0 || x > w - 30 || y > h - 30);
    }

    @Override
    public Object getContent() {
        return this.c;
    }

    public void setContent(char c) {
        this.c = c;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return 30;
    }

    @Override
    public void addTypedLetter(char input) {
        // We wont need this!
    }

    @Override
    public Object getTypedContent() {
        return null;
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public void setArea(PlayAreaGUI area) {
        this.area = area;

        x = (area instanceof PlayAreaGUI) ? area.getWidth() / 2 : 512 / 2;
        y = (area instanceof PlayAreaGUI) ? area.getHeight() / 2 : 512 / 2;
    }

}
