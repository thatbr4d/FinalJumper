package com.bradleywilcox.finaljumper;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.HashSet;
import java.util.Set;


public class SoundFiles {
    private static SoundPool soundPool;
    private static Set<Integer> soundsLoaded;
    private static int soundJumpId, soundHighScoreId, soundGameOverId;


    public static void loadSoundPool(Context context) {
        soundsLoaded = new HashSet<Integer>();

        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(2);
        soundPool = spBuilder.build();

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (status == 0) {
                    soundsLoaded.add(sampleId);
                } else {

                }
            }
        });

        soundJumpId = soundPool.load(context, R.raw.jump, 1);
        soundHighScoreId = soundPool.load(context, R.raw.tada, 1);
        soundGameOverId = soundPool.load(context, R.raw.lost, 1);
        // add other sounds
    }


    public static void playSound(int x) {
        if(x==1)
            soundPool.play(soundJumpId, 1.0f, 1.0f, 0, 0, 1.0f);
        else if(x==2)
            soundPool.play(soundHighScoreId, 1.0f, 1.0f, 0, 0, 1.0f);
        else if(x==3)
            soundPool.play(soundGameOverId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public static void clearSounds() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
            soundsLoaded.clear();
        }
    }
}
