package com.example.sidescroller.characters;

import com.example.sidescroller.graphics.Screen;
import com.example.sidescroller.graphics.Sprite;
import com.example.sidescroller.graphics.peripherals.Weapon;
import com.example.sidescroller.graphics.player.PlayerSprites;

/**
 * Created by Owner on 18/11/13.
 */
public class Frank extends Entity {
    private int lives, score;
    private Weapon weapon;
    protected Sprite  sprite  = PlayerSprites.player_side;
    private   boolean jumping = false;
    private   int     startY  = y;

    public Frank() {}
    public Frank(int lives, int score, Weapon weapon) {
        this.lives = lives;
        this.score = score;
        this.weapon = weapon;
    }

    public void move(int xa, int ya) { }

    /*
        when the user clicks the jump button, set jumping to true.
     */
    public void jump() {
        if (!isJumping()) return;
        else if (y < startY - 64) jumping = false;
        //change x to make jump visible, remove this when using real android device
        if (!collision(0, -16)) {
            y -= 16;
            x++;
        }
    }


    /**
     *
     */
    private boolean collision(int xa, int ya) {
        for (int i = 0; i < 4; i++) {
            //multiply to change left and top side
            int xt = ((getX() + xa) + i % 2 * 14 - 8) >> 4;
            int yt = ((getY() + ya) + i / 2 * 10 + 2) >> 4;
            if (level.getTile(xt, yt).isSolid()) return true;
        }
        return false;
    }
    public void draw(Screen s) {
        if (!collision(0, 16) && !isJumping()) y += 16;
        s.drawFrank(x - 16, y - 16, sprite);
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
        startY = y;
    }
    public boolean isJumping() { return jumping; }
    /**
     * Returns true if the object was hit
     */
    public boolean shoot() { return true; }

    public int getLives() { return lives; }
    public int getScore() { return score; }
    public Weapon getWeapon() { return weapon; }
    public boolean getJumping() {return jumping;}

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

}
