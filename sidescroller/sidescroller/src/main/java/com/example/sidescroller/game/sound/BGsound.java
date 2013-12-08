package com.example.sidescroller.game.sound;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;

import com.example.sidescroller.R;

import java.io.File;

/**
 * Created by fishkiller on 13-12-7.
 */
public class BGsound {

    private MediaPlayer player;
    private String soundName;
    private boolean isPlaying=false;
    private File file;
    private boolean isExists=false;

    //not tested about the media name
    public BGsound(Context context){
        this.soundName=soundName;
        player=new MediaPlayer();

       player.create(context,R.raw.background1);
        try{
        player.prepare();
        }catch (Exception e){

        };

    };

    public void play(boolean isLoop){
        player.setLooping(isLoop);
        player.start();
    };

    public void pause(){
        player.pause();
    };

    public void stop(){
        player.stop();
        player.release();
    };

    public void setSoundName(String s){
        this.soundName=s;
    };

    public boolean ifExists(){
        return isExists;
    };
}
