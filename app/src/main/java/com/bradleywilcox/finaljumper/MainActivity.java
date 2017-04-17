package com.bradleywilcox.finaljumper;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap buffer = Bitmap.createBitmap(320, 480, Bitmap.Config.RGB_565);

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

}
