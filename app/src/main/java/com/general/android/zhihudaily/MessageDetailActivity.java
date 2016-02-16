package com.general.android.zhihudaily;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.general.android.zhihudaily.http.HttpResultParse;
import com.general.android.zhihudaily.model.MessageDetailsModel;
import com.general.android.zhihudaily.utils.ImageLoaderTools;
import com.general.android.zhihudaily.utils.URLImageParser;

/**
 * Created by GeneralAndroid
 */
public class MessageDetailActivity extends BaseActivity{
    private TextView mTvBody;
    private String id;
    private GetMessageDetailsTask mGetMessageDetailsTask;
    private MessageDetailsModel messageDetailsModel;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private ImageView mImageView;
    private TextView mTvImgSource;
    private ImageLoaderTools mImageLoaderTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
        initData();
        initView();
        setUI();
        setListener();
    }

    @Override
    protected void initData() {

        id=getIntent().getStringExtra("id");
        mImageLoaderTools=ImageLoaderTools.getInstance(mContext);
        mGetMessageDetailsTask=new GetMessageDetailsTask();
        mGetMessageDetailsTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    protected void initView() {
        mTvBody=(TextView)findViewById(R.id.tv_body);
        mCollapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.ctl);
        mImageView=(ImageView)findViewById(R.id.iv_appbar);
        mTvImgSource=(TextView)findViewById(R.id.tv_img_source);
        mCoordinatorLayout=(CoordinatorLayout)findViewById(R.id.cdl);
    }

    @Override
    protected void setUI() {

    }

    @Override
    protected void setListener() {

    }
    private class GetMessageDetailsTask extends AsyncTask<Void,Void,Void> {
        private boolean isError;
        @Override
        protected Void doInBackground(Void... voids) {
            messageDetailsModel= HttpResultParse.getMessageDetail(id);
            if(messageDetailsModel!=null){
                isError=false;

            }else{
                isError=true;

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (isError) {
                Snackbar.make(mCoordinatorLayout, "网络异常，请检查你的网络！", Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }else{
                mTvBody.setMovementMethod(LinkMovementMethod.getInstance());
                mTvBody.setText(Html.fromHtml(messageDetailsModel.getBody(), new URLImageParser(mTvBody), null));
                mCollapsingToolbarLayout.setTitle(messageDetailsModel.getTitle());
                mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
                mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
                mImageLoaderTools.displayImage(messageDetailsModel.getImage(),mImageView);
                mTvImgSource.setText(messageDetailsModel.getImage_source());
            }


        }
    }




}
