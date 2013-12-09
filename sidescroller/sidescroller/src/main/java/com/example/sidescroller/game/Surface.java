package com.example.sidescroller.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sidescroller.fragments.MenuFragment;
import com.example.sidescroller.game.buttons.JumpButton;
import com.example.sidescroller.game.buttons.MenuButton;
import com.example.sidescroller.game.entities.Entity;
import com.example.sidescroller.game.entities.coins.Coin;
import com.example.sidescroller.game.entities.enemies.FishEnemy;
import com.example.sidescroller.game.entities.enemies.SnailEnemy;
import com.example.sidescroller.game.entities.peripherals.Bomb;
import com.example.sidescroller.game.entities.player.Frank;
import com.example.sidescroller.game.graphics.SpriteSheet;
import com.example.sidescroller.game.level.Level;
import com.example.sidescroller.game.level.Tile;
import com.example.sidescroller.game.sound.BGsound;
import com.example.sidescroller.game.sound.Sounds;

import java.util.LinkedList;
import java.util.Random;
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

    private        int[]  pixels;
    private static int    GAME_WIDTH;
    private static int    GAME_HEIGHT;
    private static int    LEVEL_ID;
    private        int    scrollSpeed;
    private        String scoreText;
    int snailPosition; //used when displaying the enemies for the levels
    int fishPosition;
    private Screen     screen;
    private Level      level;
    private Frank      frank;
    private SnailEnemy snailEnemy;
    private FishEnemy  fishEnemy;
    private GameLoop   thread;
    private JumpButton jumpButton;
    private MenuButton menuButton;
    private Sounds     pool;
    private BGsound    bgMusic;
    private Paint      scoreFontStyle;
    private Bitmap     bmp;
    private Entity     enemy;
    private Bitmap     bg;
    private Paint mPaint = new Paint();
    private static int difficulty;
    private Random r = new Random();

    public static void setDimensions(int width, int height) {
        GAME_WIDTH = width;
        GAME_HEIGHT = height;
    }
    public static void setLevel(int id) { LEVEL_ID = id; }
    public static void setDifficulty(int number) { difficulty = number; }//1 for easy, 2 medium, 3 hard
    public int getLevel() { return LEVEL_ID; }
    public void resetLevel(Level l) { level = l; }
    public Screen getScreen() { return screen; }

    public Surface(Context c) {
        super(c);

        scoreFontStyle = new Paint();
        scoreFontStyle.setTextSize(40);
        bmp = newBitmap();

        SpriteSheet.setView(this);
        Level.setView(this);

        jumpButton = new JumpButton(GAME_WIDTH, GAME_HEIGHT);
        menuButton = new MenuButton(GAME_WIDTH, GAME_HEIGHT);
        screen = new Screen(GAME_WIDTH, GAME_HEIGHT);
        pixels = new int[GAME_WIDTH * GAME_HEIGHT];

        Level.coins = new ConcurrentLinkedQueue<Coin>();
        level = new Level(LEVEL_ID, screen, getResources());

        Entity.setScreen(screen);
        Entity.entities = new ConcurrentLinkedQueue<Entity>();
        Bomb.bombs = new ConcurrentLinkedQueue<Bomb>();

        scrollSpeed = Tile.TILE_SIZE;
        xScroll = -scrollSpeed;
        //game height is 720. that is not a multiple of 64 so we have to set franks y to be a
        // multiple
        frank = new Frank(GAME_WIDTH, 128);

        snailPosition = GAME_WIDTH;
        fishPosition = GAME_WIDTH;

        for (int i = 0; i < difficulty*15; i++) {
            int randomJumpHeight = r.nextInt(10)+3;//between 3 and 10
            int randomPositionSnail = r.nextInt(500)+275;
            int randomPositionFish = r.nextInt(550)+375;
            snailEnemy = new SnailEnemy(snailPosition, 128);
            fishEnemy = new FishEnemy(fishPosition, 128, randomJumpHeight);
            snailPosition += randomPositionSnail;
            fishPosition += randomPositionFish;
        }

        for (Entity e : Entity.entities) {
            e.setLevel(level);
        }

        pool = new Sounds(c);
        bgMusic = new BGsound(c);

        //Set thread
        getHolder().addCallback(this);
        setFocusable(true);
        bg = level.getBg();
        bgMusic.play(true);
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
        bgMusic.stop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            if (jumpButton.wasClicked(x, y)) {
                if (!frank.isJumping() && !frank.isFalling()) {
                    jumpButton.down();
                    frank.setJumping(true);
                    pool.play(1, false);
                }
            } else if (menuButton.wasClicked(x, y)) {
                thread.setRunning(false);
                MenuFragment.setScore(frank.getScore());
                ((Activity) getContext()).getFragmentManager()
                        .beginTransaction()
                        .replace(android.R.id.content, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
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

        screen.pixels = new int[GAME_WIDTH * GAME_HEIGHT];
        pixels = new int[GAME_WIDTH * GAME_HEIGHT];
        c.drawBitmap(bg, 0, 0, mPaint);

        xScroll += scrollSpeed; //scroll map to the left
        level.draw(xScroll, screen, c);
        jumpButton.draw(screen);
        menuButton.draw(screen);

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

        enemy = frank.enemyCollision(frank.toRect());
        if (Entity.entities.contains(enemy)) {
            handleDeath();
        }
        frank.coinCollision();

        if (frank.isOffScreen()) {
            handleDeath();
        }

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);
        fillBitmap(bmp, GAME_WIDTH, GAME_HEIGHT);
        c.drawBitmap(bmp, 0, 0, null);

        scoreText = Integer.toString(frank.getScore());
        c.drawText(scoreText, GAME_WIDTH - (scoreText.length() * 25), 50, scoreFontStyle);

        jumpButton.up();
        menuButton.up();
    }

    private void handleDeath() {
        //change sprite
        frank.die();
        int lives = frank.getLives();
        int score = frank.getScore();
        frank.setLives(lives - 1);
        frank.setScore((int) (score * .75));
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
    public void resetGame() {
        Entity.entities = new ConcurrentLinkedQueue<Entity>();

        scrollSpeed = Tile.TILE_SIZE;
        xScroll = -scrollSpeed;

        frank = new Frank(GAME_WIDTH, 128);


        snailPosition = GAME_WIDTH;
        fishPosition = GAME_WIDTH;
        for (int i = 0; i < 10; i++) {
            int randomJumpHeight = 3 + (int) (Math.random() * ((10 - 3) + 1));//between 3 and 10
            snailEnemy = new SnailEnemy(snailPosition, 128);
            fishEnemy = new FishEnemy(fishPosition, 128, randomJumpHeight);
            snailPosition += 400;
            fishPosition += 450;
        }

        for (Entity e : Entity.entities) {
            e.setLevel(level);
        }
    }

    private Bitmap newBitmap() {
        return Bitmap.createBitmap(GAME_WIDTH, GAME_HEIGHT,
                IMAGE_FORMAT);
    }
    private void fillBitmap(Bitmap bmp, int width, int height) {
        bmp.setPixels(pixels, 0, width, 0, 0, width,
                height);
    }
    public GameLoop getThread() {
        return thread;
    }
    public void setThread(GameLoop thread) {
        this.thread = thread;
    }
}
