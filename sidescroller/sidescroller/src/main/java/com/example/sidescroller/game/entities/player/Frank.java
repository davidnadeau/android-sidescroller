package com.example.sidescroller.game.entities.player;

import android.graphics.Rect;

import com.example.sidescroller.game.Surface;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.entities.coins.Coin;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.TileSprites;

import java.util.LinkedList;

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
        score = 0;
        lives = 3;
        jumpHeight = this.y - 64;
        sprite = FrankSprites.frank_walk0;
        Entity.entities.add(this);
    }

    @Override
    public void move() {
        if (isDead()) {
            draw();
            return;
        }
        if (jumping) {
            if (!tileCollision(0, -sprite.getSize()) && y >= jumpHeight) {
                sprite = FrankSprites.frank_jump;//jump sprite
                y -= sprite.getSize();
            } else {
                sprite = FrankSprites.frank_fall;//fall sprite
                jumping = false;
                falling = true;
            }
        } else if (falling) {
            if (!tileCollision(0, sprite.getSize())) {
                sprite = FrankSprites.frank_fall;//fall sprite
                y += sprite.getSize();
            } else {
                sprite = FrankSprites.frank_land;//land sprite
                falling = false;
            }
        } else { //if hes not falling or hes not jumping and hes not dying, hes walking
            if (!tileCollision(0, sprite.getSize())) {//make sure hes not falling into a hole though
                falling = true;
                sprite = FrankSprites.frank_fall;
                y += sprite.getSize();
            } else {
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
                        walkingNumber = -1;  //-1 because it will get incremented to 0 outside this
                        break;
                }
                walkingNumber++;
            }
        }
        draw();
        score += 50;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
        startY = y;
        jumpHeight = startY - 128;
    }

    public void coinCollision() {
        LinkedList<Coin> mutableCoins = new LinkedList<Coin>(Level.coins);

        Rect frank = this.toRect();
        for (Coin c : mutableCoins) {
            Rect coin = c.toRect();

            if (frank.intersect(coin)) {
                Surface.pool.play(0,false);
                Level.coins.remove(c);
                score += c.getValue();
            }
        }
    }

    public boolean isJumping() { return jumping; }
    public boolean isFalling() { return falling; }

    /**
     * Returns true if the object was hit
     */
    public boolean shoot() { return true; }
    public void die() {
        sprite = FrankSprites.frank_die;
    }
    public boolean isDead() {
        return sprite.getX() / sprite.getSize() == 5
                && sprite.getY() / sprite.getSize() == 4;
    }
    public int getLives() { return lives; }
    public int getScore() { return score; }

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
}
