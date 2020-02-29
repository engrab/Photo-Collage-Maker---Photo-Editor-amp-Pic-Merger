package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib;

import android.view.MotionEvent;

public class RotationGestureDetector {
    private static final int INVALID_POINTER_ID = -1;
    private float fX;
    private float fY;
    private float mAngle;
    private OnRotationGestureListener mListener;
    private int ptrID1 = -1;
    private int ptrID2 = -1;
    private float sX;
    private float sY;

    public interface OnRotationGestureListener {
        void OnRotation(RotationGestureDetector rotationGestureDetector);
    }

    public float getAngle() {
        return this.mAngle;
    }

    public RotationGestureDetector(OnRotationGestureListener listener) {
        this.mListener = listener;
    }

    public boolean onTouchEvent(MotionEvent event) {
        RotationGestureDetector rotationGestureDetector = this;
        MotionEvent motionEvent = event;
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            rotationGestureDetector.ptrID1 = motionEvent.getPointerId(event.getActionIndex());
        } else if (actionMasked == 1) {
            rotationGestureDetector.ptrID1 = -1;
        } else if (actionMasked == 2) {
            actionMasked = rotationGestureDetector.ptrID1;
            if (!(actionMasked == -1 || rotationGestureDetector.ptrID2 == -1)) {
                int index1Move = motionEvent.findPointerIndex(actionMasked);
                int index2Move = motionEvent.findPointerIndex(rotationGestureDetector.ptrID2);
                int pointerCountMove = event.getPointerCount();
                if (index1Move >= 0 && index1Move < pointerCountMove && index2Move >= 0 && index2Move < pointerCountMove) {
                    float nsX = motionEvent.getX(motionEvent.findPointerIndex(rotationGestureDetector.ptrID1));
                    float nsY = motionEvent.getY(motionEvent.findPointerIndex(rotationGestureDetector.ptrID1));
                    rotationGestureDetector.mAngle = angleBetweenLines(rotationGestureDetector.fX, rotationGestureDetector.fY, rotationGestureDetector.sX, rotationGestureDetector.sY, motionEvent.getX(motionEvent.findPointerIndex(rotationGestureDetector.ptrID2)), motionEvent.getY(motionEvent.findPointerIndex(rotationGestureDetector.ptrID2)), nsX, nsY);
                    OnRotationGestureListener onRotationGestureListener = rotationGestureDetector.mListener;
                    if (onRotationGestureListener != null) {
                        onRotationGestureListener.OnRotation(rotationGestureDetector);
                    }
                }
            }
        } else if (actionMasked != 3) {
            if (actionMasked == 5) {
                rotationGestureDetector.ptrID2 = motionEvent.getPointerId(event.getActionIndex());
                actionMasked = motionEvent.findPointerIndex(rotationGestureDetector.ptrID1);
                int index2 = motionEvent.findPointerIndex(rotationGestureDetector.ptrID2);
                int pointerCount = event.getPointerCount();
                if (actionMasked >= 0 && actionMasked < pointerCount && index2 >= 0 && index2 < pointerCount) {
                    rotationGestureDetector.sX = motionEvent.getX(actionMasked);
                    rotationGestureDetector.sY = motionEvent.getY(actionMasked);
                    rotationGestureDetector.fX = motionEvent.getX(index2);
                    rotationGestureDetector.fY = motionEvent.getY(index2);
                }
            } else if (actionMasked != 6) {
            }
            rotationGestureDetector.ptrID2 = -1;
        } else {
            rotationGestureDetector.ptrID1 = -1;
            rotationGestureDetector.ptrID2 = -1;
        }
        return true;
    }

    private float angleBetweenLines(float fX, float fY, float sX, float sY, float nfX, float nfY, float nsX, float nsY) {
        float angle = ((float) Math.toDegrees((double) (((float) Math.atan2((double) (fY - sY), (double) (fX - sX))) - ((float) Math.atan2((double) (nfY - nsY), (double) (nfX - nsX)))))) % 360.0f;
        if (angle < -180.0f) {
            angle += 360.0f;
        }
        if (angle > 180.0f) {
            return angle - 360.0f;
        }
        return angle;
    }
}
