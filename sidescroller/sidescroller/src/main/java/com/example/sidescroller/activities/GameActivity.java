package com.example.sidescroller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.sidescroller.game.GameLoop;
import com.example.sidescroller.game.Surface;
import com.example.sidescroller.game.entities.coins.Coin;
import com.example.sidescroller.game.entities.peripherals.Bomb;
import com.example.sidescroller.game.level.Level;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Owner on 15/11/13.
 */
public class GameActivity extends Activity {

    private Surface surface;
    private boolean btnClicked = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Surface.setDimensions(width, height);
        Surface.setLevel(b.getInt("level"));
        surface = new Surface(this);
        setContentView(surface);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        enabledButtons();
    }
    public void onBackPressed(View v) {
        super.onBackPressed();
        surface.setThread(new GameLoop(surface.getHolder(), surface));
        // at this point the surface is created and
        // we can safely start the game loop
        surface.getThread().setRunning(true);
        surface.getThread().start();
    }
    public void restart(View v) {
        Bomb.bombs.clear();
        Level.coins = new ConcurrentLinkedQueue<Coin>();
        surface.resetLevel(new Level(surface.getLevel(), surface.getScreen(), getResources()));
        surface.resetGame();
        onBackPressed(v);

    }

    public void quit(View v) { finish(); }//start main activity

    private void enabledButtons() { btnClicked = false; }
}