package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;

public class BitmapLoader {
    public Bitmap load(Context context, int[] holderDimension, String image_url) throws Exception {
        Options bitmapOptions = new Options();
        bitmapOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(image_url, bitmapOptions);
        int inSampleSize = 2;
        int outWidth = bitmapOptions.outWidth;
        int outHeight = bitmapOptions.outHeight;
        int holderWidth = holderDimension[0];
        int holderHeight = holderDimension[1];
        if (outHeight > holderHeight || outWidth > holderWidth) {
            int halfWidth = outWidth / 2;
            int halfHeight = outHeight / 2;
            while (halfHeight / inSampleSize > holderHeight && halfWidth / inSampleSize > holderWidth) {
                inSampleSize *= 2;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inSampleSize);
        stringBuilder.append("");
        Log.e("Sample Size", stringBuilder.toString());
        bitmapOptions.inSampleSize = inSampleSize;
        bitmapOptions.inJustDecodeBounds = false;
        return BitmapProcessing.modifyOrientation(BitmapFactory.decodeFile(image_url, bitmapOptions), image_url);
    }
}
