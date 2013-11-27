package com.example.sidescroller.graphics;

import com.example.sidescroller.graphics.level.Tile;

/**
 * Created by soote on 11/23/13.
 */
public class Screen {
    private int width, height;
    public int[] pixels;
    public int   xOffset, yOffset;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;

        pixels = new int[width * height];
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }


    public void drawTile(int xp, int yp, Tile tile) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < tile.sprite.getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < tile.sprite.getSize(); x++) {
                int xa = x + xp;
                if (xa < -tile.sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.getSize()];
            }
        }
    }
    public void drawFrank(int xp, int yp, Sprite sprite) {
        for (int y = 0; y < sprite.getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getSize(); x++) {
                int xa = x + xp;
                if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;
                if (xa < 0) xa = 0;
                int colour = sprite.pixels[x + y * sprite.getSize()];
                if (!isColorInRange(colour)) pixels[xa + ya * width] = colour;
            }
        }
    }
    public void drawPeripheral(int xp, int yp, Sprite sprite) {
        for (int y = 0; y < sprite.getSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getSize(); x++) {
                int xa = x + xp;
                if (xa < -sprite.getSize() || xa >= width || ya < 0 || ya >= height) break;

            }
        }
    }
    private boolean isColorInRange(int color) {
        if (color > 0xffdcdcd0 && color < 0xffdcdcdf) return true;
        return false;
    }
    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
}