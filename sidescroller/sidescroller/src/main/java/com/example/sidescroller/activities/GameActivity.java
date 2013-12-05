package com.example.sidescroller.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.sidescroller.fragments.MenuFragment;
import com.example.sidescroller.game.Surface;

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
        enabledButtons();
    }

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