package com.example.sidescroller.game.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.sidescroller.R;

/**
 * Created by soote on 11/23/13.
 */
public class SpriteSheet {

    private final int IMAGE_WIDTH;
    private final int IMAGE_HEIGHT;

    private static View  v;
    public         int[] pixels;

    public static void setView(View v1) { v = v1; }

    public SpriteSheet() {
        Bitmap bmp = loadBitmap(R.drawable.spritesheet);
        IMAGE_WIDTH = bmp.getWidth();
        IMAGE_HEIGHT = bmp.getHeight();
        pixels = new int[IMAGE_WIDTH * IMAGE_HEIGHT];

        //Convert bitmap into int array
        bmp.getPixels(pixels, 0, IMAGE_WIDTH, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    private Bitmap loadBitmap(int id) {
        return BitmapFactory.decodeResource(v.getResources(), id);
    }

    public int getHeight() {
        return IMAGE_HEIGHT;
    }
    public int getWidth() {
        return IMAGE_WIDTH;
    }
}