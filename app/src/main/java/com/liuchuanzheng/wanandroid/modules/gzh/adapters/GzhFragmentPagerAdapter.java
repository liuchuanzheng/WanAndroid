package com.liuchuanzheng.wanandroid.modules.gzh.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.liuchuanzheng.wanandroid.modules.gzh.GzhDetailFragment;

import java.util.List;

/**
 * 公众号fragment 适配器
 */

public class GzhFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    GzhDetailFragment fragment;


    public GzhFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        if (fragments == null) {
            return 0;
        } else {
            return fragments.size();
        }
    }
    /**
     * 设置当前显示的fragment
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        fragment = (GzhDetailFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 获取当前显示的fragment
     */
    public GzhDetailFragment getCurrentFragment() {
        return fragment;
    }

}
