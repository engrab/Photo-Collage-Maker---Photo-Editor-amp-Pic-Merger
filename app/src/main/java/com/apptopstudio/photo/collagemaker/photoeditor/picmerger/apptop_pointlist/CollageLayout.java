package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_pointlist;

import java.util.ArrayList;
import java.util.List;

public class CollageLayout {
    public int[][] exceptionIndexForShapes = ((int[][]) null);
    boolean isScalable = false;
    public List<MaskPair> maskPairList = new ArrayList();
    public List<MaskPairSvg> maskPairListSvg = new ArrayList();
    int porterDuffClearBorderIntex = -1;
    public List shapeList;
    public boolean useLine = true;

    public CollageLayout(List list) {
        this.shapeList = list;
    }

    public int getClearIndex() {
        return this.porterDuffClearBorderIntex;
    }

    public boolean getScalibility() {
        return this.isScalable;
    }

    public int[] getexceptionIndex(int i) {
        int[][] iArr = this.exceptionIndexForShapes;
        if (iArr != null && i < iArr.length) {
            if (i >= 0) {
                return iArr[i];
            }
        }
        return null;
    }

    public void setClearIndex(int i) {
        if (i >= 0 && i < this.shapeList.size()) {
            this.porterDuffClearBorderIntex = i;
        }
    }

    public void setScalibility(boolean flag) {
        this.isScalable = flag;
    }
}
