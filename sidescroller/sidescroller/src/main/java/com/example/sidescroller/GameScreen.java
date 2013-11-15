package com.example.sidescroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Owner on 15/11/13.
 */
public class GameScreen extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
    }

    public void showMenu(View v){

    }
    public void resume(View v) {getFragmentManager().popBackStack();}
    public void restart(View v) {}//restart this activity at current level
    public void quit(View v) {startActivity(new Intent());}//start main activity
}