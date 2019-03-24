package com.liuchuanzheng.wanandroid.modules.demo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPLoadFragment;
import com.liuchuanzheng.wanandroid.base.Constant;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.demo.adapters.DemoDetailFragmentAdapter;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoDetailResponseBean;
import com.liuchuanzheng.wanandroid.modules.demo.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.demo.presenters.DemoDetailFragmentPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 16:16
 * QQ:1052374416
 * 电话:18501231486
 * 作用:每个tab的内容列表
 * 注意事项:
 */
public class DemoDetailFragment extends BaseMVPLoadFragment<IContract.main_detail.View, DemoDetailFragmentPresenter> {
    @BindView(R.id.rv_system)
    RecyclerView rvSystem;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    Unbinder unbinder;
    private DemoDetailFragmentAdapter mAdapter;
    private List<DemoDetailResponseBean.DataBean.DatasBean> dataList = new ArrayList<>();
    /**
     * id 编号
     */
    private int id = -1;

    @Override
    protected void initMVP() {
        mView = new IContract.main_detail.View() {
            @Override
            public void onGetDetail(DemoDetailResponseBean responseBean, int resultType, String errorMsg, boolean isRefresh) {
                if (normalView != null) {
                    normalView.finishRefresh(1000);
                    normalView.finishLoadMore(1000);
                }
                switch (resultType) {
                    case IBaseView.NET_ERROR:
                        ToastUtils.showLong(getString(R.string.net_error));
                        showError(getString(R.string.net_error));
                        break;
                    case IBaseView.SERVER_ERROR:
                        break;
                    case IBaseView.SERVER_NORMAL_DATANO:
                        break;
                    case IBaseView.SERVER_NORMAL_DATAYES:
                        configRvData(responseBean, isRefresh);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new DemoDetailFragmentPresenter(this.getContext(), mView);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initYourself() {
        if (getArguments() != null) {
            id = getArguments().getInt(Constant.DEMO_FRAGMENT_ID);
        }
        mAdapter = new DemoDetailFragmentAdapter(dataList);
        rvSystem.setAdapter(mAdapter);
        rvSystem.setLayoutManager(new LinearLayoutManager(this.getContext()));
        normalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getDetail(0, id, true);
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getDetail(page + 1, id, false);
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getDetail(0, id, true);
    }

    public static DemoDetailFragment getInstance(int id) {
        DemoDetailFragment fragment = new DemoDetailFragment();
        Bundle args = new Bundle();
        args.putInt(Constant.DEMO_FRAGMENT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public void scrollToTop() {
        rvSystem.smoothScrollToPosition(0);
    }

    private void configRvData(DemoDetailResponseBean responseBean, boolean isRefresh) {
        if (isRefresh) {
            page = 0;
            dataList.clear();
            dataList.addAll(responseBean.getData().getDatas());
        } else {
            page++;
            dataList.addAll(responseBean.getData().getDatas());
        }
        mAdapter.notifyDataSetChanged();
        if (dataList != null && dataList.size() != 0) {
            showNormal();
        } else {
            showEmpty();
        }

    }
}
