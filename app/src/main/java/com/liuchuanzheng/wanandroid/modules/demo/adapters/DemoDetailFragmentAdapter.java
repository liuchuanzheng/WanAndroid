package com.liuchuanzheng.wanandroid.modules.demo.adapters;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.modules.demo.beans.DemoDetailResponseBean;

import java.util.List;

public class DemoDetailFragmentAdapter extends BaseQuickAdapter<DemoDetailResponseBean.DataBean.DatasBean, BaseViewHolder> {

    public DemoDetailFragmentAdapter(@Nullable List<DemoDetailResponseBean.DataBean.DatasBean> dataList) {
        super(R.layout.item_demo_list, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, DemoDetailResponseBean.DataBean.DatasBean item) {
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.tv_title, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getDesc())) {
            helper.setText(R.id.tv_content, item.getDesc());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.tv_time, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.tv_author, item.getAuthor());
        }
        Glide.with(mContext).load(item.getEnvelopePic()).into((ImageView) helper.getView(R.id.image_simple));
    }
}
