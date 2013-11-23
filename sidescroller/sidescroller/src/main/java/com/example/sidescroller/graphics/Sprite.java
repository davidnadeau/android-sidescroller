package com.example.sidescroller.graphics;

/**
 * Created by soote on 11/23/13.
 */
public class Sprite {

    public final int SIZE;
    private int x,y;
    public int[] pixels;
    private SpriteSheet sheet;

    public Sprite(int size, int x,int y,SpriteSheet sheet) {
        SIZE = size;

        pixels = new int[SIZE*SIZE];
        this.x=x*SIZE;
        this.y=y*SIZE;
        this.sheet=sheet;

        for (int xx = 0; xx < SIZE; xx++)
        for (int yy = 0; yy < SIZE; yy++)
            pixels[xx+yy*SIZE] = sheet.pixels[(xx+this.x)+(yy+this.y)*sheet.getWidth()];
    }

    public Sprite(int size, int colour) {
        SIZE = size;
        pixels = new int[SIZE*SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = colour;
    }

}