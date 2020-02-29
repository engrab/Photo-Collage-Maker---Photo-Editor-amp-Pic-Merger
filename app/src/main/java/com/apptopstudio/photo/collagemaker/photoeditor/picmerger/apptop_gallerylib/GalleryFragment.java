package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_gallerylib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.Glob;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements OnItemClickListener {
    public static int MAX_COLLAGE = 9;
    public static int MAX_MIRROR = 1;
    public static int MAX_SCRAPBOOK = 9;
    private static final String TAG = "GalleryActivity";
    int COLLAGE_IMAGE_LIMIT_MAX = 9;
    int COLLAGE_IMAGE_LIMIT_MIN = 0;
    Activity activity;
    MyGridAdapter adapter;
    List<Album> albumList;
    boolean collageSingleMode = false;
    Context context;
    TextView deleteAllTv;
    LinearLayout footer;
    GalleryListener galleryListener;
    Parcelable gridState;
    GridView gridView;
    TextView headerText;
    ImageView imageBack;
    boolean isMirror = false;
    boolean isOnBucket = true;
    public boolean isScrapBook = false;
    boolean isShape = false;
    TextView maxTv;
    TextView nextTv;
    OnClickListener onClickListener = new C03401();
    TextView removeAllTv;
    int selectedBucketId;
    List<Long> selectedImageIdList = new ArrayList();
    List<Integer> selectedImageOrientationList = new ArrayList();
    Animation slideInLeft;

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.gallerylib.GalleryFragment$1 */
    class C03401 implements OnClickListener {
        C03401() {
        }

        @SuppressLint({"WrongConstant"})
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.imageBack) {
                GalleryFragment.this.backtrace();
            }
            if (id == R.id.imageView_delete) {
                View parent = (View) view.getParent();
                if (parent != null && parent.getParent() != null) {
                    int location = ((ViewGroup) parent.getParent()).indexOfChild(parent);
                    GalleryFragment.this.footer.removeView(parent);
                    TextView textView = GalleryFragment.this.deleteAllTv;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("(");
                    stringBuilder.append(GalleryFragment.this.footer.getChildCount());
                    stringBuilder.append(")");
                    textView.setText(stringBuilder.toString());
                    long imageid = ((Long) GalleryFragment.this.selectedImageIdList.remove(location)).longValue();
                    GalleryFragment.this.selectedImageOrientationList.remove(location);
                    Point index = GalleryFragment.this.findItemById(imageid);
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((Album) GalleryFragment.this.albumList.get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((Album) GalleryFragment.this.albumList.get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((Album) GalleryFragment.this.albumList.get(index.x)).gridItems == GalleryFragment.this.adapter.items && GalleryFragment.this.gridView.getFirstVisiblePosition() <= index.y && index.y <= GalleryFragment.this.gridView.getLastVisiblePosition() && GalleryFragment.this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) GalleryFragment.this.gridView.getChildAt(index.y).findViewById(R.id.textViewSelectedItemCount);
                            StringBuilder stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("");
                            stringBuilder2.append(value);
                            text.setText(stringBuilder2.toString());
                            if (value <= 0 && text.getVisibility() == 0) {
                                text.setVisibility(4);
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            if (!(id != R.id.gallery_delete_all || GalleryFragment.this.footer == null || GalleryFragment.this.footer.getChildCount() == 0)) {
                GalleryFragment.this.removeAllTv.setVisibility(0);
                GalleryFragment.this.maxTv.setVisibility(4);
                GalleryFragment.this.deleteAllTv.setVisibility(4);
                GalleryFragment.this.removeAllTv.startAnimation(GalleryFragment.this.slideInLeft);
            }
            if (id == R.id.gallery_remove_all) {
                GalleryFragment.this.remoAll();
            }
            if (id == R.id.gallery_next) {
                GalleryFragment.this.photosSelectFinished();
            }
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.gallerylib.GalleryFragment$3 */
    class C03413 implements Runnable {
        C03413() {
        }

        public void run() {
            if (GalleryFragment.this.gridState != null) {
                Log.d(GalleryFragment.TAG, "trying to restore listview state..");
                GalleryFragment.this.gridView.onRestoreInstanceState(GalleryFragment.this.gridState);
            }
        }
    }

    public interface GalleryListener {
        void onGalleryCancel();

        void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z, boolean z2, boolean z3);

        void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr, boolean z, boolean z2);

        void onGalleryOkSingleImage(long j, int i, boolean z, boolean z2);
    }


    @SuppressLint({"WrongConstant"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_gallery, container, false);
        if (Glob.isOnline(this.context)) {
            AdView mAdView = new AdView(this.context);
            mAdView.setAdSize(AdSize.SMART_BANNER);
            mAdView.setAdUnitId(getString(R.string.banner_ad_id));
            ((RelativeLayout) fragmentView.findViewById(R.id.g_adView)).addView(mAdView);
            mAdView.loadAd(new Builder().build());
        }

        this.footer = (LinearLayout) fragmentView.findViewById(R.id.selected_image_linear);
        this.headerText = (TextView) fragmentView.findViewById(R.id.textView_header);
        this.imageBack = (ImageView) fragmentView.findViewById(R.id.imageBack);
        this.imageBack.setOnClickListener(this.onClickListener);
        this.maxTv = (TextView) fragmentView.findViewById(R.id.gallery_max);
        this.nextTv = (TextView) fragmentView.findViewById(R.id.gallery_next);
        this.nextTv.setOnClickListener(this.onClickListener);
        this.maxTv.setText(String.format(getString(R.string.gallery_lib_max), new Object[]{Integer.valueOf(getLimitMax())}));
        this.deleteAllTv = (TextView) fragmentView.findViewById(R.id.gallery_delete_all);
        this.removeAllTv = (TextView) fragmentView.findViewById(R.id.gallery_remove_all);
        this.slideInLeft = AnimationUtils.loadAnimation(this.context, R.anim.slide_in_left);
        this.deleteAllTv.setOnClickListener(this.onClickListener);
        this.removeAllTv.setOnClickListener(this.onClickListener);
        TextView textView = this.deleteAllTv;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        stringBuilder.append(this.footer.getChildCount());
        stringBuilder.append(")");
        textView.setText(stringBuilder.toString());
        return fragmentView;
    }



    public void onDestroy() {

        super.onDestroy();
    }

    public void setIsShape(boolean isShape) {
        this.isShape = isShape;
    }

    @SuppressLint({"WrongConstant"})
    void remoAll() {
        LinearLayout linearLayout = this.footer;
        if (linearLayout != null) {
            linearLayout.removeAllViews();
            List list = this.selectedImageIdList;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                    Point index = findItemById(((Long) this.selectedImageIdList.get(i)).longValue());
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((Album) this.albumList.get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((Album) this.albumList.get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((Album) this.albumList.get(index.x)).gridItems == this.adapter.items && this.gridView.getFirstVisiblePosition() <= index.y && index.y <= this.gridView.getLastVisiblePosition() && this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) this.gridView.getChildAt(index.y).findViewById(R.id.textViewSelectedItemCount);
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("");
                            stringBuilder.append(value);
                            text.setText(stringBuilder.toString());
                            if (value <= 0 && text.getVisibility() == 0) {
                                text.setVisibility(4);
                            }
                        }
                    }
                }
            }
            list = this.selectedImageIdList;
            if (list != null) {
                list.clear();
            }
            this.selectedImageOrientationList.clear();
            TextView textView = this.deleteAllTv;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("(");
            stringBuilder2.append(this.footer.getChildCount());
            stringBuilder2.append(")");
            textView.setText(stringBuilder2.toString());
            getView().findViewById(R.id.gallery_remove_all).setVisibility(4);
            getView().findViewById(R.id.gallery_max).setVisibility(0);
            this.deleteAllTv.setVisibility(0);
        }
    }

    public int getLimitMax() {
        return this.COLLAGE_IMAGE_LIMIT_MAX;
    }

    public void setGalleryListener(GalleryListener l) {
        this.galleryListener = l;
    }

    public void setIsScrapbook(boolean isScrapbook) {
        this.isScrapBook = isScrapbook;
        setLimitMax(MAX_SCRAPBOOK);
        List list = this.selectedImageIdList;
        if (list == null || list.size() <= this.COLLAGE_IMAGE_LIMIT_MAX) {
            LinearLayout linearLayout = this.footer;
            if (linearLayout != null && linearLayout.getChildCount() > this.COLLAGE_IMAGE_LIMIT_MAX) {
                remoAll();
                return;
            }
            return;
        }
        remoAll();
    }

    public void setLimitMax(int max) {
        this.COLLAGE_IMAGE_LIMIT_MAX = max;
        TextView textView = this.maxTv;
        if (textView != null) {
            textView.setText(String.format(getString(R.string.gallery_lib_max), new Object[]{Integer.valueOf(this.COLLAGE_IMAGE_LIMIT_MAX)}));
        }
    }

    public void onResume() {
        super.onResume();
        GridView gridView = this.gridView;
        if (gridView != null) {
            try {
                this.gridState = gridView.onSaveInstanceState();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logGalleryFolders();
        updateListForSelection();
        setGridAdapter();
        if (!this.isOnBucket) {
            List list = this.albumList;
            if (list != null) {
                int i = this.selectedBucketId;
                if (i >= 0 && i < list.size()) {
                    this.adapter.items = ((Album) this.albumList.get(this.selectedBucketId)).gridItems;
                    gridView = this.gridView;
                    if (gridView != null) {
                        gridView.post(new C03413());
                    }
                }
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    void updateListForSelection() {
        List list = this.selectedImageIdList;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < this.selectedImageIdList.size(); i++) {
                Point localPoint = findItemById(((Long) this.selectedImageIdList.get(i)).longValue());
                if (localPoint != null) {
                    GridViewItem localGridViewItem = (GridViewItem) ((Album) this.albumList.get(localPoint.x)).gridItems.get(localPoint.y);
                    localGridViewItem.selectedItemCount++;
                }
            }
        }
    }

    public void setIsMirrorSelector(boolean isMirror) {
        this.isMirror = isMirror;
    }

    @SuppressLint({"WrongConstant"})
    public void setCollageSingleMode(boolean mode) {
        this.collageSingleMode = mode;
        if (mode) {
            List list = this.selectedImageIdList;
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    Point index = findItemById(((Long) this.selectedImageIdList.remove(i)).longValue());
                    if (index != null) {
                        GridViewItem gridViewItem = (GridViewItem) ((Album) this.albumList.get(index.x)).gridItems.get(index.y);
                        gridViewItem.selectedItemCount--;
                        int value = ((GridViewItem) ((Album) this.albumList.get(index.x)).gridItems.get(index.y)).selectedItemCount;
                        if (((Album) this.albumList.get(index.x)).gridItems == this.adapter.items && this.gridView.getFirstVisiblePosition() <= index.y && index.y <= this.gridView.getLastVisiblePosition() && this.gridView.getChildAt(index.y) != null) {
                            TextView text = (TextView) this.gridView.getChildAt(index.y).findViewById(R.id.textViewSelectedItemCount);
                            text.setText(value);
                            if (value <= 0 && text.getVisibility() == 0) {
                                text.setVisibility(4);
                            }
                        }
                    }
                }
            }
            list = this.selectedImageOrientationList;
            if (list != null) {
                list.clear();
            }
            LinearLayout linearLayout = this.footer;
            if (linearLayout != null) {
                linearLayout.removeAllViews();
            }
            TextView textView = this.deleteAllTv;
            if (textView != null) {
                textView.setText("(0)");
            }
        }
    }

    public void onAttach(Activity act) {
        super.onAttach(act);
        this.context = getActivity();
        this.activity = getActivity();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logGalleryFolders();
        setGridAdapter();
    }

    private void setGridAdapter() {
        this.gridView = (GridView) getView().findViewById(R.id.gridView);
        Context context = this.context;
        List list = this.albumList;
        this.adapter = new MyGridAdapter(context, ((Album) list.get(list.size() - 1)).gridItems, this.gridView);
        this.gridView.setAdapter(this.adapter);
        this.gridView.setOnItemClickListener(this);
    }

    private List<GridViewItem> createGridItemsOnClick(int position) {
        List<GridViewItem> items = new ArrayList();
        Album album = (Album) this.albumList.get(position);
        List<Long> imageIdList = album.imageIdList;
        List<Integer> orientList = album.orientationList;
        for (int i = 0; i < imageIdList.size(); i++) {
            items.add(new GridViewItem(this.activity, "", "", false, ((Long) imageIdList.get(i)).longValue(), ((Integer) orientList.get(i)).intValue()));
        }
        return items;
    }

    private boolean logGalleryFolders() {
        int bucketId;
        this.albumList = new ArrayList();
        List<Integer> bucketIdList = new ArrayList();
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        String[] strArr = new String[5];
        // cusros s hmm
        // phr kaisy karen
        // dekh len shaid aisy ho jaye
        // hmm array jo bhej rhy hn us m imageid int m save kr rhy hn wo bhe msla kr rhe h
        // ken k wo kehty hn string ki array h to parsing na kro

        String imageId = "_id";// ya kya ha  string ko integer ma pass kr rahy ho?
        // cursor m jo hmm array pass kr rhy hn us m kuch membr ko yahe imageid set kr rhy hn
        // ye int m h
        // lwken wo kehty hn strring ho

        strArr[0] = imageId;
        String str = "bucket_display_name";
        strArr[1] = str;
        String str2 = "bucket_id";
        strArr[2] = str2;
        strArr[3] = imageId;
        String str3 = "orientation";
        strArr[4] = str3;
       Cursor cur = context.getContentResolver().query(uri, strArr, "1) GROUP BY 1,(2", null, "date_modified DESC");
        String str4 = "";
        if (cur == null) {
        } else if (cur.moveToFirst()) {
            int id;
            int i;
            int bucketColumn = cur.getColumnIndex(str);
            bucketId = cur.getColumnIndex(str2);
            // wesy bhe imageid jo hmm nechy save kr rhy hn usi s value get kr rk is ki zrort
            // hamen nechy pary ge ya oper wala chaly ga yani
           int imageId1 = cur.getColumnIndex(imageId);
            int orientationColumnIndex = cur.getColumnIndex(str3);
            while (true) {
                Album album = new Album();
                id = cur.getInt(bucketId);
                album.ID = id;
                if (bucketIdList.contains(Integer.valueOf(id))) {
                    Album albumFromList = (Album) albumList.get(bucketIdList.indexOf(Integer.valueOf(album.ID)));
                    albumFromList.imageIdList.add(Long.valueOf(cur.getLong(imageId1)));
                    albumFromList.orientationList.add(Integer.valueOf(cur.getInt(orientationColumnIndex)));
                } else {
                    str2 = cur.getString(bucketColumn);
                    bucketIdList.add(Integer.valueOf(id));
                    album.name = str2;
                    album.imageIdForThumb = cur.getLong(imageId1);
                    album.imageIdList.add(Long.valueOf(album.imageIdForThumb));
                    albumList.add(album);
                    album.orientationList.add(Integer.valueOf(cur.getInt(orientationColumnIndex)));
                }
                if (!cur.moveToNext()) {
                    break;
                }
                i = imageId1;
            }
            List<GridViewItem> items = new ArrayList();
            id = 0;
            while (id < albumList.size()) {
                String str5 = ((Album) albumList.get(id)).name;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str4);
                stringBuilder.append(((Album) albumList.get(id)).imageIdList.size());
                bucketIdList = bucketIdList;
                i = imageId1;
                items.add(new GridViewItem(activity, str5, stringBuilder.toString(), true, ((Album) albumList.get(id)).imageIdForThumb, ((Integer) ((Album) albumList.get(id)).orientationList.get(0)).intValue()));
                id++;
                bucketIdList = bucketIdList;
                imageId1 = i;
            }
            i = imageId1;
            albumList.add(new Album());

            // wesy bhai ye jo hmm kr rhy hn in ki zrort h nechy ka ...
            // 2 different array hnn hmanen compare ka msla ho rha h
            //
           // yar ap na array wala kam ghlat kya hwa ha hum sursor sa folder ki id or name access
            //kry ga or us ko next activity ma pass kr day ga
            // ye apk decompile ki h us m wo kam h... decompile m kuch garbar to ho jati h
            // us ko set kr rha hn ....
//            bucketIdList = albumList;
            int i2 = 1;
            ( albumList.get(albumList.size() - 1)).gridItems = items;
           int bucketIdList2 = 0;
            /// /yahen bhe wahe kam ho ga na...
//            bucketlist ko int ly lety hn
            while (bucketIdList2 < albumList.size() - i2) {
                ((Album) albumList.get(bucketIdList2)).gridItems = createGridItemsOnClick(bucketIdList2);
                bucketIdList2++;
                i2 = 1;
            }
            return true;
        } else {
            bucketIdList = bucketIdList;
        }
        List<GridViewItem> items2 = new ArrayList();

        // mujy lagta h yahan p bhe hmm imageid ko intial krty hn int s
        // ken k ye for loop m use ho rhe h r albumlist tk us chala rhy hn
        // array m to hmm n strring m d h
        for (int imageId2 = 0; imageId2 < albumList.size(); imageId2++) {
            Activity activity2 = activity;
            str2 = ((Album) albumList.get(imageId2)).name;
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str4);
            stringBuilder2.append(((Album) albumList.get(imageId2)).imageIdList.size());
            items2.add(new GridViewItem(activity2, str2, stringBuilder2.toString(), true, ((Album) albumList.get(imageId2)).imageIdForThumb, ((Integer) ((Album) albumList.get(imageId2)).orientationList.get(0)).intValue()));
        }
        albumList.add(new Album());
        List list = albumList;
        bucketId = 1;
        ((Album) list.get(list.size() - 1)).gridItems = items2;
        int imageId3 = 0;
        // yahan bhe to mm while loop chala hy hn r r wahe alubm list k size tk chala rhy hn

        while (imageId3 < albumList.size() - bucketId) {
            ((Album) albumList.get(imageId3)).gridItems = createGridItemsOnClick(imageId3);
            imageId3++;
            bucketId = 1;
        }
        return true;
    }

    public void onBackPressed() {
        backtrace();
    }

    boolean backtrace() {
        if (this.isOnBucket) {
            GalleryListener galleryListener = this.galleryListener;
            if (galleryListener != null) {
                galleryListener.onGalleryCancel();
            }
            return true;
        }
        this.gridView.setNumColumns(3);
        MyGridAdapter myGridAdapter = this.adapter;
        List list = this.albumList;
        myGridAdapter.items = ((Album) list.get(list.size() - 1)).gridItems;
        this.adapter.notifyDataSetChanged();
        this.gridView.smoothScrollToPosition(0);
        this.isOnBucket = true;
        this.headerText.setText(getString(R.string.gallery_select_an_album));
        return false;
    }

    @SuppressLint({"WrongConstant"})
    public void onItemClick(AdapterView<?> adapterView, View arg1, int location, long arg3) {
        if (this.isOnBucket) {
            this.gridView.setNumColumns(3);
            this.adapter.items = ((Album) this.albumList.get(location)).gridItems;
            this.adapter.notifyDataSetChanged();
            this.gridView.smoothScrollToPosition(0);
            this.isOnBucket = false;
            this.selectedBucketId = location;
            this.headerText.setText(((Album) this.albumList.get(location)).name);
        } else if (this.footer.getChildCount() >= this.COLLAGE_IMAGE_LIMIT_MAX) {
            Toast msg = Toast.makeText(this.context, String.format(getString(R.string.gallery_no_more)+ new Object[]{Integer.valueOf(this.COLLAGE_IMAGE_LIMIT_MAX)}), 1);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
        } else {
            View retval = LayoutInflater.from(this.context).inflate(R.layout.footer_item, null);
            retval.findViewById(R.id.imageView_delete).setOnClickListener(this.onClickListener);
            ImageView im = (ImageView) retval.findViewById(R.id.imageView);
            int i = this.selectedBucketId;
            if (i >= 0 && i < this.albumList.size() && location >= 0 && location < ((Album) this.albumList.get(this.selectedBucketId)).imageIdList.size()) {
                long id = ((Long) ((Album) this.albumList.get(this.selectedBucketId)).imageIdList.get(location)).longValue();
                this.selectedImageIdList.add(Long.valueOf(id));
                this.selectedImageOrientationList.add(((Album) this.albumList.get(this.selectedBucketId)).orientationList.get(location));
                Bitmap bm = GalleryUtility.getThumbnailBitmap(this.context, id, ((Integer) ((Album) this.albumList.get(this.selectedBucketId)).orientationList.get(location)).intValue());
                if (bm != null) {
                    im.setImageBitmap(bm);
                }
                this.footer.addView(retval);
                TextView textView = this.deleteAllTv;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(");
                stringBuilder.append(this.footer.getChildCount());
                stringBuilder.append(")");
                textView.setText(stringBuilder.toString());
                GridViewItem gridViewItem = (GridViewItem) this.adapter.items.get(location);
                gridViewItem.selectedItemCount++;
                TextView text = (TextView) arg1.findViewById(R.id.textViewSelectedItemCount);
                stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(((GridViewItem) this.adapter.items.get(location)).selectedItemCount);
                text.setText(stringBuilder.toString());
                if (text.getVisibility() == 4) {
                    text.setVisibility(0);
                }
                if (this.collageSingleMode) {
                    photosSelectFinished();
                    this.collageSingleMode = false;
                }
            }
        }
    }

    Point findItemById(long id) {
        for (int i = 0; i < this.albumList.size() - 1; i++) {
            List<GridViewItem> list = ((Album) this.albumList.get(i)).gridItems;
            for (int j = 0; j < list.size(); j++) {
                if (((GridViewItem) list.get(j)).imageIdForThumb == id) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public int getLimitMin() {
        return this.COLLAGE_IMAGE_LIMIT_MIN;
    }

    void photosSelectFinished() {
        int size = this.selectedImageIdList.size();
        if (size <= this.COLLAGE_IMAGE_LIMIT_MIN) {
            Toast msg = Toast.makeText(this.context, String.format(getString(R.string.gallery_select_one)+ new Object[]{Integer.valueOf(getLimitMin() + 1)}), Toast.LENGTH_LONG);
            msg.setGravity(17, msg.getXOffset() / 2, msg.getYOffset() / 2);
            msg.show();
            return;
        }
        int i;
        long[] arrr = new long[size];
        for (i = 0; i < size; i++) {
            arrr[i] = ((Long) this.selectedImageIdList.get(i)).longValue();
        }
        int[] orientationArr = new int[size];
        for (i = 0; i < size; i++) {
            orientationArr[i] = ((Integer) this.selectedImageOrientationList.get(i)).intValue();
        }
        GalleryListener galleryListener = this.galleryListener;
        if (galleryListener != null) {
            galleryListener.onGalleryOkImageArray(arrr, orientationArr, this.isScrapBook, this.isShape, this.isMirror);
            return;
        }
        try {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
