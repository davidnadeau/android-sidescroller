package com.example.sidescroller.characters;

import com.example.sidescroller.Peripherals.Weapon;

/**
 * Created by Owner on 14/11/13.
 */
public class Frank extends Entity {
    private int score, lives;
    private Weapon weapon;

    public Frank(){
        super();
    }

    /*
     * Returns if the object was hit or not
     */
    public boolean shoot(){
        return true;
    }
}
