package me.tl0.jlab.logic;

import java.util.ArrayList;
import java.util.Random;
import me.tl0.jlab.gui.PlayAreaGUI;

/**
 *
 * @author Teemu
 */
public interface PlayObject {

    
    @Override
    public String toString();
    public void move();
    public boolean shouldDie();
    public Object getContent();
    public int getWidth();
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public void addTypedLetter(char input); // Just for words ..
}
