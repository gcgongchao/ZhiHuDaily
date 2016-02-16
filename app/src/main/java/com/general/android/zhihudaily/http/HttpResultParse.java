package com.general.android.zhihudaily.http;

import com.general.android.zhihudaily.model.MessageDetailsModel;
import com.general.android.zhihudaily.model.StoriesModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by GeneralAndroid.
 */
public class HttpResultParse {
    /**获取新闻列表**/
    public static ArrayList<StoriesModel> getMessageList(String date){
        ArrayList<StoriesModel> mStoriesModels=new ArrayList<>();
        String url=HttpConstants.ZHIHU_DAILY_BEFORE_MESSAGE+date;
        String result=HttpTools.getNetData(url,new HashMap<String, String>());
        try{
            JSONObject mJsonObject=new JSONObject(result);
            JSONArray stories=mJsonObject.getJSONArray("stories");
            for(int i=0;i<stories.length();i++){
                StoriesModel mStoriesModel=new StoriesModel();
                JSONObject temp=stories.getJSONObject(i);
                if(temp.has("id")){
                    mStoriesModel.setId(temp.getString("id"));
                }
                if(temp.has("type")){
                    mStoriesModel.setType(temp.getString("type"));
                }
                if(temp.has("ga_prefix")){
                    mStoriesModel.setGa_prefix(temp.getString("ga_prefix"));
                }
                if(temp.has("title")){
                    mStoriesModel.setTitle(temp.getString("title"));
                }
                if(temp.has("images")){
                    mStoriesModel.setImages(temp.getJSONArray("images").get(0).toString());
                }
               mStoriesModels.add(mStoriesModel);

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return  mStoriesModels;
    }
    /**获取新闻详情**/
    public static MessageDetailsModel getMessageDetail(String id){
        MessageDetailsModel messageDetailsModel=null;
        String url=HttpConstants.ZHIHU_DAILY_BEFORE_MESSAGE_DETAIL+id;
        String result=HttpTools.getNetData(url,new HashMap<String, String>());
        try{
            if(result!=null&& result.length()>0){
                JSONObject mJsonObject=new JSONObject(result);
                messageDetailsModel=new MessageDetailsModel();
                if(mJsonObject.has("body")){
                    messageDetailsModel.setBody(mJsonObject.getString("body"));
                }
                if(mJsonObject.has("image_source")){
                    messageDetailsModel.setImage_source(mJsonObject.getString("image_source"));
                }
                if(mJsonObject.has("title")){
                    messageDetailsModel.setTitle(mJsonObject.getString("title"));
                }
                if(mJsonObject.has("image")){
                    messageDetailsModel.setImage(mJsonObject.getString("image"));
                }
                if(mJsonObject.has("type")){
                    messageDetailsModel.setType(mJsonObject.getString("type"));
                }
                if(mJsonObject.has("id")){
                    messageDetailsModel.setId(mJsonObject.getString("id"));
                }
                if(mJsonObject.has("recommenders")){
                    JSONArray mJsonArray=mJsonObject.getJSONArray("recommenders");
                    ArrayList<String> avatars=new ArrayList<String>();
                    for(int i=0;i<mJsonArray.length();i++){
                        JSONObject temp=mJsonArray.getJSONObject(i);
                        if(temp.has("avatar")){
                            avatars.add(temp.getString("avatar"));
                        }

                    }
                }

            }
        }catch (Exception e){

        }

        return messageDetailsModel;
    }
}
