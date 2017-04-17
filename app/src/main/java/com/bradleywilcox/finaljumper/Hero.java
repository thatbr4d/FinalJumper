package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * Created by Brad on 4/17/2017.
 */

public class Hero extends GameObject {

    private static final int JUMP_VELOCITY = -50;

    private Paint paint;
    private PointF velocity;

    public Hero(int x, int y) {
        this.width = 25;
        this.height = 25;
        paint = new Paint();
        paint.setColor(Color.RED);
        velocity = new PointF(0, 0);

        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {
        velocity.y += World.GRAVITY * delta;
        x += velocity.x * delta;
        y += velocity.y * delta;

    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public void hitPlatform() {
        velocity.y = JUMP_VELOCITY;
    }

}
