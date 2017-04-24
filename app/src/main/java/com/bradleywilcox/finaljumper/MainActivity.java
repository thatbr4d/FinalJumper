package com.bradleywilcox.finaljumper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Game game;
    private Assets gameAssets;

    private SensorManager sensorManager;
    private Sensor accel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Data.context = getApplicationContext();
        Data.HighScore = Data.loadHighScore();

        Bitmap buffer = Bitmap.createBitmap(Game.BUFFER_WIDTH, Game.BUFFER_HEIGHT, Bitmap.Config.RGB_565);

        gameAssets = new Assets();
        gameAssets.heroJump = BitmapFactory.decodeResource(getResources(), R.drawable.jump);
        gameAssets.heroStand = BitmapFactory.decodeResource(getResources(), R.drawable.standing);

        sensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        game = new Game(this, buffer);

        setContentView(game);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float accelX = sensorEvent.values[0];
        if(accelX > .5) {
            InputHandler.moveLeft = true;
            InputHandler.moveRight = false;
        }else if (accelX < -.5){
            InputHandler.moveRight = true;
            InputHandler.moveLeft = false;
        }
    }


    /***
     *
     * Just for emulator testing purposes, actual device will use accelerometer
     *
     */
    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent){
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

        Data.saveHighScore();
        game.pause();
        SoundFiles.clearSounds();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        fullScreen();

        SoundFiles.loadSoundPool(getApplicationContext());
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_GAME);

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


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //TODO: add asset cleanup

}
