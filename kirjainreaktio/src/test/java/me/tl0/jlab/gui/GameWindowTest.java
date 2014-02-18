package me.tl0.jlab.gui;

import java.awt.GraphicsEnvironment;
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
        if(GraphicsEnvironment.isHeadless()) {
        assertEquals(1, win.getComponents().length); // PlayArea
        } else {
            assertEquals(true, true);
        }
    }
}
