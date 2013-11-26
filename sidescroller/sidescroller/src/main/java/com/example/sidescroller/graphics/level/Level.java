package com.example.sidescroller.graphics.level;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;

import com.example.sidescroller.R;
import com.example.sidescroller.graphics.Screen;

/**
 * Created by soote on 11/23/13.
 */
public class Level {

    protected int IMAGE_WIDTH, IMAGE_HEIGHT;
    protected int[] tiles;

    private static View v;
    public static void setView(View v1) { v = v1; }

    public Level() {
        int levelID = R.drawable.level;
        loadLevel(levelID);
    }

    private void loadLevel(int id) {
        Bitmap bmp = loadBitmap(id);
        IMAGE_WIDTH = bmp.getWidth();
        IMAGE_HEIGHT = bmp.getHeight();
        tiles = new int[IMAGE_WIDTH * IMAGE_HEIGHT];

        //Convert bitmap into int array
        bmp.getPixels(tiles, 0, IMAGE_WIDTH, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    public void draw(int xScroll, int yScroll, Screen s) {
        s.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + s.getWidth() + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + s.getHeight() + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                //draw every tile to Screen
                //getTile(x, y).draw(x, y, s);-----------------------------------uncomment this line
            }
        }
    }

    public Tile getTile(int x, int y) {
        //if out of bounds
        if (x < 0 || y < 0 || x >= IMAGE_WIDTH || y >= IMAGE_HEIGHT)
            return Tile.waterTile;

        //each int holds the colour of the pixel in ARGB format: aarrggbb
        switch (tiles[x + y * IMAGE_WIDTH]) {
            case 0xff00ff00:
                return Tile.grass;
            case 0xffffff00:
                return Tile.flower;
            case 0xff808100:
                return Tile.rock;
            case 0xffdd33ff:
                return Tile.darkWood;
            case 0xff404040:
                return Tile.rainbowBrick;
            default:
                return Tile.waterTile;
        }
    }

    private Bitmap loadBitmap(int id) {
        return BitmapFactory.decodeResource(v.getResources(), id);
    }
}