/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tl0.jlab.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import me.tl0.jlab.gui.PlayAreaGUI;

/**
 * Represents word ingame
 *
 * @author Teemu
 */
public class Word implements PlayObject {

    private String c;
    private int y;
    private int x;
    private double xX;
    private double yY;
    private Random random;
    private PlayAreaGUI area;
    private ArrayList<String> words;
    private String typed;
    private int width;

    public Word() {
        words = new ArrayList<String>(Arrays.asList("CS", "auto", "suomi", "HY", "helsinki")); // Words
        this.random = new Random();
        this.c = words.get(random.nextInt(words.size()));
        this.typed = "";
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
    public Word(PlayAreaGUI area) {
        this();
        this.area = area;
    }

    /**
     * Keeps track of current progress in typing word
     *
     * @param input
     */
    @Override
    public void addTypedLetter(char input) {
        if (Character.toUpperCase(c.charAt(typed.length())) == Character.toUpperCase(input)) {
            typed = typed.concat(String.valueOf(input));
        }
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
        yY *= (3.5 / Math.sqrt(xX * xX + yY * yY));
        xX *= (3.5 / Math.sqrt(xX * xX + yY * yY));
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
        return c.toUpperCase();
    }

    @Override
    public Object getTypedContent() {
        return typed.toUpperCase();
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
        return (int) (30 + getContent().toString().length() * 5.5);
    }

    @Override
    public int getValue() {
        return c.length();
    }

    @Override
    public void setArea(PlayAreaGUI area) {
        this.area = area;

        x = (area instanceof PlayAreaGUI) ? area.getWidth() / 2 : 512 / 2;
        y = (area instanceof PlayAreaGUI) ? area.getHeight() / 2 : 512 / 2;
    }
}
