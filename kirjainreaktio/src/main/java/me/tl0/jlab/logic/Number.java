/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tl0.jlab.logic;

/**
 * This was so easy to do add, since this new Mode enums
 *
 * Just extends Letter and replaces content with number :D Simple and working :)
 *
 * Keypad doesn't work for these though... Not on Windows for sure.
 *
 * @author Teemu
 */
public class Number extends Letter {

    public Number() {
        super();
        this.setContent(Character.toChars(random.nextInt(10) + 48)[0]);
    }
}
