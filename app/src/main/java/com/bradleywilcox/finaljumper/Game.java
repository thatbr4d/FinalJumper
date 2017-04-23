package com.bradleywilcox.finaljumper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Game extends SurfaceView implements Runnable {

    public enum GameState{
        RUNNING,
        GAME_OVER
    }

    public static final int BUFFER_WIDTH = 320;
    public static final int BUFFER_HEIGHT = 480;

    private Canvas gameCanvas;
    private Bitmap frameBuffer;
    private Thread gameThread = null;
    private SurfaceHolder holder;
    private volatile boolean running = false;
    private FPSCounter fps;
    private int counter = 0;

    private GameState gameState;
    private World world;
    private Rect background;
    private Paint backgroundPaint;

    private Paint tempPaint;

    private Context context;
    private ImageView imgPop;
    private TextView txtPopLose;
    private Button buttLose1, buttLose2;

    public Game(Context context, Bitmap buffer) {
        super(context);
        this.context = context;
        frameBuffer = buffer;
        gameCanvas = new Canvas(frameBuffer);
        holder = getHolder();
        fps = new FPSCounter();

        background = new Rect(0, 0, BUFFER_WIDTH, BUFFER_HEIGHT);
        backgroundPaint = new Paint(Color.BLACK);
        tempPaint = new Paint();
        tempPaint.setColor(Color.WHITE);

        gameState = GameState.RUNNING;
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
        if(gameState == GameState.RUNNING)
            world.update(deltaTime);

        if(world.isGameOver()) {
            gameState = GameState.GAME_OVER;
            Data.saveHighScore();
        }

    }

    public void render(float deltaTime) {
        //background
        gameCanvas.drawRect(background, backgroundPaint);

        //game world
        world.render(gameCanvas);

        // Temporary, would be nice to have a graphic with play/restart/quit buttons or something
        if (gameState == GameState.GAME_OVER) {
            gameCanvas.drawText("GAME OVER", 135, 240, tempPaint);
            gameLost(true);
            counter +=1;
        }
        else
            counter = 0;

    }

    public void gameLost(boolean state)
    {
        if(state==true && counter ==1)
        {
            SoundFiles.playSound(3);

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    runPopup2();
                }
            });
        }
    }

    public void runPopup2() {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.lose, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttLose1 = (Button) popupView.findViewById(R.id.buttonOver);
        buttLose2 = (Button) popupView.findViewById(R.id.buttonOver2);
        txtPopLose = (TextView) popupView.findViewById(R.id.textView3);
        popupWindow.showAtLocation(popupView, 150, 100, 0);

        buttLose1.setOnClickListener(new Button.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                popupWindow.dismiss();
            }
        });

        buttLose2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(context, Start.class);
                context.startActivity(intent);
                popupWindow.dismiss();
            }
        });
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
