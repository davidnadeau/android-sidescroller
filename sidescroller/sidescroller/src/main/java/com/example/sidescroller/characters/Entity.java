package com.example.sidescroller.characters;

import android.graphics.Point;

/**
 * Created by Owner on 14/11/13.
 */
public class Entity {
    private int speed, direction;
    private Point location;


    /*
     * Returns true/false based on whether there was a collision or not
     */
    protected boolean move(){
     return true;
    }

    public int getSpeed() { return speed; }
    public int getDirection() { return direction; }
    public Point getLocation() { return location; }

    public void setSpeed(int speed) { this.speed = speed; }
    public void setDirection(int direction) { this.direction = direction; }
    public void setLocation(Point location) { this.location = location; }
}