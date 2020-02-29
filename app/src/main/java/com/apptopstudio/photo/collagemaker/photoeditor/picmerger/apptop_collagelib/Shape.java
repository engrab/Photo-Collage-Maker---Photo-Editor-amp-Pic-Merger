package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Xfermode;
import android.graphics.drawable.NinePatchDrawable;
import android.util.Log;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist.Collage;

public class Shape {
    public static final int MATRIX_MODE_CENTER = 1;
    public static final int MATRIX_MODE_FIT = 0;
    public static final int MATRIX_MODE_FLIP_HORIZONTAL = 4;
    public static final int MATRIX_MODE_FLIP_VERTICAL = 5;
    public static final int MATRIX_MODE_MOVE_DOWN = 13;
    public static final int MATRIX_MODE_MOVE_LEFT = 10;
    public static final int MATRIX_MODE_MOVE_RIGHT = 11;
    public static final int MATRIX_MODE_MOVE_UP = 12;
    public static final int MATRIX_MODE_ROTATE_LEFT = 3;
    public static final int MATRIX_MODE_ROTATE_NEGATIVE = 6;
    public static final int MATRIX_MODE_ROTATE_POSITIVE = 7;
    public static final int MATRIX_MODE_ROTATE_RIGHT = 2;
    public static final int MATRIX_MODE_ZOOM_IN = 8;
    public static final int MATRIX_MODE_ZOOM_OUT = 9;
    public static final int MESSAGE_DEFAULT = 0;
    public static final int MESSAGE_MAX_BOTTOM = 6;
    public static final int MESSAGE_MAX_LEFT = 3;
    public static final int MESSAGE_MAX_RIGHT = 4;
    public static final int MESSAGE_MAX_TOP = 5;
    public static final int MESSAGE_MAX_ZOOM = 1;
    public static final int MESSAGE_MIN_ZOOM = 2;
    private static final String TAG = "Shape";
    static final int[] scrapBookRotation = new int[]{13, -13, -7, -12, 11, 8, -9, 10, 9};
    public final int SHAPE_MODE_MASK;
    public final int SHAPE_MODE_POINT;
    public final int SHAPE_MODE_RECT;
    private Bitmap bitmap;
    int bitmapHeight;
    Matrix bitmapMatrix, r1;
    float r2;
    RectF bitmapRect;
    int bitmapWidth;
    Paint borderPaint;
    int borderStrokeWidth;
    RectF bounds;
    Bitmap btmDelete;
    Bitmap btmScale;
    PointF centerOriginal;
    Paint dashPaint;
    Path dashPathHorizontal;
    Path dashPathVertical;
    int delW;
    float deleteWidthHalf;
    float dx;
    float dy;
    int[] exceptionIndex;
    float[] f506f;
    float[] f507p;
    RectF f508r;
    Paint iconMaskPaint;
    Paint iconPaint;
    Xfermode iconXferMode;
    Matrix inverse;
    boolean isScrapBook;
    private Bitmap maskBitmap;
    private Matrix maskMatrix;
    Paint maskPaint;
    float maxScale;
    float minScale;
    NinePatchDrawable npd;
    int npdPadding;
    int offsetX;
    int offsetY;
    RectF originalBounds;
    Path originalPath;
    private Paint paintPath;
    Paint paintScrap;
    private Paint paintTransparent;
    Paint paintXferMode;
    Path path;
    Path[] pathList;
    int pathListLength;
    Matrix pathMatrix;
    PointF[] points;
    float[] pts;
    private String r0;
    Region region;
    Matrix removeBitmapMatrix;
    Matrix scaleBitmapMatrix;
    float scaleDown;
    float scaleUp;
    float scrapBookPadding;
    int screenWidth;
    int shapeMode;
    RectF sourceRect;
    final float tempRadius;
    RectF tempRect;
    final float tempScrapBookPadding;
    float tempTouchStrokeWidth;
    Paint touchPaint;
    RectF touchRect;
    float touchStrokeWidth;
    Matrix transparentMaskMatrix;
    float[] values;

    public Shape(PointF[] points, Bitmap b, int[] exceptionIndex, int offsetX, int offsetY, boolean isScrapBook, int index, boolean isDelete, Bitmap del, Bitmap scl, int screenWidth) {
        int i = offsetX;
        int i2 = offsetY;
        this.offsetY = 0;
        this.offsetX = 0;
        this.SHAPE_MODE_POINT = 1;
        this.SHAPE_MODE_RECT = 2;
        this.SHAPE_MODE_MASK = 3;
        this.maskBitmap = null;
        this.maskMatrix = new Matrix();
        this.transparentMaskMatrix = new Matrix();
        this.tempRect = new RectF();
        this.f508r = new RectF();
        this.minScale = 1.0f;
        this.maxScale = 1.0f;
        this.bitmapRect = new RectF();
        this.f507p = new float[2];
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.scaleDown = 0.95f;
        this.scaleUp = 1.05f;
        this.f506f = new float[2];
        this.centerOriginal = new PointF();
        this.touchPaint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.paintScrap = new Paint(2);
        this.pts = new float[2];
        this.inverse = new Matrix();
        this.tempScrapBookPadding = 25.0f;
        this.scrapBookPadding = 25.0f;
        this.tempTouchStrokeWidth = 8.0f;
        this.touchStrokeWidth = this.tempTouchStrokeWidth;
        this.values = new float[9];
        this.tempRadius = 60.0f;
        this.borderStrokeWidth = 6;
        this.dashPaint = new Paint();
        this.delW = 0;
        this.deleteWidthHalf = 0.0f;
        this.npdPadding = 16;
        this.points = points;
        this.offsetX = i;
        this.offsetY = i2;
        this.btmDelete = del;
        this.btmScale = scl;
        this.screenWidth = screenWidth;
        this.isScrapBook = isScrapBook;
        createPathFromPoints();
        this.path.offset((float) i, (float) i2);
        this.exceptionIndex = exceptionIndex;
        this.bitmap = b;
        this.bitmapWidth = this.bitmap.getWidth();
        this.bitmapHeight = this.bitmap.getHeight();
        this.shapeMode = 1;
        init(isScrapBook, index, false, 0, 0);
    }

