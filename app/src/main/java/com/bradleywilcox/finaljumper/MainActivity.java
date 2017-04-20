package com.bradleywilcox.finaljumper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class MainActivity extends AppCompatActivity {
    private Game game;
    private Assets gameAssets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap buffer = Bitmap.createBitmap(Game.BUFFER_WIDTH, Game.BUFFER_HEIGHT, Bitmap.Config.RGB_565);

        gameAssets = new Assets();
        gameAssets.heroJump = BitmapFactory.decodeResource(getResources(), R.drawable.jump);
        gameAssets.heroStand = BitmapFactory.decodeResource(getResources(), R.drawable.standing);

        game = new Game(this, buffer);

        setContentView(game);
    }


    @Override
    public void onPause(){
        super.onPause();
        game.pause();
    }

    @Override
    public void onResume(){
        super.onResume();
        game.resume();
    }

    //TODO: add asset cleanup

}
