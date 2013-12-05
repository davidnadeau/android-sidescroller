package com.example.sidescroller.game.entities.enemies;

import com.example.sidescroller.game.entities.Entity;

/**
 * Created by Owner on 18/11/13.
 */
public class SnailEnemy extends Entity {
    private int     value; //how much the enemy is worth for the score once hit
    private boolean mid_air = true, walking_left, walking_right;
    private int spriteNumber = 0;
    //fight the power
    public SnailEnemy() {}
    public SnailEnemy(int x, int y) {
        this.x = x - 50;
        this.y = y;
        Entity.entities.add(this);
    }

    @Override
    public void move() {
        sprite = EnemySprites.snail;
        if (mid_air) { //if he is in mid air, we want him to fall to the floor
            if (!collision(x, +sprite.getSize())) { //if he is not on the ground to begin with
                y += sprite.getSize(); //increment y to make him closer to ground
            } else {
                mid_air = false;
                walking_left = true;
            }
        } else if (walking_left) { //if he is already on the ground
            x -= sprite.getSize() / 2; //move enemy to the left
            if (spriteNumber == 1) {
                sprite = EnemySprites.snail2;
                spriteNumber = 0;
            } else {
                spriteNumber++;
            }
        }
        draw();
    }
}
