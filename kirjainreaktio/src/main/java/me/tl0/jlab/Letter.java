package me.tl0.jlab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Letter {

    private char c;
    private int y;
    private int x;
    private double xX;
    private double yY;
    private Random random;

    public Letter() {
        this.random = new Random();
        this.c = Character.toChars(random.nextInt(26) + 65)[0]; // A-Z
        y = 245;
        x = 245;
        xX = Math.sin(random.nextDouble()*10);
        yY = Math.sin(random.nextDouble()*10);
    }

    @Override
    public String toString() {
        return String.valueOf(c);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(x, y, 30, 30);
        g.setColor(Color.red);
        g.drawString(String.valueOf(c), x + 12, y + 20);
    }

    public void move() {
        yY *= (2.5 / Math.sqrt(xX * xX + yY * yY));
        xX *= (2.5 / Math.sqrt(xX * xX + yY * yY));
        this.x += xX;
        this.y += yY;
    }
    
    public boolean shouldDie() {
        return (x < 0 || y < 0 || x > 512 || y > 512);
    }
    
    public char getChar() {
        return this.c;
    }
}
