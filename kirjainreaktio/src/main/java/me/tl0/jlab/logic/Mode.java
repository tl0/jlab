package me.tl0.jlab.logic;

/**
 * Enums of supported Game Modes
 *
 * Adding new Mode is relatively easy.. 1. Just create new class that implements
 * PlayObject 2. Add it to Menu, so it can actually be played on 3. Maybe tweak
 * highscoresaver, little.
 *
 * @author Teemu
 */
public enum Mode {

    LETTER(Letter.class), NUMBER(Number.class), WORD(Word.class);

    Mode(Class c) {
        this.c = c;
    }
    protected Class c;

    public Class getJuttu() {
        return this.c;
    }

}
