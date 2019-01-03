package com.liuchuanzheng.wanandroid.modules.home.activitys;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.just.agentweb.AgentWeb;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseActivity;
import com.liuchuanzheng.wanandroid.base.Constant;

import butterknife.BindView;

/**
 * @author 刘传政
 * @date 2018/12/15 0015 17:06
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class HomeDetailAcivity extends BaseActivity {
    @BindView(R.id.article_toolbar)
    Toolbar articleToolbar;
    @BindView(R.id.article_detail_web_view)
    FrameLayout articleDetailWebView;
    @BindView(R.id.article_detail_group)
    LinearLayout articleDetailGroup;
    private AgentWeb agentWeb;
    private String title;
    private String detailLink;
    private int detailId;
    private boolean isCollect;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_detail;
    }

    @Override
    protected void initView() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();

        setSupportActionBar(articleToolbar);

    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            title = bundle.getString(Constant.HOME_DETAIL_TITLE);
            getSupportActionBar().setTitle(title);
            detailLink = bundle.getString(Constant.HOME_DETAIL_PATH);
            detailId = bundle.getInt(Constant.HOME_DETAIL_ID, Constant.REQUEST_ERROR);
            isCollect = bundle.getBoolean(Constant.HOME_DETAIL_IS_COLLECT);
        }
        getSupportActionBar().setTitle(title);
        articleToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 共享元素 退出
                ActivityCompat.finishAfterTransition(HomeDetailAcivity.this);
            }
        });
    }

    @Override
    protected void doYourself() {
        agentWeb = AgentWeb.with(this)
                //传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .setAgentWebParent(articleDetailWebView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()// 使用默认进度条
                .createAgentWeb()//
                .ready()
                .go(detailLink);
        WebView mWebView = agentWeb.getWebCreator().getWebView();
        WebSettings mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);
        mSettings.setSupportZoom(true);
        mSettings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        mSettings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        mSettings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        mSettings.setLoadWithOverviewMode(true);
        //自适应屏幕
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //让agentweb 只能处理事件
        if (agentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
