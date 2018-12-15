package com.liuchuanzheng.wanandroid.modules.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.liuchuanzheng.wanandroid.R;
import com.liuchuanzheng.wanandroid.base.BaseMVPActivity;
import com.liuchuanzheng.wanandroid.base.Constant;
import com.liuchuanzheng.wanandroid.base.mvp.view.IBaseView;
import com.liuchuanzheng.wanandroid.modules.login.beans.LoginResponseBean;
import com.liuchuanzheng.wanandroid.modules.login.contracts.IContract;
import com.liuchuanzheng.wanandroid.modules.login.presenters.LoginActivityPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 刘传政
 * @date 2018/12/15 0015 20:34
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class LoginActivity extends BaseMVPActivity<IContract.Login.View, LoginActivityPresenter> {
    @BindView(R.id.et_ensure_username)
    EditText etEnsureUsername;
    @BindView(R.id.et_ensure_password)
    EditText etEnsurePassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    private String username, password;

    @Override
    protected void initMVP() {
        mView = new IContract.Login.View() {
            @Override
            public void onLogin(LoginResponseBean responseBean, int resultType, String errorMsg) {
                switch (resultType) {
                    case IBaseView.NET_ERROR:
                        ToastUtils.showLong(getString(R.string.net_error));
                        break;
                    case IBaseView.SERVER_ERROR:
                        break;
                    case IBaseView.SERVER_NORMAL_DATANO:
                        break;
                    case IBaseView.SERVER_NORMAL_DATAYES:
                        ToastUtils.showLong(getString(R.string.login_ok));
                        SPUtils.getInstance(Constant.USERINFO).put(Constant.USERNAME, responseBean.getData().getUsername());
                        SPUtils.getInstance(Constant.USERINFO).put(Constant.PASSWORD, responseBean.getData().getPassword());
                        SPUtils.getInstance(Constant.USERINFO).put(Constant.ISLOGIN, true);
                        break;
                }

            }

            @Override
            public void onComplete() {

            }
        };
        mPresenter = new LoginActivityPresenter(this, mView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void doYourself() {

    }


    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (check()) {
                    mPresenter.login(username, password);
                }
                break;
            case R.id.tv_register:
                break;
        }
    }

    private boolean check() {
        username = etEnsureUsername.getText().toString().trim();
        password = etEnsurePassword.getText().toString().trim();
        if (username.length() < 6 || password.length() < 6) {
            ToastUtils.showLong(getString(R.string.username_incorrect));
            return false;
        }
        return true;
    }
}
