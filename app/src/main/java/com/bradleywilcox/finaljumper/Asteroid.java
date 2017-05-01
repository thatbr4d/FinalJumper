package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Asteroid extends GameObject {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;

    private boolean isActive;
    private RectF drawingRect;
    private int direction;

    public Asteroid(int x, int y) {
        this.width = WIDTH;
        this.height = HEIGHT;

        this.x = x;
        this.y = y;

        this.isActive = false;
        this.direction = 50;
        drawingRect = new RectF(x, y, x + width, x + height);
    }

    @Override
    public void update(float delta){
        if(this.y > Game.BUFFER_HEIGHT)
            isActive = false;
        else if(this.y > -5)
            isActive = true;

        if(isActive)
            this.y += direction * delta;


    }

    @Override
    public void render(Canvas canvas) {
        drawingRect.set(x-5, y-5, x + width + 5, y + height + 5);
        canvas.drawBitmap(Assets.asteroid, null, drawingRect, null);
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
