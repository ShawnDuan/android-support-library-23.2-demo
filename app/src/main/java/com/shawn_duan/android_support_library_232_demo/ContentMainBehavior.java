package com.shawn_duan.android_support_library_232_demo;

import android.content.Context;
import android.graphics.Point;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by Wenxiao on 3/17/16.
 */

public class ContentMainBehavior extends CoordinatorLayout.Behavior<LinearLayout> {

    private final static String TAG = ContentMainBehavior.class.getName();
    private Context mContext;

    public ContentMainBehavior(Context context, AttributeSet attrs) {
        mContext = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout child, View dependency) {
        return dependency instanceof BottomSheetFrame;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout child, View dependency) {
        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int x = dependency.getHeight() /300 * 70;      // this number should be the collapsed height (corresponding to app:behavior_peekHeight="70dp" in bottom_sheet_frame.xml)
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        Log.d(TAG, "getTranslationY()/getHeight()/getTop(): " + dependency.getTranslationY() + "/" + dependency.getHeight() + "/" + dependency.getTop());
        float translationY = Math.min(x, dependency.getTop() - screenHeight + x);
        Log.d(TAG, "translationY: " + translationY);
        child.setTranslationY(translationY);
        return true;
    }
}
