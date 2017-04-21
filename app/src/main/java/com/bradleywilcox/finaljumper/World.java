package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class World {
    public static final int GRAVITY = 100;

    private Hero hero;
    private Platform platform;

    public World(){
        hero = new Hero(160, 25);
        platform = new Platform(100, 300);
    }

    public void update(float delta){
        hero.update(delta);

        if(Collisions.isColliding(hero, platform))
            hero.hitPlatform();

        //InputHandler.reset();
    }

    public void render(Canvas canvas){
        hero.render(canvas);
        platform.render(canvas);
    }
}
