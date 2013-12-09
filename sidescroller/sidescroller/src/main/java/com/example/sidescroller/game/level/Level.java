package com.example.sidescroller.game.level;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.sidescroller.R;
import com.example.sidescroller.game.Screen;
import com.example.sidescroller.game.entities.coins.Coin;
import com.example.sidescroller.game.entities.coins.Level1Coins;
import com.example.sidescroller.game.entities.coins.Level2Coins;
import com.example.sidescroller.game.entities.coins.Level3Coins;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by soote on 11/23/13.
 */
public class Level {
    private int IMAGE_WIDTH, IMAGE_HEIGHT;
    private       int[]                       tiles;
    private int                 DIFFICULTY = 1;
    public static ConcurrentLinkedQueue<Coin> coins;
    private Bitmap bmp    = Bitmap.createBitmap(Tile.TILE_SIZE, Tile.TILE_SIZE,
            Bitmap.Config.ARGB_8888);
    private Paint  mPaint = new Paint();
    private Tile   tile;
    private Bitmap bg;

    private static View v;
    public static void setView(View v1) { v = v1; }

    public Level(int id, int dif, Resources r) {
        int levelID = 0;
        switch (id) {
            case R.id.level1:
                levelID = R.drawable.level1image;
                new Level1Coins();
                bg = BitmapFactory.decodeResource(r, R.drawable.level1bg);
                break;
            case R.id.level2:
                levelID = R.drawable.level2image;
                new Level2Coins();
                bg = BitmapFactory.decodeResource(r, R.drawable.level2bg);
                break;
            case R.id.level3:
                levelID = R.drawable.level3image;
                new Level3Coins();
                bg = BitmapFactory.decodeResource(r, R.drawable.level1bg);
                break;
        }

        switch (dif) {
            case R.id.easy:
                DIFFICULTY = 1;
                break;
            case R.id.medium:
                DIFFICULTY = 2;
                break;
            case R.id.hard:
                DIFFICULTY = 3;
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

    public void draw(int xScroll, Screen s, Canvas c) {
        s.setOffset(xScroll);
        int x0 = xScroll / Tile.TILE_SIZE;
        int x1 = (xScroll + s.getWidth() + Tile.TILE_SIZE) / Tile.TILE_SIZE;
        int y0 = s.getHeight() / Tile.TILE_SIZE;

        for (int y = 0; y < y0; y++) {
            for (int x = x0; x < x1; x++) {
                //draw every tile to Screen
                //getTile(x, y).draw(x, y, s);
                tile = getTile(x, y);
                if (!tile.isBG()) {
                    bmp.setPixels(tile.sprite.pixels, 0, Tile.TILE_SIZE, 0, 0, Tile.TILE_SIZE,
                            Tile.TILE_SIZE);
                    c.drawBitmap(bmp,
                            x * Tile.TILE_SIZE - xScroll,
                            y * Tile.TILE_SIZE,
                            mPaint);
                }
            }
        }
    }
    public Bitmap getBg() { return bg; }
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
            case 0xffff8400: //orange
                return Tile.cloudLevel1;
            case 0xff00cccc: //baby blue
                return Tile.cloudLevel2;
            case 0xff8b4513: //brown
                return Tile.dirt;
            case 0xff147514: //green
                return Tile.grass;
            case 0xff0033ff: //dark blue
                return Tile.liquidWater;
            case 0xfffe00f0: //bright pink, put water underneath this tile to make it smooth
                return Tile.bridge;
            default:
                return Tile.castleWall;
        }
    }
    public int getDifficulty() { return DIFFICULTY; }
    private Bitmap loadBitmap(int id) {
        return BitmapFactory.decodeResource(v.getResources(), id);
    }
}