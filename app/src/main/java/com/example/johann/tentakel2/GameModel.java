package com.example.johann.tentakel2;

import android.animation.TimeAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.sql.Time;
import java.util.Observable;
import java.util.Timer;

/**
 * Created by Johann on 12.05.2015.
 */

public class GameModel implements
        ValueAnimator.AnimatorUpdateListener,
        TimeAnimator.TimeListener {

    private int WindowHeight;
    private int WindowWidth;
    private Player player;
    private Player enemy;
    private MainActivity mainActivity;
    private boolean onCooldown = false;
    private boolean attackHit = false;
    public static float timeUnit = 500.0f;
    private float damage = 1;

    public GameModel(int WindowWidth, int WindowHeight, Player p, Player e, MainActivity m) {
        this.WindowHeight = WindowHeight;
        this.WindowWidth = WindowWidth;

        player = p;
        enemy = e;
        mainActivity = m;

        player.getAnimator().addUpdateListener(this);
        player.getCooldownTimer().setTimeListener(this);
       // enemy.getAnimator().addUpdateListener(this);

    }

    public void handleInput(MotionEvent e1, MotionEvent e2) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        //Falls der Spieler nicht am Blocken/Schlagen ist und kein cooldown
        if (player.getCurrentState() == Player.State.IDLE && !onCooldown) {
            //Angriff wird gestartet, wenn Strich laenger als 1/3 vom Bildschirm in x-Richtung
            if (distanceX > (float) WindowWidth / 3f) {
                player.getAnimator().setDuration((long) GameModel.timeUnit * 2);
                //Bestimmung ob Schlag oben/unten/beides
                if (distanceY < (float) WindowHeight / -4f) {
                    player.setCurrentState(Player.State.SCHLAG_OBEN);
                } else if (distanceY > (float) WindowHeight / 4f) {
                    player.setCurrentState(Player.State.SCHLAG_BEIDES);
                } else {
                    player.setCurrentState(Player.State.SCHLAG_BAUCH);
                }
            //Falls der Touch-Input kein Angriff war, muss es ein Block sein
            } else {
                player.getAnimator().setDuration((long) GameModel.timeUnit);
                if (e2.getY() < (float) WindowHeight / 3f) {
                    player.setCurrentState(Player.State.BLOCK_OBEN);
                } else if (e2.getY() > ((float) WindowHeight / 3f) * 2) {
                    player.setCurrentState(Player.State.BLOCK_BEIDES);
                } else {
                    player.setCurrentState(Player.State.BLOCK_BAUCH);
                }
            }
            player.getAnimator().start();
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float currentFraction = animation.getAnimatedFraction();
        player.getViewComponent().animate(currentFraction);

        //Falls animation beendet ist
        if (currentFraction == 1) {
            if (player.getCurrentState() == Player.State.BLOCK_BEIDES ||
                player.getCurrentState() == Player.State.SCHLAG_BEIDES) {
                player.getCooldownTimer().start();
                onCooldown = true;
            }
            player.setCurrentState(Player.State.IDLE);
            attackHit = false;
        }
        //Falls schlag eingetroffen ist
        else if (!attackHit && currentFraction >= 0.5 &&
                       (player.getCurrentState() == Player.State.SCHLAG_BAUCH ||
                        player.getCurrentState() == Player.State.SCHLAG_OBEN ||
                        player.getCurrentState() == Player.State.SCHLAG_BEIDES)) {

            //enemy.recieveAttack(player.getCurrentState(),
            //        player.getCurrentState() == Player.State.SCHLAG_BEIDES ? damage * 2 : damage);
            attackHit = true;
        }
    }

    @Override
    public void onTimeUpdate(TimeAnimator animation, long totalTime, long deltaTime) {
        //Cooldown zuende
        if (totalTime >= animation.getDuration()) {
            animation.end();
            onCooldown = false;
        }
    }
}
