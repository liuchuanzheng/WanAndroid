package com.liuchuanzheng.wanandroid.modules.home.adapters;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.modules.home.beans.HomeArticleListReaponseBean;

import java.util.List;


/**
 * 首页界面列表 适配器
 */

public class HomeAdapter extends BaseQuickAdapter<HomeArticleListReaponseBean.DataBean.DatasBean, BaseViewHolder> {

    public HomeAdapter(@Nullable List<HomeArticleListReaponseBean.DataBean.DatasBean> dataList) {
        super(R.layout.item_homepage, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeArticleListReaponseBean.DataBean.DatasBean item) {
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
            String classifyName = item.getSuperChapterName() + " / " + item.getChapterName();
            helper.setText(R.id.tv_type, classifyName);
        }
        if (item.getSuperChapterName().contains(mContext.getString(R.string.project))) {
            helper.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_tag, mContext.getString(R.string.project));
            helper.setTextColor(R.id.tv_tag, mContext.getResources().getColor(R.color.green));
            helper.setBackgroundRes(R.id.tv_tag, R.drawable.drawable_shape_green);
        } else if (item.getSuperChapterName().contains(mContext.getString(R.string.hot))) {
            helper.getView(R.id.tv_tag).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_tag, mContext.getString(R.string.hot));
            helper.setTextColor(R.id.tv_tag, mContext.getResources().getColor(R.color.red));
            helper.setBackgroundRes(R.id.tv_tag, R.drawable.drawable_shape_red);
        }
        helper.addOnClickListener(R.id.tv_type);
        helper.addOnClickListener(R.id.image_collect);
        helper.setImageResource(R.id.image_collect, item.isCollect() ? R.drawable.icon_collect : R.drawable.icon_no_collect);
    }

}
