package com.example.johann.tentakel2.menu;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.johann.tentakel2.MainActivity;
import com.example.johann.tentakel2.R;


public class MenuActivity extends Activity {

    private Button btnQStart;
    private Button btnStart;
    private SoundPool spBuilder;
    //sound IDs
    private int soundID_critHit_scream;
    private int soundID_enemy_change_walk;
    private int soundID_enemy_entry;
    private int soundID_enemy_entry_theme;
    private int soundID_enemy_hit;
    private int soundID_gong;
    private int soundID_gong_double;
    private int soundID_gong_double_short;
    private int soundID_gong_long;
    private int soundID_gong_short;
    private int soundID_gong_short2;
    private int soundID_gong_time_ending;
    private int soundID_gong_very_short;
    private int soundID_menu_choose;
    private int soundID_normal_hit_scream;
    private int soundID_normal_hit_scream2;
    private int soundID_normal_punch;
    private int soundID_punch;
    private int soundID_scroll_sound;
    private int soundID_start_fight;
    private int soundID_tentacle_movement;
    private int soundID_title_melody;
    private int soundID_window_blow1;
    private int soundID_window_blow2;

    private MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSound();

        setContentView(R.layout.activity_menu);

        btnStart = (Button) findViewById(R.id.button_start);
        btnQStart = (Button) findViewById(R.id.button_quick_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView(v);
            }
        });
        btnQStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView(v);
            }
        });

        //sets the maximum of simultaneously played streams
        //spBuilder.setMaxStreams(2);
        mplayer = MediaPlayer.create(this, R.raw.blazblue_nightmare_fiction);


        btnStart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showToolTip(v);
                return true;
            }
        });

        btnQStart.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.setId(btnStart.getId());
                showToolTip(v);
                return true;
            }
        });
    }

    private void initSound() {

        spBuilder = new SoundPool(100, AudioManager.STREAM_DTMF, 0);

        soundID_critHit_scream = spBuilder.load(this, R.raw.critical_hit_scream, 1);
        soundID_enemy_change_walk = spBuilder.load(this, R.raw.enemy_change_walk, 1);
        soundID_enemy_entry = spBuilder.load(this, R.raw.enemy_entry, 1);
        soundID_enemy_entry_theme = spBuilder.load(this, R.raw.enemy_entry_theme, 1);
        soundID_enemy_hit = spBuilder.load(this, R.raw.enemy_hit, 1);
        soundID_gong = spBuilder.load(this, R.raw.gong, 1);
        soundID_gong_double = spBuilder.load(this, R.raw.gong_double, 1);
        soundID_gong_double_short = spBuilder.load(this, R.raw.gong_double_short, 1);
        soundID_gong_long = spBuilder.load(this, R.raw.gong_long, 1);
        soundID_gong_short = spBuilder.load(this, R.raw.gong_short, 1);
        soundID_gong_short2 = spBuilder.load(this, R.raw.gong_short2, 1);
        soundID_gong_time_ending = spBuilder.load(this, R.raw.gong_time_ending, 1);
        soundID_gong_very_short = spBuilder.load(this, R.raw.gong_very_short, 1);
        soundID_menu_choose = spBuilder.load(this, R.raw.menu_choose, 1);
        soundID_normal_hit_scream = spBuilder.load(this, R.raw.normal_hit_scream, 1);
        soundID_normal_hit_scream2 = spBuilder.load(this, R.raw.normal_hit_scream2, 1);
        soundID_normal_punch = spBuilder.load(this, R.raw.normal_punch, 1);
        soundID_punch = spBuilder.load(this, R.raw.punch, 1);
        soundID_scroll_sound = spBuilder.load(this, R.raw.scroll_sound, 1);
        soundID_start_fight = spBuilder.load(this, R.raw.start_fight, 1);
        soundID_tentacle_movement = spBuilder.load(this, R.raw.tentacle_movement, 1);
        soundID_title_melody = spBuilder.load(this, R.raw.title_melody, 1);
        soundID_window_blow1 = spBuilder.load(this, R.raw.wind_blow1, 1);
        soundID_window_blow2 = spBuilder.load(this, R.raw.wind_blow2, 1);
    }

    private void playSound(int soundID) {

        //spBuilder.setAudioAttributes((AudioAttributes.USAGE_MEDIA));
        /*int soundID_explode = spBuilder.build().load(this, R.raw.explosion, 1);
        spBuilder.build();*/
        //.load(this, R.raw.explosion, 1), 1, 1, 0, 0, 1);
        spBuilder.play(soundID, 1, 1, 0, 0, 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showToolTip(View v) {
        int id = v.getId();
        if (id == btnStart.getId()) {
            Toast.makeText(this, "experience the great story that resolves around the mysterious tentacle-monster", Toast.LENGTH_LONG).show();
        }
        if (id == btnQStart.getId())
            Toast.makeText(this,
                    "fight against some random enemies, which, I think are getting stronger, the further you get",
                    Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchView(View v) {

        mplayer.setLooping(true);
        mplayer.start();

        int result;
        Intent intent;
        if (v.getId() == btnStart.getId()) {
            playSound(soundID_menu_choose);
            intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("settings", new int[]{1, 2, 3});
            result = 1;
            startActivity(intent);
        }
        if (v.getId() == btnQStart.getId()) {
            playSound(soundID_menu_choose);
            playSound(soundID_enemy_change_walk);
            intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("settings", new int[]{1, 2, 3});
            result = 0;
            startActivityForResult(intent, result);
        }
    }
}
