package com.bradleywilcox.finaljumper;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class InputHandler {
    public static boolean IsEmulator;
    public static float touchX;
    public static float touchY;

    public static float accel;

    public static void reset(){
        accel = 0;
    }

    public static void resetTouch(){
        touchX = 0;
        touchY = 0;
    }
}
