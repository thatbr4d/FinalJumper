package com.bradleywilcox.finaljumper;

import android.util.Log;

/**
 * Created by Brad on 4/16/2017.
 */

public class FPSCounter {
    long startTime = System.nanoTime();
    int frames = 0;

    public void logFrame(){
        frames++;
        if(System.nanoTime() - startTime >= 1000000000.0f){
            Log.i("FPS: ", frames + "");
            frames = 0;
            startTime = System.nanoTime();
        }
    }
}

