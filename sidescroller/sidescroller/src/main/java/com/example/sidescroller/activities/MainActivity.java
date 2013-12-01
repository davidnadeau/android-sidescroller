package com.example.sidescroller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.sidescroller.R;
import com.example.sidescroller.fragments.LevelSelectFragment;

public class MainActivity extends Activity {

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

    public void startGame(View v) {
        //avoid multiple btn clicks from running multiple activities
        if (nestedBtnClicked) return;
        nestedBtnClicked = true;

        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        i.putExtra("level", radioGroup.getCheckedRadioButtonId());
        startActivity(i);
    }

    public void volumeSelect(View view) {
        SeekBar bar = (SeekBar) findViewById(R.id.seekBar);
        int View = bar.getProgress();
        //music.setVolume(View);???
    }

    public void quit(View v) { finish(); }

    private void enabledButtons() {
        btnClicked = false;
        nestedBtnClicked = false;
    }

}
