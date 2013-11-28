package com.example.sidescroller.graphics.peripherals;

import com.example.sidescroller.characters.Entity;
import com.example.sidescroller.characters.Frank;
import com.example.sidescroller.graphics.Screen;
import com.example.sidescroller.graphics.Sprite;
import com.example.sidescroller.graphics.level.Level;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb{

    private int   speed;
    private int startX, startY;
    protected Sprite sprite = PeripheralSprites.bomb;
    private boolean shooting = false;

    public Bomb() {}
    public Bomb(int speed) {
        this.speed = speed;
    }

    public void draw(Screen s, int x, int y) {
        s.drawPeripheral(x - 16, y - 16, sprite);
    }

    public void shoot(Screen s) {
        if (!isShooting()){
            return;
        }
        else{
            startX += 8; //increment bomb going to the right
            if(!collision(startX, startY)){ //if no collision and the bomb is not off the screen (miss)
                draw(s, startX, startY); //draw there
            }
            else{ //if there is a collision
                //explosion animation (startX, startY);
                shooting = false;
            }
        }
    }

    public boolean collision(int xa, int ya){
        return false;
    }

    public void setShooting(boolean shooting,int startX, int startY) {
        this.shooting = shooting;
        this.startX = startX; this.startY = startY;
    }

    public boolean isShooting() { return shooting; }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }
}