package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.NinePatchDrawable;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.Glob;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.ApplyTextInterface;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.CustomRelativeLayout;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.SingleTap;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.TextData;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.MyAdapter.CurrentCollageIndexChangedListener;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.RotationGestureDetector.OnRotationGestureListener;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_common_lib.Parameter;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.FontFragment;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.FontFragment.FontChoosedListener;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.FullEffectFragment;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_fragments.FullEffectFragment.FullBitmapReady;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist.Collage;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist.CollageLayout;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist.MaskPair;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_share.Apptop_ImageShareActivity;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.BlurBuilderNormal;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.LibUtility;
import com.commit451.nativestackblur.NativeStackBlur;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"NewApi"})
public class Apptop_CollageActivity extends FragmentActivity {
    private static final String TAG = "CollageView";
    private static final float UPPER_SIZE_FOR_LOAD = 1500.0f;
    int RATIO_BUTTON_SIZE = 11;
    private LinearLayout adExitChoicesContainer;
    private LinearLayout adExitView;

    AlertDialog alertDialog = null;
    Bitmap[] bitmapList;
    Bitmap btmDelete;
    Bitmap btmScale;
    CustomRelativeLayout canvasText;
    MyAdapter collageAdapter;
    RecyclerView collageRecyclerView;
    CollageView collageView;
    LinearLayout colorContainer;
    NinePatchDrawable npd;
    ViewGroup contextFooter;
    FontChoosedListener fontChoosedListener = new C05021();
    FontFragment fontFragment;
    FullEffectFragment fullEffectFragment;
    int height;
    boolean isScrapBook = false;
    InterstitialAd mInterstitialAd;
    private RotationGestureDetector mRotationDetector;
    OnSeekBarChangeListener mSeekBarListener = new C05032();
    RelativeLayout mainLayout;
    float mulX = 1.0f;
    float mulY = 1.0f;

    Parameter[] parameterList;
    ArrayList<MyRecylceAdapterBase> patternAdapterList = new ArrayList();
    private ProgressBar progressBarExitRefresh;
    Button[] ratioButtonArray;
    android.support.v7.app.AlertDialog saveImageAlert;
    SeekBar seekBarPadding;
    SeekBar seekBarRound;
    SeekBar seekbarBlur;
    SeekBar seekbarSize;
    View selectFilterTextView;
    boolean selectImageForAdj = false;
    View selectSwapTextView;
    boolean showText = false;
    private Animation slideLeftIn;
    private Animation slideLeftOut;
    private Animation slideRightIn;
    private Animation slideRightOut;
    boolean swapMode = false;
    View[] tabButtonList;
    ArrayList<TextData> textDataList = new ArrayList();
    ViewFlipper viewFlipper;
    int width;

    AdView mAdView, adView;

    class BitmapWorkerTask extends AsyncTask<Bundle, Void, Void> {
        int arraySize;
        Bundle data;
        ProgressDialog progressDialog;
        Bundle savedInstanceState;

        BitmapWorkerTask() {
        }

        protected void onPreExecute() {
            this.progressDialog = new ProgressDialog(Apptop_CollageActivity.this);
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage("loading images!");
            this.progressDialog.show();
        }

        protected Void doInBackground(Bundle... params) {
            int maxDivider;
            Log.e("doInBG", "load");
            this.data = params[0];
            this.savedInstanceState = params[1];
            Apptop_CollageActivity.this.isScrapBook = this.data.getBoolean("is_scrap_book", false);
            long[] selectedImageList = this.data.getLongArray("photo_id_list");
            int[] selectedImageOrientationList = this.data.getIntArray("photo_orientation_list");
            this.arraySize = 0;
            int maxDivider2;
            if (selectedImageList == null) {
                String selectedImagePath = this.data.getString("selected_image_path");
                if (selectedImagePath != null) {
                    this.arraySize = 1;
                    Apptop_CollageActivity.this.bitmapList = new Bitmap[this.arraySize];
                    maxDivider2 = this.arraySize;
                    if (maxDivider2 < 3) {
                        maxDivider2 = 3;
                    }
                    Apptop_CollageActivity.this.bitmapList[0] = Utility.decodeFile(selectedImagePath, Utility.maxSizeForDimension(Apptop_CollageActivity.this, maxDivider2, Apptop_CollageActivity.UPPER_SIZE_FOR_LOAD), Apptop_CollageActivity.this.isScrapBook);
                }
            } else {
                int newSize;
                this.arraySize = selectedImageList.length;
                Apptop_CollageActivity.this.bitmapList = new Bitmap[this.arraySize];
                maxDivider = this.arraySize;
                if (maxDivider < 3) {
                    maxDivider = 3;
                }
                maxDivider2 = Utility.maxSizeForDimension(Apptop_CollageActivity.this, maxDivider, Apptop_CollageActivity.UPPER_SIZE_FOR_LOAD);
                int loadingImageError = 0;
                int i = 0;
                while (true) {
                    newSize = this.arraySize;
                    if (i >= newSize) {
                        break;
                    }
                    Bitmap bitmap = null;
                    bitmap = Utility.getScaledBitmapFromId(Apptop_CollageActivity.this, selectedImageList[i], selectedImageOrientationList[i], maxDivider2, true);
                    if (bitmap != null) {
                        Apptop_CollageActivity.this.bitmapList[i] = bitmap;
                    } else {
                        loadingImageError++;
                    }
                    i++;
                }
                if (loadingImageError > 0) {
                    newSize -= loadingImageError;
                    Bitmap[] arr = new Bitmap[newSize];
                    int j = 0;
                    for (i = 0; i < this.arraySize; i++) {
                        if (Apptop_CollageActivity.this.bitmapList[i] != null) {
                            arr[j] = Apptop_CollageActivity.this.bitmapList[i];
                            j++;
                        }
                    }
                    this.arraySize = newSize;
                    Apptop_CollageActivity.this.bitmapList = arr;
                }
            }
            Apptop_CollageActivity.this.parameterList = new Parameter[this.arraySize];
            for (maxDivider = 0; maxDivider < Apptop_CollageActivity.this.parameterList.length; maxDivider++) {
                Apptop_CollageActivity.this.parameterList[maxDivider] = new Parameter();
            }
            return null;
        }

        @SuppressLint({"WrongConstant"})
        protected void onPostExecute(Void v) {
            try {
                this.progressDialog.dismiss();
            } catch (Exception e) {
            }
            if (this.arraySize <= 0) {
                Toast msg = Toast.makeText(Apptop_CollageActivity.this, "Couldn't load images!", 0);
                msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
                msg.show();
                Apptop_CollageActivity.this.finish();
                return;
            }
            Apptop_CollageActivity collageActivity;
            Context context;
            int[] iArr = Collage.collageIconArray[Apptop_CollageActivity.this.bitmapList.length - 1];
            int[] iArr2 = Apptop_CollageActivity.this.collageAdapter.iconList;
            String str = Apptop_CollageActivity.TAG;
            if (iArr != iArr2) {
                Apptop_CollageActivity.this.collageAdapter.setData(Collage.collageIconArray[Apptop_CollageActivity.this.bitmapList.length - 1]);
                Apptop_CollageActivity.this.collageAdapter.notifyDataSetChanged();
                Log.e(str, "change collage icons");
            }
            if (Apptop_CollageActivity.this.isScrapBook) {
                collageActivity = Apptop_CollageActivity.this;
                collageActivity.btmDelete = BitmapFactory.decodeResource(collageActivity.getResources(), R.drawable.scrapbook_remove);
                collageActivity = Apptop_CollageActivity.this;
                collageActivity.btmScale = BitmapFactory.decodeResource(collageActivity.getResources(), R.drawable.scrapbook_scale);
            }
            if (Apptop_CollageActivity.this.isScrapBook) {
                context = Apptop_CollageActivity.this;
                ((Apptop_CollageActivity) context).npd = (NinePatchDrawable) ContextCompat.getDrawable(context, R.drawable.shadow_7);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("ndp width ");
                stringBuilder.append(Apptop_CollageActivity.this.npd.getMinimumHeight());
                Log.e(str, stringBuilder.toString());
            }
            context = Apptop_CollageActivity.this;
            collageView = new CollageView(context, width, Apptop_CollageActivity.this.height);
            collageActivity = Apptop_CollageActivity.this;
            collageActivity.mainLayout = collageActivity.findViewById(R.id.collage_main_layout);
            Apptop_CollageActivity.this.mainLayout.addView(Apptop_CollageActivity.this.collageView);
            Apptop_CollageActivity.this.viewFlipper.bringToFront();
            slideLeftIn = AnimationUtils.loadAnimation(context, R.anim.slide_in_left);

            slideLeftOut = AnimationUtils.loadAnimation(context, R.anim.slide_out_left);

            slideRightIn = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);

