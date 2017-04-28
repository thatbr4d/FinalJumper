package com.bradleywilcox.finaljumper;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class InputHandler {
    public static float touchX;
    public static float touchY;

    public static boolean moveLeft;
    public static boolean moveRight;

    public static void reset(){
        moveLeft = false;
        moveRight = false;
    }

    public static void resetTouch(){
        touchX = 0;
        touchY = 0;
    }
}
