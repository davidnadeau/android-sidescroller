package com.example.sidescroller.game.entities;

import android.graphics.Rect;
import android.util.Log;

import com.example.sidescroller.game.Screen;
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
    public static  ConcurrentLinkedQueue<Entity> entities;

    public void setLevel(Level level) { this.level = level; }
    public static void setScreen(Screen displayScreen) { screen = displayScreen; }

    protected boolean tileCollision(int xa, int ya) {
        int tileX = (x + xa + screen.xOffset) / Tile.TILE_SIZE;
        int tileY = (y + ya) / Tile.TILE_SIZE;
        if (level==null || level.getTile(tileX, tileY)==null)
            return false;
        else return level.getTile(tileX, tileY).isSolid();
    }

    public Entity enemyCollision(Rect other) {
        LinkedList<Entity> mutableEntities = new LinkedList<Entity>(entities);
        mutableEntities.pop();

        for (Entity e : mutableEntities)
            if (other.intersect(e.toRect()))
                return e;
        return new Entity();
    }

    public boolean isOffScreen() {
        return (x < 0 || y < 0 || x >= screen.getWidth() || y >= screen.getHeight()) ? true : false;
    }

    public void move() {}
    public void draw() { screen.draw(x, y, sprite); }

    public Rect toRect() {
        if (sprite==null) return new Rect();
        int size = sprite.getSize() / 2;
        return new Rect(x - size, y - size, x + size, y + size);
    }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}