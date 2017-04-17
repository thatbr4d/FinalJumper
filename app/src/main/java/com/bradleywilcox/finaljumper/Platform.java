package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Brad on 4/17/2017.
 */

public class Platform extends GameObject {

    private Paint paint;

    public Platform(int x, int y) {
        this.width = 100;
        this.height = 10;

        paint = new Paint();
        paint.setColor(Color.WHITE);

        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawRect(x, y, x + width, y + height, paint);
    }

}