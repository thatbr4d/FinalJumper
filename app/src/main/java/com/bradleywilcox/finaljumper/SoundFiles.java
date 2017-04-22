package com.bradleywilcox.finaljumper;

import android.content.Context;

/**
 * Created by MichaelCha on 4/22/2017.
 */

public class SoundFiles {
    public static final int jump = R.raw.jump;
    public static final int highscore = R.raw.tada;


    public static final void SoundManager(Context context, Sounds sounds){
        sounds.addSound(context, jump);
        sounds.addSound(context, highscore);

    }

}
