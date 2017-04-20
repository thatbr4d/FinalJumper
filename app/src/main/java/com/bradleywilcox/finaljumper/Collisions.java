package com.bradleywilcox.finaljumper;

/**
 *
 * Brad Wilcox / Michael Cha
 * CSCI 4020 Final Project
 *
 */

public class Collisions {
    public static boolean isColliding(GameObject g1, GameObject g2){

        if(g1.lowerLeft().x < g2.lowerRight().x &&
                g1.lowerRight().x > g2.lowerLeft().x &&
                g1.lowerLeft().y < g2.upperLeft().y  &&
                g1.upperLeft().y > g2.lowerLeft().y)
            return true;


        return false;
    }
}
