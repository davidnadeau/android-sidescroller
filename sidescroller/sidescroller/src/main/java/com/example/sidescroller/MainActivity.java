package com.example.sidescroller;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

        Button button0 = (Button) findViewById(R.id.start);//New button for start
        button0.setOnClickListener(new View.OnClickListener() { //Set On Click Listener
            public void onClick(View v) {
                setContentView(R.layout.start_fragment);
            }
        });
        Button button1 = (Button) findViewById(R.id.options);//New button for options
        button1.setOnClickListener(new View.OnClickListener() { //Set On Click Listener
            public void onClick(View v) {
                setContentView(R.layout.options_fragment);
            }
        });
        Button button2 = (Button) findViewById(R.id.quit);//New button for quit
        button2.setOnClickListener(new View.OnClickListener() { //Set On Click Listener
            public void onClick(View v) {
                finish();
            }
        });
    }

}
