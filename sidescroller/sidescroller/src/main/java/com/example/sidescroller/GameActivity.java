package com.example.sidescroller;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;

import com.example.sidescroller.graphics.Sprite;
import com.example.sidescroller.graphics.Surface;
import com.example.sidescroller.levels.CreateLevel;
import com.example.sidescroller.levels.Level;

/**
 * Created by Owner on 15/11/13.
 */
public class GameActivity extends Activity {

    private boolean btnClicked = false;
    private Level level;
    private Surface s;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        s = new Surface(64,64);

        level = new CreateLevel(R.id.level);
        level.render(0, 0, s);
        Bitmap b = Bitmap.createBitmap(s.pixels, s.getWidth(), s.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
    }

    @Override
    public void onBackPressed() { super.onBackPressed(); enabledButtons(); }
    public void onBackPressed(View v) { super.onBackPressed(); enabledButtons(); }

    public void showMenu(View v) {
        //avoid multiple btn clicks from running multiple fragments
        if (btnClicked) return;
        btnClicked = true;

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }

    public void restart(View v) {}//restart this activity at current level
    public void quit(View v) { finish(); }//start main activity

    private void enabledButtons() { btnClicked = false; }
}