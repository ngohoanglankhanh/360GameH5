package com.ngo.lap11877_khanh.a360_h5_2;

public abstract class AbstractPaging implements EndlessScrollPaging {

    protected static final int DEFAULT_PAGE_SIZE = 30;

    protected final int mPageSize;

    protected boolean mHasNext = true;

    protected volatile boolean mIsLoading = false;

    public AbstractPaging() {
        this(DEFAULT_PAGE_SIZE);
    }

    public AbstractPaging(int pageSize) {
        mPageSize = pageSize;
    }

    public void setHasNext(boolean hasNext) {
        mHasNext = hasNext;
    }

    public void setLoading(boolean isLoading) {
        mIsLoading = isLoading;
    }

    @Override
    public boolean hasNext() {
        return mHasNext;
    }

    @Override
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public int getPageSize() {
        return mPageSize;
    }

    @Override
    public void reset() {
        mHasNext = true;
    }
}
