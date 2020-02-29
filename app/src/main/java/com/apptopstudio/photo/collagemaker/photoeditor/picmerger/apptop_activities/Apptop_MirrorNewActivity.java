package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.Glob;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_adapters.MyRecyclerViewAdapter.RecyclerAdapterIndexChangedListener;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.ApplyTextInterface;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.CustomRelativeLayout;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.SingleTap;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.TextData;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.Apptop_CollageActivity;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.Utility;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.EffectFragment;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.EffectFragment.BitmapReady;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.FontFragment;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.FontFragment.FontChoosedListener;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_share.Apptop_ImageShareActivity;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.LibUtility;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.MirrorMode;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Apptop_MirrorNewActivity extends AppCompatActivity {
    public static final int INDEX_MIRROR = 0;
    public static final int INDEX_MIRROR_3D = 1;
    public static final int INDEX_MIRROR_ADJUSTMENT = 5;
    public static final int INDEX_MIRROR_EFFECT = 3;
    public static final int INDEX_MIRROR_FRAME = 4;
    public static final int INDEX_MIRROR_INVISIBLE_VIEW = 7;
    public static final int INDEX_MIRROR_INVISIBLE_VIEW_ACTUAL_INDEX = 4;
    public static final int INDEX_MIRROR_RATIO = 2;
    static final int SAVE_IMAGE_ID = 1348;
    public static final int TAB_SIZE = 6;
    private static final String TAG = "MirrorNewActivity";
    int D3_BUTTON_SIZE = 24;
    int MIRROR_BUTTON_SIZE = 15;
    int RATIO_BUTTON_SIZE = 11;
    private LinearLayout adExitChoicesContainer;

    AlertDialog alertDialog = null;
    CustomRelativeLayout canvasText;
    int currentSelectedTabIndex = -1;
    ImageView[] d3ButtonArray;
    private int[] d3resList = new int[]{R.drawable.mirror_3d_14, R.drawable.mirror_3d_14, R.drawable.mirror_3d_10, R.drawable.mirror_3d_10, R.drawable.mirror_3d_11, R.drawable.mirror_3d_11, R.drawable.mirror_3d_4, R.drawable.mirror_3d_4, R.drawable.mirror_3d_3, R.drawable.mirror_3d_3, R.drawable.mirror_3d_1, R.drawable.mirror_3d_1, R.drawable.mirror_3d_6, R.drawable.mirror_3d_6, R.drawable.mirror_3d_13, R.drawable.mirror_3d_13, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_15, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16, R.drawable.mirror_3d_16};
    EffectFragment effectFragment;
    Bitmap filterBitmap;
    FontChoosedListener fontChoosedListener = new C08731();
    FontFragment fontFragment;
    int initialYPos = 0;
    RelativeLayout mainLayout;
    Matrix matrixMirrothis = new Matrix();
    Matrix matrixMirror2 = new Matrix();
    Matrix matrixMirror3 = new Matrix();
    Matrix matrixMirror4 = new Matrix();
    ImageView[] mirrorButtonArray;
    MirrorView mirrorView;
    float mulX = 16.0f;
    float mulY = 16.0f;
    private LinearLayout nativeAdContainerExitAds;
    InterstitialAd mInterstitialAd;


    private ProgressBar progressBarExitRefresh;
    Button[] ratioButtonArray;
    android.support.v7.app.AlertDialog saveImageAlert;
    int screenHeightPixels;
    int screenWidthPixels;
    boolean showText = false;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    Bitmap sourceBitmap;
    View[] tabButtonList;
    ArrayList<TextData> textDataList = new ArrayList();
    ViewFlipper viewFlipper;
    AdView adView;

    class MirrorView extends View {
        int currentModeIndex = 0;
        Bitmap d3Bitmap;
        boolean d3Mode = false;
        int defaultColor = R.color.bg;
        RectF destRect1;
        RectF destRect1X;
        RectF destRect1Y;
        RectF destRect2;
        RectF destRect2X;
        RectF destRect2Y;
        RectF destRect3;
        RectF destRect4;
        boolean drawSavedImage = false;
        RectF dstRectPapethis;
        RectF dstRectPaper2;
        RectF dstRectPaper3;
        RectF dstRectPaper4;
        final Matrix f510I = new Matrix();
        Bitmap frameBitmap;
        Paint framePaint = new Paint();
        int height;
        boolean isTouchStartedLeft;
        boolean isTouchStartedTop;
        boolean isVerticle = false;
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        Matrix m3 = new Matrix();
        MirrorMode[] mirrorModeList = new MirrorMode[20];
        MirrorMode modeX;
        MirrorMode modeX10;
        MirrorMode modeX11;
        MirrorMode modeX12;
        MirrorMode modeX13;
        MirrorMode modeX14;
        MirrorMode modeX15;
        MirrorMode modeX16;
        MirrorMode modeX17;
        MirrorMode modeX18;
        MirrorMode modeX19;
        MirrorMode modeX2;
        MirrorMode modeX20;
        MirrorMode modeX3;
        MirrorMode modeX4;
        MirrorMode modeX5;
        MirrorMode modeX6;
        MirrorMode modeX7;
        MirrorMode modeX8;
        MirrorMode modeX9;
        float oldX;
        float oldY;
        RectF srcRect1;
        RectF srcRect2;
        RectF srcRect3;
        RectF srcRectPaper;
        int tMode1;
        int tMode2;
        int tMode3;
        Matrix textMatrix = new Matrix();
        Paint textRectPaint = new Paint(1);
        RectF totalArea1;
        RectF totalArea2;
        RectF totalArea3;
        int width;

        public MirrorView(Context context, int screenWidth, int screenHeight) {
            super(context);
            this.width = Apptop_MirrorNewActivity.this.sourceBitmap.getWidth();
            this.height = Apptop_MirrorNewActivity.this.sourceBitmap.getHeight();
            int widthPixels = screenWidth;
            int heightPixels = screenHeight;
            createMatrix(widthPixels, heightPixels);
            createRectX(widthPixels, heightPixels);
            createRectY(widthPixels, heightPixels);
            createRectXY(widthPixels, heightPixels);
            createModes();
            this.framePaint.setAntiAlias(true);
            this.framePaint.setFilterBitmap(true);
            this.framePaint.setDither(true);
            this.textRectPaint.setColor(getResources().getColor(R.color.bg));
        }

        private void reset(int widthPixels, int heightPixels, boolean invalidate) {
            createMatrix(widthPixels, heightPixels);
            createRectX(widthPixels, heightPixels);
            createRectY(widthPixels, heightPixels);
            createRectXY(widthPixels, heightPixels);
            createModes();
            if (invalidate) {
                postInvalidate();
            }
        }

        private String saveBitmap(boolean saveToFile, int widthPixel, int heightPixel) {
            float f;
            int btmWidth;
            Bitmap bitmap;
            String resultPath;
            StringBuilder resultPath2;
            int i = widthPixel;
            int i2 = heightPixel;
            float upperScale = (float) Utility.maxSizeForSave();
            float scale = upperScale / ((float) Math.min(widthPixel, heightPixel));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("upperScale");
            stringBuilder.append(upperScale);
            String stringBuilder2 = stringBuilder.toString();
            String str = Apptop_MirrorNewActivity.TAG;
            Log.e(str, stringBuilder2);
            stringBuilder = new StringBuilder();
            String str2 = "scale";
            stringBuilder.append(str2);
            stringBuilder.append(scale);
            Log.e(str, stringBuilder.toString());
            if (Apptop_MirrorNewActivity.this.mulY > Apptop_MirrorNewActivity.this.mulX) {
                f = Apptop_MirrorNewActivity.this.mulX;
                scale = (1.0f * scale) / Apptop_MirrorNewActivity.this.mulY;
            }
            if (scale <= 0.0f) {
                f = 1.0f;
            } else {
                f = scale;
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(str2);
            stringBuilder3.append(f);
            Log.e(str, stringBuilder3.toString());
            int wP = Math.round(((float) i) * f);
            int wH = Math.round(((float) i2) * f);
            RectF srcRect = this.mirrorModeList[this.currentModeIndex].getSrcRect();
            reset(wP, wH, false);
            int btmWidth2 = Math.round(Apptop_MirrorNewActivity.this.mirrorView.getCurrentMirrorMode().rectTotalArea.width());
            int btmHeight = Math.round(Apptop_MirrorNewActivity.this.mirrorView.getCurrentMirrorMode().rectTotalArea.height());
            if (btmWidth2 % 2 == 1) {
                btmWidth = btmWidth2 - 1;
            } else {
                btmWidth = btmWidth2;
            }
            if (btmHeight % 2 == 1) {
                btmHeight--;
            }
            Bitmap savedBitmap = Bitmap.createBitmap(btmWidth, btmHeight, Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(savedBitmap);
            Matrix matrix = new Matrix();
            matrix.reset();
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("btmWidth ");
            stringBuilder3.append(btmWidth);
            Log.e(str, stringBuilder3.toString());
            stringBuilder3 = new StringBuilder();
            stringBuilder3.append("btmHeight ");
            stringBuilder3.append(btmHeight);
            Log.e(str, stringBuilder3.toString());
            matrix.postTranslate(((float) (-(wP - btmWidth))) / 2.0f, ((float) (-(wH - btmHeight))) / 2.0f);
            MirrorMode saveMode = this.mirrorModeList[this.currentModeIndex];
            saveMode.setSrcRect(srcRect);
            if (Apptop_MirrorNewActivity.this.filterBitmap == null) {
                drawMode(bitmapCanvas, Apptop_MirrorNewActivity.this.sourceBitmap, saveMode, matrix);
            } else {
                drawMode(bitmapCanvas, Apptop_MirrorNewActivity.this.filterBitmap, saveMode, matrix);
            }
            if (this.d3Mode) {
                bitmap = this.d3Bitmap;
                if (!(bitmap == null || bitmap.isRecycled())) {
                    bitmapCanvas.setMatrix(matrix);
                    bitmapCanvas.drawBitmap(this.d3Bitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
                    if (Apptop_MirrorNewActivity.this.textDataList == null) {
                        btmWidth2 = 0;
                        while (btmWidth2 < Apptop_MirrorNewActivity.this.textDataList.size()) {
                            Matrix mat = new Matrix();
                            mat.set(Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).imageSaveMatrix);
                            mat.postScale(f, f);
                            mat.postTranslate(((float) (-(wP - btmWidth))) / 2.0f, ((float) (-(wH - btmHeight))) / 2.0f);
                            bitmapCanvas.setMatrix(mat);
                            float scale2 = f;
                            bitmapCanvas.drawText(Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).message, Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).xPos, Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).yPos, Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).textPaint);
                            btmWidth2++;
                            f = scale2;
                        }
                    }
                    bitmap = this.frameBitmap;
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        bitmapCanvas.setMatrix(matrix);
                        bitmapCanvas.drawBitmap(this.frameBitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
                    }
                    resultPath = null;
                    if (saveToFile) {
                        resultPath2 = new StringBuilder(Environment.getExternalStorageDirectory().toString());
                        stringBuilder2 = "/";
                        resultPath2.append(stringBuilder2);
                        resultPath2.append(Apptop_MirrorNewActivity.this.getString(R.string.app_name));
                        resultPath2.append(stringBuilder2);
                        resultPath2.append(System.currentTimeMillis());
                        resultPath2.append(".jpg");
                        new File(resultPath2.toString()).getParentFile().mkdirs();
                        try {
                            FileOutputStream out = new FileOutputStream(resultPath2.toString());
                            savedBitmap.compress(CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        resultPath = resultPath2.toString();
                    }
                    savedBitmap.recycle();
                    reset(i, i2, false);
                    this.mirrorModeList[this.currentModeIndex].setSrcRect(srcRect);
                    return resultPath;
                }
            }
            MirrorMode mirrorMode = saveMode;
            if (Apptop_MirrorNewActivity.this.textDataList == null) {
            } else {
                btmWidth2 = 0;
                while (btmWidth2 < Apptop_MirrorNewActivity.this.textDataList.size()) {
                    Matrix mat2 = new Matrix();
                    mat2.set(Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).imageSaveMatrix);
                    mat2.postScale(f, f);
                    mat2.postTranslate(((float) (-(wP - btmWidth))) / 2.0f, ((float) (-(wH - btmHeight))) / 2.0f);
                    bitmapCanvas.setMatrix(mat2);
                    float scale22 = f;
                    bitmapCanvas.drawText(Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).message, Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).xPos, Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).yPos, Apptop_MirrorNewActivity.this.textDataList.get(btmWidth2).textPaint);
                    btmWidth2++;
                    f = scale22;
                }
            }
            bitmap = this.frameBitmap;
            bitmapCanvas.setMatrix(matrix);
            bitmapCanvas.drawBitmap(this.frameBitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            resultPath = null;
            if (saveToFile) {
                resultPath2 = new StringBuilder(Environment.getExternalStorageDirectory().toString());
                stringBuilder2 = "/";
                resultPath2.append(stringBuilder2);
                resultPath2.append(Apptop_MirrorNewActivity.this.getString(R.string.app_name));
                resultPath2.append(stringBuilder2);
                resultPath2.append(System.currentTimeMillis());
                resultPath2.append(".jpg");
                new File(resultPath2.toString()).getParentFile().mkdirs();
                FileOutputStream out2 = null;
                try {
                    out2 = new FileOutputStream(resultPath2.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                savedBitmap.compress(CompressFormat.JPEG, 90, out2);
                try {
                    out2.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resultPath = resultPath2.toString();
            }
            savedBitmap.recycle();
            reset(i, i2, false);
            this.mirrorModeList[this.currentModeIndex].setSrcRect(srcRect);
            return resultPath;
        }

        private void setCurrentMode(int index) {
            this.currentModeIndex = index;
        }

        public MirrorMode getCurrentMirrorMode() {
            return this.mirrorModeList[this.currentModeIndex];
        }

        private void createModes() {
            RectF rectF = this.srcRect3;
            RectF rectF2 = this.destRect1;
            RectF rectF3 = this.destRect3;
            this.modeX = new MirrorMode(4, rectF, rectF2, rectF2, rectF3, rectF3, Apptop_MirrorNewActivity.this.matrixMirrothis, this.f510I, Apptop_MirrorNewActivity.this.matrixMirrothis, this.tMode3, this.totalArea3);
            RectF rectF4 = this.srcRect3;
            rectF = this.destRect1;
            RectF rectF5 = this.destRect4;
            this.modeX2 = new MirrorMode(4, rectF4, rectF, rectF5, rectF, rectF5, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.f510I, this.tMode3, this.totalArea3);
            rectF4 = this.srcRect3;
            rectF = this.destRect3;
            rectF5 = this.destRect2;
            this.modeX3 = new MirrorMode(4, rectF4, rectF, rectF5, rectF, rectF5, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.f510I, this.tMode3, this.totalArea3);
            rectF4 = this.srcRect3;
            rectF = this.destRect1;
            this.modeX8 = new MirrorMode(4, rectF4, rectF, rectF, rectF, rectF, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirror2, Apptop_MirrorNewActivity.this.matrixMirror3, this.tMode3, this.totalArea3);
            int m9TouchMode = 4;
            if (this.tMode3 == 0) {
                m9TouchMode = 0;
            }
            rectF5 = this.srcRect3;
            RectF rectF6 = this.destRect2;
            this.modeX9 = new MirrorMode(4, rectF5, rectF6, rectF6, rectF6, rectF6, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirror2, Apptop_MirrorNewActivity.this.matrixMirror3, m9TouchMode, this.totalArea3);
            int m10TouchMode = 3;
            if (this.tMode3 == 1) {
                m10TouchMode = 1;
            }
            rectF3 = this.srcRect3;
            RectF rectF7 = this.destRect3;
            this.modeX10 = new MirrorMode(4, rectF3, rectF7, rectF7, rectF7, rectF7, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirror2, Apptop_MirrorNewActivity.this.matrixMirror3, m10TouchMode, this.totalArea3);
            int m11TouchMode = 4;
            if (this.tMode3 == 0) {
                m11TouchMode = 3;
            }
            rectF3 = this.srcRect3;
            rectF7 = this.destRect4;
            MirrorMode mirrorMode = new MirrorMode(4, rectF3, rectF7, rectF7, rectF7, rectF7, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirror2, Apptop_MirrorNewActivity.this.matrixMirror3, m11TouchMode, this.totalArea3);
            this.modeX11 = mirrorMode;
            rectF2 = this.srcRect1;
            RectF rectF8 = this.destRect1X;
            this.modeX4 = new MirrorMode(2, rectF2, rectF8, rectF8, Apptop_MirrorNewActivity.this.matrixMirrothis, this.tMode1, this.totalArea1);
            int m5TouchMode = 4;
            int i = this.tMode1;
            if (i == 0) {
                m5TouchMode = 0;
            } else if (i == 5) {
                m5TouchMode = 5;
            }
            RectF rectF9 = this.srcRect1;
            rectF7 = this.destRect2X;
            this.modeX5 = new MirrorMode(2, rectF9, rectF7, rectF7, Apptop_MirrorNewActivity.this.matrixMirrothis, m5TouchMode, this.totalArea1);
            rectF3 = this.srcRect2;
            rectF6 = this.destRect1Y;
            this.modeX6 = new MirrorMode(2, rectF3, rectF6, rectF6, Apptop_MirrorNewActivity.this.matrixMirror2, this.tMode2, this.totalArea2);
            i = 3;
            int i2 = this.tMode2;
            if (i2 == 1) {
                i = 1;
            } else if (i2 == 6) {
                i = 6;
            }
            rectF7 = this.srcRect2;
            RectF rectF10 = this.destRect2Y;
            this.modeX7 = new MirrorMode(2, rectF7, rectF10, rectF10, Apptop_MirrorNewActivity.this.matrixMirror2, i, this.totalArea2);
            this.modeX12 = new MirrorMode(2, this.srcRect1, this.destRect1X, this.destRect2X, Apptop_MirrorNewActivity.this.matrixMirror4, this.tMode1, this.totalArea1);
            this.modeX13 = new MirrorMode(2, this.srcRect2, this.destRect1Y, this.destRect2Y, Apptop_MirrorNewActivity.this.matrixMirror4, this.tMode2, this.totalArea2);
            rectF9 = this.srcRect1;
            RectF rectF11 = this.destRect1X;
            this.modeX14 = new MirrorMode(2, rectF9, rectF11, rectF11, Apptop_MirrorNewActivity.this.matrixMirror3, this.tMode1, this.totalArea1);
            rectF9 = this.srcRect2;
            rectF11 = this.destRect1Y;
            this.modeX15 = new MirrorMode(2, rectF9, rectF11, rectF11, Apptop_MirrorNewActivity.this.matrixMirror3, this.tMode2, this.totalArea2);
            this.modeX16 = new MirrorMode(4, this.srcRectPaper, this.dstRectPapethis, this.dstRectPaper2, this.dstRectPaper3, this.dstRectPaper4, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.f510I, this.tMode1, this.totalArea1);
            rectF8 = this.srcRectPaper;
            rectF3 = this.dstRectPapethis;
            rectF6 = this.dstRectPaper3;
            this.modeX17 = new MirrorMode(4, rectF8, rectF3, rectF6, rectF6, rectF3, this.f510I, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.tMode1, this.totalArea1);
            rectF8 = this.srcRectPaper;
            rectF3 = this.dstRectPaper2;
            rectF6 = this.dstRectPaper4;
            this.modeX18 = new MirrorMode(4, rectF8, rectF3, rectF6, rectF3, rectF6, this.f510I, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.tMode1, this.totalArea1);
            rectF8 = this.srcRectPaper;
            rectF3 = this.dstRectPapethis;
            rectF6 = this.dstRectPaper2;
            this.modeX19 = new MirrorMode(4, rectF8, rectF3, rectF6, rectF6, rectF3, this.f510I, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.tMode1, this.totalArea1);
            rectF8 = this.srcRectPaper;
            rectF3 = this.dstRectPaper4;
            rectF6 = this.dstRectPaper3;
            this.modeX20 = new MirrorMode(4, rectF8, rectF3, rectF6, rectF6, rectF3, this.f510I, Apptop_MirrorNewActivity.this.matrixMirrothis, Apptop_MirrorNewActivity.this.matrixMirrothis, this.tMode1, this.totalArea1);
            MirrorMode[] mirrorModeArr = this.mirrorModeList;
            mirrorModeArr[0] = this.modeX4;
            mirrorModeArr[1] = this.modeX5;
            mirrorModeArr[2] = this.modeX6;
            MirrorMode mirrorMode3 = this.modeX7;
            mirrorModeArr[3] = mirrorMode3;
            mirrorModeArr[4] = this.modeX8;
            mirrorModeArr[5] = this.modeX9;
            mirrorModeArr[6] = this.modeX10;
            mirrorModeArr[7] = this.modeX11;
            mirrorModeArr[8] = this.modeX12;
            mirrorModeArr[9] = this.modeX13;
            mirrorModeArr[10] = this.modeX14;
            mirrorModeArr[11] = this.modeX15;
            mirrorModeArr[12] = this.modeX;
            mirrorModeArr[13] = this.modeX2;
            mirrorModeArr[14] = this.modeX3;
            mirrorModeArr[15] = mirrorMode3;
            mirrorModeArr[16] = this.modeX17;
            mirrorModeArr[17] = this.modeX18;
            mirrorModeArr[18] = this.modeX19;
            mirrorModeArr[19] = this.modeX20;
        }

        public Bitmap getBitmap() {
            setDrawingCacheEnabled(true);
            buildDrawingCache();
            Bitmap bmp = Bitmap.createBitmap(getDrawingCache());
            setDrawingCacheEnabled(false);
            return bmp;
        }

        public void setFrame(int index) {
            Bitmap bitmap = this.frameBitmap;
            if (!(bitmap == null || bitmap.isRecycled())) {
                this.frameBitmap.recycle();
                this.frameBitmap = null;
            }
            if (index == 0) {
                postInvalidate();
                return;
            }
            this.frameBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderRes[index]);
            postInvalidate();
        }

        private void createMatrix(int widthPixels, int heightPixels) {
            this.f510I.reset();
            Apptop_MirrorNewActivity.this.matrixMirrothis.reset();
            Apptop_MirrorNewActivity.this.matrixMirrothis.postScale(-1.0f, 1.0f);
            Apptop_MirrorNewActivity.this.matrixMirrothis.postTranslate((float) widthPixels, 0.0f);
            Apptop_MirrorNewActivity.this.matrixMirror2.reset();
            Apptop_MirrorNewActivity.this.matrixMirror2.postScale(1.0f, -1.0f);
            Apptop_MirrorNewActivity.this.matrixMirror2.postTranslate(0.0f, (float) heightPixels);
            Apptop_MirrorNewActivity.this.matrixMirror3.reset();
            Apptop_MirrorNewActivity.this.matrixMirror3.postScale(-1.0f, -1.0f);
            Apptop_MirrorNewActivity.this.matrixMirror3.postTranslate((float) widthPixels, (float) heightPixels);
        }

        private void createRectX(int widthPixels, int heightPixels) {
            int i = widthPixels;
            int i2 = heightPixels;
            float destH = ((float) i) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX);
            float destW = ((float) i) / 2.0f;
            float destX = 0.0f;
            float destY = (float) Apptop_MirrorNewActivity.this.initialYPos;
            if (destH > ((float) i2)) {
                destH = (float) i2;
                destW = ((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * destH) / 2.0f;
                destX = (((float) i) / 2.0f) - destW;
            }
            float destY2 = ((float) Apptop_MirrorNewActivity.this.initialYPos) + ((((float) i2) - destH) / 2.0f);
            destY = 0.0f;
            float srcY = 0.0f;
            float srcX2 = (float) this.width;
            float srcY2 = (float) this.height;
            this.destRect1X = new RectF(destX, destY2, destW + destX, destH + destY2);
            float destXX = destX + destW;
            this.destRect2X = new RectF(destXX, destY2, destW + destXX, destH + destY2);
            this.totalArea1 = new RectF(destX, destY2, destW + destXX, destH + destY2);
            this.tMode1 = 1;
            float f = Apptop_MirrorNewActivity.this.mulX * ((float) this.height);
            float f2 = Apptop_MirrorNewActivity.this.mulY * 2.0f;
            int i3 = this.width;
            if (f <= f2 * ((float) i3)) {
                destY = (((float) i3) - (((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * ((float) this.height)) / 2.0f)) / 2.0f;
                srcX2 = destY + (((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * ((float) this.height)) / 2.0f);
            } else {
                srcY = (((float) this.height) - (((float) (i3 * 2)) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX))) / 2.0f;
                srcY2 = srcY + (((float) (this.width * 2)) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX));
                this.tMode1 = 5;
            }
            this.srcRect1 = new RectF(destY, srcY, srcX2, srcY2);
            this.srcRectPaper = new RectF(destY, srcY, ((srcX2 - destY) / 2.0f) + destY, srcY2);
            float destWPapar = destW / 2.0f;
            this.dstRectPapethis = new RectF(destX, destY2, destWPapar + destX, destH + destY2);
            f = destX + destWPapar;
            this.dstRectPaper2 = new RectF(f, destY2, destWPapar + f, destH + destY2);
            f += destWPapar;
            this.dstRectPaper3 = new RectF(f, destY2, destWPapar + f, destH + destY2);
            f += destWPapar;
            this.dstRectPaper4 = new RectF(f, destY2, destWPapar + f, destH + destY2);
        }

        private void createRectY(int widthPixels, int heightPixels) {
            int i = widthPixels;
            int i2 = heightPixels;
            float destH = (((float) i) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX)) / 2.0f;
            float destW = (float) i;
            float destX = 0.0f;
            float destY = (float) Apptop_MirrorNewActivity.this.initialYPos;
            if (destH > ((float) i2)) {
                destH = (float) i2;
                destW = ((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * destH) / 2.0f;
                destX = (((float) i) / 2.0f) - destW;
            }
            float destY2 = ((float) Apptop_MirrorNewActivity.this.initialYPos) + ((((float) i2) - (destH * 2.0f)) / 2.0f);
            this.destRect1Y = new RectF(destX, destY2, destW + destX, destH + destY2);
            destY = destY2 + destH;
            this.destRect2Y = new RectF(destX, destY, destW + destX, destH + destY);
            this.totalArea2 = new RectF(destX, destY2, destW + destX, destH + destY);
            float srcX = 0.0f;
            float srcY = 0.0f;
            float srcX2 = (float) this.width;
            float srcY2 = (float) this.height;
            this.tMode2 = 0;
            float f = (Apptop_MirrorNewActivity.this.mulX * 2.0f) * ((float) this.height);
            float f2 = Apptop_MirrorNewActivity.this.mulY;
            int i3 = this.width;
            if (f > f2 * ((float) i3)) {
                srcY = (((float) this.height) - (((Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX) * ((float) this.width)) / 2.0f)) / 2.0f;
                srcY2 = srcY + (((Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX) * ((float) this.width)) / 2.0f);
            } else {
                srcX = (((float) i3) - (((float) (this.height * 2)) * (Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY))) / 2.0f;
                srcX2 = srcX + (((float) (this.height * 2)) * (Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY));
                this.tMode2 = 6;
            }
            this.srcRect2 = new RectF(srcX, srcY, srcX2, srcY2);
        }

        private void createRectXY(int widthPixels, int heightPixels) {
            int i = widthPixels;
            int i2 = heightPixels;
            float destH = (((float) i) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX)) / 2.0f;
            float destW = ((float) i) / 2.0f;
            float destX = 0.0f;
            float destY = (float) Apptop_MirrorNewActivity.this.initialYPos;
            if (destH > ((float) i2)) {
                destH = (float) i2;
                destW = ((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * destH) / 2.0f;
                destX = (((float) i) / 2.0f) - destW;
            }
            float destY2 = ((float) Apptop_MirrorNewActivity.this.initialYPos) + ((((float) i2) - (destH * 2.0f)) / 2.0f);
            destY = 0.0f;
            float srcY = 0.0f;
            float srcX2 = (float) this.width;
            float srcY2 = (float) this.height;
            this.destRect1 = new RectF(destX, destY2, destW + destX, destH + destY2);
            float destX2 = destX + destW;
            this.destRect2 = new RectF(destX2, destY2, destW + destX2, destH + destY2);
            float destY22 = destY2 + destH;
            this.destRect3 = new RectF(destX, destY22, destW + destX, destH + destY22);
            this.destRect4 = new RectF(destX2, destY22, destW + destX2, destH + destY22);
            this.totalArea3 = new RectF(destX, destY2, destW + destX2, destH + destY22);
            float f = Apptop_MirrorNewActivity.this.mulX * ((float) this.height);
            float f2 = Apptop_MirrorNewActivity.this.mulY;
            int i3 = this.width;
            if (f <= f2 * ((float) i3)) {
                destY = (((float) i3) - ((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * ((float) this.height))) / 2.0f;
                srcX2 = destY + ((Apptop_MirrorNewActivity.this.mulX / Apptop_MirrorNewActivity.this.mulY) * ((float) this.height));
                this.tMode3 = 1;
            } else {
                srcY = (((float) this.height) - (((float) i3) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX))) / 2.0f;
                srcY2 = srcY + (((float) this.width) * (Apptop_MirrorNewActivity.this.mulY / Apptop_MirrorNewActivity.this.mulX));
                this.tMode3 = 0;
            }
            this.srcRect3 = new RectF(destY, srcY, srcX2, srcY2);
        }

        @SuppressLint({"ResourceAsColor"})
        public void onDraw(Canvas canvas) {
            Bitmap bitmap;
            canvas.drawColor(this.defaultColor);
            if (Apptop_MirrorNewActivity.this.filterBitmap == null) {
                drawMode(canvas, Apptop_MirrorNewActivity.this.sourceBitmap, this.mirrorModeList[this.currentModeIndex], this.f510I);
            } else {
                drawMode(canvas, Apptop_MirrorNewActivity.this.filterBitmap, this.mirrorModeList[this.currentModeIndex], this.f510I);
            }
            if (this.d3Mode) {
                bitmap = this.d3Bitmap;
                if (!(bitmap == null || bitmap.isRecycled())) {
                    canvas.setMatrix(this.f510I);
                    canvas.drawBitmap(this.d3Bitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
                }
            }
            if (Apptop_MirrorNewActivity.this.showText) {
                for (int i = 0; i < Apptop_MirrorNewActivity.this.textDataList.size(); i++) {
                    this.textMatrix.set(Apptop_MirrorNewActivity.this.textDataList.get(i).imageSaveMatrix);
                    this.textMatrix.postConcat(this.f510I);
                    canvas.setMatrix(this.textMatrix);
                    canvas.drawText(Apptop_MirrorNewActivity.this.textDataList.get(i).message, Apptop_MirrorNewActivity.this.textDataList.get(i).xPos, Apptop_MirrorNewActivity.this.textDataList.get(i).yPos, Apptop_MirrorNewActivity.this.textDataList.get(i).textPaint);
                    canvas.setMatrix(this.f510I);
                    canvas.drawRect(0.0f, 0.0f, this.mirrorModeList[this.currentModeIndex].rectTotalArea.left, (float) Apptop_MirrorNewActivity.this.screenHeightPixels, this.textRectPaint);
                    canvas.drawRect(0.0f, 0.0f, (float) Apptop_MirrorNewActivity.this.screenWidthPixels, this.mirrorModeList[this.currentModeIndex].rectTotalArea.top, this.textRectPaint);
                    canvas.drawRect(this.mirrorModeList[this.currentModeIndex].rectTotalArea.right, 0.0f, (float) Apptop_MirrorNewActivity.this.screenWidthPixels, (float) Apptop_MirrorNewActivity.this.screenHeightPixels, this.textRectPaint);
                    canvas.drawRect(0.0f, this.mirrorModeList[this.currentModeIndex].rectTotalArea.bottom, (float) Apptop_MirrorNewActivity.this.screenWidthPixels, (float) Apptop_MirrorNewActivity.this.screenHeightPixels, this.textRectPaint);
                }
            }
            bitmap = this.frameBitmap;
            if (!(bitmap == null || bitmap.isRecycled())) {
                canvas.setMatrix(this.f510I);
                canvas.drawBitmap(this.frameBitmap, null, this.mirrorModeList[this.currentModeIndex].rectTotalArea, this.framePaint);
            }
            super.onDraw(canvas);
        }

        private void drawMode(Canvas canvas, Bitmap bitmap, MirrorMode mirrorMode, Matrix matrix) {
            canvas.setMatrix(matrix);
            canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect1, this.framePaint);
            this.m1.set(mirrorMode.matrix1);
            this.m1.postConcat(matrix);
            canvas.setMatrix(this.m1);
            if (!(bitmap == null || bitmap.isRecycled())) {
                canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect2, this.framePaint);
            }
            if (mirrorMode.count == 4) {
                this.m2.set(mirrorMode.matrix2);
                this.m2.postConcat(matrix);
                canvas.setMatrix(this.m2);
                if (!(bitmap == null || bitmap.isRecycled())) {
                    canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect3, this.framePaint);
                }
                this.m3.set(mirrorMode.matrix3);
                this.m3.postConcat(matrix);
                canvas.setMatrix(this.m3);
                if (bitmap != null && !bitmap.isRecycled()) {
                    canvas.drawBitmap(bitmap, mirrorMode.getDrawBitmapSrc(), mirrorMode.rect4, this.framePaint);
                }
            }
        }

        public boolean onTouchEvent(MotionEvent event) {
            boolean z = false;
            float x = event.getX();
            float y = event.getY();
            int action = event.getAction();
            if (action == 0) {
                boolean z2;
                z2 = x < ((float) (Apptop_MirrorNewActivity.this.screenWidthPixels / 2));
                this.isTouchStartedLeft = z2;
                if (y < ((float) (Apptop_MirrorNewActivity.this.screenHeightPixels / 2))) {
                    z = true;
                }
                this.isTouchStartedTop = z;
                this.oldX = x;
                this.oldY = y;
            } else if (action == 2) {
                moveGrid(this.mirrorModeList[this.currentModeIndex].getSrcRect(), x - this.oldX, y - this.oldY);
                this.mirrorModeList[this.currentModeIndex].updateBitmapSrc();
                this.oldX = x;
                this.oldY = y;
            }
            postInvalidate();
            return true;
        }

        void moveGrid(RectF srcRect, float x, float y) {
            float f;
            int i;
            if (!(this.mirrorModeList[this.currentModeIndex].touchMode == 1 || this.mirrorModeList[this.currentModeIndex].touchMode == 4)) {
                if (this.mirrorModeList[this.currentModeIndex].touchMode != 6) {
                    if (this.mirrorModeList[this.currentModeIndex].touchMode == 0 || this.mirrorModeList[this.currentModeIndex].touchMode == 3 || this.mirrorModeList[this.currentModeIndex].touchMode == 5) {
                        if (this.mirrorModeList[this.currentModeIndex].touchMode == 3) {
                            y *= -1.0f;
                        }
                        if (this.isTouchStartedTop && this.mirrorModeList[this.currentModeIndex].touchMode != 5) {
                            y *= -1.0f;
                        }
                        if (srcRect.top + y < 0.0f) {
                            y = -srcRect.top;
                        }
                        f = srcRect.bottom + y;
                        i = this.height;
                        if (f >= ((float) i)) {
                            y = ((float) i) - srcRect.bottom;
                        }
                        srcRect.top += y;
                        srcRect.bottom += y;
                        return;
                    }
                    return;
                }
            }
            if (this.mirrorModeList[this.currentModeIndex].touchMode == 4) {
                x *= -1.0f;
            }
            if (this.isTouchStartedLeft && this.mirrorModeList[this.currentModeIndex].touchMode != 6) {
                x *= -1.0f;
            }
            if (srcRect.left + x < 0.0f) {
                x = -srcRect.left;
            }
            f = srcRect.right + x;
            i = this.width;
            if (f >= ((float) i)) {
                x = ((float) i) - srcRect.right;
            }
            srcRect.left += x;
            srcRect.right += x;
        }
    }

    final class MyMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private MediaScannerConnection mConn;
        private String mFilename;
        private String mMimetype;

        public MyMediaScannerConnectionClient(Context ctx, File file, String mimetype) {
            this.mFilename = file.getAbsolutePath();
            this.mConn = new MediaScannerConnection(ctx, this);
            this.mConn.connect();
        }

        public void onMediaScannerConnected() {
            this.mConn.scanFile(this.mFilename, this.mMimetype);
        }

        public void onScanCompleted(String path, Uri uri) {
            this.mConn.disconnect();
        }
    }

    private class SaveImageTask extends AsyncTask<Object, Object, Object> {
        ProgressDialog progressDialog;
        String resultPath;

        private SaveImageTask() {
            this.resultPath = null;
        }

        protected Object doInBackground(Object... arg0) {
            this.resultPath = Apptop_MirrorNewActivity.this.mirrorView.saveBitmap(true, Apptop_MirrorNewActivity.this.screenWidthPixels, Apptop_MirrorNewActivity.this.screenHeightPixels);
            return null;
        }

        protected void onPreExecute() {
            this.progressDialog = new ProgressDialog(Apptop_MirrorNewActivity.this);
            this.progressDialog.setMessage("Saving image ...");
            this.progressDialog.show();
        }

        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.cancel();
            }
            if (this.resultPath != null) {
                Intent intent = new Intent(Apptop_MirrorNewActivity.this, Apptop_ImageShareActivity.class);
                intent.putExtra("imagePath", this.resultPath);
                Apptop_MirrorNewActivity.this.startActivity(intent);
                showInterstitialAd();
                Apptop_MirrorNewActivity.this.finish();
            }
            Apptop_MirrorNewActivity mirrorNewActivity = Apptop_MirrorNewActivity.this;
            MyMediaScannerConnectionClient myMediaScannerConnectionClient = new MyMediaScannerConnectionClient(mirrorNewActivity.getApplicationContext(), new File(this.resultPath), null);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities.Apptop_MirrorNewActivity$1 */
    class C08731 implements FontChoosedListener {
        C08731() {
        }

        public void onOk(TextData textData) {
            Apptop_MirrorNewActivity.this.canvasText.addTextView(textData);
            Apptop_MirrorNewActivity.this.getSupportFragmentManager().beginTransaction().remove(Apptop_MirrorNewActivity.this.fontFragment).commit();
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities.Apptop_MirrorNewActivity$2 */
    class C08742 implements BitmapReady {
        C08742() {
        }

        public void onBitmapReady(Bitmap bitmap) {
            Apptop_MirrorNewActivity mirrorNewActivity = Apptop_MirrorNewActivity.this;
            mirrorNewActivity.filterBitmap = bitmap;
            mirrorNewActivity.mirrorView.postInvalidate();
        }
    }
    void loadInterstitialAd() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
        this.mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
//                Apptop_CollageActivity.this.loadInterstitialAd();

            }
        });
    }

    void showInterstitialAd() {
        if (this.mInterstitialAd != null && this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.show();
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities.Apptop_MirrorNewActivity$3 */
    class C08753 implements RecyclerAdapterIndexChangedListener {
        C08753() {
        }

        public void onIndexChanged(int i) {
            Apptop_MirrorNewActivity.this.mirrorView.setFrame(i);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities.Apptop_MirrorNewActivity$4 */
    class C08764 implements SingleTap {
        C08764() {
        }

        public void onSingleTap(TextData textData) {
            Apptop_MirrorNewActivity.this.fontFragment = new FontFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable("text_data", textData);
            Apptop_MirrorNewActivity.this.fontFragment.setArguments(arguments);
            Apptop_MirrorNewActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.text_view_fragment_container, Apptop_MirrorNewActivity.this.fontFragment, "FONT_FRAGMENT").commit();
            Log.e(Apptop_MirrorNewActivity.TAG, "replace fragment");
            Apptop_MirrorNewActivity.this.fontFragment.setFontChoosedListener(Apptop_MirrorNewActivity.this.fontChoosedListener);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities.Apptop_MirrorNewActivity$5 */
    class C08775 implements ApplyTextInterface {
        C08775() {
        }

        public void onCancel() {
            Apptop_MirrorNewActivity mirrorNewActivity = Apptop_MirrorNewActivity.this;
            mirrorNewActivity.showText = true;
            mirrorNewActivity.mainLayout.removeView(Apptop_MirrorNewActivity.this.canvasText);
            Apptop_MirrorNewActivity.this.mirrorView.postInvalidate();
        }

        public void onOk(ArrayList<TextData> arrayList) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((TextData) it.next()).setImageSaveMatrix(Apptop_MirrorNewActivity.this.mirrorView.f510I);
            }
            Apptop_MirrorNewActivity mirrorNewActivity = Apptop_MirrorNewActivity.this;
            mirrorNewActivity.textDataList = arrayList;
            mirrorNewActivity.showText = true;
            if (mirrorNewActivity.mainLayout == null) {
                mirrorNewActivity = Apptop_MirrorNewActivity.this;
                mirrorNewActivity.mainLayout = mirrorNewActivity.findViewById(R.id.layout_mirror_activity);
            }
            Apptop_MirrorNewActivity.this.mainLayout.removeView(Apptop_MirrorNewActivity.this.canvasText);
            Apptop_MirrorNewActivity.this.mirrorView.postInvalidate();
        }
    }




    @SuppressLint({"NewApi", "WrongConstant"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("MrrorNewActivity", "onCreate");
        getWindow().addFlags(1024);
        Bundle extras = getIntent().getExtras();
        this.sourceBitmap = Utility.getScaledBitmapFromId(this, extras.getLongArray("photo_id_list")[0], extras.getIntArray("photo_orientation_list")[0], -1, false);
        if (this.sourceBitmap == null) {
            Toast msg = Toast.makeText(this, "Could not load the photo, please use another GALLERY app!", 1);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
            finish();
            return;
        }

        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        if (Glob.isOnline(this)) {
            loadInterstitialAd();
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        this.screenHeightPixels = metrics.heightPixels;
        this.screenWidthPixels = metrics.widthPixels;
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        if (this.screenWidthPixels <= 0) {
            this.screenWidthPixels = width;
        }
        if (this.screenHeightPixels <= 0) {
            this.screenHeightPixels = height;
        }
        this.mirrorView = new MirrorView(this, this.screenWidthPixels, this.screenHeightPixels);
        setContentView(R.layout.activity_mirror_new);

        this.mainLayout = findViewById(R.id.layout_mirror_activity);
        this.mainLayout.addView(this.mirrorView);
        this.viewFlipper = findViewById(R.id.mirror_view_flipper);
        this.viewFlipper.bringToFront();
        findViewById(R.id.mirror_footer).bringToFront();
        this.slideLeftIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        this.slideLeftOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        this.slideRightIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        this.slideRightOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        findViewById(R.id.mirror_header).bringToFront();
        addEffectFragment();
        setSelectedTab(0);
    }

    @SuppressLint({"WrongConstant"})
    private boolean isOnline() {
        try {
            return ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo().isConnectedOrConnecting();
        } catch (Exception e) {
            return false;
        }
    }

    void addEffectFragment() {
        if (this.effectFragment == null) {
            String str = "MY_EFFECT_FRAGMENT";
            this.effectFragment = (EffectFragment) getSupportFragmentManager().findFragmentByTag(str);
            EffectFragment effectFragment = this.effectFragment;
            if (effectFragment == null) {
                this.effectFragment = new EffectFragment();
                Log.e(TAG, "EffectFragment == null");
                this.effectFragment.setBitmap(this.sourceBitmap);
                this.effectFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction().add(R.id.mirror_effect_fragment_container, this.effectFragment, str).commit();
            } else {
                effectFragment.setBitmap(this.sourceBitmap);
                this.effectFragment.setSelectedTabIndex(0);
            }
            this.effectFragment.setBitmapReadyListener(new C08742());
            this.effectFragment.setBorderIndexChangedListener(new C08753());
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("show_text", this.showText);
        savedInstanceState.putSerializable("text_data", this.textDataList);
        FontFragment fontFragment = this.fontFragment;
        if (fontFragment != null && fontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.showText = savedInstanceState.getBoolean("show_text");
        this.textDataList = (ArrayList) savedInstanceState.getSerializable("text_data");
        if (this.textDataList == null) {
            this.textDataList = new ArrayList();
        }
    }

    public void myClickHandler(View view) {
        int id = view.getId();
        MirrorView mirrorView = this.mirrorView;
        mirrorView.drawSavedImage = false;
        if (id == R.id.button_save_mirror_image) {
            new SaveImageTask().execute();
        } else if (id == R.id.closeScreen) {
            backButtonAlertBuilder();
        } else if (id == R.id.button_mirror) {
            setSelectedTab(0);
        } else if (id == R.id.button_mirror_frame) {
            setSelectedTab(4);
        } else if (id == R.id.button_mirror_ratio) {
            setSelectedTab(2);
        } else if (id == R.id.button_mirror_effect) {
            setSelectedTab(3);
        } else if (id == R.id.button_mirror_adj) {
            setSelectedTab(5);
        } else if (id == R.id.button_mirror_3d) {
            setSelectedTab(1);
        } else if (id == R.id.button_3d_1) {
            set3dMode(0);
        } else if (id == R.id.button_3d_2) {
            set3dMode(1);
        } else if (id == R.id.button_3d_3) {
            set3dMode(2);
        } else if (id == R.id.button_3d_4) {
            set3dMode(3);
        } else if (id == R.id.button_3d_5) {
            set3dMode(4);
        } else if (id == R.id.button_3d_6) {
            set3dMode(5);
        } else if (id == R.id.button_3d_7) {
            set3dMode(6);
        } else if (id == R.id.button_3d_8) {
            set3dMode(7);
        } else if (id == R.id.button_3d_9) {
            set3dMode(8);
        } else if (id == R.id.button_3d_10) {
            set3dMode(9);
        } else if (id == R.id.button_3d_11) {
            set3dMode(10);
        } else if (id == R.id.button_3d_12) {
            set3dMode(11);
        } else if (id == R.id.button_3d_13) {
            set3dMode(12);
        } else if (id == R.id.button_3d_14) {
            set3dMode(13);
        } else if (id == R.id.button_3d_15) {
            set3dMode(14);
        } else if (id == R.id.button_3d_16) {
            set3dMode(15);
        } else if (id == R.id.button_3d_17) {
            set3dMode(16);
        } else if (id == R.id.button_3d_18) {
            set3dMode(17);
        } else if (id == R.id.button_3d_19) {
            set3dMode(18);
        } else if (id == R.id.button_3d_20) {
            set3dMode(19);
        } else if (id == R.id.button_3d_21) {
            set3dMode(20);
        } else if (id == R.id.button_3d_22) {
            set3dMode(21);
        } else if (id == R.id.button_3d_23) {
            set3dMode(22);
        } else if (id == R.id.button_3d_24) {
            set3dMode(23);
        } else if (id == R.id.button11) {
            this.mulX = 1.0f;
            this.mulY = 1.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(0);
        } else if (id == R.id.button21) {
            this.mulX = 2.0f;
            this.mulY = 1.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(1);
        } else if (id == R.id.button12) {
            this.mulX = 1.0f;
            this.mulY = 2.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(2);
        } else if (id == R.id.button32) {
            this.mulX = 3.0f;
            this.mulY = 2.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(3);
        } else if (id == R.id.button23) {
            this.mulX = 2.0f;
            this.mulY = 3.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(4);
        } else if (id == R.id.button43) {
            this.mulX = 4.0f;
            this.mulY = 3.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(5);
        } else if (id == R.id.button34) {
            this.mulX = 3.0f;
            this.mulY = 4.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(6);
        } else if (id == R.id.button45) {
            this.mulX = 4.0f;
            this.mulY = 5.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(7);
        } else if (id == R.id.button57) {
            this.mulX = 5.0f;
            this.mulY = 7.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(8);
        } else if (id == R.id.button169) {
            this.mulX = 16.0f;
            this.mulY = 9.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(9);
        } else if (id == R.id.button916) {
            this.mulX = 9.0f;
            this.mulY = 16.0f;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setRatioButtonBg(10);
        } else if (id == R.id.button_m1) {
            mirrorView.setCurrentMode(0);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(0);
        } else if (id == R.id.button_m2) {
            mirrorView.setCurrentMode(1);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(1);
        } else if (id == R.id.button_m3) {
            mirrorView.setCurrentMode(2);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(2);
        } else if (id == R.id.button_m4) {
            mirrorView.setCurrentMode(3);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(3);
        } else if (id == R.id.button_m5) {
            mirrorView.setCurrentMode(4);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(4);
        } else if (id == R.id.button_m6) {
            mirrorView.setCurrentMode(5);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(5);
        } else if (id == R.id.button_m7) {
            mirrorView.setCurrentMode(6);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(6);
        } else if (id == R.id.button_m8) {
            mirrorView.setCurrentMode(7);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(7);
        } else if (id == R.id.button_m9) {
            mirrorView.setCurrentMode(8);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(8);
        } else if (id == R.id.button_m10) {
            mirrorView.setCurrentMode(9);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(9);
        } else if (id == R.id.button_m11) {
            mirrorView.setCurrentMode(10);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(10);
        } else if (id == R.id.button_m12) {
            mirrorView.setCurrentMode(11);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(11);
        } else if (id == R.id.button_m13) {
            mirrorView.setCurrentMode(12);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(12);
        } else if (id == R.id.button_m14) {
            mirrorView.setCurrentMode(13);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(13);
        } else if (id == R.id.button_m15) {
            mirrorView.setCurrentMode(14);
            mirrorView = this.mirrorView;
            mirrorView.d3Mode = false;
            mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, true);
            setMirrorButtonBg(14);
        } else if (id == R.id.button_mirror_text) {
            addCanvasTextView();
            clearViewFlipper();
        } else if (id != R.id.button_mirror_sticker) {
            this.effectFragment.myClickHandler(id);
            if (id == R.id.button_lib_cancel || id == R.id.button_lib_ok) {
                clearFxAndFrame();
            }
        }
    }

    private void clearFxAndFrame() {
        int selectedTabIndex = this.effectFragment.getSelectedTabIndex();
        int i = this.currentSelectedTabIndex;
        if (i == 3 || i == 4) {
            if (selectedTabIndex == 0 || selectedTabIndex == 1) {
                clearViewFlipper();
            }
        }
    }

    void addCanvasTextView() {
        this.canvasText = new CustomRelativeLayout(this, this.textDataList, this.mirrorView.f510I, new C08764());
        this.canvasText.setApplyTextListener(new C08775());
        this.showText = false;
        this.mirrorView.invalidate();
        this.mainLayout.addView(this.canvasText);
        findViewById(R.id.text_view_fragment_container).bringToFront();
        this.fontFragment = new FontFragment();
        this.fontFragment.setArguments(new Bundle());
        getSupportFragmentManager().beginTransaction().add(R.id.text_view_fragment_container, this.fontFragment, "FONT_FRAGMENT").commit();
        Log.e(TAG, "add fragment");
        this.fontFragment.setFontChoosedListener(this.fontChoosedListener);
    }

    private void set3dMode(int index) {
        MirrorView mirrorView = this.mirrorView;
        mirrorView.d3Mode = true;
        if (index > 15 && index < 20) {
            mirrorView.setCurrentMode(index);
        } else if (index > 19) {
            this.mirrorView.setCurrentMode(index - 4);
        } else if (index % 2 == 0) {
            this.mirrorView.setCurrentMode(0);
        } else {
            this.mirrorView.setCurrentMode(1);
        }
        this.mirrorView.reset(this.screenWidthPixels, this.screenHeightPixels, false);
        if (VERSION.SDK_INT < 19) {
            if (!(this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled())) {
                this.mirrorView.d3Bitmap.recycle();
            }
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), this.d3resList[index]);
        } else {
            loadInBitmap(this.d3resList[index]);
        }
        this.mirrorView.postInvalidate();
        setD3ButtonBg(index);
    }

    @SuppressLint({"NewApi"})
    private void loadInBitmap(int resId) {
        String str = TAG;
        Log.e(str, "loadInBitmap");
        Options options = new Options();
        if (this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled()) {
            options.inJustDecodeBounds = true;
            options.inMutable = true;
            BitmapFactory.decodeResource(getResources(), resId, options);
            this.mirrorView.d3Bitmap = Bitmap.createBitmap(options.outWidth, options.outHeight, Config.ARGB_8888);
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = 1;
        options.inBitmap = this.mirrorView.d3Bitmap;
        try {
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), resId, options);
        } catch (Exception e) {
            Log.e(str, e.toString());
            if (!(this.mirrorView.d3Bitmap == null || this.mirrorView.d3Bitmap.isRecycled())) {
                this.mirrorView.d3Bitmap.recycle();
            }
            this.mirrorView.d3Bitmap = BitmapFactory.decodeResource(getResources(), resId);
        }
    }

    private void setD3ButtonBg(int index) {
        if (this.d3ButtonArray == null) {
            this.d3ButtonArray = new ImageView[this.D3_BUTTON_SIZE];
            this.d3ButtonArray[0] = findViewById(R.id.button_3d_1);
            this.d3ButtonArray[1] = findViewById(R.id.button_3d_2);
            this.d3ButtonArray[2] = findViewById(R.id.button_3d_3);
            this.d3ButtonArray[3] = findViewById(R.id.button_3d_4);
            this.d3ButtonArray[4] = findViewById(R.id.button_3d_5);
            this.d3ButtonArray[5] = findViewById(R.id.button_3d_6);
            this.d3ButtonArray[6] = findViewById(R.id.button_3d_7);
            this.d3ButtonArray[7] = findViewById(R.id.button_3d_8);
            this.d3ButtonArray[8] = findViewById(R.id.button_3d_9);
            this.d3ButtonArray[9] = findViewById(R.id.button_3d_10);
            this.d3ButtonArray[10] = findViewById(R.id.button_3d_11);
            this.d3ButtonArray[11] = findViewById(R.id.button_3d_12);
            this.d3ButtonArray[12] = findViewById(R.id.button_3d_13);
            this.d3ButtonArray[13] = findViewById(R.id.button_3d_14);
            this.d3ButtonArray[14] = findViewById(R.id.button_3d_15);
            this.d3ButtonArray[15] = findViewById(R.id.button_3d_16);
            this.d3ButtonArray[16] = findViewById(R.id.button_3d_17);
            this.d3ButtonArray[17] = findViewById(R.id.button_3d_18);
            this.d3ButtonArray[18] = findViewById(R.id.button_3d_19);
            this.d3ButtonArray[19] = findViewById(R.id.button_3d_20);
            this.d3ButtonArray[20] = findViewById(R.id.button_3d_21);
            this.d3ButtonArray[21] = findViewById(R.id.button_3d_22);
            this.d3ButtonArray[22] = findViewById(R.id.button_3d_23);
            this.d3ButtonArray[23] = findViewById(R.id.button_3d_24);
        }
        for (int i = 0; i < this.D3_BUTTON_SIZE; i++) {
            this.d3ButtonArray[i].setBackgroundColor(getResources().getColor(R.color.primary));
        }
        this.d3ButtonArray[index].setBackgroundColor(getResources().getColor(R.color.footer_button_color_pressed));
    }

    private void setMirrorButtonBg(int index) {
        if (this.mirrorButtonArray == null) {
            this.mirrorButtonArray = new ImageView[this.MIRROR_BUTTON_SIZE];
            this.mirrorButtonArray[0] = findViewById(R.id.button_m1);
            this.mirrorButtonArray[1] = findViewById(R.id.button_m2);
            this.mirrorButtonArray[2] = findViewById(R.id.button_m3);
            this.mirrorButtonArray[3] = findViewById(R.id.button_m4);
            this.mirrorButtonArray[4] = findViewById(R.id.button_m5);
            this.mirrorButtonArray[5] = findViewById(R.id.button_m6);
            this.mirrorButtonArray[6] = findViewById(R.id.button_m7);
            this.mirrorButtonArray[7] = findViewById(R.id.button_m8);
            this.mirrorButtonArray[8] = findViewById(R.id.button_m9);
            this.mirrorButtonArray[9] = findViewById(R.id.button_m10);
            this.mirrorButtonArray[10] = findViewById(R.id.button_m11);
            this.mirrorButtonArray[11] = findViewById(R.id.button_m12);
            this.mirrorButtonArray[12] = findViewById(R.id.button_m13);
            this.mirrorButtonArray[13] = findViewById(R.id.button_m14);
            this.mirrorButtonArray[14] = findViewById(R.id.button_m15);
        }
        for (int i = 0; i < this.MIRROR_BUTTON_SIZE; i++) {
            this.mirrorButtonArray[i].setBackgroundResource(R.color.primary);
        }
        this.mirrorButtonArray[index].setBackgroundResource(R.color.footer_button_color_pressed);
    }

    private void setRatioButtonBg(int index) {
        if (this.ratioButtonArray == null) {
            this.ratioButtonArray = new Button[this.RATIO_BUTTON_SIZE];
            this.ratioButtonArray[0] = findViewById(R.id.button11);
            this.ratioButtonArray[1] = findViewById(R.id.button21);
            this.ratioButtonArray[2] = findViewById(R.id.button12);
            this.ratioButtonArray[3] = findViewById(R.id.button32);
            this.ratioButtonArray[4] = findViewById(R.id.button23);
            this.ratioButtonArray[5] = findViewById(R.id.button43);
            this.ratioButtonArray[6] = findViewById(R.id.button34);
            this.ratioButtonArray[7] = findViewById(R.id.button45);
            this.ratioButtonArray[8] = findViewById(R.id.button57);
            this.ratioButtonArray[9] = findViewById(R.id.button169);
            this.ratioButtonArray[10] = findViewById(R.id.button916);
        }
        for (int i = 0; i < this.RATIO_BUTTON_SIZE; i++) {
            this.ratioButtonArray[i].setBackgroundResource(R.drawable.selector_collage_ratio_button);
        }
        this.ratioButtonArray[index].setBackgroundResource(R.drawable.collage_ratio_bg_pressed);
    }

    void setSelectedTab(int index) {
        setTabBg(0);
        int displayedChild = this.viewFlipper.getDisplayedChild();
        if (index == 0) {
            if (displayedChild != 0) {
                this.viewFlipper.setInAnimation(this.slideLeftIn);
                this.viewFlipper.setOutAnimation(this.slideRightOut);
                this.viewFlipper.setDisplayedChild(0);
            } else {
                return;
            }
        }
        if (index == 1) {
            setTabBg(1);
            if (displayedChild != 1) {
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(1);
            } else {
                return;
            }
        }
        if (index == 2) {
            setTabBg(2);
            if (displayedChild != 2) {
                if (displayedChild == 0) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                }
                this.viewFlipper.setDisplayedChild(2);
            } else {
                return;
            }
        }
        if (index == 3) {
            setTabBg(3);
            this.effectFragment.setSelectedTabIndex(0);
            if (displayedChild != 3) {
                if (displayedChild != 0) {
                    if (displayedChild != 2) {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                        this.viewFlipper.setDisplayedChild(3);
                    }
                }
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
                this.viewFlipper.setDisplayedChild(3);
            } else {
                return;
            }
        }
        if (index == 4) {
            setTabBg(4);
            this.effectFragment.setSelectedTabIndex(1);
            if (displayedChild != 3) {
                if (displayedChild == 5) {
                    this.viewFlipper.setInAnimation(this.slideLeftIn);
                    this.viewFlipper.setOutAnimation(this.slideRightOut);
                } else {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                }
                this.viewFlipper.setDisplayedChild(3);
            } else {
                return;
            }
        }
        if (index == 5) {
            setTabBg(5);
            this.effectFragment.showToolBar();
            if (displayedChild != 3) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
                this.viewFlipper.setDisplayedChild(3);
            } else {
                return;
            }
        }
        if (index == 7) {
            setTabBg(-1);
            if (displayedChild != 4) {
                this.viewFlipper.setInAnimation(this.slideRightIn);
                this.viewFlipper.setOutAnimation(this.slideLeftOut);
                this.viewFlipper.setDisplayedChild(4);
            }
        }
    }

    private void setTabBg(int index) {
        this.currentSelectedTabIndex = index;
        if (this.tabButtonList == null) {
            this.tabButtonList = new View[6];
            this.tabButtonList[0] = findViewById(R.id.button_mirror);
            this.tabButtonList[1] = findViewById(R.id.button_mirror_3d);
            this.tabButtonList[3] = findViewById(R.id.button_mirror_effect);
            this.tabButtonList[2] = findViewById(R.id.button_mirror_ratio);
            this.tabButtonList[4] = findViewById(R.id.button_mirror_frame);
            this.tabButtonList[5] = findViewById(R.id.button_mirror_adj);
        }
        int i = 0;
        View[] viewArr = this.tabButtonList;
        while (true) {

            if (i >= viewArr.length) {
                break;
            }
            viewArr[i].setBackgroundResource(R.drawable.collage_footer_button);
            i++;
        }
        if (index >= 0) {
            viewArr[index].setBackgroundResource(R.color.footer_button_color_pressed);
        }
    }

    void clearViewFlipper() {
        this.viewFlipper.setInAnimation(null);
        this.viewFlipper.setOutAnimation(null);
        this.viewFlipper.setDisplayedChild(4);
        setTabBg(-1);
    }

    public void onBackPressed() {
        FontFragment fontFragment = this.fontFragment;
        if (fontFragment != null && fontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        } else if (this.viewFlipper.getDisplayedChild() == 3) {
            clearFxAndFrame();
            clearViewFlipper();
        } else {
            if (!this.showText) {
                View view = this.canvasText;
                if (view != null) {
                    this.showText = true;
                    this.mainLayout.removeView(view);
                    this.mirrorView.postInvalidate();
                    this.canvasText = null;
                    Log.e(TAG, "replace fragment");
                    return;
                }
            }
            if (this.viewFlipper.getDisplayedChild() != 4) {
                clearViewFlipper();
            } else {
                backButtonAlertBuilder();
            }
        }
    }

    private void backButtonAlertBuilder() {
        try {
            View view = View.inflate(this, R.layout.dialog_exit_layout, null);

            Builder alertDialogBuilder = new Builder(this);
            alertDialogBuilder.setView(view);
            if (Glob.isOnline(this)) {
                adView = view.findViewById(R.id.adView_dialoge);
                adView.loadAd(new AdRequest.Builder().build());
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        adView.setVisibility(View.VISIBLE);
                    }
                });
            }
            ((TextView) view.findViewById(R.id.dialog_heading)).setText("Want to Discard Editing?");
            ((TextView) view.findViewById(R.id.exit_app)).setText("Yes");
            ((TextView) view.findViewById(R.id.cancel_app)).setText("No");
            view.findViewById(R.id.exit_app).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Apptop_MirrorNewActivity.this.alertDialog.dismiss();
                    Apptop_MirrorNewActivity.this.finish();
                }
            });
            view.findViewById(R.id.cancel_app).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Apptop_MirrorNewActivity.this.alertDialog.dismiss();

                }
            });
            this.alertDialog = alertDialogBuilder.create();
            this.alertDialog.setCancelable(false);
            this.alertDialog.show();
        } catch (Exception e) {
        }
    }



}
