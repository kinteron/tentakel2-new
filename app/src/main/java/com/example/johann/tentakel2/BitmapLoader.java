package com.example.johann.tentakel2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Johann on 20.05.2015.
 */
public class BitmapLoader {

    public static Bitmap loadBitmap(Resources resources, int width, int height, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(resources, id);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        return bitmap;
    }
}
