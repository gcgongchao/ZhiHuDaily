package com.general.android.zhihudaily.http;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by GeneralAndroid
 */
public class HttpTools {
    public static final String CHARSET="UTF-8";
    public static String getNetData(String url,Map<String,String> parameter){
        String result="";
        if (url == null || parameter == null) {
            return null;
        }
        if(parameter!=null){
            String data = getRequestData(parameter, "UTF-8").toString();
            url=url+"?"+data;
        }

        try {
            URL getUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) getUrl.openConnection();
            httpURLConnection.setConnectTimeout(10 * 1000);
            httpURLConnection.setReadTimeout(20 * 1000);
            httpURLConnection.setRequestMethod("GET");


            int response = httpURLConnection.getResponseCode(); // 获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                final InputStream inputStream = httpURLConnection.getInputStream();
                result = inputStream2String(inputStream);
                Log.v("HttpResponse",result);

            }else {


                Log.v("HttpResponseCode",response+"");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;

    }
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }
    /**
     * Function : 封装请求体信息 Param : params请求体内容，encode编码格式
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer(); // 存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            if(stringBuffer.length()>1)
            stringBuffer.deleteCharAt(stringBuffer.length() - 1); // 删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }
}
