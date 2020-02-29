package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_bitmap.BitmapLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.io.File;

public class Apptop_ImageShareActivity extends AppCompatActivity implements OnClickListener {
    private ImageView back;
    private Bundle bundle;
    private String imagePath;
    private ImageView ivFacebook;
    private ImageView ivFinalImage;
    private ImageView ivInstagram;
    private ImageView ivShareMore;
    private ImageView ivWhatsApp;

    private class BitmapWorkerTask extends AsyncTask<Void, Void, Bitmap> {
        BitmapLoader bitmapLoader = new BitmapLoader();
        DisplayMetrics metrics;

        public BitmapWorkerTask() {
            File file = new File(Apptop_ImageShareActivity.this.imagePath);
            this.metrics = Apptop_ImageShareActivity.this.getResources().getDisplayMetrics();
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Bitmap doInBackground(Void... arg0) {
            try {
                return this.bitmapLoader.load(Apptop_ImageShareActivity.this.getApplicationContext(), new int[]{this.metrics.widthPixels, this.metrics.heightPixels}, Apptop_ImageShareActivity.this.imagePath);
            } catch (Exception e) {
                return null;
            }
        }

        @SuppressLint({"WrongConstant"})
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                Apptop_ImageShareActivity.this.ivFinalImage.setImageBitmap(bitmap);
                return;
            }
            Context context = Apptop_ImageShareActivity.this;
            Toast.makeText(context, context.getString(R.string.error_img_not_found), 0).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        this.bundle = getIntent().getExtras();
        Bundle bundle = this.bundle;
        if (bundle != null) {
            this.imagePath = bundle.getString("imagePath");
        }
        getWindow().setFlags(1024, 1024);
        bindView();
        new BitmapWorkerTask().execute();
        if (isOnline()) {
            AdView adview = new AdView(this);
            adview.setAdSize(AdSize.LARGE_BANNER);
            adview.setAdUnitId(getString(R.string.banner_ad_id));
            ((RelativeLayout) findViewById(R.id.adView)).addView(adview);
            adview.loadAd(new AdRequest.Builder().build());
        }
    }

    private void bindView() {
        this.ivFinalImage = findViewById(R.id.ivFinalImage);
        this.ivFinalImage.setOnClickListener(this);
        this.ivWhatsApp = findViewById(R.id.iv_whatsapp);
        this.ivWhatsApp.setOnClickListener(this);
        this.ivFacebook = findViewById(R.id.iv_facebook);
        this.ivFacebook.setOnClickListener(this);
        this.ivInstagram = findViewById(R.id.iv_instagram);
        this.ivInstagram.setOnClickListener(this);
        this.ivShareMore = findViewById(R.id.iv_Share_More);
        this.ivShareMore.setOnClickListener(this);
        this.back = findViewById(R.id.back);
        this.back.setOnClickListener(this);
    }

    public boolean isOnline() {
        NetworkInfo netInfo = ((ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (netInfo != null) {
            return netInfo.isConnectedOrConnecting();
        }
        return false;
    }

    public void onClick(View view) {
//        Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID, new File(this.imagePath));
        File imageFileToShare = new File(imagePath);
        Uri contentUri = Uri.fromFile(imageFileToShare);
        String str = "android.intent.action.SEND";
        Intent shareIntent = new Intent(str);
        String str2 = "image/*";
        shareIntent.setType(str2);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Download this amazing app\n\nPhoto Collage Maker - Collage Photo Editor Created By : ");
        StringBuilder stringBuilder2 = new StringBuilder();
        String str3 = "https://play.google.com/store/apps/details?id=";
        stringBuilder2.append(str3);
        stringBuilder2.append(getPackageName());
        stringBuilder.append(Uri.parse(stringBuilder2.toString()));
        String str4 = "android.intent.extra.TEXT";
        shareIntent.putExtra(str4, stringBuilder.toString());
        String str5 = "android.intent.extra.STREAM";
        shareIntent.putExtra(str5, contentUri);
        int id = view.getId();
        if (id != R.id.back) {
            switch (id) {
                case R.id.iv_Share_More:
                    Intent sharingIntent = new Intent(str);
                    sharingIntent.setType(str2);
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("Download this amazing app\n\nPhoto Collage Maker - Collage Photo Editor Create By : ");
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append(str3);
                    stringBuilder4.append(getPackageName());
                    stringBuilder3.append(Uri.parse(stringBuilder4.toString()));
                    sharingIntent.putExtra(str4, stringBuilder3.toString());
                    sharingIntent.putExtra(str5, contentUri);
                    startActivity(Intent.createChooser(sharingIntent, "Share Image using"));
                    return;
                case R.id.iv_facebook:
                    try {
                        shareIntent.setPackage("com.facebook.katana");
                        startActivity(shareIntent);
                        return;
                    } catch (Exception e) {
                        Toast.makeText(this, "Facebook not installed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                case R.id.iv_instagram:
                    try {
                        shareIntent.setPackage("com.instagram.android");
                        startActivity(shareIntent);
                        return;
                    } catch (Exception e2) {
                        Toast.makeText(this, "Instagram not installed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                case R.id.iv_whatsapp:
                    try {
                        shareIntent.setPackage("com.whatsapp");
                        startActivity(shareIntent);
                        return;
                    } catch (Exception e3) {
                        Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
                        return;
                    }
                default:
                    return;
            }
        }
        super.onBackPressed();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void onResume() {
        super.onResume();
    }
}
