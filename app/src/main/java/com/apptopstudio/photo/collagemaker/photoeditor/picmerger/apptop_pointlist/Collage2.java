package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist;

import android.graphics.PointF;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;

import java.util.ArrayList;

public class Collage2 extends Collage {
    public static int shapeCount = 2;

    public Collage2(int i, int j) {
        int i2 = i;
        int i3 = j;
        this.collageLayoutList = new ArrayList();
        ArrayList obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.5f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.5f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.5f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.4f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.4f, ((float) i3) * 1.0f)});
        CollageLayout obj2 = new CollageLayout(obj);
        obj2.maskPairList.add(new MaskPair(0, R.drawable.mask_heart));
        obj2.maskPairList.add(new MaskPair(1, R.drawable.mask_heart));
        obj2.setClearIndex(1);
        this.collageLayoutList.add(obj2);
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.13f, ((float) i3) * 0.13f), new PointF(((float) i2) * 0.13f, ((float) i3) * 0.87f), new PointF(((float) i2) * 0.87f, ((float) i3) * 0.87f), new PointF(((float) i2) * 0.87f, ((float) i3) * 0.13f)});
        obj2 = new CollageLayout(obj);
        obj2.maskPairList.add(new MaskPair(1, R.drawable.mask_heart));
        obj2.setClearIndex(1);
        this.collageLayoutList.add(obj2);
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.13f, ((float) i3) * 0.13f), new PointF(((float) i2) * 0.13f, ((float) i3) * 0.87f), new PointF(((float) i2) * 0.87f, ((float) i3) * 0.87f), new PointF(((float) i2) * 0.87f, ((float) i3) * 0.13f)});
        obj2 = new CollageLayout(obj);
        obj2.maskPairList.add(new MaskPair(1, R.drawable.mask_cloud));
        obj2.setClearIndex(1);
        this.collageLayoutList.add(obj2);
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5833f), new PointF(((float) i2) * 0.1667f, ((float) i3) * 0.41667f), new PointF(((float) i2) * 0.333f, ((float) i3) * 0.5833f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.41667f), new PointF(((float) i2) * 0.6667f, ((float) i3) * 0.5833f), new PointF(((float) i2) * 0.8333f, ((float) i3) * 0.41667f), new PointF((float) (i2 * 1), ((float) i3) * 0.5833f), new PointF((float) (i2 * 1), (float) (i3 * 1))});
        obj.add(new PointF[]{new PointF((float) (i2 * 0), ((float) i3) * 0.5833f), new PointF((float) (i2 * 0), (float) (i3 * 0)), new PointF((float) (i2 * 1), (float) (i3 * 0)), new PointF((float) (i2 * 1), ((float) i3) * 0.5833f), new PointF(((float) i2) * 0.8333f, ((float) i3) * 0.41667f), new PointF(((float) i2) * 0.6667f, ((float) i3) * 0.5833f), new PointF(((float) i2) * 0.5f, ((float) i3) * 0.41667f), new PointF(((float) i2) * 0.333f, ((float) i3) * 0.5833f), new PointF(((float) i2) * 0.1667f, ((float) i3) * 0.41667f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.13f, ((float) i3) * 0.13f), new PointF(((float) i2) * 0.13f, ((float) i3) * 0.87f), new PointF(((float) i2) * 0.87f, ((float) i3) * 0.87f), new PointF(((float) i2) * 0.87f, ((float) i3) * 0.13f)});
        obj2 = new CollageLayout(obj);
        obj2.maskPairList.add(new MaskPair(1, R.drawable.mask_hexagon));
        obj2.setClearIndex(1);
        this.collageLayoutList.add(obj2);
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.5f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.3333333f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 0.3333333f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.6666667f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.6f, ((float) i3) * 0.6f), new PointF(((float) i2) * 0.6f, ((float) i3) * 0.9333333f), new PointF(((float) i2) * 0.9333333f, ((float) i3) * 0.9333333f), new PointF(((float) i2) * 0.9333333f, ((float) i3) * 0.6f)});
        obj2 = new CollageLayout(obj);
        obj2.setClearIndex(1);
        this.collageLayoutList.add(obj2);
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.3333333f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
        obj = new ArrayList();
        obj.add(new PointF[]{new PointF(((float) i2) * 0.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f)});
        obj.add(new PointF[]{new PointF(((float) i2) * 0.6666667f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 0.0f), new PointF(((float) i2) * 1.0f, ((float) i3) * 1.0f), new PointF(((float) i2) * 0.3333333f, ((float) i3) * 1.0f)});
        this.collageLayoutList.add(new CollageLayout(obj));
    }
}
