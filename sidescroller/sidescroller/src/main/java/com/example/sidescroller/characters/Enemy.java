package com.example.sidescroller.characters;

import android.graphics.Point;

/**
 * Created by Owner on 14/11/13.
 */
public class Enemy extends Entity {
    private int value; //how much the enemy is worth for the score once hit

    //fight the power
    public Enemy() {}
    public Enemy(int value){ this.value = value; }

    public void move(Point p) {
        if (collision(p)) return;
        //move
    }

    /*
     * return true if 1 pixel over in the set direction is a solid tile
     */
    protected boolean collision(Point p) {
        //get next tile, check if solid
        return false;
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }
}
