package com.ngo.lap11877_khanh.a360_h5_2;

public interface EndlessScrollPaging {
    boolean hasNext();

    boolean isLoading();

    int getPageSize();

    boolean isBeforeFirstPage();

    void reset();
}
