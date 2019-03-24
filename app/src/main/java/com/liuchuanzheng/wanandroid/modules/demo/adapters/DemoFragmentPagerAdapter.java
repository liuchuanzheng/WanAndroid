package com.liuchuanzheng.wanandroid.modules.demo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.liuchuanzheng.wanandroid.modules.demo.fragments.DemoDetailFragment;

import java.util.List;

/**
 * demo fragment 适配器
 */

public class DemoFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;
    DemoDetailFragment fragment;


    public DemoFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
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
        fragment = (DemoDetailFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 获取当前显示的fragment
     */
    public DemoDetailFragment getCurrentFragment() {
        return fragment;
    }

}
