package me.tl0.jlab;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class LetterTests {

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
            if (!kirjaimet.contains(new Letter().getChar())) {
                assertTrue(false);
            }
        }
        assertTrue(true);
    }
    
    @Test
    public void moveDoesChangeXnY() {
        int x = l.getX();
        int y = l.getY();
        l.move();
        assertTrue(x != l.getX() && y != l.getY());
    }
    
    @Test
    public void shouldDieTest() {
        l.setX(-1);
        l.setY(-1);
        assertTrue(l.shouldDie());
    }
}
