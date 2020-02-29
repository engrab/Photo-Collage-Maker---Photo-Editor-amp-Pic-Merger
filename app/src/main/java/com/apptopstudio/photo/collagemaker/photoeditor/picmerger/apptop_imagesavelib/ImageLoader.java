package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_imagesavelib;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.util.Log;

import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.R;
import com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils.UriToUrl;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageLoader {
    String TAG = "ImageLoader";
    Context context;
    int count = 0;
    Cursor cursorBackup;
    public String filemanagerstring;
    ImageLoaded imageLoadedListener;
    String loadImageMessage = "Loading image!";
    public String selectedImagePath;

    /* renamed from: com.apptopstudio.photo.collagemaker.photoeditor.picmerger.imagesavelib.ImageLoader$1 */
    class C03421 implements OnClickListener {
        C03421() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    }

    public interface ImageLoaded {
        void callFileSizeAlertDialogBuilder();
    }

    private class LoadImage19Task extends AsyncTask<Uri, Void, Void> {
        String path;
        ProgressDialog saveImageDialog;
        Uri uri;

        private LoadImage19Task() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            ImageLoader imageLoader = ImageLoader.this;
            imageLoader.loadImageMessage = imageLoader.context.getString(R.string.loading_image);
            try {
                this.saveImageDialog = new ProgressDialog(ImageLoader.this.context);
                this.saveImageDialog.setMessage(ImageLoader.this.loadImageMessage);
                this.saveImageDialog.show();
            } catch (Exception e) {
            }
        }

        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            ImageLoader imageLoader = ImageLoader.this;
            imageLoader.selectedImagePath = this.path;
            imageLoader.startActivityFromUri(this.uri);
            try {
                this.saveImageDialog.dismiss();
            } catch (Exception e) {
            }
        }

        protected Void doInBackground(Uri... arg0) {
            if (arg0 != null) {
                try {
                    this.uri = arg0[0];
                    if (this.uri != null) {
                        this.path = ImageLoader.this.getRealPathFromURI19(this.uri);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public void setListener(ImageLoaded l) {
        this.imageLoadedListener = l;
    }

    public ImageLoader(Context context) {
        this.context = context;
    }

    public void getImageFromIntent(Intent intent) {
        Uri selectedImageUri = intent.getData();
        if (selectedImageUri == null) {
            selectedImageUri = (Uri) intent.getExtras().get("android.intent.extra.STREAM");
        }
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a");
        stringBuilder.append(selectedImageUri);
        Log.e(str, stringBuilder.toString());
        if (VERSION.SDK_INT >= 19) {
            this.selectedImagePath = getPathForKitKat(selectedImageUri);
            Log.e(this.TAG, "b");
            str = this.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("getPathForKitKat ");
            stringBuilder.append(this.selectedImagePath);
            Log.e(str, stringBuilder.toString());
            if (this.selectedImagePath == null) {
                new LoadImage19Task().execute(new Uri[]{selectedImageUri});
                return;
            }
            startActivityFromUri(selectedImageUri);
            return;
        }
        this.selectedImagePath = getRealPathFromURI(selectedImageUri);
        str = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("getImageFromIntent selectedImagePath  ");
        stringBuilder.append(this.selectedImagePath);
        Log.e(str, stringBuilder.toString());
        startActivityFromUri(selectedImageUri);
    }

    void startActivityFromUri(Uri selectedImageUri) {
        this.filemanagerstring = selectedImageUri.getPath();
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("startActivityFromUri selectedImagePath");
        stringBuilder.append(this.selectedImagePath);
        Log.w(str, stringBuilder.toString());
        if (this.selectedImagePath == null) {
            this.selectedImagePath = this.filemanagerstring;
            str = this.TAG;
            stringBuilder = new StringBuilder();
            stringBuilder.append("null selectedImagePath ");
            stringBuilder.append(this.selectedImagePath);
            Log.w(str, stringBuilder.toString());
        }
        str = this.selectedImagePath;
        if (!(str == null || str.length() == 0)) {
            if (!this.selectedImagePath.toLowerCase().contains("http")) {
                if (checkFileExtension(this.selectedImagePath)) {
                    this.imageLoadedListener.callFileSizeAlertDialogBuilder();
                    return;
                }
                Builder builder = new Builder(this.context);
                builder.setMessage("Image Format Error").setCancelable(false).setNegativeButton("Ok", new C03421());
                builder.create().show();
                return;
            }
        }
        new LoadImage19Task().execute(new Uri[]{selectedImageUri});
        this.count++;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = new String[1];
        String column_index = "_data";
        proj[0] = column_index;
        Cursor cursor = this.context.getContentResolver().query(contentUri, proj, null, null, null);
        try {
            if (cursor.moveToFirst()) {
                res = cursor.getString(cursor.getColumnIndexOrThrow(column_index));
            }
            cursor.close();
        } catch (Exception e) {
        }
        if (cursor == null || res == null) {
            this.cursorBackup = this.context.getContentResolver().query(contentUri, proj, null, null, null);
            try {
                if (this.cursorBackup != null) {
                    column_index = String.valueOf(this.cursorBackup.getColumnIndexOrThrow(column_index));
                    this.cursorBackup.moveToFirst();
                    res = this.cursorBackup.getString(Integer.parseInt(column_index));
                }
            } catch (Exception e2) {
            }
        }
        return res;
    }

    public String getRealPathFromURI19(Uri contentUri) throws IOException {
        return saveImageToTemp(getBitmapFromUri(contentUri));
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = this.context.getContentResolver().openFileDescriptor(uri, "r");
        Bitmap image = BitmapFactory.decodeFileDescriptor(parcelFileDescriptor.getFileDescriptor());
        parcelFileDescriptor.close();
        return image;
    }

    private String saveImageToTemp(Bitmap bitmap) throws FileNotFoundException {
        StringBuilder resultPath = new StringBuilder(String.valueOf(ImageUtility.getPrefferredDirectoryPath(this.context, false, true, false)));
        resultPath.append("temp/dump.dump");

        File f = new File(resultPath.toString());
        f.getParentFile().mkdirs();
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("resultPath ");
        stringBuilder.append(resultPath);
        Log.i(str, stringBuilder.toString());
        bitmap.compress(CompressFormat.JPEG, 90, new FileOutputStream(resultPath.toString()));
        bitmap.recycle();
        str = this.TAG;
        stringBuilder = new StringBuilder();
        stringBuilder.append("is file exist ");
        stringBuilder.append(f.exists());
        Log.i(str, stringBuilder.toString());
        return resultPath.toString();
    }

    private String getFileExtension(String str) {
        if (str == null) {
            str = "";
        }
        int dotPos = str.lastIndexOf(".");
        String extension = "";
        if (dotPos > 0) {
            return str.substring(dotPos);
        }
        return extension;
    }

    private boolean checkFileExtension(String str) {
        String extension = getFileExtension(str).toLowerCase();
        if (!(extension.contains("jpg") || extension.contains("png") || extension.contains("jpeg") || extension.contains("gif") || extension.contains("bmp") || extension.contains("webp"))) {
            if (!extension.contains("dump")) {
                return false;
            }
        }
        return true;
    }

    public void closeCursor() {
        Cursor cursor = this.cursorBackup;
        if (cursor != null) {
            cursor.close();
        }
    }

    @SuppressLint({"NewApi"})
    public String getPathForKitKat(Uri uri) {
        boolean isKitKat;
        if (VERSION.SDK_INT >= 19) {
            isKitKat = true;
        } else {
            isKitKat = false;
        }
        if (isKitKat && DocumentsContract.isDocumentUri(this.context, uri)) {
            String str = ":";
            if (isExternalStorageDocument(uri)) {
                UriToUrl.split = DocumentsContract.getDocumentId(uri).split(str);
                if (!"primary".equalsIgnoreCase(UriToUrl.split[0])) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Environment.getExternalStorageDirectory());
                stringBuilder.append("/");
                stringBuilder.append(UriToUrl.split[1]);
                return stringBuilder.toString();
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(this.context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else {
                if (!isMediaDocument(uri)) {
                    return null;
                }
                String type = DocumentsContract.getDocumentId(uri).split(str)[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = Media.EXTERNAL_CONTENT_URI;
                } else if (MimeTypes.BASE_TYPE_VIDEO.equals(type)) {
                    contentUri = Video.Media.EXTERNAL_CONTENT_URI;
                } else if (MimeTypes.BASE_TYPE_AUDIO.equals(type)) {
                    contentUri = Audio.Media.EXTERNAL_CONTENT_URI;
                }
                str = "_id=?";
                return getDataColumn(this.context, contentUri, "_id=?", new String[]{UriToUrl.split[1]});
            }
        }
        if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String str = "_data";
        Cursor cursor = null;
        String column = "_data";
        try {
            cursor = context.getContentResolver().query(uri, new String[]{str}, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                DatabaseUtils.dumpCursor(cursor);
                str = cursor.getString(cursor.getColumnIndexOrThrow(str));
                if (cursor == null) {
                    return str;
                }
                cursor.close();
                return str;
            } else if (cursor == null) {
                return null;
            } else {
                cursor.close();
                return null;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            return column;
        }
    }
}
