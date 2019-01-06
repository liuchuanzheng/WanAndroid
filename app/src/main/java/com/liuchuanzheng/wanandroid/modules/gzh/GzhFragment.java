package com.liuchuanzheng.wanandroid.modules.gzh;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.flyco.tablayout.SlidingTabLayout;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPLoadFragment;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.gzh.adapters.GzhFragmentPagerAdapter;
import com.liuchuanzheng.wanandroid.modules.gzh.beans.TitlesResponseBean;
import com.liuchuanzheng.wanandroid.modules.gzh.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.gzh.presenters.GzhFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 16:16
 * QQ:1052374416
 * 电话:18501231486
 * 作用:公众号
 * 注意事项:
 */
public class GzhFragment extends BaseMVPLoadFragment<IContract.main.View, GzhFragmentPresenter> {
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    Unbinder unbinder;
    private GzhFragmentPagerAdapter adapter;

    @Override
    protected void initMVP() {
        mView = new IContract.main.View() {
            @Override
            public void onGetTitleList(TitlesResponseBean responseBean, int resultType, String errorMsg) {
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
        mPresenter = new GzhFragmentPresenter(this.getContext(), mView);

    }

    private void configData(TitlesResponseBean responseBean) {
        titles.clear();
        fragmentList.clear();
        if (responseBean.getData().size() > 0) {
            for (TitlesResponseBean.DataBean dataBean : responseBean.getData()) {
                titles.add(dataBean.getName());
                fragmentList.add(GzhDetailFragment.getInstance(dataBean.getId()));
                viewPager.setAdapter(adapter);
                tabLayout.setViewPager(viewPager, titles.toArray(new String[titles.size()]));
                adapter.notifyDataSetChanged();
                viewPager.setOffscreenPageLimit(0);//记数从0开始!!!
            }
        }
        showNormal();
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_gzh;
    }

    @Override
    protected void initYourself() {
        adapter = new GzhFragmentPagerAdapter(getChildFragmentManager(), fragmentList);
        mPresenter.getTitleList();
    }

    public static GzhFragment getInstance() {
        return new GzhFragment();
    }

    public void scrollToTop() {
        ((GzhDetailFragment) (adapter.getCurrentFragment())).scrollToTop();
    }

}
