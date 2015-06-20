package com.example.johann.tentakel2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.Html;
import android.widget.ImageView;

import com.example.johann.tentakel2.animation.Animation;

import java.util.ArrayList;

/**
 * Created by Johann on 20.05.2015.
 */
public class ViewComponent {
    private ImageView arm;
    private ImageView armBack;
    private ImageView kopf;
    private ImageView brust;
    private ImageView beine;

    private int imageWidth;
    private int imageHeight;

    private int pivotX;
    private int pivotY;

    private int WindowWidth;
    private int WindowHeight;

    private Player parent;

    private boolean left;

    private int armOffsetX;
    private int armOffsetY;

    public ViewComponent(ImageView arm, ImageView armBack, ImageView kopf, ImageView brust, ImageView beine,
                         int WindowWidth, int WindowHeight, Resources resources, ArrayList<Number> ids, boolean left) {
        this.arm = arm;
        this.armBack = armBack;
        this.brust = brust;
        this.beine = beine;
        this.kopf = kopf;

        this.WindowWidth = WindowWidth;
        this.WindowHeight = WindowHeight;

        pivotX = 0;
        pivotY = 0;

        imageWidth = (int)(WindowWidth / 2.5);
        imageHeight = (int)(WindowHeight / 4);

        this.left = left;

        loadImages(resources, ids);
        positionAroundPivot();
    }

    public void setParent(Player parent) {this.parent = parent;}

    private void loadImages(Resources resources, ArrayList<Number> ids) {

        kopf.setImageBitmap(BitmapLoader.loadBitmap(resources, imageWidth, imageHeight, (int)ids.get(0)));
        kopf.getLayoutParams().width = imageWidth;
        kopf.getLayoutParams().height = imageHeight;

        brust.setImageBitmap(BitmapLoader.loadBitmap(resources, imageWidth, imageHeight, (int)ids.get(1)));
        brust.getLayoutParams().width = imageWidth;
        brust.getLayoutParams().height = imageHeight;

        arm.setImageBitmap(BitmapLoader.loadBitmap(resources, imageWidth, imageHeight, (int)ids.get(2)));
        arm.getLayoutParams().width = imageWidth;
        arm.getLayoutParams().height = imageHeight;

        armBack.setImageBitmap(BitmapLoader.loadBitmap(resources, imageWidth, imageHeight, (int)ids.get(2)));
        armBack.getLayoutParams().width = imageWidth;
        armBack.getLayoutParams().height = imageHeight;

        beine.setImageBitmap(BitmapLoader.loadBitmap(resources, imageWidth, imageHeight, (int)ids.get(3)));
        beine.getLayoutParams().width = imageWidth;
        beine.getLayoutParams().height = imageHeight;
    }

    //Von diesem Punkt aus werden die ImageViews positioniert
    public void setPivot(int x, int y) {
        this.pivotX = x;
        this.pivotY = y;

        positionAroundPivot();
    }

    public int getPivotX() {return this.pivotX;}
    public int getPivotY() {return this.pivotY;}

    public void positionAroundPivot() {

        kopf.setX(pivotX);
        kopf.setY(pivotY);

        brust.setX(pivotX);
        brust.setY(kopf.getY() + imageHeight - imageHeight / 5f);

        arm.setX(brust.getX() + (left ? imageWidth / 4 : -imageWidth / 4));
        arm.setY(brust.getY());

        armBack.setX(arm.getX() + (left ? imageWidth / 10 : -imageWidth / 10));
        armBack.setY(arm.getY());

        beine.setX(pivotX + (left ? imageWidth / 70 : - imageWidth / 70));
        beine.setY(pivotY + imageHeight * 2 - imageHeight / 3.2f);

        armOffsetX = (int)arm.getX();
        armOffsetY = (int)arm.getY();


    }

    public void animate(float currentFraction) {

        switch (parent.getCurrentState()) {
            case SCHLAG_BAUCH:
                Animation.animate(currentFraction, Animation.Animations.SCHLAG_BAUCH, arm,
                        imageWidth, imageHeight, armOffsetX, armOffsetY);
                break;
            case SCHLAG_OBEN:
                Animation.animate(currentFraction, Animation.Animations.SCHLAG_OBEN, arm,
                        imageWidth, imageHeight, armOffsetX, armOffsetY);
                break;
            case SCHLAG_BEIDES:
                Animation.animate(currentFraction, Animation.Animations.SCHLAG_BAUCH, arm,
                        imageWidth, imageHeight, armOffsetX, armOffsetY);
                Animation.animate(currentFraction, Animation.Animations.SCHLAG_OBEN, armBack,
                        imageWidth, imageHeight, armOffsetX, armOffsetY);
                break;
        }
    }

    public int getImageWidth() {return imageWidth;}
    public int getImageHeight() {return imageHeight;}
    public int getBeineWidth() {return imageWidth;}
    public int getBeineHeight() {return imageHeight;}

}
