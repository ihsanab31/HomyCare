package com.sundevs.ihsan.homycare.view.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;
import com.sundevs.ihsan.homycare.HomyCare;
import com.sundevs.ihsan.homycare.R;
import com.sundevs.ihsan.homycare.adapter.BSAdapter;
import com.sundevs.ihsan.homycare.adapter.GridMenuItemAdapter;
import com.sundevs.ihsan.homycare.adapter.model.GridMenu;
import com.sundevs.ihsan.homycare.util.param.BaseUrl;
import com.sundevs.ihsan.homycare.util.pref.SessionManager;
import com.sundevs.ihsan.homycare.view.base.NormalActivity;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for Menu
 */
public class MainActivity extends NormalActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    @BindView(R.id.gv_menu)
    GridView gridViewMenu;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_no_hp)
    TextView tv_no_hp;
    @BindView(R.id.image_users)
    NetworkImageView image_user;
    @BindView(R.id.image_circle_user)
    CircleImageView circleImageView;
    private GridMenuItemAdapter gridMenuItemAdapter;
    private boolean gridMenuInitData;
    SessionManager session;
    Criteria criteria;
    SharedPreferences preferences;
    LocationManager locationManager;
    String provider;
    ImageLoader imageLoader;
    @Override
    protected int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataMenuGrid(GridMenu.getDummyGridMenuData());
        initDataUser();
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    private void initDataMenuGrid(List<GridMenu> gridMenuList) {
        gridMenuItemAdapter = new GridMenuItemAdapter();
        gridMenuItemAdapter.setData(gridMenuList);
        gridViewMenu.setAdapter(gridMenuItemAdapter);
        gridViewMenu.setOnItemClickListener(this);
        gridMenuInitData = true;
    }

    private void initDataUser(){
        preferences=
                getApplicationContext().getSharedPreferences("data", 0);
        String nama = preferences.getString("nama", null);
        String nohp = preferences.getString("no_hp", null);
        String image = preferences.getString("image", null);
        tv_name.setText(nama);
        tv_no_hp.setText(nohp);
        Picasso.with(this).load(BaseUrl.URL_BASE + "/" + image).noFade().into(circleImageView);
    }
    private void setDataMenuGrid(List<GridMenu> gridMenuList) {
        gridMenuItemAdapter.setData(gridMenuList);
        gridMenuItemAdapter.notifyDataSetChanged();
    }

    private void getGridMenuData() {

        if (!gridMenuInitData)
            initDataMenuGrid(GridMenu.getDummyGridMenuData());
        else
            setDataMenuGrid(GridMenu.getDummyGridMenuData());
    }

    @OnClick(R.id.image_circle_user)
    void profile(){
        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = null;
        switch (position) {
            case 0:
                startActivity(new Intent(getApplicationContext(), ListBabyActivity.class));
                break;
            case 1:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                break;
            case 2:
                startActivity(new Intent(getApplicationContext(), ExploreActivity.class));
                break;
            default:
                break;
        }

        if (intent != null) {
            startActivity(intent);
//            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        getGridMenuData();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void logoutUser() {
        session.logoutUser();
        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        initDataUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDataUser();
    }
}
