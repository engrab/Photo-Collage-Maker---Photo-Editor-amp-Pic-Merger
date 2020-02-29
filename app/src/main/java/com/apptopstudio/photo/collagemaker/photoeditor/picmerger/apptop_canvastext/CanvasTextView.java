package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext.CustomRelativeLayout.RemoveTextListener;

import static android.view.MotionEvent.INVALID_POINTER_ID;

public class CanvasTextView extends View {
    static final float MIN_ZOOM = 0.8f;
    private static final String TAG = "CanvasTextView";
    float actualRadius = this.padding;
    ApplyTextInterface applyListener;
    Paint bitmapPaint = new Paint(1);
    float bitmapWidth;
    PointF center = new PointF();
    float circlePadding = 5.0f;
    DialogInterface dialogInterface;
    GestureDetector gestureDetector;
    Matrix identityMatrix = new Matrix();
    Matrix inverse = new Matrix();
    boolean isInCircle = false;
    boolean isOnRect = false;
    boolean isOnTouch = false;
    float padding = 30.0f;
    float paddingWidth = 10.0f;
    PointF previosPos = new PointF();
    float[] pts = new float[2];
    float radius = 40.0f;
    private RectF rect;
    Paint rectPaint;
    Paint rectPaintOnTouch;
    Paint redPaint = new Paint(1);
    Bitmap removeBitmap;
    Matrix removeBitmapMatrix = new Matrix();
    CustomRelativeLayout.RemoveTextListener removeTextListener;
    float scale = 1.0f;
    Bitmap scaleBitmap;
    Matrix scaleBitmapMatrix = new Matrix();
    SingleTap singleTapListener;
    PointF start = new PointF();
    private double startAngle = 0.0d;
    Matrix startMatrix = new Matrix();
    Rect textBoundrect;
    TextData textData;
    private boolean textSelected = false;
    float[] values = new float[9];
    ViewSelectedListener viewSelectedListener;
    Paint whitePaint = new Paint(1);
    PointF zoomStart = new PointF();



    private class GestureListener extends SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onDown(MotionEvent e) {
            if (!CanvasTextView.this.isInCircle) {
                if (!CanvasTextView.this.isOnRect) {
                    CanvasTextView.this.textSelected = false;
                    return false;
                }
            }
            return true;
        }

        public boolean onSingleTapUp(MotionEvent event) {
            Log.d("Single Tap", "Tapped at");
            CanvasTextView.this.pts[0] = event.getX();
            CanvasTextView.this.pts[1] = event.getY();
            CanvasTextView.this.textData.canvasMatrix.invert(CanvasTextView.this.inverse);
            CanvasTextView.this.inverse.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
            CanvasTextView canvasTextView = CanvasTextView.this;
            canvasTextView.isOnRect = canvasTextView.isOnRect(canvasTextView.pts[0], CanvasTextView.this.pts[1]);
            if (CanvasTextView.this.isOnRect) {
                Log.d("textSelected", "single Tapped at");
                CanvasTextView.this.textSelected = true;
                CanvasTextView.this.singleTapped();
            } else {
                CanvasTextView.this.textSelected = false;
            }
            if (!CanvasTextView.this.isInCircle) {
                if (!CanvasTextView.this.isOnRect) {
                    return false;
                }
            }
            return true;
        }

