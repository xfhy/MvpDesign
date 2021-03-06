package com.xfhy.baselibrary;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author : xfhy
 * Create time : 2020/1/5 15:05
 * Description : 基类Activity
 */
public abstract class BaseMvpActivity<T extends IPresenter> extends FragmentActivity implements IBaseView {

    protected T mPresenter;
    private boolean mFlagDestroyed = false;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        mFlagDestroyed = false;

        mPresenter = getPresenter();
        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFlagDestroyed = true;
        mContext = null;
        mPresenter = null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public boolean isViewDestroy() {
        return mFlagDestroyed;
    }

    /**
     * 初始化Presenter
     *
     * @return 返回真实的Presenter
     */
    public abstract T getPresenter();
}
