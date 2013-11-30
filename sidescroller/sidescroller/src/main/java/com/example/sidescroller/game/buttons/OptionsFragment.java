package com.example.sidescroller.game.buttons;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sidescroller.R;

/**
 * Created by Owner on 15/11/13.
 */
public class OptionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vw = inflater.inflate(R.layout.fragment_options, container, false);
        vw.setBackgroundColor(getResources().getColor(android.R.color.background_light));
        //start the xml files using this class not by just setting content view
        return vw;
    }
}