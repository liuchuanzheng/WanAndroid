package com.liuchuanzheng.wanandroid.modules.system;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPLoadFragment;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.system.adapters.SystemAdapter;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;
import com.liuchuanzheng.wanandroid.modules.system.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.system.presenters.SystemFragmentPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * @author 刘传政
 * @date 2018/8/13 0013 16:16
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class SystemFragment extends BaseMVPLoadFragment<IContract.main.View, SystemFragmentPresenter> {
    @BindView(R.id.rv_system)
    RecyclerView rvSystem;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    Unbinder unbinder;
    private SystemAdapter mAdapter;
    private List<SystemListResponseBean.DataBean> dataList = new ArrayList<>();

    @Override
    protected void initMVP() {
        mView = new IContract.main.View() {
            @Override
            public void onGetSysTemList(SystemListResponseBean responseBean, int resultType, String errorMsg) {
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
                        dataList.clear();
                        dataList.addAll(responseBean.getData());
                        mAdapter.notifyDataSetChanged();
                        showNormal();
                        break;
                }
            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new SystemFragmentPresenter(this.getContext(), mView);

    }

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initYourself() {
        mAdapter = new SystemAdapter(dataList);
        rvSystem.setAdapter(mAdapter);
        rvSystem.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mPresenter.getSysTemList();
    }

    public static SystemFragment getInstance() {
        return new SystemFragment();
    }

    public void scrollToTop() {
        rvSystem.smoothScrollToPosition(0);
    }
}
