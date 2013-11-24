package com.example.sidescroller.graphics.level;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.sidescroller.R;

/**
 * Created by soote on 11/23/13.
 */
public class CreateLevel extends Level {
    private static View v;
    public static void setView(View v1) { v = v1; }

    public CreateLevel() {
        super();
    }

    protected void loadLevel() {
        Bitmap bmp = loadBitmap(R.drawable.level);
        IMAGE_WIDTH = bmp.getWidth();
        IMAGE_HEIGHT = bmp.getHeight();
        tiles = new int[IMAGE_WIDTH * IMAGE_HEIGHT];

        //Convert bitmap into int array
        bmp.getPixels(tiles, 0, IMAGE_WIDTH, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
    }

    private Bitmap loadBitmap(int id) {
        return BitmapFactory.decodeResource(v.getResources(), id);
    }

    protected void generateLevel() {
    }
}