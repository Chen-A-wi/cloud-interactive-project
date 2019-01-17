package com.a_wi.cloudinteractive;

import org.json.JSONObject;

public class ListData {
    String mId, mTitle, mImgUrl;
    JSONObject mInformationData;

    public ListData(String id, String title, String imgUrl, JSONObject jsonObject) {
        setId(id);
        setTitle(title);
        setImgUrl(imgUrl);
        setInformation(jsonObject);
    }

    //region ================================ Get Method ===========================================
    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public JSONObject getInformation() {
        return mInformationData;
    }
    //endregion

    //region ================================== Set Method =========================================
    public void setId(String id) {
        this.mId = id;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setImgUrl(String imgUrl) {
        this.mImgUrl = imgUrl;
    }

    public void setInformation(JSONObject informationData) {
        this.mInformationData = informationData;
    }

    //endregion
}
