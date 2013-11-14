package com.example.sidescroller.characters;

import android.graphics.Point;

/**
 * Created by Owner on 14/11/13.
 */
public class Entity {
    protected int speed, direction;
    public Point location;


    /*
     * Returns true/false based on whether there was a collision or not
     */
    protected boolean move(){
     return true;
    }
}
