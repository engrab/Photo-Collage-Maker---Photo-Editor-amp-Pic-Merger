package com.apptopstudio.photo.collagemaker.photoeditor.picmerger.apptop_utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import com.google.android.exoplayer2.util.MimeTypes;

@SuppressLint({"NewApi"})
public class UriToUrl {
    public static final String AUTHORITY = "com.techhub.photoeffectseditor.localstorage.documents";
    public static String[] split;

    public static String get(Context context, Uri uri) {
        boolean isKitKat;
        if (VERSION.SDK_INT >= 19) {
            isKitKat = true;
        } else {
            isKitKat = false;
        }
        if (!isKitKat || !DocumentsContract.isDocumentUri(context, uri)) {
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        } else if (isLocalStorageDocument(uri)) {
            return DocumentsContract.getDocumentId(uri);
        } else {
            String str = ":";
            if (isExternalStorageDocument(uri)) {
                split = DocumentsContract.getDocumentId(uri).split(str);
                if (!"primary".equalsIgnoreCase(split[0])) {
                    return null;
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(Environment.getExternalStorageDirectory());
                stringBuilder.append("/");
                stringBuilder.append(split[1]);
                return stringBuilder.toString();
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
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
                return getDataColumn(context, contentUri, "_id=?", new String[]{split[1]});
            }
        }
    }

    public static boolean isLocalStorageDocument(Uri uri) {
        return AUTHORITY.equals(uri.getAuthority());
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

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String str = "_data";
        String str2 = null;
        Cursor cursor = null;
        String column = "_data";
        try {
            cursor = context.getContentResolver().query(uri, new String[]{str}, selection, selectionArgs, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    str2 = cursor.getString(cursor.getColumnIndexOrThrow(str));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return str2;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            return str2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }
}
