package com.ngo.lap11877_khanh.a360_h5_2;

import com.google.gson.annotations.SerializedName;

public class GameData {
    @SerializedName("imgIcon")
    private String mImageIcon;

    @SerializedName("utmGametitle")
    private String mUtmGameTitle;

    @SerializedName("openApp")
    private boolean mOpenApp;

    @SerializedName("playTimes")
    private String mPlayTimes;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("utmChoingaybtn")
    private String mPlayNow;

    @SerializedName("appDetailURL")
    private String mDetails;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("playUrl")
    private String mPlayUrl;

    @SerializedName("openWebview")
    private boolean mOpenWebView;

    @SerializedName("iconStatus")
    private String mIconStatus;

    @SerializedName("appname")
    private String mAppName;

    @SerializedName("topic")
    private String mTopic;

    @SerializedName("utmGameicon")
    private String mUtmGameIcon;

    @SerializedName("btnPlayDisplay")
    private boolean mPlayDisplay;

    public String getImageIcon() {
        return mImageIcon;
    }

    public String getUtmGameTitle() {
        return mUtmGameTitle;
    }

    public boolean isOpenApp() {
        return mOpenApp;
    }

    public String getPlayTimes() {
        return mPlayTimes;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPlayNow() {
        return mPlayNow;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPlayUrl() {
        return mPlayUrl;
    }

    public boolean isOpenWebView() {
        return mOpenWebView;
    }

    public String getIconStatus() {
        return mIconStatus;
    }

    public String getAppName() {
        return mAppName;
    }

    public String getTopic() {
        return mTopic;
    }

    public String getUtmGameIcon() {
        return mUtmGameIcon;
    }

    public boolean isPlayDisplay() {
        return mPlayDisplay;
    }
}