package com.xfhy.mvpdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.xfhy.baselibrary.BaseMvpActivity;
import com.xfhy.mvpdemo.mvp.contract.MainContract;
import com.xfhy.mvpdemo.mvp.presenter.TodayPresenter;

public class MainActivity extends BaseMvpActivity<MainContract.Presenter> implements MainContract.View, View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView mTvLoadStatus, mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public MainContract.Presenter getPresenter() {
        return new TodayPresenter();
    }

    private void initView() {
        findViewById(R.id.btn_get_data).setOnClickListener(this);
        mTvLoadStatus = findViewById(R.id.tv_load_status);
        mTvContent = findViewById(R.id.tv_content);
    }

    @Override
    public void showLoading() {
        mTvLoadStatus.setText("加载中...");
    }

    @Override
    public void hideLoading() {
        mTvLoadStatus.setText("加载完成了.");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showErrorMsg(String msg) {
        mTvLoadStatus.setText("加载失败 " + msg);
    }

    @Override
    public void showContent(String content) {
        Log.w(TAG, "showContent: view被更新了" );
        mTvContent.setText(content);
    }

    @Override
    public void onClick(View v) {
        mPresenter.loadData();
    }
}
