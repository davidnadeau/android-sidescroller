package com.example.sidescroller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = (Button) findViewById(R.id.quit);//New button for quit
        button2.setOnClickListener(new View.OnClickListener() { //Set On Click Listener
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void startStartScreen(View v) {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new StartScreenFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    public void startOptions(View v) {
        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new OptionScreenFragment())
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
    public void goBack(View v) {
        getFragmentManager().popBackStack();
    }
}
