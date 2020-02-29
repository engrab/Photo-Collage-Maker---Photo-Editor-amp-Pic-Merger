package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_share;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Glob {
    public static String app_name = "PhotoCollageMaker";

    public static boolean isOnline(Context ctx) {
        NetworkInfo netInfo = ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.isConnected()) {
                return true;
            }
        }
        return false;
    }
}
