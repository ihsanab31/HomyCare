package com.sundevs.ihsan.homycare.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.util.pref.SessionManager;
import com.sundevs.ihsan.homycare.view.base.NoActionBarConfig;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for Splash screen
 */
public class SplashScreenActivity extends NormalActivity {
    private static int intervalsplash = 2000;
    SessionManager session;
    @Override
    protected int getActivityView() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoActionBarConfig noActionBarConfig = new NoActionBarConfig();
        noActionBarConfig.fullScreen(this);
        session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();

            }

            private void finish() {
                // TODO Auto-generated method stub
            }
        }, intervalsplash);
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }
}

