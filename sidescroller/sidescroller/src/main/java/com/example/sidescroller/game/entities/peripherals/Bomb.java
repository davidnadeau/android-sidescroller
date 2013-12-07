package com.example.sidescroller.game.entities.peripherals;

import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.Entity;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb extends Entity {

    private                double                      angle;
    private                boolean                     shooting;
    public static volatile ConcurrentLinkedQueue<Bomb> bombs;

    public Bomb() {
        sprite = PeripheralSprites.bomb;
        shooting = false;
    }

    public void shoot(Screen s) {
        if (!isShooting()) return;
        else {
            int xold = x, yold = y;
            x += (int) (sprite.getSize() * Math.cos(angle)); //increment bomb
            y += (int) (sprite.getSize() * Math.sin(angle)); //increment bomb
            int xdelta = x - xold >= sprite.getSize() ? sprite.getSize() : 0;
            int ydelta = y - yold >= sprite.getSize() ? sprite.getSize() : 0;

            if (!collision(xdelta, ydelta) && !collision_enemy(x, y, 32)) { //if no collision
                draw(); //draw there
            } else { //if there is a collision
                shooting = false;
                sprite = PeripheralSprites.explosion;
                draw();
                synchronized (bombs) {
                    bombs.remove(this);
                }
            }
        }
    }

    public void setShooting(boolean shooting, int x, int y, float touchX, float touchY) {
        this.shooting = shooting;
        this.x = x;
        this.y = y;

        angle = Math.atan2(touchY - y,
                touchX - x);
    }

    public boolean isShooting() { return shooting; }
}