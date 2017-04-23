package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Platform extends GameObject {

    public static final int HEIGHT = 5;
    public static final int WIDTH = 40;

    private Paint paint;
    private boolean isActive;

    public Platform(int x, int y) {
        this.width = WIDTH;
        this.height = HEIGHT;

        paint = new Paint();
        paint.setColor(Color.WHITE);

        this.x = x;
        this.y = y;

        this.isActive = true;
    }

    @Override
    public void update(float delta){
        if(this.y > Game.BUFFER_HEIGHT)
            isActive = false;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        this.isActive = true;
    }

}