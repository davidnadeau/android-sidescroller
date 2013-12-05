package com.example.sidescroller.game.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.example.sidescroller.R;

/**
 * Created by fishkiller on 13-12-2.
 */
public class Sounds {

    private final int soundSize = 3;  //0 is background,1 is jump,2 is bomb
    private SoundPool pool;
    private int       soundID[];

    public Sounds(Context c) {
        soundID = new int[soundSize];
        pool = new SoundPool(soundSize, AudioManager.STREAM_MUSIC, 1);

        soundID[1] = pool.load(c, R.raw.jump, 1);
        soundID[2] = pool.load(c, R.raw.bomb, 1);
    }

    public void pause(int index) {
        pool.pause(soundID[index]);
    }

    public int play(int index, boolean isLoop) {
        int loop = (isLoop) ? -1 : 0;
        return pool.play(soundID[index], 1, 1, 0, loop, 1);
    }

    public void release() {
        pool.release();
    }

    public void resume(int index) {
        pool.resume(soundID[index]);
    }

    public void setVolume(int index, float leftVolume, float rightVolume) {
        pool.setVolume(soundID[index], leftVolume, rightVolume);
    }

    public void stop(int index) {
        pool.stop(soundID[index]);
    }


}
