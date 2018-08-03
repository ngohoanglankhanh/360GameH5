package com.ngo.lap11877_khanh.a360_h5_2;

public class NormalPaging extends AbstractPaging{
    private int mCurrentPage;

    public NormalPaging() {
        this(DEFAULT_PAGE_SIZE);
    }

    public NormalPaging(int pageSize) {
        this(0, pageSize);
    }

    public NormalPaging(int currentPage, int pageSize) {
        super(pageSize);
        mCurrentPage = currentPage;
    }

    public int getCurrent() {
        return mCurrentPage;
    }

    public int getNext() {
        return mCurrentPage + 1;
    }

    public void next() {
        mCurrentPage = mCurrentPage + 1;
    }

    public boolean isFirstPage() {
        return mCurrentPage == 1;
    }

    @Override
    public boolean isBeforeFirstPage() {
        return mCurrentPage == 0;
    }

    @Override
    public void reset() {
        super.reset();
        mCurrentPage = 0;
    }
}
