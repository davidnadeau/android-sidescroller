package com.example.sidescroller.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sidescroller.characters.Frank;
import com.example.sidescroller.graphics.level.Level;
import com.example.sidescroller.graphics.peripherals.Bomb;

import java.util.LinkedList;

/**
 * Created by soote on 11/23/13.
 */
public class Surface extends SurfaceView implements
        SurfaceHolder.Callback {
    LinkedList <Bomb> bomb_list = new LinkedList();
    int[] pixels;
    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    private static final Bitmap.Config IMAGE_FORMAT = Bitmap.Config.ARGB_8888;
    Screen s;
    Level  l;
    Frank  frank;
    Bomb   bomb;
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
        frank = new Frank(GAME_WIDTH,GAME_HEIGHT);
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
        thread = new GameLoop(holder, this);
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
            int X=frank.getJumpX();
            int Y=frank.getJumpY();
            if(event.getX()>=X&&event.getX()<=X+16&&event.getY()>=Y&&event.getY()<=Y+16)
                frank.setJumping(true);
            else{
                bomb.setShooting(true, frank.getX(), frank.getY(), event.getX(), event.getY(), GAME_HEIGHT);
                bomb_list.add(bomb);
            }
            //TESTING JUMP -- this will be moved to button click when thats created
            //frank.setJumping(true);
        }
        return true;
    }






    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        l.draw(xScroll, 0, s);
        xScroll++; //scroll map to the left
        frank.draw(s);

        pixels = new int[GAME_WIDTH * GAME_HEIGHT];
        System.arraycopy(s.pixels, 0, pixels, 0, pixels.length);

        Bitmap bmp = newBitmap();
        fillBitmap(bmp);
        c.drawBitmap(bmp, 0, 0, null);
    }

    public void onDrawLoop(Canvas c) {
        super.onDraw(c);
        //l.draw(xScroll, 0, s);
        //xScroll++; //scroll map to the left
        frank.jump();
        frank.draw(s);

    //this for loop allows us to shoot more then 1 bomb at 1 time.. we have to render each one
        for(int i = 0; i < bomb_list.size(); i++){
            bomb_list.get(i).shoot(s);
        }

        //pixels = new int[GAME_WIDTH * GAME_HEIGHT];
        System.arraycopy(s.pixels, 0, pixels, 0, pixels.length);

        Bitmap bmp = newBitmap();
        fillBitmap(bmp);
        c.drawBitmap(bmp, 0, 0, null);
    }

    private Bitmap newBitmap() {
        return Bitmap.createBitmap(GAME_WIDTH, GAME_HEIGHT,
                IMAGE_FORMAT);
    }
    private void fillBitmap(Bitmap bmp) {
        bmp.setPixels(pixels, 0, GAME_WIDTH, 0, 0, GAME_WIDTH,
                GAME_HEIGHT);
    }
}
