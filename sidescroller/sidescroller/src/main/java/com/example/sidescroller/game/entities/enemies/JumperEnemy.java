package com.example.sidescroller.game.entities.enemies;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.level.Tile;

/**
 * Created by Owner on 01/12/13.
 */
public class JumperEnemy extends Entity{
    private int value; //how much the enemy is worth for the score once hit
    private boolean mid_air = true, jumping;
    private int jumpHeight;

    //fight the power
    public JumperEnemy() {}
    public JumperEnemy(int x, int y) {
        this.x = x;
        this.y = y / 2;
        jumpHeight = this.y - 10;
    }

    public void move() {
        sprite = EnemySprites.enemy_walker;//walking sprite

        if(mid_air){ //if he is in mid air, we want him to fall to the floor
            if(!collision(x, +Tile.TILE_SIZE)){ //if he is not on the ground to begin with
                y += Tile.TILE_SIZE; //increment y to make him closer to ground
            }
            else{ //he has already hit the ground so now enemy has to jump
                mid_air = false;
                jumping = true;
            }
        }
        else if(jumping){ //if he is already on the ground
            if(y < jumpHeight){
                mid_air = true;
                jumping = false;
            }
            else{
                x -= Tile.TILE_SIZE; //decrement x
                y -= Tile.TILE_SIZE; //decrement y
            }
        }
    }
    public void draw(Screen s) { s.draw(x - Tile.TILE_SIZE, y - Tile.TILE_SIZE, sprite); }
}
