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

public class Score extends GameObject {

    private int highscore;
    private int score;
    private Paint paint;

    public Score(){
        score = 0;
        this.x = 1;
        this.y = Game.BUFFER_HEIGHT / 4;

        paint = new Paint();
        paint.setColor(Color.GREEN);
    }


    @Override
    public void render(Canvas canvas){
        canvas.drawLine(x, y, x + 40, y + 1, paint);
        canvas.drawText("" + score, x + 5, y - 1, paint);
    }

    public void incrementScore(float score){
        this.score += score;
    }
}
