package com.example.sidescroller.game.entities.enemies;

import android.util.Log;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.level.Tile;

/**
 * Created by Owner on 18/11/13.
 */
public class Enemy extends Entity {
    private int value; //how much the enemy is worth for the score once hit
    private boolean mid_air = true, walking_left, walking_right;

    //fight the power
    public Enemy() {}
    public Enemy(int x, int y) {
        this.x = x -50;
        this.y = y / 2;
    }

    public void move() {
        sprite = EnemySprites.enemy_walker;//walking sprite

        if(mid_air){ //if he is in mid air, we want him to fall to the floor
            if(!collision(x, +sprite.getSize())){ //if he is not on the ground to begin with
                y += sprite.getSize(); //increment y to make him closer to ground
            }
            else{
                mid_air = false;
                walking_left = true;
            }
        }
        else{ //if he is already on the ground

            if(walking_left && !collision(-sprite.getSize(), 0)){ //walk left until collision
                x -= sprite.getSize()*2; //move enemy to the left
            }
            else{ //if we hit a collision going left, go right
                walking_right = true;
                walking_left = false;
                if(walking_right && !collision(sprite.getSize(), 0)){//go right until we hit a collision
                    x += sprite.getSize(); //increment to the right
                }
                else{
                    walking_left = true; //if we hit a collision going right, walk left
                    walking_right = false;
                }
            }
        }
    }
    public void draw(Screen s) { s.draw(x, y, sprite); }
}
