package com.ngo.lap11877_khanh.a360_h5_2;

public class Game {
    private String mTitle;

    private String mTopic;

    private String mPlayTimes;

    private String mAppDetails;

    private String mIconStatus;

    private String mImageIcon;

    public Game(GameData mGameData){
        this.mTitle = mGameData.getTitle();
        this.mTopic = mGameData.getTopic();
        this.mPlayTimes = mGameData.getPlayTimes();
        this.mAppDetails = mGameData.getDetails();
        this.mIconStatus = mGameData.getIconStatus();
        this.mImageIcon = mGameData.getImageIcon();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTopic() {
        return mTopic;
    }

    public String getPlayTimes() {
        return mPlayTimes;
    }

    public String getAppDetails() {
        return mAppDetails;
    }

    public String getIconStatus() {
        return mIconStatus;
    }

    public String getImageIcon() {
        return mImageIcon;
    }
}
