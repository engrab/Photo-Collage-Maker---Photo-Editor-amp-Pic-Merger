package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Debug;
import android.provider.MediaStore.Images.Media;
import android.util.Log;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Utility {
    private static final String TAG = Utility.class.getSimpleName();
    private static final float limitDivider = 30.0f;
    private static final float limitDividerGinger = 160.0f;
    public static final int[] patternResIdList = new int[]{R.drawable.no_pattern, R.drawable.color_picker, R.drawable.pattern_01, R.drawable.pattern_02, R.drawable.pattern_03, R.drawable.pattern_04, R.drawable.pattern_05, R.drawable.pattern_06, R.drawable.pattern_07, R.drawable.pattern_08, R.drawable.pattern_09, R.drawable.pattern_10, R.drawable.pattern_11, R.drawable.pattern_12, R.drawable.pattern_13, R.drawable.pattern_14, R.drawable.pattern_15, R.drawable.pattern_16, R.drawable.pattern_17, R.drawable.pattern_18, R.drawable.pattern_19, R.drawable.pattern_20, R.drawable.pattern_21, R.drawable.pattern_22, R.drawable.pattern_23, R.drawable.pattern_24, R.drawable.pattern_25, R.drawable.pattern_26, R.drawable.pattern_27, R.drawable.pattern_28, R.drawable.pattern_29, R.drawable.pattern_30, R.drawable.pattern_31, R.drawable.pattern_32, R.drawable.pattern_33, R.drawable.pattern_34, R.drawable.pattern_35, R.drawable.pattern_36, R.drawable.pattern_37, R.drawable.pattern_38, R.drawable.pattern_39, R.drawable.pattern_40, R.drawable.pattern_41, R.drawable.pattern_42, R.drawable.pattern_43, R.drawable.pattern_44, R.drawable.pattern_45, R.drawable.pattern_46, R.drawable.pattern_47, R.drawable.pattern_48, R.drawable.pattern_49, R.drawable.pattern_50, R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54, R.drawable.pattern_55, R.drawable.pattern_56, R.drawable.pattern_57};
    public static final int[][] patternResIdList2;
    public static final int[] patternResIdList3 = new int[]{R.drawable.no_pattern, R.drawable.color_picker, R.drawable.pattern_icon_08, R.drawable.pattern_icon_09, R.drawable.pattern_icon_06, R.drawable.pattern_icon_07, R.drawable.pattern_icon_10, R.drawable.pattern_icon_11, R.drawable.pattern_icon_12, R.drawable.pattern_icon_05, R.drawable.pattern_icon_01, R.drawable.pattern_icon_02, R.drawable.pattern_icon_03, R.drawable.pattern_icon_04};
    private static final int[][] r0 = new int[12][];

    static {
        int[][] iArr = r0;
        patternResIdList2 = iArr;
        iArr[0] = new int[]{R.drawable.pattern_085, R.drawable.pattern_086, R.drawable.pattern_087, R.drawable.pattern_088, R.drawable.pattern_089, R.drawable.pattern_090, R.drawable.pattern_091, R.drawable.pattern_092, R.drawable.pattern_093, R.drawable.pattern_094, R.drawable.pattern_095, R.drawable.pattern_096};
        iArr[1] = new int[]{R.drawable.pattern_097, R.drawable.pattern_098, R.drawable.pattern_099, R.drawable.pattern_100, R.drawable.pattern_101, R.drawable.pattern_102, R.drawable.pattern_103, R.drawable.pattern_104, R.drawable.pattern_105, R.drawable.pattern_106, R.drawable.pattern_107, R.drawable.pattern_108};
        iArr[2] = new int[]{R.drawable.pattern_061, R.drawable.pattern_062, R.drawable.pattern_063, R.drawable.pattern_064, R.drawable.pattern_065, R.drawable.pattern_066, R.drawable.pattern_067, R.drawable.pattern_068, R.drawable.pattern_069, R.drawable.pattern_070, R.drawable.pattern_071, R.drawable.pattern_072};
        iArr[3] = new int[]{R.drawable.pattern_073, R.drawable.pattern_074, R.drawable.pattern_075, R.drawable.pattern_076, R.drawable.pattern_077, R.drawable.pattern_078, R.drawable.pattern_079, R.drawable.pattern_080, R.drawable.pattern_081, R.drawable.pattern_082, R.drawable.pattern_083, R.drawable.pattern_084};
        iArr[4] = new int[]{R.drawable.pattern_109, R.drawable.pattern_110, R.drawable.pattern_111, R.drawable.pattern_112, R.drawable.pattern_113, R.drawable.pattern_114, R.drawable.pattern_115, R.drawable.pattern_116, R.drawable.pattern_117, R.drawable.pattern_118, R.drawable.pattern_119, R.drawable.pattern_120};
        iArr[5] = new int[]{R.drawable.pattern_121, R.drawable.pattern_122, R.drawable.pattern_123, R.drawable.pattern_124, R.drawable.pattern_125, R.drawable.pattern_126, R.drawable.pattern_127, R.drawable.pattern_128, R.drawable.pattern_129, R.drawable.pattern_130, R.drawable.pattern_131};
        iArr[6] = new int[]{R.drawable.pattern_132, R.drawable.pattern_133, R.drawable.pattern_134, R.drawable.pattern_135, R.drawable.pattern_136, R.drawable.pattern_137, R.drawable.pattern_138, R.drawable.pattern_139, R.drawable.pattern_140, R.drawable.pattern_141, R.drawable.pattern_142};
        iArr[7] = new int[]{R.drawable.pattern_49, R.drawable.pattern_50, R.drawable.pattern_51, R.drawable.pattern_52, R.drawable.pattern_53, R.drawable.pattern_54, R.drawable.pattern_55, R.drawable.pattern_56, R.drawable.pattern_57, R.drawable.pattern_058, R.drawable.pattern_059, R.drawable.pattern_060};
        iArr[8] = new int[]{R.drawable.pattern_01, R.drawable.pattern_02, R.drawable.pattern_03, R.drawable.pattern_04, R.drawable.pattern_05, R.drawable.pattern_06, R.drawable.pattern_07, R.drawable.pattern_08, R.drawable.pattern_09, R.drawable.pattern_10, R.drawable.pattern_11, R.drawable.pattern_12};
        iArr[9] = new int[]{R.drawable.pattern_13, R.drawable.pattern_14, R.drawable.pattern_15, R.drawable.pattern_16, R.drawable.pattern_17, R.drawable.pattern_18, R.drawable.pattern_19, R.drawable.pattern_20, R.drawable.pattern_21, R.drawable.pattern_22, R.drawable.pattern_23, R.drawable.pattern_24};
        iArr[10] = new int[]{R.drawable.pattern_25, R.drawable.pattern_26, R.drawable.pattern_27, R.drawable.pattern_28, R.drawable.pattern_29, R.drawable.pattern_30, R.drawable.pattern_31, R.drawable.pattern_32, R.drawable.pattern_33, R.drawable.pattern_34, R.drawable.pattern_35, R.drawable.pattern_36};
        iArr[11] = new int[]{R.drawable.pattern_37, R.drawable.pattern_38, R.drawable.pattern_39, R.drawable.pattern_40, R.drawable.pattern_41, R.drawable.pattern_42, R.drawable.pattern_43, R.drawable.pattern_44, R.drawable.pattern_45, R.drawable.pattern_46, R.drawable.pattern_47, R.drawable.pattern_48};
    }

    @SuppressLint({"NewApi"})
    public static Bitmap getScaledBitmapFromId(Context context, long imageID, int orientation, int requiredSize, boolean isScrapBook) {
        Uri uri = Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, Long.toString(imageID));
        Options boundsOption = new Options();
        boundsOption.inJustDecodeBounds = true;
        AssetFileDescriptor fileDescriptor = null;
        try {
            fileDescriptor = context.getContentResolver().openAssetFileDescriptor(uri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (fileDescriptor == null) {
            return null;
        }
        BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, boundsOption);
        Options options = new Options();
        if (requiredSize != -1) {
            options.inSampleSize = calculateInSampleSize(boundsOption, requiredSize, requiredSize);
        }
        if (VERSION.SDK_INT >= 11 && isScrapBook) {
            options.inMutable = true;
        }
        Bitmap actuallyUsableBitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor.getFileDescriptor(), null, options);
        if (actuallyUsableBitmap == null) {
            return null;
        }
        Bitmap bitmap = rotateImage(actuallyUsableBitmap, orientation);
        if (!(bitmap == null || actuallyUsableBitmap == bitmap)) {
            actuallyUsableBitmap.recycle();
        }
        if (!bitmap.isMutable()) {
            if (isScrapBook) {
                Log.e(TAG, "bitmap is not mutable");
                Bitmap mutableBitmap = bitmap.copy(Config.ARGB_8888, true);
                if (mutableBitmap == bitmap) {
                    return mutableBitmap;
                }
                bitmap.recycle();
                return mutableBitmap;
            }
        }
        return bitmap;
    }

    @SuppressLint({"NewApi"})
    public static Bitmap decodeFile(String path, int requiredSize, boolean isScrapBook) {
        try {
            File f = new File(path);
            Options boundsOption = new Options();
            boundsOption.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, boundsOption);
            Options o2 = new Options();
            if (VERSION.SDK_INT >= 11 && isScrapBook) {
                o2.inMutable = true;
            }
            o2.inSampleSize = calculateInSampleSize(boundsOption, requiredSize, requiredSize);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = rotateImage(bitmap, exif.getAttributeInt("Orientation", 0));
            if (bitmap.isMutable()) {
                return bitmap;
            }
            Bitmap mutableBitmap = bitmap.copy(Config.ARGB_8888, true);
            if (mutableBitmap != bitmap) {
                bitmap.recycle();
            }
            return mutableBitmap;
        } catch (FileNotFoundException e2) {
            return null;
        }
    }

    private static Bitmap rotateImage(Bitmap bitmap, int orientation) {
        Bitmap resultBitmap = bitmap;
        Matrix matrix = new Matrix();
        if (orientation == 90) {
            matrix.postRotate(90.0f);
        } else if (orientation == 180) {
            matrix.postRotate(180.0f);
        } else if (orientation == 270) {
            matrix.postRotate(270.0f);
        }
        if (orientation == 0) {
            return resultBitmap;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            while (true) {
                if (halfHeight / inSampleSize <= reqHeight && halfWidth / inSampleSize <= reqWidth) {
                    break;
                }
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static double getLeftSizeOfMemory() {
        return (Double.valueOf((double) Runtime.getRuntime().maxMemory()).doubleValue() - (Double.valueOf((double) Runtime.getRuntime().totalMemory()).doubleValue() - Double.valueOf((double) Runtime.getRuntime().freeMemory()).doubleValue())) - Double.valueOf((double) Debug.getNativeHeapAllocatedSize()).doubleValue();
    }

    public static int maxSizeForDimension(Context context, int count, float upperLimit) {
        float divider = limitDivider;
        if (VERSION.SDK_INT <= 11) {
            divider = limitDividerGinger;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("divider = ");
        stringBuilder.append(divider);
        Log.e(str, stringBuilder.toString());
        double leftSizeOfMemory = getLeftSizeOfMemory();
        double d = (double) (((float) count) * divider);
        Double.isNaN(d);
        int maxSize = (int) Math.sqrt(leftSizeOfMemory / d);
        if (maxSize <= 0) {
            maxSize = getDefaultLimit(count, upperLimit);
        }
        return Math.min(maxSize, getDefaultLimit(count, upperLimit));
    }

    public static int maxSizeForSave(Context context, float upperLimit) {
        float divider = limitDivider;
        if (VERSION.SDK_INT <= 11) {
            divider = limitDividerGinger;
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("divider = ");
        stringBuilder.append(divider);
        Log.e(str, stringBuilder.toString());
        double leftSizeOfMemory = getLeftSizeOfMemory();
        double d = (double) divider;
        Double.isNaN(d);
        int maxSize = (int) Math.sqrt(leftSizeOfMemory / d);
        if (maxSize > 0) {
            return (int) Math.min((float) maxSize, upperLimit);
        }
        return (int) upperLimit;
    }

    private static int getDefaultLimit(int count, float upperLimit) {
        double d = (double) upperLimit;
        double sqrt = Math.sqrt((double) count);
        Double.isNaN(d);
        int limit = (int) (d / sqrt);
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("limit = ");
        stringBuilder.append(limit);
        Log.e(str, stringBuilder.toString());
        return limit;
    }

    public static float pointToAngle(float x, float y, float centerX, float centerY) {
        float degree = 0.0f;
        double d;
        double d2;
        double r1;
        if (x >= centerX && y < centerY) {
            d = (double) (x - centerX);
            d2 = (double) (centerY - y);
            Double.isNaN(d);
            Double.isNaN(d2);
            degree = (float) (Math.toDegrees(Math.atan(d / d2)) + 270.0d);
        } else if (x > centerX && y >= centerY) {
            r1 = (double) (y - centerY);
            d = (double) (x - centerX);
            Double.isNaN(r1);
            Double.isNaN(d);
            degree = (float) Math.toDegrees(Math.atan(r1 / d));
        } else if (x <= centerX && y > centerY) {
            d = (double) (centerX - x);
            d2 = (double) (y - centerY);
            Double.isNaN(d);
            Double.isNaN(d2);
            degree = (float) (Math.toDegrees(Math.atan(d / d2)) + 90.0d);
        } else if (x < centerX && y <= centerY) {
            r1 = (double) (centerY - y);
            d = (double) (centerX - x);
            Double.isNaN(r1);
            Double.isNaN(d);
            degree = (float) (((int) Math.toDegrees(Math.atan(r1 / d))) + 180);
        }
        if (degree < -180.0f) {
            degree += 360.0f;
        }
        if (degree > 180.0f) {
            return degree - 360.0f;
        }
        return degree;
    }

    public static int maxSizeForSave() {
        int maxSize = (int) Math.sqrt(getLeftSizeOfMemory() / 40.0d);
        if (maxSize > 1080) {
            return 1080;
        }
        return maxSize;
    }
}
