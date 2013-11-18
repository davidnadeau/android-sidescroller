package com.example.sidescroller.peripherals;

import android.graphics.Point;
/**
 * Created by Owner on 18/11/13.
 */
public class Projectile {
    //img file
    private int speed;
    private Point start, end;

    public Projectile() {}
    public Projectile( int speed, Point start, Point end ) {
        this.speed = speed;
        this.start = start;
        this.end = end;
    }

    public int getSpeed() { return speed; }
    public Point getStart() { return start; }
    public Point getEnd() { return end; }

    public void setSpeed(int speed) { this.speed = speed; }
    public void setStart(Point start) { this.start = start; }
    public void setEnd(Point end) { this.end = end; } 
}