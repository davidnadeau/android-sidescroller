package com.example.sidescroller.game.characters;

import android.util.Log;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by Owner on 18/11/13.
 */
public class Frank extends Entity {
    private int lives, score;
    private Sprite sprite;

    private boolean jumping = false;
    private boolean falling = false;
    private int     startY  = y;
    private int jumpHeight;

    public Frank(int x, int y) {
        this.x = x / 3;
        this.y = y / 2;
        jumpHeight = this.y - 64;
    }

    public void move() {
        if (jumping) {
            if (!collision(0, -16) && y >= jumpHeight) {
                y -= 16;
                x += 3;
                sprite = PlayerSprites.player_side;//jump sprite
            } else {
                jumping = false;
                falling = true;
                sprite = PlayerSprites.player_side;//fall sprite

            }
        } else if (falling) {
            if (!collision(0, 16)) {
                y += 16;
                x += 3;
                sprite = PlayerSprites.player_side;//fall sprite
            } else {
                falling = false;
                sprite = PlayerSprites.player_side;//land sprite
            }

        } else {
            sprite = PlayerSprites.player_side;//walking sprite
        }
    }

    /*
        xa: number of pixels moved on the x axis
        ya: number of pixels moved on the y axis
     */
    public boolean collision(int xa, int ya) {
        for (int i = 0; i < 4; i++) {
            //multiply to change left and top side
            int xt = ((x + xa) + i % 2 * 14 - 8) / 16;
            int yt = ((y + ya) + i / 2 * 10 + 2) / 16;
            if (jumping)
                Log.v("COLLISION", "" + xt + ": " + yt + " -- " + level.getTile(xt, yt).isSolid());
            if (level.getTile(xt, yt).isSolid()) return true;
        }
        return false;
    }

    public void draw(Screen s) { s.draw(x - 16, y - 16, sprite); }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
        startY = y;
        jumpHeight = startY - 64;
    }
    public boolean isJumping() { return jumping; }
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    public boolean isFalling() { return falling; }
    /**
     * Returns true if the object was hit
     */
    public boolean shoot() { return true; }

    public int getLives() { return lives; }
    public int getScore() { return score; }
    public boolean getJumping() {return jumping;}

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }


}
