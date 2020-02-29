package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_gallerylib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class GallerySquareImageView extends AppCompatImageView {
    public GallerySquareImageView(Context context) {
        super(context);
    }

    public GallerySquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GallerySquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = getMeasuredWidth();
        setMeasuredDimension(i, i);
    }
}
