package com.example.sidescroller.game.entities;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.Tile;

import java.util.LinkedList;

/**
 * Created by Owner on 18/11/13.
 */
public class Entity {
    protected int x, y;
    protected      Level              level;
    protected      Sprite             sprite;
    private static Screen             screen;
    public static  LinkedList<Entity> entities;

    public void setLevel(Level level) { this.level = level; }
    public static void setScreen(Screen displayScreen) { screen = displayScreen; }

    protected boolean collision(int xa, int ya) {
        int tileX = (x + xa) / Tile.TILE_SIZE;
        int tileY = (y + ya) / Tile.TILE_SIZE;
        if (level.getTile(tileX, tileY).isSolid())
            return true;
        return false;
    }

    protected boolean collision_enemy(int xa, int ya) {
        for (int i = 1; i < entities.size(); i++) {//start at 1 because frank is the first entity
            if(inTheRangeOf(xa, 32, entities.get(i).getX(), 64) &&
                    inTheRangeOf(ya,32, entities.get(i).getY(), 64)){
                entities.remove(i);//delete that guy!!
                return true;
            }
        }
        return false;
    }

    private boolean inTheRangeOf(int bombPosition, int bombSize, int enemyPosition, int enemySize) {
        enemyPosition -= enemySize / 2; //start from bottom of it
        bombPosition -= bombSize / 2;
        for (int i = 0; i < bombSize; i++) {
            for (int j = 0; j < enemySize; j++) {
                if (bombPosition + j == enemyPosition + i) {
                    return true;
                }
            }
        }
        return false;
    }

    public void move() {}
    public void draw() { screen.draw(x, y, sprite); }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}