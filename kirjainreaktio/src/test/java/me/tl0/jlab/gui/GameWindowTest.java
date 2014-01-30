package me.tl0.jlab.gui;

import me.tl0.jlab.gui.GameWindow;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameWindowTest {
    
    static GameWindow win;
    
    @BeforeClass
    public static void setUpClass() {        
        win = new GameWindow();
    }
    
    @Test
    public void componentCountIsRight() {
        assertEquals(1, win.getComponents().length); // PlayArea
    }
}
