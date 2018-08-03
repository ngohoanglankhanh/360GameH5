package com.ngo.lap11877_khanh.a360_h5_2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsPackage {
    @SerializedName("msg")
    private String mMsg;

    @SerializedName("err")
    private String mErr;

    @SerializedName("data")
    private List<NewsData> mData;

    @SerializedName("errorMessage")
    private String mErrorMessage;

    @SerializedName("resultCode")
    private String mResultCode;

    @SerializedName("errorCode")
    private String mErrorCode;

    @SerializedName("resultMessage")
    private String mResultMessage;

    @SerializedName("error")
    private String mError;

    @SerializedName("message")
    private String mMessage;

    public String getMsg() {
        return mMsg;
    }

    public String getErr() {
        return mErr;
    }

    public List<NewsData> getData() {
        return mData;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public String getResultCode() {
        return mResultCode;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public String getResultMessage() {
        return mResultMessage;
    }

    public String getError() {
        return mError;
    }

    public String getMessage() {
        return mMessage;
    }
}
