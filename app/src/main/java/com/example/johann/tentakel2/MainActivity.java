package com.example.johann.tentakel2;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class MainActivity extends Activity implements
        GestureDetector.OnGestureListener{
    private GameModel mModel;
    private Player player;
    private Player enemy;
    private GestureDetector gestureDetector;
    private ImageView armGut;
    private ImageView armGut2;
    private ImageView koerperGut;
    private ImageView kopfGut;
    private ImageView beineGut;
    private ImageView armBoese;
    private ImageView armBoese2;
    private ImageView koerperBoese;
    private ImageView kopfBoese;
    private ImageView beineBoese;
    private int WindowWidth;
    private int WindowHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, this);

        armGut = (ImageView)findViewById(R.id.armGut);
        armGut2 = (ImageView)findViewById(R.id.armGut2);
        koerperGut = (ImageView)findViewById(R.id.koerperGut);
        kopfGut = (ImageView)findViewById(R.id.kopfGut);
        beineGut = (ImageView)findViewById(R.id.beineGut);

        armBoese = (ImageView)findViewById(R.id.armBoese);
        armBoese2 = (ImageView)findViewById(R.id.armBoese2);
        koerperBoese = (ImageView)findViewById(R.id.koerperBoese);
        kopfBoese = (ImageView)findViewById(R.id.kopfBoese);
        beineBoese = (ImageView)findViewById(R.id.beineBoese);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        WindowWidth = displaymetrics.widthPixels;
        WindowHeight = displaymetrics.heightPixels;

        ArrayList<Number> enemyIDs = new ArrayList<>();
        Collections.addAll(enemyIDs, R.drawable.clown_kopf, R.drawable.clown_koerper, R.drawable.clown_arm, R.drawable.clown_beine);
        ViewComponent viewEnemy = new ViewComponent(armBoese, armBoese2, kopfBoese, koerperBoese, beineBoese,
                WindowWidth, WindowHeight, getResources(), enemyIDs, false);

        ArrayList<Number> playerIDs = new ArrayList<>();
        Collections.addAll(playerIDs, R.drawable.tentakel_kopf, R.drawable.tentakel_koerper, R.drawable.tentakel_arm, R.drawable.tentakel_beine);
        ViewComponent viewPlayer = new ViewComponent(armGut, armGut2, kopfGut, koerperGut, beineGut,
                WindowWidth, WindowHeight, getResources(), playerIDs, true);

        viewPlayer.setPivot(0, WindowHeight / 5);
        viewEnemy.setPivot((int)(WindowWidth - viewEnemy.getBeineWidth()), WindowHeight / 5);

        RelativeLayout r = (RelativeLayout)(findViewById(R.id.mainLayout));
        r.setBackground(new BitmapDrawable(getResources(), BitmapLoader.loadBitmap(getResources(), WindowWidth, WindowHeight, R.drawable.hintergrund)));

        enemy = new Player(true, viewEnemy);
        player = new Player(true, viewPlayer);

        mModel = new GameModel(WindowWidth, WindowHeight, player, enemy, this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId(); ///

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        mModel.handleInput(e, e);
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mModel.handleInput(e1, e2);
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return super.dispatchTouchEvent(event);
    }



    private int getPixels(int dipValue){
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
        return dipValue;
    }
}
