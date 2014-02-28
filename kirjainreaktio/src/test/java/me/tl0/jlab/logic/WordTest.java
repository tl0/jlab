package me.tl0.jlab.logic;

import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordTest {

    static Word w;

    @BeforeClass
    public static void setUpClass() throws Exception {
        w = new Word();
    }

    /**
     * Tää on ärsyttävä ja bugaa välil... Etenkin jos menee suoraa sivuille
     */
    @Test
    public void movesAlongX() {
        int x = w.getX();
        for (int i = 0; i < 400; i++) {
            w.move();
        }
        assertTrue(x != w.getX());
    }

    /**
     * Tää on ärsyttävä ja bugaa välil... Etenkin jos menee suoraa ylös/alas
     */
    @Test
    public void movesAlongY() {
        int x = w.getY();
        for (int i = 0; i < 400; i++) {
            w.move();
        }
        assertTrue(x != w.getY());
    }

    @Test
    public void shouldDieTestNegative() {
        w.setX(-1);
        w.setY(-1);
        assertTrue(w.shouldDie());
    }

    @Test
    public void shouldDieTestOutOfArea() {
        w.setX(555);
        w.setY(555);
        assertTrue(w.shouldDie());
    }

    @Test
    public void toStringTest() {
        assertTrue(!w.toString().isEmpty());
    }

    @Test
    public void valueIsRight() {
        assertTrue(1 < w.getValue());
    }

    @Test
    public void getWidthIs() {
        assertTrue(w.getWidth() > 30);
    }

    @Test
    public void getX() {
        assertTrue(w.getX() != 0);
    }

    @Test
    public void getY() {
        assertTrue(w.getY() != 0);
    }

    @Test
    public void typer() {
        w.addTypedLetter(w.getContent().toString().charAt(0));
        assertTrue(w.getTypedContent() != "");
    }

    @Test
    public void wordLen() {
        assertTrue(w.getContent().toString().length() > 1);
    }
}
