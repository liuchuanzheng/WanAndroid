package com.liuchuanzheng.wanandroid.modules.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPLoadFragment;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.home.adapters.HomeAdapter;
import com.liuchuanzheng.wanandroid.modules.home.beans.BannerResponseBean;
import com.liuchuanzheng.wanandroid.modules.home.beans.HomeArticleListReaponseBean;
import com.liuchuanzheng.wanandroid.modules.home.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.home.presenters.HomeFragmentPresenter;
import com.liuchuanzheng.wanandroid.utils.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 16:16
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class HomeFragment extends BaseMVPLoadFragment<IContract.main.View, HomeFragmentPresenter> {
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    Unbinder unbinder;
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder1;
    private HomeAdapter mAdapter;
    private List<HomeArticleListReaponseBean.DataBean.DatasBean> dataList = new ArrayList<>();
    private Banner banner;
    private LinearLayout bannerView;
    private List<String> linkList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void initMVP() {
        mView = new IContract.main.View() {
            @Override
            public void onGetHomeList(HomeArticleListReaponseBean responseBean, int resultType, String errorMsg, boolean isRefresh) {
                if (normalView != null) {
                    normalView.finishRefresh(1000);
                    normalView.finishLoadMore();
                }
                switch (resultType) {
                    case IBaseView.NET_ERROR:
                        ToastUtils.showLong(getString(R.string.net_error));
                        if (dataList == null && dataList.size() == 0) {
                            showError(getString(R.string.net_error));
                        }
                        break;
                    case IBaseView.SERVER_ERROR:
                        if (dataList == null && dataList.size() == 0) {
                            showError(errorMsg);
                        }
                        break;
                    case IBaseView.SERVER_NORMAL_DATANO:
                        if (dataList == null && dataList.size() == 0) {
                            showEmpty();
                        }
                        break;
                    case IBaseView.SERVER_NORMAL_DATAYES:
                        configRvData(responseBean, isRefresh);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onGetBanner(BannerResponseBean responseBean, int resultType, String errorMsg) {
                switch (resultType) {
                    case IBaseView.NET_ERROR:
                        ToastUtils.showLong(getString(R.string.net_error));
                        break;
                    case IBaseView.SERVER_ERROR:
                        break;
                    case IBaseView.SERVER_NORMAL_DATANO:
                        break;
                    case IBaseView.SERVER_NORMAL_DATAYES:
                        configBanner(responseBean);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new HomeFragmentPresenter(this.getContext(), mView);

    }

    private void configRvData(HomeArticleListReaponseBean responseBean, boolean isRefresh) {
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

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initYourself() {
        bannerView = (LinearLayout) getLayoutInflater().inflate(R.layout.view_banner, null);
        banner = bannerView.findViewById(R.id.banner);
        bannerView.removeView(banner);
        bannerView.addView(banner);

        mAdapter = new HomeAdapter(dataList);
        mAdapter.addHeaderView(bannerView);
        rv.setAdapter(mAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mPresenter.getHomeList(0, true);
        mPresenter.getBanner();
        normalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHomeList(0, true);
                mPresenter.getBanner();
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.getHomeList(page + 1, false);
            }
        });
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }

    public void configBanner(BannerResponseBean responseBean) {
        if (responseBean.getData() == null || responseBean.getData().size() == 0) {
            return;
        }
        imageList.clear();
        titleList.clear();
        linkList.clear();

        for (BannerResponseBean.DataBean dataBean : responseBean.getData()) {
            imageList.add(dataBean.getImagePath());
            titleList.add(dataBean.getTitle());
            linkList.add(dataBean.getUrl());
        }

        if (!getFragmentManager().isDestroyed()) {
            // banner git 地址 https://github.com/youth5201314/banner
            banner.setImageLoader(new GlideImageLoader())
                    .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                    .setImages(imageList)
                    .setBannerAnimation(Transformer.Accordion)
                    .setBannerTitles(titleList)
                    .isAutoPlay(true)
                    .setDelayTime(4000)
                    .setIndicatorGravity(BannerConfig.RIGHT)
                    .start();
        }
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }

    public void scrollToTop() {
        rv.smoothScrollToPosition(0);
    }
}
