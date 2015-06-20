package com.example.johann.tentakel2;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.TimeAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

/**
 * Created by Johann on 12.05.2015.
 */
public class Player {

    private boolean isHuman;
    private State currentState;
    private ValueAnimator animator;
    private TimeAnimator cooldownTimer;
    private float health;
    private boolean alive;
    private ViewComponent view;

    public enum State {
        IDLE,
        SCHLAG_OBEN,
        SCHLAG_BAUCH,
        SCHLAG_BEIDES,
        BLOCK_OBEN,
        BLOCK_BAUCH,
        BLOCK_BEIDES
    }

    public Player(boolean isHuman, ViewComponent view) {
        this.health = 10;
        this.alive = true;
        this.currentState = Player.State.IDLE;
        this.isHuman = isHuman;

        this.animator = ValueAnimator.ofFloat(0,1);
        this.animator.setDuration((long) (GameModel.timeUnit * 2));

        this.cooldownTimer = new TimeAnimator();
        this.cooldownTimer.setDuration((long) GameModel.timeUnit);
        this.cooldownTimer.setInterpolator(new LinearInterpolator());

        this.view = view;
        this.view.setParent(this);
    }

    public void setAnimator(ValueAnimator v) {
        this.animator = v;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public void setIsHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }

    public ValueAnimator getAnimator() {
        return animator;
    }

    public TimeAnimator getCooldownTimer() {
        return cooldownTimer;
    }

    public State getCurrentState() {
        return currentState;
    }

    public ViewComponent getViewComponent() {return view;}

    public void setCurrentState(State currentState) {

        this.currentState = currentState;
    }

    public void recieveAttack(State attack, float damage) {
        if(currentState != State.BLOCK_BEIDES) {
            if(currentState == State.BLOCK_BAUCH) {
                if (attack == State.SCHLAG_OBEN) {
                    health -= damage;
                }
                else if(attack == State.SCHLAG_BEIDES) {
                    health -= damage / 2;
                }
            }
            else if(currentState == State.BLOCK_OBEN) {
                if (attack == State.SCHLAG_BAUCH) {
                    health -= damage;
                }
                else if(attack == State.SCHLAG_BEIDES) {
                    health -= damage / 2;
                }
            }
            else {
                health -= damage;
            }
        }
        if (health <= 0) {
            alive = false;
       }
    }



    public boolean isAlive() {
        return alive;
    }

    public float getHealth() {
        return health;
    }
}
