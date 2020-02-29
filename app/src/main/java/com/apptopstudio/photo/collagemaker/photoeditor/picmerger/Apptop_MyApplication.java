package com.apptopstudio.photo.collagemaker.photoeditor.picmerger;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

public class Apptop_MyApplication extends MultiDexApplication {
    static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (VERSION.SDK_INT >= 24) {
            Builder builder = new Builder();
            builder.detectFileUriExposure();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
