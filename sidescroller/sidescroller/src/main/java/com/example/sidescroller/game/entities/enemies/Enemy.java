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
    private boolean mid_air = true, walking_left = false, walking_right = false;

    //fight the power
    public Enemy() {}
    public Enemy(int x, int y) {
        this.x = x / 2;
        this.y = y / 2;
    }

    public void move() {
        sprite = EnemySprites.enemy_walker;//walking sprite

        if(mid_air){ //if he is in mid air, we want him to fall to the floor
            if(!collision(x, y+Tile.TILE_SIZE)){ //if he is not on the ground to begin with
                y += Tile.TILE_SIZE; //increment y to make him closer to ground
                Log.d("ENEMY","IN MID AIR");
            }
            else{
                mid_air = false; walking_left = true;
                Log.d("ENEMY","NO LONGER IN MID AIR");
            }
        }
        else{ //if he is already on the ground
            if(walking_left && !collision(x - Tile.TILE_SIZE, y)){ //walk left until collision
                x -= Tile.TILE_SIZE; //move enemy to the left
                Log.d("ENEMY","walking left");
            }
            else{ //if we hit a collision going left, go right
                Log.d("ENEMY","we hit something going left so now go right");
                walking_right = true;
                walking_left = false;
                if(walking_right && !collision(x + Tile.TILE_SIZE, y)){//go right until we hit a collision
                    x += Tile.TILE_SIZE; //increment to the right
                    Log.d("ENEMY","walking right");
                }
                else{
                    walking_left = true; //if we hit a collision going right, walk left
                    walking_right = false;
                    Log.d("ENEMY","hit something going right");
                }
            }
        }
    }

    public boolean collision(int xa, int ya) {
        int tileX = (x + xa) / Tile.TILE_SIZE;
        int tileY = (y + ya) / Tile.TILE_SIZE;
        if (level.getTile(tileX, tileY).isSolid())
            return true;
        return false;
    }

    public void draw(Screen s) { s.draw(x - Tile.TILE_SIZE, y - Tile.TILE_SIZE, sprite); }
}
