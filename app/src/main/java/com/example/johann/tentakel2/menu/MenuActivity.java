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
    private int soundID_explode;
    private MediaPlayer mplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        spBuilder = new SoundPool(100, AudioManager.STREAM_MUSIC, 0);
        //sets the maximum of simultaneously played streams
        //spBuilder.setMaxStreams(2);
        mplayer = MediaPlayer.create(this, R.raw.explosion);

        soundID_explode = spBuilder.load(this, R.raw.explosion, 1);

        //spBuilder.setAudioAttributes((AudioAttributes.USAGE_MEDIA));
        /*int soundID_explode = spBuilder.build().load(this, R.raw.explosion, 1);
        spBuilder.build();*/
        //.load(this, R.raw.explosion, 1), 1, 1, 0, 0, 1);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mplayer.pause();
    }

    private void showToolTip(View v) {
        int id = v.getId();
        if(id == btnStart.getId()) {
            Toast.makeText(this, "experience the great story that resolves around the mysterious tentacle-monster", Toast.LENGTH_LONG).show();
        }
        if(id == btnQStart.getId())
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
        spBuilder.play(soundID_explode, 1, 1, 0, 0, 1);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void switchView(View v) {
        mplayer.setLooping(true);
        mplayer.start();
        //spBuilder.play(soundID_explode, 1, 1, 0, 0, 1);
        int result;
        Intent intent;
        if(v.getId() == btnStart.getId()){
            intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("settings", new int[] {1,2,3});
            result = 1;
            startActivity(intent);
        }
        if (v.getId() == btnQStart.getId()) {
            intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("settings", new int[]{1, 2, 3});
            result = 0;
            startActivityForResult(intent, result);
        }
    }
}
