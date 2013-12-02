package com.example.sidescroller.game.entities.player;

import android.util.Log;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.graphics.Sprite;
import com.example.sidescroller.game.level.Tile;

/**
 * Created by Owner on 18/11/13.
 */
public class Frank extends Entity {
    private int lives, score;

    private boolean jumping = false;
    private boolean falling = true;
    private int     startY  = y;
    private int jumpHeight;

    public Frank(int x, int y) {
        this.x = x / 3;
        this.y = y / 2;
        jumpHeight = this.y - 64;
    }

    public void move() {
        sprite = FrankSprites.frank_walk;
        if (jumping) {        Log.d("COLLISION","jump");

            if (!collision(0, -sprite.getSize()) && y >= jumpHeight) {        Log.d("COLLISION","keep jumping");

                y -= sprite.getSize();
                sprite = FrankSprites.frank_jump;//jump sprite
            } else {        Log.d("COLLISION","done jump");

                jumping = false;
                falling = true;
                sprite = FrankSprites.frank_fall;//fall sprite
            }
        } else if (falling) {        Log.d("COLLISION","fall");

            if (!collision(0, sprite.getSize())) {        Log.d("COLLISION","keep falling");

                y += sprite.getSize();
                sprite = FrankSprites.frank_fall;//fall sprite
            } else {        Log.d("COLLISION","done fall");

                falling = false;
                sprite = FrankSprites.frank_land;//land sprite
            }
        } else {        Log.d("COLLISION","walk");

            sprite = FrankSprites.frank_walk;//walking sprite
        }
    }

    public void draw(Screen s) { s.draw(x, y, sprite); }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
        startY = y;
        jumpHeight = startY - 128;
    }
    public boolean isJumping() { return jumping; }
    public boolean isFalling() { return falling; }

    /**
     * Returns true if the object was hit
     */
    public boolean shoot() { return true; }

    public int getLives() { return lives; }
    public int getScore() { return score; }

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
}
