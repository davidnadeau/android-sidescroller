package com.example.sidescroller.characters;

import android.graphics.Point;

/**
 * Created by Owner on 18/11/13.
 */
public class Entity {
    protected int speed, direction;
    protected Point location;

    public int getSpeed() { return speed; }
    public int getDirection() { return direction; }
    public Point getLocation() { return location; }

    public void setSpeed(int speed) { this.speed = speed; }
    public void setDirection(int direction) { this.direction = direction; }
    public void setLocation(Point location) { this.location = location; }
}