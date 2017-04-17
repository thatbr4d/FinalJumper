package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;

/**
 * Created by Brad on 4/17/2017.
 */

public class World {
    public static final int GRAVITY = 30;

    private Hero hero;
    private Platform platform;

    public World(){
        hero = new Hero(160, 25);
        platform = new Platform(100, 300);
    }

    public void update(float delta){
        hero.update(delta);

    }

    public void render(Canvas canvas){
        hero.render(canvas);
        platform.render(canvas);
    }
}
