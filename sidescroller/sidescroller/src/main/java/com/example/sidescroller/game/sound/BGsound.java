package com.example.sidescroller.game.sound;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;

import com.example.sidescroller.R;

import java.io.File;

/**
 * Created by fishkiller on 13-12-7.
 */
public class BGsound {

    private MediaPlayer player;
    private String      soundName;
    private boolean isPlaying = false;
    private File file;
    private boolean isExists = false;

    //not tested about the media name
    public BGsound(Context context) {
        player = new MediaPlayer();

        //player.create(context, R.raw.background1);

        Resources res = context.getResources();
        AssetFileDescriptor afd = res.openRawResourceFd(R.raw.background1);

        player.reset();
        player.setAudioStreamType(AudioManager.STREAM_ALARM);
        try{
        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.setVolume(0,0.2f);
        player.prepare();
        }catch (Exception e){};

    }

    public void play(boolean isLoop) {
        player.setLooping(isLoop);
        player.start();
    }

    public void pause() {
        player.pause();
    }

    public void stop() {
        player.stop();
        player.release();
    }

    public void setSoundName(String s) {
        this.soundName = s;
    }

    public boolean ifExists() {
        return isExists;
    }
}
