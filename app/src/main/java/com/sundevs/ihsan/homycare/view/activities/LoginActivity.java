package com.sundevs.ihsan.homycare.view.activities;

import android.content.Intent;
import android.os.Bundle;

import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.util.pref.SessionManager;
import com.sundevs.ihsan.homycare.view.base.NoActionBarConfig;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;
import com.sundevs.ihsan.homycare.view.dialog.LoginDialog;

import butterknife.OnClick;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for Login
 */
public class LoginActivity extends NormalActivity {
    SessionManager session;

    @Override
    protected int getActivityView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoActionBarConfig noActionBarConfig = new NoActionBarConfig();
        noActionBarConfig.fullScreen(this);
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.btn_login)
    void login() {
        LoginDialog loginDialog = new LoginDialog(this);
        loginDialog.show();
    }

    @OnClick(R.id.btn_daftar)
    void daftar() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }
}
