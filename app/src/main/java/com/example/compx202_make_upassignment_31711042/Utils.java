package com.example.compx202_make_upassignment_31711042;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }


    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        return context.getResources().getDisplayMetrics();
    }
}
