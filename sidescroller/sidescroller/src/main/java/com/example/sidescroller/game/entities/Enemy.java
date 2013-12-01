package com.example.sidescroller.game.entities;

import android.graphics.Point;

/**
 * Created by Owner on 18/11/13.
 */
public class Enemy extends Entity {
    private int value; //how much the enemy is worth for the score once hit

    //fight the power
    public Enemy() {}
    public Enemy(int value) { this.value = value; }

    public void move(Point p) {
        if (collision(p)) return;
        //move
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }
}
