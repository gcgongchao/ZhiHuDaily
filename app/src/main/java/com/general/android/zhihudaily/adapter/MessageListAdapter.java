package com.general.android.zhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.general.android.zhihudaily.MessageDetailActivity;
import com.general.android.zhihudaily.R;
import com.general.android.zhihudaily.model.StoriesModel;
import com.general.android.zhihudaily.utils.ImageLoaderTools;

import java.util.ArrayList;

/**
 * Created by GeneralAndroid.
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<StoriesModel> mStoriesModels;
    private ImageLoaderTools mImageLoaderTools;
    public MessageListAdapter(Context mContext, ArrayList<StoriesModel> mStoriesModels) {
        this.mContext = mContext;
        this.mStoriesModels = mStoriesModels;
        mImageLoaderTools=ImageLoaderTools.getInstance(mContext);
    }
    @Override
    public MessageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.fragment_message_list_item,null);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageListAdapter.ViewHolder holder, final int position) {
        mImageLoaderTools.displayImage(mStoriesModels.get(position).getImages(),holder.mImageView);
        holder.mTextView.setText(mStoriesModels.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MessageDetailActivity.class);
                intent.putExtra("id",mStoriesModels.get(position).getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStoriesModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageView mImageView;
        public View itemView;
        public ViewHolder(View itemView) {
        super(itemView);
            this.itemView=itemView;
        mTextView=(TextView)itemView.findViewById(R.id.tv_message_title);
        mImageView=(ImageView)itemView.findViewById(R.id.iv_message_img);
    }
}
}
