/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tl0.jlab.logic;

import java.util.List;
import me.tl0.jlab.gui.PlayAreaGUI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
        area.restartGame();
        assertEquals(5, area.getHealth());
    }

    @Test
    public void spawnLetter() {
        area.spawnLetter();
        assertEquals(1, area.getLetterCount());
    }

    @Test
    public void getmode() {
        assertTrue(area.getMode() instanceof Mode);
    }

    @Test
    public void getgui() {
        assertTrue(area.getGUI() instanceof PlayAreaGUI);
    }

    @Test
    public void geths() {
        assertTrue(area.getHS() >= 0);
    }

    @Test
    public void healthEnded() {
        area.setHealth(0);
        assertTrue(area.gameEnded());
    }

    @Test
    public void lastspawned() {
        area.spawnLetter();
        assertTrue(area.getLastSpawned() >= 1);
    }

    @Test
    public void getKillQueue() {
        assertTrue(area.getKillQueue() instanceof List);
    }

    @Test
    public void removeLetterIjncreasespoints() {
        area.setPoints(0);
        area.spawnLetter();
        area.removeLetter(area.getLetters().get(0));
        assertTrue(area.getPoints() == 1);
        area.setPoints(0);
    }

}
