package com.example.sidescroller.game.entities.enemies;

import com.example.sidescroller.game.entities.Entity;

/**
 * Created by Owner on 01/12/13.
 */
public class FishEnemy extends Entity {
    private int     value; //how much the enemy is worth for the score once hit
    private boolean mid_air = true, jumping;
    private int jumpHeight;
    private int spriteNumber = 0;
    //fight the power
    public FishEnemy() {}
    public FishEnemy(int x, int y) {
        this.x = x;
        this.y = y;
        jumpHeight = this.y - 10;
        Entity.entities.add(this);
    }

    @Override
    public void move() {
        sprite = EnemySprites.flyFish;

        if (mid_air) { //if he is in mid air, we want him to fall to the floor
            if (!collision(x, +sprite.getSize())) { //if he is not on the ground to begin with
                y += sprite.getSize(); //increment y to make him closer to ground
            } else { //he has already hit the ground so now enemy has to jump
                mid_air = false;
                jumping = true;
            }
        } else if (jumping) { //if he is already on the ground
            if (y < jumpHeight) {
                mid_air = true;
                jumping = false;
            } else {
                x -= sprite.getSize(); //decrement x
                y -= sprite.getSize(); //decrement y
            }
        }
        if (spriteNumber == 1) {
            sprite = EnemySprites.flyFish2;
            spriteNumber = 0;
        } else {
            spriteNumber++;
        }
        draw();
    }
}
