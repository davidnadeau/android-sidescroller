package com.example.sidescroller.graphics;

/**
 * Created by soote on 11/23/13.
 */
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.sidescroller.R;

/**
 *
 * @author         level = new SpawnLevel("/images/level1.png");
soote
 */
public class SpriteSheet {

    private final int IMAGE_WIDTH;
    private final int IMAGE_HEIGHT;

    public int[] pixels;

    public static SpriteSheet tiles = new SpriteSheet(388, 256);

    public SpriteSheet(int width, int height){
        IMAGE_WIDTH = width;
        IMAGE_HEIGHT = height;
        loadImage();
    }

    private void loadImage(){

        Bitmap bitmap = BitmapFactory.decodeResource(new Activity().getResources(), R.id.spritesheet);
        for (int x = 0; x < IMAGE_WIDTH; x++)
        for (int y = 0; y < IMAGE_HEIGHT; y++)
            pixels[x+y*IMAGE_WIDTH] = bitmap.getPixel(x,y);
    }

    public int getHeight(){
        return IMAGE_HEIGHT;
    }
    public int getWidth(){
        return IMAGE_WIDTH;
    }
}