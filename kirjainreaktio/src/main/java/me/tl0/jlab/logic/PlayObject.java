package me.tl0.jlab.logic;

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

    public int getValue(); // Valuella tarkoitan pistearvoa, en sisältöä..

    public int getWidth();

    public int getX();

    public int getY();

    public void setX(int x);

    public void setY(int y);

    public void setArea(PlayAreaGUI area);

    public void addTypedLetter(char input); // Just for words ..

    public Object getTypedContent(); // Just for words .. aswell ..
}
