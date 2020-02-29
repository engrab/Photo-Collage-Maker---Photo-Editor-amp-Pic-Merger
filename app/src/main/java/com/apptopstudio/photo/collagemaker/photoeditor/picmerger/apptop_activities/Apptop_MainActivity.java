package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.Glob;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_adapters.SlidingImage_Adapter;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_bitmap.BitmapResizer;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.Apptop_CollageActivity;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_collagelib.CollageHelper;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_gallerylib.GalleryFragment;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_imagesavelib.ImageLoader;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_imagesavelib.ImageLoader.ImageLoaded;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_share.Apptop_MyCreationActivity;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.ImageModel;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.Utility;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.viewpagerindicator.CirclePageIndicator;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Apptop_MainActivity extends AppCompatActivity implements OnClickListener {
    public static final int RequestPermissionCode = 1;
    int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    String IMAGE_DIRECTORY_NAME = "PhotoCollageMaker";
    int MEDIA_TYPE_IMAGE = 1;
    int PERMISSION_CAMERA_EDITOR = 44;
    int PERMISSION_COLLAGE_EDITOR = 11;
    int PERMISSION_MIRROR_EDITOR = 55;
    int PERMISSION_SCRAPBOOK_EDITOR = 33;
    int PERMISSION_SINGLE_EDITOR = 22;
    int REQUEST_MIRROR = 3;
    private LinearLayout adExitChoicesContainer;
    private LinearLayout adExitView;
    private AdView adView;
    AlertDialog alertDialog = null;
    Uri fileUri;
    GalleryFragment galleryFragment;
    ImageLoader imageLoader;
    private long mBackPressed = 0;
    //    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    private InterstitialAd mInterstitialAd;
    RelativeLayout mMainLayout;
    ImageView mCameraLayout;
    ImageView mCollegeLayout;
    ImageView mMirrorLayout;
    ImageView mScrapbookLayout;
    ImageView mSingleEditorLayout;
    ImageView mMyCollection;
    ImageView mTemplates;
    AdView mAdView;
    ImageView navigationvbutton;
    private ArrayList<ImageModel> imageModelArrayList;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;


    private int[] myImageList = new int[]{R.drawable.slide_1, R.drawable.slide_2,
            R.drawable.slide_3, R.drawable.slide_4
            , R.drawable.slide_5, R.drawable.slide_6, R.drawable.slide_7, R.drawable.slide_8
            , R.drawable.slide_9, R.drawable.slide_10, R.drawable.slide_11};


    private int navigationPosition = 0;
    private ProgressBar progressBarExitRefresh;
    Toolbar toolbar;


    private class DrawerItemClickListener implements OnItemClickListener {
        private DrawerItemClickListener() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Apptop_MainActivity.this.selectItem(position);
        }
    }

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_activities.Apptop_MainActivity$1 */
    class C08711 implements ImageLoaded {
        C08711() {
        }

        public void callFileSizeAlertDialogBuilder() {
            Apptop_MainActivity.this.fileSizeAlertDialogBuilder();
        }
    }


    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        if (Glob.isOnline(this)) {
            mAdView = findViewById(R.id.adView);
            mAdView.loadAd(new AdRequest.Builder().build());
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mAdView.setVisibility(View.VISIBLE);
                }
            });
        }
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();
        init();
        navigationvbutton = findViewById(R.id.iv_navigation);

        navigationvbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(Apptop_MainActivity.this, navigationvbutton);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.navigation_drawer, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        switch (item.getItemId()) {

                            case R.id.menue_collage_maker:
                                if (VERSION.SDK_INT < 19) {
                                    openCollage(false, false, false);
                                } else if (checkAndRequestCollagePermissions()) {
                                    openCollage(false, false, false);
                                }

                                break;
                            case R.id.menue_layout:
                                if (VERSION.SDK_INT < 19) {
                                    Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_LayoutActivity.class));
                                } else if (checkAndRequestCollagePermissions()) {
                                    Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_LayoutActivity.class));
                                }

                                break;
                            case R.id.menue_edit_image:
                                if (VERSION.SDK_INT < 19) {
                                    openCollage(true, false, false);
                                } else if (checkAndRequestSinglePermissions()) {
                                    openCollage(true, false, false);
                                }

                                break;
                            case R.id.menue_saved_images:
                                if (VERSION.SDK_INT < 19) {
                                    Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_MyCreationActivity.class));
                                } else if (checkAndRequestCollagePermissions()) {
                                    Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_MyCreationActivity.class));
                                }

                                break;

                            case R.id.menue_share_app:
                                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                                if (sharingIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                                }
                                break;
                            case R.id.menue_rate_us:
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                        startActivity(intent);
                                    }
                                } catch (Exception ignored) {
                                }
                                break;
                            case R.id.menue_privacy_policy:
                                startActivity(new Intent(Apptop_MainActivity.this, Apptop_PrivacyPolicyActivity.class));
                                break;

                        }

                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });//closing the setOnClickListener method

