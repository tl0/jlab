/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tl0.jlab.logic;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Teemu
 */
public class PlayAreaTest {

    static PlayArea area;

    @BeforeClass
    public static void setUpClass() {
        area = new PlayArea();
        area.pauseGame();
    }

    @Test
    public void startPoints() {
        assertEquals(0, area.getPoints());
    }

    @Test
    public void startHealth() {
        assertEquals(5, area.getHealth());
    }

    @Test
    public void spawnLetter() {
        area.spawnLetter();
        assertEquals(1, area.getLetterCount());
    }

}
