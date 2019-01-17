package com.a_wi.cloudinteractive;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiService {

//    定義Api字典檔
    public enum ApiName {
        getApiInformation
    }

//    定義Api回傳介面
    public interface ServiceInterface {
        void ServiceInterfaceBackData(JSONObject object) throws JSONException;

        void ServiceInterfaceBackDataError(String errorMessage, ApiName apiName);
    }

    private static String mApiUri = "https://jsonplaceholder.typicode.com/photos";
    public ServiceInterface mServiceInterface;

//    取得Api資料
    public void getApiInformation() {
        String url = mApiUri;
        getData(url, ApiName.getApiInformation);
    }

    private void getData(final String urlPath, final ApiName apiName) {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(urlPath)
                .get()
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String result = response.body()
                                .string();
                        try {
                            Object objectResult = new JSONTokener(result).nextValue();
                            if (objectResult instanceof JSONObject) {
                                JSONObject object = new JSONObject(result);
                                object.put("ApiName", apiName);
                                mServiceInterface.ServiceInterfaceBackData(object);
                            } else if (objectResult instanceof JSONArray) {
                                JSONArray jsonArray = new JSONArray(result);
                                JSONObject object = new JSONObject();
                                object.put("information", jsonArray);
                                object.put("ApiName", apiName);
                                mServiceInterface.ServiceInterfaceBackData(object);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        mServiceInterface.ServiceInterfaceBackDataError(String.valueOf(e), apiName);
                    }
                });
    }
}
