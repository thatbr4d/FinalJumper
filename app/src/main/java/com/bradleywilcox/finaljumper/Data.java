package com.bradleywilcox.finaljumper;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Data {
    public static Context context;
    private static final String DATA = "data";
    public static float HighScore;

    public static void saveHighScore(){
        SharedPreferences settings = context.getSharedPreferences(DATA, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("HighScoreF", HighScore);

        editor.commit();
    }

    public static float loadHighScore(){
        SharedPreferences settings = context.getSharedPreferences(DATA, MODE_PRIVATE);
        return settings.getFloat("HighScoreF", 0);
    }
}
