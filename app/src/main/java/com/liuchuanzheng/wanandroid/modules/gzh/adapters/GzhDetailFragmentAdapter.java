package com.liuchuanzheng.wanandroid.modules.gzh.adapters;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.modules.gzh.beans.GzhDetailResponseBean;

import java.util.List;

public class GzhDetailFragmentAdapter extends BaseQuickAdapter<GzhDetailResponseBean.DataBean.DatasBean, BaseViewHolder> {

    public GzhDetailFragmentAdapter(@Nullable List<GzhDetailResponseBean.DataBean.DatasBean> dataList) {
        super(R.layout.item_homepage, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, GzhDetailResponseBean.DataBean.DatasBean item) {
        helper.getView(R.id.tv_tag).setVisibility(View.GONE);
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_content, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_author, item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_time, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getChapterName())) {
            helper.setText(R.id.tv_type, item.getSuperChapterName());
        }
        helper.setImageResource(R.id.image_collect, item.isCollect() ? R.drawable.icon_collect : R.drawable.icon_no_collect);
    }
}
