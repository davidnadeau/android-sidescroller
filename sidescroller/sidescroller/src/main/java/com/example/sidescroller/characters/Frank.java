package com.example.sidescroller.characters;

import com.example.sidescroller.graphics.Screen;
import com.example.sidescroller.graphics.Sprite;
import com.example.sidescroller.graphics.player.PlayerSprites;
import com.example.sidescroller.peripherals.Weapon;

/**
 * Created by Owner on 18/11/13.
 */
public class Frank extends Entity {
    private int lives, score;
    private   Weapon weapon;
    protected Sprite sprite = PlayerSprites.player_side;

    public Frank() {}
    public Frank(int lives, int score, Weapon weapon) {
        this.lives = lives;
        this.score = score;
        this.weapon = weapon;
    }

    public void move(int xa, int ya) {
        if(!collision(xa,ya)){
            y += ya;
        }
    }

    /**
     * return true if 1 pixel over in the set direction is a solid tile
     */
    private boolean collision(int xa,int ya){
        for(int i = 0; i<4; i++){
            //multiply to change left and top side
            int xt = ((getX() +xa)+ i % 2 * 14 - 8)>>4;
            int yt = ((getY() +ya)+ i / 2 * 10 + 2)>>4;
            if(level.getTile(xt, yt).isSolid())return true;
        }
        return false;
    }
    public void draw(Screen s){ s.drawFrank(x - 16, y - 16, sprite); }

    /**
     * Returns true if the object was hit
     */
    public boolean shoot() { return true; }

    public int getLives() { return lives; }
    public int getScore() { return score; }
    public Weapon getWeapon() { return weapon; }

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
}
