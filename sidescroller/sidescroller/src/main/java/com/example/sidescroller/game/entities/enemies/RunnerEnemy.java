package com.example.sidescroller.game.entities.enemies;

import android.util.Log;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.level.Tile;

/**
 * Created by Owner on 18/11/13.
 */
public class RunnerEnemy extends Entity {
    private int value; //how much the enemy is worth for the score once hit
    private boolean mid_air = true, walking_left, walking_right;

    //fight the power
    public RunnerEnemy() {}
    public RunnerEnemy(int x, int y) {
        this.x = x - 50;
        this.y = y / 2;
    }

    public void move() {
        sprite = EnemySprites.enemy_walker;//walking sprite

        if(mid_air){ //if he is in mid air, we want him to fall to the floor
            if(!collision(x, +Tile.TILE_SIZE)){ //if he is not on the ground to begin with
                y += Tile.TILE_SIZE; //increment y to make him closer to ground
            }
            else{
                mid_air = false;
                walking_left = true;
            }
        }
        else if(walking_left){ //if he is already on the ground
            x -= Tile.TILE_SIZE; //move enemy to the left
        }
    }
    public void draw(Screen s) { s.draw(x - Tile.TILE_SIZE, y - Tile.TILE_SIZE, sprite); }
}
