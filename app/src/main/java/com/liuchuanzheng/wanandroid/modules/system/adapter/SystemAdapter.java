package com.liuchuanzheng.wanandroid.modules.system.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.modules.system.beans.SystemListResponseBean;

import java.util.List;


/**
 * 体系界面列表 适配器
 */

public class SystemAdapter extends BaseQuickAdapter<SystemListResponseBean.DataBean, BaseViewHolder> {

    public SystemAdapter(@Nullable List<SystemListResponseBean.DataBean> dataList) {
        super(R.layout.item_system, dataList);
    }

    @Override
    protected void convert(BaseViewHolder helper, SystemListResponseBean.DataBean item) {
        helper.setText(R.id.tv_knowledge_title, item.getName());
        StringBuilder sb = new StringBuilder();
        for (SystemListResponseBean.DataBean.ChildrenBean childrenBean : item.getChildren()) {
            sb.append(childrenBean.getName()).append("      ");
        }
        helper.setText(R.id.tv_knowledge_content, sb.toString());
    }
}
