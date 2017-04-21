package com.bradleywilcox.finaljumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Game extends SurfaceView implements Runnable {
    public static final int BUFFER_WIDTH = 320;
    public static final int BUFFER_HEIGHT = 480;

    private Canvas gameCanvas;
    private Bitmap frameBuffer;
    private Thread gameThread = null;
    private SurfaceHolder holder;
    private volatile boolean running = false;
    private FPSCounter fps;

    private World world;
    private Rect background;
    private Paint backgroundPaint;

    public Game(Context context, Bitmap buffer) {
        super(context);
        frameBuffer = buffer;
        gameCanvas = new Canvas(frameBuffer);
        holder = getHolder();
        fps = new FPSCounter();

        background = new Rect(0, 0, BUFFER_WIDTH, BUFFER_HEIGHT);
        backgroundPaint = new Paint(Color.BLACK);

        world = new World();
    }

    @Override
    public void run() {
        Rect rect = new Rect();
        long startTime = System.nanoTime();

        while (running) {
            if (!holder.getSurface().isValid())
                continue;

            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;

            startTime = System.nanoTime();

            update(deltaTime);
            render(deltaTime);


            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(frameBuffer, null, rect, null);
            holder.unlockCanvasAndPost(canvas);

            fps.logFrame();
        }
    }

    public void update(float deltaTime) {
        world.update(deltaTime);
    }

    public void render(float deltaTime) {
        //background
        gameCanvas.drawRect(background, backgroundPaint);

        //game world
        world.render(gameCanvas);
    }

    public void resume() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {

            }
        }
    }

}
