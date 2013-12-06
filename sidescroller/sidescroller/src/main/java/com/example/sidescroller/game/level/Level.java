package com.example.sidescroller.game.level;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.sidescroller.R;
import com.example.sidescroller.game.Screen;

/**
 * Created by soote on 11/23/13.
 */
public class Level {
    private int IMAGE_WIDTH, IMAGE_HEIGHT;
    private int[] tiles;

    private static View v;
    public static void setView(View v1) { v = v1; }

    public Level(int id) {
        int levelID = 0;
        switch (id) {
            case R.id.level1:
                levelID = R.drawable.level1image;
                break;
            case R.id.level2:
                levelID = R.drawable.level2image;
                break;
            case R.id.level3:
                levelID = R.drawable.level3image;
                break;
        }
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
        int x0 = xScroll / Tile.TILE_SIZE;
        int x1 = (xScroll + s.getWidth() + Tile.TILE_SIZE) / Tile.TILE_SIZE;
        int y0 = yScroll / Tile.TILE_SIZE;
        int y1 = (yScroll + s.getHeight() + Tile.TILE_SIZE) / Tile.TILE_SIZE;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                //draw every tile to Screen
                getTile(x, y).draw(x, y, s);
            }
        }
    }

    public Tile getTile(int x, int y) {
        //if out of bounds
        if (x < 0 || y < 0 || x >= IMAGE_WIDTH || y >= IMAGE_HEIGHT)
            return Tile.errTile;

        //each int holds the colour of the pixel in ARGB format: aarrggbb
        switch (tiles[x + y * IMAGE_WIDTH]) {
            case 0xffffffff:
                return Tile.snowMid;
            case 0xff000000:
                return Tile.snowCenter;
            case 0xff00cccc: //baby blue
                return Tile.cloud;
            case 0xff8b4513: //brown
                return Tile.dirt;
            case 0xff147514: //green
                return Tile.grass;
            case 0xff0033ff: //dark blue
                return Tile.liquidWater;
            case 0xfffe00f0: //bright pink, put water underneath this tile to make it smooth
                return Tile.bridge;
            case 0xffff0000:
                return Tile.goldCoin;
            default:
                return Tile.castleWall;
        }
    }

    private Bitmap loadBitmap(int id) {
        return BitmapFactory.decodeResource(v.getResources(), id);
    }
}