        public boolean onDoubleTap(MotionEvent e) {
            CanvasTextView.this.pts[0] = e.getX();
            CanvasTextView.this.pts[1] = e.getY();
            CanvasTextView.this.textData.canvasMatrix.invert(CanvasTextView.this.inverse);
            CanvasTextView.this.inverse.mapPoints(CanvasTextView.this.pts, CanvasTextView.this.pts);
            CanvasTextView canvasTextView = CanvasTextView.this;
            canvasTextView.isOnRect = canvasTextView.isOnRect(canvasTextView.pts[0], CanvasTextView.this.pts[1]);
            if (CanvasTextView.this.isOnRect) {
                Log.d("textSelected", "double Tapped at");
                CanvasTextView.this.textSelected = true;
                CanvasTextView.this.singleTapped();
            } else {
                CanvasTextView.this.textSelected = false;
            }
            Log.d("Double Tap", "Tapped at");
            return true;
        }
    }

    public void setTextSelected(boolean selection) {
        this.textSelected = selection;
        postInvalidate();
    }

    public boolean getTextSelected() {
        return this.textSelected;
    }

    public void setApplyInterface(ApplyTextInterface l) {
        this.applyListener = l;
    }

    public void setRemoveTextListener(RemoveTextListener listener) {
        this.removeTextListener = listener;
    }

    public CanvasTextView(Context context, TextData td, Bitmap removeBtm, Bitmap scaleBtm) {
        super(context);
        Context context2 = context;
        TextData textData = td;
        float textSize = context.getResources().getDimension(R.dimen.myFontSize);
        float screenWidth = (float) getResources().getDisplayMetrics().widthPixels;
        float screenHeight = (float) getResources().getDisplayMetrics().heightPixels;
        this.rectPaint = new Paint(1);
        this.rectPaint.setColor(2011028957);
        this.redPaint.setColor(getResources().getColor(R.color.red));
        this.whitePaint.setColor(getResources().getColor(R.color.text_white));
        this.bitmapPaint.setFilterBitmap(true);
        this.rectPaintOnTouch = new Paint(1);
        this.rectPaintOnTouch.setColor(2011028957);
        this.textBoundrect = new Rect();
        if (textData == null) {
            this.textData = new TextData(textSize);
            MyPaint myPaint = this.textData.textPaint;
            String str = TextData.defaultMessage;
            myPaint.getTextBounds(str, 0, str.length(), this.textBoundrect);
            this.textData.xPos = (screenWidth / 2.0f) - ((float) (this.textBoundrect.width() / 2));
            this.textData.yPos = screenHeight / 3.0f;
        } else {
            this.textData = textData;
            if (this.textData.getFontPath() != null) {
                Typeface typeFace = FontCache.get(context2, this.textData.getFontPath());
                if (typeFace != null) {
                    this.textData.textPaint.setTypeface(typeFace);
                }
            }
            this.textData.textPaint.getTextBounds(this.textData.message, 0, this.textData.message.length(), this.textBoundrect);
        }
        this.rect = new RectF(this.textData.xPos - this.paddingWidth, (this.textData.yPos - ((float) this.textBoundrect.height())) - this.padding, (this.textData.xPos + ((float) this.textBoundrect.width())) + (this.paddingWidth * 2.0f), this.textData.yPos + this.padding);
        this.gestureDetector = new GestureDetector(context2, new GestureListener());
        this.actualRadius = screenWidth / 20.0f;
        float f = this.actualRadius;
        this.circlePadding = f / 2.0f;
        if (f <= 5.0f) {
            this.actualRadius = this.padding;
        }
        this.removeBitmap = removeBtm;
        this.scaleBitmap = scaleBtm;
        this.bitmapWidth = (float) this.removeBitmap.getWidth();
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        float bitmapScale = (this.actualRadius * 2.0f) / this.bitmapWidth;
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.removeBitmapMatrix.postTranslate(this.rect.left - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rect.top - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        this.scaleBitmapMatrix.postTranslate(this.rect.right - ((this.bitmapWidth * bitmapScale) / 2.0f), this.rect.bottom - ((this.bitmapWidth * bitmapScale) / 2.0f));
        this.scale = getScale();
        Matrix matrix = this.scaleBitmapMatrix;
        float f2 = this.scale;
        matrix.postScale(1.0f / f2, 1.0f / f2, this.rect.right, this.rect.bottom);
        matrix = this.removeBitmapMatrix;
        f2 = this.scale;
        matrix.postScale(1.0f / f2, 1.0f / f2, this.rect.left, this.rect.top);
    }

    public void setMatrix(MyMatrix matrix) {
        this.textData.canvasMatrix = matrix;
        this.scale = getScale();
    }

    public void onDraw(Canvas canvas) {
        canvas.setMatrix(this.textData.canvasMatrix);
        if (this.textSelected) {
            if (this.isOnTouch) {
                canvas.drawRect(this.rect, this.rectPaintOnTouch);
            } else {
                canvas.drawRect(this.rect, this.rectPaint);
            }
            float rad = this.actualRadius / this.scale;
            canvas.drawCircle(this.rect.right, this.rect.bottom, rad, this.whitePaint);
            canvas.drawCircle(this.rect.left, this.rect.top, rad, this.redPaint);
            canvas.drawBitmap(this.scaleBitmap, this.scaleBitmapMatrix, this.bitmapPaint);
            canvas.drawBitmap(this.removeBitmap, this.removeBitmapMatrix, this.bitmapPaint);
        }
        Log.e("message", this.textData.message);
        StringBuilder stringBuilder = new StringBuilder();
        String str = "";
        stringBuilder.append(str);
        stringBuilder.append(this.textData.xPos);
        Log.e("X", stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(this.textData.yPos);
        Log.e("Y", stringBuilder.toString());
        canvas.drawText(this.textData.message, this.textData.xPos, this.textData.yPos, this.textData.textPaint);
    }

    void checkMatrix() {
        this.textData.canvasMatrix.getValues(this.values);
        if (getScale() < 0.5f) {
            MyMatrix myMatrix = this.textData.canvasMatrix;
            float[] fArr = this.pts;
            myMatrix.postScale(0.5f, 0.5f, fArr[0], fArr[1]);
        }
    }

    void singleTapped() {
        this.singleTapListener.onSingleTap(this.textData);
    }

    void setTextColor(int color) {
        this.textData.textPaint.setColor(color);
        postInvalidate();
    }

    public void setMessage(CharSequence newMessage) {
        if (newMessage.length() == 0) {
            this.textData.message = TextData.defaultMessage;
        } else {
            this.textData.message = newMessage.toString();
        }
        float exRight = this.rect.right;
        RectF rectF = this.rect;
        rectF.right = (rectF.left + this.textData.textPaint.measureText(this.textData.message)) + (this.paddingWidth * 2.0f);
        this.scaleBitmapMatrix.postTranslate(this.rect.right - exRight, 0.0f);
        postInvalidate();
    }

    public void setNewTextData(TextData textData) {
        this.textData = textData;
        float exRight = this.rect.right;
        RectF rectF = this.rect;
        rectF.right = (rectF.left + textData.textPaint.measureText(textData.message)) + (this.paddingWidth * 2.0f);
        this.scaleBitmapMatrix.postTranslate(this.rect.right - exRight, 0.0f);
        postInvalidate();
    }

    boolean isOnRect(float x, float y) {
        if (x > this.rect.left && x < this.rect.right && y > this.rect.top) {
            if (y < this.rect.bottom) {
                this.textSelected = true;
                return true;
            }
        }
        return false;
    }

    boolean isInCircle(float x, float y) {
        float f = ((x - this.rect.right) * (x - this.rect.right)) + ((y - this.rect.bottom) * (y - this.rect.bottom));
        float f2 = this.radius;
        f2 *= f2;
        float f3 = this.scale;
        if (f >= f2 / (f3 * f3)) {
            return false;
        }
        this.textSelected = true;
        return true;
    }

    boolean isOnCross(float x, float y) {
        float f = ((x - this.rect.left) * (x - this.rect.left)) + ((y - this.rect.top) * (y - this.rect.top));
        float f2 = this.actualRadius;
        float f3 = this.circlePadding;
        float f4 = (f2 + f3) * (f2 + f3);
        f2 = this.scale;
        if (f >= f4 / (f2 * f2)) {
            return false;
        }
        Log.e(TAG, "isOncross");
        this.textSelected = true;
        return true;
    }

    public boolean onTouchEvent(MotionEvent ev){
        return super.onTouchEvent(ev);
    }


//    public boolean onTouchEvent(android.view.MotionEvent r11) {
//
////        float r10 = r11.getX();
////        float r1 = r11.getY();
////        float r2 = r11.getAction();
////        float r3 = 0;
////        float r4 = 1;
////        if (r2 == 0) break L_0x0108;
////    L_0x0010:
////        if (r2 == r4) goto L_0x0102;
////    L_0x0012:
////        float r5 = 2;
////        if (r2 == r5) goto L_0x0017;
////    L_0x0015:
////        goto L_0x01b4;
////    L_0x0017:
////        r2 = r10.isInCircle;
////        if (r2 != 0) goto L_0x003d;
////    L_0x001b:
////        r2 = r10.isOnRect;
////        if (r2 == 0) goto L_0x003d;
////    L_0x001f:
////        r2 = r10.textData;
////        r2 = r2.canvasMatrix;
////        r3 = r10.startMatrix;
////        r2.set(r3);
////        r2 = r10.textData;
////        r2 = r2.canvasMatrix;
////        r3 = r10.previosPos;
////        r3 = r3.x;
////        r3 = this - r3;
////        r4 = r10.previosPos;
////        r4 = r4.y;
////        r4 = r1 - r4;
////        r2.postTranslate(r3, r4);
////        goto L_0x01b4;
////    L_0x003d:
////        r2 = r10.pts;
////        r5 = r2[r3];
////        r2 = r2[r4];
////        r2 = r10.pointToAngle(this, r1, r5, r2);
////        r2 = -r2;
////        r2 = (float) r2;
////        r5 = r10.textData;
////        r5 = r5.canvasMatrix;
////        r6 = r10.startAngle;
////        r8 = (double) r2;
////        java.lang.Double.isNaN(r8);
////        r6 = r6 - r8;
////        r6 = (float) r6;
////        r7 = r10.pts;
////        r8 = r7[r3];
////        r7 = r7[r4];
////        r5.postRotate(r6, r8, r7);
////        r5 = (double) r2;
////        r10.startAngle = r5;
////        r5 = r10.pts;
////        r6 = r5[r3];
////        r6 = this - r6;
////        r7 = r5[r3];
////        r7 = this - r7;
////        r6 = r6 * r7;
////        r7 = r5[r4];
////        r7 = r1 - r7;
////        r5 = r5[r4];
////        r5 = r1 - r5;
////        r7 = r7 * r5;
////        r6 = r6 + r7;
////        r5 = (double) r6;
////        r5 = java.lang.Math.sqrt(r5);
////        r5 = (float) r5;
////        r6 = r10.zoomStart;
////        r6 = r6.x;
////        r7 = r10.pts;
////        r7 = r7[r3];
////        r6 = r6 - r7;
////        r7 = r10.zoomStart;
////        r7 = r7.x;
////        r8 = r10.pts;
////        r8 = r8[r3];
////        r7 = r7 - r8;
////        r6 = r6 * r7;
////        r7 = r10.zoomStart;
////        r7 = r7.y;
////        r8 = r10.pts;
////        r8 = r8[r4];
////        r7 = r7 - r8;
////        r8 = r10.zoomStart;
////        r8 = r8.y;
////        r9 = r10.pts;
////        r9 = r9[r4];
////        r8 = r8 - r9;
////        r7 = r7 * r8;
////        r6 = r6 + r7;
////        r6 = (double) r6;
////        r6 = java.lang.Math.sqrt(r6);
////        r6 = (float) r6;
////        r5 = r5 / r6;
////        r6 = r10.getScale();
////        r10.scale = r6;
////        r6 = r10.scale;
////        r7 = 1061997773; // 0x3f4ccccd float:0.8 double:5.246966156E-315;
////        r8 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
////        r9 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1));
////        if (r9 >= 0) goto L_0x00c7;
////    L_0x00bf:
////        r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1));
////        if (r6 >= 0) goto L_0x01b4;
////    L_0x00c3:
////        r6 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1));
////        if (r6 <= 0) goto L_0x01b4;
////    L_0x00c7:
////        r6 = r10.textData;
////        r6 = r6.canvasMatrix;
////        r7 = r10.pts;
////        r3 = r7[r3];
////        r4 = r7[r4];
////        r6.postScale(r5, r5, r3, r4);
////        r3 = r10.zoomStart;
////        r3.set(this, r1);
////        r3 = r10.getScale();
////        r10.scale = r3;
////        r3 = r10.scaleBitmapMatrix;
////        r4 = r8 / r5;
////        r6 = r8 / r5;
////        r7 = r10.rect;
////        r7 = r7.right;
////        r9 = r10.rect;
////        r9 = r9.bottom;
////        r3.postScale(r4, r6, r7, r9);
////        r3 = r10.removeBitmapMatrix;
////        r4 = r8 / r5;
////        r8 = r8 / r5;
////        r6 = r10.rect;
////        r6 = r6.left;
////        r7 = r10.rect;
////        r7 = r7.top;
////        r3.postScale(r4, r8, r6, r7);
////        goto L_0x01b4;
////    L_0x0102:
////        r10.isOnTouch = r3;
////        r10.isOnRect = r3;
////        goto L_0x01b4;
////    L_0x0108:
////        r10.isOnTouch = r4;
////        r2 = r10.pts;
////        r2[r3] = this;
////        r2[r4] = r1;
////        r2 = r10.textData;
////        r2 = r2.canvasMatrix;
////        r5 = r10.inverse;
////        r2.invert(r5);
////        r2 = r10.inverse;
////        r5 = r10.pts;
////        r2.mapPoints(r5, r5);
////        r2 = r10.textSelected;
////        if (r2 == 0) goto L_0x0130;
////    L_0x0124:
////        r2 = r10.pts;
////        r5 = r2[r3];
////        r2 = r2[r4];
////        r2 = r10.isOnCross(r5, r2);
////        if (r2 != 0) goto L_0x0194;
////    L_0x0130:
////        r2 = r10.pts;
////        r5 = r2[r3];
////        r2 = r2[r4];
////        r2 = r10.isOnRect(r5, r2);
////        r10.isOnRect = r2;
////        r2 = r10.pts;
////        r5 = r2[r3];
////        r2 = r2[r4];
////        r2 = r10.isInCircle(r5, r2);
////        r10.isInCircle = r2;
////        r2 = r10.start;
////        r2.set(this, r1);
////        r2 = r10.previosPos;
////        r2.set(this, r1);
////        r2 = r10.zoomStart;
////        r2.set(this, r1);
////        r2 = r10.pts;
////        r5 = r10.rect;
////        r5 = r5.centerX();
////        r2[r3] = r5;
////        r2 = r10.pts;
////        r5 = r10.rect;
////        r5 = r5.centerY();
////        r2[r4] = r5;
////        r2 = r10.textData;
////        r2 = r2.canvasMatrix;
////        r5 = r10.pts;
////        r2.mapPoints(r5, r5);
////        r2 = r10.pts;
////        r5 = r2[r3];
////        r2 = r2[r4];
////        r2 = r10.pointToAngle(this, r1, r5, r2);
////        r2 = -r2;
////        r5 = (double) r2;
////        r10.startAngle = r5;
////        r2 = r10.startMatrix;
////        r5 = r10.textData;
////        r5 = r5.canvasMatrix;
////        r2.set(r5);
////        r2 = r10.isInCircle;
////        if (r2 != 0) goto L_0x01ae;
////    L_0x018f:
////        r2 = r10.isOnRect;
////        if (r2 == 0) goto L_0x0194;
////    L_0x0193:
////        goto L_0x01ae;
////    L_0x0194:
////        r2 = r10.textSelected;
////        if (r2 == 0) goto L_0x01ad;
////    L_0x0198:
////        r2 = r10.pts;
////        r5 = r2[r3];
////        r2 = r2[r4];
////        r2 = r10.isOnCross(r5, r2);
////        if (r2 != 0) goto L_0x01a5;
////    L_0x01a4:
////        goto L_0x01ad;
////    L_0x01a5:
////        r2 = r10.getContext();
////        r10.createDeleteDialog(r2, r10);
////        return r4;
////    L_0x01ad:
////        return r3;
////    L_0x01ae:
////        r2 = r10.viewSelectedListener;
////        r2.setSelectedView(r10);
////    L_0x01b4:
////        r10.postInvalidate();
////        r2 = r10.gestureDetector;
////        r2 = r2.onTouchEvent(r11);
////        return r2;
//
//
//        return super.onTouchEvent(r11);
//    }

//    public void createDeleteDialog(Context paramContext, final View paramView) {
//        AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
//        CharSequence charSequence = "No";
//        localBuilder.setMessage((int) R.string.collage_lib_delete_message).setCancelable(true).setPositiveButton((CharSequence) "Yes", new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            });
//
//            public void onClick(DialogInterface dialogInterface, int which) {
//                CanvasTextView.this.deleteView(paramView);
//            }
//        }).setNegativeButton(charSequence, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        localBuilder.create().show();
//    }

    public void deleteView(View paramView) {
        ViewGroup localViewGroup = (ViewGroup) paramView.getParent();
        if (localViewGroup != null) {
            localViewGroup.removeView(paramView);
            this.removeTextListener.onRemove();
        }
    }

    float getScale() {
        this.textData.canvasMatrix.getValues(this.values);
        float[] skewy = values;
        float scalex = skewy[0];
        float scaley1 = skewy[0];
        return (float) Math.sqrt((double) ((scalex * scalex) + (scaley1 * scaley1)));
    }

    public void setSingleTapListener(SingleTap l) {
        this.singleTapListener = l;
    }

    public void setViewSelectedListener(ViewSelectedListener l) {
        this.viewSelectedListener = l;
    }

    private int pointToAngle(float x, float y, float centerX, float centerY) {
        double d;
        double d2;
        if (x >= centerX && y < centerY) {
            d = (double) (x - centerX);
            d2 = (double) (centerY - y);
            Double.isNaN(d);
            Double.isNaN(d2);
            return ((int) Math.toDegrees(Math.atan(d / d2))) + 270;
        } else if (x > centerX && y >= centerY) {
            d = (double) (y - centerY);
            d2 = (double) (x - centerX);
            Double.isNaN(d);
            Double.isNaN(d2);
            return (int) Math.toDegrees(Math.atan(d / d2));
        } else if (x <= centerX && y > centerY) {
            d = (double) (centerX - x);
            d2 = (double) (y - centerY);
            Double.isNaN(d);
            Double.isNaN(d2);
            return ((int) Math.toDegrees(Math.atan(d / d2))) + 90;
        } else if (x >= centerX || y > centerY) {
            throw new IllegalArgumentException();
        } else {
            d = (double) (centerY - y);
            d2 = (double) (centerX - x);
            Double.isNaN(d);
            Double.isNaN(d2);
            return ((int) Math.toDegrees(Math.atan(d / d2))) + 180;
        }
    }
}
