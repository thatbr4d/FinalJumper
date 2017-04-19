package com.bradleywilcox.finaljumper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;


/**
 * Created by Brad on 4/17/2017.
 */

public class Hero extends GameObject{

    private static final int JUMP_VELOCITY = -50;

    private Paint paint;
    private PointF velocity;
    private Bitmap character;



    public Hero(int x, int y) {
        this.width = 25;
        this.height = 25;
        paint = new Paint();
        paint.setColor(Color.RED);
        velocity = new PointF(0, 0);

        //setCharacter();
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

    /*private void setCharacter(){
        Drawable charDraw = getResources().getDrawable(R.drawable.jump);

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int sizePixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, dm);
        character = Bitmap.createBitmap(sizePixels, sizePixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(character);
        charDraw.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        charDraw.draw(canvas);

    }*/

}
