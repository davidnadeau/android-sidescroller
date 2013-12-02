package com.example.sidescroller.game.entities.peripherals;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.level.Tile;
import com.example.sidescroller.game.level.TileSprites;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb extends Entity {

    private int startX, startY, numOfExplosionAnimations = 0;
    double angle;

    private boolean shooting = false, explosion = false;

    public Bomb() { sprite = PeripheralSprites.bomb;}

    public void shoot(Screen s) {
        if (!isShooting()) {return;}
        else {
            int tempX = startX, tempY = startY;
            startX += (int) (sprite.getSize() * Math.cos(angle)); //increment bomb
            startY += (int) (sprite.getSize() * Math.sin(angle)); //increment bomb
            int x = startX - tempX >= sprite.getSize() ? sprite.getSize() : 0;
            int y = startY - tempY >= sprite.getSize() ? sprite.getSize() : 0;

            if (!collision(x, y)) { //if no collision
                draw(s, startX, startY); //draw there
            } else { //if there is a collision
                shooting = false;
                sprite = PeripheralSprites.explosion;
                draw(s, startX, startY);
            }
        }
    }

    public boolean collision(int xa, int ya){
        int tileX = (startX+xa)/Tile.TILE_SIZE;
        int tileY = (startY+ya)/Tile.TILE_SIZE;
        if (level.getTile(tileX, tileY).isSolid())
            return true;
        return false;
    }

    public void draw(Screen s, int x, int y) {
        s.draw(x, y, sprite);
    }

    public void setShooting(boolean shooting, int startX, int startY, float touchX, float touchY) {
        this.shooting = shooting;
        this.startX = startX;
        this.startY = startY;

        angle = Math.atan2(touchY - startY,
                touchX - startX);
    }

    public boolean isShooting() { return shooting; }

}