    public Shape(PointF[] points, Bitmap b, int[] exceptionIndex, int offsetX, int offsetY, Bitmap mask, boolean isScrapBook, int index, boolean isDelete, Bitmap del, Bitmap scl, int screenWidth) {
        int i = offsetX;
        int i2 = offsetY;
        this.offsetY = 0;
        this.offsetX = 0;
        this.SHAPE_MODE_POINT = 1;
        this.SHAPE_MODE_RECT = 2;
        this.SHAPE_MODE_MASK = 3;
        this.maskBitmap = null;
        this.maskMatrix = new Matrix();
        this.transparentMaskMatrix = new Matrix();
        this.tempRect = new RectF();
        this.f508r = new RectF();
        this.minScale = 1.0f;
        this.maxScale = 1.0f;
        this.bitmapRect = new RectF();
        this.f507p = new float[2];
        this.dx = 0.0f;
        this.dy = 0.0f;
        this.scaleDown = 0.95f;
        this.scaleUp = 1.05f;
        this.f506f = new float[2];
        this.centerOriginal = new PointF();
        this.touchPaint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.paintScrap = new Paint(2);
        this.pts = new float[2];
        this.inverse = new Matrix();
        this.tempScrapBookPadding = 25.0f;
        this.scrapBookPadding = 25.0f;
        this.tempTouchStrokeWidth = 8.0f;
        this.touchStrokeWidth = this.tempTouchStrokeWidth;
        this.values = new float[9];
        this.tempRadius = 60.0f;
        this.borderStrokeWidth = 6;
        this.dashPaint = new Paint();
        this.delW = 0;
        this.deleteWidthHalf = 0.0f;
        this.npdPadding = 16;
        this.maskBitmap = mask;
        this.points = points;
        this.offsetX = i;
        this.offsetY = i2;
        this.btmDelete = del;
        this.btmScale = scl;
        this.screenWidth = screenWidth;
        this.isScrapBook = isScrapBook;
        createPathFromPoints();
        this.path.offset((float) i, (float) i2);
        this.exceptionIndex = exceptionIndex;
        this.bitmap = b;
        this.bitmapWidth = this.bitmap.getWidth();
        this.bitmapHeight = this.bitmap.getHeight();
        this.shapeMode = 3;
        init(isScrapBook, index, false, 0, 0);
    }

    public void changeRatio(PointF[] points, int[] exceptionIndex, int offsetX, int offsetY, boolean isScrapBook, int index, int w, int h) {
        int i = offsetX;
        int i2 = offsetY;
        this.points = points;
        this.offsetX = i;
        this.offsetY = i2;
        createPathFromPoints();
        this.path.offset((float) i, (float) i2);
        this.exceptionIndex = exceptionIndex;
        init(isScrapBook, index, true, w, h);
    }

