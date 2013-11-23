package com.example.sidescroller.levels;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * Created by soote on 11/23/13.
 */
public class CreateLevel extends Level{

    public CreateLevel(int level){
        super(level);
    }

    protected void loadLevel(int level){
        Bitmap bitmap = BitmapFactory.decodeResource(new Activity().getResources(), level);
        for (int x = 0; x < width; x++)
        for (int y = 0; y < height; y++)
            tiles[x+y*width] = bitmap.getPixel(x,y);
    }
    protected void generateLevel(){
    }
}