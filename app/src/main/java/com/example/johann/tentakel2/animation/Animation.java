package com.example.johann.tentakel2.animation;

import android.view.View;

import com.example.johann.tentakel2.Player;

/**
 * Created by Johann on 12.06.2015.
 */
public abstract class Animation {
    
    public enum Animations {
        SCHLAG_OBEN,
        SCHLAG_BAUCH,
        BLOCK_OBEN,
        BLOCK_BAUCH
    }
    public static void animate(float currentFraction, Animations anim, View view, float imageWidth,
                                 float imageHeight, float viewOffsetX, float viewOffsetY){
        switch (anim) {
            case SCHLAG_OBEN:
                animateOben(view, currentFraction, imageWidth,
                imageHeight, viewOffsetX, viewOffsetY);
                break;
            case SCHLAG_BAUCH:
                animateBauch(view, currentFraction, imageWidth, viewOffsetX);
                break;
                
        }
    };


    
    private static void animateOben(View view, float currentFraction, float imageWidth,
                             float imageHeight, float viewOffsetX, float viewOffsetY) {
        float factor = 2;
        float newFraction = currentFraction * factor  / (float)Math.sqrt(2d);
        view.setRotation(-45f);
        if(currentFraction < 0.5) {
            view.setScaleX(1 + newFraction);
            view.setTranslationX(viewOffsetX + (newFraction * imageWidth) / 2);
            view.setScaleY((1 + newFraction * 0.4f));
            view.setTranslationY(viewOffsetY - ((newFraction * imageHeight) / 2) * 0.4f);
        } else {
            view.setScaleX((1 + factor) - newFraction);
            view.setTranslationX(viewOffsetX + (imageWidth * factor - newFraction * imageWidth) / 2);
            view.setScaleY(((1 + factor * 0.4f) - newFraction * 0.4f));
            view.setTranslationY(viewOffsetY + (newFraction * imageHeight - imageHeight * factor) / 2 * 0.4f);
        }
        if(currentFraction == 1) {
            view.setRotation(0f);
        }
    }
    
    private static void animateBauch(View view, float currentFraction, float imageWidth, float viewOffsetX){
        float factor = 2;
        float newFraction = currentFraction * factor;
        if(currentFraction < 0.5) {
            view.setScaleX(1 + newFraction);
            view.setTranslationX(viewOffsetX + (newFraction * imageWidth) / 2);
        }
        else {
            view.setScaleX((1 + factor) - newFraction);
            view.setTranslationX(viewOffsetX + (imageWidth * factor - newFraction * imageWidth) / 2);
        }
    }
}
