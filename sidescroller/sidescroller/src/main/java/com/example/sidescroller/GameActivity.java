package com.example.sidescroller;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;

import com.example.sidescroller.graphics.SurfaceView;

/**
 * Created by Owner on 15/11/13.
 */
public class GameActivity extends Activity {

    private GLSurfaceView sv;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sv = new SurfaceView(this);
        setContentView(sv);
    }

    public void showMenu(View v) {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }
    public void resume(View v) { getFragmentManager().popBackStack(); }
    public void restart(View v) {}//restart this activity at current level
    public void quit(View v) { finish(); }//start main activity
}