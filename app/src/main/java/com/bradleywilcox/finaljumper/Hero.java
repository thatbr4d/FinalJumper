package com.bradleywilcox.finaljumper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;


/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Hero extends GameObject{

    private static final int JUMP_VELOCITY = -175;
    private static final int MOVE_VELOCITY = 50;

    private Paint paint;
    private PointF velocity;
    private RectF drawingRect;


    public Hero(int x, int y) {
        this.width = 30;
        this.height = 30;
        paint = new Paint();
        paint.setColor(Color.RED);
        velocity = new PointF(0, 0);

        this.x = x;
        this.y = y;

        drawingRect = new RectF(x, y, x + width, x + height);
    }

    @Override
    public void update(float delta) {
        if(InputHandler.moveLeft)
            velocity.x = -MOVE_VELOCITY;
        else if(InputHandler.moveRight)
            velocity.x = MOVE_VELOCITY;
        else
            velocity.x = 0;


        velocity.y += World.GRAVITY * delta;
        x += velocity.x * delta;
        y += velocity.y * delta;

        if(x > Game.BUFFER_WIDTH)
            x = 0;
        else if(x < 0)
            x = Game.BUFFER_WIDTH;


    }

    @Override
    public void render(Canvas canvas) {
        //canvas.drawRect(x, y, x + width, y + height, paint);

        drawingRect.set(x, y, x + width, y  + height);
        if(velocity.y < 0)
            canvas.drawBitmap(Assets.heroJump, null, drawingRect, null);
        else
            canvas.drawBitmap(Assets.heroStand, null, drawingRect, null);

    }

    public void hitPlatform() {
        velocity.y = JUMP_VELOCITY;
    }

    public float getVelocityY(){
        return this.velocity.y;
    }

    public float getHeroX(){
        return this.x;
    }

    public float getHeroY(){
        return this.y;
    }

}
