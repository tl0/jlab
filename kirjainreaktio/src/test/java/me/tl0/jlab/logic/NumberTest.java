package me.tl0.jlab.logic;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumberTest {

    static Number n;

    @BeforeClass
    public static void setUpClass() throws Exception {
        n = new Number();
    }

    @Test
    public void containsNumber() {
        assertTrue(Integer.parseInt(n.getContent().toString()) >= 1 && Integer.parseInt(n.getContent().toString()) <= 9);
    }
}