//        this.mDrawerLayout = findViewById(R.id.drawer_layout);
//        this.mDrawerList = findViewById(R.id.left_drawer);
//        setupToolbar();
//        DrawerItem[] drawerItem = new DrawerItem[]{new DrawerItem(R.drawable.ic_rate, getResources().getString(R.string.drawer_title_ratting)), new DrawerItem(R.drawable.ic_share, getResources().getString(R.string.drawer_title_share_friend)), new DrawerItem(R.drawable.ic_more_apps, getResources().getString(R.string.drawer_title_more_apps)), new DrawerItem(R.drawable.ic_privacy, getResources().getString(R.string.drawer_title_privacy))};
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        this.mDrawerList.addHeaderView(getLayoutInflater().inflate(R.layout.navigation_header_row, null, false));
//        this.mDrawerList.setAdapter(new DrawerItemCustomAdapter(this, drawerItem));
//        this.mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//        this.mDrawerLayout = findViewById(R.id.drawer_layout);
//        this.mDrawerLayout.setDrawerListener(this.mDrawerToggle);
//        setupDrawerToggle();
        getWindow().addFlags(1024);
        findViewbyIds();
        this.imageLoader = new ImageLoader(this);
        this.imageLoader.setListener(new C08711());


    }

    private void init() {

        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(Apptop_MainActivity.this, imageModelArrayList));

        CirclePageIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);

            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }

    void setupToolbar() {
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void selectItem(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        this.navigationPosition = position;
        if (position == 1 || position == 2) {
            ShowAfterAdNavigationOption();
        } else if (position == 3 || position == 4) {
            ShowAfterAdNavigationOption();
        }
//        this.mDrawerLayout.closeDrawers();
    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        this.mDrawerToggle.syncState();
    }

//    void setupDrawerToggle() {
//        this.mDrawerToggle = new ActionBarDrawerToggle(this, this.mDrawerLayout, this.toolbar, R.string.app_name, R.string.app_name);
//        this.mDrawerToggle.syncState();
//    }

    private void ShowAfterAdNavigationOption() {
        String str = "https://play.google.com/store/apps/details?id=";
        int i = this.navigationPosition;
        String str2 = "android.intent.action.VIEW";
        StringBuilder stringBuilder;
        if (i == 1) {
            if (Glob.isOnline(this)) {
                try {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(getPackageName());
                    startActivity(new Intent(str2, Uri.parse(stringBuilder.toString())));
                } catch (ActivityNotFoundException e) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(str);
                    stringBuilder2.append(getPackageName());
                    startActivity(new Intent(str2, Uri.parse(stringBuilder2.toString())));
                }
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Available", Toast.LENGTH_SHORT).show();
            }
        } else if (i != 2) {
            CharSequence charSequence = "No Internet Connection..";
            if (i != 3) {
                if (i == 4) {
                    if (Glob.isOnline(this)) {
                        startActivity(new Intent(getApplicationContext(), Apptop_PrivacyPolicyActivity.class));
                    } else {
                        Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show();
                    }
                }
            } else if (Glob.isOnline(this)) {
                try {
                    startActivity(new Intent(str2, Uri.parse(Glob.acc_link)));
                } catch (ActivityNotFoundException e2) {
                    Toast.makeText(this, "You don't have Google Play installed", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, charSequence, Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.setType("text/*");
            stringBuilder = new StringBuilder();
            stringBuilder.append(getResources().getString(R.string.app_name));
            stringBuilder.append(" Created By :https://play.google.com/store/apps/details?id=");
            stringBuilder.append(getPackageName());
            shareIntent.putExtra("android.intent.extra.TEXT", stringBuilder.toString());
            startActivity(Intent.createChooser(shareIntent, "Share App"));
        }
    }

    private void findViewbyIds() {
        this.mSingleEditorLayout = findViewById(R.id.iv_edite);
        this.mCameraLayout = findViewById(R.id.iv_camera);
        this.mCollegeLayout = findViewById(R.id.iv_collage);
        this.mMirrorLayout = findViewById(R.id.iv_mirror);
        this.mScrapbookLayout = findViewById(R.id.iv_scrapbook);
        this.mMyCollection = findViewById(R.id.iv_mycollection);
        this.mTemplates = findViewById(R.id.iv_layout);
        this.mTemplates.setOnClickListener(this);
        this.mMyCollection.setOnClickListener(this);
        this.mSingleEditorLayout.setOnClickListener(this);
        this.mCameraLayout.setOnClickListener(this);
        this.mCollegeLayout.setOnClickListener(this);
        this.mMirrorLayout.setOnClickListener(this);
        this.mScrapbookLayout.setOnClickListener(this);
    }

    private void fileSizeAlertDialogBuilder() {
        Point p = BitmapResizer.decodeFileSize(new File(this.imageLoader.selectedImagePath), Utility.maxSizeForDimension(this, 1, 1500.0f));
        if (p != null) {
            if (p.x == -1) {
                startShaderActivity();
                return;
            }
        }
        startShaderActivity();
    }

    private void startShaderActivity() {
        Log.e("MainActivity.startShade", this.imageLoader.selectedImagePath);
        int maxSize = Utility.maxSizeForDimension(this, 1, 1500.0f);
        Intent shaderIntent = new Intent(getApplicationContext(), Apptop_MirrorNewActivity.class);
        shaderIntent.putExtra("selectedImagePath", this.imageLoader.selectedImagePath);
        shaderIntent.putExtra("isSession", false);
        shaderIntent.putExtra("MAX_SIZE", maxSize);
        Utility.logFreeMemory(this);
        startActivity(shaderIntent);
    }

    @SuppressLint({"WrongConstant"})
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CharSequence charSequence = "Permission granted";
        CharSequence charSequence2 = "Permission denied";
        if (requestCode == this.PERMISSION_COLLAGE_EDITOR) {
            if (ContextCompat.checkSelfPermission(this, permissions[0]) == 0) {
                openCollage(false, false, false);
                Toast.makeText(this, charSequence, 0).show();
                return;
            }
            Toast.makeText(this, charSequence2, 0).show();
        } else if (requestCode == this.PERMISSION_SINGLE_EDITOR) {
            if (ContextCompat.checkSelfPermission(this, permissions[0]) == 0) {
                openCollage(true, false, false);
                Toast.makeText(this, charSequence, 0).show();
                return;
            }
            Toast.makeText(this, charSequence2, 0).show();
        } else if (requestCode == this.PERMISSION_SCRAPBOOK_EDITOR) {
            if (ContextCompat.checkSelfPermission(this, permissions[0]) == 0) {
                openCollage(false, true, false);
                Toast.makeText(this, charSequence, 0).show();
                return;
            }
            Toast.makeText(this, charSequence2, 0).show();
        } else if (requestCode == this.PERMISSION_CAMERA_EDITOR) {
            if (ContextCompat.checkSelfPermission(this, permissions[0]) == 0) {

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                this.fileUri = getOutputMediaFileUri(this.MEDIA_TYPE_IMAGE);
                intent.putExtra("output", this.fileUri);
                startActivityForResult(intent, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
                Toast.makeText(this, charSequence, 0).show();
                return;
            }
            Toast.makeText(this, charSequence2, 0).show();
        } else if (requestCode == this.PERMISSION_MIRROR_EDITOR) {
            if (ContextCompat.checkSelfPermission(this, permissions[0]) == 0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction("android.intent.action.GET_CONTENT");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), this.REQUEST_MIRROR);
                Toast.makeText(this, charSequence, 0).show();
                return;
            }
            Toast.makeText(this, charSequence2, 0).show();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri", this.fileUri);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.fileUri = savedInstanceState.getParcelable("file_uri");
    }

    @SuppressLint({"WrongConstant"})
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (requestCode == this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
                if (resultCode == -1) {
                    Intent localIntent = new Intent(this, Apptop_CollageActivity.class);
                    PrintStream printStream = System.out;
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("CAMERA IMAGE PATH");
                    stringBuilder.append(this.fileUri.getPath());
                    printStream.println(stringBuilder.toString());
                    localIntent.putExtra("selected_image_path", this.fileUri.getPath());
                    startActivity(localIntent);
                } else if (resultCode == 0) {
                    Toast.makeText(getApplicationContext(), "No Image Captured", 0).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry! Failed to capture image", 0).show();
                }
            } else if (resultCode == -1 && requestCode == this.REQUEST_MIRROR) {
                try {
                    this.imageLoader.getImageFromIntent(data);
                } catch (Exception e) {
                    e.printStackTrace();
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("");
                    stringBuilder2.append(getString(R.string.error_img_not_found));
                    Toast.makeText(this, stringBuilder2.toString(), 0).show();
                }
            }
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
    }

    void loadInterstitialAd() {
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
//                loadInterstitialAd();
            }
        });
    }

    void showInterstitialAd() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }


    @SuppressLint({"WrongConstant"})
    public void onClick(View v) {
        if (this.mTemplates == v) {

            if (VERSION.SDK_INT < 19) {
                loadInterstitialAd();
                showInterstitialAd();
                Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_LayoutActivity.class));
            } else if (checkAndRequestCollagePermissions()) {
                loadInterstitialAd();
                showInterstitialAd();
                Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_LayoutActivity.class));
            }
        }
        if (this.mMyCollection == v) {

            if (VERSION.SDK_INT < 19) {
                loadInterstitialAd();
                showInterstitialAd();
                Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_MyCreationActivity.class));
            } else if (checkAndRequestCollagePermissions()) {
                loadInterstitialAd();
                showInterstitialAd();
                Apptop_MainActivity.this.startActivity(new Intent(Apptop_MainActivity.this.getApplicationContext(), Apptop_MyCreationActivity.class));
            }
        }
        if (this.mCollegeLayout == v) {
            if (VERSION.SDK_INT < 19) {
                loadInterstitialAd();
                showInterstitialAd();
                openCollage(false, false, false);
            } else if (checkAndRequestCollagePermissions()) {
                loadInterstitialAd();
                showInterstitialAd();
                openCollage(false, false, false);
            }
        }
        if (this.mSingleEditorLayout == v) {
            if (VERSION.SDK_INT < 19) {
                loadInterstitialAd();
                showInterstitialAd();
                openCollage(true, false, false);
            } else if (checkAndRequestSinglePermissions()) {
                loadInterstitialAd();
                showInterstitialAd();
                openCollage(true, false, false);
            }
        }
        if (this.mScrapbookLayout == v) {
            if (VERSION.SDK_INT < 19) {
                loadInterstitialAd();
                showInterstitialAd();
                openCollage(false, true, false);
            } else if (checkAndRequestScrapbookPermissions()) {
                loadInterstitialAd();
                showInterstitialAd();
                openCollage(false, true, false);
            }
        }
        if (this.mCameraLayout == v) {
            String str = "output";
            String str2 = "android.media.action.IMAGE_CAPTURE";
            Intent intent;
            if (VERSION.SDK_INT < 21) {
                intent = new Intent(str2);
                this.fileUri = getOutputMediaFileUri(this.MEDIA_TYPE_IMAGE);
                intent.putExtra(str, this.fileUri);
                startActivityForResult(intent, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            } else if (checkAndRequestCameraPermissions()) {

                intent = new Intent(str2);
                this.fileUri = getOutputMediaFileUri(this.MEDIA_TYPE_IMAGE);
                intent.putExtra(str, this.fileUri);
                startActivityForResult(intent, this.CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
            }
        }
        if (this.mMirrorLayout != v) {
            return;
        }
        if (VERSION.SDK_INT < 19) {
            loadInterstitialAd();
            showInterstitialAd();

            openCollage(true, true, false, true);
        } else if (checkAndRequestMirrorPermissions()) {
            loadInterstitialAd();
            showInterstitialAd();

            openCollage(true, true, false, true);
        }
    }

    @SuppressLint({"WrongConstant"})
    public boolean isAvailable(Intent intent) {
        return getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public void openCollage(boolean isblur, boolean isScrapBook, boolean isShape) {
        openCollage(isblur, isScrapBook, isShape, false);
    }

    public void openCollage(boolean isblur, boolean isScrapBook, boolean isShape, boolean isMirror) {
        this.galleryFragment = CollageHelper.addGalleryFragment(this, R.id.gallery_fragment_container, null, true, null);
        this.galleryFragment.setCollageSingleMode(isblur);
        this.galleryFragment.setIsMirrorSelector(isMirror);
        this.galleryFragment.setIsScrapbook(isScrapBook);
        this.galleryFragment.setIsShape(isShape);
        if (!isScrapBook) {
            this.galleryFragment.setLimitMax(GalleryFragment.MAX_COLLAGE);
        }
    }

    public void onBackPressed() {
        GalleryFragment localGalleryFragment = CollageHelper.getGalleryFragment(this);
        if (localGalleryFragment != null) {
            if (localGalleryFragment.isVisible()) {
                localGalleryFragment.onBackPressed();
                return;
            }
        }
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
            ((TextView) view.findViewById(R.id.dialog_heading)).setText("Want to Exit?");
            ((TextView) view.findViewById(R.id.exit_app)).setText("Yes");
            ((TextView) view.findViewById(R.id.cancel_app)).setText("No");
            view.findViewById(R.id.exit_app).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    finish();
                    alertDialog.dismiss();
                }
            });
            view.findViewById(R.id.cancel_app).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            this.alertDialog = alertDialogBuilder.create();
            this.alertDialog.setCancelable(false);
            this.alertDialog.show();
        } catch (Exception e) {
        }

    }

    public Uri getOutputMediaFileUri(int type) {
        try {
            return Uri.fromFile(createImageFile(type));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private File createImageFile(int type) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        StringBuilder imageFileName = new StringBuilder();
        imageFileName.append("JPEG_");
        imageFileName.append(timeStamp);
        imageFileName.append("_");
        return File.createTempFile(imageFileName.toString(), ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
    }

    private boolean checkAndRequestCollagePermissions() {
        String str = "android.permission.CAMERA";
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        String str2 = "android.permission.READ_EXTERNAL_STORAGE";
        int storagePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        String str3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        int storagePermission1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionsNeeded = new ArrayList();
        if (storagePermission != 0) {
            listPermissionsNeeded.add(str2);
        }
        if (storagePermission1 != 0) {
            listPermissionsNeeded.add(str3);
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add(str);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_COLLAGE_EDITOR);
        return false;
    }

    private boolean checkAndRequestSinglePermissions() {
        String str = "android.permission.CAMERA";
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, str);
        String str2 = "android.permission.READ_EXTERNAL_STORAGE";
        int storagePermission = ContextCompat.checkSelfPermission(this, str2);
        String str3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        int storagePermission1 = ContextCompat.checkSelfPermission(this, str3);
        List<String> listPermissionsNeeded = new ArrayList();
        if (storagePermission != 0) {
            listPermissionsNeeded.add(str2);
        }
        if (storagePermission1 != 0) {
            listPermissionsNeeded.add(str3);
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add(str);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_SINGLE_EDITOR);
        return false;
    }

    private boolean checkAndRequestScrapbookPermissions() {
        String str = "android.permission.CAMERA";
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, str);
        String str2 = "android.permission.READ_EXTERNAL_STORAGE";
        int storagePermission = ContextCompat.checkSelfPermission(this, str2);
        String str3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        int storagePermission1 = ContextCompat.checkSelfPermission(this, str3);
        List<String> listPermissionsNeeded = new ArrayList();
        if (storagePermission != 0) {
            listPermissionsNeeded.add(str2);
        }
        if (storagePermission1 != 0) {
            listPermissionsNeeded.add(str3);
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add(str);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_SCRAPBOOK_EDITOR);
        return false;
    }

    private boolean checkAndRequestCameraPermissions() {
        String str = "android.permission.CAMERA";
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, str);
        String str2 = "android.permission.READ_EXTERNAL_STORAGE";
        int storagePermission = ContextCompat.checkSelfPermission(this, str2);
        String str3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        int storagePermission1 = ContextCompat.checkSelfPermission(this, str3);
        List<String> listPermissionsNeeded = new ArrayList();
        if (storagePermission != 0) {
            listPermissionsNeeded.add(str2);
        }
        if (storagePermission1 != 0) {
            listPermissionsNeeded.add(str3);
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add(str);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_CAMERA_EDITOR);
        return false;
    }

    private boolean checkAndRequestMirrorPermissions() {
        String str = "android.permission.CAMERA";
        int permissionCAMERA = ContextCompat.checkSelfPermission(this, str);
        String str2 = "android.permission.READ_EXTERNAL_STORAGE";
        int storagePermission = ContextCompat.checkSelfPermission(this, str2);
        String str3 = "android.permission.WRITE_EXTERNAL_STORAGE";
        int storagePermission1 = ContextCompat.checkSelfPermission(this, str3);
        List<String> listPermissionsNeeded = new ArrayList();
        if (storagePermission != 0) {
            listPermissionsNeeded.add(str2);
        }
        if (storagePermission1 != 0) {
            listPermissionsNeeded.add(str3);
        }
        if (permissionCAMERA != 0) {
            listPermissionsNeeded.add(str);
        }
        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), this.PERMISSION_MIRROR_EDITOR);
        return false;
    }


    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
//        if (MyCreationAdapter.imagegallary.size() == 0) {
//            findViewById(R.id.text_noimage).setVisibility(View.VISIBLE);
//        } else {
//            findViewById(R.id.text_noimage).setVisibility(View.GONE);
//        }
    }


    @Override
    protected void onPause() {

        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


}
