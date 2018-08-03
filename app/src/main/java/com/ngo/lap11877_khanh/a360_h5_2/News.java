package com.ngo.lap11877_khanh.a360_h5_2;

public class News {
    private String mTitle;

    private String mLink;

    private String mDateCreated;

    public News(NewsData mNewsData) {
        this.mTitle = mNewsData.getTitle();
        this.mLink = mNewsData.getTargetLink();
        this.mDateCreated = mNewsData.getDateCreated();
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getDateCreated() {
        return mDateCreated;
    }
}
