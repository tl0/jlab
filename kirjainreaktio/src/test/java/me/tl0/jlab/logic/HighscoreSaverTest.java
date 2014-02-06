package me.tl0.jlab.logic;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighscoreSaverTest {

    static HighscoreSaver saver;
    static File file;

    @BeforeClass
    public static void setUpClass() throws Exception {
        saver = new HighscoreSaver();
        file = new File("hs.txt");
    }

    @Before
    public void setUp() {
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void readingWorks() {
        saver.saveScore(10);
        assertEquals(10, saver.getHScore());
    }

    @Test
    public void writingWorks() {
        saver.saveScore(10);
        assertTrue(file.exists());
    }

    @Test
    public void onlyWriteIfBiggerHS() {
        saver.saveScore(10);
        saver.saveScore(15);
        assertEquals(15, saver.getHScore());
    }

    @Test
    public void dontWriteIfLess() {
        saver.saveScore(10);
        saver.saveScore(5);
        assertEquals(10, saver.getHScore());
    }

    @Test
    public void closedAfterRead() {
        saver.saveScore(1);
        saver.getHScore();
        try {
            assertTrue(saver.br.ready());
        } catch (IOException ex) {
        }
    }
}
