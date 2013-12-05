package com.example.sidescroller.game.entities.peripherals;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb extends Entity {

    private int startX, startY, numOfExplosionAnimations = 0;
    double angle;

    private boolean shooting = false, explosion = false;

    public Bomb() {
        sprite = PeripheralSprites.bomb;
        Entity.entities.add(this);
    }

    public void shoot(Screen s) {
        if (!isShooting()) return;
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

    public void setShooting(boolean shooting, int startX, int startY, float touchX, float touchY) {
        this.shooting = shooting;
        this.startX = startX;
        this.startY = startY;

        angle = Math.atan2(touchY - startY,
                touchX - startX);
    }

    public boolean isShooting() { return shooting; }

}