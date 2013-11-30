package com.example.sidescroller.game.peripherals;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.graphics.PeripheralSprites;
import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb {

    private int startX, startY;
    double angle;
    private Sprite  sprite   = PeripheralSprites.bomb;
    private boolean shooting = false;

    public Bomb() {}

    public void draw(Screen s, int x, int y) {
        s.draw(x - 16, y - 16, sprite);
    }

    public void shoot(Screen s) {
        if (!isShooting()) {
            return;
        } else {

            startX += (int) (16 * Math.cos(angle)); //increment bomb going to the right (note: 8
            // is the speed)
            startY += (int) (16 * Math.sin(angle)); //increment bomb going up
            if (!collision(startX, startY)) { //if no collision and the bomb is not off the
                // screen (miss)
                draw(s, startX, startY); //draw there
            } else { //if there is a collision
                //explosion animation (startX, startY);
                shooting = false;
            }
        }
    }

    public boolean collision(int xa, int ya) {
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
}