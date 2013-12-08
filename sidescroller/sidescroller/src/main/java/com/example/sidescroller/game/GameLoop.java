package com.example.sidescroller.game;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by soote on 11/25/13.
 */
public class GameLoop extends Thread {

    //Frame speed
    long timeNow;
    long timePrevFrame = 0;
    long timeDelta;

    // Surface holder that can access the physical surface
    private SurfaceHolder surfaceHolder;
    // The actual view that handles inputs
    // and draws to the surface
    private Surface       gamePanel;

    // flag to hold game state
    private volatile boolean running;
    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public GameLoop(SurfaceHolder surfaceHolder, Surface gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void run() {
        Canvas c;
        c = null;

        while (running) {
            //limit frame rate to max 60fps
//            timeNow = System.currentTimeMillis();
//            timeDelta = timeNow - timePrevFrame;
//            if (timeDelta < 16) {
//                try {
//                    Thread.sleep(16 - timeDelta);
//                } catch (InterruptedException e) {
//
//                }
//            }
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    //call methods to draw and process next fame
                    if (c == null) break;
                    gamePanel.onDraw(c);
                }
            } finally {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
}