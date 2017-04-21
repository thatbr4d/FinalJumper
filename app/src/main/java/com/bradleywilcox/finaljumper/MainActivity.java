package com.bradleywilcox.finaljumper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 */

public class MainActivity extends AppCompatActivity {
    private Game game;
    private Assets gameAssets;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bitmap buffer = Bitmap.createBitmap(Game.BUFFER_WIDTH, Game.BUFFER_HEIGHT, Bitmap.Config.RGB_565);

        gameAssets = new Assets();
        gameAssets.heroJump = BitmapFactory.decodeResource(getResources(), R.drawable.jump);
        gameAssets.heroStand = BitmapFactory.decodeResource(getResources(), R.drawable.standing);

        game = new Game(this, buffer);

        setContentView(game);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /***
     *
     * Just for emulator testing purposes, actual device will use accelerometer
     *
     */
    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent){
        Log.i("UP", "UP");
        if (keyEvent.getAction() == KeyEvent.ACTION_UP) {

            switch(i){
                case KeyEvent.KEYCODE_A:
                    InputHandler.moveLeft = true;
                    InputHandler.moveRight = false;
                    return true;
                case KeyEvent.KEYCODE_D:
                    InputHandler.moveRight = true;
                    InputHandler.moveLeft = false;
                    return true;
                default:
                    super.onKeyUp(i, keyEvent);
            }

        }
        return false;
    }


    @Override
    public void onPause() {
        super.onPause();
        game.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        fullScreen();
        game.resume();
    }

    private void fullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    //TODO: add asset cleanup

}
