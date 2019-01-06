package com.liuchuanzheng.wanandroid.modules.main;

import android.Manifest;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPActivity;
import com.liuchuanzheng.wanandroid.modules.gzh.GzhFragment;
import com.liuchuanzheng.wanandroid.modules.home.HomeFragment;
import com.liuchuanzheng.wanandroid.modules.main.adapter.MyMainPagerAdapter;
import com.liuchuanzheng.wanandroid.modules.main.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.main.fragment.MineFragment;
import com.liuchuanzheng.wanandroid.modules.main.presenters.MainActivityPresenter;
import com.liuchuanzheng.wanandroid.modules.system.SystemFragment;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseMVPActivity<IContract.main.View, MainActivityPresenter> {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.main_content)
    CoordinatorLayout mainContent;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    public List<String> mTitleDataList;
    private final int[] normalResId = new int[]{
            R.drawable.ic_home_theme_24dp,
            R.drawable.ic_dashboard_theme_24dp,
            R.drawable.ic_wx_theme,
            R.drawable.ic_create_new_folder_theme_24dp,
            R.drawable.ic_notifications_theme_24dp
    };
    private final int[] pressedResId = new int[]{
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_dashboard_black_24dp,
            R.drawable.ic_wx_title,
            R.drawable.ic_create_new_folder_black_24dp,
            R.drawable.ic_notifications_black_24dp
    };
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    List<Fragment> fragmentList = new ArrayList<>();
    private int lastIndex = 0;
    private long mExitTime;

    @Override
    protected void initMVP() {
        mView = new IContract.main.View() {
            @Override
            public void onGet() {

            }

            @Override
            public void onComplete() {

            }
        };

        mPresenter = new MainActivityPresenter(this, mView);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        requestPermissions();
        initViewPager();
        initMagicIndicator();
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(R.string.app_name);

    }

    private void initViewPager() {
        fragmentList.add(HomeFragment.getInstance());
        fragmentList.add(SystemFragment.getInstance());
        fragmentList.add(GzhFragment.getInstance());
        fragmentList.add(new MineFragment());
        fragmentList.add(new MineFragment());
        MyMainPagerAdapter myMainPagerAdapter = new MyMainPagerAdapter(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(myMainPagerAdapter);
        vp.setOffscreenPageLimit(0);//记数从0开始!!!
    }

    /**
     * 创建 menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * menu 选择器
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_hot:
                break;
            case R.id.main_menu_search:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initMagicIndicator() {
        //设置底部导航栏
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add("首页");
        mTitleDataList.add("体系");
        mTitleDataList.add("公众号");
        mTitleDataList.add("项目");
        mTitleDataList.add("我的");
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true); //ture 即标题平分屏幕宽度的模式
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {

                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                CommonPagerTitleView titleView = new CommonPagerTitleView(context);
                titleView.setContentView(R.layout.item_main_tab_indicator_layout);//加载自定义布局作为Tab

                final Button tab_btn = (Button) titleView.findViewById(R.id.tab_btn);
                TextView tv_title = titleView.findViewById(R.id.tv_title);
                tv_title.setText(mTitleDataList.get(index));
                titleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int i, int i1) {
                        tab_btn.setBackgroundResource(pressedResId[i]);
                        vp.setCurrentItem(index);
                        getSupportActionBar().setTitle(mTitleDataList.get(index));
                        lastIndex = i;
                    }

                    @Override
                    public void onDeselected(int i, int i1) {
                        tab_btn.setBackgroundResource(normalResId[i]);
                    }

                    @Override
                    public void onLeave(int i, int i1, float v, boolean b) {

                    }

                    @Override
                    public void onEnter(int i, int i1, float v, boolean b) {

                    }
                });
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        magicIndicator.onPageSelected(index);
                        magicIndicator.onPageScrolled(index, 0.0F, 0);
                    }
                });

                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.RED);
                return indicator;
            }


        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp);
    }

    @Override
    protected void initData() {
        // mPresenter.get();
    }

    @Override
    protected void doYourself() {

    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(MainActivity.this);
        rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』

                        }
                    }
                });
    }


    @OnClick(R.id.fab)
    public void onViewClicked() {
//        ActivityUtils.startActivity(LoginActivity.class, null);
        scrollToTop(lastIndex);
    }

    /**
     * 滚动置顶
     *
     * @param lastIndex
     */
    private void scrollToTop(int lastIndex) {
        switch (lastIndex) {
            case 0:
                HomeFragment homeFragment = (HomeFragment) fragmentList.get(0);
                homeFragment.scrollToTop();
                break;
            case 1:
                SystemFragment systemFragment = (SystemFragment) fragmentList.get(1);
                systemFragment.scrollToTop();
                break;
            case 2:
                GzhFragment gzhFragment = (GzhFragment) fragmentList.get(2);
                gzhFragment.scrollToTop();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort(getString(R.string.exit_system));
            mExitTime = System.currentTimeMillis();
            return false;
        } else {
            finish();
            return true;
        }
    }
}
