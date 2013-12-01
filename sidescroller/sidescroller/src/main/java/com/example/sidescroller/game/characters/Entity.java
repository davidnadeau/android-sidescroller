package com.example.sidescroller.game.characters;

import com.example.sidescroller.game.level.Level;

/**
 * Created by Owner on 18/11/13.
 */
public class Entity {
    protected int x, y;

    protected Level level;

    public int getX() { return x; }
    public int getY() { return y; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void setLevel(Level level) { this.level = level; }
}