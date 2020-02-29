package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_canvastext;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

public class FontCache {
    private static Map<String, Typeface> sCachedFonts = new HashMap();

    public static Typeface get(Context context, String assetPath) {
        if (!sCachedFonts.containsKey(assetPath)) {
            sCachedFonts.put(assetPath, Typeface.createFromAsset(context.getAssets(), assetPath));
        }
        return (Typeface) sCachedFonts.get(assetPath);
    }
}
