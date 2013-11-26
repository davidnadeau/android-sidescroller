package com.example.sidescroller.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sidescroller.characters.Frank;
import com.example.sidescroller.graphics.level.Level;
import com.example.sidescroller.graphics.peripherals.Bomb;

/**
 * Created by soote on 11/23/13.
 */
public class Surface extends SurfaceView implements
        SurfaceHolder.Callback{

    int[] pixels;
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private static final Bitmap.Config IMAGE_FORMAT = Bitmap.Config.ARGB_8888;
    Screen s;
    Level l;
    Frank frank;
    Bomb bomb;
    int xScroll = 0;
    GameLoop thread;
    public static void setDimensions(int width, int height) {
        GAME_WIDTH = width;
        GAME_HEIGHT = height;
    }

    public Surface(Context c) {
        super(c);

        SpriteSheet.setView(this);
        Level.setView(this);

        s = new Screen(GAME_WIDTH, GAME_HEIGHT);
        l = new Level();
        frank = new Frank();

        frank.setLevel(l);
        frank.setX(GAME_WIDTH / 2);
        frank.setY(GAME_HEIGHT / 2);

        //Set thread
        getHolder().addCallback(this);

        setFocusable(true);

    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new GameLoop(holder,this);
        // at this point the surface is created and
        // we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // tell the thread to shut down and wait for it to finish
        // this is a clean shutdown
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        bomb = new Bomb();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("BOMB","before bomb"+Math.round(event.getX())+ "," + Math.round(event.getY()));
            bomb.draw(s, Math.round(event.getX()), Math.round(event.getY()));
            Log.d("BOMB","after bomb");
        }
        return true;
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        Log.v("Colorss","Drawing");

        l.draw(xScroll, 0, s);
        xScroll++; //scroll map to the left
        frank.draw(s);

        pixels = new int[GAME_WIDTH * GAME_HEIGHT];
        System.arraycopy(s.pixels, 0, pixels, 0, pixels.length);

        Bitmap bmp = newBitmap();
        fillBitmap(bmp);
        c.drawBitmap(bmp, 0, 0, null);
        Log.v("Colorss","drawing complete");
    }

    private Bitmap newBitmap() { return Bitmap.createBitmap(GAME_WIDTH, GAME_HEIGHT, IMAGE_FORMAT); }
    private void fillBitmap(Bitmap bmp) { bmp.setPixels(pixels, 0, GAME_WIDTH, 0, 0, GAME_WIDTH, GAME_HEIGHT); }
}
