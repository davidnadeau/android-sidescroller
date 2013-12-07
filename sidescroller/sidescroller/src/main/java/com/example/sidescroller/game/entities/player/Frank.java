package com.example.sidescroller.game.entities.player;

import android.graphics.Rect;
import android.util.Log;

import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.entities.coins.Coin;
import com.example.sidescroller.game.level.Level;

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
    public boolean frankIsDead = false;

    public Frank(int x, int y) {
        this.x = x / 4;
        this.y = y;
        score = 0;
        jumpHeight = this.y - 64;
        sprite = FrankSprites.frank_walk0;
        Entity.entities.add(this);
    }

    @Override
    public void move() {
        if (jumping && !collision_enemy(x, y, 64)) {
            if (!collision(0, -sprite.getSize()) && y >= jumpHeight) {
                sprite = FrankSprites.frank_jump;//jump sprite
                y -= sprite.getSize();
            } else {
                sprite = FrankSprites.frank_fall;//fall sprite
                jumping = false;
                falling = true;
            }
        } else if (falling && !collision_enemy(x, y, 64)) {
            if (!collision(0, sprite.getSize())) {
                sprite = FrankSprites.frank_fall;//fall sprite
                y += sprite.getSize();
            } else {
                sprite = FrankSprites.frank_land;//land sprite
                falling = false;
            }
        } else  if(collision_enemy(x, y, 64)){
            frankIsDead = true;

            //Entity.entities.remove(this);
        }else { //if hes not falling or hes not jumping and hes not dying, hes walking
            if(!collision(0, sprite.getSize())){//make sure hes not falling into a hole though
                falling = true;
                sprite = FrankSprites.frank_fall;
                y += sprite.getSize();
            } else{
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
        score+=50;
        coinCollision();
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
        startY = y;
        jumpHeight = startY - 128;
    }

    private void coinCollision() {
        LinkedList<Coin> mutableCoins = new LinkedList<Coin>(Level.coins);

        for (Coin c : mutableCoins)  {
            Rect frank = this.toRect();
            Rect coin = c.toRect();

            if (frank.intersect(coin)) {
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

    public int getLives() { return lives; }
    public int getScore() { return score; }

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
}
