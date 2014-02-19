package me.tl0.jlab.logic;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class LetterTest {

    static ArrayList<Character> kirjaimet;
    static Letter l;

    @BeforeClass
    public static void setUpClass() throws Exception {
        l = new Letter();
        kirjaimet = new ArrayList<Character>();
        kirjaimet.add('A');
        kirjaimet.add('B');
        kirjaimet.add('C');
        kirjaimet.add('D');
        kirjaimet.add('E');
        kirjaimet.add('F');
        kirjaimet.add('G');
        kirjaimet.add('H');
        kirjaimet.add('I');
        kirjaimet.add('J');
        kirjaimet.add('K');
        kirjaimet.add('L');
        kirjaimet.add('M');
        kirjaimet.add('N');
        kirjaimet.add('O');
        kirjaimet.add('P');
        kirjaimet.add('Q');
        kirjaimet.add('R');
        kirjaimet.add('S');
        kirjaimet.add('T');
        kirjaimet.add('U');
        kirjaimet.add('V');
        kirjaimet.add('W');
        kirjaimet.add('X');
        kirjaimet.add('Y');
        kirjaimet.add('Z');
    }

    @Test
    public void containsAlphabet() {
        for (int i = 0; i < 100; i++) {
            if (!kirjaimet.contains(new Letter().getContent())) {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }

    @Test
    public void moveDoesChangeY() {
        int y = l.getY();
        for (int i = 0; i < 100; i++) {
            l.move(); // Just to make sure that it moves!
        }
        assertTrue(y != l.getY());
    }

    @Test
    public void moveDoesChangeX() {
        int x = l.getX();
        for (int i = 0; i < 100; i++) {
            l.move(); // Just to make sure that it moves!
        }
        assertTrue(x != l.getX());
    }

    @Test
    public void shouldDieTestNegative() {
        l.setX(-1);
        l.setY(-1);
        assertTrue(l.shouldDie());
    }

    @Test
    public void shouldDieTestOutOfArea() {
        l.setX(555);
        l.setY(555);
        assertTrue(l.shouldDie());
    }

    @Test
    public void toStringTest() {
        assertTrue(!l.toString().isEmpty());
    }
}
