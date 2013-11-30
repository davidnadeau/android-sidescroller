package com.example.sidescroller.game.characters;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by Owner on 18/11/13.
 */
public class Frank extends Entity {
    private int lives, score;
    private Sprite sprite;

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

        if (jumping) {
            if (!collision(0, -16) && y >= jumpHeight) {
                y -= 16;
                sprite = FrankSprites.frank_jump;//jump sprite
            } else {
                jumping = false;
                falling = true;
                sprite = FrankSprites.frank_fall;//fall sprite

            }
        } else if (falling) {
            if (!collision(0, 16)) {
                y += 16;
                sprite = FrankSprites.frank_fall;//fall sprite
            } else {
                falling = false;
                sprite = FrankSprites.frank_land;//land sprite
            }

        } else {
            sprite = FrankSprites.frank_walk;//walking sprite
        }
    }

    public boolean collision(int xa, int ya) {
        int tileX = (x+xa)/16;
        int tileY = (y+ya)/16;
        if (level.getTile(tileX, tileY).isSolid())
            return true;
        return false;
    }

    public void draw(Screen s) { s.draw(x - 16, y - 16, sprite); }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
        startY = y;
        jumpHeight = startY - 64;
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
