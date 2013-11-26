package com.example.sidescroller.graphics;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by soote on 11/25/13.
 */
public class GameLoop extends Thread{

    //Frame speed
    long timeNow;
    long timePrevFrame = 0;
    long timeDelta;

    // Surface holder that can access the physical surface
    private SurfaceHolder surfaceHolder;
    // The actual view that handles inputs
    // and draws to the surface
    private Surface gamePanel;

    // flag to hold game state
    private boolean running;
    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameLoop(SurfaceHolder surfaceHolder, Surface gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void run() {
        Canvas c;
        c=null;
        if (!running)return;

        Log.v("Colorss","Start running");
        try {
            c = surfaceHolder.lockCanvas(null);
            synchronized (surfaceHolder) {
                Log.v("Colorss","got it, try to draw");
                //call methods to draw and process next fame
                gamePanel.onDraw(c);
            }
        } finally {
            if (c != null) {
                Log.v("Colorss","unlock the canvas and update?");

                surfaceHolder.unlockCanvasAndPost(c);
            }
        }
        while (running) {
            //limit frame rate to max 60fps
            timeNow = System.currentTimeMillis();
            timeDelta = timeNow - timePrevFrame;
            if ( timeDelta < 16) {
                try {
                    Thread.sleep(16 - timeDelta);
                }
                catch(InterruptedException e) {

                }
            }

            c = surfaceHolder.lockCanvas(null);
            synchronized (surfaceHolder) {
                //call methods to draw and process next fame
                //gamePanel.onDraw(c);
            }
        }
        if (c!=null) surfaceHolder.unlockCanvasAndPost(c);
    }
}