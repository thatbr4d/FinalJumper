package com.bradleywilcox.finaljumper;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Sounds {

    private SoundPool sounds;
    private SparseIntArray gamesounds = new SparseIntArray();


    public Sounds() {
        sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    }

    public void addSound(Context context, int soundID) {
        gamesounds.put(soundID, sounds.load(context, soundID, 1));
    }

    public void playSound(int soundID) {

        final int soundId = sounds.play(gamesounds.get(soundID), 1, 1, 1, 0, 1.0f);
    }

    public static void initStreamTypeMedia(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    public static int getStreamMusicLevel(Activity activity) {
        AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }
}
