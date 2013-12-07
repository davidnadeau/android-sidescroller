package com.example.sidescroller.game.entities;

import android.util.Log;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.peripherals.Bomb;
import com.example.sidescroller.game.graphics.Sprite;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.Tile;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Owner on 18/11/13.
 */
public class Entity {
    protected int x, y;
    protected      Level                         level;
    protected      Sprite                        sprite;
    private static Screen                        screen;
    public static volatile ConcurrentLinkedQueue<Entity> entities;

    public void setLevel(Level level) { this.level = level; }
    public static void setScreen(Screen displayScreen) { screen = displayScreen; }

    protected boolean collision(int xa, int ya) {
        int tileX = (x + xa + screen.xOffset) / Tile.TILE_SIZE;
        int tileY = (y + ya) / Tile.TILE_SIZE;
        return level.getTile(tileX, tileY).isSolid();
    }

    protected boolean collision_enemy(int xa, int ya, int size) {
        LinkedList<Entity> mutableEntities = new LinkedList<Entity>(entities);
        mutableEntities.pop();

        for (Entity e : mutableEntities)  {
            if(inTheRangeOf(xa, size, e.getX(), 64) &&
               inTheRangeOf(ya, size, e.getY(), 64)){ //all of our entities are size 64
                synchronized (entities) {
                    entities.remove(e);//delete that guy!!
                }
                return true;
            }
        }
        return false;
    }

    protected boolean inTheRangeOf(int bombPosition, int bombSize, int enemyPosition, int enemySize) {
        enemyPosition -= enemySize / 2; //start from bottom of it
        bombPosition -= bombSize / 2;
        for (int i = 0; i < bombSize; i++) {
            for (int j = 0; j < enemySize; j++) {
                if (bombPosition + j == enemyPosition + i) {return true;}
            }
        }
        return false;
    }

    public boolean isOffScreen() {
        return (x < 0 || y < 0 || x >= screen.getWidth() || y >= screen.getHeight())? true: false;
    }

    public void move() {}
    public void draw() { screen.draw(x, y, sprite); }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}