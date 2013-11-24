package com.example.sidescroller.graphics;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

/**
 * Created by soote on 11/23/13.
 */
public class Surface  extends View {
    int[] pixels;
    int width,height;
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;

    public static void setDimensions(int width, int height) { GAME_WIDTH=width;GAME_HEIGHT=height; }

    public Surface(Context c) {
        super(c);

        SpriteSheet.setView(this);
        CreateLevel.setView(this);

        Screen s = new Screen(GAME_WIDTH,GAME_HEIGHT);
        Level l = new CreateLevel();
        l.render(0, 0, s);

        width = s.getWidth();height = s.getHeight();
        pixels = new int[width*height];
        System.arraycopy(s.pixels, 0, pixels, 0, pixels.length);

    }


    @Override
    public void onDraw(Canvas c) {
        Paint p = new Paint();
        Bitmap bmp = newBitmap();
        fillBitmap(bmp);
        c.drawBitmap(bmp, 0, 0, p);
    }

    private Bitmap newBitmap() {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    private void fillBitmap(Bitmap bmp) {
        bmp.setPixels(pixels,0,width,0,0,width,height);
    }
}
