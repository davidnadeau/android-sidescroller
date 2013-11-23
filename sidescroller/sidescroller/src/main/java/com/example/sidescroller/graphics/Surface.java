package com.example.sidescroller.graphics;

import com.example.sidescroller.levels.Tile;

import java.util.Random;

/**
 * Created by soote on 11/23/13.
 */
public class Surface {
    private int width, height;
    public int[] pixels;
    public int xOffset, yOffset;
    public final int MAP_SIZE = 64;
    public int[] tiles = new int[MAP_SIZE*MAP_SIZE];

    private Random random = new Random();
    public Surface(int width,int height){
        this.width = width;
        this.height = height;

        pixels = new int[width*height];

        for (int i = 0; i < width; i++)
            tiles[i]=random.nextInt(0xffffff);
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = 0;
    }


    public void drawTile(int xp, int yp, Tile tile){
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y<tile.sprite.SIZE; y++){
            int ya = y + yp;
            for (int x= 0; x<tile.sprite.SIZE; x++){
                int xa = x + xp;
                if(xa < -tile.sprite.SIZE||xa>=width||ya<0||ya>=height)break;
                if(xa<0)xa=0;
                pixels[xa+ya*width] = tile.sprite.pixels[x+y*tile.sprite.SIZE];            }
        }
    }

    private boolean isColorInRange(int color){
        if(color>0xffdcdcd0 && color<0xffdcdcdf)return true;
        return false;
    }

    public void setOffset(int xOffset, int yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}