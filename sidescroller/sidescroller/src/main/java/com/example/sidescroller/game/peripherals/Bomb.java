package com.example.sidescroller.game.peripherals;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.Tile;
import com.example.sidescroller.game.level.TileSprites;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb {

    private int startX, startY;
    double angle;
    private Sprite  sprite   = PeripheralSprites.bomb;
    private boolean shooting = false;
    private Level level;

    public Bomb() {}

    public void draw(Screen s, int x, int y) {
        s.draw(x - Tile.TILE_SIZE, y - Tile.TILE_SIZE, sprite);
    }

    public void shoot(Screen s) {
        if (!isShooting()) {
            return;
        } else {

            int tempX=startX,tempY=startY;
            startX += (int) (Tile.TILE_SIZE * Math.cos(angle)); //increment bomb going to the right (note: 8
            // is the speed)
            startY += (int) (Tile.TILE_SIZE * Math.sin(angle)); //increment bomb going up
            int x = startX-tempX>=Tile.TILE_SIZE ? Tile.TILE_SIZE:0;
            int y = startY-tempY>=Tile.TILE_SIZE ? Tile.TILE_SIZE:0;

            if (!collision(x,y)) { //if no collision and the bomb is not off the
                // screen (miss)
                draw(s, startX, startY); //draw there
            } else { //if there is a collision
                //explosion animation (startX, startY);
                shooting = false;
                sprite = TileSprites.errSprite;
            }
        }
    }

    public boolean collision(int xa, int ya) {
        int tileX = (startX+xa)/Tile.TILE_SIZE;
        int tileY = (startY+ya)/Tile.TILE_SIZE;
        if (level.getTile(tileX, tileY).isSolid())
            return true;
        return false;
    }

    public void setShooting(boolean shooting, int startX, int startY, float touchX, float touchY) {
        this.shooting = shooting;
        this.startX = startX;
        this.startY = startY;

        angle = Math.atan2(touchY - startY,
                touchX - startX);
    }

    public boolean isShooting() { return shooting; }
    public void setLevel(Level level) { this.level = level; }

}