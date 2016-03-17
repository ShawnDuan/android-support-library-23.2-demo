package com.shawn_duan.android_support_library_232_demo;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private ImageView mAnimVectorDrawableImageView;
    private TextView tvCurrentDayNightMode;
    private View mBottomSheet;
    private BottomSheetBehavior mBottomSheetBehavior;
    private Button btOpen, btCollapse, btHide;
    TextView mHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBottomSheet = findViewById(R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(mBottomSheet);

        mAnimVectorDrawableImageView = (ImageView) findViewById(R.id.anim_vector_drawable);

        btOpen = (Button) findViewById(R.id.open);
        btCollapse = (Button) findViewById(R.id.collapse);
        btHide = (Button) findViewById(R.id.hide);
        mHeading = (TextView) findViewById(R.id.heading);

        //Handling movement of bottom sheets from buttons
        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mHeading.setText("Click on Open");
                mHeading.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.Lime));
            }
        });

        btCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mHeading.setText("Click on Collapse");
                mHeading.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
            }
        });

        btHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

        //Handling movement of bottom sheets from sliding
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mHeading.setText("Collapsed");
                    mHeading.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    mHeading.setText("Expanded");
                    mHeading.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.Lime));
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
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
