/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tl0.jlab.logic;

/**
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
