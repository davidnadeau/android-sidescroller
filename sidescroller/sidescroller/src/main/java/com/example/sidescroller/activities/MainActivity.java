package com.example.sidescroller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.example.sidescroller.R;
import com.example.sidescroller.fragments.LevelSelectFragment;
import com.example.sidescroller.game.buttons.OptionsFragment;

public class MainActivity extends Activity {

    public static int l=0;//level
    private boolean btnClicked = false,
            nestedBtnClicked   = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        enabledButtons();
        //close start screen fragment
        getFragmentManager().popBackStack();
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

    public void openLevelSelect(View v) {
        //avoid multiple btn clicks from running multiple fragments
        if (btnClicked) return;
        btnClicked = true;

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new LevelSelectFragment())
                .addToBackStack(null)
                .commit();
    }
    public void openOptions(View v) {
        //avoid multiple btn clicks from running multiple fragments
        if (btnClicked) return;
        btnClicked = true;

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new OptionsFragment())
                .addToBackStack(null)
                .commit();
    }
    public void startGame(View v) {
        //avoid multiple btn clicks from running multiple activities
        if (nestedBtnClicked) return;
        nestedBtnClicked = true;
        startActivity(new Intent(getApplicationContext(), GameActivity.class));
    }

    public void levelSelect(View view){
        boolean checked = ((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.levelOne:l=0;break;
            case R.id.levelTwo:l=1;break;
            case R.id.levelThree:l=2;break;


        };


    };


    public void quit(View v) { finish(); }

    private void enabledButtons() {
        btnClicked = false;
        nestedBtnClicked = false;
    }

}
