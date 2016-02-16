package com.general.android.zhihudaily.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.general.android.zhihudaily.R;
import com.general.android.zhihudaily.adapter.DividerLine;
import com.general.android.zhihudaily.adapter.MessageListAdapter;
import com.general.android.zhihudaily.http.HttpResultParse;
import com.general.android.zhihudaily.model.StoriesModel;

import java.util.ArrayList;

/**
 * Created by GeneralAndroid
 */
public class MessageListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    public static final String MESSAGE_DATE="date";
    private String mDate;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<StoriesModel> mStoriesModels=new ArrayList<StoriesModel>();
    private MessageListAdapter messageListAdapter;
    private GetMessageListWithDateTask mGetMessageListWithDateTask;
    public static MessageListFragment newInstance(String date){
        Bundle bundle=new Bundle();
        bundle.putString(MESSAGE_DATE, date);
        MessageListFragment messageListFragment=new MessageListFragment();
        messageListFragment.setArguments(bundle);
        return  messageListFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_message_list,null);
        initView();
        setListener();
        return view;
}

    @Override
    protected void initData() {
        mDate=getArguments().getString(MESSAGE_DATE);
        messageListAdapter=new MessageListAdapter(mContext,mStoriesModels);
        mGetMessageListWithDateTask=new GetMessageListWithDateTask();
        mGetMessageListWithDateTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    protected void initView() {
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.rv_message_list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerLine(mContext,DividerLine.VERTICAL_LIST));
        mRecyclerView.setAdapter(messageListAdapter);
    }

    @Override
    protected void setUI() {

    }

    @Override
    protected void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mGetMessageListWithDateTask=new GetMessageListWithDateTask();
        mGetMessageListWithDateTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private class GetMessageListWithDateTask extends AsyncTask<Void,Void,Void>{
        private boolean isError;
        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<StoriesModel> temp= HttpResultParse.getMessageList(mDate);
            if(temp.size()>0){
                isError=false;
                if(mStoriesModels.size()>0){
                    mStoriesModels.clear();
                }
                mStoriesModels.addAll(temp);
            }else{
                isError=true;

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (isError) {
                Snackbar.make(view, "网络异常，请检查你的网络！", Snackbar.LENGTH_LONG).setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }

            messageListAdapter.notifyDataSetChanged();
            if(mSwipeRefreshLayout.isRefreshing()){
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

}
