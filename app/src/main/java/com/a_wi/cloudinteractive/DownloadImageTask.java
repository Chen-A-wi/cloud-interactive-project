package com.a_wi.cloudinteractive;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView mBmImage;
    String mId;
    Cache mCache;

    public DownloadImageTask(ImageView bmImage, String id, Cache cache) {
        this.mBmImage = bmImage;
        this.mId = id;
        this.mCache = cache;
    }

    //異步背景執行
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap result) {
        mBmImage.setImageBitmap(result);
        mCache.addBitmapToMemoryCache(mId,result);
    }
}
