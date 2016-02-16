package com.general.android.zhihudaily.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * author Geek_Soledad.
 */
public class URLImageParser implements Html.ImageGetter {
    TextView mTextView;

    public URLImageParser(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();
        ImageLoader.getInstance().loadImage(source, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                urlDrawable.bitmap = loadedImage;
                urlDrawable.setBounds(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
                mTextView.invalidate();
                mTextView.setText(mTextView.getText()); // 解决图文重叠
            }
        });
        return urlDrawable;
    }
}
