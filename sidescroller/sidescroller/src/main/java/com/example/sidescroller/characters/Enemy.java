package com.example.sidescroller.characters;

/**
 * Created by Owner on 14/11/13.
 */
public class Enemy {
    private int value; //how much the enemy is worth for the score once hit

    public Enemy(){
        super();
    }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }
}
