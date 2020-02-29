package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_imagesavelib;

import android.content.Context;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;

import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class ImageUtility {
    public static int SPLASH_TIME_OUT_LONG = 0;
    static int SPLASH_TIME_OUT_MAX = 0;
    public static int SPLASH_TIME_OUT_SHORT = 0;
    private static final String TAG = "SaveImage Utility";
    public static Writer writer;

    static {
        SPLASH_TIME_OUT_LONG = 0;
        SPLASH_TIME_OUT_MAX = 0;
        SPLASH_TIME_OUT_SHORT = 0;
        SPLASH_TIME_OUT_LONG = 0;
        SPLASH_TIME_OUT_MAX = 0;
        SPLASH_TIME_OUT_SHORT = 0;
        SPLASH_TIME_OUT_LONG = 0;
        SPLASH_TIME_OUT_MAX = 0;
        SPLASH_TIME_OUT_SHORT = 0;
        SPLASH_TIME_OUT_SHORT = ModuleDescriptor.MODULE_VERSION;
        SPLASH_TIME_OUT_LONG = 800;
        SPLASH_TIME_OUT_MAX = 1300;
    }

    public static String getPrefferredDirectoryPath(Context mContext, boolean showErrorMessage, boolean getPrefDirectoryNoMatterWhat, boolean isAppCamera) {
        String directory;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        if (isAppCamera) {
            stringBuilder2 = new StringBuilder(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()));
            stringBuilder2.append(File.separator);
            stringBuilder2.append(mContext.getResources().getString(R.string.directory));
            directory = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getAbsolutePath()));
            stringBuilder2.append(mContext.getResources().getString(R.string.directory));
            directory = stringBuilder2.toString();
        }
        String prefDir = PreferenceManager.getDefaultSharedPreferences(mContext).getString("save_image_directory_custom", null);
        String str = TAG;
        if (prefDir != null) {
            stringBuilder = new StringBuilder(String.valueOf(prefDir));
            stringBuilder.append(File.separator);
            prefDir = stringBuilder.toString();
            if (getPrefDirectoryNoMatterWhat) {
                return prefDir;
            }
            File dirFile = new File(prefDir);
            String finalDirectory = directory;
            if (dirFile.canRead() && dirFile.canWrite() && checkIfEACCES(prefDir)) {
                directory = prefDir;
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("prefDir ");
            stringBuilder3.append(prefDir);
            Log.e(str, stringBuilder3.toString());
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append("getPrefferredDirectoryPath ");
        stringBuilder.append(directory);
        Log.e(str, stringBuilder.toString());
        return directory;
    }

    public static boolean checkIfEACCES(String dir) {
        String str = TAG;
        boolean z = false;
        try {
            File f = new File(dir);
            StringBuilder localPath = new StringBuilder(String.valueOf(dir));
            localPath.append("mpp");
            f.mkdirs();
            Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(localPath.toString())));
            try {
                writer2.write("Something");
                z = true;
                writer2.close();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("f.delete() = ");
                stringBuilder.append(new File(localPath.toString()).delete());
                Log.e(str, stringBuilder.toString());
                try {
                    writer2.close();
                    writer = writer2;
                } catch (Exception e) {
                    writer = writer2;
                }
            } catch (IOException e2) {
                writer = writer2;
                Log.e(str, e2.toString());
                try {
                    writer.close();
                } catch (Exception e3) {
                }
                return z;
            } catch (Throwable th2) {
                Throwable th = th2;
                try {
                    writer.close();
                } catch (Exception e4) {
                }
            }
            return z;
        } catch (IOException e5) {
            Log.e(str, e5.toString());
            return z;
        }
    }
}
