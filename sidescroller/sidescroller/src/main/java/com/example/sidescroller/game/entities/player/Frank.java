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
    private int walkingNumber = 0;

    public Frank(int x, int y) {
        this.x = x / 4;
        this.y = y;
        jumpHeight = this.y - 64;
    }

    public void move() {
        sprite = FrankSprites.frank_walk0;
        if (jumping) {
            if (!collision(0, -sprite.getSize()) && y >= jumpHeight) {
                sprite = FrankSprites.frank_jump;//jump sprite
                y -= sprite.getSize();
            } else {
                sprite = FrankSprites.frank_fall;//fall sprite
                jumping = false;
                falling = true;
            }
        } else if (falling) {
            if (!collision(0, sprite.getSize())) {
                sprite = FrankSprites.frank_fall;//fall sprite
                y += sprite.getSize();
            } else {
                sprite = FrankSprites.frank_land;//land sprite
                falling = false;
            }
        } else { //if hes not falling or hes not jumping, hes walking
            switch (walkingNumber) {
                case 1:
                    sprite = FrankSprites.frank_walk1;//walking sprite
                    break;
                case 2:
                    sprite = FrankSprites.frank_walk2;//walking sprite
                    break;
                case 3:
                    sprite = FrankSprites.frank_walk3;//walking sprite
                    break;
                case 4:
                    sprite = FrankSprites.frank_walk4;//walking sprite
                    walkingNumber = -1 ;  //-1 because it will get incremented to 0 outside this
                    break;
            }
            walkingNumber++;
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
