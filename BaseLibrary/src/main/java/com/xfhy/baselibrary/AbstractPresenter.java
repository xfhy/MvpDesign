package com.xfhy.baselibrary;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : xfhy
 * Create time : 2020/1/5 15:04
 * Description :
 */
public abstract class AbstractPresenter<T extends BaseView> implements BasePresenter<T>, InvocationHandler {

    private static final String TAG = "AbstractPresenter";
    private T mView;
    protected T mViewProxy;

    @Override
    public void setView(T view) {
        this.mView = view;
        Class<?>[] interfaces = view.getClass().getInterfaces();
        for (Class<?> anInterface : interfaces) {
            if (BaseView.class == anInterface) {
                mViewProxy = (T) Proxy.newProxyInstance(anInterface.getClassLoader(), new Class[]{anInterface}, this);
                break;
            }
        }
        if (mViewProxy == null) {
            mViewProxy = (T) Proxy.newProxyInstance(BaseView.class.getClassLoader(), new Class[]{BaseView.class}, this);
        }
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (mView == null || method == null) {
            Log.w(TAG, "view 为null 不执行方法");
            return null;
        }
        Log.w(TAG, "执行方法");
        return method.invoke(mView, args);
    }
}
