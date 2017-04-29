package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

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
    private RectF drawingRect;
    private boolean isMoving;
    private int direction;

    public Platform(int x, int y) {
        this.width = WIDTH;
        this.height = HEIGHT;

        paint = new Paint();
        paint.setColor(Color.WHITE);

        this.x = x;
        this.y = y;

        this.isActive = true;
        this.isMoving = false;
        this.direction = 30;
        drawingRect = new RectF(x, y, x + width, x + height);
    }

    @Override
    public void update(float delta){
        if(this.y > Game.BUFFER_HEIGHT)
            isActive = false;

        if(isActive && isMoving){
            if(this.x + this.width >= Game.BUFFER_WIDTH)
                direction = -30;
            else if(this.x <= 0)
                direction = 30;

            this.x += direction * delta;
        }
    }

    @Override
    public void render(Canvas canvas) {
        drawingRect.set(x - 7, y - 20, x + width + 7, y + height + 20);
        canvas.drawBitmap(Assets.cloud, null, drawingRect, null);
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public void setPosition(int x, int y, boolean isMoving){
        this.x = x;
        this.y = y;
        this.isActive = true;
        this.isMoving = isMoving;
    }

}