package com.apptopstudio.photo.collagemaker.photoeditor.picmerger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Glob {
    public static String acc_link = "https://play.google.com/store/apps/developer?id=com.apptopstudio.photo.collagemaker.photoeditor.picmerger";

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
