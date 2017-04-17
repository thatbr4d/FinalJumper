package com.bradleywilcox.finaljumper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Brad on 4/16/2017.
 */

public class Game extends SurfaceView implements Runnable {
    private Canvas gameCanvas;
    private Bitmap frameBuffer;
    private Thread gameThread = null;
    private SurfaceHolder holder;
    private volatile boolean running = false;
    private FPSCounter fps;

    private World world;

    public Game(Context context, Bitmap buffer) {
        super(context);
        frameBuffer = buffer;
        gameCanvas = new Canvas(frameBuffer);
        holder = getHolder();
        fps = new FPSCounter();

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
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        //background
        Rect bg = new Rect(0, 0, 320, 480);
        gameCanvas.drawRect(bg, paint);

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
