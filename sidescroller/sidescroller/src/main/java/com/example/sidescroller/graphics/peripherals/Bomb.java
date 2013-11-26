package com.example.sidescroller.graphics.peripherals;

import android.graphics.Point;

import com.example.sidescroller.characters.Entity;
import com.example.sidescroller.graphics.Screen;
import com.example.sidescroller.graphics.Sprite;
import com.example.sidescroller.graphics.level.TileSprites;

import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

/**
 * Created by Owner on 18/11/13.
 */
public class Bomb extends Entity {
    //img file
    private int   speed;
    private Point start, end;
    protected     Sprite sprite = PeripheralSprites.bomb;

    public Bomb() {}
    public Bomb(int speed, Point start, Point end) {
        this.speed = speed;
        this.start = start;
        this.end = end;
    }

    public void draw(Screen s, int x, int y) { s.drawFrank(x - 16, y - 16, sprite); }

    public int getSpeed() { return speed; }
    public Point getStart() { return start; }
    public Point getEnd() { return end; }
    public void setSpeed(int speed) { this.speed = speed; }
    public void setStart(Point start) { this.start = start; }
    public void setEnd(Point end) { this.end = end; }
}