            slideRightOut = AnimationUtils.loadAnimation(context, R.anim.slide_out_right);
            Apptop_CollageActivity.this.addEffectFragment();
            if (this.arraySize == 1) {
                Apptop_CollageActivity.this.setVisibilityForSingleImage();
            }
            if (Apptop_CollageActivity.this.isScrapBook) {
                Apptop_CollageActivity.this.setVisibilityForScrapbook();
            }
            collageActivity = Apptop_CollageActivity.this;
            collageActivity.viewFlipper = collageActivity.findViewById(R.id.collage_view_flipper);
            Apptop_CollageActivity.this.viewFlipper.bringToFront();
            Apptop_CollageActivity.this.findViewById(R.id.collage_footer).bringToFront();
            Apptop_CollageActivity.this.findViewById(R.id.collage_header).bringToFront();
            collageActivity = Apptop_CollageActivity.this;
            collageActivity.contextFooter = collageActivity.findViewById(R.id.collage_context_menu);
            Apptop_CollageActivity.this.contextFooter.bringToFront();
            collageActivity = Apptop_CollageActivity.this;
            collageActivity.selectSwapTextView = collageActivity.findViewById(R.id.select_image_swap);
            Apptop_CollageActivity.this.selectSwapTextView.bringToFront();
            Apptop_CollageActivity.this.selectSwapTextView.setVisibility(4);
            collageActivity = Apptop_CollageActivity.this;
            collageActivity.selectFilterTextView = collageActivity.findViewById(R.id.select_image_filter);
            Apptop_CollageActivity.this.selectFilterTextView.bringToFront();
            Apptop_CollageActivity.this.selectFilterTextView.setVisibility(4);
        }
    }

    class C05032 implements OnSeekBarChangeListener {
        C05032() {
        }

        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            int id = seekBar.getId();
            if (id == R.id.seekbar_round) {
                if (Apptop_CollageActivity.this.collageView != null) {
                    Apptop_CollageActivity.this.collageView.setCornerRadius((float) progress);
                }
            } else if (id == R.id.seekbar_padding) {
                if (Apptop_CollageActivity.this.collageView != null) {
                    Apptop_CollageActivity.this.collageView.setPathPadding(Apptop_CollageActivity.this.collageView.currentCollageIndex, (float) progress);
                }
            } else if (id == R.id.seekbar_size) {
                if (Apptop_CollageActivity.this.collageView != null) {
                    Apptop_CollageActivity.this.collageView.setCollageSize(Apptop_CollageActivity.this.collageView.sizeMatrix, progress);
                }
            } else if (id != R.id.seekbar_collage_blur) {
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (seekBar.getId() == R.id.seekbar_collage_blur) {
                float radius = ((float) seekBar.getProgress()) / 4.0f;
                if (radius > 25.0f) {
                    radius = 25.0f;
                }
                if (radius < 0.0f) {
                    radius = 0.0f;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("blur radius ");
                stringBuilder.append(radius);
                Log.e(Apptop_CollageActivity.TAG, stringBuilder.toString());
                Apptop_CollageActivity.this.collageView.setBlurBitmap((int) radius, false);
            }
        }
    }

    class CollageView extends View {
        public static final int BACKGROUND_BLUR = 1;
        public static final int BACKGROUND_PATTERN = 0;
        private static final int INVALID_POINTER_ID = 1;
        public static final int PATTERN_SENTINEL = -1;
        static final float RATIO_CONSTANT = 1.25f;
        private static final int UPPER_SIZE_LIMIT = 2048;
        float MIN_ZOOM;
        RectF above;
        int animEpsilon = 20;
        int animHalfTime = ((this.animationLimit / 2) + 1);
        int animSizeSeekbarProgress = 0;
        boolean animate = false;
        int animationCount = 0;
        int animationDurationLimit = 50;
        int animationLimit = 31;
        private Runnable animator;
        int backgroundMode;
        Bitmap blurBitmap;
        BlurBuilderNormal blurBuilderNormal;
        int blurRadius = 14;
        RectF blurRectDst;
        Rect blurRectSrc;
        Paint borderPaint;
        RectF bottom;
        RectF bottomLeft;
        RectF bottomRight;
        Paint circlePaint;
        float cornerRadius = 0.0f;
        int currentCollageIndex = 0;
        RectF drawingAreaRect;
        final float epsilon;
        float finalAngle;
        Bitmap frameBitmap;
        int frameDuration = 10;
        RectF frameRect;
        Matrix identityMatrix = new Matrix();
        boolean isInCircle;
        boolean isOnCross;
        RectF left;
        private int mActivePointerId;
        float mLastTouchX;
        float mLastTouchY;
        private ScaleGestureDetector mScaleDetector;
        float mScaleFactor;
        private GestureDetectorCompat mTouchDetector;
        Bitmap[] maskBitmapArray;
        int[] maskResIdList = new int[]{R.drawable.mask_butterfly, R.drawable.mask_cloud, R.drawable.mask_clover, R.drawable.mask_leaf, R.drawable.mask_left_foot, R.drawable.mask_diamond, R.drawable.mask_santa, R.drawable.mask_snowman, R.drawable.mask_paw, R.drawable.mask_egg, R.drawable.mask_twitter, R.drawable.mask_circle, R.drawable.mask_hexagon, R.drawable.mask_heart};
        float[] matrixValues;
        boolean move;
        int offsetX;
        int offsetY;
        boolean orthogonal;
        float paddingDistance = 0.0f;
        Paint paint = new Paint();
        Paint paintGray;
        Bitmap patternBitmap;
        Paint patternPaint = new Paint(1);
        int previousIndex;
        float[] pts;
        Rect rectAnim;
        RectF right;
        OnRotationGestureListener rotateListener;
        Shape scaleShape;
        int screenHeight;
        int screenWidth;
        int shapeIndex = -1;
        List<ShapeLayout> shapeLayoutList = new ArrayList();
        Matrix sizeMatrix = new Matrix();
        Matrix sizeMatrixSaved;
        float sizeScale = 1.0f;
        ArrayList<Float> smallestDistanceList = new ArrayList();
        private float startAngle;
        Matrix startMatrix;
        long startTime = System.nanoTime();
        Matrix textMatrix;
        RectF topLeft;
        RectF topRight;
        float[] values;
        float xscale = 1.0f;
        float yscale = 1.0f;
        PointF zoomStart;

        class MyGestureListener extends SimpleOnGestureListener {
            private static final String DEBUG_TAG = "Gestures";

            MyGestureListener() {
            }

            public boolean onSingleTapConfirmed(MotionEvent event) {
                Log.d(DEBUG_TAG, "onSingleTapConfirmed: ");
                return true;
            }

            public boolean onSingleTapUp(MotionEvent event) {
                Log.d(DEBUG_TAG, "onSingleTapUp: ");
                if (!CollageView.this.isOnCross) {
                    Apptop_CollageActivity.this.collageView.selectCurrentShape(event.getX(), event.getY(), true);
                }
                return true;
            }
        }

        private class ScaleListener extends SimpleOnScaleGestureListener {
            private ScaleListener() {
            }

            public boolean onScale(ScaleGestureDetector detector) {
                if (CollageView.this.shapeIndex >= 0) {
                    CollageView.this.mScaleFactor = detector.getScaleFactor();
                    detector.isInProgress();
                    CollageView collageView = CollageView.this;
                    collageView.mScaleFactor = Math.max(0.1f, Math.min(collageView.mScaleFactor, 5.0f));
                    collageView = CollageView.this;
                    collageView.scaleShape = collageView.shapeLayoutList.get(CollageView.this.currentCollageIndex).shapeArr[CollageView.this.shapeIndex];
                    if (Apptop_CollageActivity.this.isScrapBook) {
                        CollageView.this.scaleShape.bitmapMatrixScaleScrapBook(CollageView.this.mScaleFactor, CollageView.this.mScaleFactor);
                    } else {
                        CollageView.this.scaleShape.bitmapMatrixScale(CollageView.this.mScaleFactor, CollageView.this.mScaleFactor, CollageView.this.scaleShape.bounds.centerX(), CollageView.this.scaleShape.bounds.centerY());
                    }
                    CollageView.this.invalidate();
                    CollageView.this.requestLayout();
                }
                return true;
            }
        }

        @SuppressLint({"NewApi"})
        public CollageView(Context context, int width, int height) {
            super(context);
            this.animator = new Runnable() {
                public void run() {
                    boolean scheduleNewFrame = false;
                    int iter = ((int) (((float) (System.nanoTime() - CollageView.this.startTime)) / 1000000.0f)) / CollageView.this.animationDurationLimit;
                    if (iter <= 0) {
                        iter = 1;
                    }
                    CollageView collageView;
                    if (CollageView.this.animationCount == 0) {
                        collageView = Apptop_CollageActivity.this.collageView;
                        collageView.animationCount++;
                    } else {
                        collageView = Apptop_CollageActivity.this.collageView;
                        collageView.animationCount += iter;
                    }
                    CollageView collageView2 = CollageView.this;
                    Matrix matrix = collageView2.sizeMatrix;
                    CollageView collageView3 = CollageView.this;
                    collageView2.setCollageSize(matrix, collageView3.animSize(collageView3.animationCount));
                    if (CollageView.this.animationCount < CollageView.this.animationLimit) {
                        scheduleNewFrame = true;
                    } else {
                        CollageView.this.animate = false;
                    }
                    if (scheduleNewFrame) {
                        collageView2 = CollageView.this;
                        collageView2.postDelayed(this, (long) collageView2.frameDuration);
                    } else {
                        CollageView.this.sizeMatrix.set(CollageView.this.sizeMatrixSaved);
                    }
                    CollageView.this.shapeLayoutList.get(CollageView.this.currentCollageIndex).shapeArr[0].f508r.roundOut(CollageView.this.rectAnim);
                    collageView2 = CollageView.this;
                    collageView2.invalidate(collageView2.rectAnim);
                    CollageView.this.startTime = System.nanoTime();
                }
            };
            this.rectAnim = new Rect();
            this.textMatrix = new Matrix();
            this.blurRectDst = new RectF();
            this.drawingAreaRect = new RectF();
            this.above = new RectF();
            this.left = new RectF();
            this.right = new RectF();
            this.bottom = new RectF();
            this.move = false;
            this.paintGray = new Paint(1);
            this.mActivePointerId = 1;
            this.zoomStart = new PointF();
            this.startMatrix = new Matrix();
            this.startAngle = 0.0f;
            this.MIN_ZOOM = 0.1f;
            this.isInCircle = false;
            this.isOnCross = false;
            this.orthogonal = false;
            this.mScaleFactor = 1.0f;
            this.matrixValues = new float[9];
            this.finalAngle = 0.0f;
            this.epsilon = 4.0f;
            this.rotateListener = new OnRotationGestureListener() {
                public void OnRotation(RotationGestureDetector rotationGestureDetector) {
                    if (CollageView.this.shapeIndex >= 0) {
                        float angle = rotationGestureDetector.getAngle();
                        CollageView collageView = CollageView.this;
                        collageView.scaleShape = collageView.shapeLayoutList.get(CollageView.this.currentCollageIndex).shapeArr[CollageView.this.shapeIndex];
                        float rotation;
                        rotation = getMatrixRotation(scaleShape.bitmapMatrix);
                        if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f) && Math.abs(CollageView.this.finalAngle - angle) < 4.0f) {
                            CollageView.this.orthogonal = true;
                            return;
                        }
                        if (Math.abs((rotation - CollageView.this.finalAngle) + angle) < 4.0f) {
                            angle = CollageView.this.finalAngle - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(90.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle + 90.0f) - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(180.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle + 180.0f) - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(-180.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle - 0.024902344f) - rotation;
                            CollageView.this.orthogonal = true;
                        }
                        if (Math.abs(-90.0f - ((rotation - CollageView.this.finalAngle) + angle)) < 4.0f) {
                            angle = (CollageView.this.finalAngle - 0.049804688f) - rotation;
                            CollageView.this.orthogonal = true;
                        } else {
                            CollageView.this.orthogonal = false;
                        }
                        CollageView.this.scaleShape.bitmapMatrixRotate(CollageView.this.finalAngle - angle);
                        CollageView collageView2 = CollageView.this;
                        collageView2.finalAngle = angle;
                        collageView2.invalidate();
                        CollageView.this.requestLayout();
                    }
                }
            };
            this.values = new float[9];
            this.backgroundMode = 0;
            this.blurRectSrc = new Rect();
            this.maskBitmapArray = new Bitmap[this.maskResIdList.length];
            this.borderPaint = new Paint(1);
            this.borderPaint.setColor(getResources().getColor(R.color.blue));
            this.borderPaint.setStyle(Style.STROKE);
            this.borderPaint.setStrokeWidth(10.0f);
            this.screenWidth = width;
            this.screenHeight = height;
            this.circlePaint = new Paint();
            this.circlePaint.setColor(SupportMenu.CATEGORY_MASK);
            this.identityMatrix.reset();
            this.topLeft = new RectF((float) (width * 0), (float) (height * 0), ((float) width) * 0.5f, ((float) height) * 0.5f);
            this.topRight = new RectF(((float) width) * 0.5f, ((float) height) * 0.0f, ((float) width) * 1.0f, ((float) height) * 0.5f);
            this.bottomLeft = new RectF((float) (width * 0), ((float) height) * 0.5f, ((float) width) * 0.5f, ((float) height) * 1.0f);
            this.bottomRight = new RectF(((float) width) * 0.5f, ((float) height) * 0.5f, ((float) width) * 1.0f, ((float) height) * 1.0f);
            Path pathTopLeft = new Path();
            Path pathTopRight = new Path();
            Path pathBottomLeft = new Path();
            Path pathBottomRight = new Path();
            pathTopLeft.addRect(this.topLeft, Direction.CCW);
            pathTopRight.addRect(this.topRight, Direction.CCW);
            pathBottomLeft.addRect(this.bottomLeft, Direction.CCW);
            pathBottomRight.addRect(this.bottomRight, Direction.CCW);
            this.mTouchDetector = new GestureDetectorCompat(context, new MyGestureListener());
            this.mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
            Apptop_CollageActivity.this.mRotationDetector = new RotationGestureDetector(this.rotateListener);
            calculateOffset();
            this.patternPaint = new Paint(1);
            this.patternPaint.setColor(-1);
            createShapeList(Apptop_CollageActivity.this.bitmapList.length, width, height);
            this.paintGray.setColor(-12303292);
        }

        private void calculateOffset() {
            PointF scale = getRatio();
            this.offsetX = (int) ((((float) Apptop_CollageActivity.this.width) - (scale.x * ((float) Apptop_CollageActivity.this.width))) / 2.0f);
            this.offsetY = (int) ((((float) Apptop_CollageActivity.this.height) - (scale.y * ((float) Apptop_CollageActivity.this.width))) / 2.0f);
        }

        public void setCropBitmap(int left, int top, int right, int bottom) {
            if (this.shapeIndex >= 0) {
                Bitmap sourceBitmap = Apptop_CollageActivity.this.bitmapList[this.shapeIndex];
                boolean isFilter = sourceBitmap != this.shapeLayoutList.get(0).shapeArr[this.shapeIndex].getBitmap();
                if (isFilter) {
                    doCrop(left, top, right, bottom, sourceBitmap, false, false);
                    doCrop(left, top, right, bottom, this.shapeLayoutList.get(0).shapeArr[this.shapeIndex].getBitmap(), true, true);
                } else {
                    doCrop(left, top, right, bottom, sourceBitmap, false, true);
                }
                if (!(!isFilter || Apptop_CollageActivity.this.parameterList == null || Apptop_CollageActivity.this.parameterList[this.shapeIndex] == null)) {
                    Apptop_CollageActivity.this.parameterList[this.shapeIndex].setId(Parameter.uniqueId.getAndIncrement());
                }
                invalidate();
            }
        }

        public void doCrop(int left, int top, int right, int bottom, Bitmap sourceBitmap, boolean isFilter, boolean last) {
            int right2;
            int bottom2;
            CollageView collageView = this;
            int i = left;
            int i2 = top;
            Bitmap localCropBtm = sourceBitmap;
            int bitmapWidth = sourceBitmap.getWidth();
            int bitmapHeight = sourceBitmap.getHeight();
            int right3 = right;
            if (right3 > bitmapWidth) {
                right2 = bitmapWidth;
            } else {
                right2 = right3;
            }
            right3 = bottom;
            if (right3 > bitmapHeight) {
                bottom2 = bitmapHeight;
            } else {
                bottom2 = right3;
            }
            if (right2 - i <= 0 || bottom2 - i2 <= 0) {
                return;
            }
            Bitmap sourceBitmap2;
            if (VERSION.SDK_INT < 12) {
                sourceBitmap2 = BlurBuilderNormal.createCroppedBitmap(localCropBtm, left, top, right2 - i, bottom2 - i2, false);
            } else {
                sourceBitmap2 = Bitmap.createBitmap(localCropBtm, left, i2, right2 - i, bottom2 - i2);
            }
            if (localCropBtm != sourceBitmap2) {
                localCropBtm.recycle();
            }
            if (!isFilter) {
                Apptop_CollageActivity.this.bitmapList[collageView.shapeIndex] = sourceBitmap2;
            }
            if (last) {
                int i3 = 0;
                while (collageView.shapeLayoutList.size() > 0) {
                    collageView.shapeLayoutList.get(i3).shapeArr[collageView.shapeIndex].setBitmap(sourceBitmap2, false);
                    if (Apptop_CollageActivity.this.isScrapBook) {
                        collageView.shapeLayoutList.get(i3).shapeArr[collageView.shapeIndex].resetDashPaths();
                    }
                    i3 += 0;
                }
            }
        }

        public String saveBitmap(int width, int height) {
            int newBtmWidth;
            Canvas bitmapCanvas;
            Bitmap savedBitmap;
            ShapeLayout arr;
            Canvas bitmapCanvas2;
            int i = width;
            int i2 = height;
            int btmWidth = (int) (((float) i) * Apptop_CollageActivity.this.collageView.xscale);
            int btmHeight = (int) (((float) i) * Apptop_CollageActivity.this.collageView.yscale);
            float btmScale = ((float) Utility.maxSizeForSave(Apptop_CollageActivity.this, 2048.0f)) / ((float) Math.max(btmWidth, btmHeight));
            int newBtmWidth2 = (int) (((float) btmWidth) * btmScale);
            int newBtmHeight = (int) (((float) btmHeight) * btmScale);
            String str = Apptop_CollageActivity.TAG;
            if (newBtmWidth2 <= 0) {
                newBtmWidth2 = btmWidth;
                Log.e(str, "newBtmWidth");
                newBtmWidth = newBtmWidth2;
            } else {
                newBtmWidth = newBtmWidth2;
            }
            if (newBtmHeight <= 0) {
                newBtmHeight = btmHeight;
                Log.e(str, "newBtmHeight");
            }
            Bitmap savedBitmap2 = Bitmap.createBitmap(newBtmWidth, newBtmHeight, Config.ARGB_8888);
            Canvas bitmapCanvas3 = new Canvas(savedBitmap2);
            ShapeLayout arr2 = shapeLayoutList.get(currentCollageIndex);
            Matrix sizeMat = new Matrix();
            sizeMat.reset();
            sizeMat.preScale(btmScale, btmScale);
            bitmapCanvas3.setMatrix(sizeMat);
            if (backgroundMode == 0) {
                bitmapCanvas3.drawRect(0.0f, 0.0f, (float) btmWidth, (float) btmHeight, patternPaint);
            }
            Bitmap bitmap = blurBitmap;
            if (bitmap == null || bitmap.isRecycled() || backgroundMode != 1) {
            } else {
                bitmapCanvas3.drawBitmap(blurBitmap, blurRectSrc, new RectF(0.0f, 0.0f, (float) btmWidth, (float) btmHeight), paint);
            }
            float f = sizeScale;
            sizeMat.postScale(f, f, ((float) newBtmWidth) / 2.0f, ((float) newBtmHeight) / 2.0f);
            sizeMat.preTranslate((float) (-offsetX), (float) (-offsetY));
            bitmapCanvas3.setMatrix(sizeMat);
            f = (float) (-i);
            float f2 = sizeScale;
            btmWidth = bitmapCanvas3.saveLayer(f / f2, ((float) (-i2)) / f2, ((float) offsetX) + (((float) i) / f2), ((float) offsetY) + (((float) i2) / f2), null, Canvas.ALL_SAVE_FLAG);
            newBtmWidth2 = 0;
            while (newBtmWidth2 < arr2.shapeArr.length) {
                boolean drawPorterClear;
                Matrix sizeMat2;
                drawPorterClear = newBtmWidth2 == arr2.getClearIndex();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("drawPorterClear ");
                stringBuilder.append(drawPorterClear);
                Log.e(str, stringBuilder.toString());
                if (Apptop_CollageActivity.this.isScrapBook) {
                    sizeMat2 = sizeMat;
                    ShapeLayout arr3 = arr2;
                    bitmapCanvas = bitmapCanvas3;
                    savedBitmap = savedBitmap2;
                    arr2.shapeArr[newBtmWidth2].drawShapeForScrapBook(bitmapCanvas3, newBtmWidth, newBtmHeight, false, false);
                    arr = arr3;
                } else {
                    sizeMat2 = sizeMat;
                    bitmapCanvas = bitmapCanvas3;
                    savedBitmap = savedBitmap2;
                    ShapeLayout arr4 = arr2;
                    arr = arr4;
                    arr4.shapeArr[newBtmWidth2].drawShapeForSave(bitmapCanvas, newBtmWidth, newBtmHeight, btmWidth, drawPorterClear);
                }
                newBtmWidth2++;
                savedBitmap2 = savedBitmap;
                arr2 = arr;
                sizeMat = sizeMat2;
                bitmapCanvas3 = bitmapCanvas;
//                savedBitmap = width;
            }
            arr = arr2;
            bitmapCanvas = bitmapCanvas3;
            savedBitmap = savedBitmap2;
            if (Apptop_CollageActivity.this.textDataList != null) {
                newBtmWidth2 = 0;
                while (newBtmWidth2 < Apptop_CollageActivity.this.textDataList.size()) {
                    Matrix mat = new Matrix();
                    mat.set(Apptop_CollageActivity.this.textDataList.get(newBtmWidth2).imageSaveMatrix);
                    mat.postTranslate((float) (-offsetX), (float) (-offsetY));
                    mat.postScale(btmScale, btmScale);
                    bitmapCanvas2 = bitmapCanvas;
                    bitmapCanvas2.setMatrix(mat);
                    bitmapCanvas2.drawText(Apptop_CollageActivity.this.textDataList.get(newBtmWidth2).message, Apptop_CollageActivity.this.textDataList.get(newBtmWidth2).xPos, Apptop_CollageActivity.this.textDataList.get(newBtmWidth2).yPos, Apptop_CollageActivity.this.textDataList.get(newBtmWidth2).textPaint);
                    newBtmWidth2++;
                }
                bitmapCanvas2 = bitmapCanvas;
                int i3 = newBtmWidth2;
            } else {
                bitmapCanvas2 = bitmapCanvas;
            }
            bitmapCanvas2.restoreToCount(btmWidth);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(Environment.getExternalStorageDirectory().toString());
            String str2 = "/";
            stringBuilder2.append(str2);
            stringBuilder2.append(Apptop_CollageActivity.this.getString(R.string.app_folder));
            stringBuilder2.append(str2);
            stringBuilder2.append(System.currentTimeMillis());
            stringBuilder2.append(".jpg");
            str2 = stringBuilder2.toString();
            new File(str2).getParentFile().mkdirs();
            try {
                OutputStream fileOutputStream = new FileOutputStream(str2);
                savedBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            savedBitmap.recycle();
            return str2;
        }

        public void createFolder(String fname) {
            StringBuilder myfolder = new StringBuilder();
            myfolder.append(Environment.getExternalStorageDirectory());
            myfolder.append("/");
            myfolder.append(fname);
            myfolder = myfolder;
            File f = new File(myfolder.toString());
            String str = "SAVEEE";
            StringBuilder stringBuilder;
            if (f.exists()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(myfolder);
                stringBuilder.append(" already exits.");
                Log.v(str, stringBuilder.toString());
            } else if (f.mkdir()) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(myfolder);
                stringBuilder.append(" can be created.");
                Log.v(str, stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(myfolder);
                stringBuilder.append(" can't be created.");
                Log.v(str, stringBuilder.toString());
            }
        }

        int getMaskIndex(int resId) {
            int i = 0;
            while (true) {
                int[] iArr = this.maskResIdList;
                if (i >= iArr.length) {
                    return -1;
                }
                if (resId == iArr[i]) {
                    return i;
                }
                i++;
            }
        }

        private void createShapeList(int shapeCount, int width, int height) {
            int i = shapeCount;
            this.shapeLayoutList.clear();
            this.smallestDistanceList.clear();
            int i2 = width;
            Collage collage = Collage.CreateCollage(i, i2, i2, Apptop_CollageActivity.this.isScrapBook);
            int size = ((CollageLayout) collage.collageLayoutList.get(0)).shapeList.size();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("bitmapList.length ");
            stringBuilder.append(Apptop_CollageActivity.this.bitmapList.length);
            String stringBuilder2 = stringBuilder.toString();
            String str = Apptop_CollageActivity.TAG;
            Log.e(str, stringBuilder2);
            int i3 = 0;
            while (i3 < collage.collageLayoutList.size()) {
                int size2;
                String str2;
                Shape[] shapeArray = new Shape[size];
                int j = 0;
                while (j < i) {
                    boolean masked;
                    int resId;
                    boolean masked2 = false;
                    int resId2 = 0;
                    if (((CollageLayout) collage.collageLayoutList.get(i3)).maskPairList == null || ((CollageLayout) collage.collageLayoutList.get(i3)).maskPairList.isEmpty()) {
                        masked = false;
                        resId = 0;
                    } else {
                        for (MaskPair maskPair : ((CollageLayout) collage.collageLayoutList.get(i3)).maskPairList) {
                            if (j == maskPair.index) {
                                masked2 = true;
                                resId2 = maskPair.id;
                            }
                        }
                        masked = masked2;
                        resId = resId2;
                    }
                    if (masked) {
                        Bitmap maskBitmap;
                        int maskIndex = getMaskIndex(resId);
                        if (maskIndex >= 0) {
                            if (maskBitmapArray == null) {
                                maskBitmapArray = new Bitmap[maskResIdList.length];
                            }
                            Bitmap[] bitmapArr = maskBitmapArray;
                            if (bitmapArr[maskIndex] == null) {
                                bitmapArr[maskIndex] = loadMaskBitmap2(resId);
                                Log.e(str, "load mask bitmap from factory");
                            } else {
                                Log.e(str, "load mask bitmap from pool");
                            }
                            maskBitmap = maskBitmapArray[maskIndex];
                        } else {
                            maskBitmap = null;
                        }
                        shapeArray[j] = new Shape((PointF[]) ((CollageLayout) collage.collageLayoutList.get(i3)).shapeList.get(j), Apptop_CollageActivity.this.bitmapList[j], null, offsetX, offsetY, maskBitmap, Apptop_CollageActivity.this.isScrapBook, j, false, Apptop_CollageActivity.this.btmDelete, Apptop_CollageActivity.this.btmScale, screenWidth);
                        if (Apptop_CollageActivity.this.isScrapBook) {
                            shapeArray[j].initScrapBook(Apptop_CollageActivity.this.npd);
                        }
                        size2 = size;
                        str2 = str;
                    } else {
                        size2 = size;
                        str2 = str;
                        shapeArray[j] = new Shape((PointF[]) ((CollageLayout) collage.collageLayoutList.get(i3)).shapeList.get(j), Apptop_CollageActivity.this.bitmapList[j], ((CollageLayout) collage.collageLayoutList.get(i3)).getexceptionIndex(j), offsetX, offsetY, Apptop_CollageActivity.this.isScrapBook, j, false, Apptop_CollageActivity.this.btmDelete, Apptop_CollageActivity.this.btmScale, screenWidth);
                        if (Apptop_CollageActivity.this.isScrapBook) {
                            shapeArray[j].initScrapBook(Apptop_CollageActivity.this.npd);
                        }
                    }
                    j++;
                    i2 = width;
                    size = size2;
                    str = str2;
                }
                size2 = size;
                str2 = str;
                smallestDistanceList.add(Float.valueOf(smallestDistance(shapeArray)));
                ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                shapeLayout.setClearIndex(((CollageLayout) collage.collageLayoutList.get(i3)).getClearIndex());
                shapeLayoutList.add(shapeLayout);
                i3++;
                i2 = width;
                size = size2;
            }
            if (!Apptop_CollageActivity.this.isScrapBook) {
                if (i != 1) {
                    for (int index = 0; index < shapeLayoutList.size(); index++) {
                        setPathPadding(index, (float) getResources().getInteger(R.integer.default_space_value));
                        for (Shape scaleMatrix : shapeLayoutList.get(index).shapeArr) {
                            scaleMatrix.setScaleMatrix(1);
                        }
                    }
                    setCollageSize(sizeMatrix, getResources().getInteger(R.integer.default_ssize_value));
                } else if (Apptop_CollageActivity.this.bitmapList.length == 1) {
                    setCollageSize(sizeMatrix, getResources().getInteger(R.integer.default_ssize_value));
                }
            }
        }

        private int setShapeScaleMatrix(int mode) {
            if (this.shapeIndex < 0) {
                return -1;
            }
            int message = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].setScaleMatrix(mode);
            invalidate();
            return message;
        }

        private void deleteBitmap(int index, int width, int height) {
            int i = index;
            int i2 = width;
            Shape[] scrapBookTemp = this.shapeLayoutList.get(0).shapeArr;
            if (i < 0 || i >= shapeLayoutList.get(0).shapeArr.length) {
                int i3 = height;
                return;
            }
            int i4;
            String str;
            Bitmap[] bitmapListTemp;
            int size;
            String str2;
            Bitmap[] currentBitmapListTemp;
            int newSize = shapeLayoutList.get(0).shapeArr.length - 1;
            Bitmap[] currentBitmapListTemp2 = new Bitmap[newSize];
            Bitmap[] bitmapListTemp2 = new Bitmap[newSize];
            int j = 0;
            for (i4 = 0; i4 < currentBitmapListTemp2.length + 1; i4++) {
                if (i4 != i) {
                    currentBitmapListTemp2[j] = shapeLayoutList.get(0).shapeArr[i4].getBitmap();
                    bitmapListTemp2[j] = Apptop_CollageActivity.this.bitmapList[i4];
                    j++;
                }
            }
            Apptop_CollageActivity.this.bitmapList[i].recycle();
            shapeLayoutList.get(0).shapeArr[i].getBitmap().recycle();
            shapeLayoutList.clear();
            smallestDistanceList.clear();
            Collage collage = Collage.CreateCollage(newSize, i2, i2, Apptop_CollageActivity.this.isScrapBook);
            int size2 = ((CollageLayout) collage.collageLayoutList.get(0)).shapeList.size();
            Apptop_CollageActivity.this.bitmapList = bitmapListTemp2;
            i4 = 0;
            while (true) {
                int size3 = collage.collageLayoutList.size();
                str = Apptop_CollageActivity.TAG;
                if (i4 >= size3) {
                    break;
                }
                Shape[] shapeArray = new Shape[size2];
                j = 0;
                while (j < currentBitmapListTemp2.length) {
                    boolean masked;
                    Bitmap[] maskedArr = null;
                    int resId;
                    boolean masked2 = false;
                    int resId2 = 0;
                    if (((CollageLayout) collage.collageLayoutList.get(i4)).maskPairList == null || ((CollageLayout) collage.collageLayoutList.get(i4)).maskPairList.isEmpty()) {
                        masked = false;
                        resId = 0;
                    } else {
                        Iterator it = ((CollageLayout) collage.collageLayoutList.get(i4)).maskPairList.iterator();
                        while (it.hasNext()) {
                            MaskPair maskPair = (MaskPair) it.next();
                            Iterator it2 = it;
                            if (j == maskPair.index) {
                                masked2 = true;
                                resId2 = maskPair.id;
                            }
                            it = it2;
                        }
                        masked = masked2;
                        resId = resId2;
                    }
                    PointF[] pointFArr;
                    Bitmap bitmap;
                    int i5;
                    if (masked) {
                        Bitmap maskBitmap;
                        int maskIndez = getMaskIndex(resId);
                        if (maskIndez >= 0) {
                            if (maskBitmapArray == null) {
                                maskBitmapArray = new Bitmap[maskResIdList.length];
                            }
                            Bitmap[] maskedArray = maskBitmapArray;
                            if (maskedArray[maskIndez] == null) {
                                maskedArray[maskIndez] = loadMaskBitmap2(resId);
                                Log.e(str, "load mask bitmap from factory");
                            } else {
                                Log.e(str, "load mask bitmap from pool");
                            }
                            maskBitmap = maskBitmapArray[maskIndez];
                        } else {
                            maskBitmap = null;
                        }
                        pointFArr = (PointF[]) ((CollageLayout) collage.collageLayoutList.get(i4)).shapeList.get(j);
                        bitmap = currentBitmapListTemp2[j];
                        int i6 = offsetX;
                        resId = offsetY;
                        bitmapListTemp = bitmapListTemp2;
                        boolean z = Apptop_CollageActivity.this.isScrapBook;
                        size = size2;
                        Bitmap bitmap2 = Apptop_CollageActivity.this.btmDelete;
                        str2 = str;
                        i5 = resId;
                        Bitmap bitmap3 = maskedArr[maskIndez];
                        boolean z2 = z;
                        int i7 = j;
                        Bitmap bitmap4 = bitmap2;
                        shapeArray[j] = new Shape(pointFArr, bitmap, null, i6, i5, bitmap3, z2, i7, true, bitmap4, Apptop_CollageActivity.this.btmScale, screenWidth);
                        if (Apptop_CollageActivity.this.isScrapBook) {
                            shapeArray[j].initScrapBook(Apptop_CollageActivity.this.npd);
                        }
                        currentBitmapListTemp = currentBitmapListTemp2;
                    } else {
                        int i8 = resId;
                        bitmapListTemp = bitmapListTemp2;
                        size = size2;
                        str2 = str;
                        pointFArr = (PointF[]) ((CollageLayout) collage.collageLayoutList.get(i4)).shapeList.get(j);
                        bitmap = currentBitmapListTemp2[j];
                        int[] iArr = ((CollageLayout) collage.collageLayoutList.get(i4)).getexceptionIndex(j);
                        resId = offsetX;
                        int i9 = offsetY;
                        boolean z3 = Apptop_CollageActivity.this.isScrapBook;
                        Bitmap bitmap5 = Apptop_CollageActivity.this.btmDelete;
                        currentBitmapListTemp = currentBitmapListTemp2;
                        int i10 = resId;
                        i5 = i9;
                        boolean z4 = z3;
                        int i11 = j;
                        Bitmap bitmap6 = bitmap5;
                        shapeArray[j] = new Shape(pointFArr, bitmap, iArr, i10, i5, z4, i11, true, bitmap6, Apptop_CollageActivity.this.btmScale, screenWidth);
                        if (Apptop_CollageActivity.this.isScrapBook) {
                            shapeArray[j].initScrapBook(Apptop_CollageActivity.this.npd);
                        }
                    }
                    j++;
                    currentBitmapListTemp2 = currentBitmapListTemp;
                    bitmapListTemp2 = bitmapListTemp;
                    size2 = size;
                    str = str2;
                }
                currentBitmapListTemp = currentBitmapListTemp2;
                bitmapListTemp = bitmapListTemp2;
                size = size2;
                if (Apptop_CollageActivity.this.isScrapBook) {
                    for (int i3 = 0; i3 < scrapBookTemp.length; i3++) {
                        if (i3 < i) {
                            shapeArray[i3].bitmapMatrix.set(scrapBookTemp[i3].bitmapMatrix);
                        }
                        if (i3 > i) {
                            shapeArray[i3 - 1].bitmapMatrix.set(scrapBookTemp[i3].bitmapMatrix);
                        }
                    }
                }
                ShapeLayout shapeLayout = new ShapeLayout(shapeArray);
                shapeLayout.setClearIndex(((CollageLayout) collage.collageLayoutList.get(i4)).getClearIndex());
                shapeLayoutList.add(shapeLayout);
                smallestDistanceList.add(Float.valueOf(smallestDistance(shapeArray)));
                i4++;
                currentBitmapListTemp2 = currentBitmapListTemp;
                bitmapListTemp2 = bitmapListTemp;
                size2 = size;
            }
            currentBitmapListTemp = currentBitmapListTemp2;
            bitmapListTemp = bitmapListTemp2;
            size = size2;
            str2 = str;
            currentCollageIndex = 0;
            Apptop_CollageActivity.this.collageAdapter.selectedPosition = 0;
            Apptop_CollageActivity.this.collageAdapter.setData(Collage.collageIconArray[newSize - 1]);
            Apptop_CollageActivity.this.collageAdapter.notifyDataSetChanged();
            if (isScrapBook) {
//                i3 = height;
            } else {
                updateShapeListForRatio(i2, height);
            }
            unselectShapes();
            for (int resId = 0; resId < shapeLayoutList.get(0).shapeArr.length; resId++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("i ");
                stringBuilder.append(resId);
                stringBuilder.append("is recylced ");
                stringBuilder.append(shapeLayoutList.get(0).shapeArr[resId].getBitmap().isRecycled());
                Log.e(str2, stringBuilder.toString());
            }
            invalidate();
            if (currentBitmapListTemp.length == 1) {
                Apptop_CollageActivity.this.setVisibilityForSingleImage();
            }
            if (newSize == 1) {
                setPathPadding(0, 0.0f);
                if (sizeScale == 1.0f && !Apptop_CollageActivity.this.isScrapBook) {
                    setCollageSize(sizeMatrix, getResources().getInteger(R.integer.default_ssize_value));
                }
            }
        }

        Bitmap loadMaskBitmapx(int resId) {
            new Options().inPreferredConfig = Config.ARGB_8888;
            Bitmap source = BitmapFactory.decodeResource(getResources(), resId);
            Bitmap mask = source.extractAlpha();
            source.recycle();
            return mask;
        }

        Bitmap loadMaskBitmap2(int resId) {
            return convertToAlphaMask(BitmapFactory.decodeResource(getResources(), resId));
        }

        private Bitmap convertToAlphaMask(Bitmap b) {
            Bitmap a = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Config.ALPHA_8);
            new Canvas(a).drawBitmap(b, 0.0f, 0.0f, null);
            b.recycle();
            return a;
        }

        public float smallestDistance(Shape[] shapeArray) {
            int i = 0;
            float smallestDistance = shapeArray[0].smallestDistance();
            int length = shapeArray.length;
            while (i < length) {
                float distance = shapeArray[i].smallestDistance();
                if (distance < smallestDistance) {
                    smallestDistance = distance;
                }
                i++;
            }
            return smallestDistance;
        }

        private void updateShapeListForRatio(int width, int height) {
            int i = width;
            int i2 = 0;
            int shapeCount = this.shapeLayoutList.get(0).shapeArr.length;
            PointF scale = getRatio();
            calculateOffset();
            Collage collage = Collage.CreateCollage(shapeCount, (int) (scale.x * ((float) i)), (int) (((float) i) * scale.y), Apptop_CollageActivity.this.isScrapBook);
            this.smallestDistanceList.clear();
            int index = 0;
            while (index < shapeLayoutList.size()) {
                if (shapeCount == 1) {
                    shapeLayoutList.get(index).shapeArr[i2].changeRatio((PointF[]) ((CollageLayout) collage.collageLayoutList.get(index)).shapeList.get(i2), null, offsetX, offsetY, Apptop_CollageActivity.this.isScrapBook, 0, (int) (scale.x * ((float) i)), (int) (((float) i) * scale.y));
                } else {
                    for (i2 = 0; i2 < shapeCount; i2++) {
                        shapeLayoutList.get(index).shapeArr[i2].changeRatio((PointF[]) ((CollageLayout) collage.collageLayoutList.get(index)).shapeList.get(i2), null, offsetX, offsetY, Apptop_CollageActivity.this.isScrapBook, i2, (int) (scale.x * ((float) i)), (int) (((float) i) * scale.y));
                    }
                }
                smallestDistanceList.add(Float.valueOf(smallestDistance(shapeLayoutList.get(index).shapeArr)));
                setPathPadding(index, paddingDistance);
                if (!Apptop_CollageActivity.this.isScrapBook) {
                    for (Shape scaleMatrix : shapeLayoutList.get(index).shapeArr) {
                        scaleMatrix.setScaleMatrix(1);
                    }
                }
                index++;
                i2 = 0;
            }
            setCornerRadius(cornerRadius);
            Bitmap bitmap = blurBitmap;
            if (bitmap != null) {
                setBlurRect2((float) bitmap.getWidth(), (float) blurBitmap.getHeight());
            }
            postInvalidate();
        }

        PointF getRatio() {
            this.yscale = 1.0f;
            this.xscale = 1.0f;
            this.yscale = Apptop_CollageActivity.this.mulY / Apptop_CollageActivity.this.mulX;
            if (!Apptop_CollageActivity.this.isScrapBook) {
                float f = this.yscale;
                if (f > RATIO_CONSTANT) {
                    this.xscale = RATIO_CONSTANT / f;
                    this.yscale = RATIO_CONSTANT;
                }
            }
            return new PointF(this.xscale, this.yscale);
        }

        private void updateShapeListForFilterBitmap(Bitmap bitmap) {
            if (this.shapeIndex >= 0) {
                for (int i = 0; i < this.shapeLayoutList.size(); i++) {
                    this.shapeLayoutList.get(i).shapeArr[this.shapeIndex].setBitmap(bitmap, true);
                }
            }
        }

        void updateParamList(Parameter p) {
            if (this.shapeIndex >= 0) {
                Apptop_CollageActivity.this.parameterList[this.shapeIndex] = new Parameter(p);
            }
        }

        @SuppressLint({"WrongConstant"})
        private void swapBitmaps(int index1, int index2) {
            Bitmap bitmap1 = this.shapeLayoutList.get(0).shapeArr[index1].getBitmap();
            Bitmap bitmap2 = this.shapeLayoutList.get(0).shapeArr[index2].getBitmap();
            for (int i = 0; i < this.shapeLayoutList.size(); i++) {
                this.shapeLayoutList.get(i).shapeArr[index1].setBitmap(bitmap2, false);
                this.shapeLayoutList.get(i).shapeArr[index2].setBitmap(bitmap1, false);
            }
            Bitmap temp = Apptop_CollageActivity.this.bitmapList[index1];
            Apptop_CollageActivity.this.bitmapList[index1] = Apptop_CollageActivity.this.bitmapList[index2];
            Apptop_CollageActivity.this.bitmapList[index2] = temp;
            Parameter tempParam = Apptop_CollageActivity.this.parameterList[index1];
            Apptop_CollageActivity.this.parameterList[index1] = Apptop_CollageActivity.this.parameterList[index2];
            Apptop_CollageActivity.this.parameterList[index2] = tempParam;
            float sd = this.smallestDistanceList.get(index1).floatValue();
            ArrayList arrayList = this.smallestDistanceList;
            arrayList.set(index1, arrayList.get(index2));
            this.smallestDistanceList.set(index2, Float.valueOf(sd));
            Apptop_CollageActivity.this.selectSwapTextView.setVisibility(4);
            unselectShapes();
        }

        void setCurrentCollageIndex(int index) {
            this.currentCollageIndex = index;
            if (this.currentCollageIndex >= this.shapeLayoutList.size()) {
                this.currentCollageIndex = 0;
            }
            if (this.currentCollageIndex < 0) {
                this.currentCollageIndex = this.shapeLayoutList.size() - 1;
            }
            setCornerRadius(this.cornerRadius);
            setPathPadding(this.currentCollageIndex, this.paddingDistance);
        }

        private void setCornerRadius(float radius) {
            this.cornerRadius = radius;
            CornerPathEffect corEffect = new CornerPathEffect(radius);
            for (Shape radius2 : this.shapeLayoutList.get(this.currentCollageIndex).shapeArr) {
                radius2.setRadius(corEffect);
            }
            postInvalidate();
        }

        private void setPathPadding(int index, float distance) {
            this.paddingDistance = distance;
            for (int i = 0; i < this.shapeLayoutList.get(index).shapeArr.length; i++) {
                Shape shape = this.shapeLayoutList.get(index).shapeArr[i];
                float floatValue = (this.smallestDistanceList.get(index).floatValue() / 250.0f) * distance;
                int i2 = this.screenWidth;
                shape.scalePath(floatValue, (float) i2, (float) i2);
                if (!Apptop_CollageActivity.this.isScrapBook) {
                    this.shapeLayoutList.get(index).shapeArr[i].checkScaleBounds();
                    this.shapeLayoutList.get(index).shapeArr[i].checkBoundries();
                }
            }
            postInvalidate();
        }

        private void setCollageSize(Matrix matrix, int progress) {
            matrix.reset();
            this.sizeScale = calculateSize((float) progress);
            float f = this.sizeScale;
            int i = this.offsetX;
            float f2 = (((float) (i + i)) + (((float) Apptop_CollageActivity.this.width) * this.xscale)) / 2.0f;
            int i2 = this.offsetY;
            matrix.postScale(f, f, f2, (((float) (i2 + i2)) + (((float) Apptop_CollageActivity.this.width) * this.yscale)) / 2.0f);
            invalidate();
        }

        float calculateSize(float progress) {
            return 1.0f - (progress / 200.0f);
        }

        int calculateSizeProgress(float size) {
            int progress = 200 - Math.round(200.0f * size);
            if (progress < 0) {
                progress = 0;
            }
            if (progress > 100) {
                return 100;
            }
            return progress;
        }

        void setPatternPaint(int resId) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(1);
                this.patternPaint.setColor(-1);
            }
            if (resId == -1) {
                this.patternPaint.setShader(null);
                this.patternPaint.setColor(-1);
                postInvalidate();
                return;
            }
            this.patternBitmap = BitmapFactory.decodeResource(getResources(), resId);
            this.patternPaint.setShader(new BitmapShader(this.patternBitmap, TileMode.REPEAT, TileMode.REPEAT));
            postInvalidate();
        }

        void setPatternPaintColor(int color) {
            if (this.patternPaint == null) {
                this.patternPaint = new Paint(1);
            }
            this.patternPaint.setShader(null);
            this.patternPaint.setColor(color);
            postInvalidate();
        }

        public void setFrame(int index) {
            if (this.frameRect == null) {
                int i = this.screenWidth;
                this.frameRect = new RectF(0.0f, 0.0f, (float) i, (float) i);
            }
            Bitmap bitmap = this.frameBitmap;
            if (!(bitmap == null || bitmap.isRecycled())) {
                this.frameBitmap.recycle();
                this.frameBitmap = null;
            }
            if (index != 0) {
                this.frameBitmap = BitmapFactory.decodeResource(getResources(), LibUtility.borderRes[index]);
                postInvalidate();
            }
        }

        public void startAnimator() {
            if (Apptop_CollageActivity.this.seekbarSize != null) {
                this.animSizeSeekbarProgress = Apptop_CollageActivity.this.seekbarSize.getProgress();
            } else {
                this.animSizeSeekbarProgress = 0;
            }
            this.sizeMatrixSaved = new Matrix(this.sizeMatrix);
            this.animationCount = 0;
            this.animate = true;
            removeCallbacks(this.animator);
            postDelayed(this.animator, 150);
        }

        int animSize(int value) {
            int res;
            if (value < this.animHalfTime) {
                res = value;
            } else {
                res = this.animationLimit - value;
            }
            return this.animSizeSeekbarProgress + Math.round((float) (res * 2));
        }

        @SuppressLint({"WrongConstant"})
        public void onDraw(Canvas canvas) {
            int width = getWidth();
            int height = getHeight();
            canvas.save();
            RectF rectF = this.drawingAreaRect;
            int i = this.offsetX;
            float f = (float) i;
            int i2 = this.offsetY;
            rectF.set(f, (float) i2, ((float) i) + (((float) width) * this.xscale), ((float) i2) + (((float) width) * this.yscale));
            canvas.drawPaint(this.paintGray);
            if (this.backgroundMode == 0) {
                canvas.drawRect(this.drawingAreaRect, this.patternPaint);
            }
            Bitmap bitmap = this.blurBitmap;
            if (!(bitmap == null || bitmap.isRecycled() || this.backgroundMode != 1)) {
                this.blurRectDst.set(this.drawingAreaRect);
                canvas.drawBitmap(this.blurBitmap, this.blurRectSrc, this.blurRectDst, this.paint);
            }
            if (!Apptop_CollageActivity.this.isScrapBook) {
                canvas.setMatrix(this.sizeMatrix);
            }
            int j = 0;
            if (!Apptop_CollageActivity.this.isScrapBook || Apptop_CollageActivity.this.showText) {
                float f2 = (float) width;
                float f3 = this.sizeScale;
                j = canvas.saveLayer(0.0f, 0.0f, f2 / f3, ((float) height) / f3, null, 31);
            }
            int i3 = 0;
            while (i3 < this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length) {
                boolean drawPorterClear;
                drawPorterClear = i3 == this.shapeLayoutList.get(this.currentCollageIndex).getClearIndex();
                if (Apptop_CollageActivity.this.isScrapBook) {
                    this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i3].drawShapeForScrapBook(canvas, width, height, i3 == this.shapeIndex, this.orthogonal);
                } else {
                    this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i3].drawShape(canvas, width, height, j, drawPorterClear);
                }
                i3++;
            }
            if (!Apptop_CollageActivity.this.isScrapBook && this.shapeIndex >= 0 && this.shapeLayoutList.get(0).shapeArr.length > 1) {
                canvas.drawRect(this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bounds, this.borderPaint);
            }
            if (Apptop_CollageActivity.this.showText) {
                canvas.restoreToCount(j);
                for (i3 = 0; i3 < Apptop_CollageActivity.this.textDataList.size(); i3++) {
                    this.textMatrix.set(Apptop_CollageActivity.this.textDataList.get(i3).imageSaveMatrix);
                    canvas.setMatrix(this.textMatrix);
                    canvas.drawText(Apptop_CollageActivity.this.textDataList.get(i3).message, Apptop_CollageActivity.this.textDataList.get(i3).xPos, Apptop_CollageActivity.this.textDataList.get(i3).yPos, Apptop_CollageActivity.this.textDataList.get(i3).textPaint);
                    canvas.setMatrix(this.identityMatrix);
                }
            }
            bitmap = this.frameBitmap;
            if (!(bitmap == null || bitmap.isRecycled())) {
                canvas.drawBitmap(this.frameBitmap, null, this.frameRect, this.paint);
            }
            if (Apptop_CollageActivity.this.isScrapBook) {
                canvas.restore();
                this.above.set(0.0f, 0.0f, (float) canvas.getWidth(), this.drawingAreaRect.top);
                this.left.set(0.0f, this.drawingAreaRect.top, this.drawingAreaRect.left, this.drawingAreaRect.bottom);
                this.right.set(this.drawingAreaRect.right, this.drawingAreaRect.top, (float) canvas.getWidth(), this.drawingAreaRect.bottom);
                this.bottom.set(0.0f, this.drawingAreaRect.bottom, (float) canvas.getWidth(), (float) canvas.getHeight());
                canvas.drawRect(this.above, this.paintGray);
                canvas.drawRect(this.left, this.paintGray);
                canvas.drawRect(this.right, this.paintGray);
                canvas.drawRect(this.bottom, this.paintGray);
            }
        }

        public boolean onTouchEvent(MotionEvent ev) {
            MotionEvent motionEvent = ev;
            this.mScaleDetector.onTouchEvent(motionEvent);
            this.mTouchDetector.onTouchEvent(motionEvent);
            if (Apptop_CollageActivity.this.isScrapBook) {
                Apptop_CollageActivity.this.mRotationDetector.onTouchEvent(motionEvent);
            }
            int action = ev.getAction();
            int i = action & 255;
            int newPointerIndex = 0;
            float y;
            float[] fArr;
            if (i == 0) {
                previousIndex = shapeIndex;
                float x = ev.getX();
                y = ev.getY();
                mLastTouchX = x;
                mLastTouchY = y;
                orthogonal = false;
                mActivePointerId = motionEvent.getPointerId(0);
                if (!Apptop_CollageActivity.this.isScrapBook || shapeIndex < 0) {
                    selectCurrentShape(x, y, false);
                } else {
                    zoomStart.set(x, y);
                    pts = shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].getMappedCenter();
                    fArr = pts;
                    if (fArr != null) {
                        startAngle = -Utility.pointToAngle(x, y, fArr[0], fArr[1]);
                    }
                    isInCircle = shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].isInCircle(x, y);
                    isOnCross = shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].isOnCross(x, y);
                }
            }
            else if (i == 1) {
                orthogonal = false;
                mActivePointerId = 1;
                if (isOnCross) {
                    Apptop_CollageActivity.this.createDeleteDialog();
                }
                isInCircle = false;
                isOnCross = false;
                invalidate();
            }
            else if (i != 2) {
                if (i == 3) {
                    mActivePointerId = 1;
                    isInCircle = false;
                    isOnCross = false;
                } else if (i == 6) {
                    finalAngle = 0.0f;
                    i = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & action) >> 8;
                    if (motionEvent.getPointerId(i) == mActivePointerId) {
                        if (i == 0) {
                            newPointerIndex = 1;
                        }
                        mLastTouchX = motionEvent.getX(newPointerIndex);
                        mLastTouchY = motionEvent.getY(newPointerIndex);
                        mActivePointerId = motionEvent.getPointerId(newPointerIndex);
                    }
                }
            } else if (!isOnCross) {
                i = motionEvent.findPointerIndex(mActivePointerId);
                y = motionEvent.getX(i);
                float y2 = motionEvent.getY(i);
                if (shapeIndex < 0) {
                    selectCurrentShape(y, y2, false);
                }
                if (shapeIndex >= 0) {
                    if (Apptop_CollageActivity.this.isScrapBook) {
                        if (isInCircle) {
                            float currentAngle;
                            pts = shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].getMappedCenter();
                            float[] fArr2 = pts;
                            float currentAngle2 = -Utility.pointToAngle(y, y2, fArr2[0], fArr2[1]);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("currentAngle ");
                            stringBuilder.append(currentAngle2);
                            String stringBuilder2 = stringBuilder.toString();
                            String str = Apptop_CollageActivity.TAG;
                            Log.d(str, stringBuilder2);
                            float rotation = getMatrixRotation(shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].bitmapMatrix);
                            if ((rotation == 0.0f || rotation == 90.0f || rotation == 180.0f || rotation == -180.0f || rotation == -90.0f) && Math.abs(startAngle - currentAngle2) < 4.0f) {
                                orthogonal = true;
                            } else {
                                StringBuilder stringBuilder3;
                                if (Math.abs((rotation - startAngle) + currentAngle2) < 4.0f) {
                                    currentAngle = startAngle - rotation;
                                    orthogonal = true;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("aaaaa ");
                                    stringBuilder3.append(rotation);
                                    Log.d(str, stringBuilder3.toString());
                                    currentAngle2 = currentAngle;
                                } else if (Math.abs(90.0f - ((rotation - startAngle) + currentAngle2)) < 4.0f) {
                                    currentAngle = (startAngle + 90.0f) - rotation;
                                    orthogonal = true;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("bbbbb ");
                                    stringBuilder3.append(rotation);
                                    Log.d(str, stringBuilder3.toString());
                                    currentAngle2 = currentAngle;
                                } else if (Math.abs(180.0f - ((rotation - startAngle) + currentAngle2)) < 4.0f) {
                                    currentAngle = (startAngle + 180.0f) - rotation;
                                    orthogonal = true;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("cccc ");
                                    stringBuilder3.append(rotation);
                                    Log.d(str, stringBuilder3.toString());
                                    currentAngle2 = currentAngle;
                                } else if (Math.abs(-180.0f - ((rotation - startAngle) + currentAngle2)) < 4.0f) {
                                    currentAngle = (startAngle - 0.024902344f) - rotation;
                                    orthogonal = true;
                                    currentAngle2 = currentAngle;
                                } else if (Math.abs(-90.0f - ((rotation - startAngle) + currentAngle2)) < 4.0f) {
                                    currentAngle = (startAngle - 0.049804688f) - rotation;
                                    orthogonal = true;
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("dddd ");
                                    stringBuilder3.append(rotation);
                                    Log.d(str, stringBuilder3.toString());
                                    currentAngle2 = currentAngle;
                                } else {
                                    orthogonal = false;
                                }
                                shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].bitmapMatrixRotate(startAngle - currentAngle2);
                                startAngle = currentAngle2;
                            }
                            fArr = pts;
                            currentAngle = ((float) Math.sqrt((double) (((y - fArr[0]) * (y - fArr[0])) + ((y2 - fArr[1]) * (y2 - fArr[1]))))) / ((float) Math.sqrt((double) (((zoomStart.x - pts[0]) * (zoomStart.x - pts[0])) + ((zoomStart.y - pts[1]) * (zoomStart.y - pts[1])))));
                            float scale = shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].getScale();
                            float f = MIN_ZOOM;
                            if (scale >= f || (scale < f && currentAngle > 1.0f)) {
                                shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].bitmapMatrixScaleScrapBook(currentAngle, currentAngle);
                                zoomStart.set(y, y2);
                            }
                            invalidate();
                            return true;
                        }
                    }
                    shapeLayoutList.get(currentCollageIndex).shapeArr[shapeIndex].bitmapMatrixTranslate(y - mLastTouchX, y2 - mLastTouchY);
                    mLastTouchX = y;
                    mLastTouchY = y2;
                    invalidate();
                }
            }
            return true;
        }

        @SuppressLint({"WrongConstant"})
        private void selectCurrentShapeScrapBook(float x, float y, boolean isSingleTap) {
            int i;
            int length = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length;
            boolean isSelected = false;
            for (i = length - 1; i >= 0; i--) {
                if (this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i].isScrapBookSelected(x, y)) {
                    this.shapeIndex = i;
                    isSelected = true;
                    break;
                }
            }
            if (this.previousIndex == this.shapeIndex && isSingleTap) {
                unselectShapes();
            } else if (!isSelected) {
                unselectShapes();
            } else if (Apptop_CollageActivity.this.selectImageForAdj) {
                openFilterFragment();
            } else {
                int i2 = this.shapeIndex;
                if (i2 >= 0 && i2 < length) {
                    Shape shapeTemp = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex];
                    Bitmap btmTemp = Apptop_CollageActivity.this.bitmapList[this.shapeIndex];
                    Parameter prmTemp = Apptop_CollageActivity.this.parameterList[this.shapeIndex];
                    for (i = 0; i < length; i++) {
                        if (i >= this.shapeIndex) {
                            if (i < length - 1) {
                                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i] = this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i + 1];
                                Apptop_CollageActivity.this.bitmapList[i] = Apptop_CollageActivity.this.bitmapList[i + 1];
                                Apptop_CollageActivity.this.parameterList[i] = Apptop_CollageActivity.this.parameterList[i + 1];
                            } else {
                                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i] = shapeTemp;
                                Apptop_CollageActivity.this.bitmapList[i] = btmTemp;
                                Apptop_CollageActivity.this.parameterList[i] = prmTemp;
                            }
                        }
                    }
                    int i3 = this.previousIndex;
                    int i4 = this.shapeIndex;
                    if (i3 == i4) {
                        this.previousIndex = length - 1;
                    } else if (i3 > i4) {
                        this.previousIndex = i3 - 1;
                    }
                    this.shapeIndex = length - 1;
                    if (this.shapeLayoutList.get(0).shapeArr.length > 0) {
                        Apptop_CollageActivity.this.contextFooter.setVisibility(0);
                        Apptop_CollageActivity.this.setSelectedTab(5);
                    }
                }
            }
            if (this.shapeIndex >= 0) {
                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[0];
            }
            postInvalidate();
        }

        private void selectCurrentShape(float x, float y, boolean isSingleTap) {
            if (Apptop_CollageActivity.this.isScrapBook) {
                selectCurrentShapeScrapBook(x, y, isSingleTap);
            } else {
                selectCurrentShapeCollage(x, y, isSingleTap);
            }
        }

        private void selectCurrentShapeCollage(float x, float y, boolean isSingleTap) {
            int i;
            int swapIndex = this.shapeIndex;
            for (i = 0; i < this.shapeLayoutList.get(this.currentCollageIndex).shapeArr.length; i++) {
                if (this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[i].region.contains((int) x, (int) y)) {
                    this.shapeIndex = i;
                }
            }
            if (Apptop_CollageActivity.this.selectImageForAdj) {
                openFilterFragment();
            } else {
                boolean z = Apptop_CollageActivity.this.swapMode;
                String str = Apptop_CollageActivity.TAG;
                if (z) {
                    Log.e(str, "PRE SWAP");
                    i = this.shapeIndex;
                    if (swapIndex != i && swapIndex > -1 && i > -1) {
                        Log.e(str, "SWAP");
                        swapBitmaps(this.shapeIndex, swapIndex);
                        Apptop_CollageActivity.this.swapMode = false;
                    }
                } else if (this.previousIndex == this.shapeIndex && isSingleTap) {
                    unselectShapes();
                } else if (this.shapeLayoutList.get(0).shapeArr.length > 0) {
                    Apptop_CollageActivity.this.contextFooter.setVisibility(VISIBLE);
                    Apptop_CollageActivity.this.setSelectedTab(5);
                    Log.e(str, "VISIBLE");
                }
            }
            if (this.shapeIndex >= 0) {
                this.shapeLayoutList.get(this.currentCollageIndex).shapeArr[this.shapeIndex].bitmapMatrixgGetValues(this.matrixValues);
                this.mScaleFactor = this.matrixValues[0];
            }
            postInvalidate();
        }

        @SuppressLint({"WrongConstant"})
        void unselectShapes() {
            Apptop_CollageActivity.this.contextFooter.setVisibility(4);
            this.shapeIndex = -1;
            Log.e(Apptop_CollageActivity.TAG, "unselectShapes");
            postInvalidate();
        }

        @SuppressLint({"WrongConstant"})
        public void openFilterFragment() {
            Apptop_CollageActivity.this.selectFilterTextView.setVisibility(4);
            Apptop_CollageActivity collageActivity = Apptop_CollageActivity.this;
            collageActivity.selectImageForAdj = false;
            if (this.shapeIndex >= 0) {
                collageActivity.fullEffectFragment.setBitmapWithParameter(Apptop_CollageActivity.this.bitmapList[this.shapeIndex], Apptop_CollageActivity.this.parameterList[this.shapeIndex]);
                Apptop_CollageActivity.this.setVisibilityOfFilterHorizontalListview(true);
            }
        }

        float getMatrixRotation(Matrix matrix) {
            matrix.getValues(this.values);
            float[] fArr = this.values;
            return (float) Math.round(Math.atan2((double) fArr[1], (double) fArr[0]) * 57.29577951308232d);
        }

        public void setBlurBitmap(int radius, boolean cascade) {
            if (this.blurBuilderNormal == null) {
                this.blurBuilderNormal = new BlurBuilderNormal();
            }
            if (cascade) {
                this.backgroundMode = 2;
                if (!Apptop_CollageActivity.this.isScrapBook) {
                    Apptop_CollageActivity.this.seekbarSize.setProgress(Apptop_CollageActivity.this.seekbarSize.getMax());
                }
            } else {
                this.backgroundMode = 1;
            }
            this.blurBitmap = NativeStackBlur.process(Apptop_CollageActivity.this.bitmapList[0].copy(Apptop_CollageActivity.this.bitmapList[0].getConfig(), true), radius);
            Bitmap bitmap = this.blurBitmap;
            if (bitmap != null) {
                setBlurRect2((float) bitmap.getWidth(), (float) this.blurBitmap.getHeight());
            }
            postInvalidate();
        }

        void setBlurRect2(float btmwidth, float btmheight) {
            float w;
            float h;
            if ((Apptop_CollageActivity.this.mulY * btmwidth) / Apptop_CollageActivity.this.mulX < btmheight) {
                w = (float) ((int) btmwidth);
                h = (Apptop_CollageActivity.this.mulY * btmwidth) / Apptop_CollageActivity.this.mulX;
            } else {
                w = (((float) ((int) Apptop_CollageActivity.this.mulX)) * btmheight) / Apptop_CollageActivity.this.mulY;
                h = (float) ((int) btmheight);
            }
            int l = (int) ((btmwidth - w) / 1073741824);
            int t = (int) ((btmheight - h) / 2.0f);
            this.blurRectSrc.set(l, t, (int) (((float) l) + w), (int) (((float) t) + h));
        }
    }

    private final class MyMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private MediaScannerConnection mConn;
        private String mFilename;
        private String mMimetype;

        MyMediaScannerConnectionClient(Context ctx, File file, String mimetype) {
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

        protected void onPreExecute() {
            this.progressDialog = new ProgressDialog(Apptop_CollageActivity.this);
            this.progressDialog.setMessage("Saving image...");
            this.progressDialog.show();
        }

        protected Object doInBackground(Object... arg0) {
            this.resultPath = Apptop_CollageActivity.this.collageView.saveBitmap(Apptop_CollageActivity.this.width, Apptop_CollageActivity.this.height);
            return null;
        }

        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog != null && progressDialog.isShowing()) {
                this.progressDialog.cancel();
            }
            if (this.resultPath != null) {
                Intent intent = new Intent(Apptop_CollageActivity.this, Apptop_ImageShareActivity.class);
                intent.putExtra("imagePath", this.resultPath);
                Apptop_CollageActivity.this.startActivity(intent);
                Apptop_CollageActivity.this.showInterstitialAd();
                Apptop_CollageActivity.this.finish();
            }
            Apptop_CollageActivity collageActivity = Apptop_CollageActivity.this;
            MyMediaScannerConnectionClient myMediaScannerConnectionClient = new MyMediaScannerConnectionClient(collageActivity.getApplicationContext(), new File(this.resultPath), null);
        }
    }


    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.collagelib.Apptop_CollageActivity$5 */
    class C08825 implements CurrentCollageIndexChangedListener {
        C08825() {
        }

        public void onIndexChanged(int color) {
            Apptop_CollageActivity.this.collageView.setPatternPaintColor(color);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.collagelib.Apptop_CollageActivity$6 */
    class C08836 implements CurrentCollageIndexChangedListener {
        C08836() {
        }

        public void onIndexChanged(int positionOrColor) {
            Apptop_CollageActivity.this.collageView.setPatternPaint(positionOrColor);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.collagelib.Apptop_CollageActivity$7 */
    class C08847 implements SingleTap {
        C08847() {
        }

        public void onSingleTap(TextData textData) {
            Apptop_CollageActivity.this.fontFragment = new FontFragment();
            Bundle arguments = new Bundle();
            arguments.putSerializable("text_data", textData);
            Apptop_CollageActivity.this.fontFragment.setArguments(arguments);
            Apptop_CollageActivity.this.getSupportFragmentManager().beginTransaction().replace(R.id.collage_text_view_fragment_container, Apptop_CollageActivity.this.fontFragment, "FONT_FRAGMENT").commit();
            Log.e(Apptop_CollageActivity.TAG, "replace fragment");
            Apptop_CollageActivity.this.fontFragment.setFontChoosedListener(Apptop_CollageActivity.this.fontChoosedListener);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.collagelib.Apptop_CollageActivity$8 */
    class C08858 implements ApplyTextInterface {
        C08858() {
        }

        public void onOk(ArrayList<TextData> tdList) {
            Iterator it = tdList.iterator();
            while (it.hasNext()) {
                ((TextData) it.next()).setImageSaveMatrix(Apptop_CollageActivity.this.collageView.identityMatrix);
            }
            Apptop_CollageActivity collageActivity = Apptop_CollageActivity.this;
            collageActivity.textDataList = tdList;
            collageActivity.showText = true;
            if (collageActivity.mainLayout == null) {
                collageActivity = Apptop_CollageActivity.this;
                collageActivity.mainLayout = collageActivity.findViewById(R.id.collage_main_layout);
            }
            Apptop_CollageActivity.this.mainLayout.removeView(Apptop_CollageActivity.this.canvasText);
            Apptop_CollageActivity.this.collageView.postInvalidate();
        }

        public void onCancel() {
            Apptop_CollageActivity collageActivity = Apptop_CollageActivity.this;
            collageActivity.showText = true;
            collageActivity.mainLayout.removeView(Apptop_CollageActivity.this.canvasText);
            Apptop_CollageActivity.this.collageView.postInvalidate();
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.collagelib.Apptop_CollageActivity$9 */
    class C08869 implements FullBitmapReady {
        C08869() {
        }

        public void onBitmapReady(Bitmap bitmap, Parameter parameter) {
            Apptop_CollageActivity.this.collageView.updateShapeListForFilterBitmap(bitmap);
            Apptop_CollageActivity.this.collageView.updateParamList(parameter);
            Apptop_CollageActivity.this.collageView.postInvalidate();
            Apptop_CollageActivity.this.getSupportFragmentManager().beginTransaction().hide(Apptop_CollageActivity.this.fullEffectFragment).commit();
            Apptop_CollageActivity.this.collageView.postInvalidate();
        }

        public void onCancel() {
            Apptop_CollageActivity.this.setVisibilityOfFilterHorizontalListview(false);
            Apptop_CollageActivity.this.collageView.postInvalidate();
        }
    }

    class C05021 implements FontChoosedListener {
        C05021() {
        }

        public void onOk(TextData textData) {
            if (Apptop_CollageActivity.this.canvasText == null) {
                Apptop_CollageActivity.this.addCanvasTextView();
            }
            Apptop_CollageActivity.this.canvasText.addTextView(textData);
            Apptop_CollageActivity.this.getSupportFragmentManager().beginTransaction().remove(Apptop_CollageActivity.this.fontFragment).commit();
            Log.e(Apptop_CollageActivity.TAG, "onOK called");
        }
    }

    class C05065 implements CurrentCollageIndexChangedListener {
        C05065() {
        }

        public void onIndexChanged(int i) {
            Apptop_CollageActivity.this.collageView.setCurrentCollageIndex(i);
        }
    }

    class C05087 implements CurrentCollageIndexChangedListener {
        C05087() {
        }

        public void onIndexChanged(int color) {
            Apptop_CollageActivity.this.collageView.setPatternPaintColor(color);
        }
    }

    @SuppressLint({"WrongConstant"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().addFlags(1024);
        Display display = getWindowManager().getDefaultDisplay();
        this.width = display.getWidth();
        this.height = display.getHeight();
        setContentView(R.layout.activity_collage);
        mAdView = findViewById(R.id.adView);
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mAdView.setVisibility(View.VISIBLE);
            }
        });
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        if (Glob.isOnline(this)) {
            loadInterstitialAd();
        }

        int arraySize = getCollageSize(getIntent().getExtras());
        seekBarRound = findViewById(R.id.seekbar_round);
        seekBarRound.setOnSeekBarChangeListener(mSeekBarListener);
        seekBarPadding = findViewById(R.id.seekbar_padding);
        seekBarPadding.setOnSeekBarChangeListener(mSeekBarListener);
        seekbarSize = findViewById(R.id.seekbar_size);
        seekbarSize.setOnSeekBarChangeListener(mSeekBarListener);
        seekbarBlur = findViewById(R.id.seekbar_collage_blur);
        seekbarBlur.setOnSeekBarChangeListener(mSeekBarListener);
        RecyclerView recyclerViewColor = findViewById(R.id.recyclerView_color);
        collageRecyclerView = findViewById(R.id.recyclerView_grid);
        int colorDefault = getResources().getColor(R.color.view_flipper_bg_color);
        int colorSelected = getResources().getColor(R.color.footer_button_color_pressed);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        collageRecyclerView.setLayoutManager(linearLayoutManager);
        int i = colorDefault;
        int i2 = colorSelected;
        collageAdapter = new MyAdapter(Collage.collageIconArray[arraySize - 1], new C05065(), i, i2, false, true);
        collageRecyclerView.setAdapter(collageAdapter);
        collageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        viewFlipper = findViewById(R.id.collage_view_flipper);
        viewFlipper.setDisplayedChild(5);
        createAdapterList(colorDefault, colorSelected);
        RecyclerView recyclerViewPattern = findViewById(R.id.recyclerView_pattern);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        colorContainer = findViewById(R.id.color_container);
        recyclerViewPattern.setLayoutManager(linearLayoutManager);
        final RecyclerView recyclerView = recyclerViewColor;
        Adapter myAdapter3 = new MyAdapter(Utility.patternResIdList3, new CurrentCollageIndexChangedListener() {
            @SuppressLint({"WrongConstant"})
            public void onIndexChanged(int position) {
                Apptop_CollageActivity.this.collageView.backgroundMode = 0;
                if (position == 0) {
                    Apptop_CollageActivity.this.collageView.setPatternPaint(-1);
                    return;
                }
                int newPos = position - 1;
                if (Apptop_CollageActivity.this.patternAdapterList.get(newPos) != recyclerView.getAdapter()) {
                    recyclerView.setAdapter(Apptop_CollageActivity.this.patternAdapterList.get(newPos));
                    Apptop_CollageActivity.this.patternAdapterList.get(newPos).setSelectedPositinVoid();
                } else {
                    Apptop_CollageActivity.this.patternAdapterList.get(newPos).setSelectedPositinVoid();
                    Apptop_CollageActivity.this.patternAdapterList.get(newPos).notifyDataSetChanged();
                }
                Apptop_CollageActivity.this.colorContainer.setVisibility(0);
            }
        }, i, i2, false, false);
        recyclerViewPattern.setAdapter(myAdapter3);
        recyclerViewPattern.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(0);
        recyclerViewColor.setLayoutManager(linearLayoutManager2);
        recyclerViewColor.setAdapter(new ColorPickerAdapter(new C05087(), colorDefault, colorSelected));
        recyclerViewColor.setItemAnimator(new DefaultItemAnimator());
        HorizontalScrollView horizontalScrollView = findViewById(R.id.collage_footer);
        horizontalScrollView.bringToFront();
        HorizontalScrollView horizontalScrollView2 = horizontalScrollView;
        final HorizontalScrollView finalHorizontalScrollView = horizontalScrollView2;
        horizontalScrollView.postDelayed(new Runnable() {
            public void run() {
                HorizontalScrollView horizontalScrollView = finalHorizontalScrollView;
                horizontalScrollView.scrollTo(horizontalScrollView.getChildAt(0).getMeasuredWidth(), 0);
            }
        }, 50);
        final HorizontalScrollView finalHorizontalScrollView1 = horizontalScrollView2;
        horizontalScrollView.postDelayed(new Runnable() {
            public void run() {
                finalHorizontalScrollView1.fullScroll(17);
            }
        }, 600);
        Bundle extras = getIntent().getExtras();
        new BitmapWorkerTask().execute(extras, bundle);
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

    private void createAdapterList(int colorDefault, int colorSelected) {
        this.patternAdapterList.clear();
        this.patternAdapterList.add(new ColorPickerAdapter(new C08825(), colorDefault, colorSelected));
        for (int[] myAdapter : Utility.patternResIdList2) {
            this.patternAdapterList.add(new MyAdapter(myAdapter, new C08836(), colorDefault, colorSelected, true, true));
        }
    }

    int getCollageSize(Bundle extras) {
        long[] selectedImageList = extras.getLongArray("photo_id_list");
        if (selectedImageList == null) {
            return 1;
        }
        return selectedImageList.length;
    }

    public void addCanvasTextView() {
        this.canvasText = new CustomRelativeLayout(this, this.textDataList, this.collageView.identityMatrix, new C08847());
        this.canvasText.setApplyTextListener(new C08858());
        this.showText = false;
        this.collageView.invalidate();
        this.mainLayout.addView(this.canvasText);
        findViewById(R.id.collage_text_view_fragment_container).bringToFront();
        this.fontFragment = new FontFragment();
        this.fontFragment.setArguments(new Bundle());
        getSupportFragmentManager().beginTransaction().add(R.id.collage_text_view_fragment_container, this.fontFragment, "FONT_FRAGMENT").commit();
        Log.e(TAG, "add fragment");
        this.fontFragment.setFontChoosedListener(this.fontChoosedListener);
    }

    @SuppressLint({"WrongConstant"})
    private void setVisibilityForScrapbook() {
        findViewById(R.id.button_collage_layout).setVisibility(View.GONE);
        findViewById(R.id.button_collage_space).setVisibility(View.GONE);
        findViewById(R.id.button_collage_context_swap).setVisibility(View.GONE);
        findViewById(R.id.button_collage_context_fit).setVisibility(View.GONE);
        findViewById(R.id.button_collage_context_center).setVisibility(View.GONE);
        findViewById(R.id.button_collage_context_delete).setVisibility(View.VISIBLE);
    }

    void addEffectFragment() {
        if (this.fullEffectFragment == null) {
            String str = "FULL_FRAGMENT";
            this.fullEffectFragment = (FullEffectFragment) getSupportFragmentManager().findFragmentByTag(str);
            String str2 = TAG;
            Log.e(str2, "addEffectFragment");
            if (this.fullEffectFragment == null) {
                this.fullEffectFragment = new FullEffectFragment();
                Log.e(str2, "EffectFragment == null");
                this.fullEffectFragment.setArguments(getIntent().getExtras());
                Log.e(str2, "fullEffectFragment null");
                getSupportFragmentManager().beginTransaction().add(R.id.collage_effect_fragment_container, this.fullEffectFragment, str).commitAllowingStateLoss();
            } else {
                Log.e(str2, "not null null");
                if (this.collageView.shapeIndex >= 0) {
                    this.fullEffectFragment.setBitmapWithParameter(this.bitmapList[this.collageView.shapeIndex], this.parameterList[this.collageView.shapeIndex]);
                }
            }
            getSupportFragmentManager().beginTransaction().hide(this.fullEffectFragment).commitAllowingStateLoss();
            this.fullEffectFragment.setFullBitmapReadyListener(new C08869());
            findViewById(R.id.collage_effect_fragment_container).bringToFront();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }

    }

    @Override
    protected void onPause() {

        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }


    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        int i;
        super.onDestroy();
        if (this.bitmapList != null) {
            i = 0;
            while (true) {
                Bitmap[] bitmapArr = this.bitmapList;
                if (i >= bitmapArr.length) {
                    break;
                }
                if (bitmapArr[i] != null) {
                    bitmapArr[i].recycle();
                }
                i++;
            }
        }
        CollageView collageView = this.collageView;
        if (collageView != null) {
            if (collageView.shapeLayoutList != null) {
                for (i = 0; i < this.collageView.shapeLayoutList.size(); i++) {
                    for (int j = 0; j < this.collageView.shapeLayoutList.get(i).shapeArr.length; j++) {
                        if (this.collageView.shapeLayoutList.get(i).shapeArr[j] != null) {
                            this.collageView.shapeLayoutList.get(i).shapeArr[j].freeBitmaps();
                        }
                    }
                }
            }
            if (this.collageView.maskBitmapArray != null) {
                for (i = 0; i < this.collageView.maskBitmapArray.length; i++) {
                    if (this.collageView.maskBitmapArray[i] != null) {
                        if (!this.collageView.maskBitmapArray[i].isRecycled()) {
                            this.collageView.maskBitmapArray[i].recycle();
                        }
                        this.collageView.maskBitmapArray[i] = null;
                    }
                }
            }
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("show_text", this.showText);
        outState.putSerializable("text_data", this.textDataList);
        FontFragment fontFragment = this.fontFragment;
        if (fontFragment != null && fontFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
        }
        super.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.showText = savedInstanceState.getBoolean("show_text");
        this.textDataList = (ArrayList) savedInstanceState.getSerializable("text_data");
        if (this.textDataList == null) {
            this.textDataList = new ArrayList();
        }
        if (this.contextFooter == null) {
            this.contextFooter = findViewById(R.id.collage_context_menu);
        }
        ViewGroup viewGroup = this.contextFooter;
        if (viewGroup != null) {
            viewGroup.bringToFront();
        }
    }

    @SuppressLint({"WrongConstant"})
    public void onBackPressed() {
        FontFragment fontFragment = this.fontFragment;
        if (fontFragment == null || !fontFragment.isVisible()) {
            if (!this.showText) {
                View view = this.canvasText;
                if (view != null) {
                    this.showText = true;
                    this.mainLayout.removeView(view);
                    this.collageView.postInvalidate();
                    this.canvasText = null;
                    Log.e(TAG, "replace fragment");
                    return;
                }
            }
            FullEffectFragment fullEffectFragment = this.fullEffectFragment;
            if (fullEffectFragment != null && fullEffectFragment.isVisible()) {
                return;
            }
            if (this.colorContainer.getVisibility() == 0) {
                hideColorContainer();
                return;
            } else if (this.swapMode) {
                this.selectSwapTextView.setVisibility(4);
                this.swapMode = false;
                return;
            } else {
                CollageView collageView = this.collageView;
                if (collageView != null && collageView.shapeIndex >= 0) {
                    this.collageView.unselectShapes();
                    return;
                } else if (this.selectImageForAdj) {
                    this.selectFilterTextView.setVisibility(4);
                    this.selectImageForAdj = false;
                    return;
                } else {
                    ViewFlipper viewFlipper = this.viewFlipper;
                    if (viewFlipper != null) {
                        if (viewFlipper.getDisplayedChild() != 5) {
                            setSelectedTab(5);
                            return;
                        }
                    }
                    backButtonAlertBuilder();
                    return;
                }
            }
        }
        getSupportFragmentManager().beginTransaction().remove(this.fontFragment).commit();
    }

    private void backButtonAlertBuilder() {
        try {
            View view = View.inflate(this, R.layout.dialog_exit_layout, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
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
            view.findViewById(R.id.exit_app).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                    Apptop_CollageActivity.this.finish();
                    Apptop_CollageActivity.this.alertDialog.dismiss();
                }
            });
            view.findViewById(R.id.cancel_app).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Apptop_CollageActivity.this.alertDialog.dismiss();
                }
            });
            this.alertDialog = alertDialogBuilder.create();
            this.alertDialog.setCancelable(false);
            this.alertDialog.show();
        } catch (Exception e) {
        }
    }

    @SuppressLint({"WrongConstant"})
    public void myClickHandler(View view) {
        Apptop_CollageActivity collageActivity = this;
        int id = view.getId();
        if (id == R.id.button_collage_layout) {
            setSelectedTab(0);
        } else if (id == R.id.button_collage_ratio) {
            setSelectedTab(3);
        } else if (id == R.id.button_collage_blur) {
            collageView.setBlurBitmap(collageView.blurRadius, false);
            setSelectedTab(4);
            collageActivity.collageView.startAnimator();
        } else if (id == R.id.button_collage_background) {
            setSelectedTab(1);
        } else if (id == R.id.button_collage_space) {
            setSelectedTab(2);
        } else if (id == R.id.button_collage_adj) {
            if (collageActivity.collageView.shapeLayoutList.get(0).shapeArr.length == 1) {
                collageView.shapeIndex = 0;
                collageView.openFilterFragment();
            } else if (collageActivity.collageView.shapeIndex >= 0) {
                collageActivity.collageView.openFilterFragment();
                Log.e(TAG, "collageView.shapeIndex>=0 openFilterFragment");
            } else {
                setSelectedTab(5);
                collageActivity.selectFilterTextView.setVisibility(0);
                collageActivity.selectImageForAdj = true;
            }
        } else if (id == R.id.button_collage_context_swap) {
            if (collageActivity.collageView.shapeLayoutList.get(collageActivity.collageView.currentCollageIndex).shapeArr.length == 2) {
                collageActivity.collageView.swapBitmaps(0, 1);
            } else {
                collageActivity.selectSwapTextView.setVisibility(0);
                collageActivity.swapMode = true;
            }
        } else if (id == R.id.button_collage_context_delete) {
            createDeleteDialog();
        } else if (id == R.id.button_collage_context_filter) {
            collageActivity.collageView.openFilterFragment();
        } else if (id == R.id.button_save_collage_image) {
            setSelectedTab(5);
            Log.e("CollegeActivity", "save button");
            new SaveImageTask().execute();
        } else if (id == R.id.button_cancel_collage_image) {
            backButtonAlertBuilder();
        } else if (id == R.id.button11) {
            collageActivity.mulX = 1.0f;
            collageActivity.mulY = 1.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(0);
        } else if (id == R.id.button21) {
            collageActivity.mulX = 2.0f;
            collageActivity.mulY = 1.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(1);
        } else if (id == R.id.button12) {
            collageActivity.mulX = 1.0f;
            collageActivity.mulY = 2.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(2);
        } else if (id == R.id.button32) {
            collageActivity.mulX = 3.0f;
            collageActivity.mulY = 2.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(3);
        } else if (id == R.id.button23) {
            collageActivity.mulX = 2.0f;
            collageActivity.mulY = 3.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(4);
        } else if (id == R.id.button43) {
            collageActivity.mulX = 4.0f;
            collageActivity.mulY = 3.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(5);
        } else if (id == R.id.button34) {
            collageActivity.mulX = 3.0f;
            collageActivity.mulY = 4.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(6);
        } else if (id == R.id.button45) {
            collageActivity.mulX = 4.0f;
            collageActivity.mulY = 5.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(7);
        } else if (id == R.id.button57) {
            collageActivity.mulX = 5.0f;
            collageActivity.mulY = 7.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(8);
        } else if (id == R.id.button169) {
            collageActivity.mulX = 16.0f;
            collageActivity.mulY = 9.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(9);
        } else if (id == R.id.button916) {
            collageActivity.mulX = 9.0f;
            collageActivity.mulY = 16.0f;
            collageActivity.collageView.updateShapeListForRatio(collageActivity.width, collageActivity.height);
            setRatioButtonBg(10);
        } else if (id == R.id.hide_select_image_warning) {
            collageActivity.selectSwapTextView.setVisibility(4);
            collageActivity.swapMode = false;
        } else if (id == R.id.hide_select_image_warning_filter) {
            collageActivity.selectFilterTextView.setVisibility(4);
            collageActivity.selectImageForAdj = false;
        } else if (id == R.id.hide_color_container) {
            hideColorContainer();
        } else if (id == R.id.button_mirror_text) {
            addCanvasTextView();
            clearViewFlipper();
        }
        View view2;
        if (id == R.id.button_collage_context_fit) {
            collageActivity.collageView.setShapeScaleMatrix(0);
            view2 = view;
        } else if (id == R.id.button_collage_context_center) {
            collageActivity.collageView.setShapeScaleMatrix(1);
            view2 = view;
        } else if (id == R.id.button_collage_context_rotate_left) {
            collageActivity.collageView.setShapeScaleMatrix(3);
            view2 = view;
        } else if (id == R.id.button_collage_context_rotate_right) {
            collageActivity.collageView.setShapeScaleMatrix(2);
            view2 = view;
        } else if (id == R.id.button_collage_context_flip_horizontal) {
            collageActivity.collageView.setShapeScaleMatrix(4);
            view2 = view;
        } else if (id == R.id.button_collage_context_flip_vertical) {
            collageActivity.collageView.setShapeScaleMatrix(5);
            view2 = view;
        } else if (id == R.id.button_collage_context_rotate_negative) {
            collageActivity.collageView.setShapeScaleMatrix(6);
            view2 = view;
        } else if (id == R.id.button_collage_context_rotate_positive) {
            collageActivity.collageView.setShapeScaleMatrix(7);
            view2 = view;
        } else if (id == R.id.button_collage_context_zoom_in) {
            toastMatrixMessage(collageActivity.collageView.setShapeScaleMatrix(8));
            view2 = view;
        } else if (id == R.id.button_collage_context_zoom_out) {
            toastMatrixMessage(collageActivity.collageView.setShapeScaleMatrix(9));
            view2 = view;
        } else if (id == R.id.button_collage_context_move_left) {
            toastMatrixMessage(collageActivity.collageView.setShapeScaleMatrix(10));
            view2 = view;
        } else if (id == R.id.button_collage_context_move_right) {
            toastMatrixMessage(collageActivity.collageView.setShapeScaleMatrix(11));
            view2 = view;
        } else if (id == R.id.button_collage_context_move_up) {
            toastMatrixMessage(collageActivity.collageView.setShapeScaleMatrix(12));
            view2 = view;
        } else if (id == R.id.button_collage_context_move_down) {
            toastMatrixMessage(collageActivity.collageView.setShapeScaleMatrix(13));
            view2 = view;
        } else {
            FullEffectFragment fullEffectFragment = collageActivity.fullEffectFragment;
            if (fullEffectFragment == null || !fullEffectFragment.isVisible()) {
                view2 = view;
            } else {
                collageActivity.fullEffectFragment.myClickHandler(view);
            }
        }
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

    void toastMatrixMessage(int message) {
        String str = null;
        if (message == 1) {
            str = "You reached maximum zoom!";
        } else if (message == 2) {
            str = "You reached minimum zoom!";
        } else if (message == 6) {
            str = "You reached max bottom!";
        } else if (message == 5) {
            str = "You reached max top!";
        } else if (message == 4) {
            str = "You reached max right!";
        } else if (message == 3) {
            str = "You reached max left!";
        }
        if (str != null) {
            Toast msg = Toast.makeText(this, str, Toast.LENGTH_SHORT);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
        }
    }

    void clearViewFlipper() {
        this.viewFlipper.setDisplayedChild(5);
        setTabBg(-1);
    }

    @SuppressLint({"WrongConstant"})
    private void setVisibilityForSingleImage() {
        CollageView collageView;
        findViewById(R.id.seekbar_corner_container).setVisibility(8);
        findViewById(R.id.seekbar_space_container).setVisibility(8);
        findViewById(R.id.button_collage_blur).setVisibility(0);
        findViewById(R.id.button_collage_context_delete).setVisibility(8);
        findViewById(R.id.button_collage_context_swap).setVisibility(8);
        if (!this.isScrapBook) {
            collageView = this.collageView;
            collageView.setCollageSize(collageView.sizeMatrix, 45);
            SeekBar seekBar = this.seekbarSize;
            if (seekBar != null) {
                seekBar.setProgress(45);
            }
        }
        collageView = this.collageView;
        collageView.setBlurBitmap(collageView.blurRadius, false);
        if (!this.isScrapBook) {
            setSelectedTab(2);
        }
    }

    void setSelectedTab(int index) {
        if (this.viewFlipper != null) {
            setTabBg(0);
            int displayedChild = this.viewFlipper.getDisplayedChild();
            if (displayedChild != 1) {
                hideColorContainer();
            }
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
            if (index == 4) {
                setTabBg(4);
                if (displayedChild != 4) {
                    if (displayedChild == 0) {
                        this.viewFlipper.setInAnimation(this.slideRightIn);
                        this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    } else {
                        this.viewFlipper.setInAnimation(this.slideLeftIn);
                        this.viewFlipper.setOutAnimation(this.slideRightOut);
                    }
                    this.viewFlipper.setDisplayedChild(4);
                } else {
                    return;
                }
            }
            if (index == 2) {
                setTabBg(2);
                if (displayedChild != 2) {
                    if (displayedChild != 0) {
                        if (displayedChild != 1) {
                            this.viewFlipper.setInAnimation(this.slideLeftIn);
                            this.viewFlipper.setOutAnimation(this.slideRightOut);
                            this.viewFlipper.setDisplayedChild(2);
                        }
                    }
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(2);
                } else {
                    return;
                }
            }
            if (index == 3) {
                setTabBg(3);
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
                setTabBg(-1);
                if (displayedChild != 5) {
                    this.viewFlipper.setInAnimation(this.slideRightIn);
                    this.viewFlipper.setOutAnimation(this.slideLeftOut);
                    this.viewFlipper.setDisplayedChild(5);
                }
            }
        }
    }

    private void setTabBg(int index) {
        int i = 0;
        if (this.tabButtonList == null) {
            this.tabButtonList = new View[6];
            this.tabButtonList[0] = findViewById(R.id.button_collage_layout);
            this.tabButtonList[2] = findViewById(R.id.button_collage_space);
            this.tabButtonList[4] = findViewById(R.id.button_collage_blur);
            this.tabButtonList[1] = findViewById(R.id.button_collage_background);
            this.tabButtonList[3] = findViewById(R.id.button_collage_ratio);
            this.tabButtonList[5] = findViewById(R.id.button_collage_adj);
        }
        View[] viewArr = this.tabButtonList;
        int length = viewArr.length;
        while (i < length) {
            viewArr[i].setBackgroundResource(R.drawable.collage_footer_button);
            i++;
        }
        if (index >= 0) {
            this.tabButtonList[index].setBackgroundResource(R.color.footer_button_color_pressed);
        }
    }

    void setVisibilityOfFilterHorizontalListview(boolean show) {
        if (show && this.fullEffectFragment.isHidden()) {
            getSupportFragmentManager().beginTransaction().show(this.fullEffectFragment).commit();
        }
        if (!show && this.fullEffectFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(this.fullEffectFragment).commit();
        }
        findViewById(R.id.collage_effect_fragment_container).bringToFront();
    }

    private void hideColorContainer() {
        if (this.colorContainer == null) {
            this.colorContainer = findViewById(R.id.color_container);
        }
        this.colorContainer.setVisibility(View.INVISIBLE);
    }

    void createDeleteDialog() {
        if (this.collageView.shapeLayoutList.get(0).shapeArr.length == 1) {
            Toast msg = Toast.makeText(this, "You can't delete last image!", Toast.LENGTH_SHORT);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
            return;
        }
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        CharSequence charSequence = "No";
        builder.setMessage("Do you want to delete it?").setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Apptop_CollageActivity.this.collageView.deleteBitmap(Apptop_CollageActivity.this.collageView.shapeIndex, Apptop_CollageActivity.this.width, Apptop_CollageActivity.this.height);
            }
        }).setNegativeButton(charSequence, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        this.saveImageAlert = builder.create();
        this.saveImageAlert.show();
    }

}
