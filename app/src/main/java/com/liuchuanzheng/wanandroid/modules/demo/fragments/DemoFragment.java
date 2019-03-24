package com.liuchuanzheng.wanandroid.modules.demo.fragments;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPLoadFragment;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.demo.adapters.DemoFragmentPagerAdapter;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoTitlesResponseBean;
import com.liuchuanzheng.wanandroid.modules.demo.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.demo.presenters.DemoFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 16:16
 * QQ:1052374416
 * 电话:18501231486
 * 作用:demo
 * 注意事项:
 */
public class DemoFragment extends BaseMVPLoadFragment<IContract.main.View, DemoFragmentPresenter> {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    Unbinder unbinder;
    private DemoFragmentPagerAdapter adapter;

    public static DemoFragment getInstance() {
        return new DemoFragment();
    }

    @Override
    protected void initMVP() {
        mView = new IContract.main.View() {
            @Override
            public void onGetTitleList(DemoTitlesResponseBean responseBean, int resultType, String errorMsg) {
                switch (resultType) {
                    case IBaseView.NET_ERROR:
                        ToastUtils.showLong(getString(R.string.net_error));
                        showError(getString(R.string.net_error));
                        break;
                    case IBaseView.SERVER_ERROR:
                        ToastUtils.showLong(responseBean.getErrorMsg());
                        showError(errorMsg);
                        break;
                    case IBaseView.SERVER_NORMAL_DATANO:
                        ToastUtils.showLong(getString(R.string.data_null));
                        showError(errorMsg);
                        break;
                    case IBaseView.SERVER_NORMAL_DATAYES:
                        configData(responseBean);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new DemoFragmentPresenter(this.getContext(), mView);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_demo;
    }

    @Override
    protected void initYourself() {
        adapter = new DemoFragmentPagerAdapter(getChildFragmentManager(), fragmentList);

    }

    @Override
    protected void lazyLoadData() {
        mPresenter.getTitleList();
    }


    private void configData(DemoTitlesResponseBean responseBean) {
        titles.clear();
        fragmentList.clear();
        if (responseBean.getData().size() > 0) {
            for (DemoTitlesResponseBean.DataBean dataBean : responseBean.getData()) {
                titles.add(dataBean.getName());
                fragmentList.add(DemoDetailFragment.getInstance(dataBean.getId()));
                viewPager.setAdapter(adapter);
                tabLayout.setViewPager(viewPager, titles.toArray(new String[titles.size()]));
                adapter.notifyDataSetChanged();
                viewPager.setOffscreenPageLimit(2);
            }
        }
        showNormal();
    }

    public void scrollToTop() {
        ((DemoDetailFragment) (adapter.getCurrentFragment())).scrollToTop();
    }

}
