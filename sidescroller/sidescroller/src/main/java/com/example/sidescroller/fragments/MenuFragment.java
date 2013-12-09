package com.example.sidescroller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sidescroller.R;

/**
 * Created by Owner on 11/18/13.
 */
public class MenuFragment extends Fragment {
    public static  int    score, totalCoinCount, coinCount;
    private static String message;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_menu, container, false);
        //vw.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        //start the xml files using this class not by just setting content view

        TextView scoreLabel = (TextView) vw.findViewById(R.id.score);
        TextView msgLabel = (TextView) vw.findViewById(R.id.message);
        TextView coinLabel = (TextView) vw.findViewById(R.id.coins);
        scoreLabel.setText("" + score);
        msgLabel.setText("" + message);
        coinLabel.setText("Coins " + coinCount +" / "+ totalCoinCount);
        if (!message.equalsIgnoreCase("pause"))
            vw.findViewById(R.id.button).setEnabled(false);

        return vw;
    }
    public static void setScore(int frankScore) {
        score = frankScore;
    }
    public static void setCoins(int coinss, int totall) {
        coinCount = coinss;
        totalCoinCount = totall;
    }
    public static void setMessage(String msg) {
        message = msg;
    }
}
