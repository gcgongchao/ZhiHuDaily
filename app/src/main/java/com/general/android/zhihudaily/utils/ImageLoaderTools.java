package com.general.android.zhihudaily.utils;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by GeneralAndroid
 */

public class ImageLoaderTools{

    private static ImageLoaderTools mImageLoaderWrapper;
    private static ImageLoader mImageLoader;
    private static final int DISK_CACHE_SIZE_BYTES = 50 * 1024 * 1024;
    private static final int MEMORY_CACHE_SIZE_BYTES = 2 * 1024 * 1024;

    private ImageLoaderTools(Context context){
        setImageLoader(initImageLoader(context));
    }

    public static ImageLoaderTools getInstance(Context context){
        if(mImageLoaderWrapper == null){
            mImageLoaderWrapper = new ImageLoaderTools(context);
            return mImageLoaderWrapper;
        }else{
            return mImageLoaderWrapper;
        }
    }

    //返回�??个ImageLoader对象
    private static ImageLoader initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存�??
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
//                .showStubImage(R.drawable.friends_sends_pictures_no)//设置下载期间显示的图�?
//                .showImageForEmptyUri(R.drawable.friends_sends_pictures_no)//设置图片URI为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.friends_sends_pictures_no)//设置图片加载/解码过程中错误时候显示的图片
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(defaultOptions)//
                .discCacheSize(DISK_CACHE_SIZE_BYTES)
                .memoryCacheSize(MEMORY_CACHE_SIZE_BYTES)
                .build();

        ImageLoader tmpIL = ImageLoader.getInstance();
        tmpIL.init(config);//全局初始化此配置
        return tmpIL;

    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    private static void setImageLoader(ImageLoader mImageLoader) {
        ImageLoaderTools.mImageLoader = mImageLoader;
    }

    //封装方法
    public void displayImage(String mResName, ImageView imageView) {
        if(mResName.startsWith("http://")){
            mImageLoader.displayImage(mResName, imageView);
        }else if(mResName.startsWith("assets://"))
        {
            mImageLoader.displayImage(mResName, imageView);
        }
        else if(mResName.startsWith("file://"))
        {
            mImageLoader.displayImage(mResName, imageView);
        }
        else if(mResName.startsWith("content://"))
        {
            mImageLoader.displayImage(mResName, imageView);
        }
        else if(mResName.startsWith("drawable://"))
        {
            mImageLoader.displayImage(mResName, imageView);
        }
        else{
            Uri uri = Uri.parse(mResName);
            imageView.setImageURI(uri);
        }

    }
}

