package com.example.sidescroller.characters;

import com.example.sidescroller.peripherals.Weapon;

/**
 * Created by Owner on 14/11/13.
 */
public class Frank extends Entity {
    private int lives, score;
    private Weapon weapon;

    public Frank(){
        super();
    }

    /*
     * Returns if the object was hit or not
     */
    public boolean shoot() { return true; }

    public int getLives() { return lives; }
    public int getScore() { return score; }
    public Weapon getWeapon() { return weapon; }

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
}