    public void freeBitmaps() {
        Bitmap bitmap = this.bitmap;
        if (!(bitmap == null || bitmap.isRecycled())) {
            this.bitmap.recycle();
        }
        bitmap = this.maskBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.maskBitmap = null;
        }
    }

    public void setRadius(CornerPathEffect corEffect) {
        this.paintPath.setPathEffect(corEffect);
        this.paintTransparent.setPathEffect(corEffect);
    }

    public float smallestDistance() {
        float smallestDistance = 1500.0f;
        for (int i = 0; i < this.points.length; i++) {
            int j = 0;
            while (true) {
                PointF[] pointFArr = this.points;
                if (j >= pointFArr.length) {
                    break;
                }
                if (i != j) {
                    float distance = Math.abs(pointFArr[i].x - this.points[j].x) + Math.abs(this.points[i].y - this.points[j].y);
                    if (distance < smallestDistance) {
                        smallestDistance = distance;
                    }
                }
                j++;
            }
        }
        return smallestDistance;
    }

    public void init(boolean isScrapBook, int index, boolean isChangeRatio, int w, int h) {
        this.bounds = new RectF();
        this.originalPath = new Path(this.path);
        this.path.computeBounds(this.bounds, true);
        this.originalBounds = new RectF(this.bounds);
        this.paintXferMode = new Paint(1);
        this.paintXferMode.setFilterBitmap(true);
        this.paintXferMode.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        this.paintPath = new Paint(1);
        this.paintPath.setFilterBitmap(true);
        this.maskPaint = new Paint(1);
        this.maskPaint.setFilterBitmap(true);
        this.paintTransparent = new Paint(1);
        this.paintTransparent.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.paintTransparent.setFilterBitmap(true);
        if (isScrapBook) {
            setScrapBookBitmapPosition(index, isChangeRatio, w, h);
        } else {
            setBitmapPosition();
        }
        this.paintPath.setPathEffect(new CornerPathEffect(3.0f));
        this.pathMatrix = new Matrix();
        this.region = new Region();
        this.region.setPath(this.path, new Region((int) this.bounds.left, (int) this.bounds.top, (int) this.bounds.right, (int) this.bounds.bottom));
        if (isScrapBook) {
            this.dashPaint.setColor(7829368);
            this.dashPaint.setStyle(Style.STROKE);
            float strokeW = ((float) this.screenWidth) / 120.0f;
            if (strokeW <= 0.0f) {
                strokeW = 5.0f;
            }
            this.dashPaint.setStrokeWidth(strokeW);
            this.dashPaint.setPathEffect(new DashPathEffect(new float[]{strokeW, strokeW}, 0.0f));
            this.dashPathVertical = new Path();
            this.dashPathVertical.moveTo((float) (this.bitmapWidth / 2), (float) ((-this.bitmapHeight) / 5));
            this.dashPathVertical.lineTo((float) (this.bitmapWidth / 2), (float) ((this.bitmapHeight * 6) / 5));
            this.dashPathHorizontal = new Path();
            this.dashPathHorizontal.moveTo((float) ((-this.bitmapWidth) / 5), (float) (this.bitmapHeight / 2));
            this.dashPathHorizontal.lineTo((float) ((this.bitmapWidth * 6) / 5), (float) (this.bitmapHeight / 2));
        }
    }

    public void setBitmap(Bitmap bitmap, boolean isFilter) {
        this.bitmap = bitmap;
        this.bitmapWidth = bitmap.getWidth();
        this.bitmapHeight = bitmap.getHeight();
        if (!isFilter) {
            setBitmapPosition();
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Bitmap getMaskBitmap() {
        return this.maskBitmap;
    }

    private void setBitmapPosition() {
        float scaleBitmap = getBitmapScale();
        float bitmapY = this.bounds.top - (((((float) this.bitmapHeight) * scaleBitmap) - this.bounds.height()) / 2.0f);
        float bitmapX = this.bounds.left - (((((float) this.bitmapWidth) * scaleBitmap) - this.bounds.width()) / 2.0f);
        this.bitmapMatrix = new Matrix();
        this.bitmapMatrix.reset();
        this.bitmapMatrix.postScale(scaleBitmap, scaleBitmap);
        this.bitmapMatrix.postTranslate(bitmapX, bitmapY);
        if (this.shapeMode == 3) {
            setMaskBitmapPositions();
        }
        setMaxMinScales(scaleBitmap);
    }

    private float getBitmapScale() {
        float scaleBitmapX = this.bounds.width() / ((float) this.bitmapWidth);
        float scaleBitmapY = this.bounds.height() / ((float) this.bitmapHeight);
        return scaleBitmapX < scaleBitmapY ? scaleBitmapY : scaleBitmapX;
    }

    void setMaxMinScales(float scaleBitmap) {
        if (this.isScrapBook) {
            this.minScale = scaleBitmap / 2.0f;
        } else {
            this.minScale = scaleBitmap;
        }
        if (this.isScrapBook) {
            this.maxScale = 2.0f * scaleBitmap;
        } else {
            this.maxScale = 4.0f * scaleBitmap;
        }
    }

    void setMinScales(float scaleBitmap) {
        if (this.isScrapBook) {
            this.minScale = scaleBitmap / 2.0f;
        } else {
            this.minScale = scaleBitmap;
        }
    }

    private void setMaskBitmapPositions() {
        int maskBitmapWidth = this.maskBitmap.getWidth();
        if (maskBitmapWidth != 0) {
            float scaleMaskBitmap;
            float scaleMaskBitmapTr;
            int maskBitmapHeight = this.maskBitmap.getHeight();
            float scaleMaskBitmapX = this.bounds.width() / ((float) maskBitmapWidth);
            float scaleMaskBitmapY = this.bounds.height() / ((float) maskBitmapHeight);
            if (scaleMaskBitmapX > scaleMaskBitmapY) {
                scaleMaskBitmap = scaleMaskBitmapY;
            } else {
                scaleMaskBitmap = scaleMaskBitmapX;
            }
            float maskBitmapY = this.bounds.top - (((((float) maskBitmapHeight) * scaleMaskBitmap) - this.bounds.height()) / 2.0f);
            float maskBitmapX = this.bounds.left - (((((float) maskBitmapWidth) * scaleMaskBitmap) - this.bounds.width()) / 2.0f);
            this.maskMatrix = new Matrix();
            this.maskMatrix.reset();
            this.maskMatrix.postScale(scaleMaskBitmap, scaleMaskBitmap);
            this.maskMatrix.postTranslate(maskBitmapX, maskBitmapY);
            float scaleMaskBitmapXTr = this.originalBounds.width() / ((float) maskBitmapWidth);
            float scaleMaskBitmapYTr = this.originalBounds.height() / ((float) maskBitmapHeight);
            if (scaleMaskBitmapXTr > scaleMaskBitmapYTr) {
                scaleMaskBitmapTr = scaleMaskBitmapYTr;
            } else {
                scaleMaskBitmapTr = scaleMaskBitmapXTr;
            }
            float maskBitmapYTr = this.originalBounds.top - (((((float) maskBitmapHeight) * scaleMaskBitmapTr) - this.originalBounds.height()) / 2.0f);
            float maskBitmapXTr = this.originalBounds.left - (((((float) maskBitmapWidth) * scaleMaskBitmapTr) - this.originalBounds.width()) / 2.0f);
            this.transparentMaskMatrix = new Matrix();
            this.transparentMaskMatrix.reset();
            this.transparentMaskMatrix.postScale(scaleMaskBitmapTr, scaleMaskBitmapTr);
            this.transparentMaskMatrix.postTranslate(maskBitmapXTr, maskBitmapYTr);
        }
    }

    public void scalePath(float distance, float width, float height) {
        int i = this.shapeMode;
        if (i == 1) {
            pathTransform(this.points, this.path, distance, this.originalBounds.centerX(), this.originalBounds.centerY());
        } else if (i == 2) {
            pathTransformFromRect(distance);
        } else {
            float scaleX = (width - (distance * 2.0f)) / width;
            float scaleY = (height - (2.0f * distance)) / height;
            this.pathMatrix.reset();
            this.pathMatrix.setScale(scaleX, scaleY, this.originalBounds.centerX(), this.originalBounds.centerY());
            this.originalPath.transform(this.pathMatrix, this.path);
        }
        this.path.computeBounds(this.bounds, true);
        if (this.shapeMode == 3) {
            setMaskBitmapPositions();
        }
    }

    void createPathFromPoints() {
        this.path = new Path();
        this.path.setFillType(FillType.EVEN_ODD);
        this.path.moveTo(this.points[0].x, this.points[0].y);
        int i = 1;
        while (true) {
            PointF[] pointFArr = this.points;
            if (i < pointFArr.length) {
                this.path.lineTo(pointFArr[i].x, this.points[i].y);
                i++;
            } else {
                this.path.lineTo(pointFArr[0].x, this.points[0].y);
                this.path.close();
                return;
            }
        }
    }

    void createPathFromRect() {
        this.path = new Path();
        this.path.addRect(this.sourceRect, Direction.CCW);
    }

    void pathTransform(PointF[] points, Path path, float distance, float centerX, float centerY) {
        int i;
        centerX -= (float) this.offsetX;
        centerY -= (float) this.offsetY;
        path.rewind();
        path.setFillType(FillType.EVEN_ODD);
        int size = points.length;
        float[] distanceArray = new float[size];
        for (i = 0; i < size; i++) {
            distanceArray[i] = distance;
        }
        if (this.exceptionIndex != null) {
            i = 0;
            while (true) {
                int[] iArr = this.exceptionIndex;
                if (i >= iArr.length) {
                    break;
                }
                distanceArray[iArr[i]] = 2.0f * distance;
                i++;
            }
        }
        path.moveTo(checkRange(points[0].x, distanceArray[0], centerX), checkRange(points[0].y, distance, centerY));
        for (i = 1; i < size; i++) {
            path.lineTo(checkRange(points[i].x, distanceArray[i], centerX), checkRange(points[i].y, distance, centerY));
        }
        path.lineTo(checkRange(points[0].x, distanceArray[0], centerX), checkRange(points[0].y, distance, centerY));
        path.close();
        path.offset((float) this.offsetX, (float) this.offsetY);
    }

    void pathTransformFromRect(float distance) {
        this.tempRect.set(this.sourceRect.left + distance, this.sourceRect.top + distance, this.sourceRect.right - distance, this.sourceRect.bottom - distance);
        this.path.rewind();
        this.path.addRect(this.tempRect, Direction.CCW);
    }

    float checkRange(float pointA, float distance, float centerA) {
        if (pointA > centerA) {
            return pointA - distance;
        }
        if (pointA < centerA) {
            return pointA + distance;
        }
        return pointA;
    }

    public void drawShape(Canvas canvas, int width, int height, int j, boolean drawPorterClear) {
        if (drawPorterClear) {
            if (this.shapeMode != 3) {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            } else {
                Bitmap bitmap = this.maskBitmap;
                if (!(bitmap == null || bitmap.isRecycled())) {
                    canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
                }
            }
            canvas.restoreToCount(j);
        }
        this.f508r.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
        this.bitmapMatrix.mapRect(this.f508r);
        int k = canvas.saveLayer(this.f508r, null, Canvas.ALL_SAVE_FLAG);
        if (this.shapeMode != 3) {
            canvas.drawPath(this.path, this.paintPath);
        } else {
            Bitmap bitmap2 = this.maskBitmap;
            if (!(bitmap2 == null || bitmap2.isRecycled())) {
                canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
            }
        }
        canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXferMode);
        canvas.restoreToCount(k);
    }

    public void drawShapeForSave(Canvas canvas, int width, int height, int j, boolean drawPorterClear) {
        if (drawPorterClear) {
            if (this.shapeMode != 3) {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            } else {
                Bitmap bitmap = this.maskBitmap;
                if (!(bitmap == null || bitmap.isRecycled())) {
                    canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
                }
            }
            canvas.restoreToCount(j);
        }
        RectF r = new RectF(0.0f, 0.0f, (float) (this.bitmapWidth + 0), (float) (this.bitmapHeight + 0));
        this.bitmapMatrix.mapRect(r);
        int k = canvas.saveLayer(r, null, Canvas.ALL_SAVE_FLAG);
        if (this.shapeMode != 3) {
            canvas.drawPath(this.path, this.paintPath);
        } else {
            Bitmap bitmap2 = this.maskBitmap;
            if (!(bitmap2 == null || bitmap2.isRecycled())) {
                canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.maskPaint);
            }
        }
        canvas.drawBitmap(this.bitmap, this.bitmapMatrix, this.paintXferMode);
        canvas.restoreToCount(k);
    }

    public void initIcon(int width, int height) {
        this.iconPaint = new Paint(1);
        this.iconPaint.setFilterBitmap(true);
        this.iconPaint.setColor(7829368);
        this.paintXferMode.setColor(7829368);
        scalePath(5.0f, (float) width, (float) height);
        this.iconMaskPaint = new Paint(1);
        this.iconMaskPaint.setFilterBitmap(true);
        this.iconMaskPaint.setColor(7829368);
        this.iconXferMode = new PorterDuffXfermode(Mode.SRC_IN);
        this.iconMaskPaint.setXfermode(this.iconXferMode);
    }

    void drawShapeIcon(Canvas canvas, int width, int height, int j, boolean drawPorterClear) {
        setMaskBitmapPositions();
        this.path.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.originalPath.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.maskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        this.transparentMaskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        if (drawPorterClear) {
            if (this.shapeMode == 3) {
                canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, this.paintTransparent);
            } else {
                canvas.drawPath(this.originalPath, this.paintTransparent);
            }
            canvas.restoreToCount(j);
        }
        if (this.shapeMode == 3) {
            int i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height, null, Canvas.ALL_SAVE_FLAG);
            canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.iconPaint);
            canvas.drawBitmap(this.maskBitmap, this.maskMatrix, this.iconMaskPaint);
            canvas.restoreToCount(i);
            return;
        }
        canvas.drawPath(this.path, this.iconPaint);
    }

    void drawShapeIcon2(Canvas canvas, int width, int height) {
        this.path.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.originalPath.offset((float) (-this.offsetX), (float) (-this.offsetY));
        this.maskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        this.transparentMaskMatrix.postTranslate((float) (-this.offsetX), (float) (-this.offsetY));
        Paint p2 = new Paint();
        if (this.shapeMode == 3) {
            int i = canvas.saveLayer(0.0f, 0.0f, (float) width, (float) height, null, Canvas.ALL_SAVE_FLAG);
            canvas.drawBitmap(this.maskBitmap, this.transparentMaskMatrix, p2);
            p2.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, p2);
            p2.setXfermode(null);
            canvas.restoreToCount(i);
            return;
        }
        canvas.drawPath(this.path, this.iconPaint);
    }

    void bitmapMatrixScale(float scaleX, float scaleY, float centerX, float centerY) {
        this.bitmapMatrix.postScale(scaleX, scaleY, centerX, centerY);
        checkScaleBoundries();
    }

    void bitmapMatrixScaleScrapBook(float scaleX, float scaleY) {
        float[] fArr = this.f507p;
        fArr[0] = (float) (this.bitmapWidth / 2);
        fArr[1] = (float) (this.bitmapHeight / 2);
        this.bitmapMatrix.mapPoints(fArr);
        Matrix matrix = this.bitmapMatrix;
        float[] fArr2 = this.f507p;
        matrix.postScale(scaleX, scaleY, fArr2[0], fArr2[1]);
        checkScaleBoundries();
    }

    void checkScaleBoundries() {
        float scale = getScale();
        float f = this.minScale;
        if (scale < f) {
            Matrix matrix = this.bitmapMatrix;
            float f2 = f / scale;
            f /= scale;
            float[] fArr = this.f507p;
            matrix.postScale(f2, f, fArr[0], fArr[1]);
        }
        f = this.maxScale;
        if (scale > f) {
            Matrix matrix = this.bitmapMatrix;
            float f2 = f / scale;
            f /= scale;
            float[] fArr = this.f507p;
            matrix.postScale(f2, f, fArr[0], fArr[1]);
        }
    }

    void bitmapMatrixTranslate(float dx, float dy) {
        this.bitmapMatrix.postTranslate(dx, dy);
        if (!this.isScrapBook) {
            checkBoundries();
        }
    }

    public void checkBoundries() {
        this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
        this.bitmapMatrix.mapRect(this.bitmapRect);
        float dx = 0.0f;
        float dy = 0.0f;
        if (this.bitmapRect.left > this.bounds.left) {
            dx = this.bounds.left - this.bitmapRect.left;
        }
        if (this.bitmapRect.top > this.bounds.top) {
            dy = this.bounds.top - this.bitmapRect.top;
        }
        if (this.bitmapRect.right < this.bounds.right) {
            dx = this.bounds.right - this.bitmapRect.right;
        }
        if (this.bitmapRect.bottom < this.bounds.bottom) {
            dy = this.bounds.bottom - this.bitmapRect.bottom;
        }
        this.bitmapMatrix.postTranslate(dx, dy);
    }

    public void checkScaleBounds() {
        setMinScales(getBitmapScale());
        checkScaleBoundries();
    }

    void bitmapMatrixgGetValues(float[] values) {
        this.bitmapMatrix.getValues(values);
    }

    void bitmapMatrixRotate(float angle) {
        float[] fArr = this.f507p;
        fArr[0] = (float) (this.bitmapWidth / 2);
        fArr[1] = (float) (this.bitmapHeight / 2);
        this.bitmapMatrix.mapPoints(fArr);
        Matrix matrix = this.bitmapMatrix;
        float[] fArr2 = this.f507p;
        matrix.postRotate(angle, fArr2[0], fArr2[1]);
    }

    public int setScaleMatrix(int mode) {
        if (this.dx <= 0.5f) {
            this.dx = ((float) this.bitmapWidth) / 100.0f;
        }
        if (this.dy <= 0.5f) {
            this.dy = ((float) this.bitmapHeight) / 100.0f;
        }
        PointF centerOfImage = getCenterOfImage();
        if (mode == 0) {
            setMatrixFit();
        } else if (mode == 1) {
            setBitmapPosition();
        } else if (mode == 3) {
            this.bitmapMatrix.postRotate(-90.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 2) {
            this.bitmapMatrix.postRotate(90.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 4) {
            this.bitmapMatrix.postScale(-1.0f, 1.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 5) {
            this.bitmapMatrix.postScale(1.0f, -1.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 6) {
            this.bitmapMatrix.postRotate(-10.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 7) {
            this.bitmapMatrix.postRotate(10.0f, centerOfImage.x, centerOfImage.y);
        } else if (mode == 8) {
            if (getScale() >= this.maxScale) {
                return 1;
            }
            r2 = this.scaleUp;
            bitmapMatrix.postScale(r2, r2, centerOfImage.x, centerOfImage.y);
        } else if (mode == 9) {
            if (getScale() <= this.minScale) {
                return 2;
            }
            r2 = this.scaleDown;
            bitmapMatrix.postScale(r2, r2, centerOfImage.x, centerOfImage.y);
        } else if (mode == 10) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.right <= this.bounds.right && !this.isScrapBook) {
                return 3;
            }
            this.bitmapMatrix.postTranslate(-this.dx, 0.0f);
        } else if (mode == 11) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.left >= this.bounds.left && !this.isScrapBook) {
                return 4;
            }
            this.bitmapMatrix.postTranslate(this.dx, 0.0f);
        } else if (mode == 12) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.bottom <= this.bounds.bottom && !this.isScrapBook) {
                return 5;
            }
            this.bitmapMatrix.postTranslate(0.0f, -this.dy);
        } else if (mode == 13) {
            this.bitmapRect.set(0.0f, 0.0f, (float) this.bitmapWidth, (float) this.bitmapHeight);
            this.bitmapMatrix.mapRect(this.bitmapRect);
            if (this.bitmapRect.top >= this.bounds.top && !this.isScrapBook) {
                return 6;
            }
            this.bitmapMatrix.postTranslate(0.0f, this.dy);
        }
        checkScaleBoundries();
        if (!this.isScrapBook) {
            checkBoundries();
        }
        return 0;
    }

    PointF getCenterOfImage() {
        if (this.centerOriginal == null) {
            this.centerOriginal = new PointF();
        }
        if (this.f506f == null) {
            this.f506f = new float[2];
        }
        float y = ((float) this.bitmapHeight) / 2.0f;
        float[] fArr = this.f506f;
        fArr[0] = ((float) this.bitmapWidth) / 2.0f;
        fArr[1] = y;
        this.bitmapMatrix.mapPoints(fArr);
        PointF pointF = this.centerOriginal;
        float[] fArr2 = this.f506f;
        pointF.set(fArr2[0], fArr2[1]);
        return this.centerOriginal;
    }

    void setMatrixFit() {
        float scaleBitmap = Math.min(this.bounds.width() / ((float) this.bitmapWidth), this.bounds.height() / ((float) this.bitmapHeight));
        if (this.isScrapBook) {
            scaleBitmap *= Collage.scrapBookShapeScale;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Collage.scrapBookShapeScale ");
        stringBuilder.append(Collage.scrapBookShapeScale);
        Log.e(TAG, stringBuilder.toString());
        float bitmapY = this.bounds.top + ((this.bounds.height() - (((float) this.bitmapHeight) * scaleBitmap)) / 2.0f);
        float bitmapX = this.bounds.left + ((this.bounds.width() - (((float) this.bitmapWidth) * scaleBitmap)) / 2.0f);
        this.bitmapMatrix.reset();
        this.bitmapMatrix.postScale(scaleBitmap, scaleBitmap);
        this.bitmapMatrix.postTranslate(bitmapX, bitmapY);
    }

    private void setScrapBookBitmapPosition(int index, boolean isChangeRatio, int width, int height) {
        Shape shape = this;
        if (isChangeRatio) {
            int w = shape.bitmapWidth;
            int h = shape.bitmapHeight;
            float[] points = new float[]{0.0f, 0.0f, (float) w, 0.0f, (float) w, (float) h, 0.0f, (float) h};
            shape.bitmapMatrix.mapPoints(points);
            int i = shape.offsetX;
            float f = (float) i;
            int i2 = shape.offsetY;
            RectF drawArea = new RectF(f, (float) i2, (float) (i + width), (float) (i2 + height));
            if (!drawArea.contains(points[0], points[1]) && !drawArea.contains(points[2], points[3]) && !drawArea.contains(points[4], points[5]) && !drawArea.contains(points[6], points[7])) {
                PointF A = new PointF((float) shape.offsetX, (float) shape.offsetY);
                PointF B = new PointF((float) (shape.offsetX + width), (float) shape.offsetY);
                PointF P = new PointF();
                f = points[1];
                int i3 = shape.offsetY;
                float f2 = (float) i3;
                String str = "1  ";
                String str2 = "0  ";
                String str3 = TAG;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2;
                int i4;
                if (f < f2) {
                    P.set(points[0], points[1]);
                    float[] f3 = new float[4];
                    f3[0] = pointToLineDistance(A, B, P);
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(str2);
                    stringBuilder3.append(distToSegment(P, A, B));
                    Log.e(str3, stringBuilder3.toString());
                    P.set(points[2], points[3]);
                    f3[1] = pointToLineDistance(A, B, P);
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(distToSegment(P, A, B));
                    Log.e(str3, stringBuilder.toString());
                    P.set(points[4], points[5]);
                    f3[2] = pointToLineDistance(A, B, P);
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("2  ");
                    stringBuilder2.append(distToSegment(P, A, B));
                    Log.e(str3, stringBuilder2.toString());
                    P.set(points[6], points[7]);
                    f3[3] = pointToLineDistance(A, B, P);
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("3  ");
                    stringBuilder2.append(distToSegment(P, A, B));
                    Log.e(str3, stringBuilder2.toString());
                    float min = f3[0];
                    int minIndex = 0;
                    for (i4 = 1; i4 < 4; i4++) {
                        if (f3[i4] < min) {
                            min = f3[i4];
                            minIndex = i4;
                        }
                        stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("fi  ");
                        stringBuilder3.append(f3[i4]);
                        Log.e(str3, stringBuilder3.toString());
                    }
                    shape.bitmapMatrix.postTranslate(0.0f, ((float) (shape.offsetY + 120)) - points[(minIndex * 2) + 1]);
                    return;
                }
                PointF A2 = new PointF((float) shape.offsetX, (float) (i3 + height));
                PointF B2 = new PointF((float) (shape.offsetX + width), (float) (shape.offsetY + height));
                P.set(points[0], points[1]);
                float[] f4 = new float[4];
                f4[0] = pointToLineDistance(A2, B2, P);
                float f22 = A2.x;
                StringBuilder stringBuilder4 = new StringBuilder();
                stringBuilder4.append("A  x ");
                stringBuilder4.append(shape.r0);
                String str4 = " y ";
                stringBuilder4.append(str4);
                stringBuilder4.append(A2.y);
                Log.e(str3, stringBuilder4.toString());
                float f23 = B2.x;
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append("B  x ");
                stringBuilder5.append(shape.r0);
                stringBuilder5.append(str4);
                stringBuilder5.append(B2.y);
                Log.e(str3, stringBuilder5.toString());
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append(str2);
                stringBuilder5.append(distToSegment(P, A2, B2));
                Log.e(str3, stringBuilder5.toString());
                f23 = P.x;
                stringBuilder = new StringBuilder();
                stringBuilder.append("0  x ");
                stringBuilder.append(shape.r0);
                stringBuilder.append(str4);
                stringBuilder.append(P.y);
                Log.e(str3, stringBuilder.toString());
                P.set(points[2], points[3]);
                f4[1] = pointToLineDistance(A2, B2, P);
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(distToSegment(P, A2, B2));
                Log.e(str3, stringBuilder.toString());
                f23 = P.x;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("1  x ");
                stringBuilder2.append(shape.r0);
                stringBuilder2.append(str4);
                stringBuilder2.append(P.y);
                Log.e(str3, stringBuilder2.toString());
                P.set(points[4], points[5]);
                f4[2] = pointToLineDistance(A2, B2, P);
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("2  ");
                stringBuilder2.append(distToSegment(P, A2, B2));
                Log.e(str3, stringBuilder2.toString());
                f23 = P.x;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("2  x ");
                stringBuilder2.append(shape.r0);
                stringBuilder2.append(str4);
                stringBuilder2.append(P.y);
                Log.e(str3, stringBuilder2.toString());
                P.set(points[6], points[7]);
                f4[3] = pointToLineDistance(A2, B2, P);
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("3  ");
                stringBuilder2.append(distToSegment(P, A2, B2));
                Log.e(str3, stringBuilder2.toString());
                f23 = P.x;
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("3  x ");
                stringBuilder2.append(shape.r0);
                stringBuilder2.append(str4);
                stringBuilder2.append(P.y);
                Log.e(str3, stringBuilder2.toString());
                float min2 = f4[0];
                i4 = 0;
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append("bi  ");
                stringBuilder5.append(f4[0]);
                Log.e(str3, stringBuilder5.toString());
                for (int i5 = 1; i5 < 4; i5++) {
                    if (f4[i5] < min2) {
                        min2 = f4[i5];
                        i4 = i5;
                    }
                    stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("bi  ");
                    stringBuilder4.append(f4[i5]);
                    Log.e(str3, stringBuilder4.toString());
                }
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append("minIndex  ");
                stringBuilder5.append(i4);
                Log.e(str3, stringBuilder5.toString());
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append(" points[minIndex*2+1] ");
                stringBuilder5.append(points[(i4 * 2) + 1]);
                Log.e(str3, stringBuilder5.toString());
                stringBuilder5 = new StringBuilder();
                stringBuilder5.append("translate ");
                stringBuilder5.append(((float) ((shape.offsetY + height) - 120)) - points[(i4 * 2) + 1]);
                Log.e(str3, stringBuilder5.toString());
                shape.bitmapMatrix.postTranslate(0.0f, ((float) ((shape.offsetY + height) - 120)) - points[(i4 * 2) + 1]);
                return;
            }
            return;
        }
        shape.bitmapMatrix = new Matrix();
        setMatrixFit();
        float f23 = getScale();
        setMaxMinScales(f23);
        float scale = 1.0f / f23;
        shape.touchStrokeWidth = shape.tempTouchStrokeWidth * scale;
        shape.scrapBookPadding = 25.0f * scale;
        shape.bitmapMatrix.postRotate((float) scrapBookRotation[index], shape.bounds.left + (shape.bounds.width() / 2.0f), shape.bounds.top + (shape.bounds.height() / 2.0f));
        float f5 = shape.scrapBookPadding;
        shape.touchRect = new RectF(-f5, -f5, ((float) shape.bitmapWidth) + f5, ((float) shape.bitmapHeight) + f5);
        shape.touchPaint.setColor(1290417);
        shape.touchPaint.setFilterBitmap(true);
        shape.touchPaint.setStyle(Style.STROKE);
        shape.touchPaint.setStrokeWidth(shape.touchStrokeWidth);
        shape.borderPaint.setColor(-1);
        shape.borderPaint.setStyle(Style.STROKE);
        shape.borderPaint.setStrokeWidth((float) shape.borderStrokeWidth);
        shape.borderPaint.setAntiAlias(true);
    }

    public float pointToLineDistance(PointF A, PointF B, PointF P) {
        return Math.abs(((P.x - A.x) * (B.y - A.y)) - ((P.y - A.y) * (B.x - A.x))) / ((float) Math.sqrt((double) (((B.x - A.x) * (B.x - A.x)) + ((B.y - A.y) * (B.y - A.y)))));
    }

    float sqr(float x) {
        return x * x;
    }

    float dist2(PointF v, PointF w) {
        return sqr(v.x - w.x) + sqr(v.y - w.y);
    }

    float distToSegmentSquared(PointF p, PointF v, PointF w) {
        float l2 = dist2(v, w);
        if (l2 == 0.0f) {
            return dist2(p, v);
        }
        float t = (((p.x - v.x) * (w.x - v.x)) + ((p.y - v.y) * (w.y - v.y))) / l2;
        if (t < 0.0f) {
            return dist2(p, v);
        }
        if (t > 1.0f) {
            return dist2(p, w);
        }
        return dist2(p, new PointF(v.x + ((w.x - v.x) * t), v.y + ((w.y - v.y) * t)));
    }

    float distToSegment(PointF p, PointF v, PointF w) {
        return (float) Math.sqrt((double) distToSegmentSquared(p, v, w));
    }

    float[] getMappedCenter() {
        float[] fArr = this.pts;
        fArr[0] = (float) (this.bitmapWidth / 2);
        fArr[1] = (float) (this.bitmapHeight / 2);
        this.bitmapMatrix.mapPoints(fArr, fArr);
        return this.pts;
    }

    public boolean isScrapBookSelected(float x1, float y1) {
        float[] fArr = this.pts;
        fArr[0] = x1;
        fArr[1] = y1;
        this.inverse.reset();
        boolean isInversable = this.bitmapMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float y = pts[1];
        float x = pts[0];
        if (x >= 0.0f && x <= ((float) this.bitmapWidth) && y >= 0.0f) {
            if (y <= ((float) this.bitmapHeight)) {
                return true;
            }
        }
        return false;
    }

    public void drawShapeForScrapBook(Canvas canvas, int width, int height, boolean isSelected, boolean isOrthogonal) {
        canvas.save();
        canvas.concat(this.bitmapMatrix);
        NinePatchDrawable ninePatchDrawable = this.npd;
        int i = this.npdPadding;
        int i2 = -i;
        int i3 = this.borderStrokeWidth;
        ninePatchDrawable.setBounds(i2 - i3, (-i) - i3, (this.bitmapWidth + i) + i3, (this.bitmapHeight + i) + i3);
        this.npd.draw(canvas);
        canvas.drawBitmap(this.bitmap, 0.0f, 0.0f, this.paintScrap);
        if (isSelected) {
            this.touchStrokeWidth = this.tempTouchStrokeWidth * (1.0f / getScale());
            this.touchPaint.setStrokeWidth(this.touchStrokeWidth);
            canvas.drawRect(this.touchRect, this.touchPaint);
            setDelAndScaleBitmapMatrix();
            Bitmap bitmap = this.btmDelete;
            if (!(bitmap == null || bitmap.isRecycled())) {
                canvas.drawBitmap(this.btmDelete, this.removeBitmapMatrix, this.touchPaint);
            }
            bitmap = this.btmScale;
            if (!(bitmap == null || bitmap.isRecycled())) {
                canvas.drawBitmap(this.btmScale, this.scaleBitmapMatrix, this.touchPaint);
            }
            if (isOrthogonal) {
                canvas.drawPath(this.dashPathVertical, this.dashPaint);
                canvas.drawPath(this.dashPathHorizontal, this.dashPaint);
            }
        }
        int i4 = this.borderStrokeWidth;
        canvas.drawRect((float) ((-i4) / 2), (float) ((-i4) / 2), (float) (this.bitmapWidth + (i4 / 2)), (float) (this.bitmapHeight + (i4 / 2)), this.borderPaint);
        canvas.restore();
    }

    void setDelAndScaleBitmapMatrix() {
        if (this.removeBitmapMatrix == null) {
            this.removeBitmapMatrix = new Matrix();
        }
        if (this.scaleBitmapMatrix == null) {
            this.scaleBitmapMatrix = new Matrix();
        }
        this.removeBitmapMatrix.reset();
        this.scaleBitmapMatrix.reset();
        if (this.delW == 0) {
            Bitmap bitmap = this.btmDelete;
            if (bitmap != null) {
                this.delW = bitmap.getWidth();
            }
        }
        if (this.screenWidth <= 0) {
            this.screenWidth = 720;
        }
        float bitmapScale = (((float) this.screenWidth) / 20.0f) * 2.0f;
        int i = this.delW;
        bitmapScale /= (float) i;
        this.deleteWidthHalf = (((float) i) * bitmapScale) / 1.4f;
        this.removeBitmapMatrix.postScale(bitmapScale, bitmapScale);
        Matrix matrix = this.removeBitmapMatrix;
        float f = this.scrapBookPadding;
        float f2 = -f;
        int i2 = this.delW;
        matrix.postTranslate(f2 - ((((float) i2) * bitmapScale) / 2.0f), (-f) - ((((float) i2) * bitmapScale) / 2.0f));
        this.scaleBitmapMatrix.postScale(bitmapScale, bitmapScale);
        matrix = this.scaleBitmapMatrix;
        f = (float) this.bitmapWidth;
        f2 = this.scrapBookPadding;
        f += f2;
        i2 = this.delW;
        matrix.postTranslate(f - ((((float) i2) * bitmapScale) / 2.0f), (((float) this.bitmapHeight) + f2) - ((((float) i2) * bitmapScale) / 2.0f));
        float scale = getScale();
        this.scaleBitmapMatrix.postScale(1.0f / scale, 1.0f / scale, this.touchRect.right, this.touchRect.bottom);
        this.removeBitmapMatrix.postScale(1.0f / scale, 1.0f / scale, this.touchRect.left, this.touchRect.top);
        i = this.screenWidth;
        if (i > 0) {
            this.tempTouchStrokeWidth = ((float) i) / 120.0f;
        }
    }

    public void initScrapBook(NinePatchDrawable npd) {
        setNinePatch(npd);
    }

    public void setNinePatch(NinePatchDrawable npd) {
        this.npd = npd;
        this.touchRect.round(new Rect());
    }

    float getScale() {
        this.bitmapMatrix.getValues(this.values);
        float skewy = values[3];
        float scalex = values[0];
        float scale = (float) Math.sqrt((double) ((scalex * scalex) + (skewy * skewy)));
        if (scale <= 0.0f) {
            return 1.0f;
        }
        return scale;
    }

    boolean isInCircle(float x1, float y1) {
        float[] fArr = this.pts;
        fArr[0] = x1;
        fArr[1] = y1;
        this.bitmapMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float y = pts[1];
        float x = pts[0];
        float scale = getScale();
        float f = ((x - this.touchRect.right) * (x - this.touchRect.right)) + ((y - this.touchRect.bottom) * (y - this.touchRect.bottom));
        float f2 = this.deleteWidthHalf;
        if (f < (f2 * f2) / (scale * scale)) {
            return true;
        }
        return false;
    }

    boolean isOnCross(float x1, float y1) {
        float[] fArr = this.pts;
        fArr[0] = x1;
        fArr[1] = y1;
        this.bitmapMatrix.invert(this.inverse);
        Matrix matrix = this.inverse;
        float[] fArr2 = this.pts;
        matrix.mapPoints(fArr2, fArr2);
        float y = pts[1];
        float x = pts[0];
        float scale = getScale();
        float f = ((x - this.touchRect.left) * (x - this.touchRect.left)) + ((y - this.touchRect.top) * (y - this.touchRect.top));
        float f2 = this.deleteWidthHalf;
        if (f < (f2 * f2) / (scale * scale)) {
            return true;
        }
        return false;
    }

    public void resetDashPaths() {
        if (this.dashPathVertical == null) {
            this.dashPathVertical = new Path();
        }
        this.dashPathVertical.reset();
        this.dashPathVertical.moveTo((float) (this.bitmapWidth / 2), (float) ((-this.bitmapHeight) / 5));
        this.dashPathVertical.lineTo((float) (this.bitmapWidth / 2), (float) ((this.bitmapHeight * 6) / 5));
        if (this.dashPathHorizontal == null) {
            this.dashPathHorizontal = new Path();
        }
        this.dashPathHorizontal.reset();
        this.dashPathHorizontal.moveTo((float) ((-this.bitmapWidth) / 5), (float) (this.bitmapHeight / 2));
        this.dashPathHorizontal.lineTo((float) ((this.bitmapWidth * 6) / 5), (float) (this.bitmapHeight / 2));
    }
}
