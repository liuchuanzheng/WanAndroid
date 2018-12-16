package com.liuchuanzheng.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuchuanzheng.wanandroid.R;

/**
 * @author 刘传政
 * @date 2018/12/16 0016 11:55
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public abstract class BaseLoadFragment extends BaseFragment {
    /**
     * 处理页面加载中、页面加载失败、页面没数据
     */
    private static final int NORMAL_STATE = 0;
    private static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;
    public static final int EMPTY_STATE = 3;
    /**
     * 当前状态
     */
    private int currentState = NORMAL_STATE;

    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private ViewGroup mNormalView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResID(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLoadUI();
        initYourself();
    }


    protected void initLoadUI() {
        if (getView() == null) {
            return;
        }
        mNormalView = getView().findViewById(R.id.normal_view);
        if (mNormalView == null) {
            throw new IllegalStateException("The subclass of RootActivity must contain a View named 'mNormalView'.");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("mNormalView's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(getActivity(), R.layout.view_loading, parent);
        View.inflate(getActivity(), R.layout.view_error, parent);
        View.inflate(getActivity(), R.layout.view_empty, parent);

        mLoadingView = parent.findViewById(R.id.loading_group);
        mErrorView = parent.findViewById(R.id.error_group);
        mEmptyView = parent.findViewById(R.id.empty_group);

        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.GONE);

        mNormalView.setVisibility(View.VISIBLE);
    }

    ;

    /**
     * 获取 布局信息
     *
     * @return
     */
    protected abstract int getLayoutResID();

    protected abstract void initYourself();

    public void showNormal() {
        if (currentState == NORMAL_STATE) {
            return;
        }
        hideCurrentView();
        currentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    public void showError(String err) {
        if (currentState == ERROR_STATE) {
            return;
        }
        hideCurrentView();
        currentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        if (currentState == LOADING_STATE) {
            return;
        }
        hideCurrentView();
        currentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public void showEmpty() {
        if (currentState == EMPTY_STATE) {
            return;
        }
        hideCurrentView();
        currentState = EMPTY_STATE;
        mEmptyView.setVisibility(View.VISIBLE);
    }


    private void hideCurrentView() {
        switch (currentState) {
            case NORMAL_STATE:
                if (mNormalView == null) {
                    return;
                }
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATE:
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                mErrorView.setVisibility(View.GONE);
                break;
            case EMPTY_STATE:
                mEmptyView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
