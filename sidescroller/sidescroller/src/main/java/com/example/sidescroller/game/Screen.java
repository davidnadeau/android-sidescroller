package com.example.sidescroller.game;

import com.example.sidescroller.game.graphics.Sprite;

/**
 * Created by soote on 11/23/13.
 */
public class Screen {
    private int width, height;
    public int[] pixels;
    public int   xOffset;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
    }

    public void draw(int xx, int yy, Sprite sprite) {
        for (int y = 0; y < sprite.getSize(); y++) {
            int ya = y + yy;
            for (int x = 0; x < sprite.getSize(); x++) {
                int xa = x + xx;
                if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int colour = sprite.pixels[x + y * sprite.getSize()];
                if (!isColorInRange(colour)) pixels[xa + ya * width] = colour;
            }
        }
    }

    private boolean isColorInRange(int color) {
        if (color > 0xffdcdcd0 && color < 0xffdcdcdf) return true;
        return false;
    }
    public void setOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}