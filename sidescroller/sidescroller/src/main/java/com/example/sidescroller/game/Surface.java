package com.example.sidescroller.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sidescroller.game.buttons.ButtonSprites;
import com.example.sidescroller.game.buttons.JumpButton;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.entities.coins.Coin;
import com.example.sidescroller.game.entities.enemies.FishEnemy;
import com.example.sidescroller.game.entities.enemies.SnailEnemy;
import com.example.sidescroller.game.entities.peripherals.Bomb;
import com.example.sidescroller.game.entities.player.Frank;
import com.example.sidescroller.game.graphics.SpriteSheet;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.Tile;
import com.example.sidescroller.game.sound.Sounds;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by soote on 11/23/13.
 */
public class Surface extends SurfaceView implements
        SurfaceHolder.Callback {

    //lets us delay method calls
    private static final ScheduledExecutorService worker       =
            Executors.newSingleThreadScheduledExecutor();
    private static final Bitmap.Config            IMAGE_FORMAT = Bitmap.Config.ARGB_8888;
    private int xScroll;

    private        int[] pixels;
    private static int   GAME_WIDTH;
    private static int   GAME_HEIGHT;
    private static int   LEVEL_ID;
    private        int   scrollSpeed;

    private Screen     screen;
    private Level      level;
    private Frank      frank;
    private SnailEnemy snailEnemy;
    private FishEnemy  fishEnemy;
    private GameLoop   thread;
    private JumpButton jumpButton;
    private Sounds     pool;
    private Paint      scoreFontStyle;

    public static void setDimensions(int width, int height) {
        GAME_WIDTH = width;
        GAME_HEIGHT = height;
    }
    public static void setLevel(int id) { LEVEL_ID = id; }

    public Surface(Context c) {
        super(c);

        scoreFontStyle = new Paint();
        scoreFontStyle.setTextSize(40);

        SpriteSheet.setView(this);
        Level.setView(this);

        jumpButton = new JumpButton(GAME_WIDTH, GAME_HEIGHT, ButtonSprites.jumpButton);
        screen = new Screen(GAME_WIDTH, GAME_HEIGHT);

        Level.coins = new ConcurrentLinkedQueue<Coin>();
        level = new Level(LEVEL_ID, screen);

        Entity.setScreen(screen);
        Entity.entities = new ConcurrentLinkedQueue<Entity>();
        Bomb.bombs = new ConcurrentLinkedQueue<Bomb>();

        scrollSpeed = Tile.TILE_SIZE;
        xScroll = -scrollSpeed;
        //game height is 720. that is not a multiple of 64 so we have to set franks y to be a
        // multiple
        frank = new Frank(GAME_WIDTH, 128);
        snailEnemy = new SnailEnemy(GAME_WIDTH, 128);
        fishEnemy = new FishEnemy(GAME_WIDTH, 128);

        for (Entity e : Entity.entities) {
            e.setLevel(level);
        }

        pool = new Sounds(c);

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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (jumpButton.wasClicked(x, y)) {
                if (!frank.isJumping() && !frank.isFalling()) {
                    frank.setJumping(true);
                    pool.play(1, false);
                }
            } else {
                Bomb bomb = new Bomb();
                bomb.setLevel(level);
                bomb.setShooting(true, frank.getX(), frank.getY(), event.getX(), event.getY());
                Bomb.bombs.add(bomb);
                pool.play(2, false);
            }
        }
        return true;
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        xScroll += scrollSpeed; //scroll map to the left
        level.draw(xScroll, 0, screen);
        jumpButton.draw(screen);

        //draw all the coins on the canvas
        for (Coin coin : Level.coins) {
            coin.setX(coin.getX() - scrollSpeed);
            coin.draw(coin.getX(), coin.getY(), screen);
        }
        //move and draw all our entities
        for (Entity e : Entity.entities) e.move();
        //this for loop allows us to shoot more then 1 bomb at 1 time.. we have to render each one
        //Make a copy of bomb before looping to allow removal of offscreen bombs.
        for (Bomb b : new LinkedList<Bomb>(Bomb.bombs)) {
            b.shoot(screen);
        }

        Entity enemy = frank.enemyCollision(frank.toRect());
        if (Entity.entities.contains(enemy)) {
            handleDeath();
        }
        frank.coinCollision();

        if (frank.isOffScreen()) {
            handleDeath();
        }
        
        pixels = new int[GAME_WIDTH * GAME_HEIGHT];
        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Bitmap bmp = newBitmap();
        fillBitmap(bmp);

        c.drawBitmap(bmp, 0, 0, null);

        String scoreText = Integer.toString(frank.getScore());
        c.drawText(scoreText, GAME_WIDTH - (scoreText.length() * 25), 50, scoreFontStyle);
    }

    private void handleDeath() {
        //change sprite
        frank.die();
        //play death sound

        //stop scrolling
        scrollSpeed = 0;
        Runnable task = new Runnable() {
            public void run() {
                resetGame();
            }
        };
        worker.schedule(task, 2, TimeUnit.SECONDS);
    }
    private void resetGame() {
        Entity.entities = new ConcurrentLinkedQueue<Entity>();

        scrollSpeed = Tile.TILE_SIZE;
        xScroll = -scrollSpeed;

        int lives = frank.getLives();
        int score = frank.getScore();
        frank = new Frank(GAME_WIDTH, 128);
        frank.setLives(lives - 1);
        frank.setScore((int) (score * .75));

        snailEnemy = new SnailEnemy(GAME_WIDTH, 128);
        fishEnemy = new FishEnemy(GAME_WIDTH, 128);

        for (Entity e : Entity.entities) {
            e.setLevel(level);
        }
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
