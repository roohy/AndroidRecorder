package edu.usc.roohy.recorder;

import org.json.JSONObject;

/**
 * Created by r_she on 5/16/2016.
 */
public class uploader {
    public static JSONObject postFile(String url, String filePath, int id){
        String result = "";
//        Http

        JSONObject responseObject = null;

        return responseObject;
    }
}
/*
   public static JSONObject postFile(String url,String filePath,int id){
    String result="";
    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost(url);
    File file = new File(filePath);
    MultipartEntity mpEntity = new MultipartEntity();
    ContentBody cbFile = new FileBody(file, "image/jpeg");
    StringBody stringBody= null;
    JSONObject responseObject=null;
    try {
        stringBody = new StringBody(id+"");
        mpEntity.addPart("file", cbFile);
        mpEntity.addPart("id",stringBody);
        httpPost.setEntity(mpEntity);
        System.out.println("executing request " + httpPost.getRequestLine());
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();
        result=resEntity.toString();
        responseObject=new JSONObject(result);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return responseObject;
}

 */