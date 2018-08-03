package com.ngo.lap11877_khanh.a360_h5_2;

import com.google.gson.annotations.SerializedName;

public class NewsData {
    @SerializedName("flag")
    private String mFlag;

    @SerializedName("appname")
    private String mAppName;

    @SerializedName("targetLink")
    private String mTargetLink;

    @SerializedName("createdTime")
    private String mDateCreated;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("appTitle")
    private String mAppTitle;

    @SerializedName("title")
    private String mTitle;

    public String getFlag() {
        return mFlag;
    }

    public String getAppName() {
        return mAppName;
    }

    public String getTargetLink() {
        return mTargetLink;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getAppTitle() {
        return mAppTitle;
    }

    public String getTitle() {
        return mTitle;
    }
}
