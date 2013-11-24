package com.example.sidescroller;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.sidescroller.graphics.CreateLevel;
import com.example.sidescroller.graphics.Level;
import com.example.sidescroller.graphics.Screen;
import com.example.sidescroller.graphics.Surface;

/**
 * Created by Owner on 15/11/13.
 */
public class GameActivity extends Activity {

    private boolean btnClicked = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        Surface.setDimensions(width,height);
        setContentView(new Surface(this));

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