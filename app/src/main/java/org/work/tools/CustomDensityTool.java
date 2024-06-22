package org.work.tools;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

import org.work.MainActivity;
import org.work.MainApplication;


/**
 * https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
 */
public class CustomDensityTool {


    /**
     * 设计图宽度是360dp，以宽维度来适配
     */
    private static final int designWidth = 360;

    /**
     * 的dp在渲染前会将dp转为px，计算公式：
     * px = density * dp;
     * density = dpi / 160;
     * px = dp * (dpi / 160);
     */
    private static final int factor = 160;


    private static float mDensity;
    private static float mScaleDensity;
    private static boolean mIsLoad = false;


    public static void set(MainActivity activity, final MainApplication application) {
       final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (_noInitializationVariable()) {
            mIsLoad = true;
            mDensity = displayMetrics.density;
            mScaleDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration configuration) {
                    if (configuration.fontScale > 0) {
                        mScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }


        _onFlush(activity, displayMetrics);
    }

    private static boolean _noInitializationVariable() {
        return !mIsLoad;
    }

    private static void _onFlush(MainActivity activity, DisplayMetrics displayMetrics) {
        if (_noInitializationVariable()) return;

        int targetDensity = displayMetrics.widthPixels / designWidth;
        int targetScaleDensity = (int) (targetDensity * (mScaleDensity / mDensity));
        int targetDensityDpi = (factor * targetDensity);

        // application
        {

            displayMetrics.density = targetDensity;
            displayMetrics.densityDpi = targetDensityDpi;
            displayMetrics.scaledDensity = targetScaleDensity;
        }

        // activity
        {

            displayMetrics = activity.getResources().getDisplayMetrics();
            displayMetrics.density = targetDensity;
            displayMetrics.densityDpi = targetDensityDpi;
            displayMetrics.scaledDensity = targetScaleDensity;
        }

    }
}
