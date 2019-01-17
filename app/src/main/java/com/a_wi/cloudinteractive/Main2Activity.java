package com.a_wi.cloudinteractive;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements ApiService.ServiceInterface {
    GridView mGridView;
    ApiService mApiService;
    ListAdapter mListAdapter;
    ArrayList<ListData> mListArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mApiService = new ApiService();
        mApiService.mServiceInterface = this;
        mApiService.getApiInformation();

        initObj();
        initAdapter();
    }

    private void initObj() {
        mGridView = findViewById(R.id.GridView);
    }

    private void enterListData(JSONObject jsonObjectData) {
        try {
            JSONArray jsonArray = jsonObjectData.getJSONArray("information");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String title = jsonObject.getString("title");
                String imgUrl = jsonObject.getString("thumbnailUrl");

                ListData listData = new ListData(id, title, imgUrl, jsonObject);
                mListArrayList.add(listData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //更新顯示線程
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initAdapter() {
        mListArrayList = new ArrayList<>();
        mListAdapter = new ListAdapter(this, mListArrayList);
        mGridView.setAdapter(mListAdapter);
    }

    //region ================================ ApiCallback ==========================================
    @Override
    public void ServiceInterfaceBackData(JSONObject object) throws JSONException {
        if (object != null) {
            switch (object.getString("ApiName")) {
                case "getApiInformation":
                    enterListData(object);
                    break;
            }
        }
    }

    @Override
    public void ServiceInterfaceBackDataError(String errorMessage, ApiService.ApiName apiName) {
        Toast.makeText(this, apiName + ": " + errorMessage, Toast.LENGTH_LONG).show();
    }
    //endregion
}
