/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.tl0.jlab.logic;

import java.util.Random;

/**
 *
 * @author Teemu
 */
public class Number extends Letter {
    public Number() {
        super();
        this.setContent(Character.toChars(random.nextInt(10) + 48)[0]);
    }
}
