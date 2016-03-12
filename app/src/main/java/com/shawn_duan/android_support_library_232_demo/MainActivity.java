package com.shawn_duan.android_support_library_232_demo;

import android.content.res.Configuration;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView mAnimVectorDrawableImageView;
    private TextView tvCurrentDayNightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAnimVectorDrawableImageView = (ImageView) findViewById(R.id.anim_vector_drawable);
        tvCurrentDayNightMode = (TextView) findViewById(R.id.current_night_mode);
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume()");
        super.onResume();
        Drawable drawable = mAnimVectorDrawableImageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable)drawable).start();
        }

//        int currentNightMode = getResources().getConfiguration().uiMode
//                & Configuration.UI_MODE_NIGHT_MASK;
//        switch (currentNightMode) {
//            case Configuration.UI_MODE_NIGHT_NO:
//                tvCurrentDayNightMode.setText("MODE_NIGNT_NO");
//                break;
//            case Configuration.UI_MODE_NIGHT_YES:
//                tvCurrentDayNightMode.setText("MODE_NIGHT_YES");
//                break;
//            case Configuration.UI_MODE_NIGHT_UNDEFINED:
//                tvCurrentDayNightMode.setText("MODE_NIGHT_UNDEFINED");
//                break;
//        }

        int currentNightMode = AppCompatDelegate.getDefaultNightMode();
        switch (currentNightMode) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                tvCurrentDayNightMode.setText("MODE_NIGNT_NO");
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                tvCurrentDayNightMode.setText("MODE_NIGHT_YES");
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                tvCurrentDayNightMode.setText("MODE_NIGHT_AUTO");
                break;
            default:
                tvCurrentDayNightMode.setText("MODE_NIGHT_UNDEFINED");
                break;
        }
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
        if (id == R.id.day_mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();
            return true;
        } else if (id == R.id.night_mode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            recreate();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
