package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist;

import android.graphics.PointF;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;

import java.util.ArrayList;

public class Collage3 extends Collage {
    public static int shapeCount = 3;

    public Collage3(int i, int j) {
        int i2 = i;
        int i3 = j;
        this.collageLayoutList = new ArrayList();
        PointF[] apointf = new PointF[4];
        ArrayList obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        ArrayList obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.2f, ((float) i3) * 0.2f), new PointF(((float) i2) * 0.2f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.2f)});
        CollageLayout obj22 = new CollageLayout(obj2);
        obj22.maskPairList.add(new MaskPair(2, R.drawable.mask_circle));
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.58f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.58f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.58f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.58f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.42f, ((float) i3) * 0.25f), new PointF(((float) i2) * 0.42f, ((float) i3) * 0.75f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.75f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.25f)});
        obj22 = new CollageLayout(obj2);
        obj22.maskPairList.add(new MaskPair(0, R.drawable.mask_hexagon));
        obj22.maskPairList.add(new MaskPair(1, R.drawable.mask_hexagon));
        obj22.maskPairList.add(new MaskPair(2, R.drawable.mask_hexagon));
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.25f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.75f), new PointF(((float) i2) * 0.75f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.25f)});
        obj22 = new CollageLayout(obj2);
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.2f, ((float) i3) * 0.2f), new PointF(((float) i2) * 0.2f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.2f)});
        obj22 = new CollageLayout(obj2);
        obj22.maskPairList.add(new MaskPair(2, R.drawable.mask_heart));
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.2f, ((float) i3) * 0.2f), new PointF(((float) i2) * 0.2f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.2f)});
        obj22 = new CollageLayout(obj2);
        obj22.maskPairList.add(new MaskPair(2, R.drawable.mask_hexagon));
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.2f, ((float) i3) * 0.2f), new PointF(((float) i2) * 0.2f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.8f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.2f)});
        obj22 = new CollageLayout(obj2);
        obj22.maskPairList.add(new MaskPair(2, R.drawable.mask_circle));
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.2f, ((float) i3) * 0.25f), new PointF(((float) i2) * 0.2f, ((float) i3) * 0.75f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.75f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.25f)});
        obj22 = new CollageLayout(obj2);
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.2f, ((float) i3) * 0.25f), new PointF(((float) i2) * 0.2f, ((float) i3) * 0.75f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.75f), new PointF(((float) i2) * 0.8f, ((float) i3) * 0.25f)});
        obj22 = new CollageLayout(obj2);
        obj22.setClearIndex(2);
        this.collageLayoutList.add(obj22);
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.3333333f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.5f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.25f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.25f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.75f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.25f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.25f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.75f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.75f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.75f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.6666667f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
        obj2 = new ArrayList();
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.3333333f)});
        obj2.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.3333333f)});
        this.collageLayoutList.add(new CollageLayout(obj2));
    }
}
