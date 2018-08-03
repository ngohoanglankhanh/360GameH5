package com.ngo.lap11877_khanh.a360_h5_2;

import java.util.List;

public interface NewsView {
    void showNewsList(List<News> mNews);

    void showProgressIndicator();

    void hideProgressIndicator();

    void showNoNetworkState();

    void hideNoNetworkState();
}
