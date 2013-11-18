package com.example.sidescroller.characters;

import android.graphics.Point;

import com.example.sidescroller.peripherals.Weapon;

/**
 * Created by Owner on 18/11/13.
 */
public class Frank extends Entity {
    private int lives, score;
    private Weapon weapon;

    public Frank() {
    }

    public Frank(int lives, int score, Weapon weapon) {
        this.lives = lives;
        this.score = score;
        this.weapon = weapon;
    }

    public void move(Point p) {
        if (collision(p)) return;
    }

    /**
     * return true if 1 pixel over in the set direction is a solid tile
     */
    protected boolean collision(Point p) {
        //get next tile, check if solid
        return false;
    }

    /**
     * Returns true if the object was hit
     */
    public boolean shoot() {
        return true;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
