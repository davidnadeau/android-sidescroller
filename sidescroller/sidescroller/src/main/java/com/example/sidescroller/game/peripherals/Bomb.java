package com.example.sidescroller.game.peripherals;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.Sprite;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.TileSprites;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb {

    private int startX, startY;
    double angle;
    private Sprite  sprite   = PeripheralSprites.bomb;
    private boolean shooting = false;
    protected Level level;

    public Bomb() {}

    public void draw(Screen s, int x, int y) {
        s.draw(x - 16, y - 16, sprite);
    }

    public void shoot(Screen s) {
        if (!isShooting()) {
            return;
        } else {

            int tempX=startX,tempY=startY;
            startX += (int) (16 * Math.cos(angle)); //increment bomb going to the right (note: 8
            // is the speed)
            startY += (int) (16 * Math.sin(angle)); //increment bomb going up
            int x = startX-tempX>=16 ? 16:0;
            int y = startY-tempY>=16 ? 16:0;

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
        int tileX = (startX+xa)/16;
        int tileY = (startY+ya)/16;
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