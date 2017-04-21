package com.bradleywilcox.finaljumper;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class World {
    public static final int GRAVITY = 100;

    private Hero hero;
    private ArrayList<Platform> platforms;
    private Score score;

    private int offsetPosition;

    public World(){
        hero = new Hero(160, 255);
        platforms = new ArrayList<>(25);
        score = new Score();

        generateWorld();

        offsetPosition = Game.BUFFER_HEIGHT / 4;
    }

    public void generateWorld(){
        int y = Game.BUFFER_HEIGHT;
        for(int i = 0; i < 25; i++){
            platforms.add(new Platform(140, y));
            y -= 100;
        }
    }

    public void update(float delta){
        hero.update(delta);

        if(hero.getVelocityY() > 0)
            for(Platform p : platforms){
                if(Collisions.isColliding(hero, p)) {
                    hero.hitPlatform();
                    break;
                }
            }

        if(hero.getHeroY() < offsetPosition)
            addWorldOffset(offsetPosition - hero.getHeroY());

        //InputHandler.reset();
    }

    public void render(Canvas canvas){
        hero.render(canvas);

        for(Platform p : platforms){
            p.render(canvas);
        }

        score.render(canvas);
    }

    public void addWorldOffset(float offset){
        for(Platform p : platforms){
            p.addOffsetY(offset);
        }

        hero.addOffsetY(offset);

        score.incrementScore(offset);
    }
}
