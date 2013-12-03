package com.example.sidescroller.game.graphics;

/**
 * Created by soote on 11/23/13.
 */
public class Sprite {
    private final int SIZE;
    private       int x, y;
    public int[] pixels;

    public Sprite(int size, int x, int y) {
        SIZE = size ;
        pixels = new int[SIZE * SIZE];

        this.x = x * SIZE;
        this.y = y * SIZE;
        load();
    }

    public Sprite(int size, int colour) {
        SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < SIZE * SIZE; i++)
            pixels[i] = colour;
    }
    private void load() {
        SpriteSheet sheet = new SpriteSheet();
        for (int y = 0; y < SIZE; y++)
            for (int x = 0; x < SIZE; x++)
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getWidth()];
    }

    public int getSize() { return SIZE; }

}