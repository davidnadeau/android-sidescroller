package com.example.sidescroller.game.entities.enemies;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.level.Tile;

/**
 * Created by Owner on 18/11/13.
 */
public class Enemy extends Entity {
    private int value; //how much the enemy is worth for the score once hit

    //fight the power
    public Enemy() {}
    public Enemy(int x, int y) {
        this.x = x;
        this.y = y / 2;
    }

    public void move() {
        sprite = EnemySprites.enemy_walker;//walking sprite
        //move randomly
        y += Math.random() > .5 ? 16 : -16;
        x += Math.random() > .5 ? 16 : -16;
    }

    public void draw(Screen s) { s.draw(x - Tile.TILE_SIZE, y - Tile.TILE_SIZE, sprite); }